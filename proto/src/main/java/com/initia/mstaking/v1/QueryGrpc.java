package com.initia.mstaking.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: initia/mstaking/v1/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "initia.mstaking.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryValidatorsRequest,
      com.initia.mstaking.v1.QueryProto.QueryValidatorsResponse> getValidatorsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Validators",
      requestType = com.initia.mstaking.v1.QueryProto.QueryValidatorsRequest.class,
      responseType = com.initia.mstaking.v1.QueryProto.QueryValidatorsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryValidatorsRequest,
      com.initia.mstaking.v1.QueryProto.QueryValidatorsResponse> getValidatorsMethod() {
    io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryValidatorsRequest, com.initia.mstaking.v1.QueryProto.QueryValidatorsResponse> getValidatorsMethod;
    if ((getValidatorsMethod = QueryGrpc.getValidatorsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValidatorsMethod = QueryGrpc.getValidatorsMethod) == null) {
          QueryGrpc.getValidatorsMethod = getValidatorsMethod =
              io.grpc.MethodDescriptor.<com.initia.mstaking.v1.QueryProto.QueryValidatorsRequest, com.initia.mstaking.v1.QueryProto.QueryValidatorsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Validators"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryValidatorsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryValidatorsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Validators"))
              .build();
        }
      }
    }
    return getValidatorsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryValidatorRequest,
      com.initia.mstaking.v1.QueryProto.QueryValidatorResponse> getValidatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Validator",
      requestType = com.initia.mstaking.v1.QueryProto.QueryValidatorRequest.class,
      responseType = com.initia.mstaking.v1.QueryProto.QueryValidatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryValidatorRequest,
      com.initia.mstaking.v1.QueryProto.QueryValidatorResponse> getValidatorMethod() {
    io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryValidatorRequest, com.initia.mstaking.v1.QueryProto.QueryValidatorResponse> getValidatorMethod;
    if ((getValidatorMethod = QueryGrpc.getValidatorMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValidatorMethod = QueryGrpc.getValidatorMethod) == null) {
          QueryGrpc.getValidatorMethod = getValidatorMethod =
              io.grpc.MethodDescriptor.<com.initia.mstaking.v1.QueryProto.QueryValidatorRequest, com.initia.mstaking.v1.QueryProto.QueryValidatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Validator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryValidatorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryValidatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Validator"))
              .build();
        }
      }
    }
    return getValidatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsRequest,
      com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsResponse> getValidatorDelegationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValidatorDelegations",
      requestType = com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsRequest.class,
      responseType = com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsRequest,
      com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsResponse> getValidatorDelegationsMethod() {
    io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsRequest, com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsResponse> getValidatorDelegationsMethod;
    if ((getValidatorDelegationsMethod = QueryGrpc.getValidatorDelegationsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValidatorDelegationsMethod = QueryGrpc.getValidatorDelegationsMethod) == null) {
          QueryGrpc.getValidatorDelegationsMethod = getValidatorDelegationsMethod =
              io.grpc.MethodDescriptor.<com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsRequest, com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValidatorDelegations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValidatorDelegations"))
              .build();
        }
      }
    }
    return getValidatorDelegationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsRequest,
      com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsResponse> getValidatorUnbondingDelegationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValidatorUnbondingDelegations",
      requestType = com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsRequest.class,
      responseType = com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsRequest,
      com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsResponse> getValidatorUnbondingDelegationsMethod() {
    io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsRequest, com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsResponse> getValidatorUnbondingDelegationsMethod;
    if ((getValidatorUnbondingDelegationsMethod = QueryGrpc.getValidatorUnbondingDelegationsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValidatorUnbondingDelegationsMethod = QueryGrpc.getValidatorUnbondingDelegationsMethod) == null) {
          QueryGrpc.getValidatorUnbondingDelegationsMethod = getValidatorUnbondingDelegationsMethod =
              io.grpc.MethodDescriptor.<com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsRequest, com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValidatorUnbondingDelegations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValidatorUnbondingDelegations"))
              .build();
        }
      }
    }
    return getValidatorUnbondingDelegationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryDelegationRequest,
      com.initia.mstaking.v1.QueryProto.QueryDelegationResponse> getDelegationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Delegation",
      requestType = com.initia.mstaking.v1.QueryProto.QueryDelegationRequest.class,
      responseType = com.initia.mstaking.v1.QueryProto.QueryDelegationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryDelegationRequest,
      com.initia.mstaking.v1.QueryProto.QueryDelegationResponse> getDelegationMethod() {
    io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryDelegationRequest, com.initia.mstaking.v1.QueryProto.QueryDelegationResponse> getDelegationMethod;
    if ((getDelegationMethod = QueryGrpc.getDelegationMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDelegationMethod = QueryGrpc.getDelegationMethod) == null) {
          QueryGrpc.getDelegationMethod = getDelegationMethod =
              io.grpc.MethodDescriptor.<com.initia.mstaking.v1.QueryProto.QueryDelegationRequest, com.initia.mstaking.v1.QueryProto.QueryDelegationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Delegation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryDelegationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryDelegationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Delegation"))
              .build();
        }
      }
    }
    return getDelegationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationRequest,
      com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationResponse> getUnbondingDelegationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnbondingDelegation",
      requestType = com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationRequest.class,
      responseType = com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationRequest,
      com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationResponse> getUnbondingDelegationMethod() {
    io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationRequest, com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationResponse> getUnbondingDelegationMethod;
    if ((getUnbondingDelegationMethod = QueryGrpc.getUnbondingDelegationMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUnbondingDelegationMethod = QueryGrpc.getUnbondingDelegationMethod) == null) {
          QueryGrpc.getUnbondingDelegationMethod = getUnbondingDelegationMethod =
              io.grpc.MethodDescriptor.<com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationRequest, com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnbondingDelegation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UnbondingDelegation"))
              .build();
        }
      }
    }
    return getUnbondingDelegationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsRequest,
      com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsResponse> getDelegatorDelegationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelegatorDelegations",
      requestType = com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsRequest.class,
      responseType = com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsRequest,
      com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsResponse> getDelegatorDelegationsMethod() {
    io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsRequest, com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsResponse> getDelegatorDelegationsMethod;
    if ((getDelegatorDelegationsMethod = QueryGrpc.getDelegatorDelegationsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDelegatorDelegationsMethod = QueryGrpc.getDelegatorDelegationsMethod) == null) {
          QueryGrpc.getDelegatorDelegationsMethod = getDelegatorDelegationsMethod =
              io.grpc.MethodDescriptor.<com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsRequest, com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelegatorDelegations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DelegatorDelegations"))
              .build();
        }
      }
    }
    return getDelegatorDelegationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsRequest,
      com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsResponse> getDelegatorUnbondingDelegationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelegatorUnbondingDelegations",
      requestType = com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsRequest.class,
      responseType = com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsRequest,
      com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsResponse> getDelegatorUnbondingDelegationsMethod() {
    io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsRequest, com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsResponse> getDelegatorUnbondingDelegationsMethod;
    if ((getDelegatorUnbondingDelegationsMethod = QueryGrpc.getDelegatorUnbondingDelegationsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDelegatorUnbondingDelegationsMethod = QueryGrpc.getDelegatorUnbondingDelegationsMethod) == null) {
          QueryGrpc.getDelegatorUnbondingDelegationsMethod = getDelegatorUnbondingDelegationsMethod =
              io.grpc.MethodDescriptor.<com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsRequest, com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelegatorUnbondingDelegations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DelegatorUnbondingDelegations"))
              .build();
        }
      }
    }
    return getDelegatorUnbondingDelegationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryRedelegationsRequest,
      com.initia.mstaking.v1.QueryProto.QueryRedelegationsResponse> getRedelegationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Redelegations",
      requestType = com.initia.mstaking.v1.QueryProto.QueryRedelegationsRequest.class,
      responseType = com.initia.mstaking.v1.QueryProto.QueryRedelegationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryRedelegationsRequest,
      com.initia.mstaking.v1.QueryProto.QueryRedelegationsResponse> getRedelegationsMethod() {
    io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryRedelegationsRequest, com.initia.mstaking.v1.QueryProto.QueryRedelegationsResponse> getRedelegationsMethod;
    if ((getRedelegationsMethod = QueryGrpc.getRedelegationsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRedelegationsMethod = QueryGrpc.getRedelegationsMethod) == null) {
          QueryGrpc.getRedelegationsMethod = getRedelegationsMethod =
              io.grpc.MethodDescriptor.<com.initia.mstaking.v1.QueryProto.QueryRedelegationsRequest, com.initia.mstaking.v1.QueryProto.QueryRedelegationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Redelegations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryRedelegationsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryRedelegationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Redelegations"))
              .build();
        }
      }
    }
    return getRedelegationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsRequest,
      com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsResponse> getDelegatorValidatorsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelegatorValidators",
      requestType = com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsRequest.class,
      responseType = com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsRequest,
      com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsResponse> getDelegatorValidatorsMethod() {
    io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsRequest, com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsResponse> getDelegatorValidatorsMethod;
    if ((getDelegatorValidatorsMethod = QueryGrpc.getDelegatorValidatorsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDelegatorValidatorsMethod = QueryGrpc.getDelegatorValidatorsMethod) == null) {
          QueryGrpc.getDelegatorValidatorsMethod = getDelegatorValidatorsMethod =
              io.grpc.MethodDescriptor.<com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsRequest, com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelegatorValidators"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DelegatorValidators"))
              .build();
        }
      }
    }
    return getDelegatorValidatorsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorRequest,
      com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorResponse> getDelegatorValidatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelegatorValidator",
      requestType = com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorRequest.class,
      responseType = com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorRequest,
      com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorResponse> getDelegatorValidatorMethod() {
    io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorRequest, com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorResponse> getDelegatorValidatorMethod;
    if ((getDelegatorValidatorMethod = QueryGrpc.getDelegatorValidatorMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDelegatorValidatorMethod = QueryGrpc.getDelegatorValidatorMethod) == null) {
          QueryGrpc.getDelegatorValidatorMethod = getDelegatorValidatorMethod =
              io.grpc.MethodDescriptor.<com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorRequest, com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelegatorValidator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DelegatorValidator"))
              .build();
        }
      }
    }
    return getDelegatorValidatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryPoolRequest,
      com.initia.mstaking.v1.QueryProto.QueryPoolResponse> getPoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Pool",
      requestType = com.initia.mstaking.v1.QueryProto.QueryPoolRequest.class,
      responseType = com.initia.mstaking.v1.QueryProto.QueryPoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryPoolRequest,
      com.initia.mstaking.v1.QueryProto.QueryPoolResponse> getPoolMethod() {
    io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryPoolRequest, com.initia.mstaking.v1.QueryProto.QueryPoolResponse> getPoolMethod;
    if ((getPoolMethod = QueryGrpc.getPoolMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPoolMethod = QueryGrpc.getPoolMethod) == null) {
          QueryGrpc.getPoolMethod = getPoolMethod =
              io.grpc.MethodDescriptor.<com.initia.mstaking.v1.QueryProto.QueryPoolRequest, com.initia.mstaking.v1.QueryProto.QueryPoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Pool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryPoolRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryPoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Pool"))
              .build();
        }
      }
    }
    return getPoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryParamsRequest,
      com.initia.mstaking.v1.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.initia.mstaking.v1.QueryProto.QueryParamsRequest.class,
      responseType = com.initia.mstaking.v1.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryParamsRequest,
      com.initia.mstaking.v1.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.initia.mstaking.v1.QueryProto.QueryParamsRequest, com.initia.mstaking.v1.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.initia.mstaking.v1.QueryProto.QueryParamsRequest, com.initia.mstaking.v1.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.initia.mstaking.v1.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
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
     * </pre>
     */
    default void validators(com.initia.mstaking.v1.QueryProto.QueryValidatorsRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryValidatorsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValidatorsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Validator queries validator info for given validator address.
     * </pre>
     */
    default void validator(com.initia.mstaking.v1.QueryProto.QueryValidatorRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryValidatorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValidatorMethod(), responseObserver);
    }

    /**
     * <pre>
     * ValidatorDelegations queries delegate info for given validator.
     * </pre>
     */
    default void validatorDelegations(com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValidatorDelegationsMethod(), responseObserver);
    }

    /**
     * <pre>
     * ValidatorUnbondingDelegations queries unbonding delegations of a validator.
     * </pre>
     */
    default void validatorUnbondingDelegations(com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValidatorUnbondingDelegationsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Delegation queries delegate info for given validator delegator pair.
     * </pre>
     */
    default void delegation(com.initia.mstaking.v1.QueryProto.QueryDelegationRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryDelegationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDelegationMethod(), responseObserver);
    }

    /**
     * <pre>
     * UnbondingDelegation queries unbonding info for given validator delegator
     * pair.
     * </pre>
     */
    default void unbondingDelegation(com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUnbondingDelegationMethod(), responseObserver);
    }

    /**
     * <pre>
     * DelegatorDelegations queries all delegations of a given delegator address.
     * </pre>
     */
    default void delegatorDelegations(com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDelegatorDelegationsMethod(), responseObserver);
    }

    /**
     * <pre>
     * DelegatorUnbondingDelegations queries all unbonding delegations of a given
     * delegator address.
     * </pre>
     */
    default void delegatorUnbondingDelegations(com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDelegatorUnbondingDelegationsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Redelegations queries redelegations of given address.
     * </pre>
     */
    default void redelegations(com.initia.mstaking.v1.QueryProto.QueryRedelegationsRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryRedelegationsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRedelegationsMethod(), responseObserver);
    }

    /**
     * <pre>
     * DelegatorValidators queries all validators info for given delegator
     * address.
     * </pre>
     */
    default void delegatorValidators(com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDelegatorValidatorsMethod(), responseObserver);
    }

    /**
     * <pre>
     * DelegatorValidator queries validator info for given delegator validator
     * pair.
     * </pre>
     */
    default void delegatorValidator(com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDelegatorValidatorMethod(), responseObserver);
    }

    /**
     * <pre>
     * Pool queries the pool info.
     * </pre>
     */
    default void pool(com.initia.mstaking.v1.QueryProto.QueryPoolRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryPoolResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPoolMethod(), responseObserver);
    }

    /**
     * <pre>
     * Parameters queries the staking parameters.
     * </pre>
     */
    default void params(com.initia.mstaking.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
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
     * </pre>
     */
    public void validators(com.initia.mstaking.v1.QueryProto.QueryValidatorsRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryValidatorsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValidatorsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Validator queries validator info for given validator address.
     * </pre>
     */
    public void validator(com.initia.mstaking.v1.QueryProto.QueryValidatorRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryValidatorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValidatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ValidatorDelegations queries delegate info for given validator.
     * </pre>
     */
    public void validatorDelegations(com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValidatorDelegationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ValidatorUnbondingDelegations queries unbonding delegations of a validator.
     * </pre>
     */
    public void validatorUnbondingDelegations(com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValidatorUnbondingDelegationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Delegation queries delegate info for given validator delegator pair.
     * </pre>
     */
    public void delegation(com.initia.mstaking.v1.QueryProto.QueryDelegationRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryDelegationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDelegationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UnbondingDelegation queries unbonding info for given validator delegator
     * pair.
     * </pre>
     */
    public void unbondingDelegation(com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUnbondingDelegationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DelegatorDelegations queries all delegations of a given delegator address.
     * </pre>
     */
    public void delegatorDelegations(com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDelegatorDelegationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DelegatorUnbondingDelegations queries all unbonding delegations of a given
     * delegator address.
     * </pre>
     */
    public void delegatorUnbondingDelegations(com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDelegatorUnbondingDelegationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Redelegations queries redelegations of given address.
     * </pre>
     */
    public void redelegations(com.initia.mstaking.v1.QueryProto.QueryRedelegationsRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryRedelegationsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRedelegationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DelegatorValidators queries all validators info for given delegator
     * address.
     * </pre>
     */
    public void delegatorValidators(com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDelegatorValidatorsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DelegatorValidator queries validator info for given delegator validator
     * pair.
     * </pre>
     */
    public void delegatorValidator(com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDelegatorValidatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Pool queries the pool info.
     * </pre>
     */
    public void pool(com.initia.mstaking.v1.QueryProto.QueryPoolRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryPoolResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Parameters queries the staking parameters.
     * </pre>
     */
    public void params(com.initia.mstaking.v1.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
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
     * </pre>
     */
    public com.initia.mstaking.v1.QueryProto.QueryValidatorsResponse validators(com.initia.mstaking.v1.QueryProto.QueryValidatorsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValidatorsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Validator queries validator info for given validator address.
     * </pre>
     */
    public com.initia.mstaking.v1.QueryProto.QueryValidatorResponse validator(com.initia.mstaking.v1.QueryProto.QueryValidatorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValidatorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ValidatorDelegations queries delegate info for given validator.
     * </pre>
     */
    public com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsResponse validatorDelegations(com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValidatorDelegationsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ValidatorUnbondingDelegations queries unbonding delegations of a validator.
     * </pre>
     */
    public com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsResponse validatorUnbondingDelegations(com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValidatorUnbondingDelegationsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Delegation queries delegate info for given validator delegator pair.
     * </pre>
     */
    public com.initia.mstaking.v1.QueryProto.QueryDelegationResponse delegation(com.initia.mstaking.v1.QueryProto.QueryDelegationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDelegationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UnbondingDelegation queries unbonding info for given validator delegator
     * pair.
     * </pre>
     */
    public com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationResponse unbondingDelegation(com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUnbondingDelegationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DelegatorDelegations queries all delegations of a given delegator address.
     * </pre>
     */
    public com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsResponse delegatorDelegations(com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDelegatorDelegationsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DelegatorUnbondingDelegations queries all unbonding delegations of a given
     * delegator address.
     * </pre>
     */
    public com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsResponse delegatorUnbondingDelegations(com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDelegatorUnbondingDelegationsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Redelegations queries redelegations of given address.
     * </pre>
     */
    public com.initia.mstaking.v1.QueryProto.QueryRedelegationsResponse redelegations(com.initia.mstaking.v1.QueryProto.QueryRedelegationsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRedelegationsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DelegatorValidators queries all validators info for given delegator
     * address.
     * </pre>
     */
    public com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsResponse delegatorValidators(com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDelegatorValidatorsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DelegatorValidator queries validator info for given delegator validator
     * pair.
     * </pre>
     */
    public com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorResponse delegatorValidator(com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDelegatorValidatorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Pool queries the pool info.
     * </pre>
     */
    public com.initia.mstaking.v1.QueryProto.QueryPoolResponse pool(com.initia.mstaking.v1.QueryProto.QueryPoolRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPoolMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Parameters queries the staking parameters.
     * </pre>
     */
    public com.initia.mstaking.v1.QueryProto.QueryParamsResponse params(com.initia.mstaking.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
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
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.mstaking.v1.QueryProto.QueryValidatorsResponse> validators(
        com.initia.mstaking.v1.QueryProto.QueryValidatorsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValidatorsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Validator queries validator info for given validator address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.mstaking.v1.QueryProto.QueryValidatorResponse> validator(
        com.initia.mstaking.v1.QueryProto.QueryValidatorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValidatorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ValidatorDelegations queries delegate info for given validator.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsResponse> validatorDelegations(
        com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValidatorDelegationsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ValidatorUnbondingDelegations queries unbonding delegations of a validator.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsResponse> validatorUnbondingDelegations(
        com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValidatorUnbondingDelegationsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Delegation queries delegate info for given validator delegator pair.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.mstaking.v1.QueryProto.QueryDelegationResponse> delegation(
        com.initia.mstaking.v1.QueryProto.QueryDelegationRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDelegationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UnbondingDelegation queries unbonding info for given validator delegator
     * pair.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationResponse> unbondingDelegation(
        com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUnbondingDelegationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DelegatorDelegations queries all delegations of a given delegator address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsResponse> delegatorDelegations(
        com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDelegatorDelegationsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DelegatorUnbondingDelegations queries all unbonding delegations of a given
     * delegator address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsResponse> delegatorUnbondingDelegations(
        com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDelegatorUnbondingDelegationsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Redelegations queries redelegations of given address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.mstaking.v1.QueryProto.QueryRedelegationsResponse> redelegations(
        com.initia.mstaking.v1.QueryProto.QueryRedelegationsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRedelegationsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DelegatorValidators queries all validators info for given delegator
     * address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsResponse> delegatorValidators(
        com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDelegatorValidatorsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DelegatorValidator queries validator info for given delegator validator
     * pair.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorResponse> delegatorValidator(
        com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDelegatorValidatorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Pool queries the pool info.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.mstaking.v1.QueryProto.QueryPoolResponse> pool(
        com.initia.mstaking.v1.QueryProto.QueryPoolRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPoolMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Parameters queries the staking parameters.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.initia.mstaking.v1.QueryProto.QueryParamsResponse> params(
        com.initia.mstaking.v1.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
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
  private static final int METHODID_POOL = 11;
  private static final int METHODID_PARAMS = 12;

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
          serviceImpl.validators((com.initia.mstaking.v1.QueryProto.QueryValidatorsRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryValidatorsResponse>) responseObserver);
          break;
        case METHODID_VALIDATOR:
          serviceImpl.validator((com.initia.mstaking.v1.QueryProto.QueryValidatorRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryValidatorResponse>) responseObserver);
          break;
        case METHODID_VALIDATOR_DELEGATIONS:
          serviceImpl.validatorDelegations((com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsResponse>) responseObserver);
          break;
        case METHODID_VALIDATOR_UNBONDING_DELEGATIONS:
          serviceImpl.validatorUnbondingDelegations((com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsResponse>) responseObserver);
          break;
        case METHODID_DELEGATION:
          serviceImpl.delegation((com.initia.mstaking.v1.QueryProto.QueryDelegationRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryDelegationResponse>) responseObserver);
          break;
        case METHODID_UNBONDING_DELEGATION:
          serviceImpl.unbondingDelegation((com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationResponse>) responseObserver);
          break;
        case METHODID_DELEGATOR_DELEGATIONS:
          serviceImpl.delegatorDelegations((com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsResponse>) responseObserver);
          break;
        case METHODID_DELEGATOR_UNBONDING_DELEGATIONS:
          serviceImpl.delegatorUnbondingDelegations((com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsResponse>) responseObserver);
          break;
        case METHODID_REDELEGATIONS:
          serviceImpl.redelegations((com.initia.mstaking.v1.QueryProto.QueryRedelegationsRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryRedelegationsResponse>) responseObserver);
          break;
        case METHODID_DELEGATOR_VALIDATORS:
          serviceImpl.delegatorValidators((com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsResponse>) responseObserver);
          break;
        case METHODID_DELEGATOR_VALIDATOR:
          serviceImpl.delegatorValidator((com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorResponse>) responseObserver);
          break;
        case METHODID_POOL:
          serviceImpl.pool((com.initia.mstaking.v1.QueryProto.QueryPoolRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryPoolResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((com.initia.mstaking.v1.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.initia.mstaking.v1.QueryProto.QueryParamsResponse>) responseObserver);
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
              com.initia.mstaking.v1.QueryProto.QueryValidatorsRequest,
              com.initia.mstaking.v1.QueryProto.QueryValidatorsResponse>(
                service, METHODID_VALIDATORS)))
        .addMethod(
          getValidatorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.mstaking.v1.QueryProto.QueryValidatorRequest,
              com.initia.mstaking.v1.QueryProto.QueryValidatorResponse>(
                service, METHODID_VALIDATOR)))
        .addMethod(
          getValidatorDelegationsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsRequest,
              com.initia.mstaking.v1.QueryProto.QueryValidatorDelegationsResponse>(
                service, METHODID_VALIDATOR_DELEGATIONS)))
        .addMethod(
          getValidatorUnbondingDelegationsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsRequest,
              com.initia.mstaking.v1.QueryProto.QueryValidatorUnbondingDelegationsResponse>(
                service, METHODID_VALIDATOR_UNBONDING_DELEGATIONS)))
        .addMethod(
          getDelegationMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.mstaking.v1.QueryProto.QueryDelegationRequest,
              com.initia.mstaking.v1.QueryProto.QueryDelegationResponse>(
                service, METHODID_DELEGATION)))
        .addMethod(
          getUnbondingDelegationMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationRequest,
              com.initia.mstaking.v1.QueryProto.QueryUnbondingDelegationResponse>(
                service, METHODID_UNBONDING_DELEGATION)))
        .addMethod(
          getDelegatorDelegationsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsRequest,
              com.initia.mstaking.v1.QueryProto.QueryDelegatorDelegationsResponse>(
                service, METHODID_DELEGATOR_DELEGATIONS)))
        .addMethod(
          getDelegatorUnbondingDelegationsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsRequest,
              com.initia.mstaking.v1.QueryProto.QueryDelegatorUnbondingDelegationsResponse>(
                service, METHODID_DELEGATOR_UNBONDING_DELEGATIONS)))
        .addMethod(
          getRedelegationsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.mstaking.v1.QueryProto.QueryRedelegationsRequest,
              com.initia.mstaking.v1.QueryProto.QueryRedelegationsResponse>(
                service, METHODID_REDELEGATIONS)))
        .addMethod(
          getDelegatorValidatorsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsRequest,
              com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorsResponse>(
                service, METHODID_DELEGATOR_VALIDATORS)))
        .addMethod(
          getDelegatorValidatorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorRequest,
              com.initia.mstaking.v1.QueryProto.QueryDelegatorValidatorResponse>(
                service, METHODID_DELEGATOR_VALIDATOR)))
        .addMethod(
          getPoolMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.mstaking.v1.QueryProto.QueryPoolRequest,
              com.initia.mstaking.v1.QueryProto.QueryPoolResponse>(
                service, METHODID_POOL)))
        .addMethod(
          getParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.initia.mstaking.v1.QueryProto.QueryParamsRequest,
              com.initia.mstaking.v1.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.initia.mstaking.v1.QueryProto.getDescriptor();
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
              .addMethod(getPoolMethod())
              .addMethod(getParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
