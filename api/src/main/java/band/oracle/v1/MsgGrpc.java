package band.oracle.v1;

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
 * Msg defines the oracle Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: band/oracle/v1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "band.oracle.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<band.oracle.v1.Tx.MsgRequestData,
      band.oracle.v1.Tx.MsgRequestDataResponse> getRequestDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RequestData",
      requestType = band.oracle.v1.Tx.MsgRequestData.class,
      responseType = band.oracle.v1.Tx.MsgRequestDataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<band.oracle.v1.Tx.MsgRequestData,
      band.oracle.v1.Tx.MsgRequestDataResponse> getRequestDataMethod() {
    io.grpc.MethodDescriptor<band.oracle.v1.Tx.MsgRequestData, band.oracle.v1.Tx.MsgRequestDataResponse> getRequestDataMethod;
    if ((getRequestDataMethod = MsgGrpc.getRequestDataMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRequestDataMethod = MsgGrpc.getRequestDataMethod) == null) {
          MsgGrpc.getRequestDataMethod = getRequestDataMethod =
              io.grpc.MethodDescriptor.<band.oracle.v1.Tx.MsgRequestData, band.oracle.v1.Tx.MsgRequestDataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RequestData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.Tx.MsgRequestData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.Tx.MsgRequestDataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RequestData"))
              .build();
        }
      }
    }
    return getRequestDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<band.oracle.v1.Tx.MsgReportData,
      band.oracle.v1.Tx.MsgReportDataResponse> getReportDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReportData",
      requestType = band.oracle.v1.Tx.MsgReportData.class,
      responseType = band.oracle.v1.Tx.MsgReportDataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<band.oracle.v1.Tx.MsgReportData,
      band.oracle.v1.Tx.MsgReportDataResponse> getReportDataMethod() {
    io.grpc.MethodDescriptor<band.oracle.v1.Tx.MsgReportData, band.oracle.v1.Tx.MsgReportDataResponse> getReportDataMethod;
    if ((getReportDataMethod = MsgGrpc.getReportDataMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getReportDataMethod = MsgGrpc.getReportDataMethod) == null) {
          MsgGrpc.getReportDataMethod = getReportDataMethod =
              io.grpc.MethodDescriptor.<band.oracle.v1.Tx.MsgReportData, band.oracle.v1.Tx.MsgReportDataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReportData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.Tx.MsgReportData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.Tx.MsgReportDataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ReportData"))
              .build();
        }
      }
    }
    return getReportDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<band.oracle.v1.Tx.MsgCreateDataSource,
      band.oracle.v1.Tx.MsgCreateDataSourceResponse> getCreateDataSourceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateDataSource",
      requestType = band.oracle.v1.Tx.MsgCreateDataSource.class,
      responseType = band.oracle.v1.Tx.MsgCreateDataSourceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<band.oracle.v1.Tx.MsgCreateDataSource,
      band.oracle.v1.Tx.MsgCreateDataSourceResponse> getCreateDataSourceMethod() {
    io.grpc.MethodDescriptor<band.oracle.v1.Tx.MsgCreateDataSource, band.oracle.v1.Tx.MsgCreateDataSourceResponse> getCreateDataSourceMethod;
    if ((getCreateDataSourceMethod = MsgGrpc.getCreateDataSourceMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateDataSourceMethod = MsgGrpc.getCreateDataSourceMethod) == null) {
          MsgGrpc.getCreateDataSourceMethod = getCreateDataSourceMethod =
              io.grpc.MethodDescriptor.<band.oracle.v1.Tx.MsgCreateDataSource, band.oracle.v1.Tx.MsgCreateDataSourceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateDataSource"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.Tx.MsgCreateDataSource.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.Tx.MsgCreateDataSourceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateDataSource"))
              .build();
        }
      }
    }
    return getCreateDataSourceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<band.oracle.v1.Tx.MsgEditDataSource,
      band.oracle.v1.Tx.MsgEditDataSourceResponse> getEditDataSourceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EditDataSource",
      requestType = band.oracle.v1.Tx.MsgEditDataSource.class,
      responseType = band.oracle.v1.Tx.MsgEditDataSourceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<band.oracle.v1.Tx.MsgEditDataSource,
      band.oracle.v1.Tx.MsgEditDataSourceResponse> getEditDataSourceMethod() {
    io.grpc.MethodDescriptor<band.oracle.v1.Tx.MsgEditDataSource, band.oracle.v1.Tx.MsgEditDataSourceResponse> getEditDataSourceMethod;
    if ((getEditDataSourceMethod = MsgGrpc.getEditDataSourceMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getEditDataSourceMethod = MsgGrpc.getEditDataSourceMethod) == null) {
          MsgGrpc.getEditDataSourceMethod = getEditDataSourceMethod =
              io.grpc.MethodDescriptor.<band.oracle.v1.Tx.MsgEditDataSource, band.oracle.v1.Tx.MsgEditDataSourceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EditDataSource"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.Tx.MsgEditDataSource.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.Tx.MsgEditDataSourceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("EditDataSource"))
              .build();
        }
      }
    }
    return getEditDataSourceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<band.oracle.v1.Tx.MsgCreateOracleScript,
      band.oracle.v1.Tx.MsgCreateOracleScriptResponse> getCreateOracleScriptMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateOracleScript",
      requestType = band.oracle.v1.Tx.MsgCreateOracleScript.class,
      responseType = band.oracle.v1.Tx.MsgCreateOracleScriptResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<band.oracle.v1.Tx.MsgCreateOracleScript,
      band.oracle.v1.Tx.MsgCreateOracleScriptResponse> getCreateOracleScriptMethod() {
    io.grpc.MethodDescriptor<band.oracle.v1.Tx.MsgCreateOracleScript, band.oracle.v1.Tx.MsgCreateOracleScriptResponse> getCreateOracleScriptMethod;
    if ((getCreateOracleScriptMethod = MsgGrpc.getCreateOracleScriptMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateOracleScriptMethod = MsgGrpc.getCreateOracleScriptMethod) == null) {
          MsgGrpc.getCreateOracleScriptMethod = getCreateOracleScriptMethod =
              io.grpc.MethodDescriptor.<band.oracle.v1.Tx.MsgCreateOracleScript, band.oracle.v1.Tx.MsgCreateOracleScriptResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateOracleScript"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.Tx.MsgCreateOracleScript.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.Tx.MsgCreateOracleScriptResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateOracleScript"))
              .build();
        }
      }
    }
    return getCreateOracleScriptMethod;
  }

  private static volatile io.grpc.MethodDescriptor<band.oracle.v1.Tx.MsgEditOracleScript,
      band.oracle.v1.Tx.MsgEditOracleScriptResponse> getEditOracleScriptMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EditOracleScript",
      requestType = band.oracle.v1.Tx.MsgEditOracleScript.class,
      responseType = band.oracle.v1.Tx.MsgEditOracleScriptResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<band.oracle.v1.Tx.MsgEditOracleScript,
      band.oracle.v1.Tx.MsgEditOracleScriptResponse> getEditOracleScriptMethod() {
    io.grpc.MethodDescriptor<band.oracle.v1.Tx.MsgEditOracleScript, band.oracle.v1.Tx.MsgEditOracleScriptResponse> getEditOracleScriptMethod;
    if ((getEditOracleScriptMethod = MsgGrpc.getEditOracleScriptMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getEditOracleScriptMethod = MsgGrpc.getEditOracleScriptMethod) == null) {
          MsgGrpc.getEditOracleScriptMethod = getEditOracleScriptMethod =
              io.grpc.MethodDescriptor.<band.oracle.v1.Tx.MsgEditOracleScript, band.oracle.v1.Tx.MsgEditOracleScriptResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EditOracleScript"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.Tx.MsgEditOracleScript.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.Tx.MsgEditOracleScriptResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("EditOracleScript"))
              .build();
        }
      }
    }
    return getEditOracleScriptMethod;
  }

  private static volatile io.grpc.MethodDescriptor<band.oracle.v1.Tx.MsgActivate,
      band.oracle.v1.Tx.MsgActivateResponse> getActivateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Activate",
      requestType = band.oracle.v1.Tx.MsgActivate.class,
      responseType = band.oracle.v1.Tx.MsgActivateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<band.oracle.v1.Tx.MsgActivate,
      band.oracle.v1.Tx.MsgActivateResponse> getActivateMethod() {
    io.grpc.MethodDescriptor<band.oracle.v1.Tx.MsgActivate, band.oracle.v1.Tx.MsgActivateResponse> getActivateMethod;
    if ((getActivateMethod = MsgGrpc.getActivateMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getActivateMethod = MsgGrpc.getActivateMethod) == null) {
          MsgGrpc.getActivateMethod = getActivateMethod =
              io.grpc.MethodDescriptor.<band.oracle.v1.Tx.MsgActivate, band.oracle.v1.Tx.MsgActivateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Activate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.Tx.MsgActivate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.Tx.MsgActivateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Activate"))
              .build();
        }
      }
    }
    return getActivateMethod;
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
   * Msg defines the oracle Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * RequestData defines a method for submitting a new request.
     * </pre>
     */
    public void requestData(band.oracle.v1.Tx.MsgRequestData request,
        io.grpc.stub.StreamObserver<band.oracle.v1.Tx.MsgRequestDataResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRequestDataMethod(), responseObserver);
    }

    /**
     * <pre>
     * ReportData defines a method for reporting a data to resolve the request.
     * </pre>
     */
    public void reportData(band.oracle.v1.Tx.MsgReportData request,
        io.grpc.stub.StreamObserver<band.oracle.v1.Tx.MsgReportDataResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReportDataMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateDataSource defines a method for creating a new data source.
     * </pre>
     */
    public void createDataSource(band.oracle.v1.Tx.MsgCreateDataSource request,
        io.grpc.stub.StreamObserver<band.oracle.v1.Tx.MsgCreateDataSourceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateDataSourceMethod(), responseObserver);
    }

    /**
     * <pre>
     * EditDataSource defines a method for editing an existing data source.
     * </pre>
     */
    public void editDataSource(band.oracle.v1.Tx.MsgEditDataSource request,
        io.grpc.stub.StreamObserver<band.oracle.v1.Tx.MsgEditDataSourceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEditDataSourceMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateOracleScript defines a method for creating a new oracle script.
     * </pre>
     */
    public void createOracleScript(band.oracle.v1.Tx.MsgCreateOracleScript request,
        io.grpc.stub.StreamObserver<band.oracle.v1.Tx.MsgCreateOracleScriptResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateOracleScriptMethod(), responseObserver);
    }

    /**
     * <pre>
     * EditOracleScript defines a method for editing an existing oracle script.
     * </pre>
     */
    public void editOracleScript(band.oracle.v1.Tx.MsgEditOracleScript request,
        io.grpc.stub.StreamObserver<band.oracle.v1.Tx.MsgEditOracleScriptResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEditOracleScriptMethod(), responseObserver);
    }

    /**
     * <pre>
     * Activate defines a method for applying to be an oracle validator.
     * </pre>
     */
    public void activate(band.oracle.v1.Tx.MsgActivate request,
        io.grpc.stub.StreamObserver<band.oracle.v1.Tx.MsgActivateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getActivateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRequestDataMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                band.oracle.v1.Tx.MsgRequestData,
                band.oracle.v1.Tx.MsgRequestDataResponse>(
                  this, METHODID_REQUEST_DATA)))
          .addMethod(
            getReportDataMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                band.oracle.v1.Tx.MsgReportData,
                band.oracle.v1.Tx.MsgReportDataResponse>(
                  this, METHODID_REPORT_DATA)))
          .addMethod(
            getCreateDataSourceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                band.oracle.v1.Tx.MsgCreateDataSource,
                band.oracle.v1.Tx.MsgCreateDataSourceResponse>(
                  this, METHODID_CREATE_DATA_SOURCE)))
          .addMethod(
            getEditDataSourceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                band.oracle.v1.Tx.MsgEditDataSource,
                band.oracle.v1.Tx.MsgEditDataSourceResponse>(
                  this, METHODID_EDIT_DATA_SOURCE)))
          .addMethod(
            getCreateOracleScriptMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                band.oracle.v1.Tx.MsgCreateOracleScript,
                band.oracle.v1.Tx.MsgCreateOracleScriptResponse>(
                  this, METHODID_CREATE_ORACLE_SCRIPT)))
          .addMethod(
            getEditOracleScriptMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                band.oracle.v1.Tx.MsgEditOracleScript,
                band.oracle.v1.Tx.MsgEditOracleScriptResponse>(
                  this, METHODID_EDIT_ORACLE_SCRIPT)))
          .addMethod(
            getActivateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                band.oracle.v1.Tx.MsgActivate,
                band.oracle.v1.Tx.MsgActivateResponse>(
                  this, METHODID_ACTIVATE)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the oracle Msg service.
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
     * RequestData defines a method for submitting a new request.
     * </pre>
     */
    public void requestData(band.oracle.v1.Tx.MsgRequestData request,
        io.grpc.stub.StreamObserver<band.oracle.v1.Tx.MsgRequestDataResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRequestDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ReportData defines a method for reporting a data to resolve the request.
     * </pre>
     */
    public void reportData(band.oracle.v1.Tx.MsgReportData request,
        io.grpc.stub.StreamObserver<band.oracle.v1.Tx.MsgReportDataResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReportDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateDataSource defines a method for creating a new data source.
     * </pre>
     */
    public void createDataSource(band.oracle.v1.Tx.MsgCreateDataSource request,
        io.grpc.stub.StreamObserver<band.oracle.v1.Tx.MsgCreateDataSourceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateDataSourceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EditDataSource defines a method for editing an existing data source.
     * </pre>
     */
    public void editDataSource(band.oracle.v1.Tx.MsgEditDataSource request,
        io.grpc.stub.StreamObserver<band.oracle.v1.Tx.MsgEditDataSourceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEditDataSourceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateOracleScript defines a method for creating a new oracle script.
     * </pre>
     */
    public void createOracleScript(band.oracle.v1.Tx.MsgCreateOracleScript request,
        io.grpc.stub.StreamObserver<band.oracle.v1.Tx.MsgCreateOracleScriptResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateOracleScriptMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EditOracleScript defines a method for editing an existing oracle script.
     * </pre>
     */
    public void editOracleScript(band.oracle.v1.Tx.MsgEditOracleScript request,
        io.grpc.stub.StreamObserver<band.oracle.v1.Tx.MsgEditOracleScriptResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEditOracleScriptMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Activate defines a method for applying to be an oracle validator.
     * </pre>
     */
    public void activate(band.oracle.v1.Tx.MsgActivate request,
        io.grpc.stub.StreamObserver<band.oracle.v1.Tx.MsgActivateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getActivateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the oracle Msg service.
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
     * RequestData defines a method for submitting a new request.
     * </pre>
     */
    public band.oracle.v1.Tx.MsgRequestDataResponse requestData(band.oracle.v1.Tx.MsgRequestData request) {
      return blockingUnaryCall(
          getChannel(), getRequestDataMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ReportData defines a method for reporting a data to resolve the request.
     * </pre>
     */
    public band.oracle.v1.Tx.MsgReportDataResponse reportData(band.oracle.v1.Tx.MsgReportData request) {
      return blockingUnaryCall(
          getChannel(), getReportDataMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateDataSource defines a method for creating a new data source.
     * </pre>
     */
    public band.oracle.v1.Tx.MsgCreateDataSourceResponse createDataSource(band.oracle.v1.Tx.MsgCreateDataSource request) {
      return blockingUnaryCall(
          getChannel(), getCreateDataSourceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EditDataSource defines a method for editing an existing data source.
     * </pre>
     */
    public band.oracle.v1.Tx.MsgEditDataSourceResponse editDataSource(band.oracle.v1.Tx.MsgEditDataSource request) {
      return blockingUnaryCall(
          getChannel(), getEditDataSourceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateOracleScript defines a method for creating a new oracle script.
     * </pre>
     */
    public band.oracle.v1.Tx.MsgCreateOracleScriptResponse createOracleScript(band.oracle.v1.Tx.MsgCreateOracleScript request) {
      return blockingUnaryCall(
          getChannel(), getCreateOracleScriptMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EditOracleScript defines a method for editing an existing oracle script.
     * </pre>
     */
    public band.oracle.v1.Tx.MsgEditOracleScriptResponse editOracleScript(band.oracle.v1.Tx.MsgEditOracleScript request) {
      return blockingUnaryCall(
          getChannel(), getEditOracleScriptMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Activate defines a method for applying to be an oracle validator.
     * </pre>
     */
    public band.oracle.v1.Tx.MsgActivateResponse activate(band.oracle.v1.Tx.MsgActivate request) {
      return blockingUnaryCall(
          getChannel(), getActivateMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the oracle Msg service.
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
     * RequestData defines a method for submitting a new request.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<band.oracle.v1.Tx.MsgRequestDataResponse> requestData(
        band.oracle.v1.Tx.MsgRequestData request) {
      return futureUnaryCall(
          getChannel().newCall(getRequestDataMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ReportData defines a method for reporting a data to resolve the request.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<band.oracle.v1.Tx.MsgReportDataResponse> reportData(
        band.oracle.v1.Tx.MsgReportData request) {
      return futureUnaryCall(
          getChannel().newCall(getReportDataMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateDataSource defines a method for creating a new data source.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<band.oracle.v1.Tx.MsgCreateDataSourceResponse> createDataSource(
        band.oracle.v1.Tx.MsgCreateDataSource request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateDataSourceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EditDataSource defines a method for editing an existing data source.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<band.oracle.v1.Tx.MsgEditDataSourceResponse> editDataSource(
        band.oracle.v1.Tx.MsgEditDataSource request) {
      return futureUnaryCall(
          getChannel().newCall(getEditDataSourceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateOracleScript defines a method for creating a new oracle script.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<band.oracle.v1.Tx.MsgCreateOracleScriptResponse> createOracleScript(
        band.oracle.v1.Tx.MsgCreateOracleScript request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateOracleScriptMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EditOracleScript defines a method for editing an existing oracle script.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<band.oracle.v1.Tx.MsgEditOracleScriptResponse> editOracleScript(
        band.oracle.v1.Tx.MsgEditOracleScript request) {
      return futureUnaryCall(
          getChannel().newCall(getEditOracleScriptMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Activate defines a method for applying to be an oracle validator.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<band.oracle.v1.Tx.MsgActivateResponse> activate(
        band.oracle.v1.Tx.MsgActivate request) {
      return futureUnaryCall(
          getChannel().newCall(getActivateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REQUEST_DATA = 0;
  private static final int METHODID_REPORT_DATA = 1;
  private static final int METHODID_CREATE_DATA_SOURCE = 2;
  private static final int METHODID_EDIT_DATA_SOURCE = 3;
  private static final int METHODID_CREATE_ORACLE_SCRIPT = 4;
  private static final int METHODID_EDIT_ORACLE_SCRIPT = 5;
  private static final int METHODID_ACTIVATE = 6;

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
        case METHODID_REQUEST_DATA:
          serviceImpl.requestData((band.oracle.v1.Tx.MsgRequestData) request,
              (io.grpc.stub.StreamObserver<band.oracle.v1.Tx.MsgRequestDataResponse>) responseObserver);
          break;
        case METHODID_REPORT_DATA:
          serviceImpl.reportData((band.oracle.v1.Tx.MsgReportData) request,
              (io.grpc.stub.StreamObserver<band.oracle.v1.Tx.MsgReportDataResponse>) responseObserver);
          break;
        case METHODID_CREATE_DATA_SOURCE:
          serviceImpl.createDataSource((band.oracle.v1.Tx.MsgCreateDataSource) request,
              (io.grpc.stub.StreamObserver<band.oracle.v1.Tx.MsgCreateDataSourceResponse>) responseObserver);
          break;
        case METHODID_EDIT_DATA_SOURCE:
          serviceImpl.editDataSource((band.oracle.v1.Tx.MsgEditDataSource) request,
              (io.grpc.stub.StreamObserver<band.oracle.v1.Tx.MsgEditDataSourceResponse>) responseObserver);
          break;
        case METHODID_CREATE_ORACLE_SCRIPT:
          serviceImpl.createOracleScript((band.oracle.v1.Tx.MsgCreateOracleScript) request,
              (io.grpc.stub.StreamObserver<band.oracle.v1.Tx.MsgCreateOracleScriptResponse>) responseObserver);
          break;
        case METHODID_EDIT_ORACLE_SCRIPT:
          serviceImpl.editOracleScript((band.oracle.v1.Tx.MsgEditOracleScript) request,
              (io.grpc.stub.StreamObserver<band.oracle.v1.Tx.MsgEditOracleScriptResponse>) responseObserver);
          break;
        case METHODID_ACTIVATE:
          serviceImpl.activate((band.oracle.v1.Tx.MsgActivate) request,
              (io.grpc.stub.StreamObserver<band.oracle.v1.Tx.MsgActivateResponse>) responseObserver);
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
      return band.oracle.v1.Tx.getDescriptor();
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
              .addMethod(getRequestDataMethod())
              .addMethod(getReportDataMethod())
              .addMethod(getCreateDataSourceMethod())
              .addMethod(getEditDataSourceMethod())
              .addMethod(getCreateOracleScriptMethod())
              .addMethod(getEditOracleScriptMethod())
              .addMethod(getActivateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
