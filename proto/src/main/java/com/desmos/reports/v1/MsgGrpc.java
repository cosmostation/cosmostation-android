package com.desmos.reports.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the reports Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: desmos/reports/v1/msgs.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "desmos.reports.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.desmos.reports.v1.MsgsProto.MsgCreateReport,
      com.desmos.reports.v1.MsgsProto.MsgCreateReportResponse> getCreateReportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateReport",
      requestType = com.desmos.reports.v1.MsgsProto.MsgCreateReport.class,
      responseType = com.desmos.reports.v1.MsgsProto.MsgCreateReportResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.reports.v1.MsgsProto.MsgCreateReport,
      com.desmos.reports.v1.MsgsProto.MsgCreateReportResponse> getCreateReportMethod() {
    io.grpc.MethodDescriptor<com.desmos.reports.v1.MsgsProto.MsgCreateReport, com.desmos.reports.v1.MsgsProto.MsgCreateReportResponse> getCreateReportMethod;
    if ((getCreateReportMethod = MsgGrpc.getCreateReportMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateReportMethod = MsgGrpc.getCreateReportMethod) == null) {
          MsgGrpc.getCreateReportMethod = getCreateReportMethod =
              io.grpc.MethodDescriptor.<com.desmos.reports.v1.MsgsProto.MsgCreateReport, com.desmos.reports.v1.MsgsProto.MsgCreateReportResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateReport"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reports.v1.MsgsProto.MsgCreateReport.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reports.v1.MsgsProto.MsgCreateReportResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateReport"))
              .build();
        }
      }
    }
    return getCreateReportMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.reports.v1.MsgsProto.MsgDeleteReport,
      com.desmos.reports.v1.MsgsProto.MsgDeleteReportResponse> getDeleteReportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteReport",
      requestType = com.desmos.reports.v1.MsgsProto.MsgDeleteReport.class,
      responseType = com.desmos.reports.v1.MsgsProto.MsgDeleteReportResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.reports.v1.MsgsProto.MsgDeleteReport,
      com.desmos.reports.v1.MsgsProto.MsgDeleteReportResponse> getDeleteReportMethod() {
    io.grpc.MethodDescriptor<com.desmos.reports.v1.MsgsProto.MsgDeleteReport, com.desmos.reports.v1.MsgsProto.MsgDeleteReportResponse> getDeleteReportMethod;
    if ((getDeleteReportMethod = MsgGrpc.getDeleteReportMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteReportMethod = MsgGrpc.getDeleteReportMethod) == null) {
          MsgGrpc.getDeleteReportMethod = getDeleteReportMethod =
              io.grpc.MethodDescriptor.<com.desmos.reports.v1.MsgsProto.MsgDeleteReport, com.desmos.reports.v1.MsgsProto.MsgDeleteReportResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteReport"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reports.v1.MsgsProto.MsgDeleteReport.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reports.v1.MsgsProto.MsgDeleteReportResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteReport"))
              .build();
        }
      }
    }
    return getDeleteReportMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.reports.v1.MsgsProto.MsgSupportStandardReason,
      com.desmos.reports.v1.MsgsProto.MsgSupportStandardReasonResponse> getSupportStandardReasonMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SupportStandardReason",
      requestType = com.desmos.reports.v1.MsgsProto.MsgSupportStandardReason.class,
      responseType = com.desmos.reports.v1.MsgsProto.MsgSupportStandardReasonResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.reports.v1.MsgsProto.MsgSupportStandardReason,
      com.desmos.reports.v1.MsgsProto.MsgSupportStandardReasonResponse> getSupportStandardReasonMethod() {
    io.grpc.MethodDescriptor<com.desmos.reports.v1.MsgsProto.MsgSupportStandardReason, com.desmos.reports.v1.MsgsProto.MsgSupportStandardReasonResponse> getSupportStandardReasonMethod;
    if ((getSupportStandardReasonMethod = MsgGrpc.getSupportStandardReasonMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSupportStandardReasonMethod = MsgGrpc.getSupportStandardReasonMethod) == null) {
          MsgGrpc.getSupportStandardReasonMethod = getSupportStandardReasonMethod =
              io.grpc.MethodDescriptor.<com.desmos.reports.v1.MsgsProto.MsgSupportStandardReason, com.desmos.reports.v1.MsgsProto.MsgSupportStandardReasonResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SupportStandardReason"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reports.v1.MsgsProto.MsgSupportStandardReason.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reports.v1.MsgsProto.MsgSupportStandardReasonResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SupportStandardReason"))
              .build();
        }
      }
    }
    return getSupportStandardReasonMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.reports.v1.MsgsProto.MsgAddReason,
      com.desmos.reports.v1.MsgsProto.MsgAddReasonResponse> getAddReasonMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddReason",
      requestType = com.desmos.reports.v1.MsgsProto.MsgAddReason.class,
      responseType = com.desmos.reports.v1.MsgsProto.MsgAddReasonResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.reports.v1.MsgsProto.MsgAddReason,
      com.desmos.reports.v1.MsgsProto.MsgAddReasonResponse> getAddReasonMethod() {
    io.grpc.MethodDescriptor<com.desmos.reports.v1.MsgsProto.MsgAddReason, com.desmos.reports.v1.MsgsProto.MsgAddReasonResponse> getAddReasonMethod;
    if ((getAddReasonMethod = MsgGrpc.getAddReasonMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddReasonMethod = MsgGrpc.getAddReasonMethod) == null) {
          MsgGrpc.getAddReasonMethod = getAddReasonMethod =
              io.grpc.MethodDescriptor.<com.desmos.reports.v1.MsgsProto.MsgAddReason, com.desmos.reports.v1.MsgsProto.MsgAddReasonResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddReason"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reports.v1.MsgsProto.MsgAddReason.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reports.v1.MsgsProto.MsgAddReasonResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddReason"))
              .build();
        }
      }
    }
    return getAddReasonMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.reports.v1.MsgsProto.MsgRemoveReason,
      com.desmos.reports.v1.MsgsProto.MsgRemoveReasonResponse> getRemoveReasonMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveReason",
      requestType = com.desmos.reports.v1.MsgsProto.MsgRemoveReason.class,
      responseType = com.desmos.reports.v1.MsgsProto.MsgRemoveReasonResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.reports.v1.MsgsProto.MsgRemoveReason,
      com.desmos.reports.v1.MsgsProto.MsgRemoveReasonResponse> getRemoveReasonMethod() {
    io.grpc.MethodDescriptor<com.desmos.reports.v1.MsgsProto.MsgRemoveReason, com.desmos.reports.v1.MsgsProto.MsgRemoveReasonResponse> getRemoveReasonMethod;
    if ((getRemoveReasonMethod = MsgGrpc.getRemoveReasonMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRemoveReasonMethod = MsgGrpc.getRemoveReasonMethod) == null) {
          MsgGrpc.getRemoveReasonMethod = getRemoveReasonMethod =
              io.grpc.MethodDescriptor.<com.desmos.reports.v1.MsgsProto.MsgRemoveReason, com.desmos.reports.v1.MsgsProto.MsgRemoveReasonResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemoveReason"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reports.v1.MsgsProto.MsgRemoveReason.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reports.v1.MsgsProto.MsgRemoveReasonResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RemoveReason"))
              .build();
        }
      }
    }
    return getRemoveReasonMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.reports.v1.MsgsProto.MsgUpdateParams,
      com.desmos.reports.v1.MsgsProto.MsgUpdateParamsResponse> getUpdateParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateParams",
      requestType = com.desmos.reports.v1.MsgsProto.MsgUpdateParams.class,
      responseType = com.desmos.reports.v1.MsgsProto.MsgUpdateParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.reports.v1.MsgsProto.MsgUpdateParams,
      com.desmos.reports.v1.MsgsProto.MsgUpdateParamsResponse> getUpdateParamsMethod() {
    io.grpc.MethodDescriptor<com.desmos.reports.v1.MsgsProto.MsgUpdateParams, com.desmos.reports.v1.MsgsProto.MsgUpdateParamsResponse> getUpdateParamsMethod;
    if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
          MsgGrpc.getUpdateParamsMethod = getUpdateParamsMethod =
              io.grpc.MethodDescriptor.<com.desmos.reports.v1.MsgsProto.MsgUpdateParams, com.desmos.reports.v1.MsgsProto.MsgUpdateParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reports.v1.MsgsProto.MsgUpdateParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.reports.v1.MsgsProto.MsgUpdateParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateParams"))
              .build();
        }
      }
    }
    return getUpdateParamsMethod;
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
   * Msg defines the reports Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * CreateReport allows to create a new report
     * </pre>
     */
    default void createReport(com.desmos.reports.v1.MsgsProto.MsgCreateReport request,
        io.grpc.stub.StreamObserver<com.desmos.reports.v1.MsgsProto.MsgCreateReportResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateReportMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteReport allows to delete an existing report
     * </pre>
     */
    default void deleteReport(com.desmos.reports.v1.MsgsProto.MsgDeleteReport request,
        io.grpc.stub.StreamObserver<com.desmos.reports.v1.MsgsProto.MsgDeleteReportResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteReportMethod(), responseObserver);
    }

    /**
     * <pre>
     * SupportStandardReason allows to support one of the reasons present inside
     * the module params
     * </pre>
     */
    default void supportStandardReason(com.desmos.reports.v1.MsgsProto.MsgSupportStandardReason request,
        io.grpc.stub.StreamObserver<com.desmos.reports.v1.MsgsProto.MsgSupportStandardReasonResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSupportStandardReasonMethod(), responseObserver);
    }

    /**
     * <pre>
     * AddReason allows to add a new supported reporting reason
     * </pre>
     */
    default void addReason(com.desmos.reports.v1.MsgsProto.MsgAddReason request,
        io.grpc.stub.StreamObserver<com.desmos.reports.v1.MsgsProto.MsgAddReasonResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddReasonMethod(), responseObserver);
    }

    /**
     * <pre>
     * RemoveReason allows to remove a supported reporting reason
     * </pre>
     */
    default void removeReason(com.desmos.reports.v1.MsgsProto.MsgRemoveReason request,
        io.grpc.stub.StreamObserver<com.desmos.reports.v1.MsgsProto.MsgRemoveReasonResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRemoveReasonMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateParams defines a (governance) operation for updating the module
     * parameters.
     * The authority defaults to the x/gov module account.
     * Since: Desmos 5.0.0
     * </pre>
     */
    default void updateParams(com.desmos.reports.v1.MsgsProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.desmos.reports.v1.MsgsProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateParamsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the reports Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MsgGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the reports Msg service.
   * </pre>
   */
  public static final class MsgStub
      extends io.grpc.stub.AbstractAsyncStub<MsgStub> {
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
     * CreateReport allows to create a new report
     * </pre>
     */
    public void createReport(com.desmos.reports.v1.MsgsProto.MsgCreateReport request,
        io.grpc.stub.StreamObserver<com.desmos.reports.v1.MsgsProto.MsgCreateReportResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateReportMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteReport allows to delete an existing report
     * </pre>
     */
    public void deleteReport(com.desmos.reports.v1.MsgsProto.MsgDeleteReport request,
        io.grpc.stub.StreamObserver<com.desmos.reports.v1.MsgsProto.MsgDeleteReportResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteReportMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SupportStandardReason allows to support one of the reasons present inside
     * the module params
     * </pre>
     */
    public void supportStandardReason(com.desmos.reports.v1.MsgsProto.MsgSupportStandardReason request,
        io.grpc.stub.StreamObserver<com.desmos.reports.v1.MsgsProto.MsgSupportStandardReasonResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSupportStandardReasonMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AddReason allows to add a new supported reporting reason
     * </pre>
     */
    public void addReason(com.desmos.reports.v1.MsgsProto.MsgAddReason request,
        io.grpc.stub.StreamObserver<com.desmos.reports.v1.MsgsProto.MsgAddReasonResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddReasonMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RemoveReason allows to remove a supported reporting reason
     * </pre>
     */
    public void removeReason(com.desmos.reports.v1.MsgsProto.MsgRemoveReason request,
        io.grpc.stub.StreamObserver<com.desmos.reports.v1.MsgsProto.MsgRemoveReasonResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRemoveReasonMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateParams defines a (governance) operation for updating the module
     * parameters.
     * The authority defaults to the x/gov module account.
     * Since: Desmos 5.0.0
     * </pre>
     */
    public void updateParams(com.desmos.reports.v1.MsgsProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.desmos.reports.v1.MsgsProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the reports Msg service.
   * </pre>
   */
  public static final class MsgBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<MsgBlockingStub> {
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
     * CreateReport allows to create a new report
     * </pre>
     */
    public com.desmos.reports.v1.MsgsProto.MsgCreateReportResponse createReport(com.desmos.reports.v1.MsgsProto.MsgCreateReport request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateReportMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteReport allows to delete an existing report
     * </pre>
     */
    public com.desmos.reports.v1.MsgsProto.MsgDeleteReportResponse deleteReport(com.desmos.reports.v1.MsgsProto.MsgDeleteReport request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteReportMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SupportStandardReason allows to support one of the reasons present inside
     * the module params
     * </pre>
     */
    public com.desmos.reports.v1.MsgsProto.MsgSupportStandardReasonResponse supportStandardReason(com.desmos.reports.v1.MsgsProto.MsgSupportStandardReason request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSupportStandardReasonMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AddReason allows to add a new supported reporting reason
     * </pre>
     */
    public com.desmos.reports.v1.MsgsProto.MsgAddReasonResponse addReason(com.desmos.reports.v1.MsgsProto.MsgAddReason request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddReasonMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RemoveReason allows to remove a supported reporting reason
     * </pre>
     */
    public com.desmos.reports.v1.MsgsProto.MsgRemoveReasonResponse removeReason(com.desmos.reports.v1.MsgsProto.MsgRemoveReason request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRemoveReasonMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateParams defines a (governance) operation for updating the module
     * parameters.
     * The authority defaults to the x/gov module account.
     * Since: Desmos 5.0.0
     * </pre>
     */
    public com.desmos.reports.v1.MsgsProto.MsgUpdateParamsResponse updateParams(com.desmos.reports.v1.MsgsProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the reports Msg service.
   * </pre>
   */
  public static final class MsgFutureStub
      extends io.grpc.stub.AbstractFutureStub<MsgFutureStub> {
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
     * CreateReport allows to create a new report
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.reports.v1.MsgsProto.MsgCreateReportResponse> createReport(
        com.desmos.reports.v1.MsgsProto.MsgCreateReport request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateReportMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteReport allows to delete an existing report
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.reports.v1.MsgsProto.MsgDeleteReportResponse> deleteReport(
        com.desmos.reports.v1.MsgsProto.MsgDeleteReport request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteReportMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SupportStandardReason allows to support one of the reasons present inside
     * the module params
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.reports.v1.MsgsProto.MsgSupportStandardReasonResponse> supportStandardReason(
        com.desmos.reports.v1.MsgsProto.MsgSupportStandardReason request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSupportStandardReasonMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AddReason allows to add a new supported reporting reason
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.reports.v1.MsgsProto.MsgAddReasonResponse> addReason(
        com.desmos.reports.v1.MsgsProto.MsgAddReason request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddReasonMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RemoveReason allows to remove a supported reporting reason
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.reports.v1.MsgsProto.MsgRemoveReasonResponse> removeReason(
        com.desmos.reports.v1.MsgsProto.MsgRemoveReason request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRemoveReasonMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateParams defines a (governance) operation for updating the module
     * parameters.
     * The authority defaults to the x/gov module account.
     * Since: Desmos 5.0.0
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.reports.v1.MsgsProto.MsgUpdateParamsResponse> updateParams(
        com.desmos.reports.v1.MsgsProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_REPORT = 0;
  private static final int METHODID_DELETE_REPORT = 1;
  private static final int METHODID_SUPPORT_STANDARD_REASON = 2;
  private static final int METHODID_ADD_REASON = 3;
  private static final int METHODID_REMOVE_REASON = 4;
  private static final int METHODID_UPDATE_PARAMS = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_REPORT:
          serviceImpl.createReport((com.desmos.reports.v1.MsgsProto.MsgCreateReport) request,
              (io.grpc.stub.StreamObserver<com.desmos.reports.v1.MsgsProto.MsgCreateReportResponse>) responseObserver);
          break;
        case METHODID_DELETE_REPORT:
          serviceImpl.deleteReport((com.desmos.reports.v1.MsgsProto.MsgDeleteReport) request,
              (io.grpc.stub.StreamObserver<com.desmos.reports.v1.MsgsProto.MsgDeleteReportResponse>) responseObserver);
          break;
        case METHODID_SUPPORT_STANDARD_REASON:
          serviceImpl.supportStandardReason((com.desmos.reports.v1.MsgsProto.MsgSupportStandardReason) request,
              (io.grpc.stub.StreamObserver<com.desmos.reports.v1.MsgsProto.MsgSupportStandardReasonResponse>) responseObserver);
          break;
        case METHODID_ADD_REASON:
          serviceImpl.addReason((com.desmos.reports.v1.MsgsProto.MsgAddReason) request,
              (io.grpc.stub.StreamObserver<com.desmos.reports.v1.MsgsProto.MsgAddReasonResponse>) responseObserver);
          break;
        case METHODID_REMOVE_REASON:
          serviceImpl.removeReason((com.desmos.reports.v1.MsgsProto.MsgRemoveReason) request,
              (io.grpc.stub.StreamObserver<com.desmos.reports.v1.MsgsProto.MsgRemoveReasonResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PARAMS:
          serviceImpl.updateParams((com.desmos.reports.v1.MsgsProto.MsgUpdateParams) request,
              (io.grpc.stub.StreamObserver<com.desmos.reports.v1.MsgsProto.MsgUpdateParamsResponse>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCreateReportMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.reports.v1.MsgsProto.MsgCreateReport,
              com.desmos.reports.v1.MsgsProto.MsgCreateReportResponse>(
                service, METHODID_CREATE_REPORT)))
        .addMethod(
          getDeleteReportMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.reports.v1.MsgsProto.MsgDeleteReport,
              com.desmos.reports.v1.MsgsProto.MsgDeleteReportResponse>(
                service, METHODID_DELETE_REPORT)))
        .addMethod(
          getSupportStandardReasonMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.reports.v1.MsgsProto.MsgSupportStandardReason,
              com.desmos.reports.v1.MsgsProto.MsgSupportStandardReasonResponse>(
                service, METHODID_SUPPORT_STANDARD_REASON)))
        .addMethod(
          getAddReasonMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.reports.v1.MsgsProto.MsgAddReason,
              com.desmos.reports.v1.MsgsProto.MsgAddReasonResponse>(
                service, METHODID_ADD_REASON)))
        .addMethod(
          getRemoveReasonMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.reports.v1.MsgsProto.MsgRemoveReason,
              com.desmos.reports.v1.MsgsProto.MsgRemoveReasonResponse>(
                service, METHODID_REMOVE_REASON)))
        .addMethod(
          getUpdateParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.reports.v1.MsgsProto.MsgUpdateParams,
              com.desmos.reports.v1.MsgsProto.MsgUpdateParamsResponse>(
                service, METHODID_UPDATE_PARAMS)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.desmos.reports.v1.MsgsProto.getDescriptor();
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
              .addMethod(getCreateReportMethod())
              .addMethod(getDeleteReportMethod())
              .addMethod(getSupportStandardReasonMethod())
              .addMethod(getAddReasonMethod())
              .addMethod(getRemoveReasonMethod())
              .addMethod(getUpdateParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
