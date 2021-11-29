package desmos.posts.v1beta1;

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
 * Msg defines the relationships Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: desmos/posts/v1beta1/msgs.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "desmos.posts.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<desmos.posts.v1beta1.Msgs.MsgCreatePost,
      desmos.posts.v1beta1.Msgs.MsgCreatePostResponse> getCreatePostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreatePost",
      requestType = desmos.posts.v1beta1.Msgs.MsgCreatePost.class,
      responseType = desmos.posts.v1beta1.Msgs.MsgCreatePostResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.posts.v1beta1.Msgs.MsgCreatePost,
      desmos.posts.v1beta1.Msgs.MsgCreatePostResponse> getCreatePostMethod() {
    io.grpc.MethodDescriptor<desmos.posts.v1beta1.Msgs.MsgCreatePost, desmos.posts.v1beta1.Msgs.MsgCreatePostResponse> getCreatePostMethod;
    if ((getCreatePostMethod = MsgGrpc.getCreatePostMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreatePostMethod = MsgGrpc.getCreatePostMethod) == null) {
          MsgGrpc.getCreatePostMethod = getCreatePostMethod =
              io.grpc.MethodDescriptor.<desmos.posts.v1beta1.Msgs.MsgCreatePost, desmos.posts.v1beta1.Msgs.MsgCreatePostResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreatePost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.Msgs.MsgCreatePost.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.Msgs.MsgCreatePostResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreatePost"))
              .build();
        }
      }
    }
    return getCreatePostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.posts.v1beta1.Msgs.MsgEditPost,
      desmos.posts.v1beta1.Msgs.MsgEditPostResponse> getEditPostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EditPost",
      requestType = desmos.posts.v1beta1.Msgs.MsgEditPost.class,
      responseType = desmos.posts.v1beta1.Msgs.MsgEditPostResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.posts.v1beta1.Msgs.MsgEditPost,
      desmos.posts.v1beta1.Msgs.MsgEditPostResponse> getEditPostMethod() {
    io.grpc.MethodDescriptor<desmos.posts.v1beta1.Msgs.MsgEditPost, desmos.posts.v1beta1.Msgs.MsgEditPostResponse> getEditPostMethod;
    if ((getEditPostMethod = MsgGrpc.getEditPostMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getEditPostMethod = MsgGrpc.getEditPostMethod) == null) {
          MsgGrpc.getEditPostMethod = getEditPostMethod =
              io.grpc.MethodDescriptor.<desmos.posts.v1beta1.Msgs.MsgEditPost, desmos.posts.v1beta1.Msgs.MsgEditPostResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EditPost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.Msgs.MsgEditPost.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.Msgs.MsgEditPostResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("EditPost"))
              .build();
        }
      }
    }
    return getEditPostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.posts.v1beta1.Msgs.MsgReportPost,
      desmos.posts.v1beta1.Msgs.MsgReportPostResponse> getReportPostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReportPost",
      requestType = desmos.posts.v1beta1.Msgs.MsgReportPost.class,
      responseType = desmos.posts.v1beta1.Msgs.MsgReportPostResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.posts.v1beta1.Msgs.MsgReportPost,
      desmos.posts.v1beta1.Msgs.MsgReportPostResponse> getReportPostMethod() {
    io.grpc.MethodDescriptor<desmos.posts.v1beta1.Msgs.MsgReportPost, desmos.posts.v1beta1.Msgs.MsgReportPostResponse> getReportPostMethod;
    if ((getReportPostMethod = MsgGrpc.getReportPostMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getReportPostMethod = MsgGrpc.getReportPostMethod) == null) {
          MsgGrpc.getReportPostMethod = getReportPostMethod =
              io.grpc.MethodDescriptor.<desmos.posts.v1beta1.Msgs.MsgReportPost, desmos.posts.v1beta1.Msgs.MsgReportPostResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReportPost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.Msgs.MsgReportPost.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.Msgs.MsgReportPostResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ReportPost"))
              .build();
        }
      }
    }
    return getReportPostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.posts.v1beta1.Msgs.MsgAddPostReaction,
      desmos.posts.v1beta1.Msgs.MsgAddPostReactionResponse> getAddPostReactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddPostReaction",
      requestType = desmos.posts.v1beta1.Msgs.MsgAddPostReaction.class,
      responseType = desmos.posts.v1beta1.Msgs.MsgAddPostReactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.posts.v1beta1.Msgs.MsgAddPostReaction,
      desmos.posts.v1beta1.Msgs.MsgAddPostReactionResponse> getAddPostReactionMethod() {
    io.grpc.MethodDescriptor<desmos.posts.v1beta1.Msgs.MsgAddPostReaction, desmos.posts.v1beta1.Msgs.MsgAddPostReactionResponse> getAddPostReactionMethod;
    if ((getAddPostReactionMethod = MsgGrpc.getAddPostReactionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddPostReactionMethod = MsgGrpc.getAddPostReactionMethod) == null) {
          MsgGrpc.getAddPostReactionMethod = getAddPostReactionMethod =
              io.grpc.MethodDescriptor.<desmos.posts.v1beta1.Msgs.MsgAddPostReaction, desmos.posts.v1beta1.Msgs.MsgAddPostReactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddPostReaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.Msgs.MsgAddPostReaction.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.Msgs.MsgAddPostReactionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddPostReaction"))
              .build();
        }
      }
    }
    return getAddPostReactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.posts.v1beta1.Msgs.MsgRemovePostReaction,
      desmos.posts.v1beta1.Msgs.MsgRemovePostReactionResponse> getRemovePostReactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemovePostReaction",
      requestType = desmos.posts.v1beta1.Msgs.MsgRemovePostReaction.class,
      responseType = desmos.posts.v1beta1.Msgs.MsgRemovePostReactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.posts.v1beta1.Msgs.MsgRemovePostReaction,
      desmos.posts.v1beta1.Msgs.MsgRemovePostReactionResponse> getRemovePostReactionMethod() {
    io.grpc.MethodDescriptor<desmos.posts.v1beta1.Msgs.MsgRemovePostReaction, desmos.posts.v1beta1.Msgs.MsgRemovePostReactionResponse> getRemovePostReactionMethod;
    if ((getRemovePostReactionMethod = MsgGrpc.getRemovePostReactionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRemovePostReactionMethod = MsgGrpc.getRemovePostReactionMethod) == null) {
          MsgGrpc.getRemovePostReactionMethod = getRemovePostReactionMethod =
              io.grpc.MethodDescriptor.<desmos.posts.v1beta1.Msgs.MsgRemovePostReaction, desmos.posts.v1beta1.Msgs.MsgRemovePostReactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemovePostReaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.Msgs.MsgRemovePostReaction.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.Msgs.MsgRemovePostReactionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RemovePostReaction"))
              .build();
        }
      }
    }
    return getRemovePostReactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.posts.v1beta1.Msgs.MsgRegisterReaction,
      desmos.posts.v1beta1.Msgs.MsgRegisterReactionResponse> getRegisterReactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterReaction",
      requestType = desmos.posts.v1beta1.Msgs.MsgRegisterReaction.class,
      responseType = desmos.posts.v1beta1.Msgs.MsgRegisterReactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.posts.v1beta1.Msgs.MsgRegisterReaction,
      desmos.posts.v1beta1.Msgs.MsgRegisterReactionResponse> getRegisterReactionMethod() {
    io.grpc.MethodDescriptor<desmos.posts.v1beta1.Msgs.MsgRegisterReaction, desmos.posts.v1beta1.Msgs.MsgRegisterReactionResponse> getRegisterReactionMethod;
    if ((getRegisterReactionMethod = MsgGrpc.getRegisterReactionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRegisterReactionMethod = MsgGrpc.getRegisterReactionMethod) == null) {
          MsgGrpc.getRegisterReactionMethod = getRegisterReactionMethod =
              io.grpc.MethodDescriptor.<desmos.posts.v1beta1.Msgs.MsgRegisterReaction, desmos.posts.v1beta1.Msgs.MsgRegisterReactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterReaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.Msgs.MsgRegisterReaction.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.Msgs.MsgRegisterReactionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RegisterReaction"))
              .build();
        }
      }
    }
    return getRegisterReactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.posts.v1beta1.Msgs.MsgAnswerPoll,
      desmos.posts.v1beta1.Msgs.MsgAnswerPollResponse> getAnswerPollMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AnswerPoll",
      requestType = desmos.posts.v1beta1.Msgs.MsgAnswerPoll.class,
      responseType = desmos.posts.v1beta1.Msgs.MsgAnswerPollResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.posts.v1beta1.Msgs.MsgAnswerPoll,
      desmos.posts.v1beta1.Msgs.MsgAnswerPollResponse> getAnswerPollMethod() {
    io.grpc.MethodDescriptor<desmos.posts.v1beta1.Msgs.MsgAnswerPoll, desmos.posts.v1beta1.Msgs.MsgAnswerPollResponse> getAnswerPollMethod;
    if ((getAnswerPollMethod = MsgGrpc.getAnswerPollMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAnswerPollMethod = MsgGrpc.getAnswerPollMethod) == null) {
          MsgGrpc.getAnswerPollMethod = getAnswerPollMethod =
              io.grpc.MethodDescriptor.<desmos.posts.v1beta1.Msgs.MsgAnswerPoll, desmos.posts.v1beta1.Msgs.MsgAnswerPollResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AnswerPoll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.Msgs.MsgAnswerPoll.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.Msgs.MsgAnswerPollResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AnswerPoll"))
              .build();
        }
      }
    }
    return getAnswerPollMethod;
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
   * Msg defines the relationships Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CreatePost defines the method to create a post
     * </pre>
     */
    public void createPost(desmos.posts.v1beta1.Msgs.MsgCreatePost request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.Msgs.MsgCreatePostResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreatePostMethod(), responseObserver);
    }

    /**
     * <pre>
     * EditPost defines the method to edit an existing post
     * </pre>
     */
    public void editPost(desmos.posts.v1beta1.Msgs.MsgEditPost request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.Msgs.MsgEditPostResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEditPostMethod(), responseObserver);
    }

    /**
     * <pre>
     * ReportPost defines a method for creating a new post report
     * </pre>
     */
    public void reportPost(desmos.posts.v1beta1.Msgs.MsgReportPost request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.Msgs.MsgReportPostResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReportPostMethod(), responseObserver);
    }

    /**
     * <pre>
     * AddReaction defines the method to add a reaction to a post
     * </pre>
     */
    public void addPostReaction(desmos.posts.v1beta1.Msgs.MsgAddPostReaction request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.Msgs.MsgAddPostReactionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddPostReactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * RemoveReaction defines the method to remove a reaction from a post
     * </pre>
     */
    public void removePostReaction(desmos.posts.v1beta1.Msgs.MsgRemovePostReaction request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.Msgs.MsgRemovePostReactionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRemovePostReactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * RegisterReaction defines the method to register a new reaction
     * </pre>
     */
    public void registerReaction(desmos.posts.v1beta1.Msgs.MsgRegisterReaction request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.Msgs.MsgRegisterReactionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterReactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * AnswerPoll defines the method to answer a poll
     * </pre>
     */
    public void answerPoll(desmos.posts.v1beta1.Msgs.MsgAnswerPoll request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.Msgs.MsgAnswerPollResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAnswerPollMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreatePostMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.posts.v1beta1.Msgs.MsgCreatePost,
                desmos.posts.v1beta1.Msgs.MsgCreatePostResponse>(
                  this, METHODID_CREATE_POST)))
          .addMethod(
            getEditPostMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.posts.v1beta1.Msgs.MsgEditPost,
                desmos.posts.v1beta1.Msgs.MsgEditPostResponse>(
                  this, METHODID_EDIT_POST)))
          .addMethod(
            getReportPostMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.posts.v1beta1.Msgs.MsgReportPost,
                desmos.posts.v1beta1.Msgs.MsgReportPostResponse>(
                  this, METHODID_REPORT_POST)))
          .addMethod(
            getAddPostReactionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.posts.v1beta1.Msgs.MsgAddPostReaction,
                desmos.posts.v1beta1.Msgs.MsgAddPostReactionResponse>(
                  this, METHODID_ADD_POST_REACTION)))
          .addMethod(
            getRemovePostReactionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.posts.v1beta1.Msgs.MsgRemovePostReaction,
                desmos.posts.v1beta1.Msgs.MsgRemovePostReactionResponse>(
                  this, METHODID_REMOVE_POST_REACTION)))
          .addMethod(
            getRegisterReactionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.posts.v1beta1.Msgs.MsgRegisterReaction,
                desmos.posts.v1beta1.Msgs.MsgRegisterReactionResponse>(
                  this, METHODID_REGISTER_REACTION)))
          .addMethod(
            getAnswerPollMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.posts.v1beta1.Msgs.MsgAnswerPoll,
                desmos.posts.v1beta1.Msgs.MsgAnswerPollResponse>(
                  this, METHODID_ANSWER_POLL)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the relationships Msg service.
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
     * CreatePost defines the method to create a post
     * </pre>
     */
    public void createPost(desmos.posts.v1beta1.Msgs.MsgCreatePost request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.Msgs.MsgCreatePostResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreatePostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EditPost defines the method to edit an existing post
     * </pre>
     */
    public void editPost(desmos.posts.v1beta1.Msgs.MsgEditPost request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.Msgs.MsgEditPostResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEditPostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ReportPost defines a method for creating a new post report
     * </pre>
     */
    public void reportPost(desmos.posts.v1beta1.Msgs.MsgReportPost request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.Msgs.MsgReportPostResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReportPostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AddReaction defines the method to add a reaction to a post
     * </pre>
     */
    public void addPostReaction(desmos.posts.v1beta1.Msgs.MsgAddPostReaction request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.Msgs.MsgAddPostReactionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddPostReactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RemoveReaction defines the method to remove a reaction from a post
     * </pre>
     */
    public void removePostReaction(desmos.posts.v1beta1.Msgs.MsgRemovePostReaction request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.Msgs.MsgRemovePostReactionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemovePostReactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RegisterReaction defines the method to register a new reaction
     * </pre>
     */
    public void registerReaction(desmos.posts.v1beta1.Msgs.MsgRegisterReaction request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.Msgs.MsgRegisterReactionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterReactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AnswerPoll defines the method to answer a poll
     * </pre>
     */
    public void answerPoll(desmos.posts.v1beta1.Msgs.MsgAnswerPoll request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.Msgs.MsgAnswerPollResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAnswerPollMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the relationships Msg service.
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
     * CreatePost defines the method to create a post
     * </pre>
     */
    public desmos.posts.v1beta1.Msgs.MsgCreatePostResponse createPost(desmos.posts.v1beta1.Msgs.MsgCreatePost request) {
      return blockingUnaryCall(
          getChannel(), getCreatePostMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EditPost defines the method to edit an existing post
     * </pre>
     */
    public desmos.posts.v1beta1.Msgs.MsgEditPostResponse editPost(desmos.posts.v1beta1.Msgs.MsgEditPost request) {
      return blockingUnaryCall(
          getChannel(), getEditPostMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ReportPost defines a method for creating a new post report
     * </pre>
     */
    public desmos.posts.v1beta1.Msgs.MsgReportPostResponse reportPost(desmos.posts.v1beta1.Msgs.MsgReportPost request) {
      return blockingUnaryCall(
          getChannel(), getReportPostMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AddReaction defines the method to add a reaction to a post
     * </pre>
     */
    public desmos.posts.v1beta1.Msgs.MsgAddPostReactionResponse addPostReaction(desmos.posts.v1beta1.Msgs.MsgAddPostReaction request) {
      return blockingUnaryCall(
          getChannel(), getAddPostReactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RemoveReaction defines the method to remove a reaction from a post
     * </pre>
     */
    public desmos.posts.v1beta1.Msgs.MsgRemovePostReactionResponse removePostReaction(desmos.posts.v1beta1.Msgs.MsgRemovePostReaction request) {
      return blockingUnaryCall(
          getChannel(), getRemovePostReactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RegisterReaction defines the method to register a new reaction
     * </pre>
     */
    public desmos.posts.v1beta1.Msgs.MsgRegisterReactionResponse registerReaction(desmos.posts.v1beta1.Msgs.MsgRegisterReaction request) {
      return blockingUnaryCall(
          getChannel(), getRegisterReactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AnswerPoll defines the method to answer a poll
     * </pre>
     */
    public desmos.posts.v1beta1.Msgs.MsgAnswerPollResponse answerPoll(desmos.posts.v1beta1.Msgs.MsgAnswerPoll request) {
      return blockingUnaryCall(
          getChannel(), getAnswerPollMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the relationships Msg service.
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
     * CreatePost defines the method to create a post
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.posts.v1beta1.Msgs.MsgCreatePostResponse> createPost(
        desmos.posts.v1beta1.Msgs.MsgCreatePost request) {
      return futureUnaryCall(
          getChannel().newCall(getCreatePostMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EditPost defines the method to edit an existing post
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.posts.v1beta1.Msgs.MsgEditPostResponse> editPost(
        desmos.posts.v1beta1.Msgs.MsgEditPost request) {
      return futureUnaryCall(
          getChannel().newCall(getEditPostMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ReportPost defines a method for creating a new post report
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.posts.v1beta1.Msgs.MsgReportPostResponse> reportPost(
        desmos.posts.v1beta1.Msgs.MsgReportPost request) {
      return futureUnaryCall(
          getChannel().newCall(getReportPostMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AddReaction defines the method to add a reaction to a post
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.posts.v1beta1.Msgs.MsgAddPostReactionResponse> addPostReaction(
        desmos.posts.v1beta1.Msgs.MsgAddPostReaction request) {
      return futureUnaryCall(
          getChannel().newCall(getAddPostReactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RemoveReaction defines the method to remove a reaction from a post
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.posts.v1beta1.Msgs.MsgRemovePostReactionResponse> removePostReaction(
        desmos.posts.v1beta1.Msgs.MsgRemovePostReaction request) {
      return futureUnaryCall(
          getChannel().newCall(getRemovePostReactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RegisterReaction defines the method to register a new reaction
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.posts.v1beta1.Msgs.MsgRegisterReactionResponse> registerReaction(
        desmos.posts.v1beta1.Msgs.MsgRegisterReaction request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterReactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AnswerPoll defines the method to answer a poll
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.posts.v1beta1.Msgs.MsgAnswerPollResponse> answerPoll(
        desmos.posts.v1beta1.Msgs.MsgAnswerPoll request) {
      return futureUnaryCall(
          getChannel().newCall(getAnswerPollMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_POST = 0;
  private static final int METHODID_EDIT_POST = 1;
  private static final int METHODID_REPORT_POST = 2;
  private static final int METHODID_ADD_POST_REACTION = 3;
  private static final int METHODID_REMOVE_POST_REACTION = 4;
  private static final int METHODID_REGISTER_REACTION = 5;
  private static final int METHODID_ANSWER_POLL = 6;

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
        case METHODID_CREATE_POST:
          serviceImpl.createPost((desmos.posts.v1beta1.Msgs.MsgCreatePost) request,
              (io.grpc.stub.StreamObserver<desmos.posts.v1beta1.Msgs.MsgCreatePostResponse>) responseObserver);
          break;
        case METHODID_EDIT_POST:
          serviceImpl.editPost((desmos.posts.v1beta1.Msgs.MsgEditPost) request,
              (io.grpc.stub.StreamObserver<desmos.posts.v1beta1.Msgs.MsgEditPostResponse>) responseObserver);
          break;
        case METHODID_REPORT_POST:
          serviceImpl.reportPost((desmos.posts.v1beta1.Msgs.MsgReportPost) request,
              (io.grpc.stub.StreamObserver<desmos.posts.v1beta1.Msgs.MsgReportPostResponse>) responseObserver);
          break;
        case METHODID_ADD_POST_REACTION:
          serviceImpl.addPostReaction((desmos.posts.v1beta1.Msgs.MsgAddPostReaction) request,
              (io.grpc.stub.StreamObserver<desmos.posts.v1beta1.Msgs.MsgAddPostReactionResponse>) responseObserver);
          break;
        case METHODID_REMOVE_POST_REACTION:
          serviceImpl.removePostReaction((desmos.posts.v1beta1.Msgs.MsgRemovePostReaction) request,
              (io.grpc.stub.StreamObserver<desmos.posts.v1beta1.Msgs.MsgRemovePostReactionResponse>) responseObserver);
          break;
        case METHODID_REGISTER_REACTION:
          serviceImpl.registerReaction((desmos.posts.v1beta1.Msgs.MsgRegisterReaction) request,
              (io.grpc.stub.StreamObserver<desmos.posts.v1beta1.Msgs.MsgRegisterReactionResponse>) responseObserver);
          break;
        case METHODID_ANSWER_POLL:
          serviceImpl.answerPoll((desmos.posts.v1beta1.Msgs.MsgAnswerPoll) request,
              (io.grpc.stub.StreamObserver<desmos.posts.v1beta1.Msgs.MsgAnswerPollResponse>) responseObserver);
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
      return desmos.posts.v1beta1.Msgs.getDescriptor();
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
              .addMethod(getCreatePostMethod())
              .addMethod(getEditPostMethod())
              .addMethod(getReportPostMethod())
              .addMethod(getAddPostReactionMethod())
              .addMethod(getRemovePostReactionMethod())
              .addMethod(getRegisterReactionMethod())
              .addMethod(getAnswerPollMethod())
              .build();
        }
      }
    }
    return result;
  }
}
