package com.desmos.posts.v3;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Query defines the gRPC querier service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: desmos/posts/v3/query.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "desmos.posts.v3.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.desmos.posts.v3.QueryProto.QuerySubspacePostsRequest,
      com.desmos.posts.v3.QueryProto.QuerySubspacePostsResponse> getSubspacePostsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubspacePosts",
      requestType = com.desmos.posts.v3.QueryProto.QuerySubspacePostsRequest.class,
      responseType = com.desmos.posts.v3.QueryProto.QuerySubspacePostsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.posts.v3.QueryProto.QuerySubspacePostsRequest,
      com.desmos.posts.v3.QueryProto.QuerySubspacePostsResponse> getSubspacePostsMethod() {
    io.grpc.MethodDescriptor<com.desmos.posts.v3.QueryProto.QuerySubspacePostsRequest, com.desmos.posts.v3.QueryProto.QuerySubspacePostsResponse> getSubspacePostsMethod;
    if ((getSubspacePostsMethod = QueryGrpc.getSubspacePostsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSubspacePostsMethod = QueryGrpc.getSubspacePostsMethod) == null) {
          QueryGrpc.getSubspacePostsMethod = getSubspacePostsMethod =
              io.grpc.MethodDescriptor.<com.desmos.posts.v3.QueryProto.QuerySubspacePostsRequest, com.desmos.posts.v3.QueryProto.QuerySubspacePostsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubspacePosts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.posts.v3.QueryProto.QuerySubspacePostsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.posts.v3.QueryProto.QuerySubspacePostsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SubspacePosts"))
              .build();
        }
      }
    }
    return getSubspacePostsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.posts.v3.QueryProto.QuerySectionPostsRequest,
      com.desmos.posts.v3.QueryProto.QuerySectionPostsResponse> getSectionPostsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SectionPosts",
      requestType = com.desmos.posts.v3.QueryProto.QuerySectionPostsRequest.class,
      responseType = com.desmos.posts.v3.QueryProto.QuerySectionPostsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.posts.v3.QueryProto.QuerySectionPostsRequest,
      com.desmos.posts.v3.QueryProto.QuerySectionPostsResponse> getSectionPostsMethod() {
    io.grpc.MethodDescriptor<com.desmos.posts.v3.QueryProto.QuerySectionPostsRequest, com.desmos.posts.v3.QueryProto.QuerySectionPostsResponse> getSectionPostsMethod;
    if ((getSectionPostsMethod = QueryGrpc.getSectionPostsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSectionPostsMethod = QueryGrpc.getSectionPostsMethod) == null) {
          QueryGrpc.getSectionPostsMethod = getSectionPostsMethod =
              io.grpc.MethodDescriptor.<com.desmos.posts.v3.QueryProto.QuerySectionPostsRequest, com.desmos.posts.v3.QueryProto.QuerySectionPostsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SectionPosts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.posts.v3.QueryProto.QuerySectionPostsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.posts.v3.QueryProto.QuerySectionPostsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SectionPosts"))
              .build();
        }
      }
    }
    return getSectionPostsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.posts.v3.QueryProto.QueryPostRequest,
      com.desmos.posts.v3.QueryProto.QueryPostResponse> getPostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Post",
      requestType = com.desmos.posts.v3.QueryProto.QueryPostRequest.class,
      responseType = com.desmos.posts.v3.QueryProto.QueryPostResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.posts.v3.QueryProto.QueryPostRequest,
      com.desmos.posts.v3.QueryProto.QueryPostResponse> getPostMethod() {
    io.grpc.MethodDescriptor<com.desmos.posts.v3.QueryProto.QueryPostRequest, com.desmos.posts.v3.QueryProto.QueryPostResponse> getPostMethod;
    if ((getPostMethod = QueryGrpc.getPostMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPostMethod = QueryGrpc.getPostMethod) == null) {
          QueryGrpc.getPostMethod = getPostMethod =
              io.grpc.MethodDescriptor.<com.desmos.posts.v3.QueryProto.QueryPostRequest, com.desmos.posts.v3.QueryProto.QueryPostResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Post"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.posts.v3.QueryProto.QueryPostRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.posts.v3.QueryProto.QueryPostResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Post"))
              .build();
        }
      }
    }
    return getPostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.posts.v3.QueryProto.QueryPostAttachmentsRequest,
      com.desmos.posts.v3.QueryProto.QueryPostAttachmentsResponse> getPostAttachmentsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PostAttachments",
      requestType = com.desmos.posts.v3.QueryProto.QueryPostAttachmentsRequest.class,
      responseType = com.desmos.posts.v3.QueryProto.QueryPostAttachmentsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.posts.v3.QueryProto.QueryPostAttachmentsRequest,
      com.desmos.posts.v3.QueryProto.QueryPostAttachmentsResponse> getPostAttachmentsMethod() {
    io.grpc.MethodDescriptor<com.desmos.posts.v3.QueryProto.QueryPostAttachmentsRequest, com.desmos.posts.v3.QueryProto.QueryPostAttachmentsResponse> getPostAttachmentsMethod;
    if ((getPostAttachmentsMethod = QueryGrpc.getPostAttachmentsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPostAttachmentsMethod = QueryGrpc.getPostAttachmentsMethod) == null) {
          QueryGrpc.getPostAttachmentsMethod = getPostAttachmentsMethod =
              io.grpc.MethodDescriptor.<com.desmos.posts.v3.QueryProto.QueryPostAttachmentsRequest, com.desmos.posts.v3.QueryProto.QueryPostAttachmentsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PostAttachments"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.posts.v3.QueryProto.QueryPostAttachmentsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.posts.v3.QueryProto.QueryPostAttachmentsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PostAttachments"))
              .build();
        }
      }
    }
    return getPostAttachmentsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.posts.v3.QueryProto.QueryPollAnswersRequest,
      com.desmos.posts.v3.QueryProto.QueryPollAnswersResponse> getPollAnswersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PollAnswers",
      requestType = com.desmos.posts.v3.QueryProto.QueryPollAnswersRequest.class,
      responseType = com.desmos.posts.v3.QueryProto.QueryPollAnswersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.posts.v3.QueryProto.QueryPollAnswersRequest,
      com.desmos.posts.v3.QueryProto.QueryPollAnswersResponse> getPollAnswersMethod() {
    io.grpc.MethodDescriptor<com.desmos.posts.v3.QueryProto.QueryPollAnswersRequest, com.desmos.posts.v3.QueryProto.QueryPollAnswersResponse> getPollAnswersMethod;
    if ((getPollAnswersMethod = QueryGrpc.getPollAnswersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPollAnswersMethod = QueryGrpc.getPollAnswersMethod) == null) {
          QueryGrpc.getPollAnswersMethod = getPollAnswersMethod =
              io.grpc.MethodDescriptor.<com.desmos.posts.v3.QueryProto.QueryPollAnswersRequest, com.desmos.posts.v3.QueryProto.QueryPollAnswersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PollAnswers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.posts.v3.QueryProto.QueryPollAnswersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.posts.v3.QueryProto.QueryPollAnswersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PollAnswers"))
              .build();
        }
      }
    }
    return getPollAnswersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.posts.v3.QueryProto.QueryParamsRequest,
      com.desmos.posts.v3.QueryProto.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = com.desmos.posts.v3.QueryProto.QueryParamsRequest.class,
      responseType = com.desmos.posts.v3.QueryProto.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.posts.v3.QueryProto.QueryParamsRequest,
      com.desmos.posts.v3.QueryProto.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<com.desmos.posts.v3.QueryProto.QueryParamsRequest, com.desmos.posts.v3.QueryProto.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<com.desmos.posts.v3.QueryProto.QueryParamsRequest, com.desmos.posts.v3.QueryProto.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.posts.v3.QueryProto.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.posts.v3.QueryProto.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
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
   * Query defines the gRPC querier service
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * SubspacePosts queries all the posts inside a given subspace
     * </pre>
     */
    default void subspacePosts(com.desmos.posts.v3.QueryProto.QuerySubspacePostsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.posts.v3.QueryProto.QuerySubspacePostsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubspacePostsMethod(), responseObserver);
    }

    /**
     * <pre>
     * SectionPosts queries all the posts inside a given section
     * </pre>
     */
    default void sectionPosts(com.desmos.posts.v3.QueryProto.QuerySectionPostsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.posts.v3.QueryProto.QuerySectionPostsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSectionPostsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Post queries for a single post inside a given subspace
     * </pre>
     */
    default void post(com.desmos.posts.v3.QueryProto.QueryPostRequest request,
        io.grpc.stub.StreamObserver<com.desmos.posts.v3.QueryProto.QueryPostResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPostMethod(), responseObserver);
    }

    /**
     * <pre>
     * PostAttachments queries the attachments of the post having the given id
     * </pre>
     */
    default void postAttachments(com.desmos.posts.v3.QueryProto.QueryPostAttachmentsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.posts.v3.QueryProto.QueryPostAttachmentsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPostAttachmentsMethod(), responseObserver);
    }

    /**
     * <pre>
     * PollAnswers queries the answers for the poll having the given id
     * </pre>
     */
    default void pollAnswers(com.desmos.posts.v3.QueryProto.QueryPollAnswersRequest request,
        io.grpc.stub.StreamObserver<com.desmos.posts.v3.QueryProto.QueryPollAnswersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPollAnswersMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries the module parameters
     * </pre>
     */
    default void params(com.desmos.posts.v3.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.posts.v3.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Query.
   * <pre>
   * Query defines the gRPC querier service
   * </pre>
   */
  public static abstract class QueryImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return QueryGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service
   * </pre>
   */
  public static final class QueryStub
      extends io.grpc.stub.AbstractAsyncStub<QueryStub> {
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
     * SubspacePosts queries all the posts inside a given subspace
     * </pre>
     */
    public void subspacePosts(com.desmos.posts.v3.QueryProto.QuerySubspacePostsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.posts.v3.QueryProto.QuerySubspacePostsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSubspacePostsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SectionPosts queries all the posts inside a given section
     * </pre>
     */
    public void sectionPosts(com.desmos.posts.v3.QueryProto.QuerySectionPostsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.posts.v3.QueryProto.QuerySectionPostsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSectionPostsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Post queries for a single post inside a given subspace
     * </pre>
     */
    public void post(com.desmos.posts.v3.QueryProto.QueryPostRequest request,
        io.grpc.stub.StreamObserver<com.desmos.posts.v3.QueryProto.QueryPostResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PostAttachments queries the attachments of the post having the given id
     * </pre>
     */
    public void postAttachments(com.desmos.posts.v3.QueryProto.QueryPostAttachmentsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.posts.v3.QueryProto.QueryPostAttachmentsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPostAttachmentsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PollAnswers queries the answers for the poll having the given id
     * </pre>
     */
    public void pollAnswers(com.desmos.posts.v3.QueryProto.QueryPollAnswersRequest request,
        io.grpc.stub.StreamObserver<com.desmos.posts.v3.QueryProto.QueryPollAnswersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPollAnswersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries the module parameters
     * </pre>
     */
    public void params(com.desmos.posts.v3.QueryProto.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<com.desmos.posts.v3.QueryProto.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service
   * </pre>
   */
  public static final class QueryBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<QueryBlockingStub> {
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
     * SubspacePosts queries all the posts inside a given subspace
     * </pre>
     */
    public com.desmos.posts.v3.QueryProto.QuerySubspacePostsResponse subspacePosts(com.desmos.posts.v3.QueryProto.QuerySubspacePostsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSubspacePostsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SectionPosts queries all the posts inside a given section
     * </pre>
     */
    public com.desmos.posts.v3.QueryProto.QuerySectionPostsResponse sectionPosts(com.desmos.posts.v3.QueryProto.QuerySectionPostsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSectionPostsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Post queries for a single post inside a given subspace
     * </pre>
     */
    public com.desmos.posts.v3.QueryProto.QueryPostResponse post(com.desmos.posts.v3.QueryProto.QueryPostRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPostMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PostAttachments queries the attachments of the post having the given id
     * </pre>
     */
    public com.desmos.posts.v3.QueryProto.QueryPostAttachmentsResponse postAttachments(com.desmos.posts.v3.QueryProto.QueryPostAttachmentsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPostAttachmentsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PollAnswers queries the answers for the poll having the given id
     * </pre>
     */
    public com.desmos.posts.v3.QueryProto.QueryPollAnswersResponse pollAnswers(com.desmos.posts.v3.QueryProto.QueryPollAnswersRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPollAnswersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries the module parameters
     * </pre>
     */
    public com.desmos.posts.v3.QueryProto.QueryParamsResponse params(com.desmos.posts.v3.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Query.
   * <pre>
   * Query defines the gRPC querier service
   * </pre>
   */
  public static final class QueryFutureStub
      extends io.grpc.stub.AbstractFutureStub<QueryFutureStub> {
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
     * SubspacePosts queries all the posts inside a given subspace
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.posts.v3.QueryProto.QuerySubspacePostsResponse> subspacePosts(
        com.desmos.posts.v3.QueryProto.QuerySubspacePostsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSubspacePostsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SectionPosts queries all the posts inside a given section
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.posts.v3.QueryProto.QuerySectionPostsResponse> sectionPosts(
        com.desmos.posts.v3.QueryProto.QuerySectionPostsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSectionPostsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Post queries for a single post inside a given subspace
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.posts.v3.QueryProto.QueryPostResponse> post(
        com.desmos.posts.v3.QueryProto.QueryPostRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPostMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PostAttachments queries the attachments of the post having the given id
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.posts.v3.QueryProto.QueryPostAttachmentsResponse> postAttachments(
        com.desmos.posts.v3.QueryProto.QueryPostAttachmentsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPostAttachmentsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PollAnswers queries the answers for the poll having the given id
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.posts.v3.QueryProto.QueryPollAnswersResponse> pollAnswers(
        com.desmos.posts.v3.QueryProto.QueryPollAnswersRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPollAnswersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries the module parameters
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.posts.v3.QueryProto.QueryParamsResponse> params(
        com.desmos.posts.v3.QueryProto.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SUBSPACE_POSTS = 0;
  private static final int METHODID_SECTION_POSTS = 1;
  private static final int METHODID_POST = 2;
  private static final int METHODID_POST_ATTACHMENTS = 3;
  private static final int METHODID_POLL_ANSWERS = 4;
  private static final int METHODID_PARAMS = 5;

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
        case METHODID_SUBSPACE_POSTS:
          serviceImpl.subspacePosts((com.desmos.posts.v3.QueryProto.QuerySubspacePostsRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.posts.v3.QueryProto.QuerySubspacePostsResponse>) responseObserver);
          break;
        case METHODID_SECTION_POSTS:
          serviceImpl.sectionPosts((com.desmos.posts.v3.QueryProto.QuerySectionPostsRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.posts.v3.QueryProto.QuerySectionPostsResponse>) responseObserver);
          break;
        case METHODID_POST:
          serviceImpl.post((com.desmos.posts.v3.QueryProto.QueryPostRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.posts.v3.QueryProto.QueryPostResponse>) responseObserver);
          break;
        case METHODID_POST_ATTACHMENTS:
          serviceImpl.postAttachments((com.desmos.posts.v3.QueryProto.QueryPostAttachmentsRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.posts.v3.QueryProto.QueryPostAttachmentsResponse>) responseObserver);
          break;
        case METHODID_POLL_ANSWERS:
          serviceImpl.pollAnswers((com.desmos.posts.v3.QueryProto.QueryPollAnswersRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.posts.v3.QueryProto.QueryPollAnswersResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((com.desmos.posts.v3.QueryProto.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<com.desmos.posts.v3.QueryProto.QueryParamsResponse>) responseObserver);
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
          getSubspacePostsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.posts.v3.QueryProto.QuerySubspacePostsRequest,
              com.desmos.posts.v3.QueryProto.QuerySubspacePostsResponse>(
                service, METHODID_SUBSPACE_POSTS)))
        .addMethod(
          getSectionPostsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.posts.v3.QueryProto.QuerySectionPostsRequest,
              com.desmos.posts.v3.QueryProto.QuerySectionPostsResponse>(
                service, METHODID_SECTION_POSTS)))
        .addMethod(
          getPostMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.posts.v3.QueryProto.QueryPostRequest,
              com.desmos.posts.v3.QueryProto.QueryPostResponse>(
                service, METHODID_POST)))
        .addMethod(
          getPostAttachmentsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.posts.v3.QueryProto.QueryPostAttachmentsRequest,
              com.desmos.posts.v3.QueryProto.QueryPostAttachmentsResponse>(
                service, METHODID_POST_ATTACHMENTS)))
        .addMethod(
          getPollAnswersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.posts.v3.QueryProto.QueryPollAnswersRequest,
              com.desmos.posts.v3.QueryProto.QueryPollAnswersResponse>(
                service, METHODID_POLL_ANSWERS)))
        .addMethod(
          getParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.posts.v3.QueryProto.QueryParamsRequest,
              com.desmos.posts.v3.QueryProto.QueryParamsResponse>(
                service, METHODID_PARAMS)))
        .build();
  }

  private static abstract class QueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.desmos.posts.v3.QueryProto.getDescriptor();
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
              .addMethod(getSubspacePostsMethod())
              .addMethod(getSectionPostsMethod())
              .addMethod(getPostMethod())
              .addMethod(getPostAttachmentsMethod())
              .addMethod(getPollAnswersMethod())
              .addMethod(getParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
