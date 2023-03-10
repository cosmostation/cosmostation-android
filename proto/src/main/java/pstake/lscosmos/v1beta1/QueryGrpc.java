package pstake.lscosmos.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: pstake/lscosmos/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "pstake.lscosmos.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsRequest.class,
      responseType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsResponse> getHostChainParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HostChainParams",
      requestType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsRequest.class,
      responseType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsResponse> getHostChainParamsMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsResponse> getHostChainParamsMethod;
    if ((getHostChainParamsMethod = QueryGrpc.getHostChainParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getHostChainParamsMethod = QueryGrpc.getHostChainParamsMethod) == null) {
          QueryGrpc.getHostChainParamsMethod = getHostChainParamsMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HostChainParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("HostChainParams"))
              .build();
        }
      }
    }
    return getHostChainParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateResponse> getDelegationStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelegationState",
      requestType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateRequest.class,
      responseType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateResponse> getDelegationStateMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateResponse> getDelegationStateMethod;
    if ((getDelegationStateMethod = QueryGrpc.getDelegationStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDelegationStateMethod = QueryGrpc.getDelegationStateMethod) == null) {
          QueryGrpc.getDelegationStateMethod = getDelegationStateMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelegationState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DelegationState"))
              .build();
        }
      }
    }
    return getDelegationStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsResponse> getAllowListedValidatorsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllowListedValidators",
      requestType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsRequest.class,
      responseType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsResponse> getAllowListedValidatorsMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsResponse> getAllowListedValidatorsMethod;
    if ((getAllowListedValidatorsMethod = QueryGrpc.getAllowListedValidatorsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAllowListedValidatorsMethod = QueryGrpc.getAllowListedValidatorsMethod) == null) {
          QueryGrpc.getAllowListedValidatorsMethod = getAllowListedValidatorsMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AllowListedValidators"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AllowListedValidators"))
              .build();
        }
      }
    }
    return getAllowListedValidatorsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueResponse> getCValueMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CValue",
      requestType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueRequest.class,
      responseType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueResponse> getCValueMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueResponse> getCValueMethod;
    if ((getCValueMethod = QueryGrpc.getCValueMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCValueMethod = QueryGrpc.getCValueMethod) == null) {
          QueryGrpc.getCValueMethod = getCValueMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CValue"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CValue"))
              .build();
        }
      }
    }
    return getCValueMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateResponse> getModuleStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ModuleState",
      requestType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateRequest.class,
      responseType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateResponse> getModuleStateMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateResponse> getModuleStateMethod;
    if ((getModuleStateMethod = QueryGrpc.getModuleStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getModuleStateMethod = QueryGrpc.getModuleStateMethod) == null) {
          QueryGrpc.getModuleStateMethod = getModuleStateMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ModuleState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ModuleState"))
              .build();
        }
      }
    }
    return getModuleStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreResponse> getIBCTransientStoreMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "IBCTransientStore",
      requestType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreRequest.class,
      responseType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreResponse> getIBCTransientStoreMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreResponse> getIBCTransientStoreMethod;
    if ((getIBCTransientStoreMethod = QueryGrpc.getIBCTransientStoreMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getIBCTransientStoreMethod = QueryGrpc.getIBCTransientStoreMethod) == null) {
          QueryGrpc.getIBCTransientStoreMethod = getIBCTransientStoreMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IBCTransientStore"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("IBCTransientStore"))
              .build();
        }
      }
    }
    return getIBCTransientStoreMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedResponse> getUnclaimedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Unclaimed",
      requestType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedRequest.class,
      responseType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedResponse> getUnclaimedMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedResponse> getUnclaimedMethod;
    if ((getUnclaimedMethod = QueryGrpc.getUnclaimedMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUnclaimedMethod = QueryGrpc.getUnclaimedMethod) == null) {
          QueryGrpc.getUnclaimedMethod = getUnclaimedMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Unclaimed"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Unclaimed"))
              .build();
        }
      }
    }
    return getUnclaimedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsResponse> getFailedUnbondingsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FailedUnbondings",
      requestType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsRequest.class,
      responseType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsResponse> getFailedUnbondingsMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsResponse> getFailedUnbondingsMethod;
    if ((getFailedUnbondingsMethod = QueryGrpc.getFailedUnbondingsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getFailedUnbondingsMethod = QueryGrpc.getFailedUnbondingsMethod) == null) {
          QueryGrpc.getFailedUnbondingsMethod = getFailedUnbondingsMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FailedUnbondings"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("FailedUnbondings"))
              .build();
        }
      }
    }
    return getFailedUnbondingsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsResponse> getPendingUnbondingsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PendingUnbondings",
      requestType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsRequest.class,
      responseType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsResponse> getPendingUnbondingsMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsResponse> getPendingUnbondingsMethod;
    if ((getPendingUnbondingsMethod = QueryGrpc.getPendingUnbondingsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPendingUnbondingsMethod = QueryGrpc.getPendingUnbondingsMethod) == null) {
          QueryGrpc.getPendingUnbondingsMethod = getPendingUnbondingsMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PendingUnbondings"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PendingUnbondings"))
              .build();
        }
      }
    }
    return getPendingUnbondingsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueResponse> getUnbondingEpochCValueMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnbondingEpochCValue",
      requestType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueRequest.class,
      responseType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueResponse> getUnbondingEpochCValueMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueResponse> getUnbondingEpochCValueMethod;
    if ((getUnbondingEpochCValueMethod = QueryGrpc.getUnbondingEpochCValueMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUnbondingEpochCValueMethod = QueryGrpc.getUnbondingEpochCValueMethod) == null) {
          QueryGrpc.getUnbondingEpochCValueMethod = getUnbondingEpochCValueMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnbondingEpochCValue"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UnbondingEpochCValue"))
              .build();
        }
      }
    }
    return getUnbondingEpochCValueMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationResponse> getHostAccountUndelegationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HostAccountUndelegation",
      requestType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationRequest.class,
      responseType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationResponse> getHostAccountUndelegationMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationResponse> getHostAccountUndelegationMethod;
    if ((getHostAccountUndelegationMethod = QueryGrpc.getHostAccountUndelegationMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getHostAccountUndelegationMethod = QueryGrpc.getHostAccountUndelegationMethod) == null) {
          QueryGrpc.getHostAccountUndelegationMethod = getHostAccountUndelegationMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HostAccountUndelegation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("HostAccountUndelegation"))
              .build();
        }
      }
    }
    return getHostAccountUndelegationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryResponse> getDelegatorUnbondingEpochEntryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelegatorUnbondingEpochEntry",
      requestType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryRequest.class,
      responseType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryResponse> getDelegatorUnbondingEpochEntryMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryResponse> getDelegatorUnbondingEpochEntryMethod;
    if ((getDelegatorUnbondingEpochEntryMethod = QueryGrpc.getDelegatorUnbondingEpochEntryMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDelegatorUnbondingEpochEntryMethod = QueryGrpc.getDelegatorUnbondingEpochEntryMethod) == null) {
          QueryGrpc.getDelegatorUnbondingEpochEntryMethod = getDelegatorUnbondingEpochEntryMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelegatorUnbondingEpochEntry"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DelegatorUnbondingEpochEntry"))
              .build();
        }
      }
    }
    return getDelegatorUnbondingEpochEntryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsResponse> getHostAccountsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HostAccounts",
      requestType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsRequest.class,
      responseType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsResponse> getHostAccountsMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsResponse> getHostAccountsMethod;
    if ((getHostAccountsMethod = QueryGrpc.getHostAccountsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getHostAccountsMethod = QueryGrpc.getHostAccountsMethod) == null) {
          QueryGrpc.getHostAccountsMethod = getHostAccountsMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HostAccounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("HostAccounts"))
              .build();
        }
      }
    }
    return getHostAccountsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountResponse> getDepositModuleAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DepositModuleAccount",
      requestType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountRequest.class,
      responseType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountResponse> getDepositModuleAccountMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountResponse> getDepositModuleAccountMethod;
    if ((getDepositModuleAccountMethod = QueryGrpc.getDepositModuleAccountMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDepositModuleAccountMethod = QueryGrpc.getDepositModuleAccountMethod) == null) {
          QueryGrpc.getDepositModuleAccountMethod = getDepositModuleAccountMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DepositModuleAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DepositModuleAccount"))
              .build();
        }
      }
    }
    return getDepositModuleAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesResponse> getDelegatorUnbondingEpochEntriesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelegatorUnbondingEpochEntries",
      requestType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesRequest.class,
      responseType = pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesRequest,
      pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesResponse> getDelegatorUnbondingEpochEntriesMethod() {
    io.grpc.MethodDescriptor<pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesResponse> getDelegatorUnbondingEpochEntriesMethod;
    if ((getDelegatorUnbondingEpochEntriesMethod = QueryGrpc.getDelegatorUnbondingEpochEntriesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDelegatorUnbondingEpochEntriesMethod = QueryGrpc.getDelegatorUnbondingEpochEntriesMethod) == null) {
          QueryGrpc.getDelegatorUnbondingEpochEntriesMethod = getDelegatorUnbondingEpochEntriesMethod =
              io.grpc.MethodDescriptor.<pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesRequest, pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelegatorUnbondingEpochEntries"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DelegatorUnbondingEpochEntries"))
              .build();
        }
      }
    }
    return getDelegatorUnbondingEpochEntriesMethod;
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
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Parameters queries the parameters of the module.
     * </pre>
     */
    public void params(pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     */
    public void hostChainParams(pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHostChainParamsMethod(), responseObserver);
    }

    /**
     */
    public void delegationState(pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDelegationStateMethod(), responseObserver);
    }

    /**
     */
    public void allowListedValidators(pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAllowListedValidatorsMethod(), responseObserver);
    }

    /**
     */
    public void cValue(pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCValueMethod(), responseObserver);
    }

    /**
     */
    public void moduleState(pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getModuleStateMethod(), responseObserver);
    }

    /**
     */
    public void iBCTransientStore(pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getIBCTransientStoreMethod(), responseObserver);
    }

    /**
     */
    public void unclaimed(pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnclaimedMethod(), responseObserver);
    }

    /**
     */
    public void failedUnbondings(pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFailedUnbondingsMethod(), responseObserver);
    }

    /**
     */
    public void pendingUnbondings(pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPendingUnbondingsMethod(), responseObserver);
    }

    /**
     */
    public void unbondingEpochCValue(pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnbondingEpochCValueMethod(), responseObserver);
    }

    /**
     */
    public void hostAccountUndelegation(pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHostAccountUndelegationMethod(), responseObserver);
    }

    /**
     */
    public void delegatorUnbondingEpochEntry(pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDelegatorUnbondingEpochEntryMethod(), responseObserver);
    }

    /**
     */
    public void hostAccounts(pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHostAccountsMethod(), responseObserver);
    }

    /**
     */
    public void depositModuleAccount(pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositModuleAccountMethod(), responseObserver);
    }

    /**
     */
    public void delegatorUnbondingEpochEntries(pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDelegatorUnbondingEpochEntriesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsRequest,
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getHostChainParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsRequest,
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsResponse>(
                  this, METHODID_HOST_CHAIN_PARAMS)))
          .addMethod(
            getDelegationStateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateRequest,
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateResponse>(
                  this, METHODID_DELEGATION_STATE)))
          .addMethod(
            getAllowListedValidatorsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsRequest,
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsResponse>(
                  this, METHODID_ALLOW_LISTED_VALIDATORS)))
          .addMethod(
            getCValueMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueRequest,
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueResponse>(
                  this, METHODID_CVALUE)))
          .addMethod(
            getModuleStateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateRequest,
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateResponse>(
                  this, METHODID_MODULE_STATE)))
          .addMethod(
            getIBCTransientStoreMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreRequest,
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreResponse>(
                  this, METHODID_IBCTRANSIENT_STORE)))
          .addMethod(
            getUnclaimedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedRequest,
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedResponse>(
                  this, METHODID_UNCLAIMED)))
          .addMethod(
            getFailedUnbondingsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsRequest,
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsResponse>(
                  this, METHODID_FAILED_UNBONDINGS)))
          .addMethod(
            getPendingUnbondingsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsRequest,
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsResponse>(
                  this, METHODID_PENDING_UNBONDINGS)))
          .addMethod(
            getUnbondingEpochCValueMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueRequest,
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueResponse>(
                  this, METHODID_UNBONDING_EPOCH_CVALUE)))
          .addMethod(
            getHostAccountUndelegationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationRequest,
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationResponse>(
                  this, METHODID_HOST_ACCOUNT_UNDELEGATION)))
          .addMethod(
            getDelegatorUnbondingEpochEntryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryRequest,
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryResponse>(
                  this, METHODID_DELEGATOR_UNBONDING_EPOCH_ENTRY)))
          .addMethod(
            getHostAccountsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsRequest,
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsResponse>(
                  this, METHODID_HOST_ACCOUNTS)))
          .addMethod(
            getDepositModuleAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountRequest,
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountResponse>(
                  this, METHODID_DEPOSIT_MODULE_ACCOUNT)))
          .addMethod(
            getDelegatorUnbondingEpochEntriesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesRequest,
                pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesResponse>(
                  this, METHODID_DELEGATOR_UNBONDING_EPOCH_ENTRIES)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
   * </pre>
   */
  public static final class QueryStub extends io.grpc.stub.AbstractAsyncStub<QueryStub> {
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
    public void params(pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void hostChainParams(pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHostChainParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delegationState(pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDelegationStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void allowListedValidators(pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAllowListedValidatorsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void cValue(pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCValueMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void moduleState(pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getModuleStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void iBCTransientStore(pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIBCTransientStoreMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void unclaimed(pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnclaimedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void failedUnbondings(pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFailedUnbondingsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void pendingUnbondings(pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPendingUnbondingsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void unbondingEpochCValue(pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnbondingEpochCValueMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void hostAccountUndelegation(pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHostAccountUndelegationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delegatorUnbondingEpochEntry(pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDelegatorUnbondingEpochEntryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void hostAccounts(pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHostAccountsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void depositModuleAccount(pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositModuleAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delegatorUnbondingEpochEntries(pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesRequest request,
        io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDelegatorUnbondingEpochEntriesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
   * </pre>
   */
  public static final class QueryBlockingStub extends io.grpc.stub.AbstractBlockingStub<QueryBlockingStub> {
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
    public pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsResponse params(pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsResponse hostChainParams(pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getHostChainParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateResponse delegationState(pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateRequest request) {
      return blockingUnaryCall(
          getChannel(), getDelegationStateMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsResponse allowListedValidators(pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsRequest request) {
      return blockingUnaryCall(
          getChannel(), getAllowListedValidatorsMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueResponse cValue(pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueRequest request) {
      return blockingUnaryCall(
          getChannel(), getCValueMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateResponse moduleState(pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateRequest request) {
      return blockingUnaryCall(
          getChannel(), getModuleStateMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreResponse iBCTransientStore(pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreRequest request) {
      return blockingUnaryCall(
          getChannel(), getIBCTransientStoreMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedResponse unclaimed(pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedRequest request) {
      return blockingUnaryCall(
          getChannel(), getUnclaimedMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsResponse failedUnbondings(pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsRequest request) {
      return blockingUnaryCall(
          getChannel(), getFailedUnbondingsMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsResponse pendingUnbondings(pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPendingUnbondingsMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueResponse unbondingEpochCValue(pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueRequest request) {
      return blockingUnaryCall(
          getChannel(), getUnbondingEpochCValueMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationResponse hostAccountUndelegation(pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationRequest request) {
      return blockingUnaryCall(
          getChannel(), getHostAccountUndelegationMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryResponse delegatorUnbondingEpochEntry(pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryRequest request) {
      return blockingUnaryCall(
          getChannel(), getDelegatorUnbondingEpochEntryMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsResponse hostAccounts(pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsRequest request) {
      return blockingUnaryCall(
          getChannel(), getHostAccountsMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountResponse depositModuleAccount(pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountRequest request) {
      return blockingUnaryCall(
          getChannel(), getDepositModuleAccountMethod(), getCallOptions(), request);
    }

    /**
     */
    public pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesResponse delegatorUnbondingEpochEntries(pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesRequest request) {
      return blockingUnaryCall(
          getChannel(), getDelegatorUnbondingEpochEntriesMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
   * </pre>
   */
  public static final class QueryFutureStub extends io.grpc.stub.AbstractFutureStub<QueryFutureStub> {
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
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsResponse> params(
        pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsResponse> hostChainParams(
        pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getHostChainParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateResponse> delegationState(
        pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDelegationStateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsResponse> allowListedValidators(
        pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAllowListedValidatorsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueResponse> cValue(
        pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCValueMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateResponse> moduleState(
        pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getModuleStateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreResponse> iBCTransientStore(
        pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getIBCTransientStoreMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedResponse> unclaimed(
        pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUnclaimedMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsResponse> failedUnbondings(
        pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFailedUnbondingsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsResponse> pendingUnbondings(
        pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPendingUnbondingsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueResponse> unbondingEpochCValue(
        pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUnbondingEpochCValueMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationResponse> hostAccountUndelegation(
        pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getHostAccountUndelegationMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryResponse> delegatorUnbondingEpochEntry(
        pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDelegatorUnbondingEpochEntryMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsResponse> hostAccounts(
        pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getHostAccountsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountResponse> depositModuleAccount(
        pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositModuleAccountMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesResponse> delegatorUnbondingEpochEntries(
        pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDelegatorUnbondingEpochEntriesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_HOST_CHAIN_PARAMS = 1;
  private static final int METHODID_DELEGATION_STATE = 2;
  private static final int METHODID_ALLOW_LISTED_VALIDATORS = 3;
  private static final int METHODID_CVALUE = 4;
  private static final int METHODID_MODULE_STATE = 5;
  private static final int METHODID_IBCTRANSIENT_STORE = 6;
  private static final int METHODID_UNCLAIMED = 7;
  private static final int METHODID_FAILED_UNBONDINGS = 8;
  private static final int METHODID_PENDING_UNBONDINGS = 9;
  private static final int METHODID_UNBONDING_EPOCH_CVALUE = 10;
  private static final int METHODID_HOST_ACCOUNT_UNDELEGATION = 11;
  private static final int METHODID_DELEGATOR_UNBONDING_EPOCH_ENTRY = 12;
  private static final int METHODID_HOST_ACCOUNTS = 13;
  private static final int METHODID_DEPOSIT_MODULE_ACCOUNT = 14;
  private static final int METHODID_DELEGATOR_UNBONDING_EPOCH_ENTRIES = 15;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final QueryImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(QueryImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PARAMS:
          serviceImpl.params((pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_HOST_CHAIN_PARAMS:
          serviceImpl.hostChainParams((pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsRequest) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostChainParamsResponse>) responseObserver);
          break;
        case METHODID_DELEGATION_STATE:
          serviceImpl.delegationState((pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateRequest) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegationStateResponse>) responseObserver);
          break;
        case METHODID_ALLOW_LISTED_VALIDATORS:
          serviceImpl.allowListedValidators((pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsRequest) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllowListedValidatorsResponse>) responseObserver);
          break;
        case METHODID_CVALUE:
          serviceImpl.cValue((pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueRequest) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryCValueResponse>) responseObserver);
          break;
        case METHODID_MODULE_STATE:
          serviceImpl.moduleState((pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateRequest) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryModuleStateResponse>) responseObserver);
          break;
        case METHODID_IBCTRANSIENT_STORE:
          serviceImpl.iBCTransientStore((pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreRequest) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryIBCTransientStoreResponse>) responseObserver);
          break;
        case METHODID_UNCLAIMED:
          serviceImpl.unclaimed((pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedRequest) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnclaimedResponse>) responseObserver);
          break;
        case METHODID_FAILED_UNBONDINGS:
          serviceImpl.failedUnbondings((pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsRequest) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryFailedUnbondingsResponse>) responseObserver);
          break;
        case METHODID_PENDING_UNBONDINGS:
          serviceImpl.pendingUnbondings((pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsRequest) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryPendingUnbondingsResponse>) responseObserver);
          break;
        case METHODID_UNBONDING_EPOCH_CVALUE:
          serviceImpl.unbondingEpochCValue((pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueRequest) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryUnbondingEpochCValueResponse>) responseObserver);
          break;
        case METHODID_HOST_ACCOUNT_UNDELEGATION:
          serviceImpl.hostAccountUndelegation((pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationRequest) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountUndelegationResponse>) responseObserver);
          break;
        case METHODID_DELEGATOR_UNBONDING_EPOCH_ENTRY:
          serviceImpl.delegatorUnbondingEpochEntry((pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryRequest) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDelegatorUnbondingEpochEntryResponse>) responseObserver);
          break;
        case METHODID_HOST_ACCOUNTS:
          serviceImpl.hostAccounts((pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsRequest) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryHostAccountsResponse>) responseObserver);
          break;
        case METHODID_DEPOSIT_MODULE_ACCOUNT:
          serviceImpl.depositModuleAccount((pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountRequest) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryDepositModuleAccountResponse>) responseObserver);
          break;
        case METHODID_DELEGATOR_UNBONDING_EPOCH_ENTRIES:
          serviceImpl.delegatorUnbondingEpochEntries((pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesRequest) request,
              (io.grpc.stub.StreamObserver<pstake.lscosmos.v1beta1.QueryOuterClass.QueryAllDelegatorUnbondingEpochEntriesResponse>) responseObserver);
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

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return pstake.lscosmos.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getHostChainParamsMethod())
              .addMethod(getDelegationStateMethod())
              .addMethod(getAllowListedValidatorsMethod())
              .addMethod(getCValueMethod())
              .addMethod(getModuleStateMethod())
              .addMethod(getIBCTransientStoreMethod())
              .addMethod(getUnclaimedMethod())
              .addMethod(getFailedUnbondingsMethod())
              .addMethod(getPendingUnbondingsMethod())
              .addMethod(getUnbondingEpochCValueMethod())
              .addMethod(getHostAccountUndelegationMethod())
              .addMethod(getDelegatorUnbondingEpochEntryMethod())
              .addMethod(getHostAccountsMethod())
              .addMethod(getDepositModuleAccountMethod())
              .addMethod(getDelegatorUnbondingEpochEntriesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
