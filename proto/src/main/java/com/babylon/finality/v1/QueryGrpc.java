package com.babylon.finality.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: babylon/finality/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "babylon.finality.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryParamsRequest,
      com.babylon.finality.v1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.babylon.finality.v1.QueryProto.QueryParamsRequest.class,
      responseType = com.babylon.finality.v1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryParamsRequest,
      com.babylon.finality.v1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryParamsRequest, com.babylon.finality.v1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.babylon.finality.v1.QueryProto.QueryParamsRequest, com.babylon.finality.v1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightRequest,
      com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightResponse> getActiveFinalityProvidersAtHeightMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ActiveFinalityProvidersAtHeight",
      requestType = com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightRequest.class,
      responseType = com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightRequest,
      com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightResponse> getActiveFinalityProvidersAtHeightMethod() {
    io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightRequest, com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightResponse> getActiveFinalityProvidersAtHeightMethod;
    if ((getActiveFinalityProvidersAtHeightMethod = QueryGrpc.getActiveFinalityProvidersAtHeightMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getActiveFinalityProvidersAtHeightMethod = QueryGrpc.getActiveFinalityProvidersAtHeightMethod) == null) {
          QueryGrpc.getActiveFinalityProvidersAtHeightMethod = getActiveFinalityProvidersAtHeightMethod =
              io.grpc.MethodDescriptor.<com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightRequest, com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ActiveFinalityProvidersAtHeight"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ActiveFinalityProvidersAtHeight"))
              .build();
        }
      }
    }
    return getActiveFinalityProvidersAtHeightMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightRequest,
      com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightResponse> getFinalityProviderPowerAtHeightMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FinalityProviderPowerAtHeight",
      requestType = com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightRequest.class,
      responseType = com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightRequest,
      com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightResponse> getFinalityProviderPowerAtHeightMethod() {
    io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightRequest, com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightResponse> getFinalityProviderPowerAtHeightMethod;
    if ((getFinalityProviderPowerAtHeightMethod = QueryGrpc.getFinalityProviderPowerAtHeightMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFinalityProviderPowerAtHeightMethod = QueryGrpc.getFinalityProviderPowerAtHeightMethod) == null) {
          QueryGrpc.getFinalityProviderPowerAtHeightMethod = getFinalityProviderPowerAtHeightMethod =
              io.grpc.MethodDescriptor.<com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightRequest, com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FinalityProviderPowerAtHeight"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("FinalityProviderPowerAtHeight"))
              .build();
        }
      }
    }
    return getFinalityProviderPowerAtHeightMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerRequest,
      com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerResponse> getFinalityProviderCurrentPowerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FinalityProviderCurrentPower",
      requestType = com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerRequest.class,
      responseType = com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerRequest,
      com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerResponse> getFinalityProviderCurrentPowerMethod() {
    io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerRequest, com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerResponse> getFinalityProviderCurrentPowerMethod;
    if ((getFinalityProviderCurrentPowerMethod = QueryGrpc.getFinalityProviderCurrentPowerMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFinalityProviderCurrentPowerMethod = QueryGrpc.getFinalityProviderCurrentPowerMethod) == null) {
          QueryGrpc.getFinalityProviderCurrentPowerMethod = getFinalityProviderCurrentPowerMethod =
              io.grpc.MethodDescriptor.<com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerRequest, com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FinalityProviderCurrentPower"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("FinalityProviderCurrentPower"))
              .build();
        }
      }
    }
    return getFinalityProviderCurrentPowerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryActivatedHeightRequest,
      com.babylon.finality.v1.QueryProto.QueryActivatedHeightResponse> getActivatedHeightMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ActivatedHeight",
      requestType = com.babylon.finality.v1.QueryProto.QueryActivatedHeightRequest.class,
      responseType = com.babylon.finality.v1.QueryProto.QueryActivatedHeightResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryActivatedHeightRequest,
      com.babylon.finality.v1.QueryProto.QueryActivatedHeightResponse> getActivatedHeightMethod() {
    io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryActivatedHeightRequest, com.babylon.finality.v1.QueryProto.QueryActivatedHeightResponse> getActivatedHeightMethod;
    if ((getActivatedHeightMethod = QueryGrpc.getActivatedHeightMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getActivatedHeightMethod = QueryGrpc.getActivatedHeightMethod) == null) {
          QueryGrpc.getActivatedHeightMethod = getActivatedHeightMethod =
              io.grpc.MethodDescriptor.<com.babylon.finality.v1.QueryProto.QueryActivatedHeightRequest, com.babylon.finality.v1.QueryProto.QueryActivatedHeightResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ActivatedHeight"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryActivatedHeightRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryActivatedHeightResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ActivatedHeight"))
              .build();
        }
      }
    }
    return getActivatedHeightMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessRequest,
      com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessResponse> getListPublicRandomnessMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListPublicRandomness",
      requestType = com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessRequest.class,
      responseType = com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessRequest,
      com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessResponse> getListPublicRandomnessMethod() {
    io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessRequest, com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessResponse> getListPublicRandomnessMethod;
    if ((getListPublicRandomnessMethod = QueryGrpc.getListPublicRandomnessMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getListPublicRandomnessMethod = QueryGrpc.getListPublicRandomnessMethod) == null) {
          QueryGrpc.getListPublicRandomnessMethod = getListPublicRandomnessMethod =
              io.grpc.MethodDescriptor.<com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessRequest, com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListPublicRandomness"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ListPublicRandomness"))
              .build();
        }
      }
    }
    return getListPublicRandomnessMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryListPubRandCommitRequest,
      com.babylon.finality.v1.QueryProto.QueryListPubRandCommitResponse> getListPubRandCommitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListPubRandCommit",
      requestType = com.babylon.finality.v1.QueryProto.QueryListPubRandCommitRequest.class,
      responseType = com.babylon.finality.v1.QueryProto.QueryListPubRandCommitResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryListPubRandCommitRequest,
      com.babylon.finality.v1.QueryProto.QueryListPubRandCommitResponse> getListPubRandCommitMethod() {
    io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryListPubRandCommitRequest, com.babylon.finality.v1.QueryProto.QueryListPubRandCommitResponse> getListPubRandCommitMethod;
    if ((getListPubRandCommitMethod = QueryGrpc.getListPubRandCommitMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getListPubRandCommitMethod = QueryGrpc.getListPubRandCommitMethod) == null) {
          QueryGrpc.getListPubRandCommitMethod = getListPubRandCommitMethod =
              io.grpc.MethodDescriptor.<com.babylon.finality.v1.QueryProto.QueryListPubRandCommitRequest, com.babylon.finality.v1.QueryProto.QueryListPubRandCommitResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListPubRandCommit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryListPubRandCommitRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryListPubRandCommitResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ListPubRandCommit"))
              .build();
        }
      }
    }
    return getListPubRandCommitMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryBlockRequest,
      com.babylon.finality.v1.QueryProto.QueryBlockResponse> getBlockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Block",
      requestType = com.babylon.finality.v1.QueryProto.QueryBlockRequest.class,
      responseType = com.babylon.finality.v1.QueryProto.QueryBlockResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryBlockRequest,
      com.babylon.finality.v1.QueryProto.QueryBlockResponse> getBlockMethod() {
    io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryBlockRequest, com.babylon.finality.v1.QueryProto.QueryBlockResponse> getBlockMethod;
    if ((getBlockMethod = QueryGrpc.getBlockMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBlockMethod = QueryGrpc.getBlockMethod) == null) {
          QueryGrpc.getBlockMethod = getBlockMethod =
              io.grpc.MethodDescriptor.<com.babylon.finality.v1.QueryProto.QueryBlockRequest, com.babylon.finality.v1.QueryProto.QueryBlockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Block"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryBlockRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryBlockResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Block"))
              .build();
        }
      }
    }
    return getBlockMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryListBlocksRequest,
      com.babylon.finality.v1.QueryProto.QueryListBlocksResponse> getListBlocksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListBlocks",
      requestType = com.babylon.finality.v1.QueryProto.QueryListBlocksRequest.class,
      responseType = com.babylon.finality.v1.QueryProto.QueryListBlocksResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryListBlocksRequest,
      com.babylon.finality.v1.QueryProto.QueryListBlocksResponse> getListBlocksMethod() {
    io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryListBlocksRequest, com.babylon.finality.v1.QueryProto.QueryListBlocksResponse> getListBlocksMethod;
    if ((getListBlocksMethod = QueryGrpc.getListBlocksMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getListBlocksMethod = QueryGrpc.getListBlocksMethod) == null) {
          QueryGrpc.getListBlocksMethod = getListBlocksMethod =
              io.grpc.MethodDescriptor.<com.babylon.finality.v1.QueryProto.QueryListBlocksRequest, com.babylon.finality.v1.QueryProto.QueryListBlocksResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListBlocks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryListBlocksRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryListBlocksResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ListBlocks"))
              .build();
        }
      }
    }
    return getListBlocksMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryVotesAtHeightRequest,
      com.babylon.finality.v1.QueryProto.QueryVotesAtHeightResponse> getVotesAtHeightMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "VotesAtHeight",
      requestType = com.babylon.finality.v1.QueryProto.QueryVotesAtHeightRequest.class,
      responseType = com.babylon.finality.v1.QueryProto.QueryVotesAtHeightResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryVotesAtHeightRequest,
      com.babylon.finality.v1.QueryProto.QueryVotesAtHeightResponse> getVotesAtHeightMethod() {
    io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryVotesAtHeightRequest, com.babylon.finality.v1.QueryProto.QueryVotesAtHeightResponse> getVotesAtHeightMethod;
    if ((getVotesAtHeightMethod = QueryGrpc.getVotesAtHeightMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getVotesAtHeightMethod = QueryGrpc.getVotesAtHeightMethod) == null) {
          QueryGrpc.getVotesAtHeightMethod = getVotesAtHeightMethod =
              io.grpc.MethodDescriptor.<com.babylon.finality.v1.QueryProto.QueryVotesAtHeightRequest, com.babylon.finality.v1.QueryProto.QueryVotesAtHeightResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "VotesAtHeight"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryVotesAtHeightRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryVotesAtHeightResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("VotesAtHeight"))
              .build();
        }
      }
    }
    return getVotesAtHeightMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryEvidenceRequest,
      com.babylon.finality.v1.QueryProto.QueryEvidenceResponse> getEvidenceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Evidence",
      requestType = com.babylon.finality.v1.QueryProto.QueryEvidenceRequest.class,
      responseType = com.babylon.finality.v1.QueryProto.QueryEvidenceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryEvidenceRequest,
      com.babylon.finality.v1.QueryProto.QueryEvidenceResponse> getEvidenceMethod() {
    io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryEvidenceRequest, com.babylon.finality.v1.QueryProto.QueryEvidenceResponse> getEvidenceMethod;
    if ((getEvidenceMethod = QueryGrpc.getEvidenceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEvidenceMethod = QueryGrpc.getEvidenceMethod) == null) {
          QueryGrpc.getEvidenceMethod = getEvidenceMethod =
              io.grpc.MethodDescriptor.<com.babylon.finality.v1.QueryProto.QueryEvidenceRequest, com.babylon.finality.v1.QueryProto.QueryEvidenceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Evidence"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryEvidenceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryEvidenceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Evidence"))
              .build();
        }
      }
    }
    return getEvidenceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryListEvidencesRequest,
      com.babylon.finality.v1.QueryProto.QueryListEvidencesResponse> getListEvidencesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListEvidences",
      requestType = com.babylon.finality.v1.QueryProto.QueryListEvidencesRequest.class,
      responseType = com.babylon.finality.v1.QueryProto.QueryListEvidencesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryListEvidencesRequest,
      com.babylon.finality.v1.QueryProto.QueryListEvidencesResponse> getListEvidencesMethod() {
    io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QueryListEvidencesRequest, com.babylon.finality.v1.QueryProto.QueryListEvidencesResponse> getListEvidencesMethod;
    if ((getListEvidencesMethod = QueryGrpc.getListEvidencesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getListEvidencesMethod = QueryGrpc.getListEvidencesMethod) == null) {
          QueryGrpc.getListEvidencesMethod = getListEvidencesMethod =
              io.grpc.MethodDescriptor.<com.babylon.finality.v1.QueryProto.QueryListEvidencesRequest, com.babylon.finality.v1.QueryProto.QueryListEvidencesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListEvidences"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryListEvidencesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QueryListEvidencesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ListEvidences"))
              .build();
        }
      }
    }
    return getListEvidencesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QuerySigningInfoRequest,
      com.babylon.finality.v1.QueryProto.QuerySigningInfoResponse> getSigningInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SigningInfo",
      requestType = com.babylon.finality.v1.QueryProto.QuerySigningInfoRequest.class,
      responseType = com.babylon.finality.v1.QueryProto.QuerySigningInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QuerySigningInfoRequest,
      com.babylon.finality.v1.QueryProto.QuerySigningInfoResponse> getSigningInfoMethod() {
    io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QuerySigningInfoRequest, com.babylon.finality.v1.QueryProto.QuerySigningInfoResponse> getSigningInfoMethod;
    if ((getSigningInfoMethod = QueryGrpc.getSigningInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSigningInfoMethod = QueryGrpc.getSigningInfoMethod) == null) {
          QueryGrpc.getSigningInfoMethod = getSigningInfoMethod =
              io.grpc.MethodDescriptor.<com.babylon.finality.v1.QueryProto.QuerySigningInfoRequest, com.babylon.finality.v1.QueryProto.QuerySigningInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SigningInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QuerySigningInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QuerySigningInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SigningInfo"))
              .build();
        }
      }
    }
    return getSigningInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QuerySigningInfosRequest,
      com.babylon.finality.v1.QueryProto.QuerySigningInfosResponse> getSigningInfosMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SigningInfos",
      requestType = com.babylon.finality.v1.QueryProto.QuerySigningInfosRequest.class,
      responseType = com.babylon.finality.v1.QueryProto.QuerySigningInfosResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QuerySigningInfosRequest,
      com.babylon.finality.v1.QueryProto.QuerySigningInfosResponse> getSigningInfosMethod() {
    io.grpc.MethodDescriptor<com.babylon.finality.v1.QueryProto.QuerySigningInfosRequest, com.babylon.finality.v1.QueryProto.QuerySigningInfosResponse> getSigningInfosMethod;
    if ((getSigningInfosMethod = QueryGrpc.getSigningInfosMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSigningInfosMethod = QueryGrpc.getSigningInfosMethod) == null) {
          QueryGrpc.getSigningInfosMethod = getSigningInfosMethod =
              io.grpc.MethodDescriptor.<com.babylon.finality.v1.QueryProto.QuerySigningInfosRequest, com.babylon.finality.v1.QueryProto.QuerySigningInfosResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SigningInfos"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QuerySigningInfosRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.babylon.finality.v1.QueryProto.QuerySigningInfosResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SigningInfos"))
              .build();
        }
      }
    }
    return getSigningInfosMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static QueryStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryStub>() {
        @java.lang.Override
        public QueryStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryStub(channel, callOptions);
        }
      };
    return QueryStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static QueryBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryBlockingStub>() {
        @java.lang.Override
        public QueryBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryBlockingStub(channel, callOptions);
        }
      };
    return QueryBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static QueryFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryFutureStub>() {
        @java.lang.Override
        public QueryFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryFutureStub(channel, callOptions);
        }
      };
    return QueryFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Parameters queries the parameters of the module.
     * </pre>
     */
    default void params(com.babylon.finality.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * ActiveFinalityProvidersAtHeight queries finality providers with non zero voting power at given height.
     * </pre>
     */
    default void activeFinalityProvidersAtHeight(com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getActiveFinalityProvidersAtHeightMethod(), responseObserver);
    }

    /**
     * <pre>
     * FinalityProviderPowerAtHeight queries the voting power of a finality provider at a given height
     * </pre>
     */
    default void finalityProviderPowerAtHeight(com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFinalityProviderPowerAtHeightMethod(), responseObserver);
    }

    /**
     * <pre>
     * FinalityProviderCurrentPower queries the voting power of a finality provider at the current height
     * </pre>
     */
    default void finalityProviderCurrentPower(com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFinalityProviderCurrentPowerMethod(), responseObserver);
    }

    /**
     * <pre>
     * ActivatedHeight queries the height when BTC staking protocol is activated, i.e., the first height when
     * there exists 1 finality provider with voting power
     * </pre>
     */
    default void activatedHeight(com.babylon.finality.v1.QueryProto.QueryActivatedHeightRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryActivatedHeightResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getActivatedHeightMethod(), responseObserver);
    }

    /**
     * <pre>
     * ListPublicRandomness is a range query for public randomness of a given finality provider
     * NOTE: Babylon only has the knowledge of public randomness that is already revealed by
     * finality providers, i.e., the finality provider already provides a finality signature
     * at the corresponding height
     * </pre>
     */
    default void listPublicRandomness(com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListPublicRandomnessMethod(), responseObserver);
    }

    /**
     * <pre>
     * ListPubRandCommit is a range query for public randomness commitments of a given finality provider
     * </pre>
     */
    default void listPubRandCommit(com.babylon.finality.v1.QueryProto.QueryListPubRandCommitRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryListPubRandCommitResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListPubRandCommitMethod(), responseObserver);
    }

    /**
     * <pre>
     * Block queries a block at a given height
     * </pre>
     */
    default void block(com.babylon.finality.v1.QueryProto.QueryBlockRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryBlockResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBlockMethod(), responseObserver);
    }

    /**
     * <pre>
     * ListBlocks is a range query for blocks at a given status
     * </pre>
     */
    default void listBlocks(com.babylon.finality.v1.QueryProto.QueryListBlocksRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryListBlocksResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListBlocksMethod(), responseObserver);
    }

    /**
     * <pre>
     * VotesAtHeight queries finality providers who have signed the block at given height.
     * </pre>
     */
    default void votesAtHeight(com.babylon.finality.v1.QueryProto.QueryVotesAtHeightRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryVotesAtHeightResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getVotesAtHeightMethod(), responseObserver);
    }

    /**
     * <pre>
     * Evidence queries the first evidence which can be used for extracting the BTC SK
     * </pre>
     */
    default void evidence(com.babylon.finality.v1.QueryProto.QueryEvidenceRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryEvidenceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEvidenceMethod(), responseObserver);
    }

    /**
     * <pre>
     * ListEvidences queries is a range query for evidences
     * </pre>
     */
    default void listEvidences(com.babylon.finality.v1.QueryProto.QueryListEvidencesRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryListEvidencesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListEvidencesMethod(), responseObserver);
    }

    /**
     * <pre>
     * SigningInfo queries the signing info of given finality provider BTC public key
     * </pre>
     */
    default void signingInfo(com.babylon.finality.v1.QueryProto.QuerySigningInfoRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QuerySigningInfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSigningInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * SigningInfos queries the signing info of all the active finality providers
     * </pre>
     */
    default void signingInfos(com.babylon.finality.v1.QueryProto.QuerySigningInfosRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QuerySigningInfosResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSigningInfosMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query defines the gRPC querier service.
   * </pre>
   */
  public static abstract class QueryImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return QueryGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service.
   * </pre>
   */
  public static final class QueryStub
      extends io.grpc.stub.AbstractAsyncStub<QueryStub> {
    private QueryStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryStub(channel, callOptions);
    }

    /**
     * <pre>
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public void params(com.babylon.finality.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ActiveFinalityProvidersAtHeight queries finality providers with non zero voting power at given height.
     * </pre>
     */
    public void activeFinalityProvidersAtHeight(com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getActiveFinalityProvidersAtHeightMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * FinalityProviderPowerAtHeight queries the voting power of a finality provider at a given height
     * </pre>
     */
    public void finalityProviderPowerAtHeight(com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFinalityProviderPowerAtHeightMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * FinalityProviderCurrentPower queries the voting power of a finality provider at the current height
     * </pre>
     */
    public void finalityProviderCurrentPower(com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFinalityProviderCurrentPowerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ActivatedHeight queries the height when BTC staking protocol is activated, i.e., the first height when
     * there exists 1 finality provider with voting power
     * </pre>
     */
    public void activatedHeight(com.babylon.finality.v1.QueryProto.QueryActivatedHeightRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryActivatedHeightResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getActivatedHeightMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ListPublicRandomness is a range query for public randomness of a given finality provider
     * NOTE: Babylon only has the knowledge of public randomness that is already revealed by
     * finality providers, i.e., the finality provider already provides a finality signature
     * at the corresponding height
     * </pre>
     */
    public void listPublicRandomness(com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListPublicRandomnessMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ListPubRandCommit is a range query for public randomness commitments of a given finality provider
     * </pre>
     */
    public void listPubRandCommit(com.babylon.finality.v1.QueryProto.QueryListPubRandCommitRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryListPubRandCommitResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListPubRandCommitMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Block queries a block at a given height
     * </pre>
     */
    public void block(com.babylon.finality.v1.QueryProto.QueryBlockRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryBlockResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBlockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ListBlocks is a range query for blocks at a given status
     * </pre>
     */
    public void listBlocks(com.babylon.finality.v1.QueryProto.QueryListBlocksRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryListBlocksResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListBlocksMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * VotesAtHeight queries finality providers who have signed the block at given height.
     * </pre>
     */
    public void votesAtHeight(com.babylon.finality.v1.QueryProto.QueryVotesAtHeightRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryVotesAtHeightResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getVotesAtHeightMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Evidence queries the first evidence which can be used for extracting the BTC SK
     * </pre>
     */
    public void evidence(com.babylon.finality.v1.QueryProto.QueryEvidenceRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryEvidenceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEvidenceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ListEvidences queries is a range query for evidences
     * </pre>
     */
    public void listEvidences(com.babylon.finality.v1.QueryProto.QueryListEvidencesRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryListEvidencesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListEvidencesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SigningInfo queries the signing info of given finality provider BTC public key
     * </pre>
     */
    public void signingInfo(com.babylon.finality.v1.QueryProto.QuerySigningInfoRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QuerySigningInfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSigningInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SigningInfos queries the signing info of all the active finality providers
     * </pre>
     */
    public void signingInfos(com.babylon.finality.v1.QueryProto.QuerySigningInfosRequest request,
        io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QuerySigningInfosResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSigningInfosMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service.
   * </pre>
   */
  public static final class QueryBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<QueryBlockingStub> {
    private QueryBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public com.babylon.finality.v1.QueryProto.QueryParamsResponse params(com.babylon.finality.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ActiveFinalityProvidersAtHeight queries finality providers with non zero voting power at given height.
     * </pre>
     */
    public com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightResponse activeFinalityProvidersAtHeight(com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getActiveFinalityProvidersAtHeightMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * FinalityProviderPowerAtHeight queries the voting power of a finality provider at a given height
     * </pre>
     */
    public com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightResponse finalityProviderPowerAtHeight(com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFinalityProviderPowerAtHeightMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * FinalityProviderCurrentPower queries the voting power of a finality provider at the current height
     * </pre>
     */
    public com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerResponse finalityProviderCurrentPower(com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFinalityProviderCurrentPowerMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ActivatedHeight queries the height when BTC staking protocol is activated, i.e., the first height when
     * there exists 1 finality provider with voting power
     * </pre>
     */
    public com.babylon.finality.v1.QueryProto.QueryActivatedHeightResponse activatedHeight(com.babylon.finality.v1.QueryProto.QueryActivatedHeightRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getActivatedHeightMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ListPublicRandomness is a range query for public randomness of a given finality provider
     * NOTE: Babylon only has the knowledge of public randomness that is already revealed by
     * finality providers, i.e., the finality provider already provides a finality signature
     * at the corresponding height
     * </pre>
     */
    public com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessResponse listPublicRandomness(com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListPublicRandomnessMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ListPubRandCommit is a range query for public randomness commitments of a given finality provider
     * </pre>
     */
    public com.babylon.finality.v1.QueryProto.QueryListPubRandCommitResponse listPubRandCommit(com.babylon.finality.v1.QueryProto.QueryListPubRandCommitRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListPubRandCommitMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Block queries a block at a given height
     * </pre>
     */
    public com.babylon.finality.v1.QueryProto.QueryBlockResponse block(com.babylon.finality.v1.QueryProto.QueryBlockRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBlockMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ListBlocks is a range query for blocks at a given status
     * </pre>
     */
    public com.babylon.finality.v1.QueryProto.QueryListBlocksResponse listBlocks(com.babylon.finality.v1.QueryProto.QueryListBlocksRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListBlocksMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * VotesAtHeight queries finality providers who have signed the block at given height.
     * </pre>
     */
    public com.babylon.finality.v1.QueryProto.QueryVotesAtHeightResponse votesAtHeight(com.babylon.finality.v1.QueryProto.QueryVotesAtHeightRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getVotesAtHeightMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Evidence queries the first evidence which can be used for extracting the BTC SK
     * </pre>
     */
    public com.babylon.finality.v1.QueryProto.QueryEvidenceResponse evidence(com.babylon.finality.v1.QueryProto.QueryEvidenceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEvidenceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ListEvidences queries is a range query for evidences
     * </pre>
     */
    public com.babylon.finality.v1.QueryProto.QueryListEvidencesResponse listEvidences(com.babylon.finality.v1.QueryProto.QueryListEvidencesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListEvidencesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SigningInfo queries the signing info of given finality provider BTC public key
     * </pre>
     */
    public com.babylon.finality.v1.QueryProto.QuerySigningInfoResponse signingInfo(com.babylon.finality.v1.QueryProto.QuerySigningInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSigningInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SigningInfos queries the signing info of all the active finality providers
     * </pre>
     */
    public com.babylon.finality.v1.QueryProto.QuerySigningInfosResponse signingInfos(com.babylon.finality.v1.QueryProto.QuerySigningInfosRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSigningInfosMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service.
   * </pre>
   */
  public static final class QueryFutureStub
      extends io.grpc.stub.AbstractFutureStub<QueryFutureStub> {
    private QueryFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.finality.v1.QueryProto.QueryParamsResponse> params(
        com.babylon.finality.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ActiveFinalityProvidersAtHeight queries finality providers with non zero voting power at given height.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightResponse> activeFinalityProvidersAtHeight(
        com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getActiveFinalityProvidersAtHeightMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * FinalityProviderPowerAtHeight queries the voting power of a finality provider at a given height
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightResponse> finalityProviderPowerAtHeight(
        com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFinalityProviderPowerAtHeightMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * FinalityProviderCurrentPower queries the voting power of a finality provider at the current height
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerResponse> finalityProviderCurrentPower(
        com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFinalityProviderCurrentPowerMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ActivatedHeight queries the height when BTC staking protocol is activated, i.e., the first height when
     * there exists 1 finality provider with voting power
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.finality.v1.QueryProto.QueryActivatedHeightResponse> activatedHeight(
        com.babylon.finality.v1.QueryProto.QueryActivatedHeightRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getActivatedHeightMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ListPublicRandomness is a range query for public randomness of a given finality provider
     * NOTE: Babylon only has the knowledge of public randomness that is already revealed by
     * finality providers, i.e., the finality provider already provides a finality signature
     * at the corresponding height
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessResponse> listPublicRandomness(
        com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListPublicRandomnessMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ListPubRandCommit is a range query for public randomness commitments of a given finality provider
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.finality.v1.QueryProto.QueryListPubRandCommitResponse> listPubRandCommit(
        com.babylon.finality.v1.QueryProto.QueryListPubRandCommitRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListPubRandCommitMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Block queries a block at a given height
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.finality.v1.QueryProto.QueryBlockResponse> block(
        com.babylon.finality.v1.QueryProto.QueryBlockRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBlockMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ListBlocks is a range query for blocks at a given status
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.finality.v1.QueryProto.QueryListBlocksResponse> listBlocks(
        com.babylon.finality.v1.QueryProto.QueryListBlocksRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListBlocksMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * VotesAtHeight queries finality providers who have signed the block at given height.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.finality.v1.QueryProto.QueryVotesAtHeightResponse> votesAtHeight(
        com.babylon.finality.v1.QueryProto.QueryVotesAtHeightRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getVotesAtHeightMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Evidence queries the first evidence which can be used for extracting the BTC SK
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.finality.v1.QueryProto.QueryEvidenceResponse> evidence(
        com.babylon.finality.v1.QueryProto.QueryEvidenceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEvidenceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ListEvidences queries is a range query for evidences
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.finality.v1.QueryProto.QueryListEvidencesResponse> listEvidences(
        com.babylon.finality.v1.QueryProto.QueryListEvidencesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListEvidencesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SigningInfo queries the signing info of given finality provider BTC public key
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.finality.v1.QueryProto.QuerySigningInfoResponse> signingInfo(
        com.babylon.finality.v1.QueryProto.QuerySigningInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSigningInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SigningInfos queries the signing info of all the active finality providers
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.babylon.finality.v1.QueryProto.QuerySigningInfosResponse> signingInfos(
        com.babylon.finality.v1.QueryProto.QuerySigningInfosRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSigningInfosMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_ACTIVE_FINALITY_PROVIDERS_AT_HEIGHT = 1;
  private static final int METHODID_FINALITY_PROVIDER_POWER_AT_HEIGHT = 2;
  private static final int METHODID_FINALITY_PROVIDER_CURRENT_POWER = 3;
  private static final int METHODID_ACTIVATED_HEIGHT = 4;
  private static final int METHODID_LIST_PUBLIC_RANDOMNESS = 5;
  private static final int METHODID_LIST_PUB_RAND_COMMIT = 6;
  private static final int METHODID_BLOCK = 7;
  private static final int METHODID_LIST_BLOCKS = 8;
  private static final int METHODID_VOTES_AT_HEIGHT = 9;
  private static final int METHODID_EVIDENCE = 10;
  private static final int METHODID_LIST_EVIDENCES = 11;
  private static final int METHODID_SIGNING_INFO = 12;
  private static final int METHODID_SIGNING_INFOS = 13;

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
        case METHODID_PARAMS:
          serviceImpl.params((com.babylon.finality.v1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_ACTIVE_FINALITY_PROVIDERS_AT_HEIGHT:
          serviceImpl.activeFinalityProvidersAtHeight((com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightResponse>) responseObserver);
          break;
        case METHODID_FINALITY_PROVIDER_POWER_AT_HEIGHT:
          serviceImpl.finalityProviderPowerAtHeight((com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightResponse>) responseObserver);
          break;
        case METHODID_FINALITY_PROVIDER_CURRENT_POWER:
          serviceImpl.finalityProviderCurrentPower((com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerResponse>) responseObserver);
          break;
        case METHODID_ACTIVATED_HEIGHT:
          serviceImpl.activatedHeight((com.babylon.finality.v1.QueryProto.QueryActivatedHeightRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryActivatedHeightResponse>) responseObserver);
          break;
        case METHODID_LIST_PUBLIC_RANDOMNESS:
          serviceImpl.listPublicRandomness((com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessResponse>) responseObserver);
          break;
        case METHODID_LIST_PUB_RAND_COMMIT:
          serviceImpl.listPubRandCommit((com.babylon.finality.v1.QueryProto.QueryListPubRandCommitRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryListPubRandCommitResponse>) responseObserver);
          break;
        case METHODID_BLOCK:
          serviceImpl.block((com.babylon.finality.v1.QueryProto.QueryBlockRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryBlockResponse>) responseObserver);
          break;
        case METHODID_LIST_BLOCKS:
          serviceImpl.listBlocks((com.babylon.finality.v1.QueryProto.QueryListBlocksRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryListBlocksResponse>) responseObserver);
          break;
        case METHODID_VOTES_AT_HEIGHT:
          serviceImpl.votesAtHeight((com.babylon.finality.v1.QueryProto.QueryVotesAtHeightRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryVotesAtHeightResponse>) responseObserver);
          break;
        case METHODID_EVIDENCE:
          serviceImpl.evidence((com.babylon.finality.v1.QueryProto.QueryEvidenceRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryEvidenceResponse>) responseObserver);
          break;
        case METHODID_LIST_EVIDENCES:
          serviceImpl.listEvidences((com.babylon.finality.v1.QueryProto.QueryListEvidencesRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QueryListEvidencesResponse>) responseObserver);
          break;
        case METHODID_SIGNING_INFO:
          serviceImpl.signingInfo((com.babylon.finality.v1.QueryProto.QuerySigningInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QuerySigningInfoResponse>) responseObserver);
          break;
        case METHODID_SIGNING_INFOS:
          serviceImpl.signingInfos((com.babylon.finality.v1.QueryProto.QuerySigningInfosRequest) request,
              (io.grpc.stub.StreamObserver<com.babylon.finality.v1.QueryProto.QuerySigningInfosResponse>) responseObserver);
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
          getParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.finality.v1.QueryProto.QueryParamsRequest,
              com.babylon.finality.v1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getActiveFinalityProvidersAtHeightMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightRequest,
              com.babylon.finality.v1.QueryProto.QueryActiveFinalityProvidersAtHeightResponse>(
                service, METHODID_ACTIVE_FINALITY_PROVIDERS_AT_HEIGHT)))
        .addMethod(
          getFinalityProviderPowerAtHeightMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightRequest,
              com.babylon.finality.v1.QueryProto.QueryFinalityProviderPowerAtHeightResponse>(
                service, METHODID_FINALITY_PROVIDER_POWER_AT_HEIGHT)))
        .addMethod(
          getFinalityProviderCurrentPowerMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerRequest,
              com.babylon.finality.v1.QueryProto.QueryFinalityProviderCurrentPowerResponse>(
                service, METHODID_FINALITY_PROVIDER_CURRENT_POWER)))
        .addMethod(
          getActivatedHeightMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.finality.v1.QueryProto.QueryActivatedHeightRequest,
              com.babylon.finality.v1.QueryProto.QueryActivatedHeightResponse>(
                service, METHODID_ACTIVATED_HEIGHT)))
        .addMethod(
          getListPublicRandomnessMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessRequest,
              com.babylon.finality.v1.QueryProto.QueryListPublicRandomnessResponse>(
                service, METHODID_LIST_PUBLIC_RANDOMNESS)))
        .addMethod(
          getListPubRandCommitMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.finality.v1.QueryProto.QueryListPubRandCommitRequest,
              com.babylon.finality.v1.QueryProto.QueryListPubRandCommitResponse>(
                service, METHODID_LIST_PUB_RAND_COMMIT)))
        .addMethod(
          getBlockMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.finality.v1.QueryProto.QueryBlockRequest,
              com.babylon.finality.v1.QueryProto.QueryBlockResponse>(
                service, METHODID_BLOCK)))
        .addMethod(
          getListBlocksMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.finality.v1.QueryProto.QueryListBlocksRequest,
              com.babylon.finality.v1.QueryProto.QueryListBlocksResponse>(
                service, METHODID_LIST_BLOCKS)))
        .addMethod(
          getVotesAtHeightMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.finality.v1.QueryProto.QueryVotesAtHeightRequest,
              com.babylon.finality.v1.QueryProto.QueryVotesAtHeightResponse>(
                service, METHODID_VOTES_AT_HEIGHT)))
        .addMethod(
          getEvidenceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.finality.v1.QueryProto.QueryEvidenceRequest,
              com.babylon.finality.v1.QueryProto.QueryEvidenceResponse>(
                service, METHODID_EVIDENCE)))
        .addMethod(
          getListEvidencesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.finality.v1.QueryProto.QueryListEvidencesRequest,
              com.babylon.finality.v1.QueryProto.QueryListEvidencesResponse>(
                service, METHODID_LIST_EVIDENCES)))
        .addMethod(
          getSigningInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.finality.v1.QueryProto.QuerySigningInfoRequest,
              com.babylon.finality.v1.QueryProto.QuerySigningInfoResponse>(
                service, METHODID_SIGNING_INFO)))
        .addMethod(
          getSigningInfosMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.babylon.finality.v1.QueryProto.QuerySigningInfosRequest,
              com.babylon.finality.v1.QueryProto.QuerySigningInfosResponse>(
                service, METHODID_SIGNING_INFOS)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.babylon.finality.v1.QueryProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Query");
    }
  }

  private static final class QueryFileDescriptorSupplier
      extends QueryBaseDescriptorSupplier {
    QueryFileDescriptorSupplier() {}
  }

  private static final class QueryMethodDescriptorSupplier
      extends QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    QueryMethodDescriptorSupplier(String methodName) {
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
      synchronized (QueryGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new QueryFileDescriptorSupplier())
              .addMethod(getParamsMethod())
              .addMethod(getActiveFinalityProvidersAtHeightMethod())
              .addMethod(getFinalityProviderPowerAtHeightMethod())
              .addMethod(getFinalityProviderCurrentPowerMethod())
              .addMethod(getActivatedHeightMethod())
              .addMethod(getListPublicRandomnessMethod())
              .addMethod(getListPubRandCommitMethod())
              .addMethod(getBlockMethod())
              .addMethod(getListBlocksMethod())
              .addMethod(getVotesAtHeightMethod())
              .addMethod(getEvidenceMethod())
              .addMethod(getListEvidencesMethod())
              .addMethod(getSigningInfoMethod())
              .addMethod(getSigningInfosMethod())
              .build();
        }
      }
    }
    return result;
  }
}
