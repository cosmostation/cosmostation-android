package com.babylon.btcstaking.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the Msg service.
 * TODO: handle unbonding tx with full witness
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: babylon/btcstaking/v1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "babylon.btcstaking.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProvider,
      com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProviderResponse> getCreateFinalityProviderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateFinalityProvider",
      requestType = com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProvider.class,
      responseType = com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProviderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProvider,
      com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProviderResponse> getCreateFinalityProviderMethod() {
    io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProvider, com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProviderResponse> getCreateFinalityProviderMethod;
    if ((getCreateFinalityProviderMethod = MsgGrpc.getCreateFinalityProviderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateFinalityProviderMethod = MsgGrpc.getCreateFinalityProviderMethod) == null) {
          MsgGrpc.getCreateFinalityProviderMethod = getCreateFinalityProviderMethod =
              io.grpc.MethodDescriptor.<com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProvider, com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProviderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateFinalityProvider"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProvider.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProviderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateFinalityProvider"))
              .build();
        }
      }
    }
    return getCreateFinalityProviderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProvider,
      com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProviderResponse> getEditFinalityProviderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EditFinalityProvider",
      requestType = com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProvider.class,
      responseType = com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProviderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProvider,
      com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProviderResponse> getEditFinalityProviderMethod() {
    io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProvider, com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProviderResponse> getEditFinalityProviderMethod;
    if ((getEditFinalityProviderMethod = MsgGrpc.getEditFinalityProviderMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getEditFinalityProviderMethod = MsgGrpc.getEditFinalityProviderMethod) == null) {
          MsgGrpc.getEditFinalityProviderMethod = getEditFinalityProviderMethod =
              io.grpc.MethodDescriptor.<com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProvider, com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProviderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EditFinalityProvider"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProvider.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProviderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("EditFinalityProvider"))
              .build();
        }
      }
    }
    return getEditFinalityProviderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegation,
      com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegationResponse> getCreateBTCDelegationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateBTCDelegation",
      requestType = com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegation.class,
      responseType = com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegation,
      com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegationResponse> getCreateBTCDelegationMethod() {
    io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegation, com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegationResponse> getCreateBTCDelegationMethod;
    if ((getCreateBTCDelegationMethod = MsgGrpc.getCreateBTCDelegationMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateBTCDelegationMethod = MsgGrpc.getCreateBTCDelegationMethod) == null) {
          MsgGrpc.getCreateBTCDelegationMethod = getCreateBTCDelegationMethod =
              io.grpc.MethodDescriptor.<com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegation, com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateBTCDelegation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegation.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateBTCDelegation"))
              .build();
        }
      }
    }
    return getCreateBTCDelegationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProof,
      com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProofResponse> getAddBTCDelegationInclusionProofMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddBTCDelegationInclusionProof",
      requestType = com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProof.class,
      responseType = com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProofResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProof,
      com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProofResponse> getAddBTCDelegationInclusionProofMethod() {
    io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProof, com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProofResponse> getAddBTCDelegationInclusionProofMethod;
    if ((getAddBTCDelegationInclusionProofMethod = MsgGrpc.getAddBTCDelegationInclusionProofMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddBTCDelegationInclusionProofMethod = MsgGrpc.getAddBTCDelegationInclusionProofMethod) == null) {
          MsgGrpc.getAddBTCDelegationInclusionProofMethod = getAddBTCDelegationInclusionProofMethod =
              io.grpc.MethodDescriptor.<com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProof, com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProofResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddBTCDelegationInclusionProof"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProof.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProofResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddBTCDelegationInclusionProof"))
              .build();
        }
      }
    }
    return getAddBTCDelegationInclusionProofMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigs,
      com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigsResponse> getAddCovenantSigsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddCovenantSigs",
      requestType = com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigs.class,
      responseType = com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigs,
      com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigsResponse> getAddCovenantSigsMethod() {
    io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigs, com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigsResponse> getAddCovenantSigsMethod;
    if ((getAddCovenantSigsMethod = MsgGrpc.getAddCovenantSigsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddCovenantSigsMethod = MsgGrpc.getAddCovenantSigsMethod) == null) {
          MsgGrpc.getAddCovenantSigsMethod = getAddCovenantSigsMethod =
              io.grpc.MethodDescriptor.<com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigs, com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddCovenantSigs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddCovenantSigs"))
              .build();
        }
      }
    }
    return getAddCovenantSigsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegate,
      com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegateResponse> getBTCUndelegateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BTCUndelegate",
      requestType = com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegate.class,
      responseType = com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegate,
      com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegateResponse> getBTCUndelegateMethod() {
    io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegate, com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegateResponse> getBTCUndelegateMethod;
    if ((getBTCUndelegateMethod = MsgGrpc.getBTCUndelegateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBTCUndelegateMethod = MsgGrpc.getBTCUndelegateMethod) == null) {
          MsgGrpc.getBTCUndelegateMethod = getBTCUndelegateMethod =
              io.grpc.MethodDescriptor.<com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegate, com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BTCUndelegate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BTCUndelegate"))
              .build();
        }
      }
    }
    return getBTCUndelegateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidence,
      com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidenceResponse> getSelectiveSlashingEvidenceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SelectiveSlashingEvidence",
      requestType = com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidence.class,
      responseType = com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidenceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidence,
      com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidenceResponse> getSelectiveSlashingEvidenceMethod() {
    io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidence, com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidenceResponse> getSelectiveSlashingEvidenceMethod;
    if ((getSelectiveSlashingEvidenceMethod = MsgGrpc.getSelectiveSlashingEvidenceMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSelectiveSlashingEvidenceMethod = MsgGrpc.getSelectiveSlashingEvidenceMethod) == null) {
          MsgGrpc.getSelectiveSlashingEvidenceMethod = getSelectiveSlashingEvidenceMethod =
              io.grpc.MethodDescriptor.<com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidence, com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidenceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SelectiveSlashingEvidence"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidence.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidenceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SelectiveSlashingEvidence"))
              .build();
        }
      }
    }
    return getSelectiveSlashingEvidenceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgUpdateParams,
      com.babylon.btcstaking.v1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateParams",
      requestType = com.babylon.btcstaking.v1.TxProto.MsgUpdateParams.class,
      responseType = com.babylon.btcstaking.v1.TxProto.MsgUpdateParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgUpdateParams,
      com.babylon.btcstaking.v1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod() {
    io.grpc.MethodDescriptor<com.babylon.btcstaking.v1.TxProto.MsgUpdateParams, com.babylon.btcstaking.v1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod;
    if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
          MsgGrpc.getUpdateParamsMethod = getUpdateParamsMethod =
              io.grpc.MethodDescriptor.<com.babylon.btcstaking.v1.TxProto.MsgUpdateParams, com.babylon.btcstaking.v1.TxProto.MsgUpdateParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.TxProto.MsgUpdateParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.btcstaking.v1.TxProto.MsgUpdateParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateParams"))
              .build();
        }
      }
    }
    return getUpdateParamsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MsgStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgStub>() {
        @java.lang.Override
        public MsgStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgStub(channel, callOptions);
        }
      };
    return MsgStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MsgBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgBlockingStub>() {
        @java.lang.Override
        public MsgBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgBlockingStub(channel, callOptions);
        }
      };
    return MsgBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MsgFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgFutureStub>() {
        @java.lang.Override
        public MsgFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgFutureStub(channel, callOptions);
        }
      };
    return MsgFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Msg defines the Msg service.
   * TODO: handle unbonding tx with full witness
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * CreateFinalityProvider creates a new finality provider
     * </pre>
     */
    default void createFinalityProvider(com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProvider request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProviderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateFinalityProviderMethod(), responseObserver);
    }

    /**
     * <pre>
     * EditFinalityProvider edits an existing finality provider
     * </pre>
     */
    default void editFinalityProvider(com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProvider request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProviderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEditFinalityProviderMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateBTCDelegation creates a new BTC delegation
     * </pre>
     */
    default void createBTCDelegation(com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegation request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateBTCDelegationMethod(), responseObserver);
    }

    /**
     * <pre>
     * AddBTCDelegationInclusionProof adds inclusion proof of a given delegation on BTC chain
     * </pre>
     */
    default void addBTCDelegationInclusionProof(com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProof request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProofResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddBTCDelegationInclusionProofMethod(), responseObserver);
    }

    /**
     * <pre>
     * AddCovenantSigs handles signatures from a covenant member
     * </pre>
     */
    default void addCovenantSigs(com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigs request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddCovenantSigsMethod(), responseObserver);
    }

    /**
     * <pre>
     * BTCUndelegate handles a signature on unbonding tx from its delegator
     * </pre>
     */
    default void bTCUndelegate(com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegate request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBTCUndelegateMethod(), responseObserver);
    }

    /**
     * <pre>
     * SelectiveSlashingEvidence handles the evidence of selective slashing launched
     * by a finality provider
     * </pre>
     */
    default void selectiveSlashingEvidence(com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidence request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidenceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSelectiveSlashingEvidenceMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateParams updates the btcstaking module parameters.
     * </pre>
     */
    default void updateParams(com.babylon.btcstaking.v1.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateParamsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the Msg service.
   * TODO: handle unbonding tx with full witness
   * </pre>
   */
  public static abstract class MsgImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MsgGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the Msg service.
   * TODO: handle unbonding tx with full witness
   * </pre>
   */
  public static final class MsgStub
      extends io.grpc.stub.AbstractAsyncStub<MsgStub> {
    private MsgStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgStub(channel, callOptions);
    }

    /**
     * <pre>
     * CreateFinalityProvider creates a new finality provider
     * </pre>
     */
    public void createFinalityProvider(com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProvider request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProviderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateFinalityProviderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EditFinalityProvider edits an existing finality provider
     * </pre>
     */
    public void editFinalityProvider(com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProvider request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProviderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEditFinalityProviderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateBTCDelegation creates a new BTC delegation
     * </pre>
     */
    public void createBTCDelegation(com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegation request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateBTCDelegationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AddBTCDelegationInclusionProof adds inclusion proof of a given delegation on BTC chain
     * </pre>
     */
    public void addBTCDelegationInclusionProof(com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProof request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProofResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddBTCDelegationInclusionProofMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AddCovenantSigs handles signatures from a covenant member
     * </pre>
     */
    public void addCovenantSigs(com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigs request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddCovenantSigsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BTCUndelegate handles a signature on unbonding tx from its delegator
     * </pre>
     */
    public void bTCUndelegate(com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegate request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBTCUndelegateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SelectiveSlashingEvidence handles the evidence of selective slashing launched
     * by a finality provider
     * </pre>
     */
    public void selectiveSlashingEvidence(com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidence request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidenceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSelectiveSlashingEvidenceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateParams updates the btcstaking module parameters.
     * </pre>
     */
    public void updateParams(com.babylon.btcstaking.v1.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the Msg service.
   * TODO: handle unbonding tx with full witness
   * </pre>
   */
  public static final class MsgBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<MsgBlockingStub> {
    private MsgBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * CreateFinalityProvider creates a new finality provider
     * </pre>
     */
    public com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProviderResponse createFinalityProvider(com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProvider request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateFinalityProviderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EditFinalityProvider edits an existing finality provider
     * </pre>
     */
    public com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProviderResponse editFinalityProvider(com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProvider request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEditFinalityProviderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateBTCDelegation creates a new BTC delegation
     * </pre>
     */
    public com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegationResponse createBTCDelegation(com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegation request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateBTCDelegationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AddBTCDelegationInclusionProof adds inclusion proof of a given delegation on BTC chain
     * </pre>
     */
    public com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProofResponse addBTCDelegationInclusionProof(com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProof request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddBTCDelegationInclusionProofMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AddCovenantSigs handles signatures from a covenant member
     * </pre>
     */
    public com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigsResponse addCovenantSigs(com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddCovenantSigsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BTCUndelegate handles a signature on unbonding tx from its delegator
     * </pre>
     */
    public com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegateResponse bTCUndelegate(com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegate request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBTCUndelegateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SelectiveSlashingEvidence handles the evidence of selective slashing launched
     * by a finality provider
     * </pre>
     */
    public com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidenceResponse selectiveSlashingEvidence(com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidence request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSelectiveSlashingEvidenceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateParams updates the btcstaking module parameters.
     * </pre>
     */
    public com.babylon.btcstaking.v1.TxProto.MsgUpdateParamsResponse updateParams(com.babylon.btcstaking.v1.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the Msg service.
   * TODO: handle unbonding tx with full witness
   * </pre>
   */
  public static final class MsgFutureStub
      extends io.grpc.stub.AbstractFutureStub<MsgFutureStub> {
    private MsgFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * CreateFinalityProvider creates a new finality provider
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProviderResponse> createFinalityProvider(
        com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProvider request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateFinalityProviderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EditFinalityProvider edits an existing finality provider
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProviderResponse> editFinalityProvider(
        com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProvider request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEditFinalityProviderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateBTCDelegation creates a new BTC delegation
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegationResponse> createBTCDelegation(
        com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegation request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateBTCDelegationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AddBTCDelegationInclusionProof adds inclusion proof of a given delegation on BTC chain
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProofResponse> addBTCDelegationInclusionProof(
        com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProof request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddBTCDelegationInclusionProofMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AddCovenantSigs handles signatures from a covenant member
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigsResponse> addCovenantSigs(
        com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddCovenantSigsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BTCUndelegate handles a signature on unbonding tx from its delegator
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegateResponse> bTCUndelegate(
        com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegate request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBTCUndelegateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SelectiveSlashingEvidence handles the evidence of selective slashing launched
     * by a finality provider
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidenceResponse> selectiveSlashingEvidence(
        com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidence request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSelectiveSlashingEvidenceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateParams updates the btcstaking module parameters.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.btcstaking.v1.TxProto.MsgUpdateParamsResponse> updateParams(
        com.babylon.btcstaking.v1.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_FINALITY_PROVIDER = 0;
  private static final int METHODID_EDIT_FINALITY_PROVIDER = 1;
  private static final int METHODID_CREATE_BTCDELEGATION = 2;
  private static final int METHODID_ADD_BTCDELEGATION_INCLUSION_PROOF = 3;
  private static final int METHODID_ADD_COVENANT_SIGS = 4;
  private static final int METHODID_BTCUNDELEGATE = 5;
  private static final int METHODID_SELECTIVE_SLASHING_EVIDENCE = 6;
  private static final int METHODID_UPDATE_PARAMS = 7;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_FINALITY_PROVIDER:
          serviceImpl.createFinalityProvider((com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProvider) request,
              (io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProviderResponse>) responseObserver);
          break;
        case METHODID_EDIT_FINALITY_PROVIDER:
          serviceImpl.editFinalityProvider((com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProvider) request,
              (io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProviderResponse>) responseObserver);
          break;
        case METHODID_CREATE_BTCDELEGATION:
          serviceImpl.createBTCDelegation((com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegation) request,
              (io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegationResponse>) responseObserver);
          break;
        case METHODID_ADD_BTCDELEGATION_INCLUSION_PROOF:
          serviceImpl.addBTCDelegationInclusionProof((com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProof) request,
              (io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProofResponse>) responseObserver);
          break;
        case METHODID_ADD_COVENANT_SIGS:
          serviceImpl.addCovenantSigs((com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigs) request,
              (io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigsResponse>) responseObserver);
          break;
        case METHODID_BTCUNDELEGATE:
          serviceImpl.bTCUndelegate((com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegate) request,
              (io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegateResponse>) responseObserver);
          break;
        case METHODID_SELECTIVE_SLASHING_EVIDENCE:
          serviceImpl.selectiveSlashingEvidence((com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidence) request,
              (io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidenceResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PARAMS:
          serviceImpl.updateParams((com.babylon.btcstaking.v1.TxProto.MsgUpdateParams) request,
              (io.grpc.stub.StreamObserver<com.babylon.btcstaking.v1.TxProto.MsgUpdateParamsResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCreateFinalityProviderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProvider,
              com.babylon.btcstaking.v1.TxProto.MsgCreateFinalityProviderResponse>(
                service, METHODID_CREATE_FINALITY_PROVIDER)))
        .addMethod(
          getEditFinalityProviderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProvider,
              com.babylon.btcstaking.v1.TxProto.MsgEditFinalityProviderResponse>(
                service, METHODID_EDIT_FINALITY_PROVIDER)))
        .addMethod(
          getCreateBTCDelegationMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegation,
              com.babylon.btcstaking.v1.TxProto.MsgCreateBTCDelegationResponse>(
                service, METHODID_CREATE_BTCDELEGATION)))
        .addMethod(
          getAddBTCDelegationInclusionProofMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProof,
              com.babylon.btcstaking.v1.TxProto.MsgAddBTCDelegationInclusionProofResponse>(
                service, METHODID_ADD_BTCDELEGATION_INCLUSION_PROOF)))
        .addMethod(
          getAddCovenantSigsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigs,
              com.babylon.btcstaking.v1.TxProto.MsgAddCovenantSigsResponse>(
                service, METHODID_ADD_COVENANT_SIGS)))
        .addMethod(
          getBTCUndelegateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegate,
              com.babylon.btcstaking.v1.TxProto.MsgBTCUndelegateResponse>(
                service, METHODID_BTCUNDELEGATE)))
        .addMethod(
          getSelectiveSlashingEvidenceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidence,
              com.babylon.btcstaking.v1.TxProto.MsgSelectiveSlashingEvidenceResponse>(
                service, METHODID_SELECTIVE_SLASHING_EVIDENCE)))
        .addMethod(
          getUpdateParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.btcstaking.v1.TxProto.MsgUpdateParams,
              com.babylon.btcstaking.v1.TxProto.MsgUpdateParamsResponse>(
                service, METHODID_UPDATE_PARAMS)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.babylon.btcstaking.v1.TxProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Msg");
    }
  }

  private static final class MsgFileDescriptorSupplier
      extends MsgBaseDescriptorSupplier {
    MsgFileDescriptorSupplier() {}
  }

  private static final class MsgMethodDescriptorSupplier
      extends MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MsgMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MsgGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MsgFileDescriptorSupplier())
              .addMethod(getCreateFinalityProviderMethod())
              .addMethod(getEditFinalityProviderMethod())
              .addMethod(getCreateBTCDelegationMethod())
              .addMethod(getAddBTCDelegationInclusionProofMethod())
              .addMethod(getAddCovenantSigsMethod())
              .addMethod(getBTCUndelegateMethod())
              .addMethod(getSelectiveSlashingEvidenceMethod())
              .addMethod(getUpdateParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
