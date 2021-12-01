package cosmos.farming.v1beta1;

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
 * Msg defines the farming Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: tendermint/farming/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "cosmos.farming.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlan,
      cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlanResponse> getCreateFixedAmountPlanMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateFixedAmountPlan",
      requestType = cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlan.class,
      responseType = cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlanResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlan,
      cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlanResponse> getCreateFixedAmountPlanMethod() {
    io.grpc.MethodDescriptor<cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlan, cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlanResponse> getCreateFixedAmountPlanMethod;
    if ((getCreateFixedAmountPlanMethod = MsgGrpc.getCreateFixedAmountPlanMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateFixedAmountPlanMethod = MsgGrpc.getCreateFixedAmountPlanMethod) == null) {
          MsgGrpc.getCreateFixedAmountPlanMethod = getCreateFixedAmountPlanMethod =
              io.grpc.MethodDescriptor.<cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlan, cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlanResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateFixedAmountPlan"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlan.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlanResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateFixedAmountPlan"))
              .build();
        }
      }
    }
    return getCreateFixedAmountPlanMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.farming.v1beta1.Tx.MsgCreateRatioPlan,
      cosmos.farming.v1beta1.Tx.MsgCreateRatioPlanResponse> getCreateRatioPlanMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateRatioPlan",
      requestType = cosmos.farming.v1beta1.Tx.MsgCreateRatioPlan.class,
      responseType = cosmos.farming.v1beta1.Tx.MsgCreateRatioPlanResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.farming.v1beta1.Tx.MsgCreateRatioPlan,
      cosmos.farming.v1beta1.Tx.MsgCreateRatioPlanResponse> getCreateRatioPlanMethod() {
    io.grpc.MethodDescriptor<cosmos.farming.v1beta1.Tx.MsgCreateRatioPlan, cosmos.farming.v1beta1.Tx.MsgCreateRatioPlanResponse> getCreateRatioPlanMethod;
    if ((getCreateRatioPlanMethod = MsgGrpc.getCreateRatioPlanMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateRatioPlanMethod = MsgGrpc.getCreateRatioPlanMethod) == null) {
          MsgGrpc.getCreateRatioPlanMethod = getCreateRatioPlanMethod =
              io.grpc.MethodDescriptor.<cosmos.farming.v1beta1.Tx.MsgCreateRatioPlan, cosmos.farming.v1beta1.Tx.MsgCreateRatioPlanResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateRatioPlan"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.Tx.MsgCreateRatioPlan.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.Tx.MsgCreateRatioPlanResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateRatioPlan"))
              .build();
        }
      }
    }
    return getCreateRatioPlanMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.farming.v1beta1.Tx.MsgStake,
      cosmos.farming.v1beta1.Tx.MsgStakeResponse> getStakeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Stake",
      requestType = cosmos.farming.v1beta1.Tx.MsgStake.class,
      responseType = cosmos.farming.v1beta1.Tx.MsgStakeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.farming.v1beta1.Tx.MsgStake,
      cosmos.farming.v1beta1.Tx.MsgStakeResponse> getStakeMethod() {
    io.grpc.MethodDescriptor<cosmos.farming.v1beta1.Tx.MsgStake, cosmos.farming.v1beta1.Tx.MsgStakeResponse> getStakeMethod;
    if ((getStakeMethod = MsgGrpc.getStakeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getStakeMethod = MsgGrpc.getStakeMethod) == null) {
          MsgGrpc.getStakeMethod = getStakeMethod =
              io.grpc.MethodDescriptor.<cosmos.farming.v1beta1.Tx.MsgStake, cosmos.farming.v1beta1.Tx.MsgStakeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Stake"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.Tx.MsgStake.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.Tx.MsgStakeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Stake"))
              .build();
        }
      }
    }
    return getStakeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.farming.v1beta1.Tx.MsgUnstake,
      cosmos.farming.v1beta1.Tx.MsgUnstakeResponse> getUnstakeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Unstake",
      requestType = cosmos.farming.v1beta1.Tx.MsgUnstake.class,
      responseType = cosmos.farming.v1beta1.Tx.MsgUnstakeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.farming.v1beta1.Tx.MsgUnstake,
      cosmos.farming.v1beta1.Tx.MsgUnstakeResponse> getUnstakeMethod() {
    io.grpc.MethodDescriptor<cosmos.farming.v1beta1.Tx.MsgUnstake, cosmos.farming.v1beta1.Tx.MsgUnstakeResponse> getUnstakeMethod;
    if ((getUnstakeMethod = MsgGrpc.getUnstakeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUnstakeMethod = MsgGrpc.getUnstakeMethod) == null) {
          MsgGrpc.getUnstakeMethod = getUnstakeMethod =
              io.grpc.MethodDescriptor.<cosmos.farming.v1beta1.Tx.MsgUnstake, cosmos.farming.v1beta1.Tx.MsgUnstakeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Unstake"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.Tx.MsgUnstake.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.Tx.MsgUnstakeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Unstake"))
              .build();
        }
      }
    }
    return getUnstakeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.farming.v1beta1.Tx.MsgHarvest,
      cosmos.farming.v1beta1.Tx.MsgHarvestResponse> getHarvestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Harvest",
      requestType = cosmos.farming.v1beta1.Tx.MsgHarvest.class,
      responseType = cosmos.farming.v1beta1.Tx.MsgHarvestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.farming.v1beta1.Tx.MsgHarvest,
      cosmos.farming.v1beta1.Tx.MsgHarvestResponse> getHarvestMethod() {
    io.grpc.MethodDescriptor<cosmos.farming.v1beta1.Tx.MsgHarvest, cosmos.farming.v1beta1.Tx.MsgHarvestResponse> getHarvestMethod;
    if ((getHarvestMethod = MsgGrpc.getHarvestMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getHarvestMethod = MsgGrpc.getHarvestMethod) == null) {
          MsgGrpc.getHarvestMethod = getHarvestMethod =
              io.grpc.MethodDescriptor.<cosmos.farming.v1beta1.Tx.MsgHarvest, cosmos.farming.v1beta1.Tx.MsgHarvestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Harvest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.Tx.MsgHarvest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.Tx.MsgHarvestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Harvest"))
              .build();
        }
      }
    }
    return getHarvestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.farming.v1beta1.Tx.MsgAdvanceEpoch,
      cosmos.farming.v1beta1.Tx.MsgAdvanceEpochResponse> getAdvanceEpochMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AdvanceEpoch",
      requestType = cosmos.farming.v1beta1.Tx.MsgAdvanceEpoch.class,
      responseType = cosmos.farming.v1beta1.Tx.MsgAdvanceEpochResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.farming.v1beta1.Tx.MsgAdvanceEpoch,
      cosmos.farming.v1beta1.Tx.MsgAdvanceEpochResponse> getAdvanceEpochMethod() {
    io.grpc.MethodDescriptor<cosmos.farming.v1beta1.Tx.MsgAdvanceEpoch, cosmos.farming.v1beta1.Tx.MsgAdvanceEpochResponse> getAdvanceEpochMethod;
    if ((getAdvanceEpochMethod = MsgGrpc.getAdvanceEpochMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAdvanceEpochMethod = MsgGrpc.getAdvanceEpochMethod) == null) {
          MsgGrpc.getAdvanceEpochMethod = getAdvanceEpochMethod =
              io.grpc.MethodDescriptor.<cosmos.farming.v1beta1.Tx.MsgAdvanceEpoch, cosmos.farming.v1beta1.Tx.MsgAdvanceEpochResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AdvanceEpoch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.Tx.MsgAdvanceEpoch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.farming.v1beta1.Tx.MsgAdvanceEpochResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AdvanceEpoch"))
              .build();
        }
      }
    }
    return getAdvanceEpochMethod;
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
   * Msg defines the farming Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CreateFixedAmountPlan defines a method for creating a new fixed amount
     * farming plan
     * </pre>
     */
    public void createFixedAmountPlan(cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlan request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlanResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateFixedAmountPlanMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateRatioPlan defines a method for creating a new ratio farming plan
     * </pre>
     */
    public void createRatioPlan(cosmos.farming.v1beta1.Tx.MsgCreateRatioPlan request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.Tx.MsgCreateRatioPlanResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateRatioPlanMethod(), responseObserver);
    }

    /**
     * <pre>
     * Stake defines a method for staking coins into the farming plan
     * </pre>
     */
    public void stake(cosmos.farming.v1beta1.Tx.MsgStake request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.Tx.MsgStakeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStakeMethod(), responseObserver);
    }

    /**
     * <pre>
     * Unstake defines a method for unstaking coins from the farming plan
     * </pre>
     */
    public void unstake(cosmos.farming.v1beta1.Tx.MsgUnstake request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.Tx.MsgUnstakeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnstakeMethod(), responseObserver);
    }

    /**
     * <pre>
     * Harvest defines a method for claiming farming rewards
     * </pre>
     */
    public void harvest(cosmos.farming.v1beta1.Tx.MsgHarvest request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.Tx.MsgHarvestResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHarvestMethod(), responseObserver);
    }

    /**
     * <pre>
     * AdvanceEpoch defines a method for advancing epoch by one, just for testing purpose
     * and shouldn't be used in real world
     * </pre>
     */
    public void advanceEpoch(cosmos.farming.v1beta1.Tx.MsgAdvanceEpoch request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.Tx.MsgAdvanceEpochResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAdvanceEpochMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateFixedAmountPlanMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlan,
                cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlanResponse>(
                  this, METHODID_CREATE_FIXED_AMOUNT_PLAN)))
          .addMethod(
            getCreateRatioPlanMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.farming.v1beta1.Tx.MsgCreateRatioPlan,
                cosmos.farming.v1beta1.Tx.MsgCreateRatioPlanResponse>(
                  this, METHODID_CREATE_RATIO_PLAN)))
          .addMethod(
            getStakeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.farming.v1beta1.Tx.MsgStake,
                cosmos.farming.v1beta1.Tx.MsgStakeResponse>(
                  this, METHODID_STAKE)))
          .addMethod(
            getUnstakeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.farming.v1beta1.Tx.MsgUnstake,
                cosmos.farming.v1beta1.Tx.MsgUnstakeResponse>(
                  this, METHODID_UNSTAKE)))
          .addMethod(
            getHarvestMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.farming.v1beta1.Tx.MsgHarvest,
                cosmos.farming.v1beta1.Tx.MsgHarvestResponse>(
                  this, METHODID_HARVEST)))
          .addMethod(
            getAdvanceEpochMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.farming.v1beta1.Tx.MsgAdvanceEpoch,
                cosmos.farming.v1beta1.Tx.MsgAdvanceEpochResponse>(
                  this, METHODID_ADVANCE_EPOCH)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the farming Msg service.
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
     * CreateFixedAmountPlan defines a method for creating a new fixed amount
     * farming plan
     * </pre>
     */
    public void createFixedAmountPlan(cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlan request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlanResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateFixedAmountPlanMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateRatioPlan defines a method for creating a new ratio farming plan
     * </pre>
     */
    public void createRatioPlan(cosmos.farming.v1beta1.Tx.MsgCreateRatioPlan request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.Tx.MsgCreateRatioPlanResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateRatioPlanMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Stake defines a method for staking coins into the farming plan
     * </pre>
     */
    public void stake(cosmos.farming.v1beta1.Tx.MsgStake request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.Tx.MsgStakeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStakeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Unstake defines a method for unstaking coins from the farming plan
     * </pre>
     */
    public void unstake(cosmos.farming.v1beta1.Tx.MsgUnstake request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.Tx.MsgUnstakeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnstakeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Harvest defines a method for claiming farming rewards
     * </pre>
     */
    public void harvest(cosmos.farming.v1beta1.Tx.MsgHarvest request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.Tx.MsgHarvestResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHarvestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AdvanceEpoch defines a method for advancing epoch by one, just for testing purpose
     * and shouldn't be used in real world
     * </pre>
     */
    public void advanceEpoch(cosmos.farming.v1beta1.Tx.MsgAdvanceEpoch request,
        io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.Tx.MsgAdvanceEpochResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAdvanceEpochMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the farming Msg service.
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
     * CreateFixedAmountPlan defines a method for creating a new fixed amount
     * farming plan
     * </pre>
     */
    public cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlanResponse createFixedAmountPlan(cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlan request) {
      return blockingUnaryCall(
          getChannel(), getCreateFixedAmountPlanMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateRatioPlan defines a method for creating a new ratio farming plan
     * </pre>
     */
    public cosmos.farming.v1beta1.Tx.MsgCreateRatioPlanResponse createRatioPlan(cosmos.farming.v1beta1.Tx.MsgCreateRatioPlan request) {
      return blockingUnaryCall(
          getChannel(), getCreateRatioPlanMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Stake defines a method for staking coins into the farming plan
     * </pre>
     */
    public cosmos.farming.v1beta1.Tx.MsgStakeResponse stake(cosmos.farming.v1beta1.Tx.MsgStake request) {
      return blockingUnaryCall(
          getChannel(), getStakeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Unstake defines a method for unstaking coins from the farming plan
     * </pre>
     */
    public cosmos.farming.v1beta1.Tx.MsgUnstakeResponse unstake(cosmos.farming.v1beta1.Tx.MsgUnstake request) {
      return blockingUnaryCall(
          getChannel(), getUnstakeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Harvest defines a method for claiming farming rewards
     * </pre>
     */
    public cosmos.farming.v1beta1.Tx.MsgHarvestResponse harvest(cosmos.farming.v1beta1.Tx.MsgHarvest request) {
      return blockingUnaryCall(
          getChannel(), getHarvestMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AdvanceEpoch defines a method for advancing epoch by one, just for testing purpose
     * and shouldn't be used in real world
     * </pre>
     */
    public cosmos.farming.v1beta1.Tx.MsgAdvanceEpochResponse advanceEpoch(cosmos.farming.v1beta1.Tx.MsgAdvanceEpoch request) {
      return blockingUnaryCall(
          getChannel(), getAdvanceEpochMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the farming Msg service.
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
     * CreateFixedAmountPlan defines a method for creating a new fixed amount
     * farming plan
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlanResponse> createFixedAmountPlan(
        cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlan request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateFixedAmountPlanMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateRatioPlan defines a method for creating a new ratio farming plan
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.farming.v1beta1.Tx.MsgCreateRatioPlanResponse> createRatioPlan(
        cosmos.farming.v1beta1.Tx.MsgCreateRatioPlan request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateRatioPlanMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Stake defines a method for staking coins into the farming plan
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.farming.v1beta1.Tx.MsgStakeResponse> stake(
        cosmos.farming.v1beta1.Tx.MsgStake request) {
      return futureUnaryCall(
          getChannel().newCall(getStakeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Unstake defines a method for unstaking coins from the farming plan
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.farming.v1beta1.Tx.MsgUnstakeResponse> unstake(
        cosmos.farming.v1beta1.Tx.MsgUnstake request) {
      return futureUnaryCall(
          getChannel().newCall(getUnstakeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Harvest defines a method for claiming farming rewards
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.farming.v1beta1.Tx.MsgHarvestResponse> harvest(
        cosmos.farming.v1beta1.Tx.MsgHarvest request) {
      return futureUnaryCall(
          getChannel().newCall(getHarvestMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AdvanceEpoch defines a method for advancing epoch by one, just for testing purpose
     * and shouldn't be used in real world
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.farming.v1beta1.Tx.MsgAdvanceEpochResponse> advanceEpoch(
        cosmos.farming.v1beta1.Tx.MsgAdvanceEpoch request) {
      return futureUnaryCall(
          getChannel().newCall(getAdvanceEpochMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_FIXED_AMOUNT_PLAN = 0;
  private static final int METHODID_CREATE_RATIO_PLAN = 1;
  private static final int METHODID_STAKE = 2;
  private static final int METHODID_UNSTAKE = 3;
  private static final int METHODID_HARVEST = 4;
  private static final int METHODID_ADVANCE_EPOCH = 5;

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
        case METHODID_CREATE_FIXED_AMOUNT_PLAN:
          serviceImpl.createFixedAmountPlan((cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlan) request,
              (io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.Tx.MsgCreateFixedAmountPlanResponse>) responseObserver);
          break;
        case METHODID_CREATE_RATIO_PLAN:
          serviceImpl.createRatioPlan((cosmos.farming.v1beta1.Tx.MsgCreateRatioPlan) request,
              (io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.Tx.MsgCreateRatioPlanResponse>) responseObserver);
          break;
        case METHODID_STAKE:
          serviceImpl.stake((cosmos.farming.v1beta1.Tx.MsgStake) request,
              (io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.Tx.MsgStakeResponse>) responseObserver);
          break;
        case METHODID_UNSTAKE:
          serviceImpl.unstake((cosmos.farming.v1beta1.Tx.MsgUnstake) request,
              (io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.Tx.MsgUnstakeResponse>) responseObserver);
          break;
        case METHODID_HARVEST:
          serviceImpl.harvest((cosmos.farming.v1beta1.Tx.MsgHarvest) request,
              (io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.Tx.MsgHarvestResponse>) responseObserver);
          break;
        case METHODID_ADVANCE_EPOCH:
          serviceImpl.advanceEpoch((cosmos.farming.v1beta1.Tx.MsgAdvanceEpoch) request,
              (io.grpc.stub.StreamObserver<cosmos.farming.v1beta1.Tx.MsgAdvanceEpochResponse>) responseObserver);
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
      return cosmos.farming.v1beta1.Tx.getDescriptor();
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
              .addMethod(getCreateFixedAmountPlanMethod())
              .addMethod(getCreateRatioPlanMethod())
              .addMethod(getStakeMethod())
              .addMethod(getUnstakeMethod())
              .addMethod(getHarvestMethod())
              .addMethod(getAdvanceEpochMethod())
              .build();
        }
      }
    }
    return result;
  }
}
