package akash.market.v1beta2;

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
 * Msg defines the market Msg service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: akash/market/v1beta2/service.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "akash.market.v1beta2.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<akash.market.v1beta2.BidOuterClass.MsgCreateBid,
      akash.market.v1beta2.BidOuterClass.MsgCreateBidResponse> getCreateBidMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateBid",
      requestType = akash.market.v1beta2.BidOuterClass.MsgCreateBid.class,
      responseType = akash.market.v1beta2.BidOuterClass.MsgCreateBidResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.market.v1beta2.BidOuterClass.MsgCreateBid,
      akash.market.v1beta2.BidOuterClass.MsgCreateBidResponse> getCreateBidMethod() {
    io.grpc.MethodDescriptor<akash.market.v1beta2.BidOuterClass.MsgCreateBid, akash.market.v1beta2.BidOuterClass.MsgCreateBidResponse> getCreateBidMethod;
    if ((getCreateBidMethod = MsgGrpc.getCreateBidMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateBidMethod = MsgGrpc.getCreateBidMethod) == null) {
          MsgGrpc.getCreateBidMethod = getCreateBidMethod =
              io.grpc.MethodDescriptor.<akash.market.v1beta2.BidOuterClass.MsgCreateBid, akash.market.v1beta2.BidOuterClass.MsgCreateBidResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateBid"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.market.v1beta2.BidOuterClass.MsgCreateBid.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.market.v1beta2.BidOuterClass.MsgCreateBidResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateBid"))
              .build();
        }
      }
    }
    return getCreateBidMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.market.v1beta2.BidOuterClass.MsgCloseBid,
      akash.market.v1beta2.BidOuterClass.MsgCloseBidResponse> getCloseBidMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CloseBid",
      requestType = akash.market.v1beta2.BidOuterClass.MsgCloseBid.class,
      responseType = akash.market.v1beta2.BidOuterClass.MsgCloseBidResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.market.v1beta2.BidOuterClass.MsgCloseBid,
      akash.market.v1beta2.BidOuterClass.MsgCloseBidResponse> getCloseBidMethod() {
    io.grpc.MethodDescriptor<akash.market.v1beta2.BidOuterClass.MsgCloseBid, akash.market.v1beta2.BidOuterClass.MsgCloseBidResponse> getCloseBidMethod;
    if ((getCloseBidMethod = MsgGrpc.getCloseBidMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCloseBidMethod = MsgGrpc.getCloseBidMethod) == null) {
          MsgGrpc.getCloseBidMethod = getCloseBidMethod =
              io.grpc.MethodDescriptor.<akash.market.v1beta2.BidOuterClass.MsgCloseBid, akash.market.v1beta2.BidOuterClass.MsgCloseBidResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CloseBid"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.market.v1beta2.BidOuterClass.MsgCloseBid.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.market.v1beta2.BidOuterClass.MsgCloseBidResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CloseBid"))
              .build();
        }
      }
    }
    return getCloseBidMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLease,
      akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLeaseResponse> getWithdrawLeaseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawLease",
      requestType = akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLease.class,
      responseType = akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLeaseResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLease,
      akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLeaseResponse> getWithdrawLeaseMethod() {
    io.grpc.MethodDescriptor<akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLease, akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLeaseResponse> getWithdrawLeaseMethod;
    if ((getWithdrawLeaseMethod = MsgGrpc.getWithdrawLeaseMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawLeaseMethod = MsgGrpc.getWithdrawLeaseMethod) == null) {
          MsgGrpc.getWithdrawLeaseMethod = getWithdrawLeaseMethod =
              io.grpc.MethodDescriptor.<akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLease, akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLeaseResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WithdrawLease"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLease.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLeaseResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WithdrawLease"))
              .build();
        }
      }
    }
    return getWithdrawLeaseMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.market.v1beta2.LeaseOuterClass.MsgCreateLease,
      akash.market.v1beta2.LeaseOuterClass.MsgCreateLeaseResponse> getCreateLeaseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateLease",
      requestType = akash.market.v1beta2.LeaseOuterClass.MsgCreateLease.class,
      responseType = akash.market.v1beta2.LeaseOuterClass.MsgCreateLeaseResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.market.v1beta2.LeaseOuterClass.MsgCreateLease,
      akash.market.v1beta2.LeaseOuterClass.MsgCreateLeaseResponse> getCreateLeaseMethod() {
    io.grpc.MethodDescriptor<akash.market.v1beta2.LeaseOuterClass.MsgCreateLease, akash.market.v1beta2.LeaseOuterClass.MsgCreateLeaseResponse> getCreateLeaseMethod;
    if ((getCreateLeaseMethod = MsgGrpc.getCreateLeaseMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateLeaseMethod = MsgGrpc.getCreateLeaseMethod) == null) {
          MsgGrpc.getCreateLeaseMethod = getCreateLeaseMethod =
              io.grpc.MethodDescriptor.<akash.market.v1beta2.LeaseOuterClass.MsgCreateLease, akash.market.v1beta2.LeaseOuterClass.MsgCreateLeaseResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateLease"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.market.v1beta2.LeaseOuterClass.MsgCreateLease.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.market.v1beta2.LeaseOuterClass.MsgCreateLeaseResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateLease"))
              .build();
        }
      }
    }
    return getCreateLeaseMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.market.v1beta2.LeaseOuterClass.MsgCloseLease,
      akash.market.v1beta2.LeaseOuterClass.MsgCloseLeaseResponse> getCloseLeaseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CloseLease",
      requestType = akash.market.v1beta2.LeaseOuterClass.MsgCloseLease.class,
      responseType = akash.market.v1beta2.LeaseOuterClass.MsgCloseLeaseResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.market.v1beta2.LeaseOuterClass.MsgCloseLease,
      akash.market.v1beta2.LeaseOuterClass.MsgCloseLeaseResponse> getCloseLeaseMethod() {
    io.grpc.MethodDescriptor<akash.market.v1beta2.LeaseOuterClass.MsgCloseLease, akash.market.v1beta2.LeaseOuterClass.MsgCloseLeaseResponse> getCloseLeaseMethod;
    if ((getCloseLeaseMethod = MsgGrpc.getCloseLeaseMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCloseLeaseMethod = MsgGrpc.getCloseLeaseMethod) == null) {
          MsgGrpc.getCloseLeaseMethod = getCloseLeaseMethod =
              io.grpc.MethodDescriptor.<akash.market.v1beta2.LeaseOuterClass.MsgCloseLease, akash.market.v1beta2.LeaseOuterClass.MsgCloseLeaseResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CloseLease"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.market.v1beta2.LeaseOuterClass.MsgCloseLease.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.market.v1beta2.LeaseOuterClass.MsgCloseLeaseResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CloseLease"))
              .build();
        }
      }
    }
    return getCloseLeaseMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MsgStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgStub>() {
        @java.lang.Override
        public MsgStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgStub(channel, callOptions);
        }
      };
    return MsgStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MsgBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgBlockingStub>() {
        @java.lang.Override
        public MsgBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgBlockingStub(channel, callOptions);
        }
      };
    return MsgBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MsgFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgFutureStub>() {
        @java.lang.Override
        public MsgFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgFutureStub(channel, callOptions);
        }
      };
    return MsgFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Msg defines the market Msg service
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CreateBid defines a method to create a bid given proper inputs.
     * </pre>
     */
    public void createBid(akash.market.v1beta2.BidOuterClass.MsgCreateBid request,
        io.grpc.stub.StreamObserver<akash.market.v1beta2.BidOuterClass.MsgCreateBidResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateBidMethod(), responseObserver);
    }

    /**
     * <pre>
     * CloseBid defines a method to close a bid given proper inputs.
     * </pre>
     */
    public void closeBid(akash.market.v1beta2.BidOuterClass.MsgCloseBid request,
        io.grpc.stub.StreamObserver<akash.market.v1beta2.BidOuterClass.MsgCloseBidResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCloseBidMethod(), responseObserver);
    }

    /**
     * <pre>
     * WithdrawLease withdraws accrued funds from the lease payment
     * </pre>
     */
    public void withdrawLease(akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLease request,
        io.grpc.stub.StreamObserver<akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLeaseResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawLeaseMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateLease creates a new lease
     * </pre>
     */
    public void createLease(akash.market.v1beta2.LeaseOuterClass.MsgCreateLease request,
        io.grpc.stub.StreamObserver<akash.market.v1beta2.LeaseOuterClass.MsgCreateLeaseResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateLeaseMethod(), responseObserver);
    }

    /**
     * <pre>
     * CloseLease defines a method to close an order given proper inputs.
     * </pre>
     */
    public void closeLease(akash.market.v1beta2.LeaseOuterClass.MsgCloseLease request,
        io.grpc.stub.StreamObserver<akash.market.v1beta2.LeaseOuterClass.MsgCloseLeaseResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCloseLeaseMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateBidMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.market.v1beta2.BidOuterClass.MsgCreateBid,
                akash.market.v1beta2.BidOuterClass.MsgCreateBidResponse>(
                  this, METHODID_CREATE_BID)))
          .addMethod(
            getCloseBidMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.market.v1beta2.BidOuterClass.MsgCloseBid,
                akash.market.v1beta2.BidOuterClass.MsgCloseBidResponse>(
                  this, METHODID_CLOSE_BID)))
          .addMethod(
            getWithdrawLeaseMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLease,
                akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLeaseResponse>(
                  this, METHODID_WITHDRAW_LEASE)))
          .addMethod(
            getCreateLeaseMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.market.v1beta2.LeaseOuterClass.MsgCreateLease,
                akash.market.v1beta2.LeaseOuterClass.MsgCreateLeaseResponse>(
                  this, METHODID_CREATE_LEASE)))
          .addMethod(
            getCloseLeaseMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.market.v1beta2.LeaseOuterClass.MsgCloseLease,
                akash.market.v1beta2.LeaseOuterClass.MsgCloseLeaseResponse>(
                  this, METHODID_CLOSE_LEASE)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the market Msg service
   * </pre>
   */
  public static final class MsgStub extends io.grpc.stub.AbstractAsyncStub<MsgStub> {
    private MsgStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgStub(channel, callOptions);
    }

    /**
     * <pre>
     * CreateBid defines a method to create a bid given proper inputs.
     * </pre>
     */
    public void createBid(akash.market.v1beta2.BidOuterClass.MsgCreateBid request,
        io.grpc.stub.StreamObserver<akash.market.v1beta2.BidOuterClass.MsgCreateBidResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateBidMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CloseBid defines a method to close a bid given proper inputs.
     * </pre>
     */
    public void closeBid(akash.market.v1beta2.BidOuterClass.MsgCloseBid request,
        io.grpc.stub.StreamObserver<akash.market.v1beta2.BidOuterClass.MsgCloseBidResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCloseBidMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WithdrawLease withdraws accrued funds from the lease payment
     * </pre>
     */
    public void withdrawLease(akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLease request,
        io.grpc.stub.StreamObserver<akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLeaseResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawLeaseMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateLease creates a new lease
     * </pre>
     */
    public void createLease(akash.market.v1beta2.LeaseOuterClass.MsgCreateLease request,
        io.grpc.stub.StreamObserver<akash.market.v1beta2.LeaseOuterClass.MsgCreateLeaseResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateLeaseMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CloseLease defines a method to close an order given proper inputs.
     * </pre>
     */
    public void closeLease(akash.market.v1beta2.LeaseOuterClass.MsgCloseLease request,
        io.grpc.stub.StreamObserver<akash.market.v1beta2.LeaseOuterClass.MsgCloseLeaseResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCloseLeaseMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the market Msg service
   * </pre>
   */
  public static final class MsgBlockingStub extends io.grpc.stub.AbstractBlockingStub<MsgBlockingStub> {
    private MsgBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * CreateBid defines a method to create a bid given proper inputs.
     * </pre>
     */
    public akash.market.v1beta2.BidOuterClass.MsgCreateBidResponse createBid(akash.market.v1beta2.BidOuterClass.MsgCreateBid request) {
      return blockingUnaryCall(
          getChannel(), getCreateBidMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CloseBid defines a method to close a bid given proper inputs.
     * </pre>
     */
    public akash.market.v1beta2.BidOuterClass.MsgCloseBidResponse closeBid(akash.market.v1beta2.BidOuterClass.MsgCloseBid request) {
      return blockingUnaryCall(
          getChannel(), getCloseBidMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WithdrawLease withdraws accrued funds from the lease payment
     * </pre>
     */
    public akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLeaseResponse withdrawLease(akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLease request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawLeaseMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateLease creates a new lease
     * </pre>
     */
    public akash.market.v1beta2.LeaseOuterClass.MsgCreateLeaseResponse createLease(akash.market.v1beta2.LeaseOuterClass.MsgCreateLease request) {
      return blockingUnaryCall(
          getChannel(), getCreateLeaseMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CloseLease defines a method to close an order given proper inputs.
     * </pre>
     */
    public akash.market.v1beta2.LeaseOuterClass.MsgCloseLeaseResponse closeLease(akash.market.v1beta2.LeaseOuterClass.MsgCloseLease request) {
      return blockingUnaryCall(
          getChannel(), getCloseLeaseMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the market Msg service
   * </pre>
   */
  public static final class MsgFutureStub extends io.grpc.stub.AbstractFutureStub<MsgFutureStub> {
    private MsgFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * CreateBid defines a method to create a bid given proper inputs.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.market.v1beta2.BidOuterClass.MsgCreateBidResponse> createBid(
        akash.market.v1beta2.BidOuterClass.MsgCreateBid request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateBidMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CloseBid defines a method to close a bid given proper inputs.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.market.v1beta2.BidOuterClass.MsgCloseBidResponse> closeBid(
        akash.market.v1beta2.BidOuterClass.MsgCloseBid request) {
      return futureUnaryCall(
          getChannel().newCall(getCloseBidMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WithdrawLease withdraws accrued funds from the lease payment
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLeaseResponse> withdrawLease(
        akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLease request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawLeaseMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateLease creates a new lease
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.market.v1beta2.LeaseOuterClass.MsgCreateLeaseResponse> createLease(
        akash.market.v1beta2.LeaseOuterClass.MsgCreateLease request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateLeaseMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CloseLease defines a method to close an order given proper inputs.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.market.v1beta2.LeaseOuterClass.MsgCloseLeaseResponse> closeLease(
        akash.market.v1beta2.LeaseOuterClass.MsgCloseLease request) {
      return futureUnaryCall(
          getChannel().newCall(getCloseLeaseMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_BID = 0;
  private static final int METHODID_CLOSE_BID = 1;
  private static final int METHODID_WITHDRAW_LEASE = 2;
  private static final int METHODID_CREATE_LEASE = 3;
  private static final int METHODID_CLOSE_LEASE = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MsgImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MsgImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_BID:
          serviceImpl.createBid((akash.market.v1beta2.BidOuterClass.MsgCreateBid) request,
              (io.grpc.stub.StreamObserver<akash.market.v1beta2.BidOuterClass.MsgCreateBidResponse>) responseObserver);
          break;
        case METHODID_CLOSE_BID:
          serviceImpl.closeBid((akash.market.v1beta2.BidOuterClass.MsgCloseBid) request,
              (io.grpc.stub.StreamObserver<akash.market.v1beta2.BidOuterClass.MsgCloseBidResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW_LEASE:
          serviceImpl.withdrawLease((akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLease) request,
              (io.grpc.stub.StreamObserver<akash.market.v1beta2.LeaseOuterClass.MsgWithdrawLeaseResponse>) responseObserver);
          break;
        case METHODID_CREATE_LEASE:
          serviceImpl.createLease((akash.market.v1beta2.LeaseOuterClass.MsgCreateLease) request,
              (io.grpc.stub.StreamObserver<akash.market.v1beta2.LeaseOuterClass.MsgCreateLeaseResponse>) responseObserver);
          break;
        case METHODID_CLOSE_LEASE:
          serviceImpl.closeLease((akash.market.v1beta2.LeaseOuterClass.MsgCloseLease) request,
              (io.grpc.stub.StreamObserver<akash.market.v1beta2.LeaseOuterClass.MsgCloseLeaseResponse>) responseObserver);
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

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return akash.market.v1beta2.Service.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Msg");
    }
  }

  private static final class MsgFileDescriptorSupplier
      extends MsgBaseDescriptorSupplier {
    MsgFileDescriptorSupplier() {}
  }

  private static final class MsgMethodDescriptorSupplier
      extends MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MsgMethodDescriptorSupplier(String methodName) {
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
      synchronized (MsgGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MsgFileDescriptorSupplier())
              .addMethod(getCreateBidMethod())
              .addMethod(getCloseBidMethod())
              .addMethod(getWithdrawLeaseMethod())
              .addMethod(getCreateLeaseMethod())
              .addMethod(getCloseLeaseMethod())
              .build();
        }
      }
    }
    return result;
  }
}
