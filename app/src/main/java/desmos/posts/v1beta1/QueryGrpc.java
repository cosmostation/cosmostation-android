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
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: desmos/posts/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "desmos.posts.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryPostsRequest,
      desmos.posts.v1beta1.QueryOuterClass.QueryPostsResponse> getPostsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Posts",
      requestType = desmos.posts.v1beta1.QueryOuterClass.QueryPostsRequest.class,
      responseType = desmos.posts.v1beta1.QueryOuterClass.QueryPostsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryPostsRequest,
      desmos.posts.v1beta1.QueryOuterClass.QueryPostsResponse> getPostsMethod() {
    io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryPostsRequest, desmos.posts.v1beta1.QueryOuterClass.QueryPostsResponse> getPostsMethod;
    if ((getPostsMethod = QueryGrpc.getPostsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPostsMethod = QueryGrpc.getPostsMethod) == null) {
          QueryGrpc.getPostsMethod = getPostsMethod =
              io.grpc.MethodDescriptor.<desmos.posts.v1beta1.QueryOuterClass.QueryPostsRequest, desmos.posts.v1beta1.QueryOuterClass.QueryPostsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Posts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.QueryOuterClass.QueryPostsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.QueryOuterClass.QueryPostsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Posts"))
              .build();
        }
      }
    }
    return getPostsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryPostRequest,
      desmos.posts.v1beta1.QueryOuterClass.QueryPostResponse> getPostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Post",
      requestType = desmos.posts.v1beta1.QueryOuterClass.QueryPostRequest.class,
      responseType = desmos.posts.v1beta1.QueryOuterClass.QueryPostResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryPostRequest,
      desmos.posts.v1beta1.QueryOuterClass.QueryPostResponse> getPostMethod() {
    io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryPostRequest, desmos.posts.v1beta1.QueryOuterClass.QueryPostResponse> getPostMethod;
    if ((getPostMethod = QueryGrpc.getPostMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPostMethod = QueryGrpc.getPostMethod) == null) {
          QueryGrpc.getPostMethod = getPostMethod =
              io.grpc.MethodDescriptor.<desmos.posts.v1beta1.QueryOuterClass.QueryPostRequest, desmos.posts.v1beta1.QueryOuterClass.QueryPostResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Post"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.QueryOuterClass.QueryPostRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.QueryOuterClass.QueryPostResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Post"))
              .build();
        }
      }
    }
    return getPostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryReportsRequest,
      desmos.posts.v1beta1.QueryOuterClass.QueryReportsResponse> getReportsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Reports",
      requestType = desmos.posts.v1beta1.QueryOuterClass.QueryReportsRequest.class,
      responseType = desmos.posts.v1beta1.QueryOuterClass.QueryReportsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryReportsRequest,
      desmos.posts.v1beta1.QueryOuterClass.QueryReportsResponse> getReportsMethod() {
    io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryReportsRequest, desmos.posts.v1beta1.QueryOuterClass.QueryReportsResponse> getReportsMethod;
    if ((getReportsMethod = QueryGrpc.getReportsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getReportsMethod = QueryGrpc.getReportsMethod) == null) {
          QueryGrpc.getReportsMethod = getReportsMethod =
              io.grpc.MethodDescriptor.<desmos.posts.v1beta1.QueryOuterClass.QueryReportsRequest, desmos.posts.v1beta1.QueryOuterClass.QueryReportsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Reports"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.QueryOuterClass.QueryReportsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.QueryOuterClass.QueryReportsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Reports"))
              .build();
        }
      }
    }
    return getReportsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersRequest,
      desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersResponse> getUserAnswersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UserAnswers",
      requestType = desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersRequest.class,
      responseType = desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersRequest,
      desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersResponse> getUserAnswersMethod() {
    io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersRequest, desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersResponse> getUserAnswersMethod;
    if ((getUserAnswersMethod = QueryGrpc.getUserAnswersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUserAnswersMethod = QueryGrpc.getUserAnswersMethod) == null) {
          QueryGrpc.getUserAnswersMethod = getUserAnswersMethod =
              io.grpc.MethodDescriptor.<desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersRequest, desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UserAnswers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UserAnswers"))
              .build();
        }
      }
    }
    return getUserAnswersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsRequest,
      desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsResponse> getRegisteredReactionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisteredReactions",
      requestType = desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsRequest.class,
      responseType = desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsRequest,
      desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsResponse> getRegisteredReactionsMethod() {
    io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsRequest, desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsResponse> getRegisteredReactionsMethod;
    if ((getRegisteredReactionsMethod = QueryGrpc.getRegisteredReactionsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRegisteredReactionsMethod = QueryGrpc.getRegisteredReactionsMethod) == null) {
          QueryGrpc.getRegisteredReactionsMethod = getRegisteredReactionsMethod =
              io.grpc.MethodDescriptor.<desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsRequest, desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisteredReactions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RegisteredReactions"))
              .build();
        }
      }
    }
    return getRegisteredReactionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryParamsRequest,
      desmos.posts.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = desmos.posts.v1beta1.QueryOuterClass.QueryParamsRequest.class,
      responseType = desmos.posts.v1beta1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryParamsRequest,
      desmos.posts.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryParamsRequest, desmos.posts.v1beta1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<desmos.posts.v1beta1.QueryOuterClass.QueryParamsRequest, desmos.posts.v1beta1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsRequest,
      desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsResponse> getPostCommentsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PostComments",
      requestType = desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsRequest.class,
      responseType = desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsRequest,
      desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsResponse> getPostCommentsMethod() {
    io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsRequest, desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsResponse> getPostCommentsMethod;
    if ((getPostCommentsMethod = QueryGrpc.getPostCommentsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPostCommentsMethod = QueryGrpc.getPostCommentsMethod) == null) {
          QueryGrpc.getPostCommentsMethod = getPostCommentsMethod =
              io.grpc.MethodDescriptor.<desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsRequest, desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PostComments"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PostComments"))
              .build();
        }
      }
    }
    return getPostCommentsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsRequest,
      desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsResponse> getPostReactionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PostReactions",
      requestType = desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsRequest.class,
      responseType = desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsRequest,
      desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsResponse> getPostReactionsMethod() {
    io.grpc.MethodDescriptor<desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsRequest, desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsResponse> getPostReactionsMethod;
    if ((getPostReactionsMethod = QueryGrpc.getPostReactionsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPostReactionsMethod = QueryGrpc.getPostReactionsMethod) == null) {
          QueryGrpc.getPostReactionsMethod = getPostReactionsMethod =
              io.grpc.MethodDescriptor.<desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsRequest, desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PostReactions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PostReactions"))
              .build();
        }
      }
    }
    return getPostReactionsMethod;
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
     * Posts queries all the stored posts
     * </pre>
     */
    public void posts(desmos.posts.v1beta1.QueryOuterClass.QueryPostsRequest request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryPostsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPostsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Post queries a specific post
     * </pre>
     */
    public void post(desmos.posts.v1beta1.QueryOuterClass.QueryPostRequest request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryPostResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPostMethod(), responseObserver);
    }

    /**
     * <pre>
     * Reports queries the reports for the post having the given id
     * </pre>
     */
    public void reports(desmos.posts.v1beta1.QueryOuterClass.QueryReportsRequest request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryReportsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReportsMethod(), responseObserver);
    }

    /**
     * <pre>
     * UserAnswers queries the user answers of the post having a specific id
     * </pre>
     */
    public void userAnswers(desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersRequest request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUserAnswersMethod(), responseObserver);
    }

    /**
     * <pre>
     * RegisteredReactions queries all the registered reactions
     * </pre>
     */
    public void registeredReactions(desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsRequest request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisteredReactionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries the posts module params
     * </pre>
     */
    public void params(desmos.posts.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * PostComments queries the comments of the post having the given id
     * </pre>
     */
    public void postComments(desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsRequest request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPostCommentsMethod(), responseObserver);
    }

    /**
     * <pre>
     * PostReactions queries all the reactions of the post having the given id
     * </pre>
     */
    public void postReactions(desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsRequest request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPostReactionsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPostsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.posts.v1beta1.QueryOuterClass.QueryPostsRequest,
                desmos.posts.v1beta1.QueryOuterClass.QueryPostsResponse>(
                  this, METHODID_POSTS)))
          .addMethod(
            getPostMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.posts.v1beta1.QueryOuterClass.QueryPostRequest,
                desmos.posts.v1beta1.QueryOuterClass.QueryPostResponse>(
                  this, METHODID_POST)))
          .addMethod(
            getReportsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.posts.v1beta1.QueryOuterClass.QueryReportsRequest,
                desmos.posts.v1beta1.QueryOuterClass.QueryReportsResponse>(
                  this, METHODID_REPORTS)))
          .addMethod(
            getUserAnswersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersRequest,
                desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersResponse>(
                  this, METHODID_USER_ANSWERS)))
          .addMethod(
            getRegisteredReactionsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsRequest,
                desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsResponse>(
                  this, METHODID_REGISTERED_REACTIONS)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.posts.v1beta1.QueryOuterClass.QueryParamsRequest,
                desmos.posts.v1beta1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getPostCommentsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsRequest,
                desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsResponse>(
                  this, METHODID_POST_COMMENTS)))
          .addMethod(
            getPostReactionsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsRequest,
                desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsResponse>(
                  this, METHODID_POST_REACTIONS)))
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
     * Posts queries all the stored posts
     * </pre>
     */
    public void posts(desmos.posts.v1beta1.QueryOuterClass.QueryPostsRequest request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryPostsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPostsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Post queries a specific post
     * </pre>
     */
    public void post(desmos.posts.v1beta1.QueryOuterClass.QueryPostRequest request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryPostResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Reports queries the reports for the post having the given id
     * </pre>
     */
    public void reports(desmos.posts.v1beta1.QueryOuterClass.QueryReportsRequest request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryReportsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReportsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UserAnswers queries the user answers of the post having a specific id
     * </pre>
     */
    public void userAnswers(desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersRequest request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUserAnswersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RegisteredReactions queries all the registered reactions
     * </pre>
     */
    public void registeredReactions(desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsRequest request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisteredReactionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries the posts module params
     * </pre>
     */
    public void params(desmos.posts.v1beta1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PostComments queries the comments of the post having the given id
     * </pre>
     */
    public void postComments(desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsRequest request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPostCommentsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PostReactions queries all the reactions of the post having the given id
     * </pre>
     */
    public void postReactions(desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsRequest request,
        io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPostReactionsMethod(), getCallOptions()), request, responseObserver);
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
     * Posts queries all the stored posts
     * </pre>
     */
    public desmos.posts.v1beta1.QueryOuterClass.QueryPostsResponse posts(desmos.posts.v1beta1.QueryOuterClass.QueryPostsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPostsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Post queries a specific post
     * </pre>
     */
    public desmos.posts.v1beta1.QueryOuterClass.QueryPostResponse post(desmos.posts.v1beta1.QueryOuterClass.QueryPostRequest request) {
      return blockingUnaryCall(
          getChannel(), getPostMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Reports queries the reports for the post having the given id
     * </pre>
     */
    public desmos.posts.v1beta1.QueryOuterClass.QueryReportsResponse reports(desmos.posts.v1beta1.QueryOuterClass.QueryReportsRequest request) {
      return blockingUnaryCall(
          getChannel(), getReportsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UserAnswers queries the user answers of the post having a specific id
     * </pre>
     */
    public desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersResponse userAnswers(desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersRequest request) {
      return blockingUnaryCall(
          getChannel(), getUserAnswersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RegisteredReactions queries all the registered reactions
     * </pre>
     */
    public desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsResponse registeredReactions(desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsRequest request) {
      return blockingUnaryCall(
          getChannel(), getRegisteredReactionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries the posts module params
     * </pre>
     */
    public desmos.posts.v1beta1.QueryOuterClass.QueryParamsResponse params(desmos.posts.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PostComments queries the comments of the post having the given id
     * </pre>
     */
    public desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsResponse postComments(desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPostCommentsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PostReactions queries all the reactions of the post having the given id
     * </pre>
     */
    public desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsResponse postReactions(desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPostReactionsMethod(), getCallOptions(), request);
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
     * Posts queries all the stored posts
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.posts.v1beta1.QueryOuterClass.QueryPostsResponse> posts(
        desmos.posts.v1beta1.QueryOuterClass.QueryPostsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPostsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Post queries a specific post
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.posts.v1beta1.QueryOuterClass.QueryPostResponse> post(
        desmos.posts.v1beta1.QueryOuterClass.QueryPostRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPostMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Reports queries the reports for the post having the given id
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.posts.v1beta1.QueryOuterClass.QueryReportsResponse> reports(
        desmos.posts.v1beta1.QueryOuterClass.QueryReportsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getReportsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UserAnswers queries the user answers of the post having a specific id
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersResponse> userAnswers(
        desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUserAnswersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RegisteredReactions queries all the registered reactions
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsResponse> registeredReactions(
        desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisteredReactionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries the posts module params
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.posts.v1beta1.QueryOuterClass.QueryParamsResponse> params(
        desmos.posts.v1beta1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PostComments queries the comments of the post having the given id
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsResponse> postComments(
        desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPostCommentsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PostReactions queries all the reactions of the post having the given id
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsResponse> postReactions(
        desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPostReactionsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_POSTS = 0;
  private static final int METHODID_POST = 1;
  private static final int METHODID_REPORTS = 2;
  private static final int METHODID_USER_ANSWERS = 3;
  private static final int METHODID_REGISTERED_REACTIONS = 4;
  private static final int METHODID_PARAMS = 5;
  private static final int METHODID_POST_COMMENTS = 6;
  private static final int METHODID_POST_REACTIONS = 7;

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
        case METHODID_POSTS:
          serviceImpl.posts((desmos.posts.v1beta1.QueryOuterClass.QueryPostsRequest) request,
              (io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryPostsResponse>) responseObserver);
          break;
        case METHODID_POST:
          serviceImpl.post((desmos.posts.v1beta1.QueryOuterClass.QueryPostRequest) request,
              (io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryPostResponse>) responseObserver);
          break;
        case METHODID_REPORTS:
          serviceImpl.reports((desmos.posts.v1beta1.QueryOuterClass.QueryReportsRequest) request,
              (io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryReportsResponse>) responseObserver);
          break;
        case METHODID_USER_ANSWERS:
          serviceImpl.userAnswers((desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersRequest) request,
              (io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryUserAnswersResponse>) responseObserver);
          break;
        case METHODID_REGISTERED_REACTIONS:
          serviceImpl.registeredReactions((desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsRequest) request,
              (io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryRegisteredReactionsResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((desmos.posts.v1beta1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_POST_COMMENTS:
          serviceImpl.postComments((desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsRequest) request,
              (io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryPostCommentsResponse>) responseObserver);
          break;
        case METHODID_POST_REACTIONS:
          serviceImpl.postReactions((desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsRequest) request,
              (io.grpc.stub.StreamObserver<desmos.posts.v1beta1.QueryOuterClass.QueryPostReactionsResponse>) responseObserver);
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
      return desmos.posts.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getPostsMethod())
              .addMethod(getPostMethod())
              .addMethod(getReportsMethod())
              .addMethod(getUserAnswersMethod())
              .addMethod(getRegisteredReactionsMethod())
              .addMethod(getParamsMethod())
              .addMethod(getPostCommentsMethod())
              .addMethod(getPostReactionsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
