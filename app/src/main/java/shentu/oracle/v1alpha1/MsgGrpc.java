package shentu.oracle.v1alpha1;

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
 * Msg defines the shield Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: shentu/oracle/v1alpha1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "shentu.oracle.v1alpha1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgCreateOperator,
      shentu.oracle.v1alpha1.Tx.MsgCreateOperatorResponse> getCreateOperatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateOperator",
      requestType = shentu.oracle.v1alpha1.Tx.MsgCreateOperator.class,
      responseType = shentu.oracle.v1alpha1.Tx.MsgCreateOperatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgCreateOperator,
      shentu.oracle.v1alpha1.Tx.MsgCreateOperatorResponse> getCreateOperatorMethod() {
    io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgCreateOperator, shentu.oracle.v1alpha1.Tx.MsgCreateOperatorResponse> getCreateOperatorMethod;
    if ((getCreateOperatorMethod = MsgGrpc.getCreateOperatorMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateOperatorMethod = MsgGrpc.getCreateOperatorMethod) == null) {
          MsgGrpc.getCreateOperatorMethod = getCreateOperatorMethod =
              io.grpc.MethodDescriptor.<shentu.oracle.v1alpha1.Tx.MsgCreateOperator, shentu.oracle.v1alpha1.Tx.MsgCreateOperatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateOperator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.Tx.MsgCreateOperator.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.Tx.MsgCreateOperatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateOperator"))
              .build();
        }
      }
    }
    return getCreateOperatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgRemoveOperator,
      shentu.oracle.v1alpha1.Tx.MsgRemoveOperatorResponse> getRemoveOperatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveOperator",
      requestType = shentu.oracle.v1alpha1.Tx.MsgRemoveOperator.class,
      responseType = shentu.oracle.v1alpha1.Tx.MsgRemoveOperatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgRemoveOperator,
      shentu.oracle.v1alpha1.Tx.MsgRemoveOperatorResponse> getRemoveOperatorMethod() {
    io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgRemoveOperator, shentu.oracle.v1alpha1.Tx.MsgRemoveOperatorResponse> getRemoveOperatorMethod;
    if ((getRemoveOperatorMethod = MsgGrpc.getRemoveOperatorMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRemoveOperatorMethod = MsgGrpc.getRemoveOperatorMethod) == null) {
          MsgGrpc.getRemoveOperatorMethod = getRemoveOperatorMethod =
              io.grpc.MethodDescriptor.<shentu.oracle.v1alpha1.Tx.MsgRemoveOperator, shentu.oracle.v1alpha1.Tx.MsgRemoveOperatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemoveOperator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.Tx.MsgRemoveOperator.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.Tx.MsgRemoveOperatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RemoveOperator"))
              .build();
        }
      }
    }
    return getRemoveOperatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgAddCollateral,
      shentu.oracle.v1alpha1.Tx.MsgAddCollateralResponse> getAddCollateralMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddCollateral",
      requestType = shentu.oracle.v1alpha1.Tx.MsgAddCollateral.class,
      responseType = shentu.oracle.v1alpha1.Tx.MsgAddCollateralResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgAddCollateral,
      shentu.oracle.v1alpha1.Tx.MsgAddCollateralResponse> getAddCollateralMethod() {
    io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgAddCollateral, shentu.oracle.v1alpha1.Tx.MsgAddCollateralResponse> getAddCollateralMethod;
    if ((getAddCollateralMethod = MsgGrpc.getAddCollateralMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddCollateralMethod = MsgGrpc.getAddCollateralMethod) == null) {
          MsgGrpc.getAddCollateralMethod = getAddCollateralMethod =
              io.grpc.MethodDescriptor.<shentu.oracle.v1alpha1.Tx.MsgAddCollateral, shentu.oracle.v1alpha1.Tx.MsgAddCollateralResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddCollateral"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.Tx.MsgAddCollateral.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.Tx.MsgAddCollateralResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddCollateral"))
              .build();
        }
      }
    }
    return getAddCollateralMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgReduceCollateral,
      shentu.oracle.v1alpha1.Tx.MsgReduceCollateralResponse> getReduceCollateralMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReduceCollateral",
      requestType = shentu.oracle.v1alpha1.Tx.MsgReduceCollateral.class,
      responseType = shentu.oracle.v1alpha1.Tx.MsgReduceCollateralResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgReduceCollateral,
      shentu.oracle.v1alpha1.Tx.MsgReduceCollateralResponse> getReduceCollateralMethod() {
    io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgReduceCollateral, shentu.oracle.v1alpha1.Tx.MsgReduceCollateralResponse> getReduceCollateralMethod;
    if ((getReduceCollateralMethod = MsgGrpc.getReduceCollateralMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getReduceCollateralMethod = MsgGrpc.getReduceCollateralMethod) == null) {
          MsgGrpc.getReduceCollateralMethod = getReduceCollateralMethod =
              io.grpc.MethodDescriptor.<shentu.oracle.v1alpha1.Tx.MsgReduceCollateral, shentu.oracle.v1alpha1.Tx.MsgReduceCollateralResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReduceCollateral"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.Tx.MsgReduceCollateral.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.Tx.MsgReduceCollateralResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ReduceCollateral"))
              .build();
        }
      }
    }
    return getReduceCollateralMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgWithdrawReward,
      shentu.oracle.v1alpha1.Tx.MsgWithdrawRewardResponse> getWithdrawRewardMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawReward",
      requestType = shentu.oracle.v1alpha1.Tx.MsgWithdrawReward.class,
      responseType = shentu.oracle.v1alpha1.Tx.MsgWithdrawRewardResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgWithdrawReward,
      shentu.oracle.v1alpha1.Tx.MsgWithdrawRewardResponse> getWithdrawRewardMethod() {
    io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgWithdrawReward, shentu.oracle.v1alpha1.Tx.MsgWithdrawRewardResponse> getWithdrawRewardMethod;
    if ((getWithdrawRewardMethod = MsgGrpc.getWithdrawRewardMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawRewardMethod = MsgGrpc.getWithdrawRewardMethod) == null) {
          MsgGrpc.getWithdrawRewardMethod = getWithdrawRewardMethod =
              io.grpc.MethodDescriptor.<shentu.oracle.v1alpha1.Tx.MsgWithdrawReward, shentu.oracle.v1alpha1.Tx.MsgWithdrawRewardResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WithdrawReward"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.Tx.MsgWithdrawReward.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.Tx.MsgWithdrawRewardResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WithdrawReward"))
              .build();
        }
      }
    }
    return getWithdrawRewardMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgCreateTask,
      shentu.oracle.v1alpha1.Tx.MsgCreateTaskResponse> getCreateTaskMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateTask",
      requestType = shentu.oracle.v1alpha1.Tx.MsgCreateTask.class,
      responseType = shentu.oracle.v1alpha1.Tx.MsgCreateTaskResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgCreateTask,
      shentu.oracle.v1alpha1.Tx.MsgCreateTaskResponse> getCreateTaskMethod() {
    io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgCreateTask, shentu.oracle.v1alpha1.Tx.MsgCreateTaskResponse> getCreateTaskMethod;
    if ((getCreateTaskMethod = MsgGrpc.getCreateTaskMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateTaskMethod = MsgGrpc.getCreateTaskMethod) == null) {
          MsgGrpc.getCreateTaskMethod = getCreateTaskMethod =
              io.grpc.MethodDescriptor.<shentu.oracle.v1alpha1.Tx.MsgCreateTask, shentu.oracle.v1alpha1.Tx.MsgCreateTaskResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateTask"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.Tx.MsgCreateTask.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.Tx.MsgCreateTaskResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateTask"))
              .build();
        }
      }
    }
    return getCreateTaskMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgTaskResponse,
      shentu.oracle.v1alpha1.Tx.MsgTaskResponseResponse> getTaskResponseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TaskResponse",
      requestType = shentu.oracle.v1alpha1.Tx.MsgTaskResponse.class,
      responseType = shentu.oracle.v1alpha1.Tx.MsgTaskResponseResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgTaskResponse,
      shentu.oracle.v1alpha1.Tx.MsgTaskResponseResponse> getTaskResponseMethod() {
    io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgTaskResponse, shentu.oracle.v1alpha1.Tx.MsgTaskResponseResponse> getTaskResponseMethod;
    if ((getTaskResponseMethod = MsgGrpc.getTaskResponseMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getTaskResponseMethod = MsgGrpc.getTaskResponseMethod) == null) {
          MsgGrpc.getTaskResponseMethod = getTaskResponseMethod =
              io.grpc.MethodDescriptor.<shentu.oracle.v1alpha1.Tx.MsgTaskResponse, shentu.oracle.v1alpha1.Tx.MsgTaskResponseResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TaskResponse"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.Tx.MsgTaskResponse.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.Tx.MsgTaskResponseResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("TaskResponse"))
              .build();
        }
      }
    }
    return getTaskResponseMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgDeleteTask,
      shentu.oracle.v1alpha1.Tx.MsgDeleteTaskResponse> getDeleteTaskMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteTask",
      requestType = shentu.oracle.v1alpha1.Tx.MsgDeleteTask.class,
      responseType = shentu.oracle.v1alpha1.Tx.MsgDeleteTaskResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgDeleteTask,
      shentu.oracle.v1alpha1.Tx.MsgDeleteTaskResponse> getDeleteTaskMethod() {
    io.grpc.MethodDescriptor<shentu.oracle.v1alpha1.Tx.MsgDeleteTask, shentu.oracle.v1alpha1.Tx.MsgDeleteTaskResponse> getDeleteTaskMethod;
    if ((getDeleteTaskMethod = MsgGrpc.getDeleteTaskMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteTaskMethod = MsgGrpc.getDeleteTaskMethod) == null) {
          MsgGrpc.getDeleteTaskMethod = getDeleteTaskMethod =
              io.grpc.MethodDescriptor.<shentu.oracle.v1alpha1.Tx.MsgDeleteTask, shentu.oracle.v1alpha1.Tx.MsgDeleteTaskResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteTask"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.Tx.MsgDeleteTask.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shentu.oracle.v1alpha1.Tx.MsgDeleteTaskResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteTask"))
              .build();
        }
      }
    }
    return getDeleteTaskMethod;
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
   * Msg defines the shield Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     */
    public void createOperator(shentu.oracle.v1alpha1.Tx.MsgCreateOperator request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgCreateOperatorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateOperatorMethod(), responseObserver);
    }

    /**
     */
    public void removeOperator(shentu.oracle.v1alpha1.Tx.MsgRemoveOperator request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgRemoveOperatorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveOperatorMethod(), responseObserver);
    }

    /**
     */
    public void addCollateral(shentu.oracle.v1alpha1.Tx.MsgAddCollateral request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgAddCollateralResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddCollateralMethod(), responseObserver);
    }

    /**
     */
    public void reduceCollateral(shentu.oracle.v1alpha1.Tx.MsgReduceCollateral request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgReduceCollateralResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReduceCollateralMethod(), responseObserver);
    }

    /**
     */
    public void withdrawReward(shentu.oracle.v1alpha1.Tx.MsgWithdrawReward request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgWithdrawRewardResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawRewardMethod(), responseObserver);
    }

    /**
     */
    public void createTask(shentu.oracle.v1alpha1.Tx.MsgCreateTask request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgCreateTaskResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateTaskMethod(), responseObserver);
    }

    /**
     */
    public void taskResponse(shentu.oracle.v1alpha1.Tx.MsgTaskResponse request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgTaskResponseResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTaskResponseMethod(), responseObserver);
    }

    /**
     */
    public void deleteTask(shentu.oracle.v1alpha1.Tx.MsgDeleteTask request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgDeleteTaskResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteTaskMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateOperatorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.oracle.v1alpha1.Tx.MsgCreateOperator,
                shentu.oracle.v1alpha1.Tx.MsgCreateOperatorResponse>(
                  this, METHODID_CREATE_OPERATOR)))
          .addMethod(
            getRemoveOperatorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.oracle.v1alpha1.Tx.MsgRemoveOperator,
                shentu.oracle.v1alpha1.Tx.MsgRemoveOperatorResponse>(
                  this, METHODID_REMOVE_OPERATOR)))
          .addMethod(
            getAddCollateralMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.oracle.v1alpha1.Tx.MsgAddCollateral,
                shentu.oracle.v1alpha1.Tx.MsgAddCollateralResponse>(
                  this, METHODID_ADD_COLLATERAL)))
          .addMethod(
            getReduceCollateralMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.oracle.v1alpha1.Tx.MsgReduceCollateral,
                shentu.oracle.v1alpha1.Tx.MsgReduceCollateralResponse>(
                  this, METHODID_REDUCE_COLLATERAL)))
          .addMethod(
            getWithdrawRewardMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.oracle.v1alpha1.Tx.MsgWithdrawReward,
                shentu.oracle.v1alpha1.Tx.MsgWithdrawRewardResponse>(
                  this, METHODID_WITHDRAW_REWARD)))
          .addMethod(
            getCreateTaskMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.oracle.v1alpha1.Tx.MsgCreateTask,
                shentu.oracle.v1alpha1.Tx.MsgCreateTaskResponse>(
                  this, METHODID_CREATE_TASK)))
          .addMethod(
            getTaskResponseMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.oracle.v1alpha1.Tx.MsgTaskResponse,
                shentu.oracle.v1alpha1.Tx.MsgTaskResponseResponse>(
                  this, METHODID_TASK_RESPONSE)))
          .addMethod(
            getDeleteTaskMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shentu.oracle.v1alpha1.Tx.MsgDeleteTask,
                shentu.oracle.v1alpha1.Tx.MsgDeleteTaskResponse>(
                  this, METHODID_DELETE_TASK)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the shield Msg service.
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
    public void createOperator(shentu.oracle.v1alpha1.Tx.MsgCreateOperator request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgCreateOperatorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateOperatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void removeOperator(shentu.oracle.v1alpha1.Tx.MsgRemoveOperator request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgRemoveOperatorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveOperatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addCollateral(shentu.oracle.v1alpha1.Tx.MsgAddCollateral request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgAddCollateralResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddCollateralMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void reduceCollateral(shentu.oracle.v1alpha1.Tx.MsgReduceCollateral request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgReduceCollateralResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReduceCollateralMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void withdrawReward(shentu.oracle.v1alpha1.Tx.MsgWithdrawReward request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgWithdrawRewardResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawRewardMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createTask(shentu.oracle.v1alpha1.Tx.MsgCreateTask request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgCreateTaskResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateTaskMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void taskResponse(shentu.oracle.v1alpha1.Tx.MsgTaskResponse request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgTaskResponseResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTaskResponseMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteTask(shentu.oracle.v1alpha1.Tx.MsgDeleteTask request,
        io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgDeleteTaskResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteTaskMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the shield Msg service.
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
    public shentu.oracle.v1alpha1.Tx.MsgCreateOperatorResponse createOperator(shentu.oracle.v1alpha1.Tx.MsgCreateOperator request) {
      return blockingUnaryCall(
          getChannel(), getCreateOperatorMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.oracle.v1alpha1.Tx.MsgRemoveOperatorResponse removeOperator(shentu.oracle.v1alpha1.Tx.MsgRemoveOperator request) {
      return blockingUnaryCall(
          getChannel(), getRemoveOperatorMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.oracle.v1alpha1.Tx.MsgAddCollateralResponse addCollateral(shentu.oracle.v1alpha1.Tx.MsgAddCollateral request) {
      return blockingUnaryCall(
          getChannel(), getAddCollateralMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.oracle.v1alpha1.Tx.MsgReduceCollateralResponse reduceCollateral(shentu.oracle.v1alpha1.Tx.MsgReduceCollateral request) {
      return blockingUnaryCall(
          getChannel(), getReduceCollateralMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.oracle.v1alpha1.Tx.MsgWithdrawRewardResponse withdrawReward(shentu.oracle.v1alpha1.Tx.MsgWithdrawReward request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawRewardMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.oracle.v1alpha1.Tx.MsgCreateTaskResponse createTask(shentu.oracle.v1alpha1.Tx.MsgCreateTask request) {
      return blockingUnaryCall(
          getChannel(), getCreateTaskMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.oracle.v1alpha1.Tx.MsgTaskResponseResponse taskResponse(shentu.oracle.v1alpha1.Tx.MsgTaskResponse request) {
      return blockingUnaryCall(
          getChannel(), getTaskResponseMethod(), getCallOptions(), request);
    }

    /**
     */
    public shentu.oracle.v1alpha1.Tx.MsgDeleteTaskResponse deleteTask(shentu.oracle.v1alpha1.Tx.MsgDeleteTask request) {
      return blockingUnaryCall(
          getChannel(), getDeleteTaskMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the shield Msg service.
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
    public com.google.common.util.concurrent.ListenableFuture<shentu.oracle.v1alpha1.Tx.MsgCreateOperatorResponse> createOperator(
        shentu.oracle.v1alpha1.Tx.MsgCreateOperator request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateOperatorMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.oracle.v1alpha1.Tx.MsgRemoveOperatorResponse> removeOperator(
        shentu.oracle.v1alpha1.Tx.MsgRemoveOperator request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveOperatorMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.oracle.v1alpha1.Tx.MsgAddCollateralResponse> addCollateral(
        shentu.oracle.v1alpha1.Tx.MsgAddCollateral request) {
      return futureUnaryCall(
          getChannel().newCall(getAddCollateralMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.oracle.v1alpha1.Tx.MsgReduceCollateralResponse> reduceCollateral(
        shentu.oracle.v1alpha1.Tx.MsgReduceCollateral request) {
      return futureUnaryCall(
          getChannel().newCall(getReduceCollateralMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.oracle.v1alpha1.Tx.MsgWithdrawRewardResponse> withdrawReward(
        shentu.oracle.v1alpha1.Tx.MsgWithdrawReward request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawRewardMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.oracle.v1alpha1.Tx.MsgCreateTaskResponse> createTask(
        shentu.oracle.v1alpha1.Tx.MsgCreateTask request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateTaskMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.oracle.v1alpha1.Tx.MsgTaskResponseResponse> taskResponse(
        shentu.oracle.v1alpha1.Tx.MsgTaskResponse request) {
      return futureUnaryCall(
          getChannel().newCall(getTaskResponseMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shentu.oracle.v1alpha1.Tx.MsgDeleteTaskResponse> deleteTask(
        shentu.oracle.v1alpha1.Tx.MsgDeleteTask request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteTaskMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_OPERATOR = 0;
  private static final int METHODID_REMOVE_OPERATOR = 1;
  private static final int METHODID_ADD_COLLATERAL = 2;
  private static final int METHODID_REDUCE_COLLATERAL = 3;
  private static final int METHODID_WITHDRAW_REWARD = 4;
  private static final int METHODID_CREATE_TASK = 5;
  private static final int METHODID_TASK_RESPONSE = 6;
  private static final int METHODID_DELETE_TASK = 7;

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
        case METHODID_CREATE_OPERATOR:
          serviceImpl.createOperator((shentu.oracle.v1alpha1.Tx.MsgCreateOperator) request,
              (io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgCreateOperatorResponse>) responseObserver);
          break;
        case METHODID_REMOVE_OPERATOR:
          serviceImpl.removeOperator((shentu.oracle.v1alpha1.Tx.MsgRemoveOperator) request,
              (io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgRemoveOperatorResponse>) responseObserver);
          break;
        case METHODID_ADD_COLLATERAL:
          serviceImpl.addCollateral((shentu.oracle.v1alpha1.Tx.MsgAddCollateral) request,
              (io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgAddCollateralResponse>) responseObserver);
          break;
        case METHODID_REDUCE_COLLATERAL:
          serviceImpl.reduceCollateral((shentu.oracle.v1alpha1.Tx.MsgReduceCollateral) request,
              (io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgReduceCollateralResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW_REWARD:
          serviceImpl.withdrawReward((shentu.oracle.v1alpha1.Tx.MsgWithdrawReward) request,
              (io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgWithdrawRewardResponse>) responseObserver);
          break;
        case METHODID_CREATE_TASK:
          serviceImpl.createTask((shentu.oracle.v1alpha1.Tx.MsgCreateTask) request,
              (io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgCreateTaskResponse>) responseObserver);
          break;
        case METHODID_TASK_RESPONSE:
          serviceImpl.taskResponse((shentu.oracle.v1alpha1.Tx.MsgTaskResponse) request,
              (io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgTaskResponseResponse>) responseObserver);
          break;
        case METHODID_DELETE_TASK:
          serviceImpl.deleteTask((shentu.oracle.v1alpha1.Tx.MsgDeleteTask) request,
              (io.grpc.stub.StreamObserver<shentu.oracle.v1alpha1.Tx.MsgDeleteTaskResponse>) responseObserver);
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
      return shentu.oracle.v1alpha1.Tx.getDescriptor();
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
              .addMethod(getCreateOperatorMethod())
              .addMethod(getRemoveOperatorMethod())
              .addMethod(getAddCollateralMethod())
              .addMethod(getReduceCollateralMethod())
              .addMethod(getWithdrawRewardMethod())
              .addMethod(getCreateTaskMethod())
              .addMethod(getTaskResponseMethod())
              .addMethod(getDeleteTaskMethod())
              .build();
        }
      }
    }
    return result;
  }
}
