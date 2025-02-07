package com.zrchain.validation;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: zrchain/validation/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "zrchain.validation.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryValidatorsRequest,
      com.zrchain.validation.QueryProto.QueryValidatorsResponse> getValidatorsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Validators",
      requestType = com.zrchain.validation.QueryProto.QueryValidatorsRequest.class,
      responseType = com.zrchain.validation.QueryProto.QueryValidatorsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryValidatorsRequest,
      com.zrchain.validation.QueryProto.QueryValidatorsResponse> getValidatorsMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryValidatorsRequest, com.zrchain.validation.QueryProto.QueryValidatorsResponse> getValidatorsMethod;
    if ((getValidatorsMethod = QueryGrpc.getValidatorsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValidatorsMethod = QueryGrpc.getValidatorsMethod) == null) {
          QueryGrpc.getValidatorsMethod = getValidatorsMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.QueryProto.QueryValidatorsRequest, com.zrchain.validation.QueryProto.QueryValidatorsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Validators"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryValidatorsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryValidatorsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Validators"))
              .build();
        }
      }
    }
    return getValidatorsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryValidatorRequest,
      com.zrchain.validation.QueryProto.QueryValidatorResponse> getValidatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Validator",
      requestType = com.zrchain.validation.QueryProto.QueryValidatorRequest.class,
      responseType = com.zrchain.validation.QueryProto.QueryValidatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryValidatorRequest,
      com.zrchain.validation.QueryProto.QueryValidatorResponse> getValidatorMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryValidatorRequest, com.zrchain.validation.QueryProto.QueryValidatorResponse> getValidatorMethod;
    if ((getValidatorMethod = QueryGrpc.getValidatorMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValidatorMethod = QueryGrpc.getValidatorMethod) == null) {
          QueryGrpc.getValidatorMethod = getValidatorMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.QueryProto.QueryValidatorRequest, com.zrchain.validation.QueryProto.QueryValidatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Validator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryValidatorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryValidatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Validator"))
              .build();
        }
      }
    }
    return getValidatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryValidatorDelegationsRequest,
      com.zrchain.validation.QueryProto.QueryValidatorDelegationsResponse> getValidatorDelegationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValidatorDelegations",
      requestType = com.zrchain.validation.QueryProto.QueryValidatorDelegationsRequest.class,
      responseType = com.zrchain.validation.QueryProto.QueryValidatorDelegationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryValidatorDelegationsRequest,
      com.zrchain.validation.QueryProto.QueryValidatorDelegationsResponse> getValidatorDelegationsMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryValidatorDelegationsRequest, com.zrchain.validation.QueryProto.QueryValidatorDelegationsResponse> getValidatorDelegationsMethod;
    if ((getValidatorDelegationsMethod = QueryGrpc.getValidatorDelegationsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValidatorDelegationsMethod = QueryGrpc.getValidatorDelegationsMethod) == null) {
          QueryGrpc.getValidatorDelegationsMethod = getValidatorDelegationsMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.QueryProto.QueryValidatorDelegationsRequest, com.zrchain.validation.QueryProto.QueryValidatorDelegationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValidatorDelegations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryValidatorDelegationsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryValidatorDelegationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValidatorDelegations"))
              .build();
        }
      }
    }
    return getValidatorDelegationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsRequest,
      com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsResponse> getValidatorUnbondingDelegationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValidatorUnbondingDelegations",
      requestType = com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsRequest.class,
      responseType = com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsRequest,
      com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsResponse> getValidatorUnbondingDelegationsMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsRequest, com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsResponse> getValidatorUnbondingDelegationsMethod;
    if ((getValidatorUnbondingDelegationsMethod = QueryGrpc.getValidatorUnbondingDelegationsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValidatorUnbondingDelegationsMethod = QueryGrpc.getValidatorUnbondingDelegationsMethod) == null) {
          QueryGrpc.getValidatorUnbondingDelegationsMethod = getValidatorUnbondingDelegationsMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsRequest, com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValidatorUnbondingDelegations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValidatorUnbondingDelegations"))
              .build();
        }
      }
    }
    return getValidatorUnbondingDelegationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryDelegationRequest,
      com.zrchain.validation.QueryProto.QueryDelegationResponse> getDelegationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Delegation",
      requestType = com.zrchain.validation.QueryProto.QueryDelegationRequest.class,
      responseType = com.zrchain.validation.QueryProto.QueryDelegationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryDelegationRequest,
      com.zrchain.validation.QueryProto.QueryDelegationResponse> getDelegationMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryDelegationRequest, com.zrchain.validation.QueryProto.QueryDelegationResponse> getDelegationMethod;
    if ((getDelegationMethod = QueryGrpc.getDelegationMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDelegationMethod = QueryGrpc.getDelegationMethod) == null) {
          QueryGrpc.getDelegationMethod = getDelegationMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.QueryProto.QueryDelegationRequest, com.zrchain.validation.QueryProto.QueryDelegationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Delegation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryDelegationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryDelegationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Delegation"))
              .build();
        }
      }
    }
    return getDelegationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryUnbondingDelegationRequest,
      com.zrchain.validation.QueryProto.QueryUnbondingDelegationResponse> getUnbondingDelegationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnbondingDelegation",
      requestType = com.zrchain.validation.QueryProto.QueryUnbondingDelegationRequest.class,
      responseType = com.zrchain.validation.QueryProto.QueryUnbondingDelegationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryUnbondingDelegationRequest,
      com.zrchain.validation.QueryProto.QueryUnbondingDelegationResponse> getUnbondingDelegationMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryUnbondingDelegationRequest, com.zrchain.validation.QueryProto.QueryUnbondingDelegationResponse> getUnbondingDelegationMethod;
    if ((getUnbondingDelegationMethod = QueryGrpc.getUnbondingDelegationMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUnbondingDelegationMethod = QueryGrpc.getUnbondingDelegationMethod) == null) {
          QueryGrpc.getUnbondingDelegationMethod = getUnbondingDelegationMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.QueryProto.QueryUnbondingDelegationRequest, com.zrchain.validation.QueryProto.QueryUnbondingDelegationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnbondingDelegation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryUnbondingDelegationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryUnbondingDelegationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UnbondingDelegation"))
              .build();
        }
      }
    }
    return getUnbondingDelegationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryDelegatorDelegationsRequest,
      com.zrchain.validation.QueryProto.QueryDelegatorDelegationsResponse> getDelegatorDelegationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelegatorDelegations",
      requestType = com.zrchain.validation.QueryProto.QueryDelegatorDelegationsRequest.class,
      responseType = com.zrchain.validation.QueryProto.QueryDelegatorDelegationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryDelegatorDelegationsRequest,
      com.zrchain.validation.QueryProto.QueryDelegatorDelegationsResponse> getDelegatorDelegationsMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryDelegatorDelegationsRequest, com.zrchain.validation.QueryProto.QueryDelegatorDelegationsResponse> getDelegatorDelegationsMethod;
    if ((getDelegatorDelegationsMethod = QueryGrpc.getDelegatorDelegationsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDelegatorDelegationsMethod = QueryGrpc.getDelegatorDelegationsMethod) == null) {
          QueryGrpc.getDelegatorDelegationsMethod = getDelegatorDelegationsMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.QueryProto.QueryDelegatorDelegationsRequest, com.zrchain.validation.QueryProto.QueryDelegatorDelegationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelegatorDelegations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryDelegatorDelegationsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryDelegatorDelegationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DelegatorDelegations"))
              .build();
        }
      }
    }
    return getDelegatorDelegationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsRequest,
      com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsResponse> getDelegatorUnbondingDelegationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelegatorUnbondingDelegations",
      requestType = com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsRequest.class,
      responseType = com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsRequest,
      com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsResponse> getDelegatorUnbondingDelegationsMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsRequest, com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsResponse> getDelegatorUnbondingDelegationsMethod;
    if ((getDelegatorUnbondingDelegationsMethod = QueryGrpc.getDelegatorUnbondingDelegationsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDelegatorUnbondingDelegationsMethod = QueryGrpc.getDelegatorUnbondingDelegationsMethod) == null) {
          QueryGrpc.getDelegatorUnbondingDelegationsMethod = getDelegatorUnbondingDelegationsMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsRequest, com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelegatorUnbondingDelegations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DelegatorUnbondingDelegations"))
              .build();
        }
      }
    }
    return getDelegatorUnbondingDelegationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryRedelegationsRequest,
      com.zrchain.validation.QueryProto.QueryRedelegationsResponse> getRedelegationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Redelegations",
      requestType = com.zrchain.validation.QueryProto.QueryRedelegationsRequest.class,
      responseType = com.zrchain.validation.QueryProto.QueryRedelegationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryRedelegationsRequest,
      com.zrchain.validation.QueryProto.QueryRedelegationsResponse> getRedelegationsMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryRedelegationsRequest, com.zrchain.validation.QueryProto.QueryRedelegationsResponse> getRedelegationsMethod;
    if ((getRedelegationsMethod = QueryGrpc.getRedelegationsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRedelegationsMethod = QueryGrpc.getRedelegationsMethod) == null) {
          QueryGrpc.getRedelegationsMethod = getRedelegationsMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.QueryProto.QueryRedelegationsRequest, com.zrchain.validation.QueryProto.QueryRedelegationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Redelegations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryRedelegationsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryRedelegationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Redelegations"))
              .build();
        }
      }
    }
    return getRedelegationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryDelegatorValidatorsRequest,
      com.zrchain.validation.QueryProto.QueryDelegatorValidatorsResponse> getDelegatorValidatorsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelegatorValidators",
      requestType = com.zrchain.validation.QueryProto.QueryDelegatorValidatorsRequest.class,
      responseType = com.zrchain.validation.QueryProto.QueryDelegatorValidatorsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryDelegatorValidatorsRequest,
      com.zrchain.validation.QueryProto.QueryDelegatorValidatorsResponse> getDelegatorValidatorsMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryDelegatorValidatorsRequest, com.zrchain.validation.QueryProto.QueryDelegatorValidatorsResponse> getDelegatorValidatorsMethod;
    if ((getDelegatorValidatorsMethod = QueryGrpc.getDelegatorValidatorsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDelegatorValidatorsMethod = QueryGrpc.getDelegatorValidatorsMethod) == null) {
          QueryGrpc.getDelegatorValidatorsMethod = getDelegatorValidatorsMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.QueryProto.QueryDelegatorValidatorsRequest, com.zrchain.validation.QueryProto.QueryDelegatorValidatorsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelegatorValidators"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryDelegatorValidatorsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryDelegatorValidatorsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DelegatorValidators"))
              .build();
        }
      }
    }
    return getDelegatorValidatorsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryDelegatorValidatorRequest,
      com.zrchain.validation.QueryProto.QueryDelegatorValidatorResponse> getDelegatorValidatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelegatorValidator",
      requestType = com.zrchain.validation.QueryProto.QueryDelegatorValidatorRequest.class,
      responseType = com.zrchain.validation.QueryProto.QueryDelegatorValidatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryDelegatorValidatorRequest,
      com.zrchain.validation.QueryProto.QueryDelegatorValidatorResponse> getDelegatorValidatorMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryDelegatorValidatorRequest, com.zrchain.validation.QueryProto.QueryDelegatorValidatorResponse> getDelegatorValidatorMethod;
    if ((getDelegatorValidatorMethod = QueryGrpc.getDelegatorValidatorMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDelegatorValidatorMethod = QueryGrpc.getDelegatorValidatorMethod) == null) {
          QueryGrpc.getDelegatorValidatorMethod = getDelegatorValidatorMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.QueryProto.QueryDelegatorValidatorRequest, com.zrchain.validation.QueryProto.QueryDelegatorValidatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelegatorValidator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryDelegatorValidatorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryDelegatorValidatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DelegatorValidator"))
              .build();
        }
      }
    }
    return getDelegatorValidatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryHistoricalInfoRequest,
      com.zrchain.validation.QueryProto.QueryHistoricalInfoResponse> getHistoricalInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HistoricalInfo",
      requestType = com.zrchain.validation.QueryProto.QueryHistoricalInfoRequest.class,
      responseType = com.zrchain.validation.QueryProto.QueryHistoricalInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryHistoricalInfoRequest,
      com.zrchain.validation.QueryProto.QueryHistoricalInfoResponse> getHistoricalInfoMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryHistoricalInfoRequest, com.zrchain.validation.QueryProto.QueryHistoricalInfoResponse> getHistoricalInfoMethod;
    if ((getHistoricalInfoMethod = QueryGrpc.getHistoricalInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getHistoricalInfoMethod = QueryGrpc.getHistoricalInfoMethod) == null) {
          QueryGrpc.getHistoricalInfoMethod = getHistoricalInfoMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.QueryProto.QueryHistoricalInfoRequest, com.zrchain.validation.QueryProto.QueryHistoricalInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HistoricalInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryHistoricalInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryHistoricalInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("HistoricalInfo"))
              .build();
        }
      }
    }
    return getHistoricalInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryPoolRequest,
      com.zrchain.validation.QueryProto.QueryPoolResponse> getPoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Pool",
      requestType = com.zrchain.validation.QueryProto.QueryPoolRequest.class,
      responseType = com.zrchain.validation.QueryProto.QueryPoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryPoolRequest,
      com.zrchain.validation.QueryProto.QueryPoolResponse> getPoolMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryPoolRequest, com.zrchain.validation.QueryProto.QueryPoolResponse> getPoolMethod;
    if ((getPoolMethod = QueryGrpc.getPoolMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPoolMethod = QueryGrpc.getPoolMethod) == null) {
          QueryGrpc.getPoolMethod = getPoolMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.QueryProto.QueryPoolRequest, com.zrchain.validation.QueryProto.QueryPoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Pool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryPoolRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryPoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Pool"))
              .build();
        }
      }
    }
    return getPoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryParamsRequest,
      com.zrchain.validation.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.zrchain.validation.QueryProto.QueryParamsRequest.class,
      responseType = com.zrchain.validation.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryParamsRequest,
      com.zrchain.validation.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryParamsRequest, com.zrchain.validation.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.QueryProto.QueryParamsRequest, com.zrchain.validation.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryPowerRequest,
      com.zrchain.validation.QueryProto.QueryPowerResponse> getValidatorPowerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValidatorPower",
      requestType = com.zrchain.validation.QueryProto.QueryPowerRequest.class,
      responseType = com.zrchain.validation.QueryProto.QueryPowerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryPowerRequest,
      com.zrchain.validation.QueryProto.QueryPowerResponse> getValidatorPowerMethod() {
    io.grpc.MethodDescriptor<com.zrchain.validation.QueryProto.QueryPowerRequest, com.zrchain.validation.QueryProto.QueryPowerResponse> getValidatorPowerMethod;
    if ((getValidatorPowerMethod = QueryGrpc.getValidatorPowerMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValidatorPowerMethod = QueryGrpc.getValidatorPowerMethod) == null) {
          QueryGrpc.getValidatorPowerMethod = getValidatorPowerMethod =
              io.grpc.MethodDescriptor.<com.zrchain.validation.QueryProto.QueryPowerRequest, com.zrchain.validation.QueryProto.QueryPowerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValidatorPower"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryPowerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.zrchain.validation.QueryProto.QueryPowerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValidatorPower"))
              .build();
        }
      }
    }
    return getValidatorPowerMethod;
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
     * Validators queries all validators that match the given status.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    default void validators(com.zrchain.validation.QueryProto.QueryValidatorsRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryValidatorsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValidatorsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Validator queries validator info for given validator address.
     * </pre>
     */
    default void validator(com.zrchain.validation.QueryProto.QueryValidatorRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryValidatorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValidatorMethod(), responseObserver);
    }

    /**
     * <pre>
     * ValidatorDelegations queries delegate info for given validator.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    default void validatorDelegations(com.zrchain.validation.QueryProto.QueryValidatorDelegationsRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryValidatorDelegationsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValidatorDelegationsMethod(), responseObserver);
    }

    /**
     * <pre>
     * ValidatorUnbondingDelegations queries unbonding delegations of a validator.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    default void validatorUnbondingDelegations(com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValidatorUnbondingDelegationsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Delegation queries delegate info for given validator delegator pair.
     * </pre>
     */
    default void delegation(com.zrchain.validation.QueryProto.QueryDelegationRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryDelegationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDelegationMethod(), responseObserver);
    }

    /**
     * <pre>
     * UnbondingDelegation queries unbonding info for given validator delegator
     * pair.
     * </pre>
     */
    default void unbondingDelegation(com.zrchain.validation.QueryProto.QueryUnbondingDelegationRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryUnbondingDelegationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUnbondingDelegationMethod(), responseObserver);
    }

    /**
     * <pre>
     * DelegatorDelegations queries all delegations of a given delegator address.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    default void delegatorDelegations(com.zrchain.validation.QueryProto.QueryDelegatorDelegationsRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryDelegatorDelegationsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDelegatorDelegationsMethod(), responseObserver);
    }

    /**
     * <pre>
     * DelegatorUnbondingDelegations queries all unbonding delegations of a given
     * delegator address.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    default void delegatorUnbondingDelegations(com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDelegatorUnbondingDelegationsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Redelegations queries redelegations of given address.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    default void redelegations(com.zrchain.validation.QueryProto.QueryRedelegationsRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryRedelegationsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRedelegationsMethod(), responseObserver);
    }

    /**
     * <pre>
     * DelegatorValidators queries all validators info for given delegator
     * address.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    default void delegatorValidators(com.zrchain.validation.QueryProto.QueryDelegatorValidatorsRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryDelegatorValidatorsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDelegatorValidatorsMethod(), responseObserver);
    }

    /**
     * <pre>
     * DelegatorValidator queries validator info for given delegator validator
     * pair.
     * </pre>
     */
    default void delegatorValidator(com.zrchain.validation.QueryProto.QueryDelegatorValidatorRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryDelegatorValidatorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDelegatorValidatorMethod(), responseObserver);
    }

    /**
     * <pre>
     * HistoricalInfo queries the historical info for given height.
     * </pre>
     */
    default void historicalInfo(com.zrchain.validation.QueryProto.QueryHistoricalInfoRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryHistoricalInfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHistoricalInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * Pool queries the pool info.
     * </pre>
     */
    default void pool(com.zrchain.validation.QueryProto.QueryPoolRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryPoolResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPoolMethod(), responseObserver);
    }

    /**
     * <pre>
     * Parameters queries the staking parameters.
     * </pre>
     */
    default void params(com.zrchain.validation.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     */
    default void validatorPower(com.zrchain.validation.QueryProto.QueryPowerRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryPowerResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValidatorPowerMethod(), responseObserver);
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
     * Validators queries all validators that match the given status.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public void validators(com.zrchain.validation.QueryProto.QueryValidatorsRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryValidatorsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValidatorsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Validator queries validator info for given validator address.
     * </pre>
     */
    public void validator(com.zrchain.validation.QueryProto.QueryValidatorRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryValidatorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValidatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ValidatorDelegations queries delegate info for given validator.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public void validatorDelegations(com.zrchain.validation.QueryProto.QueryValidatorDelegationsRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryValidatorDelegationsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValidatorDelegationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ValidatorUnbondingDelegations queries unbonding delegations of a validator.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public void validatorUnbondingDelegations(com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValidatorUnbondingDelegationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Delegation queries delegate info for given validator delegator pair.
     * </pre>
     */
    public void delegation(com.zrchain.validation.QueryProto.QueryDelegationRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryDelegationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDelegationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UnbondingDelegation queries unbonding info for given validator delegator
     * pair.
     * </pre>
     */
    public void unbondingDelegation(com.zrchain.validation.QueryProto.QueryUnbondingDelegationRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryUnbondingDelegationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUnbondingDelegationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DelegatorDelegations queries all delegations of a given delegator address.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public void delegatorDelegations(com.zrchain.validation.QueryProto.QueryDelegatorDelegationsRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryDelegatorDelegationsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDelegatorDelegationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DelegatorUnbondingDelegations queries all unbonding delegations of a given
     * delegator address.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public void delegatorUnbondingDelegations(com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDelegatorUnbondingDelegationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Redelegations queries redelegations of given address.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public void redelegations(com.zrchain.validation.QueryProto.QueryRedelegationsRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryRedelegationsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRedelegationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DelegatorValidators queries all validators info for given delegator
     * address.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public void delegatorValidators(com.zrchain.validation.QueryProto.QueryDelegatorValidatorsRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryDelegatorValidatorsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDelegatorValidatorsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DelegatorValidator queries validator info for given delegator validator
     * pair.
     * </pre>
     */
    public void delegatorValidator(com.zrchain.validation.QueryProto.QueryDelegatorValidatorRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryDelegatorValidatorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDelegatorValidatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * HistoricalInfo queries the historical info for given height.
     * </pre>
     */
    public void historicalInfo(com.zrchain.validation.QueryProto.QueryHistoricalInfoRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryHistoricalInfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHistoricalInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Pool queries the pool info.
     * </pre>
     */
    public void pool(com.zrchain.validation.QueryProto.QueryPoolRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryPoolResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Parameters queries the staking parameters.
     * </pre>
     */
    public void params(com.zrchain.validation.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void validatorPower(com.zrchain.validation.QueryProto.QueryPowerRequest request,
        io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryPowerResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValidatorPowerMethod(), getCallOptions()), request, responseObserver);
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
     * Validators queries all validators that match the given status.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public com.zrchain.validation.QueryProto.QueryValidatorsResponse validators(com.zrchain.validation.QueryProto.QueryValidatorsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValidatorsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Validator queries validator info for given validator address.
     * </pre>
     */
    public com.zrchain.validation.QueryProto.QueryValidatorResponse validator(com.zrchain.validation.QueryProto.QueryValidatorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValidatorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ValidatorDelegations queries delegate info for given validator.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public com.zrchain.validation.QueryProto.QueryValidatorDelegationsResponse validatorDelegations(com.zrchain.validation.QueryProto.QueryValidatorDelegationsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValidatorDelegationsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ValidatorUnbondingDelegations queries unbonding delegations of a validator.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsResponse validatorUnbondingDelegations(com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValidatorUnbondingDelegationsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Delegation queries delegate info for given validator delegator pair.
     * </pre>
     */
    public com.zrchain.validation.QueryProto.QueryDelegationResponse delegation(com.zrchain.validation.QueryProto.QueryDelegationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDelegationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UnbondingDelegation queries unbonding info for given validator delegator
     * pair.
     * </pre>
     */
    public com.zrchain.validation.QueryProto.QueryUnbondingDelegationResponse unbondingDelegation(com.zrchain.validation.QueryProto.QueryUnbondingDelegationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUnbondingDelegationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DelegatorDelegations queries all delegations of a given delegator address.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public com.zrchain.validation.QueryProto.QueryDelegatorDelegationsResponse delegatorDelegations(com.zrchain.validation.QueryProto.QueryDelegatorDelegationsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDelegatorDelegationsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DelegatorUnbondingDelegations queries all unbonding delegations of a given
     * delegator address.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsResponse delegatorUnbondingDelegations(com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDelegatorUnbondingDelegationsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Redelegations queries redelegations of given address.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public com.zrchain.validation.QueryProto.QueryRedelegationsResponse redelegations(com.zrchain.validation.QueryProto.QueryRedelegationsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRedelegationsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DelegatorValidators queries all validators info for given delegator
     * address.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public com.zrchain.validation.QueryProto.QueryDelegatorValidatorsResponse delegatorValidators(com.zrchain.validation.QueryProto.QueryDelegatorValidatorsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDelegatorValidatorsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DelegatorValidator queries validator info for given delegator validator
     * pair.
     * </pre>
     */
    public com.zrchain.validation.QueryProto.QueryDelegatorValidatorResponse delegatorValidator(com.zrchain.validation.QueryProto.QueryDelegatorValidatorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDelegatorValidatorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * HistoricalInfo queries the historical info for given height.
     * </pre>
     */
    public com.zrchain.validation.QueryProto.QueryHistoricalInfoResponse historicalInfo(com.zrchain.validation.QueryProto.QueryHistoricalInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHistoricalInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Pool queries the pool info.
     * </pre>
     */
    public com.zrchain.validation.QueryProto.QueryPoolResponse pool(com.zrchain.validation.QueryProto.QueryPoolRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPoolMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Parameters queries the staking parameters.
     * </pre>
     */
    public com.zrchain.validation.QueryProto.QueryParamsResponse params(com.zrchain.validation.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.zrchain.validation.QueryProto.QueryPowerResponse validatorPower(com.zrchain.validation.QueryProto.QueryPowerRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValidatorPowerMethod(), getCallOptions(), request);
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
     * Validators queries all validators that match the given status.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.QueryProto.QueryValidatorsResponse> validators(
        com.zrchain.validation.QueryProto.QueryValidatorsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValidatorsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Validator queries validator info for given validator address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.QueryProto.QueryValidatorResponse> validator(
        com.zrchain.validation.QueryProto.QueryValidatorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValidatorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ValidatorDelegations queries delegate info for given validator.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.QueryProto.QueryValidatorDelegationsResponse> validatorDelegations(
        com.zrchain.validation.QueryProto.QueryValidatorDelegationsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValidatorDelegationsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ValidatorUnbondingDelegations queries unbonding delegations of a validator.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsResponse> validatorUnbondingDelegations(
        com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValidatorUnbondingDelegationsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Delegation queries delegate info for given validator delegator pair.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.QueryProto.QueryDelegationResponse> delegation(
        com.zrchain.validation.QueryProto.QueryDelegationRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDelegationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UnbondingDelegation queries unbonding info for given validator delegator
     * pair.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.QueryProto.QueryUnbondingDelegationResponse> unbondingDelegation(
        com.zrchain.validation.QueryProto.QueryUnbondingDelegationRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUnbondingDelegationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DelegatorDelegations queries all delegations of a given delegator address.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.QueryProto.QueryDelegatorDelegationsResponse> delegatorDelegations(
        com.zrchain.validation.QueryProto.QueryDelegatorDelegationsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDelegatorDelegationsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DelegatorUnbondingDelegations queries all unbonding delegations of a given
     * delegator address.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsResponse> delegatorUnbondingDelegations(
        com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDelegatorUnbondingDelegationsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Redelegations queries redelegations of given address.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.QueryProto.QueryRedelegationsResponse> redelegations(
        com.zrchain.validation.QueryProto.QueryRedelegationsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRedelegationsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DelegatorValidators queries all validators info for given delegator
     * address.
     * When called from another module, this query might consume a high amount of
     * gas if the pagination field is incorrectly set.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.QueryProto.QueryDelegatorValidatorsResponse> delegatorValidators(
        com.zrchain.validation.QueryProto.QueryDelegatorValidatorsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDelegatorValidatorsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DelegatorValidator queries validator info for given delegator validator
     * pair.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.QueryProto.QueryDelegatorValidatorResponse> delegatorValidator(
        com.zrchain.validation.QueryProto.QueryDelegatorValidatorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDelegatorValidatorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * HistoricalInfo queries the historical info for given height.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.QueryProto.QueryHistoricalInfoResponse> historicalInfo(
        com.zrchain.validation.QueryProto.QueryHistoricalInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getHistoricalInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Pool queries the pool info.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.QueryProto.QueryPoolResponse> pool(
        com.zrchain.validation.QueryProto.QueryPoolRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPoolMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Parameters queries the staking parameters.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.QueryProto.QueryParamsResponse> params(
        com.zrchain.validation.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.zrchain.validation.QueryProto.QueryPowerResponse> validatorPower(
        com.zrchain.validation.QueryProto.QueryPowerRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValidatorPowerMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_VALIDATORS = 0;
  private static final int METHODID_VALIDATOR = 1;
  private static final int METHODID_VALIDATOR_DELEGATIONS = 2;
  private static final int METHODID_VALIDATOR_UNBONDING_DELEGATIONS = 3;
  private static final int METHODID_DELEGATION = 4;
  private static final int METHODID_UNBONDING_DELEGATION = 5;
  private static final int METHODID_DELEGATOR_DELEGATIONS = 6;
  private static final int METHODID_DELEGATOR_UNBONDING_DELEGATIONS = 7;
  private static final int METHODID_REDELEGATIONS = 8;
  private static final int METHODID_DELEGATOR_VALIDATORS = 9;
  private static final int METHODID_DELEGATOR_VALIDATOR = 10;
  private static final int METHODID_HISTORICAL_INFO = 11;
  private static final int METHODID_POOL = 12;
  private static final int METHODID_PARAMS = 13;
  private static final int METHODID_VALIDATOR_POWER = 14;

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
        case METHODID_VALIDATORS:
          serviceImpl.validators((com.zrchain.validation.QueryProto.QueryValidatorsRequest) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryValidatorsResponse>) responseObserver);
          break;
        case METHODID_VALIDATOR:
          serviceImpl.validator((com.zrchain.validation.QueryProto.QueryValidatorRequest) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryValidatorResponse>) responseObserver);
          break;
        case METHODID_VALIDATOR_DELEGATIONS:
          serviceImpl.validatorDelegations((com.zrchain.validation.QueryProto.QueryValidatorDelegationsRequest) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryValidatorDelegationsResponse>) responseObserver);
          break;
        case METHODID_VALIDATOR_UNBONDING_DELEGATIONS:
          serviceImpl.validatorUnbondingDelegations((com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsRequest) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsResponse>) responseObserver);
          break;
        case METHODID_DELEGATION:
          serviceImpl.delegation((com.zrchain.validation.QueryProto.QueryDelegationRequest) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryDelegationResponse>) responseObserver);
          break;
        case METHODID_UNBONDING_DELEGATION:
          serviceImpl.unbondingDelegation((com.zrchain.validation.QueryProto.QueryUnbondingDelegationRequest) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryUnbondingDelegationResponse>) responseObserver);
          break;
        case METHODID_DELEGATOR_DELEGATIONS:
          serviceImpl.delegatorDelegations((com.zrchain.validation.QueryProto.QueryDelegatorDelegationsRequest) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryDelegatorDelegationsResponse>) responseObserver);
          break;
        case METHODID_DELEGATOR_UNBONDING_DELEGATIONS:
          serviceImpl.delegatorUnbondingDelegations((com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsRequest) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsResponse>) responseObserver);
          break;
        case METHODID_REDELEGATIONS:
          serviceImpl.redelegations((com.zrchain.validation.QueryProto.QueryRedelegationsRequest) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryRedelegationsResponse>) responseObserver);
          break;
        case METHODID_DELEGATOR_VALIDATORS:
          serviceImpl.delegatorValidators((com.zrchain.validation.QueryProto.QueryDelegatorValidatorsRequest) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryDelegatorValidatorsResponse>) responseObserver);
          break;
        case METHODID_DELEGATOR_VALIDATOR:
          serviceImpl.delegatorValidator((com.zrchain.validation.QueryProto.QueryDelegatorValidatorRequest) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryDelegatorValidatorResponse>) responseObserver);
          break;
        case METHODID_HISTORICAL_INFO:
          serviceImpl.historicalInfo((com.zrchain.validation.QueryProto.QueryHistoricalInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryHistoricalInfoResponse>) responseObserver);
          break;
        case METHODID_POOL:
          serviceImpl.pool((com.zrchain.validation.QueryProto.QueryPoolRequest) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryPoolResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((com.zrchain.validation.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_VALIDATOR_POWER:
          serviceImpl.validatorPower((com.zrchain.validation.QueryProto.QueryPowerRequest) request,
              (io.grpc.stub.StreamObserver<com.zrchain.validation.QueryProto.QueryPowerResponse>) responseObserver);
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
          getValidatorsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.QueryProto.QueryValidatorsRequest,
              com.zrchain.validation.QueryProto.QueryValidatorsResponse>(
                service, METHODID_VALIDATORS)))
        .addMethod(
          getValidatorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.QueryProto.QueryValidatorRequest,
              com.zrchain.validation.QueryProto.QueryValidatorResponse>(
                service, METHODID_VALIDATOR)))
        .addMethod(
          getValidatorDelegationsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.QueryProto.QueryValidatorDelegationsRequest,
              com.zrchain.validation.QueryProto.QueryValidatorDelegationsResponse>(
                service, METHODID_VALIDATOR_DELEGATIONS)))
        .addMethod(
          getValidatorUnbondingDelegationsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsRequest,
              com.zrchain.validation.QueryProto.QueryValidatorUnbondingDelegationsResponse>(
                service, METHODID_VALIDATOR_UNBONDING_DELEGATIONS)))
        .addMethod(
          getDelegationMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.QueryProto.QueryDelegationRequest,
              com.zrchain.validation.QueryProto.QueryDelegationResponse>(
                service, METHODID_DELEGATION)))
        .addMethod(
          getUnbondingDelegationMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.QueryProto.QueryUnbondingDelegationRequest,
              com.zrchain.validation.QueryProto.QueryUnbondingDelegationResponse>(
                service, METHODID_UNBONDING_DELEGATION)))
        .addMethod(
          getDelegatorDelegationsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.QueryProto.QueryDelegatorDelegationsRequest,
              com.zrchain.validation.QueryProto.QueryDelegatorDelegationsResponse>(
                service, METHODID_DELEGATOR_DELEGATIONS)))
        .addMethod(
          getDelegatorUnbondingDelegationsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsRequest,
              com.zrchain.validation.QueryProto.QueryDelegatorUnbondingDelegationsResponse>(
                service, METHODID_DELEGATOR_UNBONDING_DELEGATIONS)))
        .addMethod(
          getRedelegationsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.QueryProto.QueryRedelegationsRequest,
              com.zrchain.validation.QueryProto.QueryRedelegationsResponse>(
                service, METHODID_REDELEGATIONS)))
        .addMethod(
          getDelegatorValidatorsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.QueryProto.QueryDelegatorValidatorsRequest,
              com.zrchain.validation.QueryProto.QueryDelegatorValidatorsResponse>(
                service, METHODID_DELEGATOR_VALIDATORS)))
        .addMethod(
          getDelegatorValidatorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.QueryProto.QueryDelegatorValidatorRequest,
              com.zrchain.validation.QueryProto.QueryDelegatorValidatorResponse>(
                service, METHODID_DELEGATOR_VALIDATOR)))
        .addMethod(
          getHistoricalInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.QueryProto.QueryHistoricalInfoRequest,
              com.zrchain.validation.QueryProto.QueryHistoricalInfoResponse>(
                service, METHODID_HISTORICAL_INFO)))
        .addMethod(
          getPoolMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.QueryProto.QueryPoolRequest,
              com.zrchain.validation.QueryProto.QueryPoolResponse>(
                service, METHODID_POOL)))
        .addMethod(
          getParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.QueryProto.QueryParamsRequest,
              com.zrchain.validation.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .addMethod(
          getValidatorPowerMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.zrchain.validation.QueryProto.QueryPowerRequest,
              com.zrchain.validation.QueryProto.QueryPowerResponse>(
                service, METHODID_VALIDATOR_POWER)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.zrchain.validation.QueryProto.getDescriptor();
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
              .addMethod(getValidatorsMethod())
              .addMethod(getValidatorMethod())
              .addMethod(getValidatorDelegationsMethod())
              .addMethod(getValidatorUnbondingDelegationsMethod())
              .addMethod(getDelegationMethod())
              .addMethod(getUnbondingDelegationMethod())
              .addMethod(getDelegatorDelegationsMethod())
              .addMethod(getDelegatorUnbondingDelegationsMethod())
              .addMethod(getRedelegationsMethod())
              .addMethod(getDelegatorValidatorsMethod())
              .addMethod(getDelegatorValidatorMethod())
              .addMethod(getHistoricalInfoMethod())
              .addMethod(getPoolMethod())
              .addMethod(getParamsMethod())
              .addMethod(getValidatorPowerMethod())
              .build();
        }
      }
    }
    return result;
  }
}
