package konstellation.oracle;

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
 * Msg defines the Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: konstellation/oracle/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "konstellation.oracle.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<konstellation.oracle.Tx.MsgSetExchangeRate,
      konstellation.oracle.Tx.MsgSetExchangeRateResponse> getSetExchangeRateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetExchangeRate",
      requestType = konstellation.oracle.Tx.MsgSetExchangeRate.class,
      responseType = konstellation.oracle.Tx.MsgSetExchangeRateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<konstellation.oracle.Tx.MsgSetExchangeRate,
      konstellation.oracle.Tx.MsgSetExchangeRateResponse> getSetExchangeRateMethod() {
    io.grpc.MethodDescriptor<konstellation.oracle.Tx.MsgSetExchangeRate, konstellation.oracle.Tx.MsgSetExchangeRateResponse> getSetExchangeRateMethod;
    if ((getSetExchangeRateMethod = MsgGrpc.getSetExchangeRateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetExchangeRateMethod = MsgGrpc.getSetExchangeRateMethod) == null) {
          MsgGrpc.getSetExchangeRateMethod = getSetExchangeRateMethod =
              io.grpc.MethodDescriptor.<konstellation.oracle.Tx.MsgSetExchangeRate, konstellation.oracle.Tx.MsgSetExchangeRateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetExchangeRate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  konstellation.oracle.Tx.MsgSetExchangeRate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  konstellation.oracle.Tx.MsgSetExchangeRateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetExchangeRate"))
              .build();
        }
      }
    }
    return getSetExchangeRateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<konstellation.oracle.Tx.MsgSetExchangeRates,
      konstellation.oracle.Tx.MsgSetExchangeRatesResponse> getSetExchangeRatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetExchangeRates",
      requestType = konstellation.oracle.Tx.MsgSetExchangeRates.class,
      responseType = konstellation.oracle.Tx.MsgSetExchangeRatesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<konstellation.oracle.Tx.MsgSetExchangeRates,
      konstellation.oracle.Tx.MsgSetExchangeRatesResponse> getSetExchangeRatesMethod() {
    io.grpc.MethodDescriptor<konstellation.oracle.Tx.MsgSetExchangeRates, konstellation.oracle.Tx.MsgSetExchangeRatesResponse> getSetExchangeRatesMethod;
    if ((getSetExchangeRatesMethod = MsgGrpc.getSetExchangeRatesMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetExchangeRatesMethod = MsgGrpc.getSetExchangeRatesMethod) == null) {
          MsgGrpc.getSetExchangeRatesMethod = getSetExchangeRatesMethod =
              io.grpc.MethodDescriptor.<konstellation.oracle.Tx.MsgSetExchangeRates, konstellation.oracle.Tx.MsgSetExchangeRatesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetExchangeRates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  konstellation.oracle.Tx.MsgSetExchangeRates.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  konstellation.oracle.Tx.MsgSetExchangeRatesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetExchangeRates"))
              .build();
        }
      }
    }
    return getSetExchangeRatesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<konstellation.oracle.Tx.MsgDeleteExchangeRate,
      konstellation.oracle.Tx.MsgDeleteExchangeRateResponse> getDeleteExchangeRateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteExchangeRate",
      requestType = konstellation.oracle.Tx.MsgDeleteExchangeRate.class,
      responseType = konstellation.oracle.Tx.MsgDeleteExchangeRateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<konstellation.oracle.Tx.MsgDeleteExchangeRate,
      konstellation.oracle.Tx.MsgDeleteExchangeRateResponse> getDeleteExchangeRateMethod() {
    io.grpc.MethodDescriptor<konstellation.oracle.Tx.MsgDeleteExchangeRate, konstellation.oracle.Tx.MsgDeleteExchangeRateResponse> getDeleteExchangeRateMethod;
    if ((getDeleteExchangeRateMethod = MsgGrpc.getDeleteExchangeRateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteExchangeRateMethod = MsgGrpc.getDeleteExchangeRateMethod) == null) {
          MsgGrpc.getDeleteExchangeRateMethod = getDeleteExchangeRateMethod =
              io.grpc.MethodDescriptor.<konstellation.oracle.Tx.MsgDeleteExchangeRate, konstellation.oracle.Tx.MsgDeleteExchangeRateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteExchangeRate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  konstellation.oracle.Tx.MsgDeleteExchangeRate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  konstellation.oracle.Tx.MsgDeleteExchangeRateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteExchangeRate"))
              .build();
        }
      }
    }
    return getDeleteExchangeRateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<konstellation.oracle.Tx.MsgDeleteExchangeRates,
      konstellation.oracle.Tx.MsgDeleteExchangeRatesResponse> getDeleteExchangeRatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteExchangeRates",
      requestType = konstellation.oracle.Tx.MsgDeleteExchangeRates.class,
      responseType = konstellation.oracle.Tx.MsgDeleteExchangeRatesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<konstellation.oracle.Tx.MsgDeleteExchangeRates,
      konstellation.oracle.Tx.MsgDeleteExchangeRatesResponse> getDeleteExchangeRatesMethod() {
    io.grpc.MethodDescriptor<konstellation.oracle.Tx.MsgDeleteExchangeRates, konstellation.oracle.Tx.MsgDeleteExchangeRatesResponse> getDeleteExchangeRatesMethod;
    if ((getDeleteExchangeRatesMethod = MsgGrpc.getDeleteExchangeRatesMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteExchangeRatesMethod = MsgGrpc.getDeleteExchangeRatesMethod) == null) {
          MsgGrpc.getDeleteExchangeRatesMethod = getDeleteExchangeRatesMethod =
              io.grpc.MethodDescriptor.<konstellation.oracle.Tx.MsgDeleteExchangeRates, konstellation.oracle.Tx.MsgDeleteExchangeRatesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteExchangeRates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  konstellation.oracle.Tx.MsgDeleteExchangeRates.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  konstellation.oracle.Tx.MsgDeleteExchangeRatesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteExchangeRates"))
              .build();
        }
      }
    }
    return getDeleteExchangeRatesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<konstellation.oracle.Tx.MsgSetAdminAddr,
      konstellation.oracle.Tx.MsgSetAdminAddrResponse> getSetAdminAddrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetAdminAddr",
      requestType = konstellation.oracle.Tx.MsgSetAdminAddr.class,
      responseType = konstellation.oracle.Tx.MsgSetAdminAddrResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<konstellation.oracle.Tx.MsgSetAdminAddr,
      konstellation.oracle.Tx.MsgSetAdminAddrResponse> getSetAdminAddrMethod() {
    io.grpc.MethodDescriptor<konstellation.oracle.Tx.MsgSetAdminAddr, konstellation.oracle.Tx.MsgSetAdminAddrResponse> getSetAdminAddrMethod;
    if ((getSetAdminAddrMethod = MsgGrpc.getSetAdminAddrMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetAdminAddrMethod = MsgGrpc.getSetAdminAddrMethod) == null) {
          MsgGrpc.getSetAdminAddrMethod = getSetAdminAddrMethod =
              io.grpc.MethodDescriptor.<konstellation.oracle.Tx.MsgSetAdminAddr, konstellation.oracle.Tx.MsgSetAdminAddrResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetAdminAddr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  konstellation.oracle.Tx.MsgSetAdminAddr.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  konstellation.oracle.Tx.MsgSetAdminAddrResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetAdminAddr"))
              .build();
        }
      }
    }
    return getSetAdminAddrMethod;
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
   * Msg defines the Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     */
    public void setExchangeRate(konstellation.oracle.Tx.MsgSetExchangeRate request,
        io.grpc.stub.StreamObserver<konstellation.oracle.Tx.MsgSetExchangeRateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetExchangeRateMethod(), responseObserver);
    }

    /**
     */
    public void setExchangeRates(konstellation.oracle.Tx.MsgSetExchangeRates request,
        io.grpc.stub.StreamObserver<konstellation.oracle.Tx.MsgSetExchangeRatesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetExchangeRatesMethod(), responseObserver);
    }

    /**
     */
    public void deleteExchangeRate(konstellation.oracle.Tx.MsgDeleteExchangeRate request,
        io.grpc.stub.StreamObserver<konstellation.oracle.Tx.MsgDeleteExchangeRateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteExchangeRateMethod(), responseObserver);
    }

    /**
     */
    public void deleteExchangeRates(konstellation.oracle.Tx.MsgDeleteExchangeRates request,
        io.grpc.stub.StreamObserver<konstellation.oracle.Tx.MsgDeleteExchangeRatesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteExchangeRatesMethod(), responseObserver);
    }

    /**
     */
    public void setAdminAddr(konstellation.oracle.Tx.MsgSetAdminAddr request,
        io.grpc.stub.StreamObserver<konstellation.oracle.Tx.MsgSetAdminAddrResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetAdminAddrMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSetExchangeRateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                konstellation.oracle.Tx.MsgSetExchangeRate,
                konstellation.oracle.Tx.MsgSetExchangeRateResponse>(
                  this, METHODID_SET_EXCHANGE_RATE)))
          .addMethod(
            getSetExchangeRatesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                konstellation.oracle.Tx.MsgSetExchangeRates,
                konstellation.oracle.Tx.MsgSetExchangeRatesResponse>(
                  this, METHODID_SET_EXCHANGE_RATES)))
          .addMethod(
            getDeleteExchangeRateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                konstellation.oracle.Tx.MsgDeleteExchangeRate,
                konstellation.oracle.Tx.MsgDeleteExchangeRateResponse>(
                  this, METHODID_DELETE_EXCHANGE_RATE)))
          .addMethod(
            getDeleteExchangeRatesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                konstellation.oracle.Tx.MsgDeleteExchangeRates,
                konstellation.oracle.Tx.MsgDeleteExchangeRatesResponse>(
                  this, METHODID_DELETE_EXCHANGE_RATES)))
          .addMethod(
            getSetAdminAddrMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                konstellation.oracle.Tx.MsgSetAdminAddr,
                konstellation.oracle.Tx.MsgSetAdminAddrResponse>(
                  this, METHODID_SET_ADMIN_ADDR)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the Msg service.
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
     */
    public void setExchangeRate(konstellation.oracle.Tx.MsgSetExchangeRate request,
        io.grpc.stub.StreamObserver<konstellation.oracle.Tx.MsgSetExchangeRateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetExchangeRateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setExchangeRates(konstellation.oracle.Tx.MsgSetExchangeRates request,
        io.grpc.stub.StreamObserver<konstellation.oracle.Tx.MsgSetExchangeRatesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetExchangeRatesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteExchangeRate(konstellation.oracle.Tx.MsgDeleteExchangeRate request,
        io.grpc.stub.StreamObserver<konstellation.oracle.Tx.MsgDeleteExchangeRateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteExchangeRateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteExchangeRates(konstellation.oracle.Tx.MsgDeleteExchangeRates request,
        io.grpc.stub.StreamObserver<konstellation.oracle.Tx.MsgDeleteExchangeRatesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteExchangeRatesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setAdminAddr(konstellation.oracle.Tx.MsgSetAdminAddr request,
        io.grpc.stub.StreamObserver<konstellation.oracle.Tx.MsgSetAdminAddrResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetAdminAddrMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the Msg service.
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
     */
    public konstellation.oracle.Tx.MsgSetExchangeRateResponse setExchangeRate(konstellation.oracle.Tx.MsgSetExchangeRate request) {
      return blockingUnaryCall(
          getChannel(), getSetExchangeRateMethod(), getCallOptions(), request);
    }

    /**
     */
    public konstellation.oracle.Tx.MsgSetExchangeRatesResponse setExchangeRates(konstellation.oracle.Tx.MsgSetExchangeRates request) {
      return blockingUnaryCall(
          getChannel(), getSetExchangeRatesMethod(), getCallOptions(), request);
    }

    /**
     */
    public konstellation.oracle.Tx.MsgDeleteExchangeRateResponse deleteExchangeRate(konstellation.oracle.Tx.MsgDeleteExchangeRate request) {
      return blockingUnaryCall(
          getChannel(), getDeleteExchangeRateMethod(), getCallOptions(), request);
    }

    /**
     */
    public konstellation.oracle.Tx.MsgDeleteExchangeRatesResponse deleteExchangeRates(konstellation.oracle.Tx.MsgDeleteExchangeRates request) {
      return blockingUnaryCall(
          getChannel(), getDeleteExchangeRatesMethod(), getCallOptions(), request);
    }

    /**
     */
    public konstellation.oracle.Tx.MsgSetAdminAddrResponse setAdminAddr(konstellation.oracle.Tx.MsgSetAdminAddr request) {
      return blockingUnaryCall(
          getChannel(), getSetAdminAddrMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the Msg service.
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
     */
    public com.google.common.util.concurrent.ListenableFuture<konstellation.oracle.Tx.MsgSetExchangeRateResponse> setExchangeRate(
        konstellation.oracle.Tx.MsgSetExchangeRate request) {
      return futureUnaryCall(
          getChannel().newCall(getSetExchangeRateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<konstellation.oracle.Tx.MsgSetExchangeRatesResponse> setExchangeRates(
        konstellation.oracle.Tx.MsgSetExchangeRates request) {
      return futureUnaryCall(
          getChannel().newCall(getSetExchangeRatesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<konstellation.oracle.Tx.MsgDeleteExchangeRateResponse> deleteExchangeRate(
        konstellation.oracle.Tx.MsgDeleteExchangeRate request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteExchangeRateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<konstellation.oracle.Tx.MsgDeleteExchangeRatesResponse> deleteExchangeRates(
        konstellation.oracle.Tx.MsgDeleteExchangeRates request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteExchangeRatesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<konstellation.oracle.Tx.MsgSetAdminAddrResponse> setAdminAddr(
        konstellation.oracle.Tx.MsgSetAdminAddr request) {
      return futureUnaryCall(
          getChannel().newCall(getSetAdminAddrMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET_EXCHANGE_RATE = 0;
  private static final int METHODID_SET_EXCHANGE_RATES = 1;
  private static final int METHODID_DELETE_EXCHANGE_RATE = 2;
  private static final int METHODID_DELETE_EXCHANGE_RATES = 3;
  private static final int METHODID_SET_ADMIN_ADDR = 4;

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
        case METHODID_SET_EXCHANGE_RATE:
          serviceImpl.setExchangeRate((konstellation.oracle.Tx.MsgSetExchangeRate) request,
              (io.grpc.stub.StreamObserver<konstellation.oracle.Tx.MsgSetExchangeRateResponse>) responseObserver);
          break;
        case METHODID_SET_EXCHANGE_RATES:
          serviceImpl.setExchangeRates((konstellation.oracle.Tx.MsgSetExchangeRates) request,
              (io.grpc.stub.StreamObserver<konstellation.oracle.Tx.MsgSetExchangeRatesResponse>) responseObserver);
          break;
        case METHODID_DELETE_EXCHANGE_RATE:
          serviceImpl.deleteExchangeRate((konstellation.oracle.Tx.MsgDeleteExchangeRate) request,
              (io.grpc.stub.StreamObserver<konstellation.oracle.Tx.MsgDeleteExchangeRateResponse>) responseObserver);
          break;
        case METHODID_DELETE_EXCHANGE_RATES:
          serviceImpl.deleteExchangeRates((konstellation.oracle.Tx.MsgDeleteExchangeRates) request,
              (io.grpc.stub.StreamObserver<konstellation.oracle.Tx.MsgDeleteExchangeRatesResponse>) responseObserver);
          break;
        case METHODID_SET_ADMIN_ADDR:
          serviceImpl.setAdminAddr((konstellation.oracle.Tx.MsgSetAdminAddr) request,
              (io.grpc.stub.StreamObserver<konstellation.oracle.Tx.MsgSetAdminAddrResponse>) responseObserver);
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
      return konstellation.oracle.Tx.getDescriptor();
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
              .addMethod(getSetExchangeRateMethod())
              .addMethod(getSetExchangeRatesMethod())
              .addMethod(getDeleteExchangeRateMethod())
              .addMethod(getDeleteExchangeRatesMethod())
              .addMethod(getSetAdminAddrMethod())
              .build();
        }
      }
    }
    return result;
  }
}
