package umeenetwork.umee.peggy.v1;

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
 * Query defines the gRPC querier service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: umee/peggy/v1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "umeenetwork.umee.peggy.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsRequest.class,
      responseType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetResponse> getCurrentValsetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CurrentValset",
      requestType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetRequest.class,
      responseType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetResponse> getCurrentValsetMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetResponse> getCurrentValsetMethod;
    if ((getCurrentValsetMethod = QueryGrpc.getCurrentValsetMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCurrentValsetMethod = QueryGrpc.getCurrentValsetMethod) == null) {
          QueryGrpc.getCurrentValsetMethod = getCurrentValsetMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CurrentValset"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CurrentValset"))
              .build();
        }
      }
    }
    return getCurrentValsetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestResponse> getValsetRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValsetRequest",
      requestType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestRequest.class,
      responseType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestResponse> getValsetRequestMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestResponse> getValsetRequestMethod;
    if ((getValsetRequestMethod = QueryGrpc.getValsetRequestMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValsetRequestMethod = QueryGrpc.getValsetRequestMethod) == null) {
          QueryGrpc.getValsetRequestMethod = getValsetRequestMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValsetRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValsetRequest"))
              .build();
        }
      }
    }
    return getValsetRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmResponse> getValsetConfirmMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValsetConfirm",
      requestType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmRequest.class,
      responseType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmResponse> getValsetConfirmMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmResponse> getValsetConfirmMethod;
    if ((getValsetConfirmMethod = QueryGrpc.getValsetConfirmMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValsetConfirmMethod = QueryGrpc.getValsetConfirmMethod) == null) {
          QueryGrpc.getValsetConfirmMethod = getValsetConfirmMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValsetConfirm"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValsetConfirm"))
              .build();
        }
      }
    }
    return getValsetConfirmMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse> getValsetConfirmsByNonceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValsetConfirmsByNonce",
      requestType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest.class,
      responseType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse> getValsetConfirmsByNonceMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse> getValsetConfirmsByNonceMethod;
    if ((getValsetConfirmsByNonceMethod = QueryGrpc.getValsetConfirmsByNonceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValsetConfirmsByNonceMethod = QueryGrpc.getValsetConfirmsByNonceMethod) == null) {
          QueryGrpc.getValsetConfirmsByNonceMethod = getValsetConfirmsByNonceMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValsetConfirmsByNonce"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValsetConfirmsByNonce"))
              .build();
        }
      }
    }
    return getValsetConfirmsByNonceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsResponse> getLastValsetRequestsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastValsetRequests",
      requestType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsRequest.class,
      responseType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsResponse> getLastValsetRequestsMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsResponse> getLastValsetRequestsMethod;
    if ((getLastValsetRequestsMethod = QueryGrpc.getLastValsetRequestsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastValsetRequestsMethod = QueryGrpc.getLastValsetRequestsMethod) == null) {
          QueryGrpc.getLastValsetRequestsMethod = getLastValsetRequestsMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastValsetRequests"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastValsetRequests"))
              .build();
        }
      }
    }
    return getLastValsetRequestsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse> getLastPendingValsetRequestByAddrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastPendingValsetRequestByAddr",
      requestType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest.class,
      responseType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse> getLastPendingValsetRequestByAddrMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse> getLastPendingValsetRequestByAddrMethod;
    if ((getLastPendingValsetRequestByAddrMethod = QueryGrpc.getLastPendingValsetRequestByAddrMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastPendingValsetRequestByAddrMethod = QueryGrpc.getLastPendingValsetRequestByAddrMethod) == null) {
          QueryGrpc.getLastPendingValsetRequestByAddrMethod = getLastPendingValsetRequestByAddrMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastPendingValsetRequestByAddr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastPendingValsetRequestByAddr"))
              .build();
        }
      }
    }
    return getLastPendingValsetRequestByAddrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomResponse> getERC20ToDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ERC20ToDenom",
      requestType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomRequest.class,
      responseType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomResponse> getERC20ToDenomMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomResponse> getERC20ToDenomMethod;
    if ((getERC20ToDenomMethod = QueryGrpc.getERC20ToDenomMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getERC20ToDenomMethod = QueryGrpc.getERC20ToDenomMethod) == null) {
          QueryGrpc.getERC20ToDenomMethod = getERC20ToDenomMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ERC20ToDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ERC20ToDenom"))
              .build();
        }
      }
    }
    return getERC20ToDenomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Request,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Response> getDenomToERC20Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomToERC20",
      requestType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Request.class,
      responseType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Request,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Response> getDenomToERC20Method() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Request, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Response> getDenomToERC20Method;
    if ((getDenomToERC20Method = QueryGrpc.getDenomToERC20Method) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomToERC20Method = QueryGrpc.getDenomToERC20Method) == null) {
          QueryGrpc.getDenomToERC20Method = getDenomToERC20Method =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Request, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomToERC20"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Response.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomToERC20"))
              .build();
        }
      }
    }
    return getDenomToERC20Method;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrResponse> getLastEventByAddrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastEventByAddr",
      requestType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrRequest.class,
      responseType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrResponse> getLastEventByAddrMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrResponse> getLastEventByAddrMethod;
    if ((getLastEventByAddrMethod = QueryGrpc.getLastEventByAddrMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastEventByAddrMethod = QueryGrpc.getLastEventByAddrMethod) == null) {
          QueryGrpc.getLastEventByAddrMethod = getLastEventByAddrMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastEventByAddr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastEventByAddr"))
              .build();
        }
      }
    }
    return getLastEventByAddrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEth,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEthResponse> getGetPendingSendToEthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPendingSendToEth",
      requestType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEth.class,
      responseType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEthResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEth,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEthResponse> getGetPendingSendToEthMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEth, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEthResponse> getGetPendingSendToEthMethod;
    if ((getGetPendingSendToEthMethod = QueryGrpc.getGetPendingSendToEthMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetPendingSendToEthMethod = QueryGrpc.getGetPendingSendToEthMethod) == null) {
          QueryGrpc.getGetPendingSendToEthMethod = getGetPendingSendToEthMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEth, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEthResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetPendingSendToEth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEth.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEthResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetPendingSendToEth"))
              .build();
        }
      }
    }
    return getGetPendingSendToEthMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeResponse> getBatchFeesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchFees",
      requestType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeRequest.class,
      responseType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeResponse> getBatchFeesMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeResponse> getBatchFeesMethod;
    if ((getBatchFeesMethod = QueryGrpc.getBatchFeesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchFeesMethod = QueryGrpc.getBatchFeesMethod) == null) {
          QueryGrpc.getBatchFeesMethod = getBatchFeesMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchFees"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchFees"))
              .build();
        }
      }
    }
    return getBatchFeesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> getOutgoingTxBatchesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OutgoingTxBatches",
      requestType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest.class,
      responseType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> getOutgoingTxBatchesMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> getOutgoingTxBatchesMethod;
    if ((getOutgoingTxBatchesMethod = QueryGrpc.getOutgoingTxBatchesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOutgoingTxBatchesMethod = QueryGrpc.getOutgoingTxBatchesMethod) == null) {
          QueryGrpc.getOutgoingTxBatchesMethod = getOutgoingTxBatchesMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OutgoingTxBatches"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OutgoingTxBatches"))
              .build();
        }
      }
    }
    return getOutgoingTxBatchesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> getLastPendingBatchRequestByAddrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastPendingBatchRequestByAddr",
      requestType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest.class,
      responseType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> getLastPendingBatchRequestByAddrMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> getLastPendingBatchRequestByAddrMethod;
    if ((getLastPendingBatchRequestByAddrMethod = QueryGrpc.getLastPendingBatchRequestByAddrMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastPendingBatchRequestByAddrMethod = QueryGrpc.getLastPendingBatchRequestByAddrMethod) == null) {
          QueryGrpc.getLastPendingBatchRequestByAddrMethod = getLastPendingBatchRequestByAddrMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastPendingBatchRequestByAddr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastPendingBatchRequestByAddr"))
              .build();
        }
      }
    }
    return getLastPendingBatchRequestByAddrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> getBatchRequestByNonceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchRequestByNonce",
      requestType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceRequest.class,
      responseType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> getBatchRequestByNonceMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> getBatchRequestByNonceMethod;
    if ((getBatchRequestByNonceMethod = QueryGrpc.getBatchRequestByNonceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchRequestByNonceMethod = QueryGrpc.getBatchRequestByNonceMethod) == null) {
          QueryGrpc.getBatchRequestByNonceMethod = getBatchRequestByNonceMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchRequestByNonce"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchRequestByNonce"))
              .build();
        }
      }
    }
    return getBatchRequestByNonceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsResponse> getBatchConfirmsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchConfirms",
      requestType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsRequest.class,
      responseType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsResponse> getBatchConfirmsMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsResponse> getBatchConfirmsMethod;
    if ((getBatchConfirmsMethod = QueryGrpc.getBatchConfirmsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchConfirmsMethod = QueryGrpc.getBatchConfirmsMethod) == null) {
          QueryGrpc.getBatchConfirmsMethod = getBatchConfirmsMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchConfirms"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchConfirms"))
              .build();
        }
      }
    }
    return getBatchConfirmsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse> getGetDelegateKeyByValidatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDelegateKeyByValidator",
      requestType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress.class,
      responseType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse> getGetDelegateKeyByValidatorMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse> getGetDelegateKeyByValidatorMethod;
    if ((getGetDelegateKeyByValidatorMethod = QueryGrpc.getGetDelegateKeyByValidatorMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetDelegateKeyByValidatorMethod = QueryGrpc.getGetDelegateKeyByValidatorMethod) == null) {
          QueryGrpc.getGetDelegateKeyByValidatorMethod = getGetDelegateKeyByValidatorMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetDelegateKeyByValidator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetDelegateKeyByValidator"))
              .build();
        }
      }
    }
    return getGetDelegateKeyByValidatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddress,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse> getGetDelegateKeyByEthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDelegateKeyByEth",
      requestType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddress.class,
      responseType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddress,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse> getGetDelegateKeyByEthMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddress, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse> getGetDelegateKeyByEthMethod;
    if ((getGetDelegateKeyByEthMethod = QueryGrpc.getGetDelegateKeyByEthMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetDelegateKeyByEthMethod = QueryGrpc.getGetDelegateKeyByEthMethod) == null) {
          QueryGrpc.getGetDelegateKeyByEthMethod = getGetDelegateKeyByEthMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddress, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetDelegateKeyByEth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddress.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetDelegateKeyByEth"))
              .build();
        }
      }
    }
    return getGetDelegateKeyByEthMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse> getGetDelegateKeyByOrchestratorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDelegateKeyByOrchestrator",
      requestType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress.class,
      responseType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse> getGetDelegateKeyByOrchestratorMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse> getGetDelegateKeyByOrchestratorMethod;
    if ((getGetDelegateKeyByOrchestratorMethod = QueryGrpc.getGetDelegateKeyByOrchestratorMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetDelegateKeyByOrchestratorMethod = QueryGrpc.getGetDelegateKeyByOrchestratorMethod) == null) {
          QueryGrpc.getGetDelegateKeyByOrchestratorMethod = getGetDelegateKeyByOrchestratorMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetDelegateKeyByOrchestrator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetDelegateKeyByOrchestrator"))
              .build();
        }
      }
    }
    return getGetDelegateKeyByOrchestratorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateResponse> getPeggyModuleStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PeggyModuleState",
      requestType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateRequest.class,
      responseType = umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateRequest,
      umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateResponse> getPeggyModuleStateMethod() {
    io.grpc.MethodDescriptor<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateResponse> getPeggyModuleStateMethod;
    if ((getPeggyModuleStateMethod = QueryGrpc.getPeggyModuleStateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPeggyModuleStateMethod = QueryGrpc.getPeggyModuleStateMethod) == null) {
          QueryGrpc.getPeggyModuleStateMethod = getPeggyModuleStateMethod =
              io.grpc.MethodDescriptor.<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateRequest, umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PeggyModuleState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PeggyModuleState"))
              .build();
        }
      }
    }
    return getPeggyModuleStateMethod;
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
   * Query defines the gRPC querier service
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     */
    public void params(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     */
    public void currentValset(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCurrentValsetMethod(), responseObserver);
    }

    /**
     */
    public void valsetRequest(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getValsetRequestMethod(), responseObserver);
    }

    /**
     */
    public void valsetConfirm(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getValsetConfirmMethod(), responseObserver);
    }

    /**
     */
    public void valsetConfirmsByNonce(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getValsetConfirmsByNonceMethod(), responseObserver);
    }

    /**
     */
    public void lastValsetRequests(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLastValsetRequestsMethod(), responseObserver);
    }

    /**
     */
    public void lastPendingValsetRequestByAddr(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLastPendingValsetRequestByAddrMethod(), responseObserver);
    }

    /**
     */
    public void eRC20ToDenom(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getERC20ToDenomMethod(), responseObserver);
    }

    /**
     */
    public void denomToERC20(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Request request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Response> responseObserver) {
      asyncUnimplementedUnaryCall(getDenomToERC20Method(), responseObserver);
    }

    /**
     */
    public void lastEventByAddr(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLastEventByAddrMethod(), responseObserver);
    }

    /**
     */
    public void getPendingSendToEth(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEth request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEthResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetPendingSendToEthMethod(), responseObserver);
    }

    /**
     */
    public void batchFees(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBatchFeesMethod(), responseObserver);
    }

    /**
     */
    public void outgoingTxBatches(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOutgoingTxBatchesMethod(), responseObserver);
    }

    /**
     */
    public void lastPendingBatchRequestByAddr(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLastPendingBatchRequestByAddrMethod(), responseObserver);
    }

    /**
     */
    public void batchRequestByNonce(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBatchRequestByNonceMethod(), responseObserver);
    }

    /**
     */
    public void batchConfirms(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBatchConfirmsMethod(), responseObserver);
    }

    /**
     */
    public void getDelegateKeyByValidator(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDelegateKeyByValidatorMethod(), responseObserver);
    }

    /**
     */
    public void getDelegateKeyByEth(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddress request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDelegateKeyByEthMethod(), responseObserver);
    }

    /**
     */
    public void getDelegateKeyByOrchestrator(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDelegateKeyByOrchestratorMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire peggy module's state
     * </pre>
     */
    public void peggyModuleState(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPeggyModuleStateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsRequest,
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getCurrentValsetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetRequest,
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetResponse>(
                  this, METHODID_CURRENT_VALSET)))
          .addMethod(
            getValsetRequestMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestRequest,
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestResponse>(
                  this, METHODID_VALSET_REQUEST)))
          .addMethod(
            getValsetConfirmMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmRequest,
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmResponse>(
                  this, METHODID_VALSET_CONFIRM)))
          .addMethod(
            getValsetConfirmsByNonceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest,
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse>(
                  this, METHODID_VALSET_CONFIRMS_BY_NONCE)))
          .addMethod(
            getLastValsetRequestsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsRequest,
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsResponse>(
                  this, METHODID_LAST_VALSET_REQUESTS)))
          .addMethod(
            getLastPendingValsetRequestByAddrMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest,
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse>(
                  this, METHODID_LAST_PENDING_VALSET_REQUEST_BY_ADDR)))
          .addMethod(
            getERC20ToDenomMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomRequest,
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomResponse>(
                  this, METHODID_ERC20TO_DENOM)))
          .addMethod(
            getDenomToERC20Method(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Request,
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Response>(
                  this, METHODID_DENOM_TO_ERC20)))
          .addMethod(
            getLastEventByAddrMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrRequest,
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrResponse>(
                  this, METHODID_LAST_EVENT_BY_ADDR)))
          .addMethod(
            getGetPendingSendToEthMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEth,
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEthResponse>(
                  this, METHODID_GET_PENDING_SEND_TO_ETH)))
          .addMethod(
            getBatchFeesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeRequest,
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeResponse>(
                  this, METHODID_BATCH_FEES)))
          .addMethod(
            getOutgoingTxBatchesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest,
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse>(
                  this, METHODID_OUTGOING_TX_BATCHES)))
          .addMethod(
            getLastPendingBatchRequestByAddrMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest,
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse>(
                  this, METHODID_LAST_PENDING_BATCH_REQUEST_BY_ADDR)))
          .addMethod(
            getBatchRequestByNonceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceRequest,
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceResponse>(
                  this, METHODID_BATCH_REQUEST_BY_NONCE)))
          .addMethod(
            getBatchConfirmsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsRequest,
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsResponse>(
                  this, METHODID_BATCH_CONFIRMS)))
          .addMethod(
            getGetDelegateKeyByValidatorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress,
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse>(
                  this, METHODID_GET_DELEGATE_KEY_BY_VALIDATOR)))
          .addMethod(
            getGetDelegateKeyByEthMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddress,
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse>(
                  this, METHODID_GET_DELEGATE_KEY_BY_ETH)))
          .addMethod(
            getGetDelegateKeyByOrchestratorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress,
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse>(
                  this, METHODID_GET_DELEGATE_KEY_BY_ORCHESTRATOR)))
          .addMethod(
            getPeggyModuleStateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateRequest,
                umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateResponse>(
                  this, METHODID_PEGGY_MODULE_STATE)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service
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
     */
    public void params(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void currentValset(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCurrentValsetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void valsetRequest(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getValsetRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void valsetConfirm(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getValsetConfirmMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void valsetConfirmsByNonce(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getValsetConfirmsByNonceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastValsetRequests(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLastValsetRequestsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastPendingValsetRequestByAddr(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLastPendingValsetRequestByAddrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void eRC20ToDenom(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getERC20ToDenomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void denomToERC20(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Request request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDenomToERC20Method(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastEventByAddr(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLastEventByAddrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPendingSendToEth(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEth request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEthResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetPendingSendToEthMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void batchFees(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBatchFeesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void outgoingTxBatches(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOutgoingTxBatchesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lastPendingBatchRequestByAddr(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLastPendingBatchRequestByAddrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void batchRequestByNonce(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBatchRequestByNonceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void batchConfirms(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBatchConfirmsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDelegateKeyByValidator(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDelegateKeyByValidatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDelegateKeyByEth(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddress request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDelegateKeyByEthMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDelegateKeyByOrchestrator(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDelegateKeyByOrchestratorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the entire peggy module's state
     * </pre>
     */
    public void peggyModuleState(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateRequest request,
        io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPeggyModuleStateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service
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
     */
    public umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsResponse params(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetResponse currentValset(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetRequest request) {
      return blockingUnaryCall(
          getChannel(), getCurrentValsetMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestResponse valsetRequest(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestRequest request) {
      return blockingUnaryCall(
          getChannel(), getValsetRequestMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmResponse valsetConfirm(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmRequest request) {
      return blockingUnaryCall(
          getChannel(), getValsetConfirmMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse valsetConfirmsByNonce(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest request) {
      return blockingUnaryCall(
          getChannel(), getValsetConfirmsByNonceMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsResponse lastValsetRequests(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsRequest request) {
      return blockingUnaryCall(
          getChannel(), getLastValsetRequestsMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse lastPendingValsetRequestByAddr(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest request) {
      return blockingUnaryCall(
          getChannel(), getLastPendingValsetRequestByAddrMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomResponse eRC20ToDenom(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomRequest request) {
      return blockingUnaryCall(
          getChannel(), getERC20ToDenomMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Response denomToERC20(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Request request) {
      return blockingUnaryCall(
          getChannel(), getDenomToERC20Method(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrResponse lastEventByAddr(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrRequest request) {
      return blockingUnaryCall(
          getChannel(), getLastEventByAddrMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEthResponse getPendingSendToEth(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEth request) {
      return blockingUnaryCall(
          getChannel(), getGetPendingSendToEthMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeResponse batchFees(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeRequest request) {
      return blockingUnaryCall(
          getChannel(), getBatchFeesMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse outgoingTxBatches(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest request) {
      return blockingUnaryCall(
          getChannel(), getOutgoingTxBatchesMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse lastPendingBatchRequestByAddr(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest request) {
      return blockingUnaryCall(
          getChannel(), getLastPendingBatchRequestByAddrMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceResponse batchRequestByNonce(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceRequest request) {
      return blockingUnaryCall(
          getChannel(), getBatchRequestByNonceMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsResponse batchConfirms(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsRequest request) {
      return blockingUnaryCall(
          getChannel(), getBatchConfirmsMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse getDelegateKeyByValidator(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress request) {
      return blockingUnaryCall(
          getChannel(), getGetDelegateKeyByValidatorMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse getDelegateKeyByEth(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddress request) {
      return blockingUnaryCall(
          getChannel(), getGetDelegateKeyByEthMethod(), getCallOptions(), request);
    }

    /**
     */
    public umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse getDelegateKeyByOrchestrator(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress request) {
      return blockingUnaryCall(
          getChannel(), getGetDelegateKeyByOrchestratorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the entire peggy module's state
     * </pre>
     */
    public umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateResponse peggyModuleState(umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateRequest request) {
      return blockingUnaryCall(
          getChannel(), getPeggyModuleStateMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service
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
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsResponse> params(
        umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetResponse> currentValset(
        umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCurrentValsetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestResponse> valsetRequest(
        umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getValsetRequestMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmResponse> valsetConfirm(
        umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getValsetConfirmMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse> valsetConfirmsByNonce(
        umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getValsetConfirmsByNonceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsResponse> lastValsetRequests(
        umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLastValsetRequestsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse> lastPendingValsetRequestByAddr(
        umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLastPendingValsetRequestByAddrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomResponse> eRC20ToDenom(
        umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getERC20ToDenomMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Response> denomToERC20(
        umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Request request) {
      return futureUnaryCall(
          getChannel().newCall(getDenomToERC20Method(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrResponse> lastEventByAddr(
        umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLastEventByAddrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEthResponse> getPendingSendToEth(
        umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEth request) {
      return futureUnaryCall(
          getChannel().newCall(getGetPendingSendToEthMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeResponse> batchFees(
        umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBatchFeesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> outgoingTxBatches(
        umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOutgoingTxBatchesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> lastPendingBatchRequestByAddr(
        umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLastPendingBatchRequestByAddrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> batchRequestByNonce(
        umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBatchRequestByNonceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsResponse> batchConfirms(
        umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBatchConfirmsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse> getDelegateKeyByValidator(
        umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDelegateKeyByValidatorMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse> getDelegateKeyByEth(
        umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddress request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDelegateKeyByEthMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse> getDelegateKeyByOrchestrator(
        umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDelegateKeyByOrchestratorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the entire peggy module's state
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateResponse> peggyModuleState(
        umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPeggyModuleStateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_CURRENT_VALSET = 1;
  private static final int METHODID_VALSET_REQUEST = 2;
  private static final int METHODID_VALSET_CONFIRM = 3;
  private static final int METHODID_VALSET_CONFIRMS_BY_NONCE = 4;
  private static final int METHODID_LAST_VALSET_REQUESTS = 5;
  private static final int METHODID_LAST_PENDING_VALSET_REQUEST_BY_ADDR = 6;
  private static final int METHODID_ERC20TO_DENOM = 7;
  private static final int METHODID_DENOM_TO_ERC20 = 8;
  private static final int METHODID_LAST_EVENT_BY_ADDR = 9;
  private static final int METHODID_GET_PENDING_SEND_TO_ETH = 10;
  private static final int METHODID_BATCH_FEES = 11;
  private static final int METHODID_OUTGOING_TX_BATCHES = 12;
  private static final int METHODID_LAST_PENDING_BATCH_REQUEST_BY_ADDR = 13;
  private static final int METHODID_BATCH_REQUEST_BY_NONCE = 14;
  private static final int METHODID_BATCH_CONFIRMS = 15;
  private static final int METHODID_GET_DELEGATE_KEY_BY_VALIDATOR = 16;
  private static final int METHODID_GET_DELEGATE_KEY_BY_ETH = 17;
  private static final int METHODID_GET_DELEGATE_KEY_BY_ORCHESTRATOR = 18;
  private static final int METHODID_PEGGY_MODULE_STATE = 19;

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
          serviceImpl.params((umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_CURRENT_VALSET:
          serviceImpl.currentValset((umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetRequest) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryCurrentValsetResponse>) responseObserver);
          break;
        case METHODID_VALSET_REQUEST:
          serviceImpl.valsetRequest((umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestRequest) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetRequestResponse>) responseObserver);
          break;
        case METHODID_VALSET_CONFIRM:
          serviceImpl.valsetConfirm((umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmRequest) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmResponse>) responseObserver);
          break;
        case METHODID_VALSET_CONFIRMS_BY_NONCE:
          serviceImpl.valsetConfirmsByNonce((umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse>) responseObserver);
          break;
        case METHODID_LAST_VALSET_REQUESTS:
          serviceImpl.lastValsetRequests((umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsRequest) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastValsetRequestsResponse>) responseObserver);
          break;
        case METHODID_LAST_PENDING_VALSET_REQUEST_BY_ADDR:
          serviceImpl.lastPendingValsetRequestByAddr((umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse>) responseObserver);
          break;
        case METHODID_ERC20TO_DENOM:
          serviceImpl.eRC20ToDenom((umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomRequest) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryERC20ToDenomResponse>) responseObserver);
          break;
        case METHODID_DENOM_TO_ERC20:
          serviceImpl.denomToERC20((umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Request) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDenomToERC20Response>) responseObserver);
          break;
        case METHODID_LAST_EVENT_BY_ADDR:
          serviceImpl.lastEventByAddr((umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrRequest) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastEventByAddrResponse>) responseObserver);
          break;
        case METHODID_GET_PENDING_SEND_TO_ETH:
          serviceImpl.getPendingSendToEth((umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEth) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryPendingSendToEthResponse>) responseObserver);
          break;
        case METHODID_BATCH_FEES:
          serviceImpl.batchFees((umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeRequest) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchFeeResponse>) responseObserver);
          break;
        case METHODID_OUTGOING_TX_BATCHES:
          serviceImpl.outgoingTxBatches((umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse>) responseObserver);
          break;
        case METHODID_LAST_PENDING_BATCH_REQUEST_BY_ADDR:
          serviceImpl.lastPendingBatchRequestByAddr((umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse>) responseObserver);
          break;
        case METHODID_BATCH_REQUEST_BY_NONCE:
          serviceImpl.batchRequestByNonce((umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceRequest) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchRequestByNonceResponse>) responseObserver);
          break;
        case METHODID_BATCH_CONFIRMS:
          serviceImpl.batchConfirms((umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsRequest) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryBatchConfirmsResponse>) responseObserver);
          break;
        case METHODID_GET_DELEGATE_KEY_BY_VALIDATOR:
          serviceImpl.getDelegateKeyByValidator((umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddress) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByValidatorAddressResponse>) responseObserver);
          break;
        case METHODID_GET_DELEGATE_KEY_BY_ETH:
          serviceImpl.getDelegateKeyByEth((umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddress) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByEthAddressResponse>) responseObserver);
          break;
        case METHODID_GET_DELEGATE_KEY_BY_ORCHESTRATOR:
          serviceImpl.getDelegateKeyByOrchestrator((umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddress) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryDelegateKeysByOrchestratorAddressResponse>) responseObserver);
          break;
        case METHODID_PEGGY_MODULE_STATE:
          serviceImpl.peggyModuleState((umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateRequest) request,
              (io.grpc.stub.StreamObserver<umeenetwork.umee.peggy.v1.QueryOuterClass.QueryModuleStateResponse>) responseObserver);
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
      return umeenetwork.umee.peggy.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getCurrentValsetMethod())
              .addMethod(getValsetRequestMethod())
              .addMethod(getValsetConfirmMethod())
              .addMethod(getValsetConfirmsByNonceMethod())
              .addMethod(getLastValsetRequestsMethod())
              .addMethod(getLastPendingValsetRequestByAddrMethod())
              .addMethod(getERC20ToDenomMethod())
              .addMethod(getDenomToERC20Method())
              .addMethod(getLastEventByAddrMethod())
              .addMethod(getGetPendingSendToEthMethod())
              .addMethod(getBatchFeesMethod())
              .addMethod(getOutgoingTxBatchesMethod())
              .addMethod(getLastPendingBatchRequestByAddrMethod())
              .addMethod(getBatchRequestByNonceMethod())
              .addMethod(getBatchConfirmsMethod())
              .addMethod(getGetDelegateKeyByValidatorMethod())
              .addMethod(getGetDelegateKeyByEthMethod())
              .addMethod(getGetDelegateKeyByOrchestratorMethod())
              .addMethod(getPeggyModuleStateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
