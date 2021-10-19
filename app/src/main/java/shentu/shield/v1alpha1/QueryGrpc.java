package shentu.shield.v1alpha1;

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
 * Query defines the gRPC querier service for shield module
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: shentu/shield/v1alpha1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "shentu.shield.v1alpha1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryPoolResponse> getPoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Pool",
      requestType = shentu.shield.v1alpha1.QueryOuterClass.QueryPoolRequest.class,
      responseType = shentu.shield.v1alpha1.QueryOuterClass.QueryPoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryPoolResponse> getPoolMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryPoolResponse> getPoolMethod;
    if ((getPoolMethod = QueryGrpc.getPoolMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPoolMethod = QueryGrpc.getPoolMethod) == null) {
          QueryGrpc.getPoolMethod = getPoolMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryPoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Pool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryPoolRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryPoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Pool"))
              .build();
        }
      }
    }
    return getPoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorResponse> getSponsorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Sponsor",
      requestType = shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorRequest.class,
      responseType = shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorResponse> getSponsorMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorRequest, shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorResponse> getSponsorMethod;
    if ((getSponsorMethod = QueryGrpc.getSponsorMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSponsorMethod = QueryGrpc.getSponsorMethod) == null) {
          QueryGrpc.getSponsorMethod = getSponsorMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorRequest, shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Sponsor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Sponsor"))
              .build();
        }
      }
    }
    return getSponsorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsResponse> getPoolsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Pools",
      requestType = shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsRequest.class,
      responseType = shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsResponse> getPoolsMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsResponse> getPoolsMethod;
    if ((getPoolsMethod = QueryGrpc.getPoolsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPoolsMethod = QueryGrpc.getPoolsMethod) == null) {
          QueryGrpc.getPoolsMethod = getPoolsMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Pools"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Pools"))
              .build();
        }
      }
    }
    return getPoolsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolPurchaseListsRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse> getPoolPurchaseListsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PoolPurchaseLists",
      requestType = shentu.shield.v1alpha1.QueryOuterClass.QueryPoolPurchaseListsRequest.class,
      responseType = shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolPurchaseListsRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse> getPoolPurchaseListsMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolPurchaseListsRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse> getPoolPurchaseListsMethod;
    if ((getPoolPurchaseListsMethod = QueryGrpc.getPoolPurchaseListsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPoolPurchaseListsMethod = QueryGrpc.getPoolPurchaseListsMethod) == null) {
          QueryGrpc.getPoolPurchaseListsMethod = getPoolPurchaseListsMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolPurchaseListsRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PoolPurchaseLists"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryPoolPurchaseListsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PoolPurchaseLists"))
              .build();
        }
      }
    }
    return getPoolPurchaseListsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse> getPurchaseListsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PurchaseLists",
      requestType = shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsRequest.class,
      responseType = shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse> getPurchaseListsMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse> getPurchaseListsMethod;
    if ((getPurchaseListsMethod = QueryGrpc.getPurchaseListsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPurchaseListsMethod = QueryGrpc.getPurchaseListsMethod) == null) {
          QueryGrpc.getPurchaseListsMethod = getPurchaseListsMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PurchaseLists"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PurchaseLists"))
              .build();
        }
      }
    }
    return getPurchaseListsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListResponse> getPurchaseListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PurchaseList",
      requestType = shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListRequest.class,
      responseType = shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListResponse> getPurchaseListMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListResponse> getPurchaseListMethod;
    if ((getPurchaseListMethod = QueryGrpc.getPurchaseListMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPurchaseListMethod = QueryGrpc.getPurchaseListMethod) == null) {
          QueryGrpc.getPurchaseListMethod = getPurchaseListMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PurchaseList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PurchaseList"))
              .build();
        }
      }
    }
    return getPurchaseListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesResponse> getPurchasesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Purchases",
      requestType = shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesRequest.class,
      responseType = shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesResponse> getPurchasesMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesResponse> getPurchasesMethod;
    if ((getPurchasesMethod = QueryGrpc.getPurchasesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPurchasesMethod = QueryGrpc.getPurchasesMethod) == null) {
          QueryGrpc.getPurchasesMethod = getPurchasesMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Purchases"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Purchases"))
              .build();
        }
      }
    }
    return getPurchasesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryProviderRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryProviderResponse> getProviderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Provider",
      requestType = shentu.shield.v1alpha1.QueryOuterClass.QueryProviderRequest.class,
      responseType = shentu.shield.v1alpha1.QueryOuterClass.QueryProviderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryProviderRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryProviderResponse> getProviderMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryProviderRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryProviderResponse> getProviderMethod;
    if ((getProviderMethod = QueryGrpc.getProviderMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProviderMethod = QueryGrpc.getProviderMethod) == null) {
          QueryGrpc.getProviderMethod = getProviderMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.QueryOuterClass.QueryProviderRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryProviderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Provider"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryProviderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryProviderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Provider"))
              .build();
        }
      }
    }
    return getProviderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersResponse> getProvidersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Providers",
      requestType = shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersRequest.class,
      responseType = shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersResponse> getProvidersMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersResponse> getProvidersMethod;
    if ((getProvidersMethod = QueryGrpc.getProvidersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProvidersMethod = QueryGrpc.getProvidersMethod) == null) {
          QueryGrpc.getProvidersMethod = getProvidersMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Providers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Providers"))
              .build();
        }
      }
    }
    return getProvidersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsResponse> getPoolParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PoolParams",
      requestType = shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsRequest.class,
      responseType = shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsResponse> getPoolParamsMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsResponse> getPoolParamsMethod;
    if ((getPoolParamsMethod = QueryGrpc.getPoolParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPoolParamsMethod = QueryGrpc.getPoolParamsMethod) == null) {
          QueryGrpc.getPoolParamsMethod = getPoolParamsMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PoolParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PoolParams"))
              .build();
        }
      }
    }
    return getPoolParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsResponse> getClaimParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClaimParams",
      requestType = shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsRequest.class,
      responseType = shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsResponse> getClaimParamsMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsResponse> getClaimParamsMethod;
    if ((getClaimParamsMethod = QueryGrpc.getClaimParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getClaimParamsMethod = QueryGrpc.getClaimParamsMethod) == null) {
          QueryGrpc.getClaimParamsMethod = getClaimParamsMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClaimParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ClaimParams"))
              .build();
        }
      }
    }
    return getClaimParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusResponse> getShieldStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ShieldStatus",
      requestType = shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusRequest.class,
      responseType = shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusResponse> getShieldStatusMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusResponse> getShieldStatusMethod;
    if ((getShieldStatusMethod = QueryGrpc.getShieldStatusMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getShieldStatusMethod = QueryGrpc.getShieldStatusMethod) == null) {
          QueryGrpc.getShieldStatusMethod = getShieldStatusMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ShieldStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ShieldStatus"))
              .build();
        }
      }
    }
    return getShieldStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingResponse> getShieldStakingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ShieldStaking",
      requestType = shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRequest.class,
      responseType = shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingResponse> getShieldStakingMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingResponse> getShieldStakingMethod;
    if ((getShieldStakingMethod = QueryGrpc.getShieldStakingMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getShieldStakingMethod = QueryGrpc.getShieldStakingMethod) == null) {
          QueryGrpc.getShieldStakingMethod = getShieldStakingMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ShieldStaking"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ShieldStaking"))
              .build();
        }
      }
    }
    return getShieldStakingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateResponse> getShieldStakingRateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ShieldStakingRate",
      requestType = shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateRequest.class,
      responseType = shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateResponse> getShieldStakingRateMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateResponse> getShieldStakingRateMethod;
    if ((getShieldStakingRateMethod = QueryGrpc.getShieldStakingRateMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getShieldStakingRateMethod = QueryGrpc.getShieldStakingRateMethod) == null) {
          QueryGrpc.getShieldStakingRateMethod = getShieldStakingRateMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ShieldStakingRate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ShieldStakingRate"))
              .build();
        }
      }
    }
    return getShieldStakingRateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementResponse> getReimbursementMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Reimbursement",
      requestType = shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementRequest.class,
      responseType = shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementResponse> getReimbursementMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementResponse> getReimbursementMethod;
    if ((getReimbursementMethod = QueryGrpc.getReimbursementMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getReimbursementMethod = QueryGrpc.getReimbursementMethod) == null) {
          QueryGrpc.getReimbursementMethod = getReimbursementMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Reimbursement"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Reimbursement"))
              .build();
        }
      }
    }
    return getReimbursementMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsResponse> getReimbursementsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Reimbursements",
      requestType = shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsRequest.class,
      responseType = shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsRequest,
      shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsResponse> getReimbursementsMethod() {
    io.grpc.MethodDescriptor<shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsResponse> getReimbursementsMethod;
    if ((getReimbursementsMethod = QueryGrpc.getReimbursementsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getReimbursementsMethod = QueryGrpc.getReimbursementsMethod) == null) {
          QueryGrpc.getReimbursementsMethod = getReimbursementsMethod =
              io.grpc.MethodDescriptor.<shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsRequest, shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Reimbursements"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Reimbursements"))
              .build();
        }
      }
    }
    return getReimbursementsMethod;
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
   * Query defines the gRPC querier service for shield module
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     */
    public void pool(shentu.shield.v1alpha1.QueryOuterClass.QueryPoolRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPoolMethod(), responseObserver);
    }

    /**
     */
    public void sponsor(shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSponsorMethod(), responseObserver);
    }

    /**
     */
    public void pools(shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPoolsMethod(), responseObserver);
    }

    /**
     */
    public void poolPurchaseLists(shentu.shield.v1alpha1.QueryOuterClass.QueryPoolPurchaseListsRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPoolPurchaseListsMethod(), responseObserver);
    }

    /**
     */
    public void purchaseLists(shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPurchaseListsMethod(), responseObserver);
    }

    /**
     */
    public void purchaseList(shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPurchaseListMethod(), responseObserver);
    }

    /**
     */
    public void purchases(shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPurchasesMethod(), responseObserver);
    }

    /**
     */
    public void provider(shentu.shield.v1alpha1.QueryOuterClass.QueryProviderRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryProviderResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProviderMethod(), responseObserver);
    }

    /**
     */
    public void providers(shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProvidersMethod(), responseObserver);
    }

    /**
     */
    public void poolParams(shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPoolParamsMethod(), responseObserver);
    }

    /**
     */
    public void claimParams(shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClaimParamsMethod(), responseObserver);
    }

    /**
     */
    public void shieldStatus(shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getShieldStatusMethod(), responseObserver);
    }

    /**
     */
    public void shieldStaking(shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getShieldStakingMethod(), responseObserver);
    }

    /**
     */
    public void shieldStakingRate(shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getShieldStakingRateMethod(), responseObserver);
    }

    /**
     */
    public void reimbursement(shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReimbursementMethod(), responseObserver);
    }

    /**
     */
    public void reimbursements(shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReimbursementsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.QueryOuterClass.QueryPoolRequest,
                shentu.shield.v1alpha1.QueryOuterClass.QueryPoolResponse>(
                  this, METHODID_POOL)))
          .addMethod(
            getSponsorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorRequest,
                shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorResponse>(
                  this, METHODID_SPONSOR)))
          .addMethod(
            getPoolsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsRequest,
                shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsResponse>(
                  this, METHODID_POOLS)))
          .addMethod(
            getPoolPurchaseListsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.QueryOuterClass.QueryPoolPurchaseListsRequest,
                shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse>(
                  this, METHODID_POOL_PURCHASE_LISTS)))
          .addMethod(
            getPurchaseListsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsRequest,
                shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse>(
                  this, METHODID_PURCHASE_LISTS)))
          .addMethod(
            getPurchaseListMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListRequest,
                shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListResponse>(
                  this, METHODID_PURCHASE_LIST)))
          .addMethod(
            getPurchasesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesRequest,
                shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesResponse>(
                  this, METHODID_PURCHASES)))
          .addMethod(
            getProviderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.QueryOuterClass.QueryProviderRequest,
                shentu.shield.v1alpha1.QueryOuterClass.QueryProviderResponse>(
                  this, METHODID_PROVIDER)))
          .addMethod(
            getProvidersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersRequest,
                shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersResponse>(
                  this, METHODID_PROVIDERS)))
          .addMethod(
            getPoolParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsRequest,
                shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsResponse>(
                  this, METHODID_POOL_PARAMS)))
          .addMethod(
            getClaimParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsRequest,
                shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsResponse>(
                  this, METHODID_CLAIM_PARAMS)))
          .addMethod(
            getShieldStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusRequest,
                shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusResponse>(
                  this, METHODID_SHIELD_STATUS)))
          .addMethod(
            getShieldStakingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRequest,
                shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingResponse>(
                  this, METHODID_SHIELD_STAKING)))
          .addMethod(
            getShieldStakingRateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateRequest,
                shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateResponse>(
                  this, METHODID_SHIELD_STAKING_RATE)))
          .addMethod(
            getReimbursementMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementRequest,
                shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementResponse>(
                  this, METHODID_REIMBURSEMENT)))
          .addMethod(
            getReimbursementsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsRequest,
                shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsResponse>(
                  this, METHODID_REIMBURSEMENTS)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for shield module
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
    public void pool(shentu.shield.v1alpha1.QueryOuterClass.QueryPoolRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sponsor(shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSponsorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void pools(shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPoolsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void poolPurchaseLists(shentu.shield.v1alpha1.QueryOuterClass.QueryPoolPurchaseListsRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPoolPurchaseListsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void purchaseLists(shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPurchaseListsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void purchaseList(shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPurchaseListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void purchases(shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPurchasesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void provider(shentu.shield.v1alpha1.QueryOuterClass.QueryProviderRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryProviderResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProviderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void providers(shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProvidersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void poolParams(shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPoolParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void claimParams(shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClaimParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void shieldStatus(shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getShieldStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void shieldStaking(shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getShieldStakingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void shieldStakingRate(shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getShieldStakingRateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void reimbursement(shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReimbursementMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void reimbursements(shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsRequest request,
        io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReimbursementsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for shield module
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
    public shentu.shield.v1alpha1.QueryOuterClass.QueryPoolResponse pool(shentu.shield.v1alpha1.QueryOuterClass.QueryPoolRequest request) {
      return blockingUnaryCall(
          getChannel(), getPoolMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorResponse sponsor(shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorRequest request) {
      return blockingUnaryCall(
          getChannel(), getSponsorMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsResponse pools(shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPoolsMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse poolPurchaseLists(shentu.shield.v1alpha1.QueryOuterClass.QueryPoolPurchaseListsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPoolPurchaseListsMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse purchaseLists(shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPurchaseListsMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListResponse purchaseList(shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListRequest request) {
      return blockingUnaryCall(
          getChannel(), getPurchaseListMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesResponse purchases(shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesRequest request) {
      return blockingUnaryCall(
          getChannel(), getPurchasesMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.QueryOuterClass.QueryProviderResponse provider(shentu.shield.v1alpha1.QueryOuterClass.QueryProviderRequest request) {
      return blockingUnaryCall(
          getChannel(), getProviderMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersResponse providers(shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersRequest request) {
      return blockingUnaryCall(
          getChannel(), getProvidersMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsResponse poolParams(shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPoolParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsResponse claimParams(shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getClaimParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusResponse shieldStatus(shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusRequest request) {
      return blockingUnaryCall(
          getChannel(), getShieldStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingResponse shieldStaking(shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRequest request) {
      return blockingUnaryCall(
          getChannel(), getShieldStakingMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateResponse shieldStakingRate(shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateRequest request) {
      return blockingUnaryCall(
          getChannel(), getShieldStakingRateMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementResponse reimbursement(shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementRequest request) {
      return blockingUnaryCall(
          getChannel(), getReimbursementMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsResponse reimbursements(shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsRequest request) {
      return blockingUnaryCall(
          getChannel(), getReimbursementsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service for shield module
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
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolResponse> pool(
        shentu.shield.v1alpha1.QueryOuterClass.QueryPoolRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPoolMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorResponse> sponsor(
        shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSponsorMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsResponse> pools(
        shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPoolsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse> poolPurchaseLists(
        shentu.shield.v1alpha1.QueryOuterClass.QueryPoolPurchaseListsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPoolPurchaseListsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse> purchaseLists(
        shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPurchaseListsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListResponse> purchaseList(
        shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPurchaseListMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesResponse> purchases(
        shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPurchasesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.QueryOuterClass.QueryProviderResponse> provider(
        shentu.shield.v1alpha1.QueryOuterClass.QueryProviderRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getProviderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersResponse> providers(
        shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getProvidersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsResponse> poolParams(
        shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPoolParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsResponse> claimParams(
        shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getClaimParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusResponse> shieldStatus(
        shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getShieldStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingResponse> shieldStaking(
        shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getShieldStakingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateResponse> shieldStakingRate(
        shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getShieldStakingRateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementResponse> reimbursement(
        shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getReimbursementMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsResponse> reimbursements(
        shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getReimbursementsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_POOL = 0;
  private static final int METHODID_SPONSOR = 1;
  private static final int METHODID_POOLS = 2;
  private static final int METHODID_POOL_PURCHASE_LISTS = 3;
  private static final int METHODID_PURCHASE_LISTS = 4;
  private static final int METHODID_PURCHASE_LIST = 5;
  private static final int METHODID_PURCHASES = 6;
  private static final int METHODID_PROVIDER = 7;
  private static final int METHODID_PROVIDERS = 8;
  private static final int METHODID_POOL_PARAMS = 9;
  private static final int METHODID_CLAIM_PARAMS = 10;
  private static final int METHODID_SHIELD_STATUS = 11;
  private static final int METHODID_SHIELD_STAKING = 12;
  private static final int METHODID_SHIELD_STAKING_RATE = 13;
  private static final int METHODID_REIMBURSEMENT = 14;
  private static final int METHODID_REIMBURSEMENTS = 15;

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
        case METHODID_POOL:
          serviceImpl.pool((shentu.shield.v1alpha1.QueryOuterClass.QueryPoolRequest) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolResponse>) responseObserver);
          break;
        case METHODID_SPONSOR:
          serviceImpl.sponsor((shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorRequest) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QuerySponsorResponse>) responseObserver);
          break;
        case METHODID_POOLS:
          serviceImpl.pools((shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsRequest) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolsResponse>) responseObserver);
          break;
        case METHODID_POOL_PURCHASE_LISTS:
          serviceImpl.poolPurchaseLists((shentu.shield.v1alpha1.QueryOuterClass.QueryPoolPurchaseListsRequest) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse>) responseObserver);
          break;
        case METHODID_PURCHASE_LISTS:
          serviceImpl.purchaseLists((shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsRequest) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListsResponse>) responseObserver);
          break;
        case METHODID_PURCHASE_LIST:
          serviceImpl.purchaseList((shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListRequest) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchaseListResponse>) responseObserver);
          break;
        case METHODID_PURCHASES:
          serviceImpl.purchases((shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesRequest) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryPurchasesResponse>) responseObserver);
          break;
        case METHODID_PROVIDER:
          serviceImpl.provider((shentu.shield.v1alpha1.QueryOuterClass.QueryProviderRequest) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryProviderResponse>) responseObserver);
          break;
        case METHODID_PROVIDERS:
          serviceImpl.providers((shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersRequest) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryProvidersResponse>) responseObserver);
          break;
        case METHODID_POOL_PARAMS:
          serviceImpl.poolParams((shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsRequest) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryPoolParamsResponse>) responseObserver);
          break;
        case METHODID_CLAIM_PARAMS:
          serviceImpl.claimParams((shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsRequest) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryClaimParamsResponse>) responseObserver);
          break;
        case METHODID_SHIELD_STATUS:
          serviceImpl.shieldStatus((shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusRequest) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStatusResponse>) responseObserver);
          break;
        case METHODID_SHIELD_STAKING:
          serviceImpl.shieldStaking((shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRequest) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingResponse>) responseObserver);
          break;
        case METHODID_SHIELD_STAKING_RATE:
          serviceImpl.shieldStakingRate((shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateRequest) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryShieldStakingRateResponse>) responseObserver);
          break;
        case METHODID_REIMBURSEMENT:
          serviceImpl.reimbursement((shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementRequest) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementResponse>) responseObserver);
          break;
        case METHODID_REIMBURSEMENTS:
          serviceImpl.reimbursements((shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsRequest) request,
              (io.grpc.stub.StreamObserver<shentu.shield.v1alpha1.QueryOuterClass.QueryReimbursementsResponse>) responseObserver);
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
      return shentu.shield.v1alpha1.QueryOuterClass.getDescriptor();
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
              .addMethod(getPoolMethod())
              .addMethod(getSponsorMethod())
              .addMethod(getPoolsMethod())
              .addMethod(getPoolPurchaseListsMethod())
              .addMethod(getPurchaseListsMethod())
              .addMethod(getPurchaseListMethod())
              .addMethod(getPurchasesMethod())
              .addMethod(getProviderMethod())
              .addMethod(getProvidersMethod())
              .addMethod(getPoolParamsMethod())
              .addMethod(getClaimParamsMethod())
              .addMethod(getShieldStatusMethod())
              .addMethod(getShieldStakingMethod())
              .addMethod(getShieldStakingRateMethod())
              .addMethod(getReimbursementMethod())
              .addMethod(getReimbursementsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
