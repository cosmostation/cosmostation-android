package ixo.payments;

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
 * Msg defines the payments Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: ixo/payments/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "payments.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ixo.payments.Tx.MsgSetPaymentContractAuthorisation,
      ixo.payments.Tx.MsgSetPaymentContractAuthorisationResponse> getSetPaymentContractAuthorisationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetPaymentContractAuthorisation",
      requestType = ixo.payments.Tx.MsgSetPaymentContractAuthorisation.class,
      responseType = ixo.payments.Tx.MsgSetPaymentContractAuthorisationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ixo.payments.Tx.MsgSetPaymentContractAuthorisation,
      ixo.payments.Tx.MsgSetPaymentContractAuthorisationResponse> getSetPaymentContractAuthorisationMethod() {
    io.grpc.MethodDescriptor<ixo.payments.Tx.MsgSetPaymentContractAuthorisation, ixo.payments.Tx.MsgSetPaymentContractAuthorisationResponse> getSetPaymentContractAuthorisationMethod;
    if ((getSetPaymentContractAuthorisationMethod = MsgGrpc.getSetPaymentContractAuthorisationMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetPaymentContractAuthorisationMethod = MsgGrpc.getSetPaymentContractAuthorisationMethod) == null) {
          MsgGrpc.getSetPaymentContractAuthorisationMethod = getSetPaymentContractAuthorisationMethod =
              io.grpc.MethodDescriptor.<ixo.payments.Tx.MsgSetPaymentContractAuthorisation, ixo.payments.Tx.MsgSetPaymentContractAuthorisationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetPaymentContractAuthorisation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.payments.Tx.MsgSetPaymentContractAuthorisation.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.payments.Tx.MsgSetPaymentContractAuthorisationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetPaymentContractAuthorisation"))
              .build();
        }
      }
    }
    return getSetPaymentContractAuthorisationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ixo.payments.Tx.MsgCreatePaymentTemplate,
      ixo.payments.Tx.MsgCreatePaymentTemplateResponse> getCreatePaymentTemplateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreatePaymentTemplate",
      requestType = ixo.payments.Tx.MsgCreatePaymentTemplate.class,
      responseType = ixo.payments.Tx.MsgCreatePaymentTemplateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ixo.payments.Tx.MsgCreatePaymentTemplate,
      ixo.payments.Tx.MsgCreatePaymentTemplateResponse> getCreatePaymentTemplateMethod() {
    io.grpc.MethodDescriptor<ixo.payments.Tx.MsgCreatePaymentTemplate, ixo.payments.Tx.MsgCreatePaymentTemplateResponse> getCreatePaymentTemplateMethod;
    if ((getCreatePaymentTemplateMethod = MsgGrpc.getCreatePaymentTemplateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreatePaymentTemplateMethod = MsgGrpc.getCreatePaymentTemplateMethod) == null) {
          MsgGrpc.getCreatePaymentTemplateMethod = getCreatePaymentTemplateMethod =
              io.grpc.MethodDescriptor.<ixo.payments.Tx.MsgCreatePaymentTemplate, ixo.payments.Tx.MsgCreatePaymentTemplateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreatePaymentTemplate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.payments.Tx.MsgCreatePaymentTemplate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.payments.Tx.MsgCreatePaymentTemplateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreatePaymentTemplate"))
              .build();
        }
      }
    }
    return getCreatePaymentTemplateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ixo.payments.Tx.MsgCreatePaymentContract,
      ixo.payments.Tx.MsgCreatePaymentContractResponse> getCreatePaymentContractMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreatePaymentContract",
      requestType = ixo.payments.Tx.MsgCreatePaymentContract.class,
      responseType = ixo.payments.Tx.MsgCreatePaymentContractResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ixo.payments.Tx.MsgCreatePaymentContract,
      ixo.payments.Tx.MsgCreatePaymentContractResponse> getCreatePaymentContractMethod() {
    io.grpc.MethodDescriptor<ixo.payments.Tx.MsgCreatePaymentContract, ixo.payments.Tx.MsgCreatePaymentContractResponse> getCreatePaymentContractMethod;
    if ((getCreatePaymentContractMethod = MsgGrpc.getCreatePaymentContractMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreatePaymentContractMethod = MsgGrpc.getCreatePaymentContractMethod) == null) {
          MsgGrpc.getCreatePaymentContractMethod = getCreatePaymentContractMethod =
              io.grpc.MethodDescriptor.<ixo.payments.Tx.MsgCreatePaymentContract, ixo.payments.Tx.MsgCreatePaymentContractResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreatePaymentContract"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.payments.Tx.MsgCreatePaymentContract.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.payments.Tx.MsgCreatePaymentContractResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreatePaymentContract"))
              .build();
        }
      }
    }
    return getCreatePaymentContractMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ixo.payments.Tx.MsgCreateSubscription,
      ixo.payments.Tx.MsgCreateSubscriptionResponse> getCreateSubscriptionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateSubscription",
      requestType = ixo.payments.Tx.MsgCreateSubscription.class,
      responseType = ixo.payments.Tx.MsgCreateSubscriptionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ixo.payments.Tx.MsgCreateSubscription,
      ixo.payments.Tx.MsgCreateSubscriptionResponse> getCreateSubscriptionMethod() {
    io.grpc.MethodDescriptor<ixo.payments.Tx.MsgCreateSubscription, ixo.payments.Tx.MsgCreateSubscriptionResponse> getCreateSubscriptionMethod;
    if ((getCreateSubscriptionMethod = MsgGrpc.getCreateSubscriptionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateSubscriptionMethod = MsgGrpc.getCreateSubscriptionMethod) == null) {
          MsgGrpc.getCreateSubscriptionMethod = getCreateSubscriptionMethod =
              io.grpc.MethodDescriptor.<ixo.payments.Tx.MsgCreateSubscription, ixo.payments.Tx.MsgCreateSubscriptionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateSubscription"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.payments.Tx.MsgCreateSubscription.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.payments.Tx.MsgCreateSubscriptionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateSubscription"))
              .build();
        }
      }
    }
    return getCreateSubscriptionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ixo.payments.Tx.MsgGrantDiscount,
      ixo.payments.Tx.MsgGrantDiscountResponse> getGrantDiscountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GrantDiscount",
      requestType = ixo.payments.Tx.MsgGrantDiscount.class,
      responseType = ixo.payments.Tx.MsgGrantDiscountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ixo.payments.Tx.MsgGrantDiscount,
      ixo.payments.Tx.MsgGrantDiscountResponse> getGrantDiscountMethod() {
    io.grpc.MethodDescriptor<ixo.payments.Tx.MsgGrantDiscount, ixo.payments.Tx.MsgGrantDiscountResponse> getGrantDiscountMethod;
    if ((getGrantDiscountMethod = MsgGrpc.getGrantDiscountMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getGrantDiscountMethod = MsgGrpc.getGrantDiscountMethod) == null) {
          MsgGrpc.getGrantDiscountMethod = getGrantDiscountMethod =
              io.grpc.MethodDescriptor.<ixo.payments.Tx.MsgGrantDiscount, ixo.payments.Tx.MsgGrantDiscountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GrantDiscount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.payments.Tx.MsgGrantDiscount.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.payments.Tx.MsgGrantDiscountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("GrantDiscount"))
              .build();
        }
      }
    }
    return getGrantDiscountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ixo.payments.Tx.MsgRevokeDiscount,
      ixo.payments.Tx.MsgRevokeDiscountResponse> getRevokeDiscountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RevokeDiscount",
      requestType = ixo.payments.Tx.MsgRevokeDiscount.class,
      responseType = ixo.payments.Tx.MsgRevokeDiscountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ixo.payments.Tx.MsgRevokeDiscount,
      ixo.payments.Tx.MsgRevokeDiscountResponse> getRevokeDiscountMethod() {
    io.grpc.MethodDescriptor<ixo.payments.Tx.MsgRevokeDiscount, ixo.payments.Tx.MsgRevokeDiscountResponse> getRevokeDiscountMethod;
    if ((getRevokeDiscountMethod = MsgGrpc.getRevokeDiscountMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRevokeDiscountMethod = MsgGrpc.getRevokeDiscountMethod) == null) {
          MsgGrpc.getRevokeDiscountMethod = getRevokeDiscountMethod =
              io.grpc.MethodDescriptor.<ixo.payments.Tx.MsgRevokeDiscount, ixo.payments.Tx.MsgRevokeDiscountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RevokeDiscount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.payments.Tx.MsgRevokeDiscount.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.payments.Tx.MsgRevokeDiscountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RevokeDiscount"))
              .build();
        }
      }
    }
    return getRevokeDiscountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ixo.payments.Tx.MsgEffectPayment,
      ixo.payments.Tx.MsgEffectPaymentResponse> getEffectPaymentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EffectPayment",
      requestType = ixo.payments.Tx.MsgEffectPayment.class,
      responseType = ixo.payments.Tx.MsgEffectPaymentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ixo.payments.Tx.MsgEffectPayment,
      ixo.payments.Tx.MsgEffectPaymentResponse> getEffectPaymentMethod() {
    io.grpc.MethodDescriptor<ixo.payments.Tx.MsgEffectPayment, ixo.payments.Tx.MsgEffectPaymentResponse> getEffectPaymentMethod;
    if ((getEffectPaymentMethod = MsgGrpc.getEffectPaymentMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getEffectPaymentMethod = MsgGrpc.getEffectPaymentMethod) == null) {
          MsgGrpc.getEffectPaymentMethod = getEffectPaymentMethod =
              io.grpc.MethodDescriptor.<ixo.payments.Tx.MsgEffectPayment, ixo.payments.Tx.MsgEffectPaymentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EffectPayment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.payments.Tx.MsgEffectPayment.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ixo.payments.Tx.MsgEffectPaymentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("EffectPayment"))
              .build();
        }
      }
    }
    return getEffectPaymentMethod;
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
   * Msg defines the payments Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * SetPaymentContractAuthorisation defines a method for authorising or deauthorising a payment contract.
     * </pre>
     */
    public void setPaymentContractAuthorisation(ixo.payments.Tx.MsgSetPaymentContractAuthorisation request,
        io.grpc.stub.StreamObserver<ixo.payments.Tx.MsgSetPaymentContractAuthorisationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetPaymentContractAuthorisationMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreatePaymentTemplate defines a method for creating a payment template.
     * </pre>
     */
    public void createPaymentTemplate(ixo.payments.Tx.MsgCreatePaymentTemplate request,
        io.grpc.stub.StreamObserver<ixo.payments.Tx.MsgCreatePaymentTemplateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreatePaymentTemplateMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreatePaymentContract defines a method for creating a payment contract.
     * </pre>
     */
    public void createPaymentContract(ixo.payments.Tx.MsgCreatePaymentContract request,
        io.grpc.stub.StreamObserver<ixo.payments.Tx.MsgCreatePaymentContractResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreatePaymentContractMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateSubscription defines a method for creating a subscription.
     * </pre>
     */
    public void createSubscription(ixo.payments.Tx.MsgCreateSubscription request,
        io.grpc.stub.StreamObserver<ixo.payments.Tx.MsgCreateSubscriptionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateSubscriptionMethod(), responseObserver);
    }

    /**
     * <pre>
     * GrantDiscount defines a method for granting a discount to a payer on a specific payment contract.
     * </pre>
     */
    public void grantDiscount(ixo.payments.Tx.MsgGrantDiscount request,
        io.grpc.stub.StreamObserver<ixo.payments.Tx.MsgGrantDiscountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGrantDiscountMethod(), responseObserver);
    }

    /**
     * <pre>
     * RevokeDiscount defines a method for revoking a discount previously granted to a payer.
     * </pre>
     */
    public void revokeDiscount(ixo.payments.Tx.MsgRevokeDiscount request,
        io.grpc.stub.StreamObserver<ixo.payments.Tx.MsgRevokeDiscountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRevokeDiscountMethod(), responseObserver);
    }

    /**
     * <pre>
     * EffectPayment defines a method for putting a specific payment contract into effect.
     * </pre>
     */
    public void effectPayment(ixo.payments.Tx.MsgEffectPayment request,
        io.grpc.stub.StreamObserver<ixo.payments.Tx.MsgEffectPaymentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEffectPaymentMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSetPaymentContractAuthorisationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ixo.payments.Tx.MsgSetPaymentContractAuthorisation,
                ixo.payments.Tx.MsgSetPaymentContractAuthorisationResponse>(
                  this, METHODID_SET_PAYMENT_CONTRACT_AUTHORISATION)))
          .addMethod(
            getCreatePaymentTemplateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ixo.payments.Tx.MsgCreatePaymentTemplate,
                ixo.payments.Tx.MsgCreatePaymentTemplateResponse>(
                  this, METHODID_CREATE_PAYMENT_TEMPLATE)))
          .addMethod(
            getCreatePaymentContractMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ixo.payments.Tx.MsgCreatePaymentContract,
                ixo.payments.Tx.MsgCreatePaymentContractResponse>(
                  this, METHODID_CREATE_PAYMENT_CONTRACT)))
          .addMethod(
            getCreateSubscriptionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ixo.payments.Tx.MsgCreateSubscription,
                ixo.payments.Tx.MsgCreateSubscriptionResponse>(
                  this, METHODID_CREATE_SUBSCRIPTION)))
          .addMethod(
            getGrantDiscountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ixo.payments.Tx.MsgGrantDiscount,
                ixo.payments.Tx.MsgGrantDiscountResponse>(
                  this, METHODID_GRANT_DISCOUNT)))
          .addMethod(
            getRevokeDiscountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ixo.payments.Tx.MsgRevokeDiscount,
                ixo.payments.Tx.MsgRevokeDiscountResponse>(
                  this, METHODID_REVOKE_DISCOUNT)))
          .addMethod(
            getEffectPaymentMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ixo.payments.Tx.MsgEffectPayment,
                ixo.payments.Tx.MsgEffectPaymentResponse>(
                  this, METHODID_EFFECT_PAYMENT)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the payments Msg service.
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
     * SetPaymentContractAuthorisation defines a method for authorising or deauthorising a payment contract.
     * </pre>
     */
    public void setPaymentContractAuthorisation(ixo.payments.Tx.MsgSetPaymentContractAuthorisation request,
        io.grpc.stub.StreamObserver<ixo.payments.Tx.MsgSetPaymentContractAuthorisationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetPaymentContractAuthorisationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreatePaymentTemplate defines a method for creating a payment template.
     * </pre>
     */
    public void createPaymentTemplate(ixo.payments.Tx.MsgCreatePaymentTemplate request,
        io.grpc.stub.StreamObserver<ixo.payments.Tx.MsgCreatePaymentTemplateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreatePaymentTemplateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreatePaymentContract defines a method for creating a payment contract.
     * </pre>
     */
    public void createPaymentContract(ixo.payments.Tx.MsgCreatePaymentContract request,
        io.grpc.stub.StreamObserver<ixo.payments.Tx.MsgCreatePaymentContractResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreatePaymentContractMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateSubscription defines a method for creating a subscription.
     * </pre>
     */
    public void createSubscription(ixo.payments.Tx.MsgCreateSubscription request,
        io.grpc.stub.StreamObserver<ixo.payments.Tx.MsgCreateSubscriptionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateSubscriptionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GrantDiscount defines a method for granting a discount to a payer on a specific payment contract.
     * </pre>
     */
    public void grantDiscount(ixo.payments.Tx.MsgGrantDiscount request,
        io.grpc.stub.StreamObserver<ixo.payments.Tx.MsgGrantDiscountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGrantDiscountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RevokeDiscount defines a method for revoking a discount previously granted to a payer.
     * </pre>
     */
    public void revokeDiscount(ixo.payments.Tx.MsgRevokeDiscount request,
        io.grpc.stub.StreamObserver<ixo.payments.Tx.MsgRevokeDiscountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRevokeDiscountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EffectPayment defines a method for putting a specific payment contract into effect.
     * </pre>
     */
    public void effectPayment(ixo.payments.Tx.MsgEffectPayment request,
        io.grpc.stub.StreamObserver<ixo.payments.Tx.MsgEffectPaymentResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEffectPaymentMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the payments Msg service.
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
     * SetPaymentContractAuthorisation defines a method for authorising or deauthorising a payment contract.
     * </pre>
     */
    public ixo.payments.Tx.MsgSetPaymentContractAuthorisationResponse setPaymentContractAuthorisation(ixo.payments.Tx.MsgSetPaymentContractAuthorisation request) {
      return blockingUnaryCall(
          getChannel(), getSetPaymentContractAuthorisationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreatePaymentTemplate defines a method for creating a payment template.
     * </pre>
     */
    public ixo.payments.Tx.MsgCreatePaymentTemplateResponse createPaymentTemplate(ixo.payments.Tx.MsgCreatePaymentTemplate request) {
      return blockingUnaryCall(
          getChannel(), getCreatePaymentTemplateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreatePaymentContract defines a method for creating a payment contract.
     * </pre>
     */
    public ixo.payments.Tx.MsgCreatePaymentContractResponse createPaymentContract(ixo.payments.Tx.MsgCreatePaymentContract request) {
      return blockingUnaryCall(
          getChannel(), getCreatePaymentContractMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateSubscription defines a method for creating a subscription.
     * </pre>
     */
    public ixo.payments.Tx.MsgCreateSubscriptionResponse createSubscription(ixo.payments.Tx.MsgCreateSubscription request) {
      return blockingUnaryCall(
          getChannel(), getCreateSubscriptionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GrantDiscount defines a method for granting a discount to a payer on a specific payment contract.
     * </pre>
     */
    public ixo.payments.Tx.MsgGrantDiscountResponse grantDiscount(ixo.payments.Tx.MsgGrantDiscount request) {
      return blockingUnaryCall(
          getChannel(), getGrantDiscountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RevokeDiscount defines a method for revoking a discount previously granted to a payer.
     * </pre>
     */
    public ixo.payments.Tx.MsgRevokeDiscountResponse revokeDiscount(ixo.payments.Tx.MsgRevokeDiscount request) {
      return blockingUnaryCall(
          getChannel(), getRevokeDiscountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EffectPayment defines a method for putting a specific payment contract into effect.
     * </pre>
     */
    public ixo.payments.Tx.MsgEffectPaymentResponse effectPayment(ixo.payments.Tx.MsgEffectPayment request) {
      return blockingUnaryCall(
          getChannel(), getEffectPaymentMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the payments Msg service.
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
     * SetPaymentContractAuthorisation defines a method for authorising or deauthorising a payment contract.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ixo.payments.Tx.MsgSetPaymentContractAuthorisationResponse> setPaymentContractAuthorisation(
        ixo.payments.Tx.MsgSetPaymentContractAuthorisation request) {
      return futureUnaryCall(
          getChannel().newCall(getSetPaymentContractAuthorisationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreatePaymentTemplate defines a method for creating a payment template.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ixo.payments.Tx.MsgCreatePaymentTemplateResponse> createPaymentTemplate(
        ixo.payments.Tx.MsgCreatePaymentTemplate request) {
      return futureUnaryCall(
          getChannel().newCall(getCreatePaymentTemplateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreatePaymentContract defines a method for creating a payment contract.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ixo.payments.Tx.MsgCreatePaymentContractResponse> createPaymentContract(
        ixo.payments.Tx.MsgCreatePaymentContract request) {
      return futureUnaryCall(
          getChannel().newCall(getCreatePaymentContractMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateSubscription defines a method for creating a subscription.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ixo.payments.Tx.MsgCreateSubscriptionResponse> createSubscription(
        ixo.payments.Tx.MsgCreateSubscription request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateSubscriptionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GrantDiscount defines a method for granting a discount to a payer on a specific payment contract.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ixo.payments.Tx.MsgGrantDiscountResponse> grantDiscount(
        ixo.payments.Tx.MsgGrantDiscount request) {
      return futureUnaryCall(
          getChannel().newCall(getGrantDiscountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RevokeDiscount defines a method for revoking a discount previously granted to a payer.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ixo.payments.Tx.MsgRevokeDiscountResponse> revokeDiscount(
        ixo.payments.Tx.MsgRevokeDiscount request) {
      return futureUnaryCall(
          getChannel().newCall(getRevokeDiscountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EffectPayment defines a method for putting a specific payment contract into effect.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ixo.payments.Tx.MsgEffectPaymentResponse> effectPayment(
        ixo.payments.Tx.MsgEffectPayment request) {
      return futureUnaryCall(
          getChannel().newCall(getEffectPaymentMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET_PAYMENT_CONTRACT_AUTHORISATION = 0;
  private static final int METHODID_CREATE_PAYMENT_TEMPLATE = 1;
  private static final int METHODID_CREATE_PAYMENT_CONTRACT = 2;
  private static final int METHODID_CREATE_SUBSCRIPTION = 3;
  private static final int METHODID_GRANT_DISCOUNT = 4;
  private static final int METHODID_REVOKE_DISCOUNT = 5;
  private static final int METHODID_EFFECT_PAYMENT = 6;

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
        case METHODID_SET_PAYMENT_CONTRACT_AUTHORISATION:
          serviceImpl.setPaymentContractAuthorisation((ixo.payments.Tx.MsgSetPaymentContractAuthorisation) request,
              (io.grpc.stub.StreamObserver<ixo.payments.Tx.MsgSetPaymentContractAuthorisationResponse>) responseObserver);
          break;
        case METHODID_CREATE_PAYMENT_TEMPLATE:
          serviceImpl.createPaymentTemplate((ixo.payments.Tx.MsgCreatePaymentTemplate) request,
              (io.grpc.stub.StreamObserver<ixo.payments.Tx.MsgCreatePaymentTemplateResponse>) responseObserver);
          break;
        case METHODID_CREATE_PAYMENT_CONTRACT:
          serviceImpl.createPaymentContract((ixo.payments.Tx.MsgCreatePaymentContract) request,
              (io.grpc.stub.StreamObserver<ixo.payments.Tx.MsgCreatePaymentContractResponse>) responseObserver);
          break;
        case METHODID_CREATE_SUBSCRIPTION:
          serviceImpl.createSubscription((ixo.payments.Tx.MsgCreateSubscription) request,
              (io.grpc.stub.StreamObserver<ixo.payments.Tx.MsgCreateSubscriptionResponse>) responseObserver);
          break;
        case METHODID_GRANT_DISCOUNT:
          serviceImpl.grantDiscount((ixo.payments.Tx.MsgGrantDiscount) request,
              (io.grpc.stub.StreamObserver<ixo.payments.Tx.MsgGrantDiscountResponse>) responseObserver);
          break;
        case METHODID_REVOKE_DISCOUNT:
          serviceImpl.revokeDiscount((ixo.payments.Tx.MsgRevokeDiscount) request,
              (io.grpc.stub.StreamObserver<ixo.payments.Tx.MsgRevokeDiscountResponse>) responseObserver);
          break;
        case METHODID_EFFECT_PAYMENT:
          serviceImpl.effectPayment((ixo.payments.Tx.MsgEffectPayment) request,
              (io.grpc.stub.StreamObserver<ixo.payments.Tx.MsgEffectPaymentResponse>) responseObserver);
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
      return ixo.payments.Tx.getDescriptor();
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
              .addMethod(getSetPaymentContractAuthorisationMethod())
              .addMethod(getCreatePaymentTemplateMethod())
              .addMethod(getCreatePaymentContractMethod())
              .addMethod(getCreateSubscriptionMethod())
              .addMethod(getGrantDiscountMethod())
              .addMethod(getRevokeDiscountMethod())
              .addMethod(getEffectPaymentMethod())
              .build();
        }
      }
    }
    return result;
  }
}
