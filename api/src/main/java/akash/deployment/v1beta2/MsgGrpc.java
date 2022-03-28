package akash.deployment.v1beta2;

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
 * Msg defines the deployment Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: akash/deployment/v1beta2/service.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "akash.deployment.v1beta2.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeployment,
      akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeploymentResponse> getCreateDeploymentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateDeployment",
      requestType = akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeployment.class,
      responseType = akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeploymentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeployment,
      akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeploymentResponse> getCreateDeploymentMethod() {
    io.grpc.MethodDescriptor<akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeployment, akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeploymentResponse> getCreateDeploymentMethod;
    if ((getCreateDeploymentMethod = MsgGrpc.getCreateDeploymentMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateDeploymentMethod = MsgGrpc.getCreateDeploymentMethod) == null) {
          MsgGrpc.getCreateDeploymentMethod = getCreateDeploymentMethod =
              io.grpc.MethodDescriptor.<akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeployment, akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeploymentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateDeployment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeployment.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeploymentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateDeployment"))
              .build();
        }
      }
    }
    return getCreateDeploymentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeployment,
      akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeploymentResponse> getDepositDeploymentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DepositDeployment",
      requestType = akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeployment.class,
      responseType = akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeploymentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeployment,
      akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeploymentResponse> getDepositDeploymentMethod() {
    io.grpc.MethodDescriptor<akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeployment, akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeploymentResponse> getDepositDeploymentMethod;
    if ((getDepositDeploymentMethod = MsgGrpc.getDepositDeploymentMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDepositDeploymentMethod = MsgGrpc.getDepositDeploymentMethod) == null) {
          MsgGrpc.getDepositDeploymentMethod = getDepositDeploymentMethod =
              io.grpc.MethodDescriptor.<akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeployment, akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeploymentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DepositDeployment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeployment.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeploymentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DepositDeployment"))
              .build();
        }
      }
    }
    return getDepositDeploymentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeployment,
      akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeploymentResponse> getUpdateDeploymentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateDeployment",
      requestType = akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeployment.class,
      responseType = akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeploymentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeployment,
      akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeploymentResponse> getUpdateDeploymentMethod() {
    io.grpc.MethodDescriptor<akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeployment, akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeploymentResponse> getUpdateDeploymentMethod;
    if ((getUpdateDeploymentMethod = MsgGrpc.getUpdateDeploymentMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateDeploymentMethod = MsgGrpc.getUpdateDeploymentMethod) == null) {
          MsgGrpc.getUpdateDeploymentMethod = getUpdateDeploymentMethod =
              io.grpc.MethodDescriptor.<akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeployment, akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeploymentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateDeployment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeployment.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeploymentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateDeployment"))
              .build();
        }
      }
    }
    return getUpdateDeploymentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeployment,
      akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeploymentResponse> getCloseDeploymentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CloseDeployment",
      requestType = akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeployment.class,
      responseType = akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeploymentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeployment,
      akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeploymentResponse> getCloseDeploymentMethod() {
    io.grpc.MethodDescriptor<akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeployment, akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeploymentResponse> getCloseDeploymentMethod;
    if ((getCloseDeploymentMethod = MsgGrpc.getCloseDeploymentMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCloseDeploymentMethod = MsgGrpc.getCloseDeploymentMethod) == null) {
          MsgGrpc.getCloseDeploymentMethod = getCloseDeploymentMethod =
              io.grpc.MethodDescriptor.<akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeployment, akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeploymentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CloseDeployment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeployment.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeploymentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CloseDeployment"))
              .build();
        }
      }
    }
    return getCloseDeploymentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.deployment.v1beta2.Groupmsg.MsgCloseGroup,
      akash.deployment.v1beta2.Groupmsg.MsgCloseGroupResponse> getCloseGroupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CloseGroup",
      requestType = akash.deployment.v1beta2.Groupmsg.MsgCloseGroup.class,
      responseType = akash.deployment.v1beta2.Groupmsg.MsgCloseGroupResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.deployment.v1beta2.Groupmsg.MsgCloseGroup,
      akash.deployment.v1beta2.Groupmsg.MsgCloseGroupResponse> getCloseGroupMethod() {
    io.grpc.MethodDescriptor<akash.deployment.v1beta2.Groupmsg.MsgCloseGroup, akash.deployment.v1beta2.Groupmsg.MsgCloseGroupResponse> getCloseGroupMethod;
    if ((getCloseGroupMethod = MsgGrpc.getCloseGroupMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCloseGroupMethod = MsgGrpc.getCloseGroupMethod) == null) {
          MsgGrpc.getCloseGroupMethod = getCloseGroupMethod =
              io.grpc.MethodDescriptor.<akash.deployment.v1beta2.Groupmsg.MsgCloseGroup, akash.deployment.v1beta2.Groupmsg.MsgCloseGroupResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CloseGroup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.deployment.v1beta2.Groupmsg.MsgCloseGroup.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.deployment.v1beta2.Groupmsg.MsgCloseGroupResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CloseGroup"))
              .build();
        }
      }
    }
    return getCloseGroupMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.deployment.v1beta2.Groupmsg.MsgPauseGroup,
      akash.deployment.v1beta2.Groupmsg.MsgPauseGroupResponse> getPauseGroupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PauseGroup",
      requestType = akash.deployment.v1beta2.Groupmsg.MsgPauseGroup.class,
      responseType = akash.deployment.v1beta2.Groupmsg.MsgPauseGroupResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.deployment.v1beta2.Groupmsg.MsgPauseGroup,
      akash.deployment.v1beta2.Groupmsg.MsgPauseGroupResponse> getPauseGroupMethod() {
    io.grpc.MethodDescriptor<akash.deployment.v1beta2.Groupmsg.MsgPauseGroup, akash.deployment.v1beta2.Groupmsg.MsgPauseGroupResponse> getPauseGroupMethod;
    if ((getPauseGroupMethod = MsgGrpc.getPauseGroupMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getPauseGroupMethod = MsgGrpc.getPauseGroupMethod) == null) {
          MsgGrpc.getPauseGroupMethod = getPauseGroupMethod =
              io.grpc.MethodDescriptor.<akash.deployment.v1beta2.Groupmsg.MsgPauseGroup, akash.deployment.v1beta2.Groupmsg.MsgPauseGroupResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PauseGroup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.deployment.v1beta2.Groupmsg.MsgPauseGroup.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.deployment.v1beta2.Groupmsg.MsgPauseGroupResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("PauseGroup"))
              .build();
        }
      }
    }
    return getPauseGroupMethod;
  }

  private static volatile io.grpc.MethodDescriptor<akash.deployment.v1beta2.Groupmsg.MsgStartGroup,
      akash.deployment.v1beta2.Groupmsg.MsgStartGroupResponse> getStartGroupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StartGroup",
      requestType = akash.deployment.v1beta2.Groupmsg.MsgStartGroup.class,
      responseType = akash.deployment.v1beta2.Groupmsg.MsgStartGroupResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<akash.deployment.v1beta2.Groupmsg.MsgStartGroup,
      akash.deployment.v1beta2.Groupmsg.MsgStartGroupResponse> getStartGroupMethod() {
    io.grpc.MethodDescriptor<akash.deployment.v1beta2.Groupmsg.MsgStartGroup, akash.deployment.v1beta2.Groupmsg.MsgStartGroupResponse> getStartGroupMethod;
    if ((getStartGroupMethod = MsgGrpc.getStartGroupMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getStartGroupMethod = MsgGrpc.getStartGroupMethod) == null) {
          MsgGrpc.getStartGroupMethod = getStartGroupMethod =
              io.grpc.MethodDescriptor.<akash.deployment.v1beta2.Groupmsg.MsgStartGroup, akash.deployment.v1beta2.Groupmsg.MsgStartGroupResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StartGroup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.deployment.v1beta2.Groupmsg.MsgStartGroup.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  akash.deployment.v1beta2.Groupmsg.MsgStartGroupResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("StartGroup"))
              .build();
        }
      }
    }
    return getStartGroupMethod;
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
   * Msg defines the deployment Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CreateDeployment defines a method to create new deployment given proper inputs.
     * </pre>
     */
    public void createDeployment(akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeployment request,
        io.grpc.stub.StreamObserver<akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeploymentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateDeploymentMethod(), responseObserver);
    }

    /**
     * <pre>
     * DepositDeployment deposits more funds into the deployment account
     * </pre>
     */
    public void depositDeployment(akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeployment request,
        io.grpc.stub.StreamObserver<akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeploymentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositDeploymentMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateDeployment defines a method to update a deployment given proper inputs.
     * </pre>
     */
    public void updateDeployment(akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeployment request,
        io.grpc.stub.StreamObserver<akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeploymentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateDeploymentMethod(), responseObserver);
    }

    /**
     * <pre>
     * CloseDeployment defines a method to close a deployment given proper inputs.
     * </pre>
     */
    public void closeDeployment(akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeployment request,
        io.grpc.stub.StreamObserver<akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeploymentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCloseDeploymentMethod(), responseObserver);
    }

    /**
     * <pre>
     * CloseGroup defines a method to close a group of a deployment given proper inputs.
     * </pre>
     */
    public void closeGroup(akash.deployment.v1beta2.Groupmsg.MsgCloseGroup request,
        io.grpc.stub.StreamObserver<akash.deployment.v1beta2.Groupmsg.MsgCloseGroupResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCloseGroupMethod(), responseObserver);
    }

    /**
     * <pre>
     * PauseGroup defines a method to close a group of a deployment given proper inputs.
     * </pre>
     */
    public void pauseGroup(akash.deployment.v1beta2.Groupmsg.MsgPauseGroup request,
        io.grpc.stub.StreamObserver<akash.deployment.v1beta2.Groupmsg.MsgPauseGroupResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPauseGroupMethod(), responseObserver);
    }

    /**
     * <pre>
     * StartGroup defines a method to close a group of a deployment given proper inputs.
     * </pre>
     */
    public void startGroup(akash.deployment.v1beta2.Groupmsg.MsgStartGroup request,
        io.grpc.stub.StreamObserver<akash.deployment.v1beta2.Groupmsg.MsgStartGroupResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStartGroupMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateDeploymentMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeployment,
                akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeploymentResponse>(
                  this, METHODID_CREATE_DEPLOYMENT)))
          .addMethod(
            getDepositDeploymentMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeployment,
                akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeploymentResponse>(
                  this, METHODID_DEPOSIT_DEPLOYMENT)))
          .addMethod(
            getUpdateDeploymentMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeployment,
                akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeploymentResponse>(
                  this, METHODID_UPDATE_DEPLOYMENT)))
          .addMethod(
            getCloseDeploymentMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeployment,
                akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeploymentResponse>(
                  this, METHODID_CLOSE_DEPLOYMENT)))
          .addMethod(
            getCloseGroupMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.deployment.v1beta2.Groupmsg.MsgCloseGroup,
                akash.deployment.v1beta2.Groupmsg.MsgCloseGroupResponse>(
                  this, METHODID_CLOSE_GROUP)))
          .addMethod(
            getPauseGroupMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.deployment.v1beta2.Groupmsg.MsgPauseGroup,
                akash.deployment.v1beta2.Groupmsg.MsgPauseGroupResponse>(
                  this, METHODID_PAUSE_GROUP)))
          .addMethod(
            getStartGroupMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                akash.deployment.v1beta2.Groupmsg.MsgStartGroup,
                akash.deployment.v1beta2.Groupmsg.MsgStartGroupResponse>(
                  this, METHODID_START_GROUP)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the deployment Msg service.
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
     * CreateDeployment defines a method to create new deployment given proper inputs.
     * </pre>
     */
    public void createDeployment(akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeployment request,
        io.grpc.stub.StreamObserver<akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeploymentResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateDeploymentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DepositDeployment deposits more funds into the deployment account
     * </pre>
     */
    public void depositDeployment(akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeployment request,
        io.grpc.stub.StreamObserver<akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeploymentResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositDeploymentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateDeployment defines a method to update a deployment given proper inputs.
     * </pre>
     */
    public void updateDeployment(akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeployment request,
        io.grpc.stub.StreamObserver<akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeploymentResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateDeploymentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CloseDeployment defines a method to close a deployment given proper inputs.
     * </pre>
     */
    public void closeDeployment(akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeployment request,
        io.grpc.stub.StreamObserver<akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeploymentResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCloseDeploymentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CloseGroup defines a method to close a group of a deployment given proper inputs.
     * </pre>
     */
    public void closeGroup(akash.deployment.v1beta2.Groupmsg.MsgCloseGroup request,
        io.grpc.stub.StreamObserver<akash.deployment.v1beta2.Groupmsg.MsgCloseGroupResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCloseGroupMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PauseGroup defines a method to close a group of a deployment given proper inputs.
     * </pre>
     */
    public void pauseGroup(akash.deployment.v1beta2.Groupmsg.MsgPauseGroup request,
        io.grpc.stub.StreamObserver<akash.deployment.v1beta2.Groupmsg.MsgPauseGroupResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPauseGroupMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * StartGroup defines a method to close a group of a deployment given proper inputs.
     * </pre>
     */
    public void startGroup(akash.deployment.v1beta2.Groupmsg.MsgStartGroup request,
        io.grpc.stub.StreamObserver<akash.deployment.v1beta2.Groupmsg.MsgStartGroupResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStartGroupMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the deployment Msg service.
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
     * CreateDeployment defines a method to create new deployment given proper inputs.
     * </pre>
     */
    public akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeploymentResponse createDeployment(akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeployment request) {
      return blockingUnaryCall(
          getChannel(), getCreateDeploymentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DepositDeployment deposits more funds into the deployment account
     * </pre>
     */
    public akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeploymentResponse depositDeployment(akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeployment request) {
      return blockingUnaryCall(
          getChannel(), getDepositDeploymentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateDeployment defines a method to update a deployment given proper inputs.
     * </pre>
     */
    public akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeploymentResponse updateDeployment(akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeployment request) {
      return blockingUnaryCall(
          getChannel(), getUpdateDeploymentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CloseDeployment defines a method to close a deployment given proper inputs.
     * </pre>
     */
    public akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeploymentResponse closeDeployment(akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeployment request) {
      return blockingUnaryCall(
          getChannel(), getCloseDeploymentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CloseGroup defines a method to close a group of a deployment given proper inputs.
     * </pre>
     */
    public akash.deployment.v1beta2.Groupmsg.MsgCloseGroupResponse closeGroup(akash.deployment.v1beta2.Groupmsg.MsgCloseGroup request) {
      return blockingUnaryCall(
          getChannel(), getCloseGroupMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PauseGroup defines a method to close a group of a deployment given proper inputs.
     * </pre>
     */
    public akash.deployment.v1beta2.Groupmsg.MsgPauseGroupResponse pauseGroup(akash.deployment.v1beta2.Groupmsg.MsgPauseGroup request) {
      return blockingUnaryCall(
          getChannel(), getPauseGroupMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * StartGroup defines a method to close a group of a deployment given proper inputs.
     * </pre>
     */
    public akash.deployment.v1beta2.Groupmsg.MsgStartGroupResponse startGroup(akash.deployment.v1beta2.Groupmsg.MsgStartGroup request) {
      return blockingUnaryCall(
          getChannel(), getStartGroupMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the deployment Msg service.
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
     * CreateDeployment defines a method to create new deployment given proper inputs.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeploymentResponse> createDeployment(
        akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeployment request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateDeploymentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DepositDeployment deposits more funds into the deployment account
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeploymentResponse> depositDeployment(
        akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeployment request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositDeploymentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateDeployment defines a method to update a deployment given proper inputs.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeploymentResponse> updateDeployment(
        akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeployment request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateDeploymentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CloseDeployment defines a method to close a deployment given proper inputs.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeploymentResponse> closeDeployment(
        akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeployment request) {
      return futureUnaryCall(
          getChannel().newCall(getCloseDeploymentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CloseGroup defines a method to close a group of a deployment given proper inputs.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.deployment.v1beta2.Groupmsg.MsgCloseGroupResponse> closeGroup(
        akash.deployment.v1beta2.Groupmsg.MsgCloseGroup request) {
      return futureUnaryCall(
          getChannel().newCall(getCloseGroupMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PauseGroup defines a method to close a group of a deployment given proper inputs.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.deployment.v1beta2.Groupmsg.MsgPauseGroupResponse> pauseGroup(
        akash.deployment.v1beta2.Groupmsg.MsgPauseGroup request) {
      return futureUnaryCall(
          getChannel().newCall(getPauseGroupMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * StartGroup defines a method to close a group of a deployment given proper inputs.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<akash.deployment.v1beta2.Groupmsg.MsgStartGroupResponse> startGroup(
        akash.deployment.v1beta2.Groupmsg.MsgStartGroup request) {
      return futureUnaryCall(
          getChannel().newCall(getStartGroupMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_DEPLOYMENT = 0;
  private static final int METHODID_DEPOSIT_DEPLOYMENT = 1;
  private static final int METHODID_UPDATE_DEPLOYMENT = 2;
  private static final int METHODID_CLOSE_DEPLOYMENT = 3;
  private static final int METHODID_CLOSE_GROUP = 4;
  private static final int METHODID_PAUSE_GROUP = 5;
  private static final int METHODID_START_GROUP = 6;

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
        case METHODID_CREATE_DEPLOYMENT:
          serviceImpl.createDeployment((akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeployment) request,
              (io.grpc.stub.StreamObserver<akash.deployment.v1beta2.Deploymentmsg.MsgCreateDeploymentResponse>) responseObserver);
          break;
        case METHODID_DEPOSIT_DEPLOYMENT:
          serviceImpl.depositDeployment((akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeployment) request,
              (io.grpc.stub.StreamObserver<akash.deployment.v1beta2.Deploymentmsg.MsgDepositDeploymentResponse>) responseObserver);
          break;
        case METHODID_UPDATE_DEPLOYMENT:
          serviceImpl.updateDeployment((akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeployment) request,
              (io.grpc.stub.StreamObserver<akash.deployment.v1beta2.Deploymentmsg.MsgUpdateDeploymentResponse>) responseObserver);
          break;
        case METHODID_CLOSE_DEPLOYMENT:
          serviceImpl.closeDeployment((akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeployment) request,
              (io.grpc.stub.StreamObserver<akash.deployment.v1beta2.Deploymentmsg.MsgCloseDeploymentResponse>) responseObserver);
          break;
        case METHODID_CLOSE_GROUP:
          serviceImpl.closeGroup((akash.deployment.v1beta2.Groupmsg.MsgCloseGroup) request,
              (io.grpc.stub.StreamObserver<akash.deployment.v1beta2.Groupmsg.MsgCloseGroupResponse>) responseObserver);
          break;
        case METHODID_PAUSE_GROUP:
          serviceImpl.pauseGroup((akash.deployment.v1beta2.Groupmsg.MsgPauseGroup) request,
              (io.grpc.stub.StreamObserver<akash.deployment.v1beta2.Groupmsg.MsgPauseGroupResponse>) responseObserver);
          break;
        case METHODID_START_GROUP:
          serviceImpl.startGroup((akash.deployment.v1beta2.Groupmsg.MsgStartGroup) request,
              (io.grpc.stub.StreamObserver<akash.deployment.v1beta2.Groupmsg.MsgStartGroupResponse>) responseObserver);
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
      return akash.deployment.v1beta2.Service.getDescriptor();
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
              .addMethod(getCreateDeploymentMethod())
              .addMethod(getDepositDeploymentMethod())
              .addMethod(getUpdateDeploymentMethod())
              .addMethod(getCloseDeploymentMethod())
              .addMethod(getCloseGroupMethod())
              .addMethod(getPauseGroupMethod())
              .addMethod(getStartGroupMethod())
              .build();
        }
      }
    }
    return result;
  }
}
