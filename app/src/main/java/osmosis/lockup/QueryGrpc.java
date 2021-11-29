package osmosis.lockup;

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
    comments = "Source: osmosis/lockup/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "osmosis.lockup.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.ModuleBalanceRequest,
      osmosis.lockup.QueryOuterClass.ModuleBalanceResponse> getModuleBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ModuleBalance",
      requestType = osmosis.lockup.QueryOuterClass.ModuleBalanceRequest.class,
      responseType = osmosis.lockup.QueryOuterClass.ModuleBalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.ModuleBalanceRequest,
      osmosis.lockup.QueryOuterClass.ModuleBalanceResponse> getModuleBalanceMethod() {
    io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.ModuleBalanceRequest, osmosis.lockup.QueryOuterClass.ModuleBalanceResponse> getModuleBalanceMethod;
    if ((getModuleBalanceMethod = QueryGrpc.getModuleBalanceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getModuleBalanceMethod = QueryGrpc.getModuleBalanceMethod) == null) {
          QueryGrpc.getModuleBalanceMethod = getModuleBalanceMethod =
              io.grpc.MethodDescriptor.<osmosis.lockup.QueryOuterClass.ModuleBalanceRequest, osmosis.lockup.QueryOuterClass.ModuleBalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ModuleBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.ModuleBalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.ModuleBalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ModuleBalance"))
              .build();
        }
      }
    }
    return getModuleBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.ModuleLockedAmountRequest,
      osmosis.lockup.QueryOuterClass.ModuleLockedAmountResponse> getModuleLockedAmountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ModuleLockedAmount",
      requestType = osmosis.lockup.QueryOuterClass.ModuleLockedAmountRequest.class,
      responseType = osmosis.lockup.QueryOuterClass.ModuleLockedAmountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.ModuleLockedAmountRequest,
      osmosis.lockup.QueryOuterClass.ModuleLockedAmountResponse> getModuleLockedAmountMethod() {
    io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.ModuleLockedAmountRequest, osmosis.lockup.QueryOuterClass.ModuleLockedAmountResponse> getModuleLockedAmountMethod;
    if ((getModuleLockedAmountMethod = QueryGrpc.getModuleLockedAmountMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getModuleLockedAmountMethod = QueryGrpc.getModuleLockedAmountMethod) == null) {
          QueryGrpc.getModuleLockedAmountMethod = getModuleLockedAmountMethod =
              io.grpc.MethodDescriptor.<osmosis.lockup.QueryOuterClass.ModuleLockedAmountRequest, osmosis.lockup.QueryOuterClass.ModuleLockedAmountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ModuleLockedAmount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.ModuleLockedAmountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.ModuleLockedAmountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ModuleLockedAmount"))
              .build();
        }
      }
    }
    return getModuleLockedAmountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsRequest,
      osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsResponse> getAccountUnlockableCoinsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AccountUnlockableCoins",
      requestType = osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsRequest.class,
      responseType = osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsRequest,
      osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsResponse> getAccountUnlockableCoinsMethod() {
    io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsRequest, osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsResponse> getAccountUnlockableCoinsMethod;
    if ((getAccountUnlockableCoinsMethod = QueryGrpc.getAccountUnlockableCoinsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountUnlockableCoinsMethod = QueryGrpc.getAccountUnlockableCoinsMethod) == null) {
          QueryGrpc.getAccountUnlockableCoinsMethod = getAccountUnlockableCoinsMethod =
              io.grpc.MethodDescriptor.<osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsRequest, osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AccountUnlockableCoins"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AccountUnlockableCoins"))
              .build();
        }
      }
    }
    return getAccountUnlockableCoinsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsRequest,
      osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsResponse> getAccountUnlockingCoinsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AccountUnlockingCoins",
      requestType = osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsRequest.class,
      responseType = osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsRequest,
      osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsResponse> getAccountUnlockingCoinsMethod() {
    io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsRequest, osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsResponse> getAccountUnlockingCoinsMethod;
    if ((getAccountUnlockingCoinsMethod = QueryGrpc.getAccountUnlockingCoinsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountUnlockingCoinsMethod = QueryGrpc.getAccountUnlockingCoinsMethod) == null) {
          QueryGrpc.getAccountUnlockingCoinsMethod = getAccountUnlockingCoinsMethod =
              io.grpc.MethodDescriptor.<osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsRequest, osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AccountUnlockingCoins"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AccountUnlockingCoins"))
              .build();
        }
      }
    }
    return getAccountUnlockingCoinsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountLockedCoinsRequest,
      osmosis.lockup.QueryOuterClass.AccountLockedCoinsResponse> getAccountLockedCoinsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AccountLockedCoins",
      requestType = osmosis.lockup.QueryOuterClass.AccountLockedCoinsRequest.class,
      responseType = osmosis.lockup.QueryOuterClass.AccountLockedCoinsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountLockedCoinsRequest,
      osmosis.lockup.QueryOuterClass.AccountLockedCoinsResponse> getAccountLockedCoinsMethod() {
    io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountLockedCoinsRequest, osmosis.lockup.QueryOuterClass.AccountLockedCoinsResponse> getAccountLockedCoinsMethod;
    if ((getAccountLockedCoinsMethod = QueryGrpc.getAccountLockedCoinsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountLockedCoinsMethod = QueryGrpc.getAccountLockedCoinsMethod) == null) {
          QueryGrpc.getAccountLockedCoinsMethod = getAccountLockedCoinsMethod =
              io.grpc.MethodDescriptor.<osmosis.lockup.QueryOuterClass.AccountLockedCoinsRequest, osmosis.lockup.QueryOuterClass.AccountLockedCoinsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AccountLockedCoins"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.AccountLockedCoinsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.AccountLockedCoinsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AccountLockedCoins"))
              .build();
        }
      }
    }
    return getAccountLockedCoinsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeRequest,
      osmosis.lockup.QueryOuterClass.AccountLockedPastTimeResponse> getAccountLockedPastTimeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AccountLockedPastTime",
      requestType = osmosis.lockup.QueryOuterClass.AccountLockedPastTimeRequest.class,
      responseType = osmosis.lockup.QueryOuterClass.AccountLockedPastTimeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeRequest,
      osmosis.lockup.QueryOuterClass.AccountLockedPastTimeResponse> getAccountLockedPastTimeMethod() {
    io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeRequest, osmosis.lockup.QueryOuterClass.AccountLockedPastTimeResponse> getAccountLockedPastTimeMethod;
    if ((getAccountLockedPastTimeMethod = QueryGrpc.getAccountLockedPastTimeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountLockedPastTimeMethod = QueryGrpc.getAccountLockedPastTimeMethod) == null) {
          QueryGrpc.getAccountLockedPastTimeMethod = getAccountLockedPastTimeMethod =
              io.grpc.MethodDescriptor.<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeRequest, osmosis.lockup.QueryOuterClass.AccountLockedPastTimeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AccountLockedPastTime"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.AccountLockedPastTimeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.AccountLockedPastTimeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AccountLockedPastTime"))
              .build();
        }
      }
    }
    return getAccountLockedPastTimeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyRequest,
      osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyResponse> getAccountLockedPastTimeNotUnlockingOnlyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AccountLockedPastTimeNotUnlockingOnly",
      requestType = osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyRequest.class,
      responseType = osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyRequest,
      osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyResponse> getAccountLockedPastTimeNotUnlockingOnlyMethod() {
    io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyRequest, osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyResponse> getAccountLockedPastTimeNotUnlockingOnlyMethod;
    if ((getAccountLockedPastTimeNotUnlockingOnlyMethod = QueryGrpc.getAccountLockedPastTimeNotUnlockingOnlyMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountLockedPastTimeNotUnlockingOnlyMethod = QueryGrpc.getAccountLockedPastTimeNotUnlockingOnlyMethod) == null) {
          QueryGrpc.getAccountLockedPastTimeNotUnlockingOnlyMethod = getAccountLockedPastTimeNotUnlockingOnlyMethod =
              io.grpc.MethodDescriptor.<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyRequest, osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AccountLockedPastTimeNotUnlockingOnly"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AccountLockedPastTimeNotUnlockingOnly"))
              .build();
        }
      }
    }
    return getAccountLockedPastTimeNotUnlockingOnlyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeRequest,
      osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeResponse> getAccountUnlockedBeforeTimeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AccountUnlockedBeforeTime",
      requestType = osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeRequest.class,
      responseType = osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeRequest,
      osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeResponse> getAccountUnlockedBeforeTimeMethod() {
    io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeRequest, osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeResponse> getAccountUnlockedBeforeTimeMethod;
    if ((getAccountUnlockedBeforeTimeMethod = QueryGrpc.getAccountUnlockedBeforeTimeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountUnlockedBeforeTimeMethod = QueryGrpc.getAccountUnlockedBeforeTimeMethod) == null) {
          QueryGrpc.getAccountUnlockedBeforeTimeMethod = getAccountUnlockedBeforeTimeMethod =
              io.grpc.MethodDescriptor.<osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeRequest, osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AccountUnlockedBeforeTime"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AccountUnlockedBeforeTime"))
              .build();
        }
      }
    }
    return getAccountUnlockedBeforeTimeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomRequest,
      osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomResponse> getAccountLockedPastTimeDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AccountLockedPastTimeDenom",
      requestType = osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomRequest.class,
      responseType = osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomRequest,
      osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomResponse> getAccountLockedPastTimeDenomMethod() {
    io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomRequest, osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomResponse> getAccountLockedPastTimeDenomMethod;
    if ((getAccountLockedPastTimeDenomMethod = QueryGrpc.getAccountLockedPastTimeDenomMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountLockedPastTimeDenomMethod = QueryGrpc.getAccountLockedPastTimeDenomMethod) == null) {
          QueryGrpc.getAccountLockedPastTimeDenomMethod = getAccountLockedPastTimeDenomMethod =
              io.grpc.MethodDescriptor.<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomRequest, osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AccountLockedPastTimeDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AccountLockedPastTimeDenom"))
              .build();
        }
      }
    }
    return getAccountLockedPastTimeDenomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.LockedDenomRequest,
      osmosis.lockup.QueryOuterClass.LockedDenomResponse> getLockedDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LockedDenom",
      requestType = osmosis.lockup.QueryOuterClass.LockedDenomRequest.class,
      responseType = osmosis.lockup.QueryOuterClass.LockedDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.LockedDenomRequest,
      osmosis.lockup.QueryOuterClass.LockedDenomResponse> getLockedDenomMethod() {
    io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.LockedDenomRequest, osmosis.lockup.QueryOuterClass.LockedDenomResponse> getLockedDenomMethod;
    if ((getLockedDenomMethod = QueryGrpc.getLockedDenomMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLockedDenomMethod = QueryGrpc.getLockedDenomMethod) == null) {
          QueryGrpc.getLockedDenomMethod = getLockedDenomMethod =
              io.grpc.MethodDescriptor.<osmosis.lockup.QueryOuterClass.LockedDenomRequest, osmosis.lockup.QueryOuterClass.LockedDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LockedDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.LockedDenomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.LockedDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LockedDenom"))
              .build();
        }
      }
    }
    return getLockedDenomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.LockedRequest,
      osmosis.lockup.QueryOuterClass.LockedResponse> getLockedByIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LockedByID",
      requestType = osmosis.lockup.QueryOuterClass.LockedRequest.class,
      responseType = osmosis.lockup.QueryOuterClass.LockedResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.LockedRequest,
      osmosis.lockup.QueryOuterClass.LockedResponse> getLockedByIDMethod() {
    io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.LockedRequest, osmosis.lockup.QueryOuterClass.LockedResponse> getLockedByIDMethod;
    if ((getLockedByIDMethod = QueryGrpc.getLockedByIDMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLockedByIDMethod = QueryGrpc.getLockedByIDMethod) == null) {
          QueryGrpc.getLockedByIDMethod = getLockedByIDMethod =
              io.grpc.MethodDescriptor.<osmosis.lockup.QueryOuterClass.LockedRequest, osmosis.lockup.QueryOuterClass.LockedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LockedByID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.LockedRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.LockedResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LockedByID"))
              .build();
        }
      }
    }
    return getLockedByIDMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationRequest,
      osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationResponse> getAccountLockedLongerDurationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AccountLockedLongerDuration",
      requestType = osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationRequest.class,
      responseType = osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationRequest,
      osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationResponse> getAccountLockedLongerDurationMethod() {
    io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationRequest, osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationResponse> getAccountLockedLongerDurationMethod;
    if ((getAccountLockedLongerDurationMethod = QueryGrpc.getAccountLockedLongerDurationMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountLockedLongerDurationMethod = QueryGrpc.getAccountLockedLongerDurationMethod) == null) {
          QueryGrpc.getAccountLockedLongerDurationMethod = getAccountLockedLongerDurationMethod =
              io.grpc.MethodDescriptor.<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationRequest, osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AccountLockedLongerDuration"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AccountLockedLongerDuration"))
              .build();
        }
      }
    }
    return getAccountLockedLongerDurationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyRequest,
      osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyResponse> getAccountLockedLongerDurationNotUnlockingOnlyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AccountLockedLongerDurationNotUnlockingOnly",
      requestType = osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyRequest.class,
      responseType = osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyRequest,
      osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyResponse> getAccountLockedLongerDurationNotUnlockingOnlyMethod() {
    io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyRequest, osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyResponse> getAccountLockedLongerDurationNotUnlockingOnlyMethod;
    if ((getAccountLockedLongerDurationNotUnlockingOnlyMethod = QueryGrpc.getAccountLockedLongerDurationNotUnlockingOnlyMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountLockedLongerDurationNotUnlockingOnlyMethod = QueryGrpc.getAccountLockedLongerDurationNotUnlockingOnlyMethod) == null) {
          QueryGrpc.getAccountLockedLongerDurationNotUnlockingOnlyMethod = getAccountLockedLongerDurationNotUnlockingOnlyMethod =
              io.grpc.MethodDescriptor.<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyRequest, osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AccountLockedLongerDurationNotUnlockingOnly"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AccountLockedLongerDurationNotUnlockingOnly"))
              .build();
        }
      }
    }
    return getAccountLockedLongerDurationNotUnlockingOnlyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomRequest,
      osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomResponse> getAccountLockedLongerDurationDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AccountLockedLongerDurationDenom",
      requestType = osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomRequest.class,
      responseType = osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomRequest,
      osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomResponse> getAccountLockedLongerDurationDenomMethod() {
    io.grpc.MethodDescriptor<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomRequest, osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomResponse> getAccountLockedLongerDurationDenomMethod;
    if ((getAccountLockedLongerDurationDenomMethod = QueryGrpc.getAccountLockedLongerDurationDenomMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAccountLockedLongerDurationDenomMethod = QueryGrpc.getAccountLockedLongerDurationDenomMethod) == null) {
          QueryGrpc.getAccountLockedLongerDurationDenomMethod = getAccountLockedLongerDurationDenomMethod =
              io.grpc.MethodDescriptor.<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomRequest, osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AccountLockedLongerDurationDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AccountLockedLongerDurationDenom"))
              .build();
        }
      }
    }
    return getAccountLockedLongerDurationDenomMethod;
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
     * Return full balance of the module
     * </pre>
     */
    public void moduleBalance(osmosis.lockup.QueryOuterClass.ModuleBalanceRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.ModuleBalanceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getModuleBalanceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Return locked balance of the module
     * </pre>
     */
    public void moduleLockedAmount(osmosis.lockup.QueryOuterClass.ModuleLockedAmountRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.ModuleLockedAmountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getModuleLockedAmountMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns unlockable coins which are not withdrawn yet
     * </pre>
     */
    public void accountUnlockableCoins(osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAccountUnlockableCoinsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns unlocking coins
     * </pre>
     */
    public void accountUnlockingCoins(osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAccountUnlockingCoinsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Return a locked coins that can't be withdrawn
     * </pre>
     */
    public void accountLockedCoins(osmosis.lockup.QueryOuterClass.AccountLockedCoinsRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountLockedCoinsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAccountLockedCoinsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns locked records of an account with unlock time beyond timestamp
     * </pre>
     */
    public void accountLockedPastTime(osmosis.lockup.QueryOuterClass.AccountLockedPastTimeRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAccountLockedPastTimeMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns locked records of an account with unlock time beyond timestamp
     * excluding tokens started unlocking
     * </pre>
     */
    public void accountLockedPastTimeNotUnlockingOnly(osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAccountLockedPastTimeNotUnlockingOnlyMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns unlocked records with unlock time before timestamp
     * </pre>
     */
    public void accountUnlockedBeforeTime(osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAccountUnlockedBeforeTimeMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns lock records by address, timestamp, denom
     * </pre>
     */
    public void accountLockedPastTimeDenom(osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAccountLockedPastTimeDenomMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns total locked per denom with longer past given time
     * </pre>
     */
    public void lockedDenom(osmosis.lockup.QueryOuterClass.LockedDenomRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.LockedDenomResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLockedDenomMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns lock record by id
     * </pre>
     */
    public void lockedByID(osmosis.lockup.QueryOuterClass.LockedRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.LockedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLockedByIDMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns account locked records with longer duration
     * </pre>
     */
    public void accountLockedLongerDuration(osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAccountLockedLongerDurationMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns account locked records with longer duration excluding tokens
     * started unlocking
     * </pre>
     */
    public void accountLockedLongerDurationNotUnlockingOnly(osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAccountLockedLongerDurationNotUnlockingOnlyMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns account's locked records for a denom with longer duration
     * </pre>
     */
    public void accountLockedLongerDurationDenom(osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAccountLockedLongerDurationDenomMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getModuleBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.lockup.QueryOuterClass.ModuleBalanceRequest,
                osmosis.lockup.QueryOuterClass.ModuleBalanceResponse>(
                  this, METHODID_MODULE_BALANCE)))
          .addMethod(
            getModuleLockedAmountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.lockup.QueryOuterClass.ModuleLockedAmountRequest,
                osmosis.lockup.QueryOuterClass.ModuleLockedAmountResponse>(
                  this, METHODID_MODULE_LOCKED_AMOUNT)))
          .addMethod(
            getAccountUnlockableCoinsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsRequest,
                osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsResponse>(
                  this, METHODID_ACCOUNT_UNLOCKABLE_COINS)))
          .addMethod(
            getAccountUnlockingCoinsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsRequest,
                osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsResponse>(
                  this, METHODID_ACCOUNT_UNLOCKING_COINS)))
          .addMethod(
            getAccountLockedCoinsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.lockup.QueryOuterClass.AccountLockedCoinsRequest,
                osmosis.lockup.QueryOuterClass.AccountLockedCoinsResponse>(
                  this, METHODID_ACCOUNT_LOCKED_COINS)))
          .addMethod(
            getAccountLockedPastTimeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.lockup.QueryOuterClass.AccountLockedPastTimeRequest,
                osmosis.lockup.QueryOuterClass.AccountLockedPastTimeResponse>(
                  this, METHODID_ACCOUNT_LOCKED_PAST_TIME)))
          .addMethod(
            getAccountLockedPastTimeNotUnlockingOnlyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyRequest,
                osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyResponse>(
                  this, METHODID_ACCOUNT_LOCKED_PAST_TIME_NOT_UNLOCKING_ONLY)))
          .addMethod(
            getAccountUnlockedBeforeTimeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeRequest,
                osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeResponse>(
                  this, METHODID_ACCOUNT_UNLOCKED_BEFORE_TIME)))
          .addMethod(
            getAccountLockedPastTimeDenomMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomRequest,
                osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomResponse>(
                  this, METHODID_ACCOUNT_LOCKED_PAST_TIME_DENOM)))
          .addMethod(
            getLockedDenomMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.lockup.QueryOuterClass.LockedDenomRequest,
                osmosis.lockup.QueryOuterClass.LockedDenomResponse>(
                  this, METHODID_LOCKED_DENOM)))
          .addMethod(
            getLockedByIDMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.lockup.QueryOuterClass.LockedRequest,
                osmosis.lockup.QueryOuterClass.LockedResponse>(
                  this, METHODID_LOCKED_BY_ID)))
          .addMethod(
            getAccountLockedLongerDurationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationRequest,
                osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationResponse>(
                  this, METHODID_ACCOUNT_LOCKED_LONGER_DURATION)))
          .addMethod(
            getAccountLockedLongerDurationNotUnlockingOnlyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyRequest,
                osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyResponse>(
                  this, METHODID_ACCOUNT_LOCKED_LONGER_DURATION_NOT_UNLOCKING_ONLY)))
          .addMethod(
            getAccountLockedLongerDurationDenomMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomRequest,
                osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomResponse>(
                  this, METHODID_ACCOUNT_LOCKED_LONGER_DURATION_DENOM)))
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
     * Return full balance of the module
     * </pre>
     */
    public void moduleBalance(osmosis.lockup.QueryOuterClass.ModuleBalanceRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.ModuleBalanceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getModuleBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Return locked balance of the module
     * </pre>
     */
    public void moduleLockedAmount(osmosis.lockup.QueryOuterClass.ModuleLockedAmountRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.ModuleLockedAmountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getModuleLockedAmountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns unlockable coins which are not withdrawn yet
     * </pre>
     */
    public void accountUnlockableCoins(osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAccountUnlockableCoinsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns unlocking coins
     * </pre>
     */
    public void accountUnlockingCoins(osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAccountUnlockingCoinsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Return a locked coins that can't be withdrawn
     * </pre>
     */
    public void accountLockedCoins(osmosis.lockup.QueryOuterClass.AccountLockedCoinsRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountLockedCoinsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAccountLockedCoinsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns locked records of an account with unlock time beyond timestamp
     * </pre>
     */
    public void accountLockedPastTime(osmosis.lockup.QueryOuterClass.AccountLockedPastTimeRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAccountLockedPastTimeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns locked records of an account with unlock time beyond timestamp
     * excluding tokens started unlocking
     * </pre>
     */
    public void accountLockedPastTimeNotUnlockingOnly(osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAccountLockedPastTimeNotUnlockingOnlyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns unlocked records with unlock time before timestamp
     * </pre>
     */
    public void accountUnlockedBeforeTime(osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAccountUnlockedBeforeTimeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns lock records by address, timestamp, denom
     * </pre>
     */
    public void accountLockedPastTimeDenom(osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAccountLockedPastTimeDenomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns total locked per denom with longer past given time
     * </pre>
     */
    public void lockedDenom(osmosis.lockup.QueryOuterClass.LockedDenomRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.LockedDenomResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLockedDenomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns lock record by id
     * </pre>
     */
    public void lockedByID(osmosis.lockup.QueryOuterClass.LockedRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.LockedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLockedByIDMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns account locked records with longer duration
     * </pre>
     */
    public void accountLockedLongerDuration(osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAccountLockedLongerDurationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns account locked records with longer duration excluding tokens
     * started unlocking
     * </pre>
     */
    public void accountLockedLongerDurationNotUnlockingOnly(osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAccountLockedLongerDurationNotUnlockingOnlyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns account's locked records for a denom with longer duration
     * </pre>
     */
    public void accountLockedLongerDurationDenom(osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomRequest request,
        io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAccountLockedLongerDurationDenomMethod(), getCallOptions()), request, responseObserver);
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
     * Return full balance of the module
     * </pre>
     */
    public osmosis.lockup.QueryOuterClass.ModuleBalanceResponse moduleBalance(osmosis.lockup.QueryOuterClass.ModuleBalanceRequest request) {
      return blockingUnaryCall(
          getChannel(), getModuleBalanceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Return locked balance of the module
     * </pre>
     */
    public osmosis.lockup.QueryOuterClass.ModuleLockedAmountResponse moduleLockedAmount(osmosis.lockup.QueryOuterClass.ModuleLockedAmountRequest request) {
      return blockingUnaryCall(
          getChannel(), getModuleLockedAmountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns unlockable coins which are not withdrawn yet
     * </pre>
     */
    public osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsResponse accountUnlockableCoins(osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsRequest request) {
      return blockingUnaryCall(
          getChannel(), getAccountUnlockableCoinsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns unlocking coins
     * </pre>
     */
    public osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsResponse accountUnlockingCoins(osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsRequest request) {
      return blockingUnaryCall(
          getChannel(), getAccountUnlockingCoinsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Return a locked coins that can't be withdrawn
     * </pre>
     */
    public osmosis.lockup.QueryOuterClass.AccountLockedCoinsResponse accountLockedCoins(osmosis.lockup.QueryOuterClass.AccountLockedCoinsRequest request) {
      return blockingUnaryCall(
          getChannel(), getAccountLockedCoinsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns locked records of an account with unlock time beyond timestamp
     * </pre>
     */
    public osmosis.lockup.QueryOuterClass.AccountLockedPastTimeResponse accountLockedPastTime(osmosis.lockup.QueryOuterClass.AccountLockedPastTimeRequest request) {
      return blockingUnaryCall(
          getChannel(), getAccountLockedPastTimeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns locked records of an account with unlock time beyond timestamp
     * excluding tokens started unlocking
     * </pre>
     */
    public osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyResponse accountLockedPastTimeNotUnlockingOnly(osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyRequest request) {
      return blockingUnaryCall(
          getChannel(), getAccountLockedPastTimeNotUnlockingOnlyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns unlocked records with unlock time before timestamp
     * </pre>
     */
    public osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeResponse accountUnlockedBeforeTime(osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeRequest request) {
      return blockingUnaryCall(
          getChannel(), getAccountUnlockedBeforeTimeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns lock records by address, timestamp, denom
     * </pre>
     */
    public osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomResponse accountLockedPastTimeDenom(osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomRequest request) {
      return blockingUnaryCall(
          getChannel(), getAccountLockedPastTimeDenomMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns total locked per denom with longer past given time
     * </pre>
     */
    public osmosis.lockup.QueryOuterClass.LockedDenomResponse lockedDenom(osmosis.lockup.QueryOuterClass.LockedDenomRequest request) {
      return blockingUnaryCall(
          getChannel(), getLockedDenomMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns lock record by id
     * </pre>
     */
    public osmosis.lockup.QueryOuterClass.LockedResponse lockedByID(osmosis.lockup.QueryOuterClass.LockedRequest request) {
      return blockingUnaryCall(
          getChannel(), getLockedByIDMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns account locked records with longer duration
     * </pre>
     */
    public osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationResponse accountLockedLongerDuration(osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationRequest request) {
      return blockingUnaryCall(
          getChannel(), getAccountLockedLongerDurationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns account locked records with longer duration excluding tokens
     * started unlocking
     * </pre>
     */
    public osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyResponse accountLockedLongerDurationNotUnlockingOnly(osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyRequest request) {
      return blockingUnaryCall(
          getChannel(), getAccountLockedLongerDurationNotUnlockingOnlyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns account's locked records for a denom with longer duration
     * </pre>
     */
    public osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomResponse accountLockedLongerDurationDenom(osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomRequest request) {
      return blockingUnaryCall(
          getChannel(), getAccountLockedLongerDurationDenomMethod(), getCallOptions(), request);
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
     * Return full balance of the module
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.lockup.QueryOuterClass.ModuleBalanceResponse> moduleBalance(
        osmosis.lockup.QueryOuterClass.ModuleBalanceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getModuleBalanceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Return locked balance of the module
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.lockup.QueryOuterClass.ModuleLockedAmountResponse> moduleLockedAmount(
        osmosis.lockup.QueryOuterClass.ModuleLockedAmountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getModuleLockedAmountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns unlockable coins which are not withdrawn yet
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsResponse> accountUnlockableCoins(
        osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAccountUnlockableCoinsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns unlocking coins
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsResponse> accountUnlockingCoins(
        osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAccountUnlockingCoinsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Return a locked coins that can't be withdrawn
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.lockup.QueryOuterClass.AccountLockedCoinsResponse> accountLockedCoins(
        osmosis.lockup.QueryOuterClass.AccountLockedCoinsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAccountLockedCoinsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns locked records of an account with unlock time beyond timestamp
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeResponse> accountLockedPastTime(
        osmosis.lockup.QueryOuterClass.AccountLockedPastTimeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAccountLockedPastTimeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns locked records of an account with unlock time beyond timestamp
     * excluding tokens started unlocking
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyResponse> accountLockedPastTimeNotUnlockingOnly(
        osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAccountLockedPastTimeNotUnlockingOnlyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns unlocked records with unlock time before timestamp
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeResponse> accountUnlockedBeforeTime(
        osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAccountUnlockedBeforeTimeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns lock records by address, timestamp, denom
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomResponse> accountLockedPastTimeDenom(
        osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAccountLockedPastTimeDenomMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns total locked per denom with longer past given time
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.lockup.QueryOuterClass.LockedDenomResponse> lockedDenom(
        osmosis.lockup.QueryOuterClass.LockedDenomRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLockedDenomMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns lock record by id
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.lockup.QueryOuterClass.LockedResponse> lockedByID(
        osmosis.lockup.QueryOuterClass.LockedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLockedByIDMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns account locked records with longer duration
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationResponse> accountLockedLongerDuration(
        osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAccountLockedLongerDurationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns account locked records with longer duration excluding tokens
     * started unlocking
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyResponse> accountLockedLongerDurationNotUnlockingOnly(
        osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAccountLockedLongerDurationNotUnlockingOnlyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns account's locked records for a denom with longer duration
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomResponse> accountLockedLongerDurationDenom(
        osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAccountLockedLongerDurationDenomMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MODULE_BALANCE = 0;
  private static final int METHODID_MODULE_LOCKED_AMOUNT = 1;
  private static final int METHODID_ACCOUNT_UNLOCKABLE_COINS = 2;
  private static final int METHODID_ACCOUNT_UNLOCKING_COINS = 3;
  private static final int METHODID_ACCOUNT_LOCKED_COINS = 4;
  private static final int METHODID_ACCOUNT_LOCKED_PAST_TIME = 5;
  private static final int METHODID_ACCOUNT_LOCKED_PAST_TIME_NOT_UNLOCKING_ONLY = 6;
  private static final int METHODID_ACCOUNT_UNLOCKED_BEFORE_TIME = 7;
  private static final int METHODID_ACCOUNT_LOCKED_PAST_TIME_DENOM = 8;
  private static final int METHODID_LOCKED_DENOM = 9;
  private static final int METHODID_LOCKED_BY_ID = 10;
  private static final int METHODID_ACCOUNT_LOCKED_LONGER_DURATION = 11;
  private static final int METHODID_ACCOUNT_LOCKED_LONGER_DURATION_NOT_UNLOCKING_ONLY = 12;
  private static final int METHODID_ACCOUNT_LOCKED_LONGER_DURATION_DENOM = 13;

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
        case METHODID_MODULE_BALANCE:
          serviceImpl.moduleBalance((osmosis.lockup.QueryOuterClass.ModuleBalanceRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.ModuleBalanceResponse>) responseObserver);
          break;
        case METHODID_MODULE_LOCKED_AMOUNT:
          serviceImpl.moduleLockedAmount((osmosis.lockup.QueryOuterClass.ModuleLockedAmountRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.ModuleLockedAmountResponse>) responseObserver);
          break;
        case METHODID_ACCOUNT_UNLOCKABLE_COINS:
          serviceImpl.accountUnlockableCoins((osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountUnlockableCoinsResponse>) responseObserver);
          break;
        case METHODID_ACCOUNT_UNLOCKING_COINS:
          serviceImpl.accountUnlockingCoins((osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountUnlockingCoinsResponse>) responseObserver);
          break;
        case METHODID_ACCOUNT_LOCKED_COINS:
          serviceImpl.accountLockedCoins((osmosis.lockup.QueryOuterClass.AccountLockedCoinsRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountLockedCoinsResponse>) responseObserver);
          break;
        case METHODID_ACCOUNT_LOCKED_PAST_TIME:
          serviceImpl.accountLockedPastTime((osmosis.lockup.QueryOuterClass.AccountLockedPastTimeRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeResponse>) responseObserver);
          break;
        case METHODID_ACCOUNT_LOCKED_PAST_TIME_NOT_UNLOCKING_ONLY:
          serviceImpl.accountLockedPastTimeNotUnlockingOnly((osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeNotUnlockingOnlyResponse>) responseObserver);
          break;
        case METHODID_ACCOUNT_UNLOCKED_BEFORE_TIME:
          serviceImpl.accountUnlockedBeforeTime((osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountUnlockedBeforeTimeResponse>) responseObserver);
          break;
        case METHODID_ACCOUNT_LOCKED_PAST_TIME_DENOM:
          serviceImpl.accountLockedPastTimeDenom((osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountLockedPastTimeDenomResponse>) responseObserver);
          break;
        case METHODID_LOCKED_DENOM:
          serviceImpl.lockedDenom((osmosis.lockup.QueryOuterClass.LockedDenomRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.LockedDenomResponse>) responseObserver);
          break;
        case METHODID_LOCKED_BY_ID:
          serviceImpl.lockedByID((osmosis.lockup.QueryOuterClass.LockedRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.LockedResponse>) responseObserver);
          break;
        case METHODID_ACCOUNT_LOCKED_LONGER_DURATION:
          serviceImpl.accountLockedLongerDuration((osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationResponse>) responseObserver);
          break;
        case METHODID_ACCOUNT_LOCKED_LONGER_DURATION_NOT_UNLOCKING_ONLY:
          serviceImpl.accountLockedLongerDurationNotUnlockingOnly((osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationNotUnlockingOnlyResponse>) responseObserver);
          break;
        case METHODID_ACCOUNT_LOCKED_LONGER_DURATION_DENOM:
          serviceImpl.accountLockedLongerDurationDenom((osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.lockup.QueryOuterClass.AccountLockedLongerDurationDenomResponse>) responseObserver);
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
      return osmosis.lockup.QueryOuterClass.getDescriptor();
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
              .addMethod(getModuleBalanceMethod())
              .addMethod(getModuleLockedAmountMethod())
              .addMethod(getAccountUnlockableCoinsMethod())
              .addMethod(getAccountUnlockingCoinsMethod())
              .addMethod(getAccountLockedCoinsMethod())
              .addMethod(getAccountLockedPastTimeMethod())
              .addMethod(getAccountLockedPastTimeNotUnlockingOnlyMethod())
              .addMethod(getAccountUnlockedBeforeTimeMethod())
              .addMethod(getAccountLockedPastTimeDenomMethod())
              .addMethod(getLockedDenomMethod())
              .addMethod(getLockedByIDMethod())
              .addMethod(getAccountLockedLongerDurationMethod())
              .addMethod(getAccountLockedLongerDurationNotUnlockingOnlyMethod())
              .addMethod(getAccountLockedLongerDurationDenomMethod())
              .build();
        }
      }
    }
    return result;
  }
}
