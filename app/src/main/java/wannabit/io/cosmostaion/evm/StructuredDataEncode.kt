package wannabit.io.cosmostaion.evm

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.web3j.abi.TypeEncoder
import org.web3j.abi.datatypes.AbiTypes
import org.web3j.abi.datatypes.Type.MAX_BYTE_LENGTH
import org.web3j.crypto.Hash.sha3
import org.web3j.crypto.Hash.sha3String
import org.web3j.utils.Numeric
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.lang.reflect.InvocationTargetException
import java.math.BigInteger
import java.util.Locale
import java.util.StringJoiner
import java.util.regex.Matcher
import java.util.regex.Pattern


class StructuredDataEncode(jsonMessageInString: String) {
    private var mapper: ObjectMapper =
        ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
    private val jsonMessageObject: StructuredData.EIP712Message

    private val arrayTypeRegex = "^([a-zA-Z_$][a-zA-Z_$0-9]*)((\\[([1-9]\\d*)?\\])+)$"
    private val arrayTypePattern = Pattern.compile(arrayTypeRegex)

    private val arrayDimensionRegex = "\\[([1-9]\\d*)?\\]"
    private val arrayDimensionPattern = Pattern.compile(arrayDimensionRegex)

    private val bytesTypeRegex = "^bytes[0-9][0-9]?$"
    private val bytesTypePattern = Pattern.compile(bytesTypeRegex)

    private val typeRegex = "^[a-zA-Z_$][a-zA-Z_$0-9]*(\\[([1-9]\\d*)*\\])*$"
    private val typePattern = Pattern.compile(typeRegex)

    private val identifierRegex = "^[a-zA-Z_$][a-zA-Z_$0-9]*$"
    private val identifierPattern = Pattern.compile(identifierRegex)

    init {
        try {
            jsonMessageObject = parseJSONMessage(jsonMessageInString)
        } catch (e: IOException) {
            throw RuntimeException("Error parsing JSON message", e)
        }
    }

    @Throws(java.lang.RuntimeException::class)
    fun validateStructuredData(jsonMessageObject: StructuredData.EIP712Message) {
        for (structName in jsonMessageObject.types.keys) {
            val fields = jsonMessageObject.types[structName]
            for (entry in fields!!) {
                if (!identifierPattern.matcher(entry.name).find()) {
                    throw java.lang.RuntimeException(
                        String.format(
                            "Invalid Identifier %s in %s", entry.name, structName
                        )
                    )
                }
                if (!typePattern.matcher(entry.type).find()) {
                    throw java.lang.RuntimeException(
                        String.format(
                            "Invalid Type %s in %s", entry.type, structName
                        )
                    )
                }
            }
        }
    }

    @Throws(IOException::class, java.lang.RuntimeException::class)
    fun parseJSONMessage(jsonMessageInString: String?): StructuredData.EIP712Message {
        val tempJSONMessageObject: StructuredData.EIP712Message = mapper.readValue(
            jsonMessageInString, StructuredData.EIP712Message::class.java
        )
        validateStructuredData(tempJSONMessageObject)
        return tempJSONMessageObject
    }

    @Throws(java.lang.RuntimeException::class)
    fun hashStructuredData(): ByteArray {
        return sha3(getStructuredData())
    }

    @Throws(java.lang.RuntimeException::class)
    fun getStructuredData(): ByteArray? {
        val baos = ByteArrayOutputStream()
        val messagePrefix = "\u0019\u0001"
        val prefix = messagePrefix.toByteArray()
        baos.write(prefix, 0, prefix.size)
        val domainHash: ByteArray = hashDomain()
        baos.write(domainHash, 0, domainHash.size)
        val dataHash: ByteArray = hashMessage(
            jsonMessageObject.primaryType, jsonMessageObject.message as HashMap<String?, Any?>
        )
        baos.write(dataHash, 0, dataHash.size)
        return baos.toByteArray()
    }

    @Throws(java.lang.NumberFormatException::class, java.lang.NullPointerException::class)
    private fun convertToBigInt(value: Any): BigInteger {
        return if (value.toString().startsWith("0x")) {
            Numeric.toBigInt(value.toString())
        } else {
            BigInteger(value.toString())
        }
    }

    @Throws(java.lang.RuntimeException::class)
    fun hashDomain(): ByteArray {
        val data: java.util.HashMap<*, *> =
            mapper.convertValue(jsonMessageObject.domain, HashMap::class.java)
        return sha3(encodeData("EIP712Domain", data))
    }

    @Throws(java.lang.RuntimeException::class)
    fun hashMessage(primaryType: String?, data: HashMap<String?, Any?>): ByteArray {
        primaryType?.let { primary ->
            return sha3(encodeData(primary, data))
        }
        return byteArrayOf()
    }

    @Throws(java.lang.RuntimeException::class)
    fun encodeData(primaryType: String, data: java.util.HashMap<*, *>): ByteArray? {
        val types = jsonMessageObject.types
        val encTypes: MutableList<String> = ArrayList()
        val encValues: MutableList<Any> = ArrayList()

        encTypes.add("bytes32")
        encValues.add(typeHash(primaryType))

        for (field: StructuredData.Entry in types[primaryType]!!) {
            val value = data[field.name] ?: continue
            if (field.type == "string") {
                encTypes.add("bytes32")
                val hashedValue = Numeric.hexStringToByteArray(sha3String(value as String?))
                encValues.add(hashedValue)
            } else if (field.type == "bytes") {
                encTypes.add("bytes32")
                encValues.add(sha3(Numeric.hexStringToByteArray(value as String?)))
            } else if (types.containsKey(field.type)) {
                val hashedValue = sha3(encodeData(field.type, value as HashMap<String?, Any?>))
                encTypes.add("bytes32")
                encValues.add(hashedValue)
            } else if (bytesTypePattern.matcher(field.type).find()) {
                encTypes.add(field.type)
                encValues.add(Numeric.hexStringToByteArray(value as String?))
            } else if (arrayTypePattern.matcher(field.type).find()) {
                val baseTypeName = field.type.substring(0, field.type.indexOf('['))
                val arrayItems: List<Any> = getArrayItems(field, value)
                val concatenatedArrayEncodingBuffer = ByteArrayOutputStream()
                for (arrayItem: Any in arrayItems) {
                    var arrayItemEncoding: ByteArray
                    if (types.containsKey(baseTypeName)) {
                        arrayItemEncoding = sha3(
                            encodeData(
                                baseTypeName, arrayItem as HashMap<String?, Any?>
                            )
                        )
                    } else {
                        arrayItemEncoding = convertToEncodedItem(
                            baseTypeName, arrayItem
                        )
                    }
                    concatenatedArrayEncodingBuffer.write(
                        arrayItemEncoding, 0, arrayItemEncoding.size
                    )
                }
                val concatenatedArrayEncodings = concatenatedArrayEncodingBuffer.toByteArray()
                val hashedValue = sha3(concatenatedArrayEncodings)
                encTypes.add("bytes32")
                encValues.add(hashedValue)
            } else if (field.type.startsWith("uint") || field.type.startsWith("int")) {
                encTypes.add(field.type)
                try {
                    encValues.add(convertToBigInt(value))
                } catch (e: NumberFormatException) {
                    encValues.add(
                        value
                    )
                } catch (e: NullPointerException) {
                    encValues.add(
                        value
                    )
                }
            } else {
                encTypes.add(field.type)
                encValues.add(value)
            }
        }
        val baos = ByteArrayOutputStream()
        for (i in encTypes.indices) {
            val typeClazz = AbiTypes.getType(encTypes[i])
            var atleastOneConstructorExistsForGivenParametersType = false
            val constructors = typeClazz.constructors
            for (constructor in constructors) {
                // Check which constructor matches
                try {
                    val parameterTypes: Array<Class<*>> = constructor.parameterTypes
                    val temp = Numeric.hexStringToByteArray(
                        TypeEncoder.encode(
                            typeClazz.getDeclaredConstructor(*parameterTypes)
                                .newInstance(encValues[i])
                        )
                    )
                    baos.write(temp, 0, temp.size)
                    atleastOneConstructorExistsForGivenParametersType = true
                    break
                } catch (ignored: IllegalArgumentException) {
                } catch (ignored: NoSuchMethodException) {
                } catch (ignored: InstantiationException) {
                } catch (ignored: IllegalAccessException) {
                } catch (ignored: InvocationTargetException) {
                }
            }
            if (!atleastOneConstructorExistsForGivenParametersType) {
                throw java.lang.RuntimeException(
                    String.format(
                        "Received an invalid argument for which no constructor" + " exists for the ABI Class %s",
                        typeClazz.getSimpleName()
                    )
                )
            }
        }
        return baos.toByteArray()
    }

    private fun getArrayItems(field: StructuredData.Entry, value: Any): List<Any> {
        val expectedDimensions: List<Int> = getArrayDimensionsFromDeclaration(field.type)
        val dataDimensions: List<Int> = getArrayDimensionsFromData(value)
        val format = String.format(
            "Array Data %s has dimensions %s, " + "but expected dimensions are %s",
            value.toString(),
            dataDimensions.toString(),
            expectedDimensions.toString()
        )
        if (expectedDimensions.size != dataDimensions.size) {
            // Ex: Expected a 3d array, but got only a 2d array
            throw java.lang.RuntimeException(format)
        }
        for (i in expectedDimensions.indices) {
            if (expectedDimensions[i] == -1) {
                // Skip empty or dynamically declared dimensions
                continue
            }
            if (expectedDimensions[i] != dataDimensions[i]) {
                throw java.lang.RuntimeException(format)
            }
        }
        return flattenMultidimensionalArray(value)
    }

    private fun flattenMultidimensionalArray(data: Any): List<Any> {
        if (data !is List<*>) {
            return listOf(data)
        }

        val flattenedArray = mutableListOf<Any>()
        for (arrayItem in data) {
            flattenedArray.addAll(flattenMultidimensionalArray(arrayItem!!))
        }
        return flattenedArray
    }

    private fun encodeStruct(structName: String): String? {
        val types = jsonMessageObject.types
        val structRepresentation = StringJoiner(
            ",", "$structName(", ")"
        )
        for (entry in types[structName]!!) {
            structRepresentation.add(String.format("%s %s", entry.type, entry.name))
        }
        return structRepresentation.toString()
    }

    private fun encodeType(primaryType: String): String {
        val deps = getDependencies(primaryType).toMutableSet()
        deps.remove(primaryType)

        // Sort the other dependencies based on Alphabetical Order and finally add the primaryType
        val depsAsList = deps.toList().sorted().toMutableList()
        depsAsList.add(0, primaryType)

        val result = StringBuilder()
        for (structName in depsAsList) {
            result.append(encodeStruct(structName))
        }
        return result.toString()
    }

    private fun typeHash(primaryType: String): ByteArray {
        return Numeric.hexStringToByteArray(sha3String(encodeType(primaryType)))
    }

    private fun getArrayDimensionsFromDeclaration(declaration: String?): List<Int> {
        val arrayTypeMatcher = declaration?.let { arrayTypePattern.matcher(it) }
        arrayTypeMatcher?.find()
        val dimensionTypeMatcher: Matcher? = declaration?.let { arrayDimensionPattern.matcher(it) }
        val dimensions: MutableList<Int> = ArrayList()
        while (dimensionTypeMatcher?.find() == true) {
            val currentDimension = dimensionTypeMatcher.group(1)
            if (currentDimension == null) {
                dimensions.add("-1".toInt())
            } else {
                dimensions.add(currentDimension.toInt())
            }
        }
        return dimensions
    }

    fun getDepthsAndDimensions(data: Any?, depth: Int): List<org.web3j.crypto.Pair?> {
        if (data !is List<*>) {
            return ArrayList()
        }
        val list: MutableList<org.web3j.crypto.Pair?> = ArrayList()
        list.add(org.web3j.crypto.Pair(depth, data.size))
        for (subdimensionalData in data) {
            list.addAll(getDepthsAndDimensions(subdimensionalData, depth + 1))
        }
        return list
    }

    private fun convertToEncodedItem(baseType: String, data: Any): ByteArray {
        var hashBytes: ByteArray
        try {
            if (baseType.lowercase(Locale.getDefault()).startsWith("uint") || baseType.lowercase(
                    Locale.getDefault()
                ).startsWith("int")
            ) {
                val value: BigInteger = convertToBigInt(data)
                if (value.signum() >= 0) {
                    hashBytes = Numeric.toBytesPadded(convertToBigInt(data), MAX_BYTE_LENGTH)
                } else {
                    val signPadding = 0xff.toByte()
                    val rawValue: ByteArray = convertToBigInt(data).toByteArray()
                    hashBytes = ByteArray(MAX_BYTE_LENGTH)
                    for (i in hashBytes.indices) {
                        hashBytes[i] = signPadding
                    }
                    System.arraycopy(
                        rawValue, 0, hashBytes, MAX_BYTE_LENGTH - rawValue.size, rawValue.size
                    )
                }
            } else if (baseType == "string") {
                hashBytes = Numeric.hexStringToByteArray(sha3String(data as String))
            } else if (baseType == "bytes") {
                hashBytes = sha3(Numeric.hexStringToByteArray(data as String))
            } else {
                val b: ByteArray = convertArgToBytes(data as String)
                val bi = BigInteger(1, b)
                hashBytes = Numeric.toBytesPadded(bi, 32)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            hashBytes = ByteArray(0)
        }
        return hashBytes
    }

    private fun getArrayDimensionsFromData(data: Any): List<Int> {
        val depthsAndDimensions = getDepthsAndDimensions(data, 0)
        val groupedByDepth = depthsAndDimensions.groupBy { it?.first }

        val depthDimensionsMap = mutableMapOf<Int, List<Int>>()
        for ((key, value) in groupedByDepth) {
            val pureDimensions = value.map { it?.second as Int }
            depthDimensionsMap[key as Int] = pureDimensions
        }

        val dimensions = mutableListOf<Int>()
        for ((key, value) in depthDimensionsMap) {
            val setOfDimensionsInParticularDepth = value.toSortedSet()
            if (setOfDimensionsInParticularDepth.size != 1) {
                throw RuntimeException("Depth $key of array data has more than one dimension")
            }
            dimensions.add(setOfDimensionsInParticularDepth.first())
        }

        return dimensions
    }

    private fun getDependencies(primaryType: String): MutableSet<String> {
        val types = jsonMessageObject.types
        val deps: MutableSet<String> = HashSet()
        if (!types.containsKey(primaryType)) {
            return deps
        }
        val remainingTypes: MutableList<String> = ArrayList()
        remainingTypes.add(primaryType)
        while (remainingTypes.size > 0) {
            val structName = remainingTypes[remainingTypes.size - 1]
            remainingTypes.removeAt(remainingTypes.size - 1)
            deps.add(structName)
            for (entry in types[structName]!!) {
                val declarationFieldTypeName = entry.type
                val baseDeclarationTypeName = if (arrayTypePattern.matcher(declarationFieldTypeName)
                        .find()
                ) declarationFieldTypeName.substring(
                    0, declarationFieldTypeName.indexOf('[')
                ) else declarationFieldTypeName
                if (!types.containsKey(baseDeclarationTypeName)) {
                    // Don't expand on non-user defined types
                } else if (deps.contains(baseDeclarationTypeName)) {
                    // Skip types which are already expanded
                } else {
                    // Encountered a user defined type
                    remainingTypes.add(baseDeclarationTypeName)
                }
            }
        }
        return deps
    }

    @Throws(java.lang.Exception::class)
    private fun convertArgToBytes(inputValue: String): ByteArray {
        var hexValue = inputValue
        if (!Numeric.containsHexPrefix(inputValue)) {
            val value: BigInteger = try {
                BigInteger(inputValue)
            } catch (e: java.lang.NumberFormatException) {
                BigInteger(inputValue, 16)
            }
            hexValue = Numeric.toHexStringNoPrefix(value.toByteArray())
            if (hexValue.length > 64 && hexValue.startsWith("00")) {
                hexValue = hexValue.substring(2)
            }
        }
        return Numeric.hexStringToByteArray(hexValue)
    }
}