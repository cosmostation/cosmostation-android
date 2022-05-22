package gravity.v1;

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
    comments = "Source: gravity/v1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "gravity.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.ParamsRequest,
      gravity.v1.QueryOuterClass.ParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = gravity.v1.QueryOuterClass.ParamsRequest.class,
      responseType = gravity.v1.QueryOuterClass.ParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.ParamsRequest,
      gravity.v1.QueryOuterClass.ParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.ParamsRequest, gravity.v1.QueryOuterClass.ParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.ParamsRequest, gravity.v1.QueryOuterClass.ParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.ParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.ParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.SignerSetTxRequest,
      gravity.v1.QueryOuterClass.SignerSetTxResponse> getSignerSetTxMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SignerSetTx",
      requestType = gravity.v1.QueryOuterClass.SignerSetTxRequest.class,
      responseType = gravity.v1.QueryOuterClass.SignerSetTxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.SignerSetTxRequest,
      gravity.v1.QueryOuterClass.SignerSetTxResponse> getSignerSetTxMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.SignerSetTxRequest, gravity.v1.QueryOuterClass.SignerSetTxResponse> getSignerSetTxMethod;
    if ((getSignerSetTxMethod = QueryGrpc.getSignerSetTxMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSignerSetTxMethod = QueryGrpc.getSignerSetTxMethod) == null) {
          QueryGrpc.getSignerSetTxMethod = getSignerSetTxMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.SignerSetTxRequest, gravity.v1.QueryOuterClass.SignerSetTxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SignerSetTx"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.SignerSetTxRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.SignerSetTxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SignerSetTx"))
              .build();
        }
      }
    }
    return getSignerSetTxMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.LatestSignerSetTxRequest,
      gravity.v1.QueryOuterClass.SignerSetTxResponse> getLatestSignerSetTxMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LatestSignerSetTx",
      requestType = gravity.v1.QueryOuterClass.LatestSignerSetTxRequest.class,
      responseType = gravity.v1.QueryOuterClass.SignerSetTxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.LatestSignerSetTxRequest,
      gravity.v1.QueryOuterClass.SignerSetTxResponse> getLatestSignerSetTxMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.LatestSignerSetTxRequest, gravity.v1.QueryOuterClass.SignerSetTxResponse> getLatestSignerSetTxMethod;
    if ((getLatestSignerSetTxMethod = QueryGrpc.getLatestSignerSetTxMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLatestSignerSetTxMethod = QueryGrpc.getLatestSignerSetTxMethod) == null) {
          QueryGrpc.getLatestSignerSetTxMethod = getLatestSignerSetTxMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.LatestSignerSetTxRequest, gravity.v1.QueryOuterClass.SignerSetTxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LatestSignerSetTx"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.LatestSignerSetTxRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.SignerSetTxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LatestSignerSetTx"))
              .build();
        }
      }
    }
    return getLatestSignerSetTxMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.BatchTxRequest,
      gravity.v1.QueryOuterClass.BatchTxResponse> getBatchTxMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchTx",
      requestType = gravity.v1.QueryOuterClass.BatchTxRequest.class,
      responseType = gravity.v1.QueryOuterClass.BatchTxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.BatchTxRequest,
      gravity.v1.QueryOuterClass.BatchTxResponse> getBatchTxMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.BatchTxRequest, gravity.v1.QueryOuterClass.BatchTxResponse> getBatchTxMethod;
    if ((getBatchTxMethod = QueryGrpc.getBatchTxMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchTxMethod = QueryGrpc.getBatchTxMethod) == null) {
          QueryGrpc.getBatchTxMethod = getBatchTxMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.BatchTxRequest, gravity.v1.QueryOuterClass.BatchTxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchTx"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.BatchTxRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.BatchTxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchTx"))
              .build();
        }
      }
    }
    return getBatchTxMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.ContractCallTxRequest,
      gravity.v1.QueryOuterClass.ContractCallTxResponse> getContractCallTxMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ContractCallTx",
      requestType = gravity.v1.QueryOuterClass.ContractCallTxRequest.class,
      responseType = gravity.v1.QueryOuterClass.ContractCallTxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.ContractCallTxRequest,
      gravity.v1.QueryOuterClass.ContractCallTxResponse> getContractCallTxMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.ContractCallTxRequest, gravity.v1.QueryOuterClass.ContractCallTxResponse> getContractCallTxMethod;
    if ((getContractCallTxMethod = QueryGrpc.getContractCallTxMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getContractCallTxMethod = QueryGrpc.getContractCallTxMethod) == null) {
          QueryGrpc.getContractCallTxMethod = getContractCallTxMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.ContractCallTxRequest, gravity.v1.QueryOuterClass.ContractCallTxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ContractCallTx"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.ContractCallTxRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.ContractCallTxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ContractCallTx"))
              .build();
        }
      }
    }
    return getContractCallTxMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.SignerSetTxsRequest,
      gravity.v1.QueryOuterClass.SignerSetTxsResponse> getSignerSetTxsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SignerSetTxs",
      requestType = gravity.v1.QueryOuterClass.SignerSetTxsRequest.class,
      responseType = gravity.v1.QueryOuterClass.SignerSetTxsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.SignerSetTxsRequest,
      gravity.v1.QueryOuterClass.SignerSetTxsResponse> getSignerSetTxsMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.SignerSetTxsRequest, gravity.v1.QueryOuterClass.SignerSetTxsResponse> getSignerSetTxsMethod;
    if ((getSignerSetTxsMethod = QueryGrpc.getSignerSetTxsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSignerSetTxsMethod = QueryGrpc.getSignerSetTxsMethod) == null) {
          QueryGrpc.getSignerSetTxsMethod = getSignerSetTxsMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.SignerSetTxsRequest, gravity.v1.QueryOuterClass.SignerSetTxsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SignerSetTxs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.SignerSetTxsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.SignerSetTxsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SignerSetTxs"))
              .build();
        }
      }
    }
    return getSignerSetTxsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.BatchTxsRequest,
      gravity.v1.QueryOuterClass.BatchTxsResponse> getBatchTxsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchTxs",
      requestType = gravity.v1.QueryOuterClass.BatchTxsRequest.class,
      responseType = gravity.v1.QueryOuterClass.BatchTxsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.BatchTxsRequest,
      gravity.v1.QueryOuterClass.BatchTxsResponse> getBatchTxsMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.BatchTxsRequest, gravity.v1.QueryOuterClass.BatchTxsResponse> getBatchTxsMethod;
    if ((getBatchTxsMethod = QueryGrpc.getBatchTxsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchTxsMethod = QueryGrpc.getBatchTxsMethod) == null) {
          QueryGrpc.getBatchTxsMethod = getBatchTxsMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.BatchTxsRequest, gravity.v1.QueryOuterClass.BatchTxsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchTxs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.BatchTxsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.BatchTxsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchTxs"))
              .build();
        }
      }
    }
    return getBatchTxsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.ContractCallTxsRequest,
      gravity.v1.QueryOuterClass.ContractCallTxsResponse> getContractCallTxsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ContractCallTxs",
      requestType = gravity.v1.QueryOuterClass.ContractCallTxsRequest.class,
      responseType = gravity.v1.QueryOuterClass.ContractCallTxsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.ContractCallTxsRequest,
      gravity.v1.QueryOuterClass.ContractCallTxsResponse> getContractCallTxsMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.ContractCallTxsRequest, gravity.v1.QueryOuterClass.ContractCallTxsResponse> getContractCallTxsMethod;
    if ((getContractCallTxsMethod = QueryGrpc.getContractCallTxsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getContractCallTxsMethod = QueryGrpc.getContractCallTxsMethod) == null) {
          QueryGrpc.getContractCallTxsMethod = getContractCallTxsMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.ContractCallTxsRequest, gravity.v1.QueryOuterClass.ContractCallTxsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ContractCallTxs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.ContractCallTxsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.ContractCallTxsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ContractCallTxs"))
              .build();
        }
      }
    }
    return getContractCallTxsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.SignerSetTxConfirmationsRequest,
      gravity.v1.QueryOuterClass.SignerSetTxConfirmationsResponse> getSignerSetTxConfirmationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SignerSetTxConfirmations",
      requestType = gravity.v1.QueryOuterClass.SignerSetTxConfirmationsRequest.class,
      responseType = gravity.v1.QueryOuterClass.SignerSetTxConfirmationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.SignerSetTxConfirmationsRequest,
      gravity.v1.QueryOuterClass.SignerSetTxConfirmationsResponse> getSignerSetTxConfirmationsMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.SignerSetTxConfirmationsRequest, gravity.v1.QueryOuterClass.SignerSetTxConfirmationsResponse> getSignerSetTxConfirmationsMethod;
    if ((getSignerSetTxConfirmationsMethod = QueryGrpc.getSignerSetTxConfirmationsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSignerSetTxConfirmationsMethod = QueryGrpc.getSignerSetTxConfirmationsMethod) == null) {
          QueryGrpc.getSignerSetTxConfirmationsMethod = getSignerSetTxConfirmationsMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.SignerSetTxConfirmationsRequest, gravity.v1.QueryOuterClass.SignerSetTxConfirmationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SignerSetTxConfirmations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.SignerSetTxConfirmationsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.SignerSetTxConfirmationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SignerSetTxConfirmations"))
              .build();
        }
      }
    }
    return getSignerSetTxConfirmationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.BatchTxConfirmationsRequest,
      gravity.v1.QueryOuterClass.BatchTxConfirmationsResponse> getBatchTxConfirmationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchTxConfirmations",
      requestType = gravity.v1.QueryOuterClass.BatchTxConfirmationsRequest.class,
      responseType = gravity.v1.QueryOuterClass.BatchTxConfirmationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.BatchTxConfirmationsRequest,
      gravity.v1.QueryOuterClass.BatchTxConfirmationsResponse> getBatchTxConfirmationsMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.BatchTxConfirmationsRequest, gravity.v1.QueryOuterClass.BatchTxConfirmationsResponse> getBatchTxConfirmationsMethod;
    if ((getBatchTxConfirmationsMethod = QueryGrpc.getBatchTxConfirmationsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchTxConfirmationsMethod = QueryGrpc.getBatchTxConfirmationsMethod) == null) {
          QueryGrpc.getBatchTxConfirmationsMethod = getBatchTxConfirmationsMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.BatchTxConfirmationsRequest, gravity.v1.QueryOuterClass.BatchTxConfirmationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchTxConfirmations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.BatchTxConfirmationsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.BatchTxConfirmationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchTxConfirmations"))
              .build();
        }
      }
    }
    return getBatchTxConfirmationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.ContractCallTxConfirmationsRequest,
      gravity.v1.QueryOuterClass.ContractCallTxConfirmationsResponse> getContractCallTxConfirmationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ContractCallTxConfirmations",
      requestType = gravity.v1.QueryOuterClass.ContractCallTxConfirmationsRequest.class,
      responseType = gravity.v1.QueryOuterClass.ContractCallTxConfirmationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.ContractCallTxConfirmationsRequest,
      gravity.v1.QueryOuterClass.ContractCallTxConfirmationsResponse> getContractCallTxConfirmationsMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.ContractCallTxConfirmationsRequest, gravity.v1.QueryOuterClass.ContractCallTxConfirmationsResponse> getContractCallTxConfirmationsMethod;
    if ((getContractCallTxConfirmationsMethod = QueryGrpc.getContractCallTxConfirmationsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getContractCallTxConfirmationsMethod = QueryGrpc.getContractCallTxConfirmationsMethod) == null) {
          QueryGrpc.getContractCallTxConfirmationsMethod = getContractCallTxConfirmationsMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.ContractCallTxConfirmationsRequest, gravity.v1.QueryOuterClass.ContractCallTxConfirmationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ContractCallTxConfirmations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.ContractCallTxConfirmationsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.ContractCallTxConfirmationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ContractCallTxConfirmations"))
              .build();
        }
      }
    }
    return getContractCallTxConfirmationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.UnsignedSignerSetTxsRequest,
      gravity.v1.QueryOuterClass.UnsignedSignerSetTxsResponse> getUnsignedSignerSetTxsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnsignedSignerSetTxs",
      requestType = gravity.v1.QueryOuterClass.UnsignedSignerSetTxsRequest.class,
      responseType = gravity.v1.QueryOuterClass.UnsignedSignerSetTxsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.UnsignedSignerSetTxsRequest,
      gravity.v1.QueryOuterClass.UnsignedSignerSetTxsResponse> getUnsignedSignerSetTxsMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.UnsignedSignerSetTxsRequest, gravity.v1.QueryOuterClass.UnsignedSignerSetTxsResponse> getUnsignedSignerSetTxsMethod;
    if ((getUnsignedSignerSetTxsMethod = QueryGrpc.getUnsignedSignerSetTxsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUnsignedSignerSetTxsMethod = QueryGrpc.getUnsignedSignerSetTxsMethod) == null) {
          QueryGrpc.getUnsignedSignerSetTxsMethod = getUnsignedSignerSetTxsMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.UnsignedSignerSetTxsRequest, gravity.v1.QueryOuterClass.UnsignedSignerSetTxsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnsignedSignerSetTxs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.UnsignedSignerSetTxsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.UnsignedSignerSetTxsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UnsignedSignerSetTxs"))
              .build();
        }
      }
    }
    return getUnsignedSignerSetTxsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.UnsignedBatchTxsRequest,
      gravity.v1.QueryOuterClass.UnsignedBatchTxsResponse> getUnsignedBatchTxsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnsignedBatchTxs",
      requestType = gravity.v1.QueryOuterClass.UnsignedBatchTxsRequest.class,
      responseType = gravity.v1.QueryOuterClass.UnsignedBatchTxsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.UnsignedBatchTxsRequest,
      gravity.v1.QueryOuterClass.UnsignedBatchTxsResponse> getUnsignedBatchTxsMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.UnsignedBatchTxsRequest, gravity.v1.QueryOuterClass.UnsignedBatchTxsResponse> getUnsignedBatchTxsMethod;
    if ((getUnsignedBatchTxsMethod = QueryGrpc.getUnsignedBatchTxsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUnsignedBatchTxsMethod = QueryGrpc.getUnsignedBatchTxsMethod) == null) {
          QueryGrpc.getUnsignedBatchTxsMethod = getUnsignedBatchTxsMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.UnsignedBatchTxsRequest, gravity.v1.QueryOuterClass.UnsignedBatchTxsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnsignedBatchTxs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.UnsignedBatchTxsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.UnsignedBatchTxsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UnsignedBatchTxs"))
              .build();
        }
      }
    }
    return getUnsignedBatchTxsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.UnsignedContractCallTxsRequest,
      gravity.v1.QueryOuterClass.UnsignedContractCallTxsResponse> getUnsignedContractCallTxsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnsignedContractCallTxs",
      requestType = gravity.v1.QueryOuterClass.UnsignedContractCallTxsRequest.class,
      responseType = gravity.v1.QueryOuterClass.UnsignedContractCallTxsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.UnsignedContractCallTxsRequest,
      gravity.v1.QueryOuterClass.UnsignedContractCallTxsResponse> getUnsignedContractCallTxsMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.UnsignedContractCallTxsRequest, gravity.v1.QueryOuterClass.UnsignedContractCallTxsResponse> getUnsignedContractCallTxsMethod;
    if ((getUnsignedContractCallTxsMethod = QueryGrpc.getUnsignedContractCallTxsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUnsignedContractCallTxsMethod = QueryGrpc.getUnsignedContractCallTxsMethod) == null) {
          QueryGrpc.getUnsignedContractCallTxsMethod = getUnsignedContractCallTxsMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.UnsignedContractCallTxsRequest, gravity.v1.QueryOuterClass.UnsignedContractCallTxsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnsignedContractCallTxs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.UnsignedContractCallTxsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.UnsignedContractCallTxsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UnsignedContractCallTxs"))
              .build();
        }
      }
    }
    return getUnsignedContractCallTxsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.LastSubmittedEthereumEventRequest,
      gravity.v1.QueryOuterClass.LastSubmittedEthereumEventResponse> getLastSubmittedEthereumEventMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LastSubmittedEthereumEvent",
      requestType = gravity.v1.QueryOuterClass.LastSubmittedEthereumEventRequest.class,
      responseType = gravity.v1.QueryOuterClass.LastSubmittedEthereumEventResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.LastSubmittedEthereumEventRequest,
      gravity.v1.QueryOuterClass.LastSubmittedEthereumEventResponse> getLastSubmittedEthereumEventMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.LastSubmittedEthereumEventRequest, gravity.v1.QueryOuterClass.LastSubmittedEthereumEventResponse> getLastSubmittedEthereumEventMethod;
    if ((getLastSubmittedEthereumEventMethod = QueryGrpc.getLastSubmittedEthereumEventMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getLastSubmittedEthereumEventMethod = QueryGrpc.getLastSubmittedEthereumEventMethod) == null) {
          QueryGrpc.getLastSubmittedEthereumEventMethod = getLastSubmittedEthereumEventMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.LastSubmittedEthereumEventRequest, gravity.v1.QueryOuterClass.LastSubmittedEthereumEventResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LastSubmittedEthereumEvent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.LastSubmittedEthereumEventRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.LastSubmittedEthereumEventResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("LastSubmittedEthereumEvent"))
              .build();
        }
      }
    }
    return getLastSubmittedEthereumEventMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.BatchTxFeesRequest,
      gravity.v1.QueryOuterClass.BatchTxFeesResponse> getBatchTxFeesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchTxFees",
      requestType = gravity.v1.QueryOuterClass.BatchTxFeesRequest.class,
      responseType = gravity.v1.QueryOuterClass.BatchTxFeesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.BatchTxFeesRequest,
      gravity.v1.QueryOuterClass.BatchTxFeesResponse> getBatchTxFeesMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.BatchTxFeesRequest, gravity.v1.QueryOuterClass.BatchTxFeesResponse> getBatchTxFeesMethod;
    if ((getBatchTxFeesMethod = QueryGrpc.getBatchTxFeesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchTxFeesMethod = QueryGrpc.getBatchTxFeesMethod) == null) {
          QueryGrpc.getBatchTxFeesMethod = getBatchTxFeesMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.BatchTxFeesRequest, gravity.v1.QueryOuterClass.BatchTxFeesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchTxFees"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.BatchTxFeesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.BatchTxFeesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchTxFees"))
              .build();
        }
      }
    }
    return getBatchTxFeesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.ERC20ToDenomRequest,
      gravity.v1.QueryOuterClass.ERC20ToDenomResponse> getERC20ToDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ERC20ToDenom",
      requestType = gravity.v1.QueryOuterClass.ERC20ToDenomRequest.class,
      responseType = gravity.v1.QueryOuterClass.ERC20ToDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.ERC20ToDenomRequest,
      gravity.v1.QueryOuterClass.ERC20ToDenomResponse> getERC20ToDenomMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.ERC20ToDenomRequest, gravity.v1.QueryOuterClass.ERC20ToDenomResponse> getERC20ToDenomMethod;
    if ((getERC20ToDenomMethod = QueryGrpc.getERC20ToDenomMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getERC20ToDenomMethod = QueryGrpc.getERC20ToDenomMethod) == null) {
          QueryGrpc.getERC20ToDenomMethod = getERC20ToDenomMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.ERC20ToDenomRequest, gravity.v1.QueryOuterClass.ERC20ToDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ERC20ToDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.ERC20ToDenomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.ERC20ToDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ERC20ToDenom"))
              .build();
        }
      }
    }
    return getERC20ToDenomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.DenomToERC20ParamsRequest,
      gravity.v1.QueryOuterClass.DenomToERC20ParamsResponse> getDenomToERC20ParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomToERC20Params",
      requestType = gravity.v1.QueryOuterClass.DenomToERC20ParamsRequest.class,
      responseType = gravity.v1.QueryOuterClass.DenomToERC20ParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.DenomToERC20ParamsRequest,
      gravity.v1.QueryOuterClass.DenomToERC20ParamsResponse> getDenomToERC20ParamsMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.DenomToERC20ParamsRequest, gravity.v1.QueryOuterClass.DenomToERC20ParamsResponse> getDenomToERC20ParamsMethod;
    if ((getDenomToERC20ParamsMethod = QueryGrpc.getDenomToERC20ParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomToERC20ParamsMethod = QueryGrpc.getDenomToERC20ParamsMethod) == null) {
          QueryGrpc.getDenomToERC20ParamsMethod = getDenomToERC20ParamsMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.DenomToERC20ParamsRequest, gravity.v1.QueryOuterClass.DenomToERC20ParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomToERC20Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.DenomToERC20ParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.DenomToERC20ParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomToERC20Params"))
              .build();
        }
      }
    }
    return getDenomToERC20ParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.DenomToERC20Request,
      gravity.v1.QueryOuterClass.DenomToERC20Response> getDenomToERC20Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DenomToERC20",
      requestType = gravity.v1.QueryOuterClass.DenomToERC20Request.class,
      responseType = gravity.v1.QueryOuterClass.DenomToERC20Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.DenomToERC20Request,
      gravity.v1.QueryOuterClass.DenomToERC20Response> getDenomToERC20Method() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.DenomToERC20Request, gravity.v1.QueryOuterClass.DenomToERC20Response> getDenomToERC20Method;
    if ((getDenomToERC20Method = QueryGrpc.getDenomToERC20Method) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDenomToERC20Method = QueryGrpc.getDenomToERC20Method) == null) {
          QueryGrpc.getDenomToERC20Method = getDenomToERC20Method =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.DenomToERC20Request, gravity.v1.QueryOuterClass.DenomToERC20Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DenomToERC20"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.DenomToERC20Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.DenomToERC20Response.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DenomToERC20"))
              .build();
        }
      }
    }
    return getDenomToERC20Method;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.BatchedSendToEthereumsRequest,
      gravity.v1.QueryOuterClass.BatchedSendToEthereumsResponse> getBatchedSendToEthereumsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchedSendToEthereums",
      requestType = gravity.v1.QueryOuterClass.BatchedSendToEthereumsRequest.class,
      responseType = gravity.v1.QueryOuterClass.BatchedSendToEthereumsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.BatchedSendToEthereumsRequest,
      gravity.v1.QueryOuterClass.BatchedSendToEthereumsResponse> getBatchedSendToEthereumsMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.BatchedSendToEthereumsRequest, gravity.v1.QueryOuterClass.BatchedSendToEthereumsResponse> getBatchedSendToEthereumsMethod;
    if ((getBatchedSendToEthereumsMethod = QueryGrpc.getBatchedSendToEthereumsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchedSendToEthereumsMethod = QueryGrpc.getBatchedSendToEthereumsMethod) == null) {
          QueryGrpc.getBatchedSendToEthereumsMethod = getBatchedSendToEthereumsMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.BatchedSendToEthereumsRequest, gravity.v1.QueryOuterClass.BatchedSendToEthereumsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchedSendToEthereums"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.BatchedSendToEthereumsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.BatchedSendToEthereumsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchedSendToEthereums"))
              .build();
        }
      }
    }
    return getBatchedSendToEthereumsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsRequest,
      gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsResponse> getUnbatchedSendToEthereumsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnbatchedSendToEthereums",
      requestType = gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsRequest.class,
      responseType = gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsRequest,
      gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsResponse> getUnbatchedSendToEthereumsMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsRequest, gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsResponse> getUnbatchedSendToEthereumsMethod;
    if ((getUnbatchedSendToEthereumsMethod = QueryGrpc.getUnbatchedSendToEthereumsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUnbatchedSendToEthereumsMethod = QueryGrpc.getUnbatchedSendToEthereumsMethod) == null) {
          QueryGrpc.getUnbatchedSendToEthereumsMethod = getUnbatchedSendToEthereumsMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsRequest, gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnbatchedSendToEthereums"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UnbatchedSendToEthereums"))
              .build();
        }
      }
    }
    return getUnbatchedSendToEthereumsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.DelegateKeysByValidatorRequest,
      gravity.v1.QueryOuterClass.DelegateKeysByValidatorResponse> getDelegateKeysByValidatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelegateKeysByValidator",
      requestType = gravity.v1.QueryOuterClass.DelegateKeysByValidatorRequest.class,
      responseType = gravity.v1.QueryOuterClass.DelegateKeysByValidatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.DelegateKeysByValidatorRequest,
      gravity.v1.QueryOuterClass.DelegateKeysByValidatorResponse> getDelegateKeysByValidatorMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.DelegateKeysByValidatorRequest, gravity.v1.QueryOuterClass.DelegateKeysByValidatorResponse> getDelegateKeysByValidatorMethod;
    if ((getDelegateKeysByValidatorMethod = QueryGrpc.getDelegateKeysByValidatorMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDelegateKeysByValidatorMethod = QueryGrpc.getDelegateKeysByValidatorMethod) == null) {
          QueryGrpc.getDelegateKeysByValidatorMethod = getDelegateKeysByValidatorMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.DelegateKeysByValidatorRequest, gravity.v1.QueryOuterClass.DelegateKeysByValidatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelegateKeysByValidator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.DelegateKeysByValidatorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.DelegateKeysByValidatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DelegateKeysByValidator"))
              .build();
        }
      }
    }
    return getDelegateKeysByValidatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerRequest,
      gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerResponse> getDelegateKeysByEthereumSignerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelegateKeysByEthereumSigner",
      requestType = gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerRequest.class,
      responseType = gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerRequest,
      gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerResponse> getDelegateKeysByEthereumSignerMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerRequest, gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerResponse> getDelegateKeysByEthereumSignerMethod;
    if ((getDelegateKeysByEthereumSignerMethod = QueryGrpc.getDelegateKeysByEthereumSignerMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDelegateKeysByEthereumSignerMethod = QueryGrpc.getDelegateKeysByEthereumSignerMethod) == null) {
          QueryGrpc.getDelegateKeysByEthereumSignerMethod = getDelegateKeysByEthereumSignerMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerRequest, gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelegateKeysByEthereumSigner"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DelegateKeysByEthereumSigner"))
              .build();
        }
      }
    }
    return getDelegateKeysByEthereumSignerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorRequest,
      gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorResponse> getDelegateKeysByOrchestratorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelegateKeysByOrchestrator",
      requestType = gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorRequest.class,
      responseType = gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorRequest,
      gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorResponse> getDelegateKeysByOrchestratorMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorRequest, gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorResponse> getDelegateKeysByOrchestratorMethod;
    if ((getDelegateKeysByOrchestratorMethod = QueryGrpc.getDelegateKeysByOrchestratorMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDelegateKeysByOrchestratorMethod = QueryGrpc.getDelegateKeysByOrchestratorMethod) == null) {
          QueryGrpc.getDelegateKeysByOrchestratorMethod = getDelegateKeysByOrchestratorMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorRequest, gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelegateKeysByOrchestrator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DelegateKeysByOrchestrator"))
              .build();
        }
      }
    }
    return getDelegateKeysByOrchestratorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.DelegateKeysRequest,
      gravity.v1.QueryOuterClass.DelegateKeysResponse> getDelegateKeysMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelegateKeys",
      requestType = gravity.v1.QueryOuterClass.DelegateKeysRequest.class,
      responseType = gravity.v1.QueryOuterClass.DelegateKeysResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.DelegateKeysRequest,
      gravity.v1.QueryOuterClass.DelegateKeysResponse> getDelegateKeysMethod() {
    io.grpc.MethodDescriptor<gravity.v1.QueryOuterClass.DelegateKeysRequest, gravity.v1.QueryOuterClass.DelegateKeysResponse> getDelegateKeysMethod;
    if ((getDelegateKeysMethod = QueryGrpc.getDelegateKeysMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDelegateKeysMethod = QueryGrpc.getDelegateKeysMethod) == null) {
          QueryGrpc.getDelegateKeysMethod = getDelegateKeysMethod =
              io.grpc.MethodDescriptor.<gravity.v1.QueryOuterClass.DelegateKeysRequest, gravity.v1.QueryOuterClass.DelegateKeysResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelegateKeys"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.DelegateKeysRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gravity.v1.QueryOuterClass.DelegateKeysResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DelegateKeys"))
              .build();
        }
      }
    }
    return getDelegateKeysMethod;
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
     * <pre>
     * Module parameters query
     * </pre>
     */
    public void params(gravity.v1.QueryOuterClass.ParamsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.ParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * get info on individual outgoing data
     * </pre>
     */
    public void signerSetTx(gravity.v1.QueryOuterClass.SignerSetTxRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.SignerSetTxResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSignerSetTxMethod(), responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get = "/gravity/v1/signer_set/latest";
     * </pre>
     */
    public void latestSignerSetTx(gravity.v1.QueryOuterClass.LatestSignerSetTxRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.SignerSetTxResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLatestSignerSetTxMethod(), responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/batch_txs/{token_contract}/{nonce}";
     * </pre>
     */
    public void batchTx(gravity.v1.QueryOuterClass.BatchTxRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.BatchTxResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBatchTxMethod(), responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/contract_call_txs/{invalidation_id}/{invalidation_nonce}";
     * </pre>
     */
    public void contractCallTx(gravity.v1.QueryOuterClass.ContractCallTxRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.ContractCallTxResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getContractCallTxMethod(), responseObserver);
    }

    /**
     * <pre>
     * get collections of outgoing traffic from the bridge
     * </pre>
     */
    public void signerSetTxs(gravity.v1.QueryOuterClass.SignerSetTxsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.SignerSetTxsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSignerSetTxsMethod(), responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get = "/gravity/v1/batch/batch_txs";
     * </pre>
     */
    public void batchTxs(gravity.v1.QueryOuterClass.BatchTxsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.BatchTxsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBatchTxsMethod(), responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get = "/gravity/v1/batch/contract_call_txs";
     * </pre>
     */
    public void contractCallTxs(gravity.v1.QueryOuterClass.ContractCallTxsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.ContractCallTxsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getContractCallTxsMethod(), responseObserver);
    }

    /**
     * <pre>
     * TODO: can/should we group these into one endpoint?
     * </pre>
     */
    public void signerSetTxConfirmations(gravity.v1.QueryOuterClass.SignerSetTxConfirmationsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.SignerSetTxConfirmationsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSignerSetTxConfirmationsMethod(), responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/batch_txs/ethereum_signatures";
     * </pre>
     */
    public void batchTxConfirmations(gravity.v1.QueryOuterClass.BatchTxConfirmationsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.BatchTxConfirmationsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBatchTxConfirmationsMethod(), responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/logic_calls/ethereum_signatures";
     * </pre>
     */
    public void contractCallTxConfirmations(gravity.v1.QueryOuterClass.ContractCallTxConfirmationsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.ContractCallTxConfirmationsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getContractCallTxConfirmationsMethod(), responseObserver);
    }

    /**
     * <pre>
     * pending ethereum signature queries for orchestrators to figure out which
     * signatures they are missing
     * TODO: can/should we group this into one endpoint?
     * </pre>
     */
    public void unsignedSignerSetTxs(gravity.v1.QueryOuterClass.UnsignedSignerSetTxsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.UnsignedSignerSetTxsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnsignedSignerSetTxsMethod(), responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get = "/gravity/v1/batches/{address}/pending";
     * </pre>
     */
    public void unsignedBatchTxs(gravity.v1.QueryOuterClass.UnsignedBatchTxsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.UnsignedBatchTxsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnsignedBatchTxsMethod(), responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/ContractCallTxs/{address}/pending";
     * </pre>
     */
    public void unsignedContractCallTxs(gravity.v1.QueryOuterClass.UnsignedContractCallTxsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.UnsignedContractCallTxsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnsignedContractCallTxsMethod(), responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/oracle/event_nonce/{address}";
     * </pre>
     */
    public void lastSubmittedEthereumEvent(gravity.v1.QueryOuterClass.LastSubmittedEthereumEventRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.LastSubmittedEthereumEventResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLastSubmittedEthereumEventMethod(), responseObserver);
    }

    /**
     * <pre>
     * Queries the fees for all pending batches, results are returned in sdk.Coin
     * (fee_amount_int)(contract_address) style
     * </pre>
     */
    public void batchTxFees(gravity.v1.QueryOuterClass.BatchTxFeesRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.BatchTxFeesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBatchTxFeesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Query for info about denoms tracked by gravity
     * </pre>
     */
    public void eRC20ToDenom(gravity.v1.QueryOuterClass.ERC20ToDenomRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.ERC20ToDenomResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getERC20ToDenomMethod(), responseObserver);
    }

    /**
     * <pre>
     * DenomToERC20Params implements a query that allows ERC-20 parameter information
     * to be retrieved by a Cosmos base denomination.
     * </pre>
     */
    public void denomToERC20Params(gravity.v1.QueryOuterClass.DenomToERC20ParamsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.DenomToERC20ParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDenomToERC20ParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Query for info about denoms tracked by gravity
     * </pre>
     */
    public void denomToERC20(gravity.v1.QueryOuterClass.DenomToERC20Request request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.DenomToERC20Response> responseObserver) {
      asyncUnimplementedUnaryCall(getDenomToERC20Method(), responseObserver);
    }

    /**
     * <pre>
     * Query for batch send to ethereums
     * </pre>
     */
    public void batchedSendToEthereums(gravity.v1.QueryOuterClass.BatchedSendToEthereumsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.BatchedSendToEthereumsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBatchedSendToEthereumsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Query for unbatched send to ethereums
     * </pre>
     */
    public void unbatchedSendToEthereums(gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnbatchedSendToEthereumsMethod(), responseObserver);
    }

    /**
     * <pre>
     * delegate keys
     * </pre>
     */
    public void delegateKeysByValidator(gravity.v1.QueryOuterClass.DelegateKeysByValidatorRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.DelegateKeysByValidatorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDelegateKeysByValidatorMethod(), responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/delegate_keys/ethereum/{ethereum_signer}";
     * </pre>
     */
    public void delegateKeysByEthereumSigner(gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDelegateKeysByEthereumSignerMethod(), responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/delegate_keys/orchestrator/{orchestrator}";
     * </pre>
     */
    public void delegateKeysByOrchestrator(gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDelegateKeysByOrchestratorMethod(), responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/delegate_keys";
     * </pre>
     */
    public void delegateKeys(gravity.v1.QueryOuterClass.DelegateKeysRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.DelegateKeysResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDelegateKeysMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.ParamsRequest,
                gravity.v1.QueryOuterClass.ParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getSignerSetTxMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.SignerSetTxRequest,
                gravity.v1.QueryOuterClass.SignerSetTxResponse>(
                  this, METHODID_SIGNER_SET_TX)))
          .addMethod(
            getLatestSignerSetTxMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.LatestSignerSetTxRequest,
                gravity.v1.QueryOuterClass.SignerSetTxResponse>(
                  this, METHODID_LATEST_SIGNER_SET_TX)))
          .addMethod(
            getBatchTxMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.BatchTxRequest,
                gravity.v1.QueryOuterClass.BatchTxResponse>(
                  this, METHODID_BATCH_TX)))
          .addMethod(
            getContractCallTxMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.ContractCallTxRequest,
                gravity.v1.QueryOuterClass.ContractCallTxResponse>(
                  this, METHODID_CONTRACT_CALL_TX)))
          .addMethod(
            getSignerSetTxsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.SignerSetTxsRequest,
                gravity.v1.QueryOuterClass.SignerSetTxsResponse>(
                  this, METHODID_SIGNER_SET_TXS)))
          .addMethod(
            getBatchTxsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.BatchTxsRequest,
                gravity.v1.QueryOuterClass.BatchTxsResponse>(
                  this, METHODID_BATCH_TXS)))
          .addMethod(
            getContractCallTxsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.ContractCallTxsRequest,
                gravity.v1.QueryOuterClass.ContractCallTxsResponse>(
                  this, METHODID_CONTRACT_CALL_TXS)))
          .addMethod(
            getSignerSetTxConfirmationsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.SignerSetTxConfirmationsRequest,
                gravity.v1.QueryOuterClass.SignerSetTxConfirmationsResponse>(
                  this, METHODID_SIGNER_SET_TX_CONFIRMATIONS)))
          .addMethod(
            getBatchTxConfirmationsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.BatchTxConfirmationsRequest,
                gravity.v1.QueryOuterClass.BatchTxConfirmationsResponse>(
                  this, METHODID_BATCH_TX_CONFIRMATIONS)))
          .addMethod(
            getContractCallTxConfirmationsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.ContractCallTxConfirmationsRequest,
                gravity.v1.QueryOuterClass.ContractCallTxConfirmationsResponse>(
                  this, METHODID_CONTRACT_CALL_TX_CONFIRMATIONS)))
          .addMethod(
            getUnsignedSignerSetTxsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.UnsignedSignerSetTxsRequest,
                gravity.v1.QueryOuterClass.UnsignedSignerSetTxsResponse>(
                  this, METHODID_UNSIGNED_SIGNER_SET_TXS)))
          .addMethod(
            getUnsignedBatchTxsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.UnsignedBatchTxsRequest,
                gravity.v1.QueryOuterClass.UnsignedBatchTxsResponse>(
                  this, METHODID_UNSIGNED_BATCH_TXS)))
          .addMethod(
            getUnsignedContractCallTxsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.UnsignedContractCallTxsRequest,
                gravity.v1.QueryOuterClass.UnsignedContractCallTxsResponse>(
                  this, METHODID_UNSIGNED_CONTRACT_CALL_TXS)))
          .addMethod(
            getLastSubmittedEthereumEventMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.LastSubmittedEthereumEventRequest,
                gravity.v1.QueryOuterClass.LastSubmittedEthereumEventResponse>(
                  this, METHODID_LAST_SUBMITTED_ETHEREUM_EVENT)))
          .addMethod(
            getBatchTxFeesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.BatchTxFeesRequest,
                gravity.v1.QueryOuterClass.BatchTxFeesResponse>(
                  this, METHODID_BATCH_TX_FEES)))
          .addMethod(
            getERC20ToDenomMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.ERC20ToDenomRequest,
                gravity.v1.QueryOuterClass.ERC20ToDenomResponse>(
                  this, METHODID_ERC20TO_DENOM)))
          .addMethod(
            getDenomToERC20ParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.DenomToERC20ParamsRequest,
                gravity.v1.QueryOuterClass.DenomToERC20ParamsResponse>(
                  this, METHODID_DENOM_TO_ERC20PARAMS)))
          .addMethod(
            getDenomToERC20Method(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.DenomToERC20Request,
                gravity.v1.QueryOuterClass.DenomToERC20Response>(
                  this, METHODID_DENOM_TO_ERC20)))
          .addMethod(
            getBatchedSendToEthereumsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.BatchedSendToEthereumsRequest,
                gravity.v1.QueryOuterClass.BatchedSendToEthereumsResponse>(
                  this, METHODID_BATCHED_SEND_TO_ETHEREUMS)))
          .addMethod(
            getUnbatchedSendToEthereumsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsRequest,
                gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsResponse>(
                  this, METHODID_UNBATCHED_SEND_TO_ETHEREUMS)))
          .addMethod(
            getDelegateKeysByValidatorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.DelegateKeysByValidatorRequest,
                gravity.v1.QueryOuterClass.DelegateKeysByValidatorResponse>(
                  this, METHODID_DELEGATE_KEYS_BY_VALIDATOR)))
          .addMethod(
            getDelegateKeysByEthereumSignerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerRequest,
                gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerResponse>(
                  this, METHODID_DELEGATE_KEYS_BY_ETHEREUM_SIGNER)))
          .addMethod(
            getDelegateKeysByOrchestratorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorRequest,
                gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorResponse>(
                  this, METHODID_DELEGATE_KEYS_BY_ORCHESTRATOR)))
          .addMethod(
            getDelegateKeysMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gravity.v1.QueryOuterClass.DelegateKeysRequest,
                gravity.v1.QueryOuterClass.DelegateKeysResponse>(
                  this, METHODID_DELEGATE_KEYS)))
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
     * <pre>
     * Module parameters query
     * </pre>
     */
    public void params(gravity.v1.QueryOuterClass.ParamsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.ParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get info on individual outgoing data
     * </pre>
     */
    public void signerSetTx(gravity.v1.QueryOuterClass.SignerSetTxRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.SignerSetTxResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSignerSetTxMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get = "/gravity/v1/signer_set/latest";
     * </pre>
     */
    public void latestSignerSetTx(gravity.v1.QueryOuterClass.LatestSignerSetTxRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.SignerSetTxResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLatestSignerSetTxMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/batch_txs/{token_contract}/{nonce}";
     * </pre>
     */
    public void batchTx(gravity.v1.QueryOuterClass.BatchTxRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.BatchTxResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBatchTxMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/contract_call_txs/{invalidation_id}/{invalidation_nonce}";
     * </pre>
     */
    public void contractCallTx(gravity.v1.QueryOuterClass.ContractCallTxRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.ContractCallTxResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getContractCallTxMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get collections of outgoing traffic from the bridge
     * </pre>
     */
    public void signerSetTxs(gravity.v1.QueryOuterClass.SignerSetTxsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.SignerSetTxsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSignerSetTxsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get = "/gravity/v1/batch/batch_txs";
     * </pre>
     */
    public void batchTxs(gravity.v1.QueryOuterClass.BatchTxsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.BatchTxsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBatchTxsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get = "/gravity/v1/batch/contract_call_txs";
     * </pre>
     */
    public void contractCallTxs(gravity.v1.QueryOuterClass.ContractCallTxsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.ContractCallTxsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getContractCallTxsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TODO: can/should we group these into one endpoint?
     * </pre>
     */
    public void signerSetTxConfirmations(gravity.v1.QueryOuterClass.SignerSetTxConfirmationsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.SignerSetTxConfirmationsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSignerSetTxConfirmationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/batch_txs/ethereum_signatures";
     * </pre>
     */
    public void batchTxConfirmations(gravity.v1.QueryOuterClass.BatchTxConfirmationsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.BatchTxConfirmationsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBatchTxConfirmationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/logic_calls/ethereum_signatures";
     * </pre>
     */
    public void contractCallTxConfirmations(gravity.v1.QueryOuterClass.ContractCallTxConfirmationsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.ContractCallTxConfirmationsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getContractCallTxConfirmationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * pending ethereum signature queries for orchestrators to figure out which
     * signatures they are missing
     * TODO: can/should we group this into one endpoint?
     * </pre>
     */
    public void unsignedSignerSetTxs(gravity.v1.QueryOuterClass.UnsignedSignerSetTxsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.UnsignedSignerSetTxsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnsignedSignerSetTxsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get = "/gravity/v1/batches/{address}/pending";
     * </pre>
     */
    public void unsignedBatchTxs(gravity.v1.QueryOuterClass.UnsignedBatchTxsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.UnsignedBatchTxsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnsignedBatchTxsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/ContractCallTxs/{address}/pending";
     * </pre>
     */
    public void unsignedContractCallTxs(gravity.v1.QueryOuterClass.UnsignedContractCallTxsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.UnsignedContractCallTxsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnsignedContractCallTxsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/oracle/event_nonce/{address}";
     * </pre>
     */
    public void lastSubmittedEthereumEvent(gravity.v1.QueryOuterClass.LastSubmittedEthereumEventRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.LastSubmittedEthereumEventResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLastSubmittedEthereumEventMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Queries the fees for all pending batches, results are returned in sdk.Coin
     * (fee_amount_int)(contract_address) style
     * </pre>
     */
    public void batchTxFees(gravity.v1.QueryOuterClass.BatchTxFeesRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.BatchTxFeesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBatchTxFeesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Query for info about denoms tracked by gravity
     * </pre>
     */
    public void eRC20ToDenom(gravity.v1.QueryOuterClass.ERC20ToDenomRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.ERC20ToDenomResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getERC20ToDenomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DenomToERC20Params implements a query that allows ERC-20 parameter information
     * to be retrieved by a Cosmos base denomination.
     * </pre>
     */
    public void denomToERC20Params(gravity.v1.QueryOuterClass.DenomToERC20ParamsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.DenomToERC20ParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDenomToERC20ParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Query for info about denoms tracked by gravity
     * </pre>
     */
    public void denomToERC20(gravity.v1.QueryOuterClass.DenomToERC20Request request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.DenomToERC20Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDenomToERC20Method(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Query for batch send to ethereums
     * </pre>
     */
    public void batchedSendToEthereums(gravity.v1.QueryOuterClass.BatchedSendToEthereumsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.BatchedSendToEthereumsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBatchedSendToEthereumsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Query for unbatched send to ethereums
     * </pre>
     */
    public void unbatchedSendToEthereums(gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnbatchedSendToEthereumsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * delegate keys
     * </pre>
     */
    public void delegateKeysByValidator(gravity.v1.QueryOuterClass.DelegateKeysByValidatorRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.DelegateKeysByValidatorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDelegateKeysByValidatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/delegate_keys/ethereum/{ethereum_signer}";
     * </pre>
     */
    public void delegateKeysByEthereumSigner(gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDelegateKeysByEthereumSignerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/delegate_keys/orchestrator/{orchestrator}";
     * </pre>
     */
    public void delegateKeysByOrchestrator(gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDelegateKeysByOrchestratorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/delegate_keys";
     * </pre>
     */
    public void delegateKeys(gravity.v1.QueryOuterClass.DelegateKeysRequest request,
        io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.DelegateKeysResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDelegateKeysMethod(), getCallOptions()), request, responseObserver);
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
     * <pre>
     * Module parameters query
     * </pre>
     */
    public gravity.v1.QueryOuterClass.ParamsResponse params(gravity.v1.QueryOuterClass.ParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * get info on individual outgoing data
     * </pre>
     */
    public gravity.v1.QueryOuterClass.SignerSetTxResponse signerSetTx(gravity.v1.QueryOuterClass.SignerSetTxRequest request) {
      return blockingUnaryCall(
          getChannel(), getSignerSetTxMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * option (google.api.http).get = "/gravity/v1/signer_set/latest";
     * </pre>
     */
    public gravity.v1.QueryOuterClass.SignerSetTxResponse latestSignerSetTx(gravity.v1.QueryOuterClass.LatestSignerSetTxRequest request) {
      return blockingUnaryCall(
          getChannel(), getLatestSignerSetTxMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/batch_txs/{token_contract}/{nonce}";
     * </pre>
     */
    public gravity.v1.QueryOuterClass.BatchTxResponse batchTx(gravity.v1.QueryOuterClass.BatchTxRequest request) {
      return blockingUnaryCall(
          getChannel(), getBatchTxMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/contract_call_txs/{invalidation_id}/{invalidation_nonce}";
     * </pre>
     */
    public gravity.v1.QueryOuterClass.ContractCallTxResponse contractCallTx(gravity.v1.QueryOuterClass.ContractCallTxRequest request) {
      return blockingUnaryCall(
          getChannel(), getContractCallTxMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * get collections of outgoing traffic from the bridge
     * </pre>
     */
    public gravity.v1.QueryOuterClass.SignerSetTxsResponse signerSetTxs(gravity.v1.QueryOuterClass.SignerSetTxsRequest request) {
      return blockingUnaryCall(
          getChannel(), getSignerSetTxsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * option (google.api.http).get = "/gravity/v1/batch/batch_txs";
     * </pre>
     */
    public gravity.v1.QueryOuterClass.BatchTxsResponse batchTxs(gravity.v1.QueryOuterClass.BatchTxsRequest request) {
      return blockingUnaryCall(
          getChannel(), getBatchTxsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * option (google.api.http).get = "/gravity/v1/batch/contract_call_txs";
     * </pre>
     */
    public gravity.v1.QueryOuterClass.ContractCallTxsResponse contractCallTxs(gravity.v1.QueryOuterClass.ContractCallTxsRequest request) {
      return blockingUnaryCall(
          getChannel(), getContractCallTxsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TODO: can/should we group these into one endpoint?
     * </pre>
     */
    public gravity.v1.QueryOuterClass.SignerSetTxConfirmationsResponse signerSetTxConfirmations(gravity.v1.QueryOuterClass.SignerSetTxConfirmationsRequest request) {
      return blockingUnaryCall(
          getChannel(), getSignerSetTxConfirmationsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/batch_txs/ethereum_signatures";
     * </pre>
     */
    public gravity.v1.QueryOuterClass.BatchTxConfirmationsResponse batchTxConfirmations(gravity.v1.QueryOuterClass.BatchTxConfirmationsRequest request) {
      return blockingUnaryCall(
          getChannel(), getBatchTxConfirmationsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/logic_calls/ethereum_signatures";
     * </pre>
     */
    public gravity.v1.QueryOuterClass.ContractCallTxConfirmationsResponse contractCallTxConfirmations(gravity.v1.QueryOuterClass.ContractCallTxConfirmationsRequest request) {
      return blockingUnaryCall(
          getChannel(), getContractCallTxConfirmationsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * pending ethereum signature queries for orchestrators to figure out which
     * signatures they are missing
     * TODO: can/should we group this into one endpoint?
     * </pre>
     */
    public gravity.v1.QueryOuterClass.UnsignedSignerSetTxsResponse unsignedSignerSetTxs(gravity.v1.QueryOuterClass.UnsignedSignerSetTxsRequest request) {
      return blockingUnaryCall(
          getChannel(), getUnsignedSignerSetTxsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * option (google.api.http).get = "/gravity/v1/batches/{address}/pending";
     * </pre>
     */
    public gravity.v1.QueryOuterClass.UnsignedBatchTxsResponse unsignedBatchTxs(gravity.v1.QueryOuterClass.UnsignedBatchTxsRequest request) {
      return blockingUnaryCall(
          getChannel(), getUnsignedBatchTxsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/ContractCallTxs/{address}/pending";
     * </pre>
     */
    public gravity.v1.QueryOuterClass.UnsignedContractCallTxsResponse unsignedContractCallTxs(gravity.v1.QueryOuterClass.UnsignedContractCallTxsRequest request) {
      return blockingUnaryCall(
          getChannel(), getUnsignedContractCallTxsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/oracle/event_nonce/{address}";
     * </pre>
     */
    public gravity.v1.QueryOuterClass.LastSubmittedEthereumEventResponse lastSubmittedEthereumEvent(gravity.v1.QueryOuterClass.LastSubmittedEthereumEventRequest request) {
      return blockingUnaryCall(
          getChannel(), getLastSubmittedEthereumEventMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Queries the fees for all pending batches, results are returned in sdk.Coin
     * (fee_amount_int)(contract_address) style
     * </pre>
     */
    public gravity.v1.QueryOuterClass.BatchTxFeesResponse batchTxFees(gravity.v1.QueryOuterClass.BatchTxFeesRequest request) {
      return blockingUnaryCall(
          getChannel(), getBatchTxFeesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Query for info about denoms tracked by gravity
     * </pre>
     */
    public gravity.v1.QueryOuterClass.ERC20ToDenomResponse eRC20ToDenom(gravity.v1.QueryOuterClass.ERC20ToDenomRequest request) {
      return blockingUnaryCall(
          getChannel(), getERC20ToDenomMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DenomToERC20Params implements a query that allows ERC-20 parameter information
     * to be retrieved by a Cosmos base denomination.
     * </pre>
     */
    public gravity.v1.QueryOuterClass.DenomToERC20ParamsResponse denomToERC20Params(gravity.v1.QueryOuterClass.DenomToERC20ParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getDenomToERC20ParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Query for info about denoms tracked by gravity
     * </pre>
     */
    public gravity.v1.QueryOuterClass.DenomToERC20Response denomToERC20(gravity.v1.QueryOuterClass.DenomToERC20Request request) {
      return blockingUnaryCall(
          getChannel(), getDenomToERC20Method(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Query for batch send to ethereums
     * </pre>
     */
    public gravity.v1.QueryOuterClass.BatchedSendToEthereumsResponse batchedSendToEthereums(gravity.v1.QueryOuterClass.BatchedSendToEthereumsRequest request) {
      return blockingUnaryCall(
          getChannel(), getBatchedSendToEthereumsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Query for unbatched send to ethereums
     * </pre>
     */
    public gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsResponse unbatchedSendToEthereums(gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsRequest request) {
      return blockingUnaryCall(
          getChannel(), getUnbatchedSendToEthereumsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * delegate keys
     * </pre>
     */
    public gravity.v1.QueryOuterClass.DelegateKeysByValidatorResponse delegateKeysByValidator(gravity.v1.QueryOuterClass.DelegateKeysByValidatorRequest request) {
      return blockingUnaryCall(
          getChannel(), getDelegateKeysByValidatorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/delegate_keys/ethereum/{ethereum_signer}";
     * </pre>
     */
    public gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerResponse delegateKeysByEthereumSigner(gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerRequest request) {
      return blockingUnaryCall(
          getChannel(), getDelegateKeysByEthereumSignerMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/delegate_keys/orchestrator/{orchestrator}";
     * </pre>
     */
    public gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorResponse delegateKeysByOrchestrator(gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorRequest request) {
      return blockingUnaryCall(
          getChannel(), getDelegateKeysByOrchestratorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/delegate_keys";
     * </pre>
     */
    public gravity.v1.QueryOuterClass.DelegateKeysResponse delegateKeys(gravity.v1.QueryOuterClass.DelegateKeysRequest request) {
      return blockingUnaryCall(
          getChannel(), getDelegateKeysMethod(), getCallOptions(), request);
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
     * <pre>
     * Module parameters query
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.ParamsResponse> params(
        gravity.v1.QueryOuterClass.ParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * get info on individual outgoing data
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.SignerSetTxResponse> signerSetTx(
        gravity.v1.QueryOuterClass.SignerSetTxRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSignerSetTxMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * option (google.api.http).get = "/gravity/v1/signer_set/latest";
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.SignerSetTxResponse> latestSignerSetTx(
        gravity.v1.QueryOuterClass.LatestSignerSetTxRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLatestSignerSetTxMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/batch_txs/{token_contract}/{nonce}";
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.BatchTxResponse> batchTx(
        gravity.v1.QueryOuterClass.BatchTxRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBatchTxMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/contract_call_txs/{invalidation_id}/{invalidation_nonce}";
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.ContractCallTxResponse> contractCallTx(
        gravity.v1.QueryOuterClass.ContractCallTxRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getContractCallTxMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * get collections of outgoing traffic from the bridge
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.SignerSetTxsResponse> signerSetTxs(
        gravity.v1.QueryOuterClass.SignerSetTxsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSignerSetTxsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * option (google.api.http).get = "/gravity/v1/batch/batch_txs";
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.BatchTxsResponse> batchTxs(
        gravity.v1.QueryOuterClass.BatchTxsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBatchTxsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * option (google.api.http).get = "/gravity/v1/batch/contract_call_txs";
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.ContractCallTxsResponse> contractCallTxs(
        gravity.v1.QueryOuterClass.ContractCallTxsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getContractCallTxsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TODO: can/should we group these into one endpoint?
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.SignerSetTxConfirmationsResponse> signerSetTxConfirmations(
        gravity.v1.QueryOuterClass.SignerSetTxConfirmationsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSignerSetTxConfirmationsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/batch_txs/ethereum_signatures";
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.BatchTxConfirmationsResponse> batchTxConfirmations(
        gravity.v1.QueryOuterClass.BatchTxConfirmationsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBatchTxConfirmationsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/logic_calls/ethereum_signatures";
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.ContractCallTxConfirmationsResponse> contractCallTxConfirmations(
        gravity.v1.QueryOuterClass.ContractCallTxConfirmationsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getContractCallTxConfirmationsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * pending ethereum signature queries for orchestrators to figure out which
     * signatures they are missing
     * TODO: can/should we group this into one endpoint?
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.UnsignedSignerSetTxsResponse> unsignedSignerSetTxs(
        gravity.v1.QueryOuterClass.UnsignedSignerSetTxsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUnsignedSignerSetTxsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * option (google.api.http).get = "/gravity/v1/batches/{address}/pending";
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.UnsignedBatchTxsResponse> unsignedBatchTxs(
        gravity.v1.QueryOuterClass.UnsignedBatchTxsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUnsignedBatchTxsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/ContractCallTxs/{address}/pending";
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.UnsignedContractCallTxsResponse> unsignedContractCallTxs(
        gravity.v1.QueryOuterClass.UnsignedContractCallTxsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUnsignedContractCallTxsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/oracle/event_nonce/{address}";
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.LastSubmittedEthereumEventResponse> lastSubmittedEthereumEvent(
        gravity.v1.QueryOuterClass.LastSubmittedEthereumEventRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLastSubmittedEthereumEventMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Queries the fees for all pending batches, results are returned in sdk.Coin
     * (fee_amount_int)(contract_address) style
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.BatchTxFeesResponse> batchTxFees(
        gravity.v1.QueryOuterClass.BatchTxFeesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBatchTxFeesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Query for info about denoms tracked by gravity
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.ERC20ToDenomResponse> eRC20ToDenom(
        gravity.v1.QueryOuterClass.ERC20ToDenomRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getERC20ToDenomMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DenomToERC20Params implements a query that allows ERC-20 parameter information
     * to be retrieved by a Cosmos base denomination.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.DenomToERC20ParamsResponse> denomToERC20Params(
        gravity.v1.QueryOuterClass.DenomToERC20ParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDenomToERC20ParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Query for info about denoms tracked by gravity
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.DenomToERC20Response> denomToERC20(
        gravity.v1.QueryOuterClass.DenomToERC20Request request) {
      return futureUnaryCall(
          getChannel().newCall(getDenomToERC20Method(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Query for batch send to ethereums
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.BatchedSendToEthereumsResponse> batchedSendToEthereums(
        gravity.v1.QueryOuterClass.BatchedSendToEthereumsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBatchedSendToEthereumsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Query for unbatched send to ethereums
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsResponse> unbatchedSendToEthereums(
        gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUnbatchedSendToEthereumsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * delegate keys
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.DelegateKeysByValidatorResponse> delegateKeysByValidator(
        gravity.v1.QueryOuterClass.DelegateKeysByValidatorRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDelegateKeysByValidatorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/delegate_keys/ethereum/{ethereum_signer}";
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerResponse> delegateKeysByEthereumSigner(
        gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDelegateKeysByEthereumSignerMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/delegate_keys/orchestrator/{orchestrator}";
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorResponse> delegateKeysByOrchestrator(
        gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDelegateKeysByOrchestratorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * option (google.api.http).get =
     * "/gravity/v1/delegate_keys";
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<gravity.v1.QueryOuterClass.DelegateKeysResponse> delegateKeys(
        gravity.v1.QueryOuterClass.DelegateKeysRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDelegateKeysMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_SIGNER_SET_TX = 1;
  private static final int METHODID_LATEST_SIGNER_SET_TX = 2;
  private static final int METHODID_BATCH_TX = 3;
  private static final int METHODID_CONTRACT_CALL_TX = 4;
  private static final int METHODID_SIGNER_SET_TXS = 5;
  private static final int METHODID_BATCH_TXS = 6;
  private static final int METHODID_CONTRACT_CALL_TXS = 7;
  private static final int METHODID_SIGNER_SET_TX_CONFIRMATIONS = 8;
  private static final int METHODID_BATCH_TX_CONFIRMATIONS = 9;
  private static final int METHODID_CONTRACT_CALL_TX_CONFIRMATIONS = 10;
  private static final int METHODID_UNSIGNED_SIGNER_SET_TXS = 11;
  private static final int METHODID_UNSIGNED_BATCH_TXS = 12;
  private static final int METHODID_UNSIGNED_CONTRACT_CALL_TXS = 13;
  private static final int METHODID_LAST_SUBMITTED_ETHEREUM_EVENT = 14;
  private static final int METHODID_BATCH_TX_FEES = 15;
  private static final int METHODID_ERC20TO_DENOM = 16;
  private static final int METHODID_DENOM_TO_ERC20PARAMS = 17;
  private static final int METHODID_DENOM_TO_ERC20 = 18;
  private static final int METHODID_BATCHED_SEND_TO_ETHEREUMS = 19;
  private static final int METHODID_UNBATCHED_SEND_TO_ETHEREUMS = 20;
  private static final int METHODID_DELEGATE_KEYS_BY_VALIDATOR = 21;
  private static final int METHODID_DELEGATE_KEYS_BY_ETHEREUM_SIGNER = 22;
  private static final int METHODID_DELEGATE_KEYS_BY_ORCHESTRATOR = 23;
  private static final int METHODID_DELEGATE_KEYS = 24;

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
          serviceImpl.params((gravity.v1.QueryOuterClass.ParamsRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.ParamsResponse>) responseObserver);
          break;
        case METHODID_SIGNER_SET_TX:
          serviceImpl.signerSetTx((gravity.v1.QueryOuterClass.SignerSetTxRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.SignerSetTxResponse>) responseObserver);
          break;
        case METHODID_LATEST_SIGNER_SET_TX:
          serviceImpl.latestSignerSetTx((gravity.v1.QueryOuterClass.LatestSignerSetTxRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.SignerSetTxResponse>) responseObserver);
          break;
        case METHODID_BATCH_TX:
          serviceImpl.batchTx((gravity.v1.QueryOuterClass.BatchTxRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.BatchTxResponse>) responseObserver);
          break;
        case METHODID_CONTRACT_CALL_TX:
          serviceImpl.contractCallTx((gravity.v1.QueryOuterClass.ContractCallTxRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.ContractCallTxResponse>) responseObserver);
          break;
        case METHODID_SIGNER_SET_TXS:
          serviceImpl.signerSetTxs((gravity.v1.QueryOuterClass.SignerSetTxsRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.SignerSetTxsResponse>) responseObserver);
          break;
        case METHODID_BATCH_TXS:
          serviceImpl.batchTxs((gravity.v1.QueryOuterClass.BatchTxsRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.BatchTxsResponse>) responseObserver);
          break;
        case METHODID_CONTRACT_CALL_TXS:
          serviceImpl.contractCallTxs((gravity.v1.QueryOuterClass.ContractCallTxsRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.ContractCallTxsResponse>) responseObserver);
          break;
        case METHODID_SIGNER_SET_TX_CONFIRMATIONS:
          serviceImpl.signerSetTxConfirmations((gravity.v1.QueryOuterClass.SignerSetTxConfirmationsRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.SignerSetTxConfirmationsResponse>) responseObserver);
          break;
        case METHODID_BATCH_TX_CONFIRMATIONS:
          serviceImpl.batchTxConfirmations((gravity.v1.QueryOuterClass.BatchTxConfirmationsRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.BatchTxConfirmationsResponse>) responseObserver);
          break;
        case METHODID_CONTRACT_CALL_TX_CONFIRMATIONS:
          serviceImpl.contractCallTxConfirmations((gravity.v1.QueryOuterClass.ContractCallTxConfirmationsRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.ContractCallTxConfirmationsResponse>) responseObserver);
          break;
        case METHODID_UNSIGNED_SIGNER_SET_TXS:
          serviceImpl.unsignedSignerSetTxs((gravity.v1.QueryOuterClass.UnsignedSignerSetTxsRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.UnsignedSignerSetTxsResponse>) responseObserver);
          break;
        case METHODID_UNSIGNED_BATCH_TXS:
          serviceImpl.unsignedBatchTxs((gravity.v1.QueryOuterClass.UnsignedBatchTxsRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.UnsignedBatchTxsResponse>) responseObserver);
          break;
        case METHODID_UNSIGNED_CONTRACT_CALL_TXS:
          serviceImpl.unsignedContractCallTxs((gravity.v1.QueryOuterClass.UnsignedContractCallTxsRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.UnsignedContractCallTxsResponse>) responseObserver);
          break;
        case METHODID_LAST_SUBMITTED_ETHEREUM_EVENT:
          serviceImpl.lastSubmittedEthereumEvent((gravity.v1.QueryOuterClass.LastSubmittedEthereumEventRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.LastSubmittedEthereumEventResponse>) responseObserver);
          break;
        case METHODID_BATCH_TX_FEES:
          serviceImpl.batchTxFees((gravity.v1.QueryOuterClass.BatchTxFeesRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.BatchTxFeesResponse>) responseObserver);
          break;
        case METHODID_ERC20TO_DENOM:
          serviceImpl.eRC20ToDenom((gravity.v1.QueryOuterClass.ERC20ToDenomRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.ERC20ToDenomResponse>) responseObserver);
          break;
        case METHODID_DENOM_TO_ERC20PARAMS:
          serviceImpl.denomToERC20Params((gravity.v1.QueryOuterClass.DenomToERC20ParamsRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.DenomToERC20ParamsResponse>) responseObserver);
          break;
        case METHODID_DENOM_TO_ERC20:
          serviceImpl.denomToERC20((gravity.v1.QueryOuterClass.DenomToERC20Request) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.DenomToERC20Response>) responseObserver);
          break;
        case METHODID_BATCHED_SEND_TO_ETHEREUMS:
          serviceImpl.batchedSendToEthereums((gravity.v1.QueryOuterClass.BatchedSendToEthereumsRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.BatchedSendToEthereumsResponse>) responseObserver);
          break;
        case METHODID_UNBATCHED_SEND_TO_ETHEREUMS:
          serviceImpl.unbatchedSendToEthereums((gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.UnbatchedSendToEthereumsResponse>) responseObserver);
          break;
        case METHODID_DELEGATE_KEYS_BY_VALIDATOR:
          serviceImpl.delegateKeysByValidator((gravity.v1.QueryOuterClass.DelegateKeysByValidatorRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.DelegateKeysByValidatorResponse>) responseObserver);
          break;
        case METHODID_DELEGATE_KEYS_BY_ETHEREUM_SIGNER:
          serviceImpl.delegateKeysByEthereumSigner((gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.DelegateKeysByEthereumSignerResponse>) responseObserver);
          break;
        case METHODID_DELEGATE_KEYS_BY_ORCHESTRATOR:
          serviceImpl.delegateKeysByOrchestrator((gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.DelegateKeysByOrchestratorResponse>) responseObserver);
          break;
        case METHODID_DELEGATE_KEYS:
          serviceImpl.delegateKeys((gravity.v1.QueryOuterClass.DelegateKeysRequest) request,
              (io.grpc.stub.StreamObserver<gravity.v1.QueryOuterClass.DelegateKeysResponse>) responseObserver);
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
      return gravity.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getSignerSetTxMethod())
              .addMethod(getLatestSignerSetTxMethod())
              .addMethod(getBatchTxMethod())
              .addMethod(getContractCallTxMethod())
              .addMethod(getSignerSetTxsMethod())
              .addMethod(getBatchTxsMethod())
              .addMethod(getContractCallTxsMethod())
              .addMethod(getSignerSetTxConfirmationsMethod())
              .addMethod(getBatchTxConfirmationsMethod())
              .addMethod(getContractCallTxConfirmationsMethod())
              .addMethod(getUnsignedSignerSetTxsMethod())
              .addMethod(getUnsignedBatchTxsMethod())
              .addMethod(getUnsignedContractCallTxsMethod())
              .addMethod(getLastSubmittedEthereumEventMethod())
              .addMethod(getBatchTxFeesMethod())
              .addMethod(getERC20ToDenomMethod())
              .addMethod(getDenomToERC20ParamsMethod())
              .addMethod(getDenomToERC20Method())
              .addMethod(getBatchedSendToEthereumsMethod())
              .addMethod(getUnbatchedSendToEthereumsMethod())
              .addMethod(getDelegateKeysByValidatorMethod())
              .addMethod(getDelegateKeysByEthereumSignerMethod())
              .addMethod(getDelegateKeysByOrchestratorMethod())
              .addMethod(getDelegateKeysMethod())
              .build();
        }
      }
    }
    return result;
  }
}
