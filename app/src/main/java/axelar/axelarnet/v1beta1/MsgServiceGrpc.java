package axelar.axelarnet.v1beta1;

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
 * Msg defines the axelarnet Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: axelar/axelarnet/v1beta1/service.proto")
public final class MsgServiceGrpc {

  private MsgServiceGrpc() {}

  public static final String SERVICE_NAME = "axelar.axelarnet.v1beta1.MsgService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.LinkRequest,
      axelar.axelarnet.v1beta1.Tx.LinkResponse> getLinkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Link",
      requestType = axelar.axelarnet.v1beta1.Tx.LinkRequest.class,
      responseType = axelar.axelarnet.v1beta1.Tx.LinkResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.LinkRequest,
      axelar.axelarnet.v1beta1.Tx.LinkResponse> getLinkMethod() {
    io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.LinkRequest, axelar.axelarnet.v1beta1.Tx.LinkResponse> getLinkMethod;
    if ((getLinkMethod = MsgServiceGrpc.getLinkMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getLinkMethod = MsgServiceGrpc.getLinkMethod) == null) {
          MsgServiceGrpc.getLinkMethod = getLinkMethod =
              io.grpc.MethodDescriptor.<axelar.axelarnet.v1beta1.Tx.LinkRequest, axelar.axelarnet.v1beta1.Tx.LinkResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Link"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.axelarnet.v1beta1.Tx.LinkRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.axelarnet.v1beta1.Tx.LinkResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("Link"))
              .build();
        }
      }
    }
    return getLinkMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.ConfirmDepositRequest,
      axelar.axelarnet.v1beta1.Tx.ConfirmDepositResponse> getConfirmDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConfirmDeposit",
      requestType = axelar.axelarnet.v1beta1.Tx.ConfirmDepositRequest.class,
      responseType = axelar.axelarnet.v1beta1.Tx.ConfirmDepositResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.ConfirmDepositRequest,
      axelar.axelarnet.v1beta1.Tx.ConfirmDepositResponse> getConfirmDepositMethod() {
    io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.ConfirmDepositRequest, axelar.axelarnet.v1beta1.Tx.ConfirmDepositResponse> getConfirmDepositMethod;
    if ((getConfirmDepositMethod = MsgServiceGrpc.getConfirmDepositMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getConfirmDepositMethod = MsgServiceGrpc.getConfirmDepositMethod) == null) {
          MsgServiceGrpc.getConfirmDepositMethod = getConfirmDepositMethod =
              io.grpc.MethodDescriptor.<axelar.axelarnet.v1beta1.Tx.ConfirmDepositRequest, axelar.axelarnet.v1beta1.Tx.ConfirmDepositResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConfirmDeposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.axelarnet.v1beta1.Tx.ConfirmDepositRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.axelarnet.v1beta1.Tx.ConfirmDepositResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("ConfirmDeposit"))
              .build();
        }
      }
    }
    return getConfirmDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersRequest,
      axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersResponse> getExecutePendingTransfersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExecutePendingTransfers",
      requestType = axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersRequest.class,
      responseType = axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersRequest,
      axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersResponse> getExecutePendingTransfersMethod() {
    io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersRequest, axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersResponse> getExecutePendingTransfersMethod;
    if ((getExecutePendingTransfersMethod = MsgServiceGrpc.getExecutePendingTransfersMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getExecutePendingTransfersMethod = MsgServiceGrpc.getExecutePendingTransfersMethod) == null) {
          MsgServiceGrpc.getExecutePendingTransfersMethod = getExecutePendingTransfersMethod =
              io.grpc.MethodDescriptor.<axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersRequest, axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExecutePendingTransfers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("ExecutePendingTransfers"))
              .build();
        }
      }
    }
    return getExecutePendingTransfersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.RegisterIBCPathRequest,
      axelar.axelarnet.v1beta1.Tx.RegisterIBCPathResponse> getRegisterIBCPathMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterIBCPath",
      requestType = axelar.axelarnet.v1beta1.Tx.RegisterIBCPathRequest.class,
      responseType = axelar.axelarnet.v1beta1.Tx.RegisterIBCPathResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.RegisterIBCPathRequest,
      axelar.axelarnet.v1beta1.Tx.RegisterIBCPathResponse> getRegisterIBCPathMethod() {
    io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.RegisterIBCPathRequest, axelar.axelarnet.v1beta1.Tx.RegisterIBCPathResponse> getRegisterIBCPathMethod;
    if ((getRegisterIBCPathMethod = MsgServiceGrpc.getRegisterIBCPathMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getRegisterIBCPathMethod = MsgServiceGrpc.getRegisterIBCPathMethod) == null) {
          MsgServiceGrpc.getRegisterIBCPathMethod = getRegisterIBCPathMethod =
              io.grpc.MethodDescriptor.<axelar.axelarnet.v1beta1.Tx.RegisterIBCPathRequest, axelar.axelarnet.v1beta1.Tx.RegisterIBCPathResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterIBCPath"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.axelarnet.v1beta1.Tx.RegisterIBCPathRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.axelarnet.v1beta1.Tx.RegisterIBCPathResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("RegisterIBCPath"))
              .build();
        }
      }
    }
    return getRegisterIBCPathMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainRequest,
      axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainResponse> getAddCosmosBasedChainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddCosmosBasedChain",
      requestType = axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainRequest.class,
      responseType = axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainRequest,
      axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainResponse> getAddCosmosBasedChainMethod() {
    io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainRequest, axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainResponse> getAddCosmosBasedChainMethod;
    if ((getAddCosmosBasedChainMethod = MsgServiceGrpc.getAddCosmosBasedChainMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getAddCosmosBasedChainMethod = MsgServiceGrpc.getAddCosmosBasedChainMethod) == null) {
          MsgServiceGrpc.getAddCosmosBasedChainMethod = getAddCosmosBasedChainMethod =
              io.grpc.MethodDescriptor.<axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainRequest, axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddCosmosBasedChain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("AddCosmosBasedChain"))
              .build();
        }
      }
    }
    return getAddCosmosBasedChainMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.RegisterAssetRequest,
      axelar.axelarnet.v1beta1.Tx.RegisterAssetResponse> getRegisterAssetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterAsset",
      requestType = axelar.axelarnet.v1beta1.Tx.RegisterAssetRequest.class,
      responseType = axelar.axelarnet.v1beta1.Tx.RegisterAssetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.RegisterAssetRequest,
      axelar.axelarnet.v1beta1.Tx.RegisterAssetResponse> getRegisterAssetMethod() {
    io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.RegisterAssetRequest, axelar.axelarnet.v1beta1.Tx.RegisterAssetResponse> getRegisterAssetMethod;
    if ((getRegisterAssetMethod = MsgServiceGrpc.getRegisterAssetMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getRegisterAssetMethod = MsgServiceGrpc.getRegisterAssetMethod) == null) {
          MsgServiceGrpc.getRegisterAssetMethod = getRegisterAssetMethod =
              io.grpc.MethodDescriptor.<axelar.axelarnet.v1beta1.Tx.RegisterAssetRequest, axelar.axelarnet.v1beta1.Tx.RegisterAssetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterAsset"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.axelarnet.v1beta1.Tx.RegisterAssetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.axelarnet.v1beta1.Tx.RegisterAssetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("RegisterAsset"))
              .build();
        }
      }
    }
    return getRegisterAssetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersRequest,
      axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersResponse> getRouteIBCTransfersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RouteIBCTransfers",
      requestType = axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersRequest.class,
      responseType = axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersRequest,
      axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersResponse> getRouteIBCTransfersMethod() {
    io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersRequest, axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersResponse> getRouteIBCTransfersMethod;
    if ((getRouteIBCTransfersMethod = MsgServiceGrpc.getRouteIBCTransfersMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getRouteIBCTransfersMethod = MsgServiceGrpc.getRouteIBCTransfersMethod) == null) {
          MsgServiceGrpc.getRouteIBCTransfersMethod = getRouteIBCTransfersMethod =
              io.grpc.MethodDescriptor.<axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersRequest, axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RouteIBCTransfers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("RouteIBCTransfers"))
              .build();
        }
      }
    }
    return getRouteIBCTransfersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorRequest,
      axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorResponse> getRegisterFeeCollectorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterFeeCollector",
      requestType = axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorRequest.class,
      responseType = axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorRequest,
      axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorResponse> getRegisterFeeCollectorMethod() {
    io.grpc.MethodDescriptor<axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorRequest, axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorResponse> getRegisterFeeCollectorMethod;
    if ((getRegisterFeeCollectorMethod = MsgServiceGrpc.getRegisterFeeCollectorMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getRegisterFeeCollectorMethod = MsgServiceGrpc.getRegisterFeeCollectorMethod) == null) {
          MsgServiceGrpc.getRegisterFeeCollectorMethod = getRegisterFeeCollectorMethod =
              io.grpc.MethodDescriptor.<axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorRequest, axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterFeeCollector"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("RegisterFeeCollector"))
              .build();
        }
      }
    }
    return getRegisterFeeCollectorMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MsgServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgServiceStub>() {
        @java.lang.Override
        public MsgServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgServiceStub(channel, callOptions);
        }
      };
    return MsgServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MsgServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgServiceBlockingStub>() {
        @java.lang.Override
        public MsgServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgServiceBlockingStub(channel, callOptions);
        }
      };
    return MsgServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MsgServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgServiceFutureStub>() {
        @java.lang.Override
        public MsgServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgServiceFutureStub(channel, callOptions);
        }
      };
    return MsgServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Msg defines the axelarnet Msg service.
   * </pre>
   */
  public static abstract class MsgServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void link(axelar.axelarnet.v1beta1.Tx.LinkRequest request,
        io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.LinkResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLinkMethod(), responseObserver);
    }

    /**
     */
    public void confirmDeposit(axelar.axelarnet.v1beta1.Tx.ConfirmDepositRequest request,
        io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.ConfirmDepositResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getConfirmDepositMethod(), responseObserver);
    }

    /**
     */
    public void executePendingTransfers(axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersRequest request,
        io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getExecutePendingTransfersMethod(), responseObserver);
    }

    /**
     */
    public void registerIBCPath(axelar.axelarnet.v1beta1.Tx.RegisterIBCPathRequest request,
        io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.RegisterIBCPathResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterIBCPathMethod(), responseObserver);
    }

    /**
     */
    public void addCosmosBasedChain(axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainRequest request,
        io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddCosmosBasedChainMethod(), responseObserver);
    }

    /**
     */
    public void registerAsset(axelar.axelarnet.v1beta1.Tx.RegisterAssetRequest request,
        io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.RegisterAssetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterAssetMethod(), responseObserver);
    }

    /**
     */
    public void routeIBCTransfers(axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersRequest request,
        io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRouteIBCTransfersMethod(), responseObserver);
    }

    /**
     */
    public void registerFeeCollector(axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorRequest request,
        io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterFeeCollectorMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLinkMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.axelarnet.v1beta1.Tx.LinkRequest,
                axelar.axelarnet.v1beta1.Tx.LinkResponse>(
                  this, METHODID_LINK)))
          .addMethod(
            getConfirmDepositMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.axelarnet.v1beta1.Tx.ConfirmDepositRequest,
                axelar.axelarnet.v1beta1.Tx.ConfirmDepositResponse>(
                  this, METHODID_CONFIRM_DEPOSIT)))
          .addMethod(
            getExecutePendingTransfersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersRequest,
                axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersResponse>(
                  this, METHODID_EXECUTE_PENDING_TRANSFERS)))
          .addMethod(
            getRegisterIBCPathMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.axelarnet.v1beta1.Tx.RegisterIBCPathRequest,
                axelar.axelarnet.v1beta1.Tx.RegisterIBCPathResponse>(
                  this, METHODID_REGISTER_IBCPATH)))
          .addMethod(
            getAddCosmosBasedChainMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainRequest,
                axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainResponse>(
                  this, METHODID_ADD_COSMOS_BASED_CHAIN)))
          .addMethod(
            getRegisterAssetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.axelarnet.v1beta1.Tx.RegisterAssetRequest,
                axelar.axelarnet.v1beta1.Tx.RegisterAssetResponse>(
                  this, METHODID_REGISTER_ASSET)))
          .addMethod(
            getRouteIBCTransfersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersRequest,
                axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersResponse>(
                  this, METHODID_ROUTE_IBCTRANSFERS)))
          .addMethod(
            getRegisterFeeCollectorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorRequest,
                axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorResponse>(
                  this, METHODID_REGISTER_FEE_COLLECTOR)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the axelarnet Msg service.
   * </pre>
   */
  public static final class MsgServiceStub extends io.grpc.stub.AbstractAsyncStub<MsgServiceStub> {
    private MsgServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgServiceStub(channel, callOptions);
    }

    /**
     */
    public void link(axelar.axelarnet.v1beta1.Tx.LinkRequest request,
        io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.LinkResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLinkMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void confirmDeposit(axelar.axelarnet.v1beta1.Tx.ConfirmDepositRequest request,
        io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.ConfirmDepositResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConfirmDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void executePendingTransfers(axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersRequest request,
        io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExecutePendingTransfersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void registerIBCPath(axelar.axelarnet.v1beta1.Tx.RegisterIBCPathRequest request,
        io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.RegisterIBCPathResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterIBCPathMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addCosmosBasedChain(axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainRequest request,
        io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddCosmosBasedChainMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void registerAsset(axelar.axelarnet.v1beta1.Tx.RegisterAssetRequest request,
        io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.RegisterAssetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterAssetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void routeIBCTransfers(axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersRequest request,
        io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRouteIBCTransfersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void registerFeeCollector(axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorRequest request,
        io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterFeeCollectorMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the axelarnet Msg service.
   * </pre>
   */
  public static final class MsgServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<MsgServiceBlockingStub> {
    private MsgServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public axelar.axelarnet.v1beta1.Tx.LinkResponse link(axelar.axelarnet.v1beta1.Tx.LinkRequest request) {
      return blockingUnaryCall(
          getChannel(), getLinkMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.axelarnet.v1beta1.Tx.ConfirmDepositResponse confirmDeposit(axelar.axelarnet.v1beta1.Tx.ConfirmDepositRequest request) {
      return blockingUnaryCall(
          getChannel(), getConfirmDepositMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersResponse executePendingTransfers(axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersRequest request) {
      return blockingUnaryCall(
          getChannel(), getExecutePendingTransfersMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.axelarnet.v1beta1.Tx.RegisterIBCPathResponse registerIBCPath(axelar.axelarnet.v1beta1.Tx.RegisterIBCPathRequest request) {
      return blockingUnaryCall(
          getChannel(), getRegisterIBCPathMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainResponse addCosmosBasedChain(axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainRequest request) {
      return blockingUnaryCall(
          getChannel(), getAddCosmosBasedChainMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.axelarnet.v1beta1.Tx.RegisterAssetResponse registerAsset(axelar.axelarnet.v1beta1.Tx.RegisterAssetRequest request) {
      return blockingUnaryCall(
          getChannel(), getRegisterAssetMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersResponse routeIBCTransfers(axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersRequest request) {
      return blockingUnaryCall(
          getChannel(), getRouteIBCTransfersMethod(), getCallOptions(), request);
    }

    /**
     */
    public axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorResponse registerFeeCollector(axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorRequest request) {
      return blockingUnaryCall(
          getChannel(), getRegisterFeeCollectorMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the axelarnet Msg service.
   * </pre>
   */
  public static final class MsgServiceFutureStub extends io.grpc.stub.AbstractFutureStub<MsgServiceFutureStub> {
    private MsgServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.axelarnet.v1beta1.Tx.LinkResponse> link(
        axelar.axelarnet.v1beta1.Tx.LinkRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLinkMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.axelarnet.v1beta1.Tx.ConfirmDepositResponse> confirmDeposit(
        axelar.axelarnet.v1beta1.Tx.ConfirmDepositRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getConfirmDepositMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersResponse> executePendingTransfers(
        axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getExecutePendingTransfersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.axelarnet.v1beta1.Tx.RegisterIBCPathResponse> registerIBCPath(
        axelar.axelarnet.v1beta1.Tx.RegisterIBCPathRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterIBCPathMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainResponse> addCosmosBasedChain(
        axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAddCosmosBasedChainMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.axelarnet.v1beta1.Tx.RegisterAssetResponse> registerAsset(
        axelar.axelarnet.v1beta1.Tx.RegisterAssetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterAssetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersResponse> routeIBCTransfers(
        axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRouteIBCTransfersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorResponse> registerFeeCollector(
        axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterFeeCollectorMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LINK = 0;
  private static final int METHODID_CONFIRM_DEPOSIT = 1;
  private static final int METHODID_EXECUTE_PENDING_TRANSFERS = 2;
  private static final int METHODID_REGISTER_IBCPATH = 3;
  private static final int METHODID_ADD_COSMOS_BASED_CHAIN = 4;
  private static final int METHODID_REGISTER_ASSET = 5;
  private static final int METHODID_ROUTE_IBCTRANSFERS = 6;
  private static final int METHODID_REGISTER_FEE_COLLECTOR = 7;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MsgServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MsgServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LINK:
          serviceImpl.link((axelar.axelarnet.v1beta1.Tx.LinkRequest) request,
              (io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.LinkResponse>) responseObserver);
          break;
        case METHODID_CONFIRM_DEPOSIT:
          serviceImpl.confirmDeposit((axelar.axelarnet.v1beta1.Tx.ConfirmDepositRequest) request,
              (io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.ConfirmDepositResponse>) responseObserver);
          break;
        case METHODID_EXECUTE_PENDING_TRANSFERS:
          serviceImpl.executePendingTransfers((axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersRequest) request,
              (io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.ExecutePendingTransfersResponse>) responseObserver);
          break;
        case METHODID_REGISTER_IBCPATH:
          serviceImpl.registerIBCPath((axelar.axelarnet.v1beta1.Tx.RegisterIBCPathRequest) request,
              (io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.RegisterIBCPathResponse>) responseObserver);
          break;
        case METHODID_ADD_COSMOS_BASED_CHAIN:
          serviceImpl.addCosmosBasedChain((axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainRequest) request,
              (io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.AddCosmosBasedChainResponse>) responseObserver);
          break;
        case METHODID_REGISTER_ASSET:
          serviceImpl.registerAsset((axelar.axelarnet.v1beta1.Tx.RegisterAssetRequest) request,
              (io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.RegisterAssetResponse>) responseObserver);
          break;
        case METHODID_ROUTE_IBCTRANSFERS:
          serviceImpl.routeIBCTransfers((axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersRequest) request,
              (io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.RouteIBCTransfersResponse>) responseObserver);
          break;
        case METHODID_REGISTER_FEE_COLLECTOR:
          serviceImpl.registerFeeCollector((axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorRequest) request,
              (io.grpc.stub.StreamObserver<axelar.axelarnet.v1beta1.Tx.RegisterFeeCollectorResponse>) responseObserver);
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

  private static abstract class MsgServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return axelar.axelarnet.v1beta1.Service.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MsgService");
    }
  }

  private static final class MsgServiceFileDescriptorSupplier
      extends MsgServiceBaseDescriptorSupplier {
    MsgServiceFileDescriptorSupplier() {}
  }

  private static final class MsgServiceMethodDescriptorSupplier
      extends MsgServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MsgServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (MsgServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MsgServiceFileDescriptorSupplier())
              .addMethod(getLinkMethod())
              .addMethod(getConfirmDepositMethod())
              .addMethod(getExecutePendingTransfersMethod())
              .addMethod(getRegisterIBCPathMethod())
              .addMethod(getAddCosmosBasedChainMethod())
              .addMethod(getRegisterAssetMethod())
              .addMethod(getRouteIBCTransfersMethod())
              .addMethod(getRegisterFeeCollectorMethod())
              .build();
        }
      }
    }
    return result;
  }
}
