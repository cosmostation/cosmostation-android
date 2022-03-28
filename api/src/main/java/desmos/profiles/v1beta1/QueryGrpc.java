package desmos.profiles.v1beta1;

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
    comments = "Source: desmos/profiles/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "desmos.profiles.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryProfile.QueryProfileRequest,
      desmos.profiles.v1beta1.QueryProfile.QueryProfileResponse> getProfileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Profile",
      requestType = desmos.profiles.v1beta1.QueryProfile.QueryProfileRequest.class,
      responseType = desmos.profiles.v1beta1.QueryProfile.QueryProfileResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryProfile.QueryProfileRequest,
      desmos.profiles.v1beta1.QueryProfile.QueryProfileResponse> getProfileMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryProfile.QueryProfileRequest, desmos.profiles.v1beta1.QueryProfile.QueryProfileResponse> getProfileMethod;
    if ((getProfileMethod = QueryGrpc.getProfileMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProfileMethod = QueryGrpc.getProfileMethod) == null) {
          QueryGrpc.getProfileMethod = getProfileMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v1beta1.QueryProfile.QueryProfileRequest, desmos.profiles.v1beta1.QueryProfile.QueryProfileResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Profile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v1beta1.QueryProfile.QueryProfileRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v1beta1.QueryProfile.QueryProfileResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Profile"))
              .build();
        }
      }
    }
    return getProfileMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest,
      desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse> getIncomingDTagTransferRequestsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "IncomingDTagTransferRequests",
      requestType = desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest.class,
      responseType = desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest,
      desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse> getIncomingDTagTransferRequestsMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest, desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse> getIncomingDTagTransferRequestsMethod;
    if ((getIncomingDTagTransferRequestsMethod = QueryGrpc.getIncomingDTagTransferRequestsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getIncomingDTagTransferRequestsMethod = QueryGrpc.getIncomingDTagTransferRequestsMethod) == null) {
          QueryGrpc.getIncomingDTagTransferRequestsMethod = getIncomingDTagTransferRequestsMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest, desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IncomingDTagTransferRequests"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("IncomingDTagTransferRequests"))
              .build();
        }
      }
    }
    return getIncomingDTagTransferRequestsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryParams.QueryParamsRequest,
      desmos.profiles.v1beta1.QueryParams.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = desmos.profiles.v1beta1.QueryParams.QueryParamsRequest.class,
      responseType = desmos.profiles.v1beta1.QueryParams.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryParams.QueryParamsRequest,
      desmos.profiles.v1beta1.QueryParams.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryParams.QueryParamsRequest, desmos.profiles.v1beta1.QueryParams.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v1beta1.QueryParams.QueryParamsRequest, desmos.profiles.v1beta1.QueryParams.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v1beta1.QueryParams.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v1beta1.QueryParams.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsRequest,
      desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsResponse> getRelationshipsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Relationships",
      requestType = desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsRequest.class,
      responseType = desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsRequest,
      desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsResponse> getRelationshipsMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsRequest, desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsResponse> getRelationshipsMethod;
    if ((getRelationshipsMethod = QueryGrpc.getRelationshipsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRelationshipsMethod = QueryGrpc.getRelationshipsMethod) == null) {
          QueryGrpc.getRelationshipsMethod = getRelationshipsMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsRequest, desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Relationships"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Relationships"))
              .build();
        }
      }
    }
    return getRelationshipsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryRelationships.QueryBlocksRequest,
      desmos.profiles.v1beta1.QueryRelationships.QueryBlocksResponse> getBlocksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Blocks",
      requestType = desmos.profiles.v1beta1.QueryRelationships.QueryBlocksRequest.class,
      responseType = desmos.profiles.v1beta1.QueryRelationships.QueryBlocksResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryRelationships.QueryBlocksRequest,
      desmos.profiles.v1beta1.QueryRelationships.QueryBlocksResponse> getBlocksMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryRelationships.QueryBlocksRequest, desmos.profiles.v1beta1.QueryRelationships.QueryBlocksResponse> getBlocksMethod;
    if ((getBlocksMethod = QueryGrpc.getBlocksMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBlocksMethod = QueryGrpc.getBlocksMethod) == null) {
          QueryGrpc.getBlocksMethod = getBlocksMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v1beta1.QueryRelationships.QueryBlocksRequest, desmos.profiles.v1beta1.QueryRelationships.QueryBlocksResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Blocks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v1beta1.QueryRelationships.QueryBlocksRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v1beta1.QueryRelationships.QueryBlocksResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Blocks"))
              .build();
        }
      }
    }
    return getBlocksMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksRequest,
      desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksResponse> getChainLinksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ChainLinks",
      requestType = desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksRequest.class,
      responseType = desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksRequest,
      desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksResponse> getChainLinksMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksRequest, desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksResponse> getChainLinksMethod;
    if ((getChainLinksMethod = QueryGrpc.getChainLinksMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getChainLinksMethod = QueryGrpc.getChainLinksMethod) == null) {
          QueryGrpc.getChainLinksMethod = getChainLinksMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksRequest, desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ChainLinks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ChainLinks"))
              .build();
        }
      }
    }
    return getChainLinksMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkRequest,
      desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkResponse> getUserChainLinkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UserChainLink",
      requestType = desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkRequest.class,
      responseType = desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkRequest,
      desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkResponse> getUserChainLinkMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkRequest, desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkResponse> getUserChainLinkMethod;
    if ((getUserChainLinkMethod = QueryGrpc.getUserChainLinkMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUserChainLinkMethod = QueryGrpc.getUserChainLinkMethod) == null) {
          QueryGrpc.getUserChainLinkMethod = getUserChainLinkMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkRequest, desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UserChainLink"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UserChainLink"))
              .build();
        }
      }
    }
    return getUserChainLinkMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksRequest,
      desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksResponse> getApplicationLinksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ApplicationLinks",
      requestType = desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksRequest.class,
      responseType = desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksRequest,
      desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksResponse> getApplicationLinksMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksRequest, desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksResponse> getApplicationLinksMethod;
    if ((getApplicationLinksMethod = QueryGrpc.getApplicationLinksMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getApplicationLinksMethod = QueryGrpc.getApplicationLinksMethod) == null) {
          QueryGrpc.getApplicationLinksMethod = getApplicationLinksMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksRequest, desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ApplicationLinks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ApplicationLinks"))
              .build();
        }
      }
    }
    return getApplicationLinksMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkRequest,
      desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkResponse> getUserApplicationLinkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UserApplicationLink",
      requestType = desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkRequest.class,
      responseType = desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkRequest,
      desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkResponse> getUserApplicationLinkMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkRequest, desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkResponse> getUserApplicationLinkMethod;
    if ((getUserApplicationLinkMethod = QueryGrpc.getUserApplicationLinkMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getUserApplicationLinkMethod = QueryGrpc.getUserApplicationLinkMethod) == null) {
          QueryGrpc.getUserApplicationLinkMethod = getUserApplicationLinkMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkRequest, desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UserApplicationLink"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("UserApplicationLink"))
              .build();
        }
      }
    }
    return getUserApplicationLinkMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDRequest,
      desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDResponse> getApplicationLinkByClientIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ApplicationLinkByClientID",
      requestType = desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDRequest.class,
      responseType = desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDRequest,
      desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDResponse> getApplicationLinkByClientIDMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDRequest, desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDResponse> getApplicationLinkByClientIDMethod;
    if ((getApplicationLinkByClientIDMethod = QueryGrpc.getApplicationLinkByClientIDMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getApplicationLinkByClientIDMethod = QueryGrpc.getApplicationLinkByClientIDMethod) == null) {
          QueryGrpc.getApplicationLinkByClientIDMethod = getApplicationLinkByClientIDMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDRequest, desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ApplicationLinkByClientID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ApplicationLinkByClientID"))
              .build();
        }
      }
    }
    return getApplicationLinkByClientIDMethod;
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
     * Profile queries the profile of a specific user given their DTag or address.
     * If the queried user does not have a profile, the returned response will
     * contain a null profile.
     * </pre>
     */
    public void profile(desmos.profiles.v1beta1.QueryProfile.QueryProfileRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryProfile.QueryProfileResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProfileMethod(), responseObserver);
    }

    /**
     * <pre>
     * IncomingDTagTransferRequests queries all the DTag transfers requests that
     * have been made towards the user with the given address
     * </pre>
     */
    public void incomingDTagTransferRequests(desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getIncomingDTagTransferRequestsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries the profiles module params
     * </pre>
     */
    public void params(desmos.profiles.v1beta1.QueryParams.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryParams.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Relationships queries all relationships for the given user, if provided.
     * Otherwise, it queries all the relationships stored.
     * </pre>
     */
    public void relationships(desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRelationshipsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Blocks queries the blocks for the given user, if provided.
     * Otherwise, it queries all the stored blocks.
     * </pre>
     */
    public void blocks(desmos.profiles.v1beta1.QueryRelationships.QueryBlocksRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryRelationships.QueryBlocksResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBlocksMethod(), responseObserver);
    }

    /**
     * <pre>
     * ChainLinks queries the chain links associated to the given user, if
     * provided. Otherwise it queries all the chain links stored.
     * </pre>
     */
    public void chainLinks(desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getChainLinksMethod(), responseObserver);
    }

    /**
     * <pre>
     * UserChainLink queries the chain link for the given user, chain name and
     * target address
     * </pre>
     */
    public void userChainLink(desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUserChainLinkMethod(), responseObserver);
    }

    /**
     * <pre>
     * ApplicationLinks queries the applications links associated to the given
     * user, if provided. Otherwise, it queries all the application links stored.
     * </pre>
     */
    public void applicationLinks(desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getApplicationLinksMethod(), responseObserver);
    }

    /**
     * <pre>
     * UserApplicationLinks queries a single application link for a given user,
     * searching via the application name and username
     * </pre>
     */
    public void userApplicationLink(desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUserApplicationLinkMethod(), responseObserver);
    }

    /**
     * <pre>
     * ApplicationLinkByClientID queries a single application link for a given
     * client id.
     * </pre>
     */
    public void applicationLinkByClientID(desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getApplicationLinkByClientIDMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getProfileMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v1beta1.QueryProfile.QueryProfileRequest,
                desmos.profiles.v1beta1.QueryProfile.QueryProfileResponse>(
                  this, METHODID_PROFILE)))
          .addMethod(
            getIncomingDTagTransferRequestsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest,
                desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse>(
                  this, METHODID_INCOMING_DTAG_TRANSFER_REQUESTS)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v1beta1.QueryParams.QueryParamsRequest,
                desmos.profiles.v1beta1.QueryParams.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getRelationshipsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsRequest,
                desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsResponse>(
                  this, METHODID_RELATIONSHIPS)))
          .addMethod(
            getBlocksMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v1beta1.QueryRelationships.QueryBlocksRequest,
                desmos.profiles.v1beta1.QueryRelationships.QueryBlocksResponse>(
                  this, METHODID_BLOCKS)))
          .addMethod(
            getChainLinksMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksRequest,
                desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksResponse>(
                  this, METHODID_CHAIN_LINKS)))
          .addMethod(
            getUserChainLinkMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkRequest,
                desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkResponse>(
                  this, METHODID_USER_CHAIN_LINK)))
          .addMethod(
            getApplicationLinksMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksRequest,
                desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksResponse>(
                  this, METHODID_APPLICATION_LINKS)))
          .addMethod(
            getUserApplicationLinkMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkRequest,
                desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkResponse>(
                  this, METHODID_USER_APPLICATION_LINK)))
          .addMethod(
            getApplicationLinkByClientIDMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDRequest,
                desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDResponse>(
                  this, METHODID_APPLICATION_LINK_BY_CLIENT_ID)))
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
     * Profile queries the profile of a specific user given their DTag or address.
     * If the queried user does not have a profile, the returned response will
     * contain a null profile.
     * </pre>
     */
    public void profile(desmos.profiles.v1beta1.QueryProfile.QueryProfileRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryProfile.QueryProfileResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProfileMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * IncomingDTagTransferRequests queries all the DTag transfers requests that
     * have been made towards the user with the given address
     * </pre>
     */
    public void incomingDTagTransferRequests(desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIncomingDTagTransferRequestsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries the profiles module params
     * </pre>
     */
    public void params(desmos.profiles.v1beta1.QueryParams.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryParams.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Relationships queries all relationships for the given user, if provided.
     * Otherwise, it queries all the relationships stored.
     * </pre>
     */
    public void relationships(desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRelationshipsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Blocks queries the blocks for the given user, if provided.
     * Otherwise, it queries all the stored blocks.
     * </pre>
     */
    public void blocks(desmos.profiles.v1beta1.QueryRelationships.QueryBlocksRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryRelationships.QueryBlocksResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBlocksMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ChainLinks queries the chain links associated to the given user, if
     * provided. Otherwise it queries all the chain links stored.
     * </pre>
     */
    public void chainLinks(desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getChainLinksMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UserChainLink queries the chain link for the given user, chain name and
     * target address
     * </pre>
     */
    public void userChainLink(desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUserChainLinkMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ApplicationLinks queries the applications links associated to the given
     * user, if provided. Otherwise, it queries all the application links stored.
     * </pre>
     */
    public void applicationLinks(desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getApplicationLinksMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UserApplicationLinks queries a single application link for a given user,
     * searching via the application name and username
     * </pre>
     */
    public void userApplicationLink(desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUserApplicationLinkMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ApplicationLinkByClientID queries a single application link for a given
     * client id.
     * </pre>
     */
    public void applicationLinkByClientID(desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getApplicationLinkByClientIDMethod(), getCallOptions()), request, responseObserver);
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
     * Profile queries the profile of a specific user given their DTag or address.
     * If the queried user does not have a profile, the returned response will
     * contain a null profile.
     * </pre>
     */
    public desmos.profiles.v1beta1.QueryProfile.QueryProfileResponse profile(desmos.profiles.v1beta1.QueryProfile.QueryProfileRequest request) {
      return blockingUnaryCall(
          getChannel(), getProfileMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * IncomingDTagTransferRequests queries all the DTag transfers requests that
     * have been made towards the user with the given address
     * </pre>
     */
    public desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse incomingDTagTransferRequests(desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest request) {
      return blockingUnaryCall(
          getChannel(), getIncomingDTagTransferRequestsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries the profiles module params
     * </pre>
     */
    public desmos.profiles.v1beta1.QueryParams.QueryParamsResponse params(desmos.profiles.v1beta1.QueryParams.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Relationships queries all relationships for the given user, if provided.
     * Otherwise, it queries all the relationships stored.
     * </pre>
     */
    public desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsResponse relationships(desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsRequest request) {
      return blockingUnaryCall(
          getChannel(), getRelationshipsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Blocks queries the blocks for the given user, if provided.
     * Otherwise, it queries all the stored blocks.
     * </pre>
     */
    public desmos.profiles.v1beta1.QueryRelationships.QueryBlocksResponse blocks(desmos.profiles.v1beta1.QueryRelationships.QueryBlocksRequest request) {
      return blockingUnaryCall(
          getChannel(), getBlocksMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ChainLinks queries the chain links associated to the given user, if
     * provided. Otherwise it queries all the chain links stored.
     * </pre>
     */
    public desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksResponse chainLinks(desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksRequest request) {
      return blockingUnaryCall(
          getChannel(), getChainLinksMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UserChainLink queries the chain link for the given user, chain name and
     * target address
     * </pre>
     */
    public desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkResponse userChainLink(desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkRequest request) {
      return blockingUnaryCall(
          getChannel(), getUserChainLinkMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ApplicationLinks queries the applications links associated to the given
     * user, if provided. Otherwise, it queries all the application links stored.
     * </pre>
     */
    public desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksResponse applicationLinks(desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksRequest request) {
      return blockingUnaryCall(
          getChannel(), getApplicationLinksMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UserApplicationLinks queries a single application link for a given user,
     * searching via the application name and username
     * </pre>
     */
    public desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkResponse userApplicationLink(desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkRequest request) {
      return blockingUnaryCall(
          getChannel(), getUserApplicationLinkMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ApplicationLinkByClientID queries a single application link for a given
     * client id.
     * </pre>
     */
    public desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDResponse applicationLinkByClientID(desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDRequest request) {
      return blockingUnaryCall(
          getChannel(), getApplicationLinkByClientIDMethod(), getCallOptions(), request);
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
     * Profile queries the profile of a specific user given their DTag or address.
     * If the queried user does not have a profile, the returned response will
     * contain a null profile.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v1beta1.QueryProfile.QueryProfileResponse> profile(
        desmos.profiles.v1beta1.QueryProfile.QueryProfileRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getProfileMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * IncomingDTagTransferRequests queries all the DTag transfers requests that
     * have been made towards the user with the given address
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse> incomingDTagTransferRequests(
        desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getIncomingDTagTransferRequestsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries the profiles module params
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v1beta1.QueryParams.QueryParamsResponse> params(
        desmos.profiles.v1beta1.QueryParams.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Relationships queries all relationships for the given user, if provided.
     * Otherwise, it queries all the relationships stored.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsResponse> relationships(
        desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRelationshipsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Blocks queries the blocks for the given user, if provided.
     * Otherwise, it queries all the stored blocks.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v1beta1.QueryRelationships.QueryBlocksResponse> blocks(
        desmos.profiles.v1beta1.QueryRelationships.QueryBlocksRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBlocksMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ChainLinks queries the chain links associated to the given user, if
     * provided. Otherwise it queries all the chain links stored.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksResponse> chainLinks(
        desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getChainLinksMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UserChainLink queries the chain link for the given user, chain name and
     * target address
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkResponse> userChainLink(
        desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUserChainLinkMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ApplicationLinks queries the applications links associated to the given
     * user, if provided. Otherwise, it queries all the application links stored.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksResponse> applicationLinks(
        desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getApplicationLinksMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UserApplicationLinks queries a single application link for a given user,
     * searching via the application name and username
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkResponse> userApplicationLink(
        desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUserApplicationLinkMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ApplicationLinkByClientID queries a single application link for a given
     * client id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDResponse> applicationLinkByClientID(
        desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getApplicationLinkByClientIDMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PROFILE = 0;
  private static final int METHODID_INCOMING_DTAG_TRANSFER_REQUESTS = 1;
  private static final int METHODID_PARAMS = 2;
  private static final int METHODID_RELATIONSHIPS = 3;
  private static final int METHODID_BLOCKS = 4;
  private static final int METHODID_CHAIN_LINKS = 5;
  private static final int METHODID_USER_CHAIN_LINK = 6;
  private static final int METHODID_APPLICATION_LINKS = 7;
  private static final int METHODID_USER_APPLICATION_LINK = 8;
  private static final int METHODID_APPLICATION_LINK_BY_CLIENT_ID = 9;

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
        case METHODID_PROFILE:
          serviceImpl.profile((desmos.profiles.v1beta1.QueryProfile.QueryProfileRequest) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryProfile.QueryProfileResponse>) responseObserver);
          break;
        case METHODID_INCOMING_DTAG_TRANSFER_REQUESTS:
          serviceImpl.incomingDTagTransferRequests((desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((desmos.profiles.v1beta1.QueryParams.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryParams.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_RELATIONSHIPS:
          serviceImpl.relationships((desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsRequest) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryRelationships.QueryRelationshipsResponse>) responseObserver);
          break;
        case METHODID_BLOCKS:
          serviceImpl.blocks((desmos.profiles.v1beta1.QueryRelationships.QueryBlocksRequest) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryRelationships.QueryBlocksResponse>) responseObserver);
          break;
        case METHODID_CHAIN_LINKS:
          serviceImpl.chainLinks((desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksRequest) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryChainLinks.QueryChainLinksResponse>) responseObserver);
          break;
        case METHODID_USER_CHAIN_LINK:
          serviceImpl.userChainLink((desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkRequest) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryChainLinks.QueryUserChainLinkResponse>) responseObserver);
          break;
        case METHODID_APPLICATION_LINKS:
          serviceImpl.applicationLinks((desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksRequest) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinksResponse>) responseObserver);
          break;
        case METHODID_USER_APPLICATION_LINK:
          serviceImpl.userApplicationLink((desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkRequest) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryAppLinks.QueryUserApplicationLinkResponse>) responseObserver);
          break;
        case METHODID_APPLICATION_LINK_BY_CLIENT_ID:
          serviceImpl.applicationLinkByClientID((desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDRequest) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v1beta1.QueryAppLinks.QueryApplicationLinkByClientIDResponse>) responseObserver);
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
      return desmos.profiles.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getProfileMethod())
              .addMethod(getIncomingDTagTransferRequestsMethod())
              .addMethod(getParamsMethod())
              .addMethod(getRelationshipsMethod())
              .addMethod(getBlocksMethod())
              .addMethod(getChainLinksMethod())
              .addMethod(getUserChainLinkMethod())
              .addMethod(getApplicationLinksMethod())
              .addMethod(getUserApplicationLinkMethod())
              .addMethod(getApplicationLinkByClientIDMethod())
              .build();
        }
      }
    }
    return result;
  }
}
