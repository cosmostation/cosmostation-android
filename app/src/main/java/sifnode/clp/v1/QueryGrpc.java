package sifnode.clp.v1;

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
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: sifnode/clp/v1/querier.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "sifnode.clp.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.PoolReq,
      sifnode.clp.v1.Querier.PoolRes> getGetPoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPool",
      requestType = sifnode.clp.v1.Querier.PoolReq.class,
      responseType = sifnode.clp.v1.Querier.PoolRes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.PoolReq,
      sifnode.clp.v1.Querier.PoolRes> getGetPoolMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.PoolReq, sifnode.clp.v1.Querier.PoolRes> getGetPoolMethod;
    if ((getGetPoolMethod = QueryGrpc.getGetPoolMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetPoolMethod = QueryGrpc.getGetPoolMethod) == null) {
          QueryGrpc.getGetPoolMethod = getGetPoolMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Querier.PoolReq, sifnode.clp.v1.Querier.PoolRes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetPool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Querier.PoolReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Querier.PoolRes.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetPool"))
              .build();
        }
      }
    }
    return getGetPoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.PoolsReq,
      sifnode.clp.v1.Querier.PoolsRes> getGetPoolsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPools",
      requestType = sifnode.clp.v1.Querier.PoolsReq.class,
      responseType = sifnode.clp.v1.Querier.PoolsRes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.PoolsReq,
      sifnode.clp.v1.Querier.PoolsRes> getGetPoolsMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.PoolsReq, sifnode.clp.v1.Querier.PoolsRes> getGetPoolsMethod;
    if ((getGetPoolsMethod = QueryGrpc.getGetPoolsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetPoolsMethod = QueryGrpc.getGetPoolsMethod) == null) {
          QueryGrpc.getGetPoolsMethod = getGetPoolsMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Querier.PoolsReq, sifnode.clp.v1.Querier.PoolsRes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetPools"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Querier.PoolsReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Querier.PoolsRes.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetPools"))
              .build();
        }
      }
    }
    return getGetPoolsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.LiquidityProviderReq,
      sifnode.clp.v1.Querier.LiquidityProviderRes> getGetLiquidityProviderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetLiquidityProvider",
      requestType = sifnode.clp.v1.Querier.LiquidityProviderReq.class,
      responseType = sifnode.clp.v1.Querier.LiquidityProviderRes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.LiquidityProviderReq,
      sifnode.clp.v1.Querier.LiquidityProviderRes> getGetLiquidityProviderMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.LiquidityProviderReq, sifnode.clp.v1.Querier.LiquidityProviderRes> getGetLiquidityProviderMethod;
    if ((getGetLiquidityProviderMethod = QueryGrpc.getGetLiquidityProviderMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetLiquidityProviderMethod = QueryGrpc.getGetLiquidityProviderMethod) == null) {
          QueryGrpc.getGetLiquidityProviderMethod = getGetLiquidityProviderMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Querier.LiquidityProviderReq, sifnode.clp.v1.Querier.LiquidityProviderRes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetLiquidityProvider"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Querier.LiquidityProviderReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Querier.LiquidityProviderRes.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetLiquidityProvider"))
              .build();
        }
      }
    }
    return getGetLiquidityProviderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.LiquidityProviderDataReq,
      sifnode.clp.v1.Querier.LiquidityProviderDataRes> getGetLiquidityProviderDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetLiquidityProviderData",
      requestType = sifnode.clp.v1.Querier.LiquidityProviderDataReq.class,
      responseType = sifnode.clp.v1.Querier.LiquidityProviderDataRes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.LiquidityProviderDataReq,
      sifnode.clp.v1.Querier.LiquidityProviderDataRes> getGetLiquidityProviderDataMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.LiquidityProviderDataReq, sifnode.clp.v1.Querier.LiquidityProviderDataRes> getGetLiquidityProviderDataMethod;
    if ((getGetLiquidityProviderDataMethod = QueryGrpc.getGetLiquidityProviderDataMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetLiquidityProviderDataMethod = QueryGrpc.getGetLiquidityProviderDataMethod) == null) {
          QueryGrpc.getGetLiquidityProviderDataMethod = getGetLiquidityProviderDataMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Querier.LiquidityProviderDataReq, sifnode.clp.v1.Querier.LiquidityProviderDataRes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetLiquidityProviderData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Querier.LiquidityProviderDataReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Querier.LiquidityProviderDataRes.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetLiquidityProviderData"))
              .build();
        }
      }
    }
    return getGetLiquidityProviderDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.AssetListReq,
      sifnode.clp.v1.Querier.AssetListRes> getGetAssetListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAssetList",
      requestType = sifnode.clp.v1.Querier.AssetListReq.class,
      responseType = sifnode.clp.v1.Querier.AssetListRes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.AssetListReq,
      sifnode.clp.v1.Querier.AssetListRes> getGetAssetListMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.AssetListReq, sifnode.clp.v1.Querier.AssetListRes> getGetAssetListMethod;
    if ((getGetAssetListMethod = QueryGrpc.getGetAssetListMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetAssetListMethod = QueryGrpc.getGetAssetListMethod) == null) {
          QueryGrpc.getGetAssetListMethod = getGetAssetListMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Querier.AssetListReq, sifnode.clp.v1.Querier.AssetListRes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAssetList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Querier.AssetListReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Querier.AssetListRes.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetAssetList"))
              .build();
        }
      }
    }
    return getGetAssetListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.LiquidityProvidersReq,
      sifnode.clp.v1.Querier.LiquidityProvidersRes> getGetLiquidityProvidersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetLiquidityProviders",
      requestType = sifnode.clp.v1.Querier.LiquidityProvidersReq.class,
      responseType = sifnode.clp.v1.Querier.LiquidityProvidersRes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.LiquidityProvidersReq,
      sifnode.clp.v1.Querier.LiquidityProvidersRes> getGetLiquidityProvidersMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.LiquidityProvidersReq, sifnode.clp.v1.Querier.LiquidityProvidersRes> getGetLiquidityProvidersMethod;
    if ((getGetLiquidityProvidersMethod = QueryGrpc.getGetLiquidityProvidersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetLiquidityProvidersMethod = QueryGrpc.getGetLiquidityProvidersMethod) == null) {
          QueryGrpc.getGetLiquidityProvidersMethod = getGetLiquidityProvidersMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Querier.LiquidityProvidersReq, sifnode.clp.v1.Querier.LiquidityProvidersRes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetLiquidityProviders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Querier.LiquidityProvidersReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Querier.LiquidityProvidersRes.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetLiquidityProviders"))
              .build();
        }
      }
    }
    return getGetLiquidityProvidersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.LiquidityProviderListReq,
      sifnode.clp.v1.Querier.LiquidityProviderListRes> getGetLiquidityProviderListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetLiquidityProviderList",
      requestType = sifnode.clp.v1.Querier.LiquidityProviderListReq.class,
      responseType = sifnode.clp.v1.Querier.LiquidityProviderListRes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.LiquidityProviderListReq,
      sifnode.clp.v1.Querier.LiquidityProviderListRes> getGetLiquidityProviderListMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.LiquidityProviderListReq, sifnode.clp.v1.Querier.LiquidityProviderListRes> getGetLiquidityProviderListMethod;
    if ((getGetLiquidityProviderListMethod = QueryGrpc.getGetLiquidityProviderListMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetLiquidityProviderListMethod = QueryGrpc.getGetLiquidityProviderListMethod) == null) {
          QueryGrpc.getGetLiquidityProviderListMethod = getGetLiquidityProviderListMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Querier.LiquidityProviderListReq, sifnode.clp.v1.Querier.LiquidityProviderListRes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetLiquidityProviderList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Querier.LiquidityProviderListReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Querier.LiquidityProviderListRes.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetLiquidityProviderList"))
              .build();
        }
      }
    }
    return getGetLiquidityProviderListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.ParamsReq,
      sifnode.clp.v1.Querier.ParamsRes> getGetParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetParams",
      requestType = sifnode.clp.v1.Querier.ParamsReq.class,
      responseType = sifnode.clp.v1.Querier.ParamsRes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.ParamsReq,
      sifnode.clp.v1.Querier.ParamsRes> getGetParamsMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.ParamsReq, sifnode.clp.v1.Querier.ParamsRes> getGetParamsMethod;
    if ((getGetParamsMethod = QueryGrpc.getGetParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetParamsMethod = QueryGrpc.getGetParamsMethod) == null) {
          QueryGrpc.getGetParamsMethod = getGetParamsMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Querier.ParamsReq, sifnode.clp.v1.Querier.ParamsRes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Querier.ParamsReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Querier.ParamsRes.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetParams"))
              .build();
        }
      }
    }
    return getGetParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.RewardParamsReq,
      sifnode.clp.v1.Querier.RewardParamsRes> getGetRewardParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetRewardParams",
      requestType = sifnode.clp.v1.Querier.RewardParamsReq.class,
      responseType = sifnode.clp.v1.Querier.RewardParamsRes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.RewardParamsReq,
      sifnode.clp.v1.Querier.RewardParamsRes> getGetRewardParamsMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.RewardParamsReq, sifnode.clp.v1.Querier.RewardParamsRes> getGetRewardParamsMethod;
    if ((getGetRewardParamsMethod = QueryGrpc.getGetRewardParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetRewardParamsMethod = QueryGrpc.getGetRewardParamsMethod) == null) {
          QueryGrpc.getGetRewardParamsMethod = getGetRewardParamsMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Querier.RewardParamsReq, sifnode.clp.v1.Querier.RewardParamsRes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetRewardParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Querier.RewardParamsReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Querier.RewardParamsRes.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetRewardParams"))
              .build();
        }
      }
    }
    return getGetRewardParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.PmtpParamsReq,
      sifnode.clp.v1.Querier.PmtpParamsRes> getGetPmtpParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPmtpParams",
      requestType = sifnode.clp.v1.Querier.PmtpParamsReq.class,
      responseType = sifnode.clp.v1.Querier.PmtpParamsRes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.PmtpParamsReq,
      sifnode.clp.v1.Querier.PmtpParamsRes> getGetPmtpParamsMethod() {
    io.grpc.MethodDescriptor<sifnode.clp.v1.Querier.PmtpParamsReq, sifnode.clp.v1.Querier.PmtpParamsRes> getGetPmtpParamsMethod;
    if ((getGetPmtpParamsMethod = QueryGrpc.getGetPmtpParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGetPmtpParamsMethod = QueryGrpc.getGetPmtpParamsMethod) == null) {
          QueryGrpc.getGetPmtpParamsMethod = getGetPmtpParamsMethod =
              io.grpc.MethodDescriptor.<sifnode.clp.v1.Querier.PmtpParamsReq, sifnode.clp.v1.Querier.PmtpParamsRes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetPmtpParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Querier.PmtpParamsReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.clp.v1.Querier.PmtpParamsRes.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GetPmtpParams"))
              .build();
        }
      }
    }
    return getGetPmtpParamsMethod;
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
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     */
    public void getPool(sifnode.clp.v1.Querier.PoolReq request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.PoolRes> responseObserver) {
      asyncUnimplementedUnaryCall(getGetPoolMethod(), responseObserver);
    }

    /**
     */
    public void getPools(sifnode.clp.v1.Querier.PoolsReq request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.PoolsRes> responseObserver) {
      asyncUnimplementedUnaryCall(getGetPoolsMethod(), responseObserver);
    }

    /**
     */
    public void getLiquidityProvider(sifnode.clp.v1.Querier.LiquidityProviderReq request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.LiquidityProviderRes> responseObserver) {
      asyncUnimplementedUnaryCall(getGetLiquidityProviderMethod(), responseObserver);
    }

    /**
     */
    public void getLiquidityProviderData(sifnode.clp.v1.Querier.LiquidityProviderDataReq request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.LiquidityProviderDataRes> responseObserver) {
      asyncUnimplementedUnaryCall(getGetLiquidityProviderDataMethod(), responseObserver);
    }

    /**
     */
    public void getAssetList(sifnode.clp.v1.Querier.AssetListReq request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.AssetListRes> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAssetListMethod(), responseObserver);
    }

    /**
     */
    public void getLiquidityProviders(sifnode.clp.v1.Querier.LiquidityProvidersReq request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.LiquidityProvidersRes> responseObserver) {
      asyncUnimplementedUnaryCall(getGetLiquidityProvidersMethod(), responseObserver);
    }

    /**
     */
    public void getLiquidityProviderList(sifnode.clp.v1.Querier.LiquidityProviderListReq request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.LiquidityProviderListRes> responseObserver) {
      asyncUnimplementedUnaryCall(getGetLiquidityProviderListMethod(), responseObserver);
    }

    /**
     */
    public void getParams(sifnode.clp.v1.Querier.ParamsReq request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.ParamsRes> responseObserver) {
      asyncUnimplementedUnaryCall(getGetParamsMethod(), responseObserver);
    }

    /**
     */
    public void getRewardParams(sifnode.clp.v1.Querier.RewardParamsReq request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.RewardParamsRes> responseObserver) {
      asyncUnimplementedUnaryCall(getGetRewardParamsMethod(), responseObserver);
    }

    /**
     */
    public void getPmtpParams(sifnode.clp.v1.Querier.PmtpParamsReq request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.PmtpParamsRes> responseObserver) {
      asyncUnimplementedUnaryCall(getGetPmtpParamsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetPoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Querier.PoolReq,
                sifnode.clp.v1.Querier.PoolRes>(
                  this, METHODID_GET_POOL)))
          .addMethod(
            getGetPoolsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Querier.PoolsReq,
                sifnode.clp.v1.Querier.PoolsRes>(
                  this, METHODID_GET_POOLS)))
          .addMethod(
            getGetLiquidityProviderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Querier.LiquidityProviderReq,
                sifnode.clp.v1.Querier.LiquidityProviderRes>(
                  this, METHODID_GET_LIQUIDITY_PROVIDER)))
          .addMethod(
            getGetLiquidityProviderDataMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Querier.LiquidityProviderDataReq,
                sifnode.clp.v1.Querier.LiquidityProviderDataRes>(
                  this, METHODID_GET_LIQUIDITY_PROVIDER_DATA)))
          .addMethod(
            getGetAssetListMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Querier.AssetListReq,
                sifnode.clp.v1.Querier.AssetListRes>(
                  this, METHODID_GET_ASSET_LIST)))
          .addMethod(
            getGetLiquidityProvidersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Querier.LiquidityProvidersReq,
                sifnode.clp.v1.Querier.LiquidityProvidersRes>(
                  this, METHODID_GET_LIQUIDITY_PROVIDERS)))
          .addMethod(
            getGetLiquidityProviderListMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Querier.LiquidityProviderListReq,
                sifnode.clp.v1.Querier.LiquidityProviderListRes>(
                  this, METHODID_GET_LIQUIDITY_PROVIDER_LIST)))
          .addMethod(
            getGetParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Querier.ParamsReq,
                sifnode.clp.v1.Querier.ParamsRes>(
                  this, METHODID_GET_PARAMS)))
          .addMethod(
            getGetRewardParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Querier.RewardParamsReq,
                sifnode.clp.v1.Querier.RewardParamsRes>(
                  this, METHODID_GET_REWARD_PARAMS)))
          .addMethod(
            getGetPmtpParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.clp.v1.Querier.PmtpParamsReq,
                sifnode.clp.v1.Querier.PmtpParamsRes>(
                  this, METHODID_GET_PMTP_PARAMS)))
          .build();
    }
  }

  /**
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
    public void getPool(sifnode.clp.v1.Querier.PoolReq request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.PoolRes> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetPoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPools(sifnode.clp.v1.Querier.PoolsReq request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.PoolsRes> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetPoolsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getLiquidityProvider(sifnode.clp.v1.Querier.LiquidityProviderReq request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.LiquidityProviderRes> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetLiquidityProviderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getLiquidityProviderData(sifnode.clp.v1.Querier.LiquidityProviderDataReq request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.LiquidityProviderDataRes> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetLiquidityProviderDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAssetList(sifnode.clp.v1.Querier.AssetListReq request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.AssetListRes> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAssetListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getLiquidityProviders(sifnode.clp.v1.Querier.LiquidityProvidersReq request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.LiquidityProvidersRes> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetLiquidityProvidersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getLiquidityProviderList(sifnode.clp.v1.Querier.LiquidityProviderListReq request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.LiquidityProviderListRes> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetLiquidityProviderListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getParams(sifnode.clp.v1.Querier.ParamsReq request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.ParamsRes> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getRewardParams(sifnode.clp.v1.Querier.RewardParamsReq request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.RewardParamsRes> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetRewardParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPmtpParams(sifnode.clp.v1.Querier.PmtpParamsReq request,
        io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.PmtpParamsRes> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetPmtpParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
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
    public sifnode.clp.v1.Querier.PoolRes getPool(sifnode.clp.v1.Querier.PoolReq request) {
      return blockingUnaryCall(
          getChannel(), getGetPoolMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Querier.PoolsRes getPools(sifnode.clp.v1.Querier.PoolsReq request) {
      return blockingUnaryCall(
          getChannel(), getGetPoolsMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Querier.LiquidityProviderRes getLiquidityProvider(sifnode.clp.v1.Querier.LiquidityProviderReq request) {
      return blockingUnaryCall(
          getChannel(), getGetLiquidityProviderMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Querier.LiquidityProviderDataRes getLiquidityProviderData(sifnode.clp.v1.Querier.LiquidityProviderDataReq request) {
      return blockingUnaryCall(
          getChannel(), getGetLiquidityProviderDataMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Querier.AssetListRes getAssetList(sifnode.clp.v1.Querier.AssetListReq request) {
      return blockingUnaryCall(
          getChannel(), getGetAssetListMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Querier.LiquidityProvidersRes getLiquidityProviders(sifnode.clp.v1.Querier.LiquidityProvidersReq request) {
      return blockingUnaryCall(
          getChannel(), getGetLiquidityProvidersMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Querier.LiquidityProviderListRes getLiquidityProviderList(sifnode.clp.v1.Querier.LiquidityProviderListReq request) {
      return blockingUnaryCall(
          getChannel(), getGetLiquidityProviderListMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Querier.ParamsRes getParams(sifnode.clp.v1.Querier.ParamsReq request) {
      return blockingUnaryCall(
          getChannel(), getGetParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Querier.RewardParamsRes getRewardParams(sifnode.clp.v1.Querier.RewardParamsReq request) {
      return blockingUnaryCall(
          getChannel(), getGetRewardParamsMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.clp.v1.Querier.PmtpParamsRes getPmtpParams(sifnode.clp.v1.Querier.PmtpParamsReq request) {
      return blockingUnaryCall(
          getChannel(), getGetPmtpParamsMethod(), getCallOptions(), request);
    }
  }

  /**
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
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Querier.PoolRes> getPool(
        sifnode.clp.v1.Querier.PoolReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetPoolMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Querier.PoolsRes> getPools(
        sifnode.clp.v1.Querier.PoolsReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetPoolsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Querier.LiquidityProviderRes> getLiquidityProvider(
        sifnode.clp.v1.Querier.LiquidityProviderReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetLiquidityProviderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Querier.LiquidityProviderDataRes> getLiquidityProviderData(
        sifnode.clp.v1.Querier.LiquidityProviderDataReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetLiquidityProviderDataMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Querier.AssetListRes> getAssetList(
        sifnode.clp.v1.Querier.AssetListReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAssetListMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Querier.LiquidityProvidersRes> getLiquidityProviders(
        sifnode.clp.v1.Querier.LiquidityProvidersReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetLiquidityProvidersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Querier.LiquidityProviderListRes> getLiquidityProviderList(
        sifnode.clp.v1.Querier.LiquidityProviderListReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetLiquidityProviderListMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Querier.ParamsRes> getParams(
        sifnode.clp.v1.Querier.ParamsReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Querier.RewardParamsRes> getRewardParams(
        sifnode.clp.v1.Querier.RewardParamsReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetRewardParamsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.clp.v1.Querier.PmtpParamsRes> getPmtpParams(
        sifnode.clp.v1.Querier.PmtpParamsReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetPmtpParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_POOL = 0;
  private static final int METHODID_GET_POOLS = 1;
  private static final int METHODID_GET_LIQUIDITY_PROVIDER = 2;
  private static final int METHODID_GET_LIQUIDITY_PROVIDER_DATA = 3;
  private static final int METHODID_GET_ASSET_LIST = 4;
  private static final int METHODID_GET_LIQUIDITY_PROVIDERS = 5;
  private static final int METHODID_GET_LIQUIDITY_PROVIDER_LIST = 6;
  private static final int METHODID_GET_PARAMS = 7;
  private static final int METHODID_GET_REWARD_PARAMS = 8;
  private static final int METHODID_GET_PMTP_PARAMS = 9;

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
        case METHODID_GET_POOL:
          serviceImpl.getPool((sifnode.clp.v1.Querier.PoolReq) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.PoolRes>) responseObserver);
          break;
        case METHODID_GET_POOLS:
          serviceImpl.getPools((sifnode.clp.v1.Querier.PoolsReq) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.PoolsRes>) responseObserver);
          break;
        case METHODID_GET_LIQUIDITY_PROVIDER:
          serviceImpl.getLiquidityProvider((sifnode.clp.v1.Querier.LiquidityProviderReq) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.LiquidityProviderRes>) responseObserver);
          break;
        case METHODID_GET_LIQUIDITY_PROVIDER_DATA:
          serviceImpl.getLiquidityProviderData((sifnode.clp.v1.Querier.LiquidityProviderDataReq) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.LiquidityProviderDataRes>) responseObserver);
          break;
        case METHODID_GET_ASSET_LIST:
          serviceImpl.getAssetList((sifnode.clp.v1.Querier.AssetListReq) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.AssetListRes>) responseObserver);
          break;
        case METHODID_GET_LIQUIDITY_PROVIDERS:
          serviceImpl.getLiquidityProviders((sifnode.clp.v1.Querier.LiquidityProvidersReq) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.LiquidityProvidersRes>) responseObserver);
          break;
        case METHODID_GET_LIQUIDITY_PROVIDER_LIST:
          serviceImpl.getLiquidityProviderList((sifnode.clp.v1.Querier.LiquidityProviderListReq) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.LiquidityProviderListRes>) responseObserver);
          break;
        case METHODID_GET_PARAMS:
          serviceImpl.getParams((sifnode.clp.v1.Querier.ParamsReq) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.ParamsRes>) responseObserver);
          break;
        case METHODID_GET_REWARD_PARAMS:
          serviceImpl.getRewardParams((sifnode.clp.v1.Querier.RewardParamsReq) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.RewardParamsRes>) responseObserver);
          break;
        case METHODID_GET_PMTP_PARAMS:
          serviceImpl.getPmtpParams((sifnode.clp.v1.Querier.PmtpParamsReq) request,
              (io.grpc.stub.StreamObserver<sifnode.clp.v1.Querier.PmtpParamsRes>) responseObserver);
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
      return sifnode.clp.v1.Querier.getDescriptor();
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
              .addMethod(getGetPoolMethod())
              .addMethod(getGetPoolsMethod())
              .addMethod(getGetLiquidityProviderMethod())
              .addMethod(getGetLiquidityProviderDataMethod())
              .addMethod(getGetAssetListMethod())
              .addMethod(getGetLiquidityProvidersMethod())
              .addMethod(getGetLiquidityProviderListMethod())
              .addMethod(getGetParamsMethod())
              .addMethod(getGetRewardParamsMethod())
              .addMethod(getGetPmtpParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
