package confio.poe.v1beta1;

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
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: confio/poe/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "confio.poe.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<confio.poe.v1beta1.QueryOuterClass.QueryContractAddressRequest,
      confio.poe.v1beta1.QueryOuterClass.QueryContractAddressResponse> getContractAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ContractAddress",
      requestType = confio.poe.v1beta1.QueryOuterClass.QueryContractAddressRequest.class,
      responseType = confio.poe.v1beta1.QueryOuterClass.QueryContractAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<confio.poe.v1beta1.QueryOuterClass.QueryContractAddressRequest,
      confio.poe.v1beta1.QueryOuterClass.QueryContractAddressResponse> getContractAddressMethod() {
    io.grpc.MethodDescriptor<confio.poe.v1beta1.QueryOuterClass.QueryContractAddressRequest, confio.poe.v1beta1.QueryOuterClass.QueryContractAddressResponse> getContractAddressMethod;
    if ((getContractAddressMethod = QueryGrpc.getContractAddressMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getContractAddressMethod = QueryGrpc.getContractAddressMethod) == null) {
          QueryGrpc.getContractAddressMethod = getContractAddressMethod =
              io.grpc.MethodDescriptor.<confio.poe.v1beta1.QueryOuterClass.QueryContractAddressRequest, confio.poe.v1beta1.QueryOuterClass.QueryContractAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ContractAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  confio.poe.v1beta1.QueryOuterClass.QueryContractAddressRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  confio.poe.v1beta1.QueryOuterClass.QueryContractAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ContractAddress"))
              .build();
        }
      }
    }
    return getContractAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsRequest,
      cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> getValidatorsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Validators",
      requestType = cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsRequest.class,
      responseType = cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsRequest,
      cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> getValidatorsMethod() {
    io.grpc.MethodDescriptor<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsRequest, cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> getValidatorsMethod;
    if ((getValidatorsMethod = QueryGrpc.getValidatorsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValidatorsMethod = QueryGrpc.getValidatorsMethod) == null) {
          QueryGrpc.getValidatorsMethod = getValidatorsMethod =
              io.grpc.MethodDescriptor.<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsRequest, cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Validators"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Validators"))
              .build();
        }
      }
    }
    return getValidatorsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorRequest,
      cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorResponse> getValidatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Validator",
      requestType = cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorRequest.class,
      responseType = cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorRequest,
      cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorResponse> getValidatorMethod() {
    io.grpc.MethodDescriptor<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorRequest, cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorResponse> getValidatorMethod;
    if ((getValidatorMethod = QueryGrpc.getValidatorMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValidatorMethod = QueryGrpc.getValidatorMethod) == null) {
          QueryGrpc.getValidatorMethod = getValidatorMethod =
              io.grpc.MethodDescriptor.<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorRequest, cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Validator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Validator"))
              .build();
        }
      }
    }
    return getValidatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodRequest,
      confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodResponse> getUnbondingPeriodMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnbondingPeriod",
      requestType = confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodRequest.class,
      responseType = confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodRequest,
      confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodResponse> getUnbondingPeriodMethod() {
    io.grpc.MethodDescriptor<confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodRequest, confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodResponse> getUnbondingPeriodMethod;
    if ((getUnbondingPeriodMethod = QueryGrpc.getUnbondingPeriodMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUnbondingPeriodMethod = QueryGrpc.getUnbondingPeriodMethod) == null) {
          QueryGrpc.getUnbondingPeriodMethod = getUnbondingPeriodMethod =
              io.grpc.MethodDescriptor.<confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodRequest, confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnbondingPeriod"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UnbondingPeriod"))
              .build();
        }
      }
    }
    return getUnbondingPeriodMethod;
  }

  private static volatile io.grpc.MethodDescriptor<confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationRequest,
      confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationResponse> getValidatorDelegationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValidatorDelegation",
      requestType = confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationRequest.class,
      responseType = confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationRequest,
      confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationResponse> getValidatorDelegationMethod() {
    io.grpc.MethodDescriptor<confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationRequest, confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationResponse> getValidatorDelegationMethod;
    if ((getValidatorDelegationMethod = QueryGrpc.getValidatorDelegationMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValidatorDelegationMethod = QueryGrpc.getValidatorDelegationMethod) == null) {
          QueryGrpc.getValidatorDelegationMethod = getValidatorDelegationMethod =
              io.grpc.MethodDescriptor.<confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationRequest, confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValidatorDelegation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValidatorDelegation"))
              .build();
        }
      }
    }
    return getValidatorDelegationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsRequest,
      confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse> getValidatorUnbondingDelegationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValidatorUnbondingDelegations",
      requestType = confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsRequest.class,
      responseType = confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsRequest,
      confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse> getValidatorUnbondingDelegationsMethod() {
    io.grpc.MethodDescriptor<confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsRequest, confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse> getValidatorUnbondingDelegationsMethod;
    if ((getValidatorUnbondingDelegationsMethod = QueryGrpc.getValidatorUnbondingDelegationsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValidatorUnbondingDelegationsMethod = QueryGrpc.getValidatorUnbondingDelegationsMethod) == null) {
          QueryGrpc.getValidatorUnbondingDelegationsMethod = getValidatorUnbondingDelegationsMethod =
              io.grpc.MethodDescriptor.<confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsRequest, confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValidatorUnbondingDelegations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValidatorUnbondingDelegations"))
              .build();
        }
      }
    }
    return getValidatorUnbondingDelegationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoRequest,
      cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoResponse> getHistoricalInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HistoricalInfo",
      requestType = cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoRequest.class,
      responseType = cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoRequest,
      cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoResponse> getHistoricalInfoMethod() {
    io.grpc.MethodDescriptor<cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoRequest, cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoResponse> getHistoricalInfoMethod;
    if ((getHistoricalInfoMethod = QueryGrpc.getHistoricalInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getHistoricalInfoMethod = QueryGrpc.getHistoricalInfoMethod) == null) {
          QueryGrpc.getHistoricalInfoMethod = getHistoricalInfoMethod =
              io.grpc.MethodDescriptor.<cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoRequest, cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HistoricalInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("HistoricalInfo"))
              .build();
        }
      }
    }
    return getHistoricalInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardRequest,
      confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardResponse> getValidatorOutstandingRewardMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValidatorOutstandingReward",
      requestType = confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardRequest.class,
      responseType = confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardRequest,
      confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardResponse> getValidatorOutstandingRewardMethod() {
    io.grpc.MethodDescriptor<confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardRequest, confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardResponse> getValidatorOutstandingRewardMethod;
    if ((getValidatorOutstandingRewardMethod = QueryGrpc.getValidatorOutstandingRewardMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValidatorOutstandingRewardMethod = QueryGrpc.getValidatorOutstandingRewardMethod) == null) {
          QueryGrpc.getValidatorOutstandingRewardMethod = getValidatorOutstandingRewardMethod =
              io.grpc.MethodDescriptor.<confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardRequest, confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValidatorOutstandingReward"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValidatorOutstandingReward"))
              .build();
        }
      }
    }
    return getValidatorOutstandingRewardMethod;
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
   * Query defines the gRPC querier service.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * ContractAddress queries the address for one of the PoE contracts
     * </pre>
     */
    public void contractAddress(confio.poe.v1beta1.QueryOuterClass.QueryContractAddressRequest request,
        io.grpc.stub.StreamObserver<confio.poe.v1beta1.QueryOuterClass.QueryContractAddressResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getContractAddressMethod(), responseObserver);
    }

    /**
     * <pre>
     * Validators queries all validators that match the given status.
     * </pre>
     */
    public void validators(cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsRequest request,
        io.grpc.stub.StreamObserver<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getValidatorsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Validator queries validator info for given validator address.
     * </pre>
     */
    public void validator(cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorRequest request,
        io.grpc.stub.StreamObserver<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getValidatorMethod(), responseObserver);
    }

    /**
     * <pre>
     * Validator queries validator info for given validator address.
     * </pre>
     */
    public void unbondingPeriod(confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodRequest request,
        io.grpc.stub.StreamObserver<confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnbondingPeriodMethod(), responseObserver);
    }

    /**
     * <pre>
     * ValidatorDelegation queries self delegated amount for given validator.
     * </pre>
     */
    public void validatorDelegation(confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationRequest request,
        io.grpc.stub.StreamObserver<confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getValidatorDelegationMethod(), responseObserver);
    }

    /**
     * <pre>
     * ValidatorUnbondingDelegations queries unbonding delegations of a validator.
     * </pre>
     */
    public void validatorUnbondingDelegations(confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsRequest request,
        io.grpc.stub.StreamObserver<confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getValidatorUnbondingDelegationsMethod(), responseObserver);
    }

    /**
     * <pre>
     * HistoricalInfo queries the historical info for given height.
     * </pre>
     */
    public void historicalInfo(cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoRequest request,
        io.grpc.stub.StreamObserver<cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHistoricalInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * ValidatorOutstandingRewards queries rewards of a validator address.
     * </pre>
     */
    public void validatorOutstandingReward(confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardRequest request,
        io.grpc.stub.StreamObserver<confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getValidatorOutstandingRewardMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getContractAddressMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                confio.poe.v1beta1.QueryOuterClass.QueryContractAddressRequest,
                confio.poe.v1beta1.QueryOuterClass.QueryContractAddressResponse>(
                  this, METHODID_CONTRACT_ADDRESS)))
          .addMethod(
            getValidatorsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsRequest,
                cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse>(
                  this, METHODID_VALIDATORS)))
          .addMethod(
            getValidatorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorRequest,
                cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorResponse>(
                  this, METHODID_VALIDATOR)))
          .addMethod(
            getUnbondingPeriodMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodRequest,
                confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodResponse>(
                  this, METHODID_UNBONDING_PERIOD)))
          .addMethod(
            getValidatorDelegationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationRequest,
                confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationResponse>(
                  this, METHODID_VALIDATOR_DELEGATION)))
          .addMethod(
            getValidatorUnbondingDelegationsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsRequest,
                confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse>(
                  this, METHODID_VALIDATOR_UNBONDING_DELEGATIONS)))
          .addMethod(
            getHistoricalInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoRequest,
                cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoResponse>(
                  this, METHODID_HISTORICAL_INFO)))
          .addMethod(
            getValidatorOutstandingRewardMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardRequest,
                confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardResponse>(
                  this, METHODID_VALIDATOR_OUTSTANDING_REWARD)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
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
     * ContractAddress queries the address for one of the PoE contracts
     * </pre>
     */
    public void contractAddress(confio.poe.v1beta1.QueryOuterClass.QueryContractAddressRequest request,
        io.grpc.stub.StreamObserver<confio.poe.v1beta1.QueryOuterClass.QueryContractAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getContractAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Validators queries all validators that match the given status.
     * </pre>
     */
    public void validators(cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsRequest request,
        io.grpc.stub.StreamObserver<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getValidatorsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Validator queries validator info for given validator address.
     * </pre>
     */
    public void validator(cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorRequest request,
        io.grpc.stub.StreamObserver<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getValidatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Validator queries validator info for given validator address.
     * </pre>
     */
    public void unbondingPeriod(confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodRequest request,
        io.grpc.stub.StreamObserver<confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnbondingPeriodMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ValidatorDelegation queries self delegated amount for given validator.
     * </pre>
     */
    public void validatorDelegation(confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationRequest request,
        io.grpc.stub.StreamObserver<confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getValidatorDelegationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ValidatorUnbondingDelegations queries unbonding delegations of a validator.
     * </pre>
     */
    public void validatorUnbondingDelegations(confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsRequest request,
        io.grpc.stub.StreamObserver<confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getValidatorUnbondingDelegationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * HistoricalInfo queries the historical info for given height.
     * </pre>
     */
    public void historicalInfo(cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoRequest request,
        io.grpc.stub.StreamObserver<cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHistoricalInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ValidatorOutstandingRewards queries rewards of a validator address.
     * </pre>
     */
    public void validatorOutstandingReward(confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardRequest request,
        io.grpc.stub.StreamObserver<confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getValidatorOutstandingRewardMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
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
     * ContractAddress queries the address for one of the PoE contracts
     * </pre>
     */
    public confio.poe.v1beta1.QueryOuterClass.QueryContractAddressResponse contractAddress(confio.poe.v1beta1.QueryOuterClass.QueryContractAddressRequest request) {
      return blockingUnaryCall(
          getChannel(), getContractAddressMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Validators queries all validators that match the given status.
     * </pre>
     */
    public cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse validators(cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsRequest request) {
      return blockingUnaryCall(
          getChannel(), getValidatorsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Validator queries validator info for given validator address.
     * </pre>
     */
    public cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorResponse validator(cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorRequest request) {
      return blockingUnaryCall(
          getChannel(), getValidatorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Validator queries validator info for given validator address.
     * </pre>
     */
    public confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodResponse unbondingPeriod(confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodRequest request) {
      return blockingUnaryCall(
          getChannel(), getUnbondingPeriodMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ValidatorDelegation queries self delegated amount for given validator.
     * </pre>
     */
    public confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationResponse validatorDelegation(confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationRequest request) {
      return blockingUnaryCall(
          getChannel(), getValidatorDelegationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ValidatorUnbondingDelegations queries unbonding delegations of a validator.
     * </pre>
     */
    public confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse validatorUnbondingDelegations(confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsRequest request) {
      return blockingUnaryCall(
          getChannel(), getValidatorUnbondingDelegationsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * HistoricalInfo queries the historical info for given height.
     * </pre>
     */
    public cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoResponse historicalInfo(cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoRequest request) {
      return blockingUnaryCall(
          getChannel(), getHistoricalInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ValidatorOutstandingRewards queries rewards of a validator address.
     * </pre>
     */
    public confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardResponse validatorOutstandingReward(confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardRequest request) {
      return blockingUnaryCall(
          getChannel(), getValidatorOutstandingRewardMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the gRPC querier service.
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
     * ContractAddress queries the address for one of the PoE contracts
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<confio.poe.v1beta1.QueryOuterClass.QueryContractAddressResponse> contractAddress(
        confio.poe.v1beta1.QueryOuterClass.QueryContractAddressRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getContractAddressMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Validators queries all validators that match the given status.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> validators(
        cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getValidatorsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Validator queries validator info for given validator address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorResponse> validator(
        cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getValidatorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Validator queries validator info for given validator address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodResponse> unbondingPeriod(
        confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUnbondingPeriodMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ValidatorDelegation queries self delegated amount for given validator.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationResponse> validatorDelegation(
        confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getValidatorDelegationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ValidatorUnbondingDelegations queries unbonding delegations of a validator.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse> validatorUnbondingDelegations(
        confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getValidatorUnbondingDelegationsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * HistoricalInfo queries the historical info for given height.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoResponse> historicalInfo(
        cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getHistoricalInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ValidatorOutstandingRewards queries rewards of a validator address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardResponse> validatorOutstandingReward(
        confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getValidatorOutstandingRewardMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CONTRACT_ADDRESS = 0;
  private static final int METHODID_VALIDATORS = 1;
  private static final int METHODID_VALIDATOR = 2;
  private static final int METHODID_UNBONDING_PERIOD = 3;
  private static final int METHODID_VALIDATOR_DELEGATION = 4;
  private static final int METHODID_VALIDATOR_UNBONDING_DELEGATIONS = 5;
  private static final int METHODID_HISTORICAL_INFO = 6;
  private static final int METHODID_VALIDATOR_OUTSTANDING_REWARD = 7;

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
        case METHODID_CONTRACT_ADDRESS:
          serviceImpl.contractAddress((confio.poe.v1beta1.QueryOuterClass.QueryContractAddressRequest) request,
              (io.grpc.stub.StreamObserver<confio.poe.v1beta1.QueryOuterClass.QueryContractAddressResponse>) responseObserver);
          break;
        case METHODID_VALIDATORS:
          serviceImpl.validators((cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse>) responseObserver);
          break;
        case METHODID_VALIDATOR:
          serviceImpl.validator((cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorResponse>) responseObserver);
          break;
        case METHODID_UNBONDING_PERIOD:
          serviceImpl.unbondingPeriod((confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodRequest) request,
              (io.grpc.stub.StreamObserver<confio.poe.v1beta1.QueryOuterClass.QueryUnbondingPeriodResponse>) responseObserver);
          break;
        case METHODID_VALIDATOR_DELEGATION:
          serviceImpl.validatorDelegation((confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationRequest) request,
              (io.grpc.stub.StreamObserver<confio.poe.v1beta1.QueryOuterClass.QueryValidatorDelegationResponse>) responseObserver);
          break;
        case METHODID_VALIDATOR_UNBONDING_DELEGATIONS:
          serviceImpl.validatorUnbondingDelegations((confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsRequest) request,
              (io.grpc.stub.StreamObserver<confio.poe.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse>) responseObserver);
          break;
        case METHODID_HISTORICAL_INFO:
          serviceImpl.historicalInfo((cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoResponse>) responseObserver);
          break;
        case METHODID_VALIDATOR_OUTSTANDING_REWARD:
          serviceImpl.validatorOutstandingReward((confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardRequest) request,
              (io.grpc.stub.StreamObserver<confio.poe.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardResponse>) responseObserver);
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
      return confio.poe.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getContractAddressMethod())
              .addMethod(getValidatorsMethod())
              .addMethod(getValidatorMethod())
              .addMethod(getUnbondingPeriodMethod())
              .addMethod(getValidatorDelegationMethod())
              .addMethod(getValidatorUnbondingDelegationsMethod())
              .addMethod(getHistoricalInfoMethod())
              .addMethod(getValidatorOutstandingRewardMethod())
              .build();
        }
      }
    }
    return result;
  }
}
