package wannabit.io.cosmostaion.sign

import com.google.gson.JsonObject
import com.google.protobuf.ByteString
import com.sui.rpc.v2.ArgumentProto
import com.sui.rpc.v2.InputProto
import com.sui.rpc.v2.TransactionProto
import org.bouncycastle.util.encoders.Base64

/**
 * Maps the JSON produced by the dApp SDK's `Transaction.serialize()` / `Transaction.toJSON()`
 * (a `$kind`-tagged tree of inputs/commands, where object inputs may still be UNresolved -
 * only an objectId, no version/digest) directly into a `sui.rpc.v2.TransactionKind`
 * (ProgrammableTransaction) proto message.
 *
 * No object resolution or Move-type inference happens here - unresolved object inputs are
 * passed through with just their objectId set. The fullnode resolves them (and selects gas)
 * server-side when this Transaction is used in a SimulateTransactionRequest with
 * do_gas_selection = true. Verified live against mainnet: the server correctly infers
 * owned vs shared kind, version/digest, and even the mutable flag from the target Move
 * function's signature.
 */
fun mapProgrammableTransactionKind(txJson: JsonObject): TransactionProto.TransactionKind {
    val ptb = TransactionProto.ProgrammableTransaction.newBuilder()

    txJson["inputs"]?.takeIf { it.isJsonArray }?.asJsonArray?.forEach { element ->
        ptb.addInputs(mapSuiInput(element.asJsonObject))
    }
    txJson["commands"]?.takeIf { it.isJsonArray }?.asJsonArray?.forEach { element ->
        ptb.addCommands(mapSuiCommand(element.asJsonObject))
    }

    return TransactionProto.TransactionKind.newBuilder()
        .setKind(TransactionProto.TransactionKind.Kind.PROGRAMMABLE_TRANSACTION)
        .setProgrammableTransaction(ptb)
        .build()
}

private fun mapSuiInput(input: JsonObject): InputProto.Input {
    val builder = InputProto.Input.newBuilder()

    when {
        input.has("Pure") -> {
            val bytes = input["Pure"].asJsonObject["bytes"].asString
            builder.kind = InputProto.Input.InputKind.PURE
            builder.pure = ByteString.copyFrom(Base64.decode(bytes))
        }

        input.has("UnresolvedObject") -> {
            val obj = input["UnresolvedObject"].asJsonObject
            builder.objectId = obj["objectId"].asString
            // Intentionally leave kind/version/digest unset when not already known -
            // the fullnode resolves these server-side during SimulateTransaction.
            obj.optString("version")?.toLongOrNull()?.let { builder.version = it }
            obj.optString("digest")?.let { builder.digest = it }
            obj.optString("initialSharedVersion")?.toLongOrNull()?.let {
                builder.kind = InputProto.Input.InputKind.SHARED
                builder.version = it
            }
        }

        input.has("Object") -> {
            val objWrapper = input["Object"].asJsonObject
            when {
                objWrapper.has("ImmOrOwnedObject") -> {
                    val o = objWrapper["ImmOrOwnedObject"].asJsonObject
                    builder.kind = InputProto.Input.InputKind.IMMUTABLE_OR_OWNED
                    builder.objectId = o["objectId"].asString
                    builder.version = o["version"].asString.toLong()
                    builder.digest = o["digest"].asString
                }

                objWrapper.has("SharedObject") -> {
                    val o = objWrapper["SharedObject"].asJsonObject
                    builder.kind = InputProto.Input.InputKind.SHARED
                    builder.objectId = o["objectId"].asString
                    builder.version = o["initialSharedVersion"].asString.toLong()
                    builder.mutable = o["mutable"].asBoolean
                }

                objWrapper.has("Receiving") -> {
                    val o = objWrapper["Receiving"].asJsonObject
                    builder.kind = InputProto.Input.InputKind.RECEIVING
                    builder.objectId = o["objectId"].asString
                    builder.version = o["version"].asString.toLong()
                    builder.digest = o["digest"].asString
                }

                else -> throw IllegalArgumentException("Unsupported Object input: $objWrapper")
            }
        }

        else -> throw IllegalArgumentException("Unsupported transaction input: $input")
    }

    return builder.build()
}

private fun mapSuiCommand(command: JsonObject): TransactionProto.Command {
    val builder = TransactionProto.Command.newBuilder()

    when {
        command.has("MoveCall") -> {
            val c = command["MoveCall"].asJsonObject
            val moveCall = TransactionProto.MoveCall.newBuilder()
                .setPackage(c["package"].asString)
                .setModule(c["module"].asString)
                .setFunction(c["function"].asString)
            c["typeArguments"]?.takeIf { it.isJsonArray }?.asJsonArray?.forEach {
                moveCall.addTypeArguments(it.asString)
            }
            c["arguments"]?.takeIf { it.isJsonArray }?.asJsonArray?.forEach {
                moveCall.addArguments(mapSuiArgument(it.asJsonObject))
            }
            builder.moveCall = moveCall.build()
        }

        command.has("TransferObjects") -> {
            val c = command["TransferObjects"].asJsonObject
            val transferObjects = TransactionProto.TransferObjects.newBuilder()
                .setAddress(mapSuiArgument(c["address"].asJsonObject))
            c["objects"]?.takeIf { it.isJsonArray }?.asJsonArray?.forEach {
                transferObjects.addObjects(mapSuiArgument(it.asJsonObject))
            }
            builder.transferObjects = transferObjects.build()
        }

        command.has("SplitCoins") -> {
            val c = command["SplitCoins"].asJsonObject
            val splitCoins = TransactionProto.SplitCoins.newBuilder()
                .setCoin(mapSuiArgument(c["coin"].asJsonObject))
            c["amounts"]?.takeIf { it.isJsonArray }?.asJsonArray?.forEach {
                splitCoins.addAmounts(mapSuiArgument(it.asJsonObject))
            }
            builder.splitCoins = splitCoins.build()
        }

        command.has("MergeCoins") -> {
            val c = command["MergeCoins"].asJsonObject
            val mergeCoins = TransactionProto.MergeCoins.newBuilder()
                .setCoin(mapSuiArgument(c["destination"].asJsonObject))
            c["sources"]?.takeIf { it.isJsonArray }?.asJsonArray?.forEach {
                mergeCoins.addCoinsToMerge(mapSuiArgument(it.asJsonObject))
            }
            builder.mergeCoins = mergeCoins.build()
        }

        command.has("MakeMoveVec") -> {
            val c = command["MakeMoveVec"].asJsonObject
            val makeMoveVec = TransactionProto.MakeMoveVector.newBuilder()
            c["type"]?.takeIf { !it.isJsonNull }?.asString?.let { makeMoveVec.elementType = it }
            c["elements"]?.takeIf { it.isJsonArray }?.asJsonArray?.forEach {
                makeMoveVec.addElements(mapSuiArgument(it.asJsonObject))
            }
            builder.makeMoveVector = makeMoveVec.build()
        }

        command.has("Publish") -> {
            val c = command["Publish"].asJsonObject
            val publish = TransactionProto.Publish.newBuilder()
            c["modules"]?.takeIf { it.isJsonArray }?.asJsonArray?.forEach {
                publish.addModules(ByteString.copyFrom(Base64.decode(it.asString)))
            }
            c["dependencies"]?.takeIf { it.isJsonArray }?.asJsonArray?.forEach {
                publish.addDependencies(it.asString)
            }
            builder.publish = publish.build()
        }

        command.has("Upgrade") -> {
            val c = command["Upgrade"].asJsonObject
            val upgrade = TransactionProto.Upgrade.newBuilder()
                .setPackage(c["package"].asString)
                .setTicket(mapSuiArgument(c["ticket"].asJsonObject))
            c["modules"]?.takeIf { it.isJsonArray }?.asJsonArray?.forEach {
                upgrade.addModules(ByteString.copyFrom(Base64.decode(it.asString)))
            }
            c["dependencies"]?.takeIf { it.isJsonArray }?.asJsonArray?.forEach {
                upgrade.addDependencies(it.asString)
            }
            builder.upgrade = upgrade.build()
        }

        else -> throw IllegalArgumentException("Unsupported transaction command: $command")
    }

    return builder.build()
}

private fun mapSuiArgument(argument: JsonObject): ArgumentProto.Argument {
    val builder = ArgumentProto.Argument.newBuilder()
    when {
        argument.has("GasCoin") -> builder.kind = ArgumentProto.Argument.ArgumentKind.GAS
        argument.has("Input") -> {
            builder.kind = ArgumentProto.Argument.ArgumentKind.INPUT
            builder.input = argument["Input"].asInt
        }

        argument.has("Result") -> {
            builder.kind = ArgumentProto.Argument.ArgumentKind.RESULT
            builder.result = argument["Result"].asInt
        }

        argument.has("NestedResult") -> {
            val pair = argument["NestedResult"].asJsonArray
            builder.kind = ArgumentProto.Argument.ArgumentKind.RESULT
            builder.result = pair[0].asInt
            builder.subresult = pair[1].asInt
        }

        else -> throw IllegalArgumentException("Unsupported transaction argument: $argument")
    }
    return builder.build()
}

private fun JsonObject.optString(key: String): String? {
    return this[key]?.takeIf { !it.isJsonNull }?.asString
}
