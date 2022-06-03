package osmosis.gamm.v1beta1;

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
    comments = "Source: osmosis/gamm/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "osmosis.gamm.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgJoinPool,
      osmosis.gamm.v1beta1.Tx.MsgJoinPoolResponse> getJoinPoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "JoinPool",
      requestType = osmosis.gamm.v1beta1.Tx.MsgJoinPool.class,
      responseType = osmosis.gamm.v1beta1.Tx.MsgJoinPoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgJoinPool,
      osmosis.gamm.v1beta1.Tx.MsgJoinPoolResponse> getJoinPoolMethod() {
    io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgJoinPool, osmosis.gamm.v1beta1.Tx.MsgJoinPoolResponse> getJoinPoolMethod;
    if ((getJoinPoolMethod = MsgGrpc.getJoinPoolMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getJoinPoolMethod = MsgGrpc.getJoinPoolMethod) == null) {
          MsgGrpc.getJoinPoolMethod = getJoinPoolMethod =
              io.grpc.MethodDescriptor.<osmosis.gamm.v1beta1.Tx.MsgJoinPool, osmosis.gamm.v1beta1.Tx.MsgJoinPoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "JoinPool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.Tx.MsgJoinPool.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.Tx.MsgJoinPoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("JoinPool"))
              .build();
        }
      }
    }
    return getJoinPoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgExitPool,
      osmosis.gamm.v1beta1.Tx.MsgExitPoolResponse> getExitPoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExitPool",
      requestType = osmosis.gamm.v1beta1.Tx.MsgExitPool.class,
      responseType = osmosis.gamm.v1beta1.Tx.MsgExitPoolResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgExitPool,
      osmosis.gamm.v1beta1.Tx.MsgExitPoolResponse> getExitPoolMethod() {
    io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgExitPool, osmosis.gamm.v1beta1.Tx.MsgExitPoolResponse> getExitPoolMethod;
    if ((getExitPoolMethod = MsgGrpc.getExitPoolMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getExitPoolMethod = MsgGrpc.getExitPoolMethod) == null) {
          MsgGrpc.getExitPoolMethod = getExitPoolMethod =
              io.grpc.MethodDescriptor.<osmosis.gamm.v1beta1.Tx.MsgExitPool, osmosis.gamm.v1beta1.Tx.MsgExitPoolResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExitPool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.Tx.MsgExitPool.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.Tx.MsgExitPoolResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ExitPool"))
              .build();
        }
      }
    }
    return getExitPoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountIn,
      osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountInResponse> getSwapExactAmountInMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SwapExactAmountIn",
      requestType = osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountIn.class,
      responseType = osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountInResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountIn,
      osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountInResponse> getSwapExactAmountInMethod() {
    io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountIn, osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountInResponse> getSwapExactAmountInMethod;
    if ((getSwapExactAmountInMethod = MsgGrpc.getSwapExactAmountInMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSwapExactAmountInMethod = MsgGrpc.getSwapExactAmountInMethod) == null) {
          MsgGrpc.getSwapExactAmountInMethod = getSwapExactAmountInMethod =
              io.grpc.MethodDescriptor.<osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountIn, osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountInResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SwapExactAmountIn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountIn.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountInResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SwapExactAmountIn"))
              .build();
        }
      }
    }
    return getSwapExactAmountInMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOut,
      osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOutResponse> getSwapExactAmountOutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SwapExactAmountOut",
      requestType = osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOut.class,
      responseType = osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOutResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOut,
      osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOutResponse> getSwapExactAmountOutMethod() {
    io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOut, osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOutResponse> getSwapExactAmountOutMethod;
    if ((getSwapExactAmountOutMethod = MsgGrpc.getSwapExactAmountOutMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSwapExactAmountOutMethod = MsgGrpc.getSwapExactAmountOutMethod) == null) {
          MsgGrpc.getSwapExactAmountOutMethod = getSwapExactAmountOutMethod =
              io.grpc.MethodDescriptor.<osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOut, osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOutResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SwapExactAmountOut"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOut.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOutResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SwapExactAmountOut"))
              .build();
        }
      }
    }
    return getSwapExactAmountOutMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountIn,
      osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountInResponse> getJoinSwapExternAmountInMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "JoinSwapExternAmountIn",
      requestType = osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountIn.class,
      responseType = osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountInResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountIn,
      osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountInResponse> getJoinSwapExternAmountInMethod() {
    io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountIn, osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountInResponse> getJoinSwapExternAmountInMethod;
    if ((getJoinSwapExternAmountInMethod = MsgGrpc.getJoinSwapExternAmountInMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getJoinSwapExternAmountInMethod = MsgGrpc.getJoinSwapExternAmountInMethod) == null) {
          MsgGrpc.getJoinSwapExternAmountInMethod = getJoinSwapExternAmountInMethod =
              io.grpc.MethodDescriptor.<osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountIn, osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountInResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "JoinSwapExternAmountIn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountIn.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountInResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("JoinSwapExternAmountIn"))
              .build();
        }
      }
    }
    return getJoinSwapExternAmountInMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOut,
      osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOutResponse> getJoinSwapShareAmountOutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "JoinSwapShareAmountOut",
      requestType = osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOut.class,
      responseType = osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOutResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOut,
      osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOutResponse> getJoinSwapShareAmountOutMethod() {
    io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOut, osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOutResponse> getJoinSwapShareAmountOutMethod;
    if ((getJoinSwapShareAmountOutMethod = MsgGrpc.getJoinSwapShareAmountOutMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getJoinSwapShareAmountOutMethod = MsgGrpc.getJoinSwapShareAmountOutMethod) == null) {
          MsgGrpc.getJoinSwapShareAmountOutMethod = getJoinSwapShareAmountOutMethod =
              io.grpc.MethodDescriptor.<osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOut, osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOutResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "JoinSwapShareAmountOut"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOut.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOutResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("JoinSwapShareAmountOut"))
              .build();
        }
      }
    }
    return getJoinSwapShareAmountOutMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOut,
      osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOutResponse> getExitSwapExternAmountOutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExitSwapExternAmountOut",
      requestType = osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOut.class,
      responseType = osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOutResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOut,
      osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOutResponse> getExitSwapExternAmountOutMethod() {
    io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOut, osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOutResponse> getExitSwapExternAmountOutMethod;
    if ((getExitSwapExternAmountOutMethod = MsgGrpc.getExitSwapExternAmountOutMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getExitSwapExternAmountOutMethod = MsgGrpc.getExitSwapExternAmountOutMethod) == null) {
          MsgGrpc.getExitSwapExternAmountOutMethod = getExitSwapExternAmountOutMethod =
              io.grpc.MethodDescriptor.<osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOut, osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOutResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExitSwapExternAmountOut"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOut.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOutResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ExitSwapExternAmountOut"))
              .build();
        }
      }
    }
    return getExitSwapExternAmountOutMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountIn,
      osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountInResponse> getExitSwapShareAmountInMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExitSwapShareAmountIn",
      requestType = osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountIn.class,
      responseType = osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountInResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountIn,
      osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountInResponse> getExitSwapShareAmountInMethod() {
    io.grpc.MethodDescriptor<osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountIn, osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountInResponse> getExitSwapShareAmountInMethod;
    if ((getExitSwapShareAmountInMethod = MsgGrpc.getExitSwapShareAmountInMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getExitSwapShareAmountInMethod = MsgGrpc.getExitSwapShareAmountInMethod) == null) {
          MsgGrpc.getExitSwapShareAmountInMethod = getExitSwapShareAmountInMethod =
              io.grpc.MethodDescriptor.<osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountIn, osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountInResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExitSwapShareAmountIn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountIn.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountInResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ExitSwapShareAmountIn"))
              .build();
        }
      }
    }
    return getExitSwapShareAmountInMethod;
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
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     */
    public void joinPool(osmosis.gamm.v1beta1.Tx.MsgJoinPool request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgJoinPoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getJoinPoolMethod(), responseObserver);
    }

    /**
     */
    public void exitPool(osmosis.gamm.v1beta1.Tx.MsgExitPool request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgExitPoolResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getExitPoolMethod(), responseObserver);
    }

    /**
     */
    public void swapExactAmountIn(osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountIn request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountInResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSwapExactAmountInMethod(), responseObserver);
    }

    /**
     */
    public void swapExactAmountOut(osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOut request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOutResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSwapExactAmountOutMethod(), responseObserver);
    }

    /**
     */
    public void joinSwapExternAmountIn(osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountIn request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountInResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getJoinSwapExternAmountInMethod(), responseObserver);
    }

    /**
     */
    public void joinSwapShareAmountOut(osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOut request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOutResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getJoinSwapShareAmountOutMethod(), responseObserver);
    }

    /**
     */
    public void exitSwapExternAmountOut(osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOut request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOutResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getExitSwapExternAmountOutMethod(), responseObserver);
    }

    /**
     */
    public void exitSwapShareAmountIn(osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountIn request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountInResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getExitSwapShareAmountInMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getJoinPoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.gamm.v1beta1.Tx.MsgJoinPool,
                osmosis.gamm.v1beta1.Tx.MsgJoinPoolResponse>(
                  this, METHODID_JOIN_POOL)))
          .addMethod(
            getExitPoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.gamm.v1beta1.Tx.MsgExitPool,
                osmosis.gamm.v1beta1.Tx.MsgExitPoolResponse>(
                  this, METHODID_EXIT_POOL)))
          .addMethod(
            getSwapExactAmountInMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountIn,
                osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountInResponse>(
                  this, METHODID_SWAP_EXACT_AMOUNT_IN)))
          .addMethod(
            getSwapExactAmountOutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOut,
                osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOutResponse>(
                  this, METHODID_SWAP_EXACT_AMOUNT_OUT)))
          .addMethod(
            getJoinSwapExternAmountInMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountIn,
                osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountInResponse>(
                  this, METHODID_JOIN_SWAP_EXTERN_AMOUNT_IN)))
          .addMethod(
            getJoinSwapShareAmountOutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOut,
                osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOutResponse>(
                  this, METHODID_JOIN_SWAP_SHARE_AMOUNT_OUT)))
          .addMethod(
            getExitSwapExternAmountOutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOut,
                osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOutResponse>(
                  this, METHODID_EXIT_SWAP_EXTERN_AMOUNT_OUT)))
          .addMethod(
            getExitSwapShareAmountInMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountIn,
                osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountInResponse>(
                  this, METHODID_EXIT_SWAP_SHARE_AMOUNT_IN)))
          .build();
    }
  }

  /**
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
    public void joinPool(osmosis.gamm.v1beta1.Tx.MsgJoinPool request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgJoinPoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getJoinPoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void exitPool(osmosis.gamm.v1beta1.Tx.MsgExitPool request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgExitPoolResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExitPoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void swapExactAmountIn(osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountIn request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountInResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSwapExactAmountInMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void swapExactAmountOut(osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOut request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOutResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSwapExactAmountOutMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void joinSwapExternAmountIn(osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountIn request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountInResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getJoinSwapExternAmountInMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void joinSwapShareAmountOut(osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOut request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOutResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getJoinSwapShareAmountOutMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void exitSwapExternAmountOut(osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOut request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOutResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExitSwapExternAmountOutMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void exitSwapShareAmountIn(osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountIn request,
        io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountInResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExitSwapShareAmountInMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
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
    public osmosis.gamm.v1beta1.Tx.MsgJoinPoolResponse joinPool(osmosis.gamm.v1beta1.Tx.MsgJoinPool request) {
      return blockingUnaryCall(
          getChannel(), getJoinPoolMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.gamm.v1beta1.Tx.MsgExitPoolResponse exitPool(osmosis.gamm.v1beta1.Tx.MsgExitPool request) {
      return blockingUnaryCall(
          getChannel(), getExitPoolMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountInResponse swapExactAmountIn(osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountIn request) {
      return blockingUnaryCall(
          getChannel(), getSwapExactAmountInMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOutResponse swapExactAmountOut(osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOut request) {
      return blockingUnaryCall(
          getChannel(), getSwapExactAmountOutMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountInResponse joinSwapExternAmountIn(osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountIn request) {
      return blockingUnaryCall(
          getChannel(), getJoinSwapExternAmountInMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOutResponse joinSwapShareAmountOut(osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOut request) {
      return blockingUnaryCall(
          getChannel(), getJoinSwapShareAmountOutMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOutResponse exitSwapExternAmountOut(osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOut request) {
      return blockingUnaryCall(
          getChannel(), getExitSwapExternAmountOutMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountInResponse exitSwapShareAmountIn(osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountIn request) {
      return blockingUnaryCall(
          getChannel(), getExitSwapShareAmountInMethod(), getCallOptions(), request);
    }
  }

  /**
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
    public com.google.common.util.concurrent.ListenableFuture<osmosis.gamm.v1beta1.Tx.MsgJoinPoolResponse> joinPool(
        osmosis.gamm.v1beta1.Tx.MsgJoinPool request) {
      return futureUnaryCall(
          getChannel().newCall(getJoinPoolMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.gamm.v1beta1.Tx.MsgExitPoolResponse> exitPool(
        osmosis.gamm.v1beta1.Tx.MsgExitPool request) {
      return futureUnaryCall(
          getChannel().newCall(getExitPoolMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountInResponse> swapExactAmountIn(
        osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountIn request) {
      return futureUnaryCall(
          getChannel().newCall(getSwapExactAmountInMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOutResponse> swapExactAmountOut(
        osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOut request) {
      return futureUnaryCall(
          getChannel().newCall(getSwapExactAmountOutMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountInResponse> joinSwapExternAmountIn(
        osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountIn request) {
      return futureUnaryCall(
          getChannel().newCall(getJoinSwapExternAmountInMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOutResponse> joinSwapShareAmountOut(
        osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOut request) {
      return futureUnaryCall(
          getChannel().newCall(getJoinSwapShareAmountOutMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOutResponse> exitSwapExternAmountOut(
        osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOut request) {
      return futureUnaryCall(
          getChannel().newCall(getExitSwapExternAmountOutMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountInResponse> exitSwapShareAmountIn(
        osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountIn request) {
      return futureUnaryCall(
          getChannel().newCall(getExitSwapShareAmountInMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_JOIN_POOL = 0;
  private static final int METHODID_EXIT_POOL = 1;
  private static final int METHODID_SWAP_EXACT_AMOUNT_IN = 2;
  private static final int METHODID_SWAP_EXACT_AMOUNT_OUT = 3;
  private static final int METHODID_JOIN_SWAP_EXTERN_AMOUNT_IN = 4;
  private static final int METHODID_JOIN_SWAP_SHARE_AMOUNT_OUT = 5;
  private static final int METHODID_EXIT_SWAP_EXTERN_AMOUNT_OUT = 6;
  private static final int METHODID_EXIT_SWAP_SHARE_AMOUNT_IN = 7;

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
        case METHODID_JOIN_POOL:
          serviceImpl.joinPool((osmosis.gamm.v1beta1.Tx.MsgJoinPool) request,
              (io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgJoinPoolResponse>) responseObserver);
          break;
        case METHODID_EXIT_POOL:
          serviceImpl.exitPool((osmosis.gamm.v1beta1.Tx.MsgExitPool) request,
              (io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgExitPoolResponse>) responseObserver);
          break;
        case METHODID_SWAP_EXACT_AMOUNT_IN:
          serviceImpl.swapExactAmountIn((osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountIn) request,
              (io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountInResponse>) responseObserver);
          break;
        case METHODID_SWAP_EXACT_AMOUNT_OUT:
          serviceImpl.swapExactAmountOut((osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOut) request,
              (io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountOutResponse>) responseObserver);
          break;
        case METHODID_JOIN_SWAP_EXTERN_AMOUNT_IN:
          serviceImpl.joinSwapExternAmountIn((osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountIn) request,
              (io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgJoinSwapExternAmountInResponse>) responseObserver);
          break;
        case METHODID_JOIN_SWAP_SHARE_AMOUNT_OUT:
          serviceImpl.joinSwapShareAmountOut((osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOut) request,
              (io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgJoinSwapShareAmountOutResponse>) responseObserver);
          break;
        case METHODID_EXIT_SWAP_EXTERN_AMOUNT_OUT:
          serviceImpl.exitSwapExternAmountOut((osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOut) request,
              (io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgExitSwapExternAmountOutResponse>) responseObserver);
          break;
        case METHODID_EXIT_SWAP_SHARE_AMOUNT_IN:
          serviceImpl.exitSwapShareAmountIn((osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountIn) request,
              (io.grpc.stub.StreamObserver<osmosis.gamm.v1beta1.Tx.MsgExitSwapShareAmountInResponse>) responseObserver);
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
      return osmosis.gamm.v1beta1.Tx.getDescriptor();
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
              .addMethod(getJoinPoolMethod())
              .addMethod(getExitPoolMethod())
              .addMethod(getSwapExactAmountInMethod())
              .addMethod(getSwapExactAmountOutMethod())
              .addMethod(getJoinSwapExternAmountInMethod())
              .addMethod(getJoinSwapShareAmountOutMethod())
              .addMethod(getExitSwapExternAmountOutMethod())
              .addMethod(getExitSwapShareAmountInMethod())
              .build();
        }
      }
    }
    return result;
  }
}
