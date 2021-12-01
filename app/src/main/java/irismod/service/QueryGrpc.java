package irismod.service;

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
 * Query creates service with iservice as rpc
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: iris_mod/service/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "irismod.service.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryDefinitionRequest,
      irismod.service.QueryOuterClass.QueryDefinitionResponse> getDefinitionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Definition",
      requestType = irismod.service.QueryOuterClass.QueryDefinitionRequest.class,
      responseType = irismod.service.QueryOuterClass.QueryDefinitionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryDefinitionRequest,
      irismod.service.QueryOuterClass.QueryDefinitionResponse> getDefinitionMethod() {
    io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryDefinitionRequest, irismod.service.QueryOuterClass.QueryDefinitionResponse> getDefinitionMethod;
    if ((getDefinitionMethod = QueryGrpc.getDefinitionMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDefinitionMethod = QueryGrpc.getDefinitionMethod) == null) {
          QueryGrpc.getDefinitionMethod = getDefinitionMethod =
              io.grpc.MethodDescriptor.<irismod.service.QueryOuterClass.QueryDefinitionRequest, irismod.service.QueryOuterClass.QueryDefinitionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Definition"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryDefinitionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryDefinitionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Definition"))
              .build();
        }
      }
    }
    return getDefinitionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryBindingRequest,
      irismod.service.QueryOuterClass.QueryBindingResponse> getBindingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Binding",
      requestType = irismod.service.QueryOuterClass.QueryBindingRequest.class,
      responseType = irismod.service.QueryOuterClass.QueryBindingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryBindingRequest,
      irismod.service.QueryOuterClass.QueryBindingResponse> getBindingMethod() {
    io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryBindingRequest, irismod.service.QueryOuterClass.QueryBindingResponse> getBindingMethod;
    if ((getBindingMethod = QueryGrpc.getBindingMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBindingMethod = QueryGrpc.getBindingMethod) == null) {
          QueryGrpc.getBindingMethod = getBindingMethod =
              io.grpc.MethodDescriptor.<irismod.service.QueryOuterClass.QueryBindingRequest, irismod.service.QueryOuterClass.QueryBindingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Binding"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryBindingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryBindingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Binding"))
              .build();
        }
      }
    }
    return getBindingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryBindingsRequest,
      irismod.service.QueryOuterClass.QueryBindingsResponse> getBindingsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Bindings",
      requestType = irismod.service.QueryOuterClass.QueryBindingsRequest.class,
      responseType = irismod.service.QueryOuterClass.QueryBindingsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryBindingsRequest,
      irismod.service.QueryOuterClass.QueryBindingsResponse> getBindingsMethod() {
    io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryBindingsRequest, irismod.service.QueryOuterClass.QueryBindingsResponse> getBindingsMethod;
    if ((getBindingsMethod = QueryGrpc.getBindingsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBindingsMethod = QueryGrpc.getBindingsMethod) == null) {
          QueryGrpc.getBindingsMethod = getBindingsMethod =
              io.grpc.MethodDescriptor.<irismod.service.QueryOuterClass.QueryBindingsRequest, irismod.service.QueryOuterClass.QueryBindingsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Bindings"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryBindingsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryBindingsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Bindings"))
              .build();
        }
      }
    }
    return getBindingsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryWithdrawAddressRequest,
      irismod.service.QueryOuterClass.QueryWithdrawAddressResponse> getWithdrawAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawAddress",
      requestType = irismod.service.QueryOuterClass.QueryWithdrawAddressRequest.class,
      responseType = irismod.service.QueryOuterClass.QueryWithdrawAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryWithdrawAddressRequest,
      irismod.service.QueryOuterClass.QueryWithdrawAddressResponse> getWithdrawAddressMethod() {
    io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryWithdrawAddressRequest, irismod.service.QueryOuterClass.QueryWithdrawAddressResponse> getWithdrawAddressMethod;
    if ((getWithdrawAddressMethod = QueryGrpc.getWithdrawAddressMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getWithdrawAddressMethod = QueryGrpc.getWithdrawAddressMethod) == null) {
          QueryGrpc.getWithdrawAddressMethod = getWithdrawAddressMethod =
              io.grpc.MethodDescriptor.<irismod.service.QueryOuterClass.QueryWithdrawAddressRequest, irismod.service.QueryOuterClass.QueryWithdrawAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WithdrawAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryWithdrawAddressRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryWithdrawAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("WithdrawAddress"))
              .build();
        }
      }
    }
    return getWithdrawAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryRequestContextRequest,
      irismod.service.QueryOuterClass.QueryRequestContextResponse> getRequestContextMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RequestContext",
      requestType = irismod.service.QueryOuterClass.QueryRequestContextRequest.class,
      responseType = irismod.service.QueryOuterClass.QueryRequestContextResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryRequestContextRequest,
      irismod.service.QueryOuterClass.QueryRequestContextResponse> getRequestContextMethod() {
    io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryRequestContextRequest, irismod.service.QueryOuterClass.QueryRequestContextResponse> getRequestContextMethod;
    if ((getRequestContextMethod = QueryGrpc.getRequestContextMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRequestContextMethod = QueryGrpc.getRequestContextMethod) == null) {
          QueryGrpc.getRequestContextMethod = getRequestContextMethod =
              io.grpc.MethodDescriptor.<irismod.service.QueryOuterClass.QueryRequestContextRequest, irismod.service.QueryOuterClass.QueryRequestContextResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RequestContext"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryRequestContextRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryRequestContextResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RequestContext"))
              .build();
        }
      }
    }
    return getRequestContextMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryRequestRequest,
      irismod.service.QueryOuterClass.QueryRequestResponse> getRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Request",
      requestType = irismod.service.QueryOuterClass.QueryRequestRequest.class,
      responseType = irismod.service.QueryOuterClass.QueryRequestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryRequestRequest,
      irismod.service.QueryOuterClass.QueryRequestResponse> getRequestMethod() {
    io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryRequestRequest, irismod.service.QueryOuterClass.QueryRequestResponse> getRequestMethod;
    if ((getRequestMethod = QueryGrpc.getRequestMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRequestMethod = QueryGrpc.getRequestMethod) == null) {
          QueryGrpc.getRequestMethod = getRequestMethod =
              io.grpc.MethodDescriptor.<irismod.service.QueryOuterClass.QueryRequestRequest, irismod.service.QueryOuterClass.QueryRequestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Request"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryRequestRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryRequestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Request"))
              .build();
        }
      }
    }
    return getRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryRequestsRequest,
      irismod.service.QueryOuterClass.QueryRequestsResponse> getRequestsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Requests",
      requestType = irismod.service.QueryOuterClass.QueryRequestsRequest.class,
      responseType = irismod.service.QueryOuterClass.QueryRequestsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryRequestsRequest,
      irismod.service.QueryOuterClass.QueryRequestsResponse> getRequestsMethod() {
    io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryRequestsRequest, irismod.service.QueryOuterClass.QueryRequestsResponse> getRequestsMethod;
    if ((getRequestsMethod = QueryGrpc.getRequestsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRequestsMethod = QueryGrpc.getRequestsMethod) == null) {
          QueryGrpc.getRequestsMethod = getRequestsMethod =
              io.grpc.MethodDescriptor.<irismod.service.QueryOuterClass.QueryRequestsRequest, irismod.service.QueryOuterClass.QueryRequestsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Requests"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryRequestsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryRequestsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Requests"))
              .build();
        }
      }
    }
    return getRequestsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryRequestsByReqCtxRequest,
      irismod.service.QueryOuterClass.QueryRequestsByReqCtxResponse> getRequestsByReqCtxMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RequestsByReqCtx",
      requestType = irismod.service.QueryOuterClass.QueryRequestsByReqCtxRequest.class,
      responseType = irismod.service.QueryOuterClass.QueryRequestsByReqCtxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryRequestsByReqCtxRequest,
      irismod.service.QueryOuterClass.QueryRequestsByReqCtxResponse> getRequestsByReqCtxMethod() {
    io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryRequestsByReqCtxRequest, irismod.service.QueryOuterClass.QueryRequestsByReqCtxResponse> getRequestsByReqCtxMethod;
    if ((getRequestsByReqCtxMethod = QueryGrpc.getRequestsByReqCtxMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRequestsByReqCtxMethod = QueryGrpc.getRequestsByReqCtxMethod) == null) {
          QueryGrpc.getRequestsByReqCtxMethod = getRequestsByReqCtxMethod =
              io.grpc.MethodDescriptor.<irismod.service.QueryOuterClass.QueryRequestsByReqCtxRequest, irismod.service.QueryOuterClass.QueryRequestsByReqCtxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RequestsByReqCtx"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryRequestsByReqCtxRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryRequestsByReqCtxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RequestsByReqCtx"))
              .build();
        }
      }
    }
    return getRequestsByReqCtxMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryResponseRequest,
      irismod.service.QueryOuterClass.QueryResponseResponse> getResponseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Response",
      requestType = irismod.service.QueryOuterClass.QueryResponseRequest.class,
      responseType = irismod.service.QueryOuterClass.QueryResponseResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryResponseRequest,
      irismod.service.QueryOuterClass.QueryResponseResponse> getResponseMethod() {
    io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryResponseRequest, irismod.service.QueryOuterClass.QueryResponseResponse> getResponseMethod;
    if ((getResponseMethod = QueryGrpc.getResponseMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getResponseMethod = QueryGrpc.getResponseMethod) == null) {
          QueryGrpc.getResponseMethod = getResponseMethod =
              io.grpc.MethodDescriptor.<irismod.service.QueryOuterClass.QueryResponseRequest, irismod.service.QueryOuterClass.QueryResponseResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Response"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryResponseRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryResponseResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Response"))
              .build();
        }
      }
    }
    return getResponseMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryResponsesRequest,
      irismod.service.QueryOuterClass.QueryResponsesResponse> getResponsesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Responses",
      requestType = irismod.service.QueryOuterClass.QueryResponsesRequest.class,
      responseType = irismod.service.QueryOuterClass.QueryResponsesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryResponsesRequest,
      irismod.service.QueryOuterClass.QueryResponsesResponse> getResponsesMethod() {
    io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryResponsesRequest, irismod.service.QueryOuterClass.QueryResponsesResponse> getResponsesMethod;
    if ((getResponsesMethod = QueryGrpc.getResponsesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getResponsesMethod = QueryGrpc.getResponsesMethod) == null) {
          QueryGrpc.getResponsesMethod = getResponsesMethod =
              io.grpc.MethodDescriptor.<irismod.service.QueryOuterClass.QueryResponsesRequest, irismod.service.QueryOuterClass.QueryResponsesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Responses"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryResponsesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryResponsesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Responses"))
              .build();
        }
      }
    }
    return getResponsesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryEarnedFeesRequest,
      irismod.service.QueryOuterClass.QueryEarnedFeesResponse> getEarnedFeesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EarnedFees",
      requestType = irismod.service.QueryOuterClass.QueryEarnedFeesRequest.class,
      responseType = irismod.service.QueryOuterClass.QueryEarnedFeesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryEarnedFeesRequest,
      irismod.service.QueryOuterClass.QueryEarnedFeesResponse> getEarnedFeesMethod() {
    io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryEarnedFeesRequest, irismod.service.QueryOuterClass.QueryEarnedFeesResponse> getEarnedFeesMethod;
    if ((getEarnedFeesMethod = QueryGrpc.getEarnedFeesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEarnedFeesMethod = QueryGrpc.getEarnedFeesMethod) == null) {
          QueryGrpc.getEarnedFeesMethod = getEarnedFeesMethod =
              io.grpc.MethodDescriptor.<irismod.service.QueryOuterClass.QueryEarnedFeesRequest, irismod.service.QueryOuterClass.QueryEarnedFeesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EarnedFees"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryEarnedFeesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryEarnedFeesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EarnedFees"))
              .build();
        }
      }
    }
    return getEarnedFeesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QuerySchemaRequest,
      irismod.service.QueryOuterClass.QuerySchemaResponse> getSchemaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Schema",
      requestType = irismod.service.QueryOuterClass.QuerySchemaRequest.class,
      responseType = irismod.service.QueryOuterClass.QuerySchemaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QuerySchemaRequest,
      irismod.service.QueryOuterClass.QuerySchemaResponse> getSchemaMethod() {
    io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QuerySchemaRequest, irismod.service.QueryOuterClass.QuerySchemaResponse> getSchemaMethod;
    if ((getSchemaMethod = QueryGrpc.getSchemaMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSchemaMethod = QueryGrpc.getSchemaMethod) == null) {
          QueryGrpc.getSchemaMethod = getSchemaMethod =
              io.grpc.MethodDescriptor.<irismod.service.QueryOuterClass.QuerySchemaRequest, irismod.service.QueryOuterClass.QuerySchemaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Schema"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QuerySchemaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QuerySchemaResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Schema"))
              .build();
        }
      }
    }
    return getSchemaMethod;
  }

  private static volatile io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryParamsRequest,
      irismod.service.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = irismod.service.QueryOuterClass.QueryParamsRequest.class,
      responseType = irismod.service.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryParamsRequest,
      irismod.service.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<irismod.service.QueryOuterClass.QueryParamsRequest, irismod.service.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<irismod.service.QueryOuterClass.QueryParamsRequest, irismod.service.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  irismod.service.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
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
   * Query creates service with iservice as rpc
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Definition returns service definition
     * </pre>
     */
    public void definition(irismod.service.QueryOuterClass.QueryDefinitionRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryDefinitionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDefinitionMethod(), responseObserver);
    }

    /**
     * <pre>
     * Binding returns service Binding with service name and provider
     * </pre>
     */
    public void binding(irismod.service.QueryOuterClass.QueryBindingRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryBindingResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBindingMethod(), responseObserver);
    }

    /**
     * <pre>
     * Bindings returns all service Bindings with service name and owner
     * </pre>
     */
    public void bindings(irismod.service.QueryOuterClass.QueryBindingsRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryBindingsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBindingsMethod(), responseObserver);
    }

    /**
     * <pre>
     * WithdrawAddress returns the withdraw address of the binding owner
     * </pre>
     */
    public void withdrawAddress(irismod.service.QueryOuterClass.QueryWithdrawAddressRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryWithdrawAddressResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawAddressMethod(), responseObserver);
    }

    /**
     * <pre>
     * RequestContext returns the request context
     * </pre>
     */
    public void requestContext(irismod.service.QueryOuterClass.QueryRequestContextRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryRequestContextResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRequestContextMethod(), responseObserver);
    }

    /**
     * <pre>
     * Request returns the request
     * </pre>
     */
    public void request(irismod.service.QueryOuterClass.QueryRequestRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryRequestResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRequestMethod(), responseObserver);
    }

    /**
     * <pre>
     * Request returns all requests of one service with provider
     * </pre>
     */
    public void requests(irismod.service.QueryOuterClass.QueryRequestsRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryRequestsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRequestsMethod(), responseObserver);
    }

    /**
     * <pre>
     * RequestsByReqCtx returns all requests of one service call batch
     * </pre>
     */
    public void requestsByReqCtx(irismod.service.QueryOuterClass.QueryRequestsByReqCtxRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryRequestsByReqCtxResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRequestsByReqCtxMethod(), responseObserver);
    }

    /**
     * <pre>
     * Response returns the response of request
     * </pre>
     */
    public void response(irismod.service.QueryOuterClass.QueryResponseRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryResponseResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getResponseMethod(), responseObserver);
    }

    /**
     * <pre>
     * Responses returns all responses of one service call batch
     * </pre>
     */
    public void responses(irismod.service.QueryOuterClass.QueryResponsesRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryResponsesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getResponsesMethod(), responseObserver);
    }

    /**
     * <pre>
     * EarnedFees returns the earned service fee of one provider
     * </pre>
     */
    public void earnedFees(irismod.service.QueryOuterClass.QueryEarnedFeesRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryEarnedFeesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEarnedFeesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Schema returns the schema
     * </pre>
     */
    public void schema(irismod.service.QueryOuterClass.QuerySchemaRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QuerySchemaResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSchemaMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries the service parameters
     * </pre>
     */
    public void params(irismod.service.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDefinitionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.service.QueryOuterClass.QueryDefinitionRequest,
                irismod.service.QueryOuterClass.QueryDefinitionResponse>(
                  this, METHODID_DEFINITION)))
          .addMethod(
            getBindingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.service.QueryOuterClass.QueryBindingRequest,
                irismod.service.QueryOuterClass.QueryBindingResponse>(
                  this, METHODID_BINDING)))
          .addMethod(
            getBindingsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.service.QueryOuterClass.QueryBindingsRequest,
                irismod.service.QueryOuterClass.QueryBindingsResponse>(
                  this, METHODID_BINDINGS)))
          .addMethod(
            getWithdrawAddressMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.service.QueryOuterClass.QueryWithdrawAddressRequest,
                irismod.service.QueryOuterClass.QueryWithdrawAddressResponse>(
                  this, METHODID_WITHDRAW_ADDRESS)))
          .addMethod(
            getRequestContextMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.service.QueryOuterClass.QueryRequestContextRequest,
                irismod.service.QueryOuterClass.QueryRequestContextResponse>(
                  this, METHODID_REQUEST_CONTEXT)))
          .addMethod(
            getRequestMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.service.QueryOuterClass.QueryRequestRequest,
                irismod.service.QueryOuterClass.QueryRequestResponse>(
                  this, METHODID_REQUEST)))
          .addMethod(
            getRequestsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.service.QueryOuterClass.QueryRequestsRequest,
                irismod.service.QueryOuterClass.QueryRequestsResponse>(
                  this, METHODID_REQUESTS)))
          .addMethod(
            getRequestsByReqCtxMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.service.QueryOuterClass.QueryRequestsByReqCtxRequest,
                irismod.service.QueryOuterClass.QueryRequestsByReqCtxResponse>(
                  this, METHODID_REQUESTS_BY_REQ_CTX)))
          .addMethod(
            getResponseMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.service.QueryOuterClass.QueryResponseRequest,
                irismod.service.QueryOuterClass.QueryResponseResponse>(
                  this, METHODID_RESPONSE)))
          .addMethod(
            getResponsesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.service.QueryOuterClass.QueryResponsesRequest,
                irismod.service.QueryOuterClass.QueryResponsesResponse>(
                  this, METHODID_RESPONSES)))
          .addMethod(
            getEarnedFeesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.service.QueryOuterClass.QueryEarnedFeesRequest,
                irismod.service.QueryOuterClass.QueryEarnedFeesResponse>(
                  this, METHODID_EARNED_FEES)))
          .addMethod(
            getSchemaMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.service.QueryOuterClass.QuerySchemaRequest,
                irismod.service.QueryOuterClass.QuerySchemaResponse>(
                  this, METHODID_SCHEMA)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                irismod.service.QueryOuterClass.QueryParamsRequest,
                irismod.service.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .build();
    }
  }

  /**
   * <pre>
   * Query creates service with iservice as rpc
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
     * Definition returns service definition
     * </pre>
     */
    public void definition(irismod.service.QueryOuterClass.QueryDefinitionRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryDefinitionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDefinitionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Binding returns service Binding with service name and provider
     * </pre>
     */
    public void binding(irismod.service.QueryOuterClass.QueryBindingRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryBindingResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBindingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Bindings returns all service Bindings with service name and owner
     * </pre>
     */
    public void bindings(irismod.service.QueryOuterClass.QueryBindingsRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryBindingsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBindingsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WithdrawAddress returns the withdraw address of the binding owner
     * </pre>
     */
    public void withdrawAddress(irismod.service.QueryOuterClass.QueryWithdrawAddressRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryWithdrawAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RequestContext returns the request context
     * </pre>
     */
    public void requestContext(irismod.service.QueryOuterClass.QueryRequestContextRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryRequestContextResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRequestContextMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Request returns the request
     * </pre>
     */
    public void request(irismod.service.QueryOuterClass.QueryRequestRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryRequestResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Request returns all requests of one service with provider
     * </pre>
     */
    public void requests(irismod.service.QueryOuterClass.QueryRequestsRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryRequestsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRequestsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RequestsByReqCtx returns all requests of one service call batch
     * </pre>
     */
    public void requestsByReqCtx(irismod.service.QueryOuterClass.QueryRequestsByReqCtxRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryRequestsByReqCtxResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRequestsByReqCtxMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Response returns the response of request
     * </pre>
     */
    public void response(irismod.service.QueryOuterClass.QueryResponseRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryResponseResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getResponseMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Responses returns all responses of one service call batch
     * </pre>
     */
    public void responses(irismod.service.QueryOuterClass.QueryResponsesRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryResponsesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getResponsesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EarnedFees returns the earned service fee of one provider
     * </pre>
     */
    public void earnedFees(irismod.service.QueryOuterClass.QueryEarnedFeesRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryEarnedFeesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEarnedFeesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Schema returns the schema
     * </pre>
     */
    public void schema(irismod.service.QueryOuterClass.QuerySchemaRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QuerySchemaResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSchemaMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries the service parameters
     * </pre>
     */
    public void params(irismod.service.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query creates service with iservice as rpc
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
     * Definition returns service definition
     * </pre>
     */
    public irismod.service.QueryOuterClass.QueryDefinitionResponse definition(irismod.service.QueryOuterClass.QueryDefinitionRequest request) {
      return blockingUnaryCall(
          getChannel(), getDefinitionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Binding returns service Binding with service name and provider
     * </pre>
     */
    public irismod.service.QueryOuterClass.QueryBindingResponse binding(irismod.service.QueryOuterClass.QueryBindingRequest request) {
      return blockingUnaryCall(
          getChannel(), getBindingMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Bindings returns all service Bindings with service name and owner
     * </pre>
     */
    public irismod.service.QueryOuterClass.QueryBindingsResponse bindings(irismod.service.QueryOuterClass.QueryBindingsRequest request) {
      return blockingUnaryCall(
          getChannel(), getBindingsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WithdrawAddress returns the withdraw address of the binding owner
     * </pre>
     */
    public irismod.service.QueryOuterClass.QueryWithdrawAddressResponse withdrawAddress(irismod.service.QueryOuterClass.QueryWithdrawAddressRequest request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawAddressMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RequestContext returns the request context
     * </pre>
     */
    public irismod.service.QueryOuterClass.QueryRequestContextResponse requestContext(irismod.service.QueryOuterClass.QueryRequestContextRequest request) {
      return blockingUnaryCall(
          getChannel(), getRequestContextMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Request returns the request
     * </pre>
     */
    public irismod.service.QueryOuterClass.QueryRequestResponse request(irismod.service.QueryOuterClass.QueryRequestRequest request) {
      return blockingUnaryCall(
          getChannel(), getRequestMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Request returns all requests of one service with provider
     * </pre>
     */
    public irismod.service.QueryOuterClass.QueryRequestsResponse requests(irismod.service.QueryOuterClass.QueryRequestsRequest request) {
      return blockingUnaryCall(
          getChannel(), getRequestsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RequestsByReqCtx returns all requests of one service call batch
     * </pre>
     */
    public irismod.service.QueryOuterClass.QueryRequestsByReqCtxResponse requestsByReqCtx(irismod.service.QueryOuterClass.QueryRequestsByReqCtxRequest request) {
      return blockingUnaryCall(
          getChannel(), getRequestsByReqCtxMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Response returns the response of request
     * </pre>
     */
    public irismod.service.QueryOuterClass.QueryResponseResponse response(irismod.service.QueryOuterClass.QueryResponseRequest request) {
      return blockingUnaryCall(
          getChannel(), getResponseMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Responses returns all responses of one service call batch
     * </pre>
     */
    public irismod.service.QueryOuterClass.QueryResponsesResponse responses(irismod.service.QueryOuterClass.QueryResponsesRequest request) {
      return blockingUnaryCall(
          getChannel(), getResponsesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EarnedFees returns the earned service fee of one provider
     * </pre>
     */
    public irismod.service.QueryOuterClass.QueryEarnedFeesResponse earnedFees(irismod.service.QueryOuterClass.QueryEarnedFeesRequest request) {
      return blockingUnaryCall(
          getChannel(), getEarnedFeesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Schema returns the schema
     * </pre>
     */
    public irismod.service.QueryOuterClass.QuerySchemaResponse schema(irismod.service.QueryOuterClass.QuerySchemaRequest request) {
      return blockingUnaryCall(
          getChannel(), getSchemaMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries the service parameters
     * </pre>
     */
    public irismod.service.QueryOuterClass.QueryParamsResponse params(irismod.service.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query creates service with iservice as rpc
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
     * Definition returns service definition
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.service.QueryOuterClass.QueryDefinitionResponse> definition(
        irismod.service.QueryOuterClass.QueryDefinitionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDefinitionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Binding returns service Binding with service name and provider
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.service.QueryOuterClass.QueryBindingResponse> binding(
        irismod.service.QueryOuterClass.QueryBindingRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBindingMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Bindings returns all service Bindings with service name and owner
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.service.QueryOuterClass.QueryBindingsResponse> bindings(
        irismod.service.QueryOuterClass.QueryBindingsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBindingsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WithdrawAddress returns the withdraw address of the binding owner
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.service.QueryOuterClass.QueryWithdrawAddressResponse> withdrawAddress(
        irismod.service.QueryOuterClass.QueryWithdrawAddressRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawAddressMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RequestContext returns the request context
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.service.QueryOuterClass.QueryRequestContextResponse> requestContext(
        irismod.service.QueryOuterClass.QueryRequestContextRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRequestContextMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Request returns the request
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.service.QueryOuterClass.QueryRequestResponse> request(
        irismod.service.QueryOuterClass.QueryRequestRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRequestMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Request returns all requests of one service with provider
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.service.QueryOuterClass.QueryRequestsResponse> requests(
        irismod.service.QueryOuterClass.QueryRequestsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRequestsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RequestsByReqCtx returns all requests of one service call batch
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.service.QueryOuterClass.QueryRequestsByReqCtxResponse> requestsByReqCtx(
        irismod.service.QueryOuterClass.QueryRequestsByReqCtxRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRequestsByReqCtxMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Response returns the response of request
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.service.QueryOuterClass.QueryResponseResponse> response(
        irismod.service.QueryOuterClass.QueryResponseRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getResponseMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Responses returns all responses of one service call batch
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.service.QueryOuterClass.QueryResponsesResponse> responses(
        irismod.service.QueryOuterClass.QueryResponsesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getResponsesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EarnedFees returns the earned service fee of one provider
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.service.QueryOuterClass.QueryEarnedFeesResponse> earnedFees(
        irismod.service.QueryOuterClass.QueryEarnedFeesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEarnedFeesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Schema returns the schema
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.service.QueryOuterClass.QuerySchemaResponse> schema(
        irismod.service.QueryOuterClass.QuerySchemaRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSchemaMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries the service parameters
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<irismod.service.QueryOuterClass.QueryParamsResponse> params(
        irismod.service.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DEFINITION = 0;
  private static final int METHODID_BINDING = 1;
  private static final int METHODID_BINDINGS = 2;
  private static final int METHODID_WITHDRAW_ADDRESS = 3;
  private static final int METHODID_REQUEST_CONTEXT = 4;
  private static final int METHODID_REQUEST = 5;
  private static final int METHODID_REQUESTS = 6;
  private static final int METHODID_REQUESTS_BY_REQ_CTX = 7;
  private static final int METHODID_RESPONSE = 8;
  private static final int METHODID_RESPONSES = 9;
  private static final int METHODID_EARNED_FEES = 10;
  private static final int METHODID_SCHEMA = 11;
  private static final int METHODID_PARAMS = 12;

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
        case METHODID_DEFINITION:
          serviceImpl.definition((irismod.service.QueryOuterClass.QueryDefinitionRequest) request,
              (io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryDefinitionResponse>) responseObserver);
          break;
        case METHODID_BINDING:
          serviceImpl.binding((irismod.service.QueryOuterClass.QueryBindingRequest) request,
              (io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryBindingResponse>) responseObserver);
          break;
        case METHODID_BINDINGS:
          serviceImpl.bindings((irismod.service.QueryOuterClass.QueryBindingsRequest) request,
              (io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryBindingsResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW_ADDRESS:
          serviceImpl.withdrawAddress((irismod.service.QueryOuterClass.QueryWithdrawAddressRequest) request,
              (io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryWithdrawAddressResponse>) responseObserver);
          break;
        case METHODID_REQUEST_CONTEXT:
          serviceImpl.requestContext((irismod.service.QueryOuterClass.QueryRequestContextRequest) request,
              (io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryRequestContextResponse>) responseObserver);
          break;
        case METHODID_REQUEST:
          serviceImpl.request((irismod.service.QueryOuterClass.QueryRequestRequest) request,
              (io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryRequestResponse>) responseObserver);
          break;
        case METHODID_REQUESTS:
          serviceImpl.requests((irismod.service.QueryOuterClass.QueryRequestsRequest) request,
              (io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryRequestsResponse>) responseObserver);
          break;
        case METHODID_REQUESTS_BY_REQ_CTX:
          serviceImpl.requestsByReqCtx((irismod.service.QueryOuterClass.QueryRequestsByReqCtxRequest) request,
              (io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryRequestsByReqCtxResponse>) responseObserver);
          break;
        case METHODID_RESPONSE:
          serviceImpl.response((irismod.service.QueryOuterClass.QueryResponseRequest) request,
              (io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryResponseResponse>) responseObserver);
          break;
        case METHODID_RESPONSES:
          serviceImpl.responses((irismod.service.QueryOuterClass.QueryResponsesRequest) request,
              (io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryResponsesResponse>) responseObserver);
          break;
        case METHODID_EARNED_FEES:
          serviceImpl.earnedFees((irismod.service.QueryOuterClass.QueryEarnedFeesRequest) request,
              (io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryEarnedFeesResponse>) responseObserver);
          break;
        case METHODID_SCHEMA:
          serviceImpl.schema((irismod.service.QueryOuterClass.QuerySchemaRequest) request,
              (io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QuerySchemaResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((irismod.service.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<irismod.service.QueryOuterClass.QueryParamsResponse>) responseObserver);
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
      return irismod.service.QueryOuterClass.getDescriptor();
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
              .addMethod(getDefinitionMethod())
              .addMethod(getBindingMethod())
              .addMethod(getBindingsMethod())
              .addMethod(getWithdrawAddressMethod())
              .addMethod(getRequestContextMethod())
              .addMethod(getRequestMethod())
              .addMethod(getRequestsMethod())
              .addMethod(getRequestsByReqCtxMethod())
              .addMethod(getResponseMethod())
              .addMethod(getResponsesMethod())
              .addMethod(getEarnedFeesMethod())
              .addMethod(getSchemaMethod())
              .addMethod(getParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
