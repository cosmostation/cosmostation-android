package regen.ecocredit.v1alpha2;

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
 * Msg is the regen.ecocredit.v1alpha2 Query service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: regen/ecocredit/v1alpha2/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "regen.ecocredit.v1alpha2.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesResponse> getClassesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Classes",
      requestType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesRequest.class,
      responseType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesResponse> getClassesMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesResponse> getClassesMethod;
    if ((getClassesMethod = QueryGrpc.getClassesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClassesMethod = QueryGrpc.getClassesMethod) == null) {
          QueryGrpc.getClassesMethod = getClassesMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Classes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Classes"))
              .build();
        }
      }
    }
    return getClassesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoResponse> getClassInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClassInfo",
      requestType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoRequest.class,
      responseType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoResponse> getClassInfoMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoResponse> getClassInfoMethod;
    if ((getClassInfoMethod = QueryGrpc.getClassInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClassInfoMethod = QueryGrpc.getClassInfoMethod) == null) {
          QueryGrpc.getClassInfoMethod = getClassInfoMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClassInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClassInfo"))
              .build();
        }
      }
    }
    return getClassInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsResponse> getProjectsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Projects",
      requestType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsRequest.class,
      responseType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsResponse> getProjectsMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsResponse> getProjectsMethod;
    if ((getProjectsMethod = QueryGrpc.getProjectsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProjectsMethod = QueryGrpc.getProjectsMethod) == null) {
          QueryGrpc.getProjectsMethod = getProjectsMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Projects"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Projects"))
              .build();
        }
      }
    }
    return getProjectsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoResponse> getProjectInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProjectInfo",
      requestType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoRequest.class,
      responseType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoResponse> getProjectInfoMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoResponse> getProjectInfoMethod;
    if ((getProjectInfoMethod = QueryGrpc.getProjectInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProjectInfoMethod = QueryGrpc.getProjectInfoMethod) == null) {
          QueryGrpc.getProjectInfoMethod = getProjectInfoMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ProjectInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ProjectInfo"))
              .build();
        }
      }
    }
    return getProjectInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesResponse> getBatchesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Batches",
      requestType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesRequest.class,
      responseType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesResponse> getBatchesMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesResponse> getBatchesMethod;
    if ((getBatchesMethod = QueryGrpc.getBatchesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchesMethod = QueryGrpc.getBatchesMethod) == null) {
          QueryGrpc.getBatchesMethod = getBatchesMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Batches"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Batches"))
              .build();
        }
      }
    }
    return getBatchesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoResponse> getBatchInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchInfo",
      requestType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoRequest.class,
      responseType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoResponse> getBatchInfoMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoResponse> getBatchInfoMethod;
    if ((getBatchInfoMethod = QueryGrpc.getBatchInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBatchInfoMethod = QueryGrpc.getBatchInfoMethod) == null) {
          QueryGrpc.getBatchInfoMethod = getBatchInfoMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BatchInfo"))
              .build();
        }
      }
    }
    return getBatchInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceResponse> getBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Balance",
      requestType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceRequest.class,
      responseType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceResponse> getBalanceMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceResponse> getBalanceMethod;
    if ((getBalanceMethod = QueryGrpc.getBalanceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBalanceMethod = QueryGrpc.getBalanceMethod) == null) {
          QueryGrpc.getBalanceMethod = getBalanceMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Balance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Balance"))
              .build();
        }
      }
    }
    return getBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyResponse> getSupplyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Supply",
      requestType = regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyRequest.class,
      responseType = regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyResponse> getSupplyMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyResponse> getSupplyMethod;
    if ((getSupplyMethod = QueryGrpc.getSupplyMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSupplyMethod = QueryGrpc.getSupplyMethod) == null) {
          QueryGrpc.getSupplyMethod = getSupplyMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Supply"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Supply"))
              .build();
        }
      }
    }
    return getSupplyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesResponse> getCreditTypesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreditTypes",
      requestType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesRequest.class,
      responseType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesResponse> getCreditTypesMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesResponse> getCreditTypesMethod;
    if ((getCreditTypesMethod = QueryGrpc.getCreditTypesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCreditTypesMethod = QueryGrpc.getCreditTypesMethod) == null) {
          QueryGrpc.getCreditTypesMethod = getCreditTypesMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreditTypes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("CreditTypes"))
              .build();
        }
      }
    }
    return getCreditTypesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsRequest.class,
      responseType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderResponse> getSellOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SellOrder",
      requestType = regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderRequest.class,
      responseType = regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderResponse> getSellOrderMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderResponse> getSellOrderMethod;
    if ((getSellOrderMethod = QueryGrpc.getSellOrderMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSellOrderMethod = QueryGrpc.getSellOrderMethod) == null) {
          QueryGrpc.getSellOrderMethod = getSellOrderMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SellOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SellOrder"))
              .build();
        }
      }
    }
    return getSellOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersResponse> getSellOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SellOrders",
      requestType = regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersRequest.class,
      responseType = regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersResponse> getSellOrdersMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersResponse> getSellOrdersMethod;
    if ((getSellOrdersMethod = QueryGrpc.getSellOrdersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSellOrdersMethod = QueryGrpc.getSellOrdersMethod) == null) {
          QueryGrpc.getSellOrdersMethod = getSellOrdersMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SellOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SellOrders"))
              .build();
        }
      }
    }
    return getSellOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomResponse> getSellOrdersByBatchDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SellOrdersByBatchDenom",
      requestType = regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomRequest.class,
      responseType = regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomResponse> getSellOrdersByBatchDenomMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomResponse> getSellOrdersByBatchDenomMethod;
    if ((getSellOrdersByBatchDenomMethod = QueryGrpc.getSellOrdersByBatchDenomMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSellOrdersByBatchDenomMethod = QueryGrpc.getSellOrdersByBatchDenomMethod) == null) {
          QueryGrpc.getSellOrdersByBatchDenomMethod = getSellOrdersByBatchDenomMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SellOrdersByBatchDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SellOrdersByBatchDenom"))
              .build();
        }
      }
    }
    return getSellOrdersByBatchDenomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressResponse> getSellOrdersByAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SellOrdersByAddress",
      requestType = regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressRequest.class,
      responseType = regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressResponse> getSellOrdersByAddressMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressResponse> getSellOrdersByAddressMethod;
    if ((getSellOrdersByAddressMethod = QueryGrpc.getSellOrdersByAddressMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSellOrdersByAddressMethod = QueryGrpc.getSellOrdersByAddressMethod) == null) {
          QueryGrpc.getSellOrdersByAddressMethod = getSellOrdersByAddressMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SellOrdersByAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SellOrdersByAddress"))
              .build();
        }
      }
    }
    return getSellOrdersByAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderResponse> getBuyOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BuyOrder",
      requestType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderRequest.class,
      responseType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderResponse> getBuyOrderMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderResponse> getBuyOrderMethod;
    if ((getBuyOrderMethod = QueryGrpc.getBuyOrderMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBuyOrderMethod = QueryGrpc.getBuyOrderMethod) == null) {
          QueryGrpc.getBuyOrderMethod = getBuyOrderMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BuyOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BuyOrder"))
              .build();
        }
      }
    }
    return getBuyOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersResponse> getBuyOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BuyOrders",
      requestType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersRequest.class,
      responseType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersResponse> getBuyOrdersMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersResponse> getBuyOrdersMethod;
    if ((getBuyOrdersMethod = QueryGrpc.getBuyOrdersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBuyOrdersMethod = QueryGrpc.getBuyOrdersMethod) == null) {
          QueryGrpc.getBuyOrdersMethod = getBuyOrdersMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BuyOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BuyOrders"))
              .build();
        }
      }
    }
    return getBuyOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressResponse> getBuyOrdersByAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BuyOrdersByAddress",
      requestType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressRequest.class,
      responseType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressResponse> getBuyOrdersByAddressMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressResponse> getBuyOrdersByAddressMethod;
    if ((getBuyOrdersByAddressMethod = QueryGrpc.getBuyOrdersByAddressMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBuyOrdersByAddressMethod = QueryGrpc.getBuyOrdersByAddressMethod) == null) {
          QueryGrpc.getBuyOrdersByAddressMethod = getBuyOrdersByAddressMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BuyOrdersByAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BuyOrdersByAddress"))
              .build();
        }
      }
    }
    return getBuyOrdersByAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsResponse> getAllowedAskDenomsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllowedAskDenoms",
      requestType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsRequest.class,
      responseType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsResponse> getAllowedAskDenomsMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsResponse> getAllowedAskDenomsMethod;
    if ((getAllowedAskDenomsMethod = QueryGrpc.getAllowedAskDenomsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAllowedAskDenomsMethod = QueryGrpc.getAllowedAskDenomsMethod) == null) {
          QueryGrpc.getAllowedAskDenomsMethod = getAllowedAskDenomsMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AllowedAskDenoms"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AllowedAskDenoms"))
              .build();
        }
      }
    }
    return getAllowedAskDenomsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketResponse> getBasketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Basket",
      requestType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketRequest.class,
      responseType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketResponse> getBasketMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketResponse> getBasketMethod;
    if ((getBasketMethod = QueryGrpc.getBasketMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBasketMethod = QueryGrpc.getBasketMethod) == null) {
          QueryGrpc.getBasketMethod = getBasketMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Basket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Basket"))
              .build();
        }
      }
    }
    return getBasketMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsResponse> getBasketsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Baskets",
      requestType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsRequest.class,
      responseType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsResponse> getBasketsMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsResponse> getBasketsMethod;
    if ((getBasketsMethod = QueryGrpc.getBasketsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBasketsMethod = QueryGrpc.getBasketsMethod) == null) {
          QueryGrpc.getBasketsMethod = getBasketsMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Baskets"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Baskets"))
              .build();
        }
      }
    }
    return getBasketsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsResponse> getBasketCreditsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BasketCredits",
      requestType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsRequest.class,
      responseType = regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsRequest,
      regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsResponse> getBasketCreditsMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsResponse> getBasketCreditsMethod;
    if ((getBasketCreditsMethod = QueryGrpc.getBasketCreditsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBasketCreditsMethod = QueryGrpc.getBasketCreditsMethod) == null) {
          QueryGrpc.getBasketCreditsMethod = getBasketCreditsMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsRequest, regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BasketCredits"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BasketCredits"))
              .build();
        }
      }
    }
    return getBasketCreditsMethod;
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
   * Msg is the regen.ecocredit.v1alpha2 Query service.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Classes queries for all credit classes with pagination.
     * </pre>
     */
    public void classes(regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClassesMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClassInfo queries for information on a credit class.
     * </pre>
     */
    public void classInfo(regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClassInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * Projects queries for all projects within a class with pagination.
     * </pre>
     */
    public void projects(regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProjectsMethod(), responseObserver);
    }

    /**
     * <pre>
     * ClassInfo queries for information on a project.
     * </pre>
     */
    public void projectInfo(regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProjectInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * Batches queries for all batches in the given project with pagination.
     * </pre>
     */
    public void batches(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBatchesMethod(), responseObserver);
    }

    /**
     * <pre>
     * BatchInfo queries for information on a credit batch.
     * </pre>
     */
    public void batchInfo(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBatchInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * Balance queries the balance (both tradable and retired) of a given credit
     * batch for a given account.
     * </pre>
     */
    public void balance(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBalanceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Supply queries the tradable and retired supply of a credit batch.
     * </pre>
     */
    public void supply(regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSupplyMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreditTypes returns the list of allowed types that credit classes can have.
     * See Types/CreditType for more details.
     * </pre>
     */
    public void creditTypes(regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreditTypesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries the ecocredit module parameters.
     * </pre>
     */
    public void params(regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * SellOrder queries a sell order by its ID
     * </pre>
     */
    public void sellOrder(regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSellOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     * SellOrders queries a paginated list of all sell orders
     * </pre>
     */
    public void sellOrders(regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSellOrdersMethod(), responseObserver);
    }

    /**
     * <pre>
     * SellOrdersByDenom queries a paginated list of all sell orders of a specific ecocredit denom
     * </pre>
     */
    public void sellOrdersByBatchDenom(regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSellOrdersByBatchDenomMethod(), responseObserver);
    }

    /**
     * <pre>
     * SellOrdersByAddress queries a paginated list of all sell orders from a specific address
     * </pre>
     */
    public void sellOrdersByAddress(regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSellOrdersByAddressMethod(), responseObserver);
    }

    /**
     * <pre>
     * BuyOrder queries a buy order by its id
     * </pre>
     */
    public void buyOrder(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBuyOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     * BuyOrders queries a paginated list of all buy orders
     * </pre>
     */
    public void buyOrders(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBuyOrdersMethod(), responseObserver);
    }

    /**
     * <pre>
     * BuyOrdersByAddress queries a paginated list of buy orders by creator address
     * </pre>
     */
    public void buyOrdersByAddress(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBuyOrdersByAddressMethod(), responseObserver);
    }

    /**
     * <pre>
     * AllowedAskDenoms queries all denoms allowed to be set in the AskPrice of a sell order
     * </pre>
     */
    public void allowedAskDenoms(regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAllowedAskDenomsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Basket queries one basket by denom.
     * </pre>
     */
    public void basket(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBasketMethod(), responseObserver);
    }

    /**
     * <pre>
     * Baskets lists all baskets in the ecocredit module.
     * </pre>
     */
    public void baskets(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBasketsMethod(), responseObserver);
    }

    /**
     * <pre>
     * BasketCredits lists all ecocredits inside a given basket.
     * </pre>
     */
    public void basketCredits(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBasketCreditsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getClassesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesRequest,
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesResponse>(
                  this, METHODID_CLASSES)))
          .addMethod(
            getClassInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoRequest,
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoResponse>(
                  this, METHODID_CLASS_INFO)))
          .addMethod(
            getProjectsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsRequest,
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsResponse>(
                  this, METHODID_PROJECTS)))
          .addMethod(
            getProjectInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoRequest,
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoResponse>(
                  this, METHODID_PROJECT_INFO)))
          .addMethod(
            getBatchesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesRequest,
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesResponse>(
                  this, METHODID_BATCHES)))
          .addMethod(
            getBatchInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoRequest,
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoResponse>(
                  this, METHODID_BATCH_INFO)))
          .addMethod(
            getBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceRequest,
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceResponse>(
                  this, METHODID_BALANCE)))
          .addMethod(
            getSupplyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyRequest,
                regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyResponse>(
                  this, METHODID_SUPPLY)))
          .addMethod(
            getCreditTypesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesRequest,
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesResponse>(
                  this, METHODID_CREDIT_TYPES)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsRequest,
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getSellOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderRequest,
                regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderResponse>(
                  this, METHODID_SELL_ORDER)))
          .addMethod(
            getSellOrdersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersRequest,
                regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersResponse>(
                  this, METHODID_SELL_ORDERS)))
          .addMethod(
            getSellOrdersByBatchDenomMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomRequest,
                regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomResponse>(
                  this, METHODID_SELL_ORDERS_BY_BATCH_DENOM)))
          .addMethod(
            getSellOrdersByAddressMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressRequest,
                regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressResponse>(
                  this, METHODID_SELL_ORDERS_BY_ADDRESS)))
          .addMethod(
            getBuyOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderRequest,
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderResponse>(
                  this, METHODID_BUY_ORDER)))
          .addMethod(
            getBuyOrdersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersRequest,
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersResponse>(
                  this, METHODID_BUY_ORDERS)))
          .addMethod(
            getBuyOrdersByAddressMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressRequest,
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressResponse>(
                  this, METHODID_BUY_ORDERS_BY_ADDRESS)))
          .addMethod(
            getAllowedAskDenomsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsRequest,
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsResponse>(
                  this, METHODID_ALLOWED_ASK_DENOMS)))
          .addMethod(
            getBasketMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketRequest,
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketResponse>(
                  this, METHODID_BASKET)))
          .addMethod(
            getBasketsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsRequest,
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsResponse>(
                  this, METHODID_BASKETS)))
          .addMethod(
            getBasketCreditsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsRequest,
                regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsResponse>(
                  this, METHODID_BASKET_CREDITS)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg is the regen.ecocredit.v1alpha2 Query service.
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
     * Classes queries for all credit classes with pagination.
     * </pre>
     */
    public void classes(regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClassesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClassInfo queries for information on a credit class.
     * </pre>
     */
    public void classInfo(regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClassInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Projects queries for all projects within a class with pagination.
     * </pre>
     */
    public void projects(regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProjectsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ClassInfo queries for information on a project.
     * </pre>
     */
    public void projectInfo(regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProjectInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Batches queries for all batches in the given project with pagination.
     * </pre>
     */
    public void batches(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBatchesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BatchInfo queries for information on a credit batch.
     * </pre>
     */
    public void batchInfo(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBatchInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Balance queries the balance (both tradable and retired) of a given credit
     * batch for a given account.
     * </pre>
     */
    public void balance(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Supply queries the tradable and retired supply of a credit batch.
     * </pre>
     */
    public void supply(regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSupplyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreditTypes returns the list of allowed types that credit classes can have.
     * See Types/CreditType for more details.
     * </pre>
     */
    public void creditTypes(regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreditTypesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries the ecocredit module parameters.
     * </pre>
     */
    public void params(regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SellOrder queries a sell order by its ID
     * </pre>
     */
    public void sellOrder(regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSellOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SellOrders queries a paginated list of all sell orders
     * </pre>
     */
    public void sellOrders(regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSellOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SellOrdersByDenom queries a paginated list of all sell orders of a specific ecocredit denom
     * </pre>
     */
    public void sellOrdersByBatchDenom(regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSellOrdersByBatchDenomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SellOrdersByAddress queries a paginated list of all sell orders from a specific address
     * </pre>
     */
    public void sellOrdersByAddress(regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSellOrdersByAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BuyOrder queries a buy order by its id
     * </pre>
     */
    public void buyOrder(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBuyOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BuyOrders queries a paginated list of all buy orders
     * </pre>
     */
    public void buyOrders(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBuyOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BuyOrdersByAddress queries a paginated list of buy orders by creator address
     * </pre>
     */
    public void buyOrdersByAddress(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBuyOrdersByAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AllowedAskDenoms queries all denoms allowed to be set in the AskPrice of a sell order
     * </pre>
     */
    public void allowedAskDenoms(regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAllowedAskDenomsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Basket queries one basket by denom.
     * </pre>
     */
    public void basket(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBasketMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Baskets lists all baskets in the ecocredit module.
     * </pre>
     */
    public void baskets(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBasketsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BasketCredits lists all ecocredits inside a given basket.
     * </pre>
     */
    public void basketCredits(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsRequest request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBasketCreditsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg is the regen.ecocredit.v1alpha2 Query service.
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
     * Classes queries for all credit classes with pagination.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesResponse classes(regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesRequest request) {
      return blockingUnaryCall(
          getChannel(), getClassesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClassInfo queries for information on a credit class.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoResponse classInfo(regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoRequest request) {
      return blockingUnaryCall(
          getChannel(), getClassInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Projects queries for all projects within a class with pagination.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsResponse projects(regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsRequest request) {
      return blockingUnaryCall(
          getChannel(), getProjectsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ClassInfo queries for information on a project.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoResponse projectInfo(regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoRequest request) {
      return blockingUnaryCall(
          getChannel(), getProjectInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Batches queries for all batches in the given project with pagination.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesResponse batches(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesRequest request) {
      return blockingUnaryCall(
          getChannel(), getBatchesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BatchInfo queries for information on a credit batch.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoResponse batchInfo(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoRequest request) {
      return blockingUnaryCall(
          getChannel(), getBatchInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Balance queries the balance (both tradable and retired) of a given credit
     * batch for a given account.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceResponse balance(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceRequest request) {
      return blockingUnaryCall(
          getChannel(), getBalanceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Supply queries the tradable and retired supply of a credit batch.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyResponse supply(regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyRequest request) {
      return blockingUnaryCall(
          getChannel(), getSupplyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreditTypes returns the list of allowed types that credit classes can have.
     * See Types/CreditType for more details.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesResponse creditTypes(regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreditTypesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries the ecocredit module parameters.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsResponse params(regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SellOrder queries a sell order by its ID
     * </pre>
     */
    public regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderResponse sellOrder(regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderRequest request) {
      return blockingUnaryCall(
          getChannel(), getSellOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SellOrders queries a paginated list of all sell orders
     * </pre>
     */
    public regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersResponse sellOrders(regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersRequest request) {
      return blockingUnaryCall(
          getChannel(), getSellOrdersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SellOrdersByDenom queries a paginated list of all sell orders of a specific ecocredit denom
     * </pre>
     */
    public regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomResponse sellOrdersByBatchDenom(regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomRequest request) {
      return blockingUnaryCall(
          getChannel(), getSellOrdersByBatchDenomMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SellOrdersByAddress queries a paginated list of all sell orders from a specific address
     * </pre>
     */
    public regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressResponse sellOrdersByAddress(regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressRequest request) {
      return blockingUnaryCall(
          getChannel(), getSellOrdersByAddressMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BuyOrder queries a buy order by its id
     * </pre>
     */
    public regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderResponse buyOrder(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderRequest request) {
      return blockingUnaryCall(
          getChannel(), getBuyOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BuyOrders queries a paginated list of all buy orders
     * </pre>
     */
    public regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersResponse buyOrders(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersRequest request) {
      return blockingUnaryCall(
          getChannel(), getBuyOrdersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BuyOrdersByAddress queries a paginated list of buy orders by creator address
     * </pre>
     */
    public regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressResponse buyOrdersByAddress(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressRequest request) {
      return blockingUnaryCall(
          getChannel(), getBuyOrdersByAddressMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AllowedAskDenoms queries all denoms allowed to be set in the AskPrice of a sell order
     * </pre>
     */
    public regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsResponse allowedAskDenoms(regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsRequest request) {
      return blockingUnaryCall(
          getChannel(), getAllowedAskDenomsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Basket queries one basket by denom.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketResponse basket(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketRequest request) {
      return blockingUnaryCall(
          getChannel(), getBasketMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Baskets lists all baskets in the ecocredit module.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsResponse baskets(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsRequest request) {
      return blockingUnaryCall(
          getChannel(), getBasketsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BasketCredits lists all ecocredits inside a given basket.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsResponse basketCredits(regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsRequest request) {
      return blockingUnaryCall(
          getChannel(), getBasketCreditsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg is the regen.ecocredit.v1alpha2 Query service.
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
     * Classes queries for all credit classes with pagination.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesResponse> classes(
        regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getClassesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClassInfo queries for information on a credit class.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoResponse> classInfo(
        regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getClassInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Projects queries for all projects within a class with pagination.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsResponse> projects(
        regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getProjectsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ClassInfo queries for information on a project.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoResponse> projectInfo(
        regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getProjectInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Batches queries for all batches in the given project with pagination.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesResponse> batches(
        regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBatchesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BatchInfo queries for information on a credit batch.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoResponse> batchInfo(
        regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBatchInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Balance queries the balance (both tradable and retired) of a given credit
     * batch for a given account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceResponse> balance(
        regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBalanceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Supply queries the tradable and retired supply of a credit batch.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyResponse> supply(
        regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSupplyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreditTypes returns the list of allowed types that credit classes can have.
     * See Types/CreditType for more details.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesResponse> creditTypes(
        regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreditTypesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries the ecocredit module parameters.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsResponse> params(
        regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SellOrder queries a sell order by its ID
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderResponse> sellOrder(
        regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSellOrderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SellOrders queries a paginated list of all sell orders
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersResponse> sellOrders(
        regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSellOrdersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SellOrdersByDenom queries a paginated list of all sell orders of a specific ecocredit denom
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomResponse> sellOrdersByBatchDenom(
        regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSellOrdersByBatchDenomMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SellOrdersByAddress queries a paginated list of all sell orders from a specific address
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressResponse> sellOrdersByAddress(
        regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSellOrdersByAddressMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BuyOrder queries a buy order by its id
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderResponse> buyOrder(
        regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBuyOrderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BuyOrders queries a paginated list of all buy orders
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersResponse> buyOrders(
        regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBuyOrdersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BuyOrdersByAddress queries a paginated list of buy orders by creator address
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressResponse> buyOrdersByAddress(
        regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBuyOrdersByAddressMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AllowedAskDenoms queries all denoms allowed to be set in the AskPrice of a sell order
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsResponse> allowedAskDenoms(
        regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAllowedAskDenomsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Basket queries one basket by denom.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketResponse> basket(
        regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBasketMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Baskets lists all baskets in the ecocredit module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsResponse> baskets(
        regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBasketsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BasketCredits lists all ecocredits inside a given basket.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsResponse> basketCredits(
        regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBasketCreditsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CLASSES = 0;
  private static final int METHODID_CLASS_INFO = 1;
  private static final int METHODID_PROJECTS = 2;
  private static final int METHODID_PROJECT_INFO = 3;
  private static final int METHODID_BATCHES = 4;
  private static final int METHODID_BATCH_INFO = 5;
  private static final int METHODID_BALANCE = 6;
  private static final int METHODID_SUPPLY = 7;
  private static final int METHODID_CREDIT_TYPES = 8;
  private static final int METHODID_PARAMS = 9;
  private static final int METHODID_SELL_ORDER = 10;
  private static final int METHODID_SELL_ORDERS = 11;
  private static final int METHODID_SELL_ORDERS_BY_BATCH_DENOM = 12;
  private static final int METHODID_SELL_ORDERS_BY_ADDRESS = 13;
  private static final int METHODID_BUY_ORDER = 14;
  private static final int METHODID_BUY_ORDERS = 15;
  private static final int METHODID_BUY_ORDERS_BY_ADDRESS = 16;
  private static final int METHODID_ALLOWED_ASK_DENOMS = 17;
  private static final int METHODID_BASKET = 18;
  private static final int METHODID_BASKETS = 19;
  private static final int METHODID_BASKET_CREDITS = 20;

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
        case METHODID_CLASSES:
          serviceImpl.classes((regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesRequest) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassesResponse>) responseObserver);
          break;
        case METHODID_CLASS_INFO:
          serviceImpl.classInfo((regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoRequest) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryClassInfoResponse>) responseObserver);
          break;
        case METHODID_PROJECTS:
          serviceImpl.projects((regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsRequest) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectsResponse>) responseObserver);
          break;
        case METHODID_PROJECT_INFO:
          serviceImpl.projectInfo((regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoRequest) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryProjectInfoResponse>) responseObserver);
          break;
        case METHODID_BATCHES:
          serviceImpl.batches((regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesRequest) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchesResponse>) responseObserver);
          break;
        case METHODID_BATCH_INFO:
          serviceImpl.batchInfo((regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoRequest) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBatchInfoResponse>) responseObserver);
          break;
        case METHODID_BALANCE:
          serviceImpl.balance((regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceRequest) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBalanceResponse>) responseObserver);
          break;
        case METHODID_SUPPLY:
          serviceImpl.supply((regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyRequest) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySupplyResponse>) responseObserver);
          break;
        case METHODID_CREDIT_TYPES:
          serviceImpl.creditTypes((regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesRequest) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryCreditTypesResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_SELL_ORDER:
          serviceImpl.sellOrder((regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderRequest) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrderResponse>) responseObserver);
          break;
        case METHODID_SELL_ORDERS:
          serviceImpl.sellOrders((regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersRequest) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersResponse>) responseObserver);
          break;
        case METHODID_SELL_ORDERS_BY_BATCH_DENOM:
          serviceImpl.sellOrdersByBatchDenom((regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomRequest) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByBatchDenomResponse>) responseObserver);
          break;
        case METHODID_SELL_ORDERS_BY_ADDRESS:
          serviceImpl.sellOrdersByAddress((regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressRequest) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QuerySellOrdersByAddressResponse>) responseObserver);
          break;
        case METHODID_BUY_ORDER:
          serviceImpl.buyOrder((regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderRequest) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrderResponse>) responseObserver);
          break;
        case METHODID_BUY_ORDERS:
          serviceImpl.buyOrders((regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersRequest) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersResponse>) responseObserver);
          break;
        case METHODID_BUY_ORDERS_BY_ADDRESS:
          serviceImpl.buyOrdersByAddress((regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressRequest) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBuyOrdersByAddressResponse>) responseObserver);
          break;
        case METHODID_ALLOWED_ASK_DENOMS:
          serviceImpl.allowedAskDenoms((regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsRequest) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryAllowedAskDenomsResponse>) responseObserver);
          break;
        case METHODID_BASKET:
          serviceImpl.basket((regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketRequest) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketResponse>) responseObserver);
          break;
        case METHODID_BASKETS:
          serviceImpl.baskets((regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsRequest) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketsResponse>) responseObserver);
          break;
        case METHODID_BASKET_CREDITS:
          serviceImpl.basketCredits((regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsRequest) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.QueryOuterClass.QueryBasketCreditsResponse>) responseObserver);
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
      return regen.ecocredit.v1alpha2.QueryOuterClass.getDescriptor();
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
              .addMethod(getClassesMethod())
              .addMethod(getClassInfoMethod())
              .addMethod(getProjectsMethod())
              .addMethod(getProjectInfoMethod())
              .addMethod(getBatchesMethod())
              .addMethod(getBatchInfoMethod())
              .addMethod(getBalanceMethod())
              .addMethod(getSupplyMethod())
              .addMethod(getCreditTypesMethod())
              .addMethod(getParamsMethod())
              .addMethod(getSellOrderMethod())
              .addMethod(getSellOrdersMethod())
              .addMethod(getSellOrdersByBatchDenomMethod())
              .addMethod(getSellOrdersByAddressMethod())
              .addMethod(getBuyOrderMethod())
              .addMethod(getBuyOrdersMethod())
              .addMethod(getBuyOrdersByAddressMethod())
              .addMethod(getAllowedAskDenomsMethod())
              .addMethod(getBasketMethod())
              .addMethod(getBasketsMethod())
              .addMethod(getBasketCreditsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
