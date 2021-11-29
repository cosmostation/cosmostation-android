package project;

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
 * Msg defines the project Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: project/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "project.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<project.Tx.MsgCreateProject,
      project.Tx.MsgCreateProjectResponse> getCreateProjectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateProject",
      requestType = project.Tx.MsgCreateProject.class,
      responseType = project.Tx.MsgCreateProjectResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<project.Tx.MsgCreateProject,
      project.Tx.MsgCreateProjectResponse> getCreateProjectMethod() {
    io.grpc.MethodDescriptor<project.Tx.MsgCreateProject, project.Tx.MsgCreateProjectResponse> getCreateProjectMethod;
    if ((getCreateProjectMethod = MsgGrpc.getCreateProjectMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateProjectMethod = MsgGrpc.getCreateProjectMethod) == null) {
          MsgGrpc.getCreateProjectMethod = getCreateProjectMethod =
              io.grpc.MethodDescriptor.<project.Tx.MsgCreateProject, project.Tx.MsgCreateProjectResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateProject"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.Tx.MsgCreateProject.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.Tx.MsgCreateProjectResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateProject"))
              .build();
        }
      }
    }
    return getCreateProjectMethod;
  }

  private static volatile io.grpc.MethodDescriptor<project.Tx.MsgUpdateProjectStatus,
      project.Tx.MsgUpdateProjectStatusResponse> getUpdateProjectStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateProjectStatus",
      requestType = project.Tx.MsgUpdateProjectStatus.class,
      responseType = project.Tx.MsgUpdateProjectStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<project.Tx.MsgUpdateProjectStatus,
      project.Tx.MsgUpdateProjectStatusResponse> getUpdateProjectStatusMethod() {
    io.grpc.MethodDescriptor<project.Tx.MsgUpdateProjectStatus, project.Tx.MsgUpdateProjectStatusResponse> getUpdateProjectStatusMethod;
    if ((getUpdateProjectStatusMethod = MsgGrpc.getUpdateProjectStatusMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateProjectStatusMethod = MsgGrpc.getUpdateProjectStatusMethod) == null) {
          MsgGrpc.getUpdateProjectStatusMethod = getUpdateProjectStatusMethod =
              io.grpc.MethodDescriptor.<project.Tx.MsgUpdateProjectStatus, project.Tx.MsgUpdateProjectStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateProjectStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.Tx.MsgUpdateProjectStatus.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.Tx.MsgUpdateProjectStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateProjectStatus"))
              .build();
        }
      }
    }
    return getUpdateProjectStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<project.Tx.MsgCreateAgent,
      project.Tx.MsgCreateAgentResponse> getCreateAgentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateAgent",
      requestType = project.Tx.MsgCreateAgent.class,
      responseType = project.Tx.MsgCreateAgentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<project.Tx.MsgCreateAgent,
      project.Tx.MsgCreateAgentResponse> getCreateAgentMethod() {
    io.grpc.MethodDescriptor<project.Tx.MsgCreateAgent, project.Tx.MsgCreateAgentResponse> getCreateAgentMethod;
    if ((getCreateAgentMethod = MsgGrpc.getCreateAgentMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateAgentMethod = MsgGrpc.getCreateAgentMethod) == null) {
          MsgGrpc.getCreateAgentMethod = getCreateAgentMethod =
              io.grpc.MethodDescriptor.<project.Tx.MsgCreateAgent, project.Tx.MsgCreateAgentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateAgent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.Tx.MsgCreateAgent.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.Tx.MsgCreateAgentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateAgent"))
              .build();
        }
      }
    }
    return getCreateAgentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<project.Tx.MsgUpdateAgent,
      project.Tx.MsgUpdateAgentResponse> getUpdateAgentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateAgent",
      requestType = project.Tx.MsgUpdateAgent.class,
      responseType = project.Tx.MsgUpdateAgentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<project.Tx.MsgUpdateAgent,
      project.Tx.MsgUpdateAgentResponse> getUpdateAgentMethod() {
    io.grpc.MethodDescriptor<project.Tx.MsgUpdateAgent, project.Tx.MsgUpdateAgentResponse> getUpdateAgentMethod;
    if ((getUpdateAgentMethod = MsgGrpc.getUpdateAgentMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateAgentMethod = MsgGrpc.getUpdateAgentMethod) == null) {
          MsgGrpc.getUpdateAgentMethod = getUpdateAgentMethod =
              io.grpc.MethodDescriptor.<project.Tx.MsgUpdateAgent, project.Tx.MsgUpdateAgentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateAgent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.Tx.MsgUpdateAgent.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.Tx.MsgUpdateAgentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateAgent"))
              .build();
        }
      }
    }
    return getUpdateAgentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<project.Tx.MsgCreateClaim,
      project.Tx.MsgCreateClaimResponse> getCreateClaimMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateClaim",
      requestType = project.Tx.MsgCreateClaim.class,
      responseType = project.Tx.MsgCreateClaimResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<project.Tx.MsgCreateClaim,
      project.Tx.MsgCreateClaimResponse> getCreateClaimMethod() {
    io.grpc.MethodDescriptor<project.Tx.MsgCreateClaim, project.Tx.MsgCreateClaimResponse> getCreateClaimMethod;
    if ((getCreateClaimMethod = MsgGrpc.getCreateClaimMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateClaimMethod = MsgGrpc.getCreateClaimMethod) == null) {
          MsgGrpc.getCreateClaimMethod = getCreateClaimMethod =
              io.grpc.MethodDescriptor.<project.Tx.MsgCreateClaim, project.Tx.MsgCreateClaimResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateClaim"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.Tx.MsgCreateClaim.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.Tx.MsgCreateClaimResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateClaim"))
              .build();
        }
      }
    }
    return getCreateClaimMethod;
  }

  private static volatile io.grpc.MethodDescriptor<project.Tx.MsgCreateEvaluation,
      project.Tx.MsgCreateEvaluationResponse> getCreateEvaluationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateEvaluation",
      requestType = project.Tx.MsgCreateEvaluation.class,
      responseType = project.Tx.MsgCreateEvaluationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<project.Tx.MsgCreateEvaluation,
      project.Tx.MsgCreateEvaluationResponse> getCreateEvaluationMethod() {
    io.grpc.MethodDescriptor<project.Tx.MsgCreateEvaluation, project.Tx.MsgCreateEvaluationResponse> getCreateEvaluationMethod;
    if ((getCreateEvaluationMethod = MsgGrpc.getCreateEvaluationMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateEvaluationMethod = MsgGrpc.getCreateEvaluationMethod) == null) {
          MsgGrpc.getCreateEvaluationMethod = getCreateEvaluationMethod =
              io.grpc.MethodDescriptor.<project.Tx.MsgCreateEvaluation, project.Tx.MsgCreateEvaluationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateEvaluation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.Tx.MsgCreateEvaluation.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.Tx.MsgCreateEvaluationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateEvaluation"))
              .build();
        }
      }
    }
    return getCreateEvaluationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<project.Tx.MsgWithdrawFunds,
      project.Tx.MsgWithdrawFundsResponse> getWithdrawFundsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WithdrawFunds",
      requestType = project.Tx.MsgWithdrawFunds.class,
      responseType = project.Tx.MsgWithdrawFundsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<project.Tx.MsgWithdrawFunds,
      project.Tx.MsgWithdrawFundsResponse> getWithdrawFundsMethod() {
    io.grpc.MethodDescriptor<project.Tx.MsgWithdrawFunds, project.Tx.MsgWithdrawFundsResponse> getWithdrawFundsMethod;
    if ((getWithdrawFundsMethod = MsgGrpc.getWithdrawFundsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWithdrawFundsMethod = MsgGrpc.getWithdrawFundsMethod) == null) {
          MsgGrpc.getWithdrawFundsMethod = getWithdrawFundsMethod =
              io.grpc.MethodDescriptor.<project.Tx.MsgWithdrawFunds, project.Tx.MsgWithdrawFundsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WithdrawFunds"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.Tx.MsgWithdrawFunds.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.Tx.MsgWithdrawFundsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WithdrawFunds"))
              .build();
        }
      }
    }
    return getWithdrawFundsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<project.Tx.MsgUpdateProjectDoc,
      project.Tx.MsgUpdateProjectDocResponse> getUpdateProjectDocMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateProjectDoc",
      requestType = project.Tx.MsgUpdateProjectDoc.class,
      responseType = project.Tx.MsgUpdateProjectDocResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<project.Tx.MsgUpdateProjectDoc,
      project.Tx.MsgUpdateProjectDocResponse> getUpdateProjectDocMethod() {
    io.grpc.MethodDescriptor<project.Tx.MsgUpdateProjectDoc, project.Tx.MsgUpdateProjectDocResponse> getUpdateProjectDocMethod;
    if ((getUpdateProjectDocMethod = MsgGrpc.getUpdateProjectDocMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateProjectDocMethod = MsgGrpc.getUpdateProjectDocMethod) == null) {
          MsgGrpc.getUpdateProjectDocMethod = getUpdateProjectDocMethod =
              io.grpc.MethodDescriptor.<project.Tx.MsgUpdateProjectDoc, project.Tx.MsgUpdateProjectDocResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateProjectDoc"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.Tx.MsgUpdateProjectDoc.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  project.Tx.MsgUpdateProjectDocResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateProjectDoc"))
              .build();
        }
      }
    }
    return getUpdateProjectDocMethod;
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
   * Msg defines the project Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CreateProject defines a method for creating a project.
     * </pre>
     */
    public void createProject(project.Tx.MsgCreateProject request,
        io.grpc.stub.StreamObserver<project.Tx.MsgCreateProjectResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateProjectMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateProjectStatus defines a method for updating a project's current status.
     * </pre>
     */
    public void updateProjectStatus(project.Tx.MsgUpdateProjectStatus request,
        io.grpc.stub.StreamObserver<project.Tx.MsgUpdateProjectStatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateProjectStatusMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateAgent defines a method for creating an agent on a project.
     * </pre>
     */
    public void createAgent(project.Tx.MsgCreateAgent request,
        io.grpc.stub.StreamObserver<project.Tx.MsgCreateAgentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateAgentMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateAgent defines a method for updating an agent on a project.
     * </pre>
     */
    public void updateAgent(project.Tx.MsgUpdateAgent request,
        io.grpc.stub.StreamObserver<project.Tx.MsgUpdateAgentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateAgentMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateClaim defines a method for creating a claim on a project.
     * </pre>
     */
    public void createClaim(project.Tx.MsgCreateClaim request,
        io.grpc.stub.StreamObserver<project.Tx.MsgCreateClaimResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateClaimMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateEvaluation defines a method for creating an evaluation for a specific claim on a project.
     * </pre>
     */
    public void createEvaluation(project.Tx.MsgCreateEvaluation request,
        io.grpc.stub.StreamObserver<project.Tx.MsgCreateEvaluationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateEvaluationMethod(), responseObserver);
    }

    /**
     * <pre>
     * WithdrawFunds defines a method for project agents to withdraw their funds from a project.
     * </pre>
     */
    public void withdrawFunds(project.Tx.MsgWithdrawFunds request,
        io.grpc.stub.StreamObserver<project.Tx.MsgWithdrawFundsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawFundsMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateProjectDoc defines a method for updating a project's data.
     * </pre>
     */
    public void updateProjectDoc(project.Tx.MsgUpdateProjectDoc request,
        io.grpc.stub.StreamObserver<project.Tx.MsgUpdateProjectDocResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateProjectDocMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateProjectMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                project.Tx.MsgCreateProject,
                project.Tx.MsgCreateProjectResponse>(
                  this, METHODID_CREATE_PROJECT)))
          .addMethod(
            getUpdateProjectStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                project.Tx.MsgUpdateProjectStatus,
                project.Tx.MsgUpdateProjectStatusResponse>(
                  this, METHODID_UPDATE_PROJECT_STATUS)))
          .addMethod(
            getCreateAgentMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                project.Tx.MsgCreateAgent,
                project.Tx.MsgCreateAgentResponse>(
                  this, METHODID_CREATE_AGENT)))
          .addMethod(
            getUpdateAgentMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                project.Tx.MsgUpdateAgent,
                project.Tx.MsgUpdateAgentResponse>(
                  this, METHODID_UPDATE_AGENT)))
          .addMethod(
            getCreateClaimMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                project.Tx.MsgCreateClaim,
                project.Tx.MsgCreateClaimResponse>(
                  this, METHODID_CREATE_CLAIM)))
          .addMethod(
            getCreateEvaluationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                project.Tx.MsgCreateEvaluation,
                project.Tx.MsgCreateEvaluationResponse>(
                  this, METHODID_CREATE_EVALUATION)))
          .addMethod(
            getWithdrawFundsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                project.Tx.MsgWithdrawFunds,
                project.Tx.MsgWithdrawFundsResponse>(
                  this, METHODID_WITHDRAW_FUNDS)))
          .addMethod(
            getUpdateProjectDocMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                project.Tx.MsgUpdateProjectDoc,
                project.Tx.MsgUpdateProjectDocResponse>(
                  this, METHODID_UPDATE_PROJECT_DOC)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the project Msg service.
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
     * CreateProject defines a method for creating a project.
     * </pre>
     */
    public void createProject(project.Tx.MsgCreateProject request,
        io.grpc.stub.StreamObserver<project.Tx.MsgCreateProjectResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateProjectMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateProjectStatus defines a method for updating a project's current status.
     * </pre>
     */
    public void updateProjectStatus(project.Tx.MsgUpdateProjectStatus request,
        io.grpc.stub.StreamObserver<project.Tx.MsgUpdateProjectStatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateProjectStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateAgent defines a method for creating an agent on a project.
     * </pre>
     */
    public void createAgent(project.Tx.MsgCreateAgent request,
        io.grpc.stub.StreamObserver<project.Tx.MsgCreateAgentResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateAgentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateAgent defines a method for updating an agent on a project.
     * </pre>
     */
    public void updateAgent(project.Tx.MsgUpdateAgent request,
        io.grpc.stub.StreamObserver<project.Tx.MsgUpdateAgentResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateAgentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateClaim defines a method for creating a claim on a project.
     * </pre>
     */
    public void createClaim(project.Tx.MsgCreateClaim request,
        io.grpc.stub.StreamObserver<project.Tx.MsgCreateClaimResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateClaimMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateEvaluation defines a method for creating an evaluation for a specific claim on a project.
     * </pre>
     */
    public void createEvaluation(project.Tx.MsgCreateEvaluation request,
        io.grpc.stub.StreamObserver<project.Tx.MsgCreateEvaluationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateEvaluationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WithdrawFunds defines a method for project agents to withdraw their funds from a project.
     * </pre>
     */
    public void withdrawFunds(project.Tx.MsgWithdrawFunds request,
        io.grpc.stub.StreamObserver<project.Tx.MsgWithdrawFundsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawFundsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateProjectDoc defines a method for updating a project's data.
     * </pre>
     */
    public void updateProjectDoc(project.Tx.MsgUpdateProjectDoc request,
        io.grpc.stub.StreamObserver<project.Tx.MsgUpdateProjectDocResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateProjectDocMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the project Msg service.
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
     * CreateProject defines a method for creating a project.
     * </pre>
     */
    public project.Tx.MsgCreateProjectResponse createProject(project.Tx.MsgCreateProject request) {
      return blockingUnaryCall(
          getChannel(), getCreateProjectMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateProjectStatus defines a method for updating a project's current status.
     * </pre>
     */
    public project.Tx.MsgUpdateProjectStatusResponse updateProjectStatus(project.Tx.MsgUpdateProjectStatus request) {
      return blockingUnaryCall(
          getChannel(), getUpdateProjectStatusMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateAgent defines a method for creating an agent on a project.
     * </pre>
     */
    public project.Tx.MsgCreateAgentResponse createAgent(project.Tx.MsgCreateAgent request) {
      return blockingUnaryCall(
          getChannel(), getCreateAgentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateAgent defines a method for updating an agent on a project.
     * </pre>
     */
    public project.Tx.MsgUpdateAgentResponse updateAgent(project.Tx.MsgUpdateAgent request) {
      return blockingUnaryCall(
          getChannel(), getUpdateAgentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateClaim defines a method for creating a claim on a project.
     * </pre>
     */
    public project.Tx.MsgCreateClaimResponse createClaim(project.Tx.MsgCreateClaim request) {
      return blockingUnaryCall(
          getChannel(), getCreateClaimMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateEvaluation defines a method for creating an evaluation for a specific claim on a project.
     * </pre>
     */
    public project.Tx.MsgCreateEvaluationResponse createEvaluation(project.Tx.MsgCreateEvaluation request) {
      return blockingUnaryCall(
          getChannel(), getCreateEvaluationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WithdrawFunds defines a method for project agents to withdraw their funds from a project.
     * </pre>
     */
    public project.Tx.MsgWithdrawFundsResponse withdrawFunds(project.Tx.MsgWithdrawFunds request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawFundsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateProjectDoc defines a method for updating a project's data.
     * </pre>
     */
    public project.Tx.MsgUpdateProjectDocResponse updateProjectDoc(project.Tx.MsgUpdateProjectDoc request) {
      return blockingUnaryCall(
          getChannel(), getUpdateProjectDocMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the project Msg service.
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
     * CreateProject defines a method for creating a project.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<project.Tx.MsgCreateProjectResponse> createProject(
        project.Tx.MsgCreateProject request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateProjectMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateProjectStatus defines a method for updating a project's current status.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<project.Tx.MsgUpdateProjectStatusResponse> updateProjectStatus(
        project.Tx.MsgUpdateProjectStatus request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateProjectStatusMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateAgent defines a method for creating an agent on a project.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<project.Tx.MsgCreateAgentResponse> createAgent(
        project.Tx.MsgCreateAgent request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateAgentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateAgent defines a method for updating an agent on a project.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<project.Tx.MsgUpdateAgentResponse> updateAgent(
        project.Tx.MsgUpdateAgent request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateAgentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateClaim defines a method for creating a claim on a project.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<project.Tx.MsgCreateClaimResponse> createClaim(
        project.Tx.MsgCreateClaim request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateClaimMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateEvaluation defines a method for creating an evaluation for a specific claim on a project.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<project.Tx.MsgCreateEvaluationResponse> createEvaluation(
        project.Tx.MsgCreateEvaluation request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateEvaluationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WithdrawFunds defines a method for project agents to withdraw their funds from a project.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<project.Tx.MsgWithdrawFundsResponse> withdrawFunds(
        project.Tx.MsgWithdrawFunds request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawFundsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateProjectDoc defines a method for updating a project's data.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<project.Tx.MsgUpdateProjectDocResponse> updateProjectDoc(
        project.Tx.MsgUpdateProjectDoc request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateProjectDocMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_PROJECT = 0;
  private static final int METHODID_UPDATE_PROJECT_STATUS = 1;
  private static final int METHODID_CREATE_AGENT = 2;
  private static final int METHODID_UPDATE_AGENT = 3;
  private static final int METHODID_CREATE_CLAIM = 4;
  private static final int METHODID_CREATE_EVALUATION = 5;
  private static final int METHODID_WITHDRAW_FUNDS = 6;
  private static final int METHODID_UPDATE_PROJECT_DOC = 7;

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
        case METHODID_CREATE_PROJECT:
          serviceImpl.createProject((project.Tx.MsgCreateProject) request,
              (io.grpc.stub.StreamObserver<project.Tx.MsgCreateProjectResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PROJECT_STATUS:
          serviceImpl.updateProjectStatus((project.Tx.MsgUpdateProjectStatus) request,
              (io.grpc.stub.StreamObserver<project.Tx.MsgUpdateProjectStatusResponse>) responseObserver);
          break;
        case METHODID_CREATE_AGENT:
          serviceImpl.createAgent((project.Tx.MsgCreateAgent) request,
              (io.grpc.stub.StreamObserver<project.Tx.MsgCreateAgentResponse>) responseObserver);
          break;
        case METHODID_UPDATE_AGENT:
          serviceImpl.updateAgent((project.Tx.MsgUpdateAgent) request,
              (io.grpc.stub.StreamObserver<project.Tx.MsgUpdateAgentResponse>) responseObserver);
          break;
        case METHODID_CREATE_CLAIM:
          serviceImpl.createClaim((project.Tx.MsgCreateClaim) request,
              (io.grpc.stub.StreamObserver<project.Tx.MsgCreateClaimResponse>) responseObserver);
          break;
        case METHODID_CREATE_EVALUATION:
          serviceImpl.createEvaluation((project.Tx.MsgCreateEvaluation) request,
              (io.grpc.stub.StreamObserver<project.Tx.MsgCreateEvaluationResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW_FUNDS:
          serviceImpl.withdrawFunds((project.Tx.MsgWithdrawFunds) request,
              (io.grpc.stub.StreamObserver<project.Tx.MsgWithdrawFundsResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PROJECT_DOC:
          serviceImpl.updateProjectDoc((project.Tx.MsgUpdateProjectDoc) request,
              (io.grpc.stub.StreamObserver<project.Tx.MsgUpdateProjectDocResponse>) responseObserver);
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
      return project.Tx.getDescriptor();
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
              .addMethod(getCreateProjectMethod())
              .addMethod(getUpdateProjectStatusMethod())
              .addMethod(getCreateAgentMethod())
              .addMethod(getUpdateAgentMethod())
              .addMethod(getCreateClaimMethod())
              .addMethod(getCreateEvaluationMethod())
              .addMethod(getWithdrawFundsMethod())
              .addMethod(getUpdateProjectDocMethod())
              .build();
        }
      }
    }
    return result;
  }
}
