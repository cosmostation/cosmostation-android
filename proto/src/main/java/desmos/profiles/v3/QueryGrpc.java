package desmos.profiles.v3;

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
    comments = "Source: desmos/profiles/v3/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "desmos.profiles.v3.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v3.QueryProfile.QueryProfileRequest,
      desmos.profiles.v3.QueryProfile.QueryProfileResponse> getProfileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Profile",
      requestType = desmos.profiles.v3.QueryProfile.QueryProfileRequest.class,
      responseType = desmos.profiles.v3.QueryProfile.QueryProfileResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v3.QueryProfile.QueryProfileRequest,
      desmos.profiles.v3.QueryProfile.QueryProfileResponse> getProfileMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v3.QueryProfile.QueryProfileRequest, desmos.profiles.v3.QueryProfile.QueryProfileResponse> getProfileMethod;
    if ((getProfileMethod = QueryGrpc.getProfileMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProfileMethod = QueryGrpc.getProfileMethod) == null) {
          QueryGrpc.getProfileMethod = getProfileMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v3.QueryProfile.QueryProfileRequest, desmos.profiles.v3.QueryProfile.QueryProfileResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Profile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.QueryProfile.QueryProfileRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.QueryProfile.QueryProfileResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Profile"))
              .build();
        }
      }
    }
    return getProfileMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest,
      desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse> getIncomingDTagTransferRequestsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "IncomingDTagTransferRequests",
      requestType = desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest.class,
      responseType = desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest,
      desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse> getIncomingDTagTransferRequestsMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest, desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse> getIncomingDTagTransferRequestsMethod;
    if ((getIncomingDTagTransferRequestsMethod = QueryGrpc.getIncomingDTagTransferRequestsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getIncomingDTagTransferRequestsMethod = QueryGrpc.getIncomingDTagTransferRequestsMethod) == null) {
          QueryGrpc.getIncomingDTagTransferRequestsMethod = getIncomingDTagTransferRequestsMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest, desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IncomingDTagTransferRequests"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("IncomingDTagTransferRequests"))
              .build();
        }
      }
    }
    return getIncomingDTagTransferRequestsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v3.QueryChainLinks.QueryChainLinksRequest,
      desmos.profiles.v3.QueryChainLinks.QueryChainLinksResponse> getChainLinksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ChainLinks",
      requestType = desmos.profiles.v3.QueryChainLinks.QueryChainLinksRequest.class,
      responseType = desmos.profiles.v3.QueryChainLinks.QueryChainLinksResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v3.QueryChainLinks.QueryChainLinksRequest,
      desmos.profiles.v3.QueryChainLinks.QueryChainLinksResponse> getChainLinksMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v3.QueryChainLinks.QueryChainLinksRequest, desmos.profiles.v3.QueryChainLinks.QueryChainLinksResponse> getChainLinksMethod;
    if ((getChainLinksMethod = QueryGrpc.getChainLinksMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getChainLinksMethod = QueryGrpc.getChainLinksMethod) == null) {
          QueryGrpc.getChainLinksMethod = getChainLinksMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v3.QueryChainLinks.QueryChainLinksRequest, desmos.profiles.v3.QueryChainLinks.QueryChainLinksResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ChainLinks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.QueryChainLinks.QueryChainLinksRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.QueryChainLinks.QueryChainLinksResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ChainLinks"))
              .build();
        }
      }
    }
    return getChainLinksMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersRequest,
      desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersResponse> getChainLinkOwnersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ChainLinkOwners",
      requestType = desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersRequest.class,
      responseType = desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersRequest,
      desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersResponse> getChainLinkOwnersMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersRequest, desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersResponse> getChainLinkOwnersMethod;
    if ((getChainLinkOwnersMethod = QueryGrpc.getChainLinkOwnersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getChainLinkOwnersMethod = QueryGrpc.getChainLinkOwnersMethod) == null) {
          QueryGrpc.getChainLinkOwnersMethod = getChainLinkOwnersMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersRequest, desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ChainLinkOwners"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ChainLinkOwners"))
              .build();
        }
      }
    }
    return getChainLinkOwnersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesRequest,
      desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesResponse> getDefaultExternalAddressesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DefaultExternalAddresses",
      requestType = desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesRequest.class,
      responseType = desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesRequest,
      desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesResponse> getDefaultExternalAddressesMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesRequest, desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesResponse> getDefaultExternalAddressesMethod;
    if ((getDefaultExternalAddressesMethod = QueryGrpc.getDefaultExternalAddressesMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDefaultExternalAddressesMethod = QueryGrpc.getDefaultExternalAddressesMethod) == null) {
          QueryGrpc.getDefaultExternalAddressesMethod = getDefaultExternalAddressesMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesRequest, desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DefaultExternalAddresses"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DefaultExternalAddresses"))
              .build();
        }
      }
    }
    return getDefaultExternalAddressesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksRequest,
      desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksResponse> getApplicationLinksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ApplicationLinks",
      requestType = desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksRequest.class,
      responseType = desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksRequest,
      desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksResponse> getApplicationLinksMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksRequest, desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksResponse> getApplicationLinksMethod;
    if ((getApplicationLinksMethod = QueryGrpc.getApplicationLinksMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getApplicationLinksMethod = QueryGrpc.getApplicationLinksMethod) == null) {
          QueryGrpc.getApplicationLinksMethod = getApplicationLinksMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksRequest, desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ApplicationLinks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ApplicationLinks"))
              .build();
        }
      }
    }
    return getApplicationLinksMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDRequest,
      desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDResponse> getApplicationLinkByClientIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ApplicationLinkByClientID",
      requestType = desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDRequest.class,
      responseType = desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDRequest,
      desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDResponse> getApplicationLinkByClientIDMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDRequest, desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDResponse> getApplicationLinkByClientIDMethod;
    if ((getApplicationLinkByClientIDMethod = QueryGrpc.getApplicationLinkByClientIDMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getApplicationLinkByClientIDMethod = QueryGrpc.getApplicationLinkByClientIDMethod) == null) {
          QueryGrpc.getApplicationLinkByClientIDMethod = getApplicationLinkByClientIDMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDRequest, desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ApplicationLinkByClientID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ApplicationLinkByClientID"))
              .build();
        }
      }
    }
    return getApplicationLinkByClientIDMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersRequest,
      desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersResponse> getApplicationLinkOwnersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ApplicationLinkOwners",
      requestType = desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersRequest.class,
      responseType = desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersRequest,
      desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersResponse> getApplicationLinkOwnersMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersRequest, desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersResponse> getApplicationLinkOwnersMethod;
    if ((getApplicationLinkOwnersMethod = QueryGrpc.getApplicationLinkOwnersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getApplicationLinkOwnersMethod = QueryGrpc.getApplicationLinkOwnersMethod) == null) {
          QueryGrpc.getApplicationLinkOwnersMethod = getApplicationLinkOwnersMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersRequest, desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ApplicationLinkOwners"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ApplicationLinkOwners"))
              .build();
        }
      }
    }
    return getApplicationLinkOwnersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.profiles.v3.QueryParams.QueryParamsRequest,
      desmos.profiles.v3.QueryParams.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = desmos.profiles.v3.QueryParams.QueryParamsRequest.class,
      responseType = desmos.profiles.v3.QueryParams.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.profiles.v3.QueryParams.QueryParamsRequest,
      desmos.profiles.v3.QueryParams.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<desmos.profiles.v3.QueryParams.QueryParamsRequest, desmos.profiles.v3.QueryParams.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<desmos.profiles.v3.QueryParams.QueryParamsRequest, desmos.profiles.v3.QueryParams.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.QueryParams.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.profiles.v3.QueryParams.QueryParamsResponse.getDefaultInstance()))
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
    public void profile(desmos.profiles.v3.QueryProfile.QueryProfileRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryProfile.QueryProfileResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProfileMethod(), responseObserver);
    }

    /**
     * <pre>
     * IncomingDTagTransferRequests queries all the DTag transfers requests that
     * have been made towards the user with the given address
     * </pre>
     */
    public void incomingDTagTransferRequests(desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getIncomingDTagTransferRequestsMethod(), responseObserver);
    }

    /**
     * <pre>
     * ChainLinks queries the chain links associated to the given user, if
     * provided. Otherwise it queries all the chain links stored.
     * </pre>
     */
    public void chainLinks(desmos.profiles.v3.QueryChainLinks.QueryChainLinksRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryChainLinks.QueryChainLinksResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getChainLinksMethod(), responseObserver);
    }

    /**
     * <pre>
     * ChainLinkOwners queries for the owners of chain links, optionally searching
     * for a specific chain name and external address
     * </pre>
     */
    public void chainLinkOwners(desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getChainLinkOwnersMethod(), responseObserver);
    }

    /**
     * <pre>
     * DefaultExternalAddresses queries the default addresses associated to the
     * given user and (optionally) chain name
     * </pre>
     */
    public void defaultExternalAddresses(desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDefaultExternalAddressesMethod(), responseObserver);
    }

    /**
     * <pre>
     * ApplicationLinks queries the applications links associated to the given
     * user, if provided. Otherwise, it queries all the application links stored.
     * </pre>
     */
    public void applicationLinks(desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getApplicationLinksMethod(), responseObserver);
    }

    /**
     * <pre>
     * ApplicationLinkByClientID queries a single application link for a given
     * client id.
     * </pre>
     */
    public void applicationLinkByClientID(desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getApplicationLinkByClientIDMethod(), responseObserver);
    }

    /**
     * <pre>
     * ApplicationLinkOwners queries for the owners of applications links,
     * optionally searching for a specific application and username.
     * </pre>
     */
    public void applicationLinkOwners(desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getApplicationLinkOwnersMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries the profiles module params
     * </pre>
     */
    public void params(desmos.profiles.v3.QueryParams.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryParams.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getProfileMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v3.QueryProfile.QueryProfileRequest,
                desmos.profiles.v3.QueryProfile.QueryProfileResponse>(
                  this, METHODID_PROFILE)))
          .addMethod(
            getIncomingDTagTransferRequestsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest,
                desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse>(
                  this, METHODID_INCOMING_DTAG_TRANSFER_REQUESTS)))
          .addMethod(
            getChainLinksMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v3.QueryChainLinks.QueryChainLinksRequest,
                desmos.profiles.v3.QueryChainLinks.QueryChainLinksResponse>(
                  this, METHODID_CHAIN_LINKS)))
          .addMethod(
            getChainLinkOwnersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersRequest,
                desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersResponse>(
                  this, METHODID_CHAIN_LINK_OWNERS)))
          .addMethod(
            getDefaultExternalAddressesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesRequest,
                desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesResponse>(
                  this, METHODID_DEFAULT_EXTERNAL_ADDRESSES)))
          .addMethod(
            getApplicationLinksMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksRequest,
                desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksResponse>(
                  this, METHODID_APPLICATION_LINKS)))
          .addMethod(
            getApplicationLinkByClientIDMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDRequest,
                desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDResponse>(
                  this, METHODID_APPLICATION_LINK_BY_CLIENT_ID)))
          .addMethod(
            getApplicationLinkOwnersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersRequest,
                desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersResponse>(
                  this, METHODID_APPLICATION_LINK_OWNERS)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.profiles.v3.QueryParams.QueryParamsRequest,
                desmos.profiles.v3.QueryParams.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
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
    public void profile(desmos.profiles.v3.QueryProfile.QueryProfileRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryProfile.QueryProfileResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProfileMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * IncomingDTagTransferRequests queries all the DTag transfers requests that
     * have been made towards the user with the given address
     * </pre>
     */
    public void incomingDTagTransferRequests(desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIncomingDTagTransferRequestsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ChainLinks queries the chain links associated to the given user, if
     * provided. Otherwise it queries all the chain links stored.
     * </pre>
     */
    public void chainLinks(desmos.profiles.v3.QueryChainLinks.QueryChainLinksRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryChainLinks.QueryChainLinksResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getChainLinksMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ChainLinkOwners queries for the owners of chain links, optionally searching
     * for a specific chain name and external address
     * </pre>
     */
    public void chainLinkOwners(desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getChainLinkOwnersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DefaultExternalAddresses queries the default addresses associated to the
     * given user and (optionally) chain name
     * </pre>
     */
    public void defaultExternalAddresses(desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDefaultExternalAddressesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ApplicationLinks queries the applications links associated to the given
     * user, if provided. Otherwise, it queries all the application links stored.
     * </pre>
     */
    public void applicationLinks(desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getApplicationLinksMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ApplicationLinkByClientID queries a single application link for a given
     * client id.
     * </pre>
     */
    public void applicationLinkByClientID(desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getApplicationLinkByClientIDMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ApplicationLinkOwners queries for the owners of applications links,
     * optionally searching for a specific application and username.
     * </pre>
     */
    public void applicationLinkOwners(desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getApplicationLinkOwnersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries the profiles module params
     * </pre>
     */
    public void params(desmos.profiles.v3.QueryParams.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryParams.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
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
    public desmos.profiles.v3.QueryProfile.QueryProfileResponse profile(desmos.profiles.v3.QueryProfile.QueryProfileRequest request) {
      return blockingUnaryCall(
          getChannel(), getProfileMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * IncomingDTagTransferRequests queries all the DTag transfers requests that
     * have been made towards the user with the given address
     * </pre>
     */
    public desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse incomingDTagTransferRequests(desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest request) {
      return blockingUnaryCall(
          getChannel(), getIncomingDTagTransferRequestsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ChainLinks queries the chain links associated to the given user, if
     * provided. Otherwise it queries all the chain links stored.
     * </pre>
     */
    public desmos.profiles.v3.QueryChainLinks.QueryChainLinksResponse chainLinks(desmos.profiles.v3.QueryChainLinks.QueryChainLinksRequest request) {
      return blockingUnaryCall(
          getChannel(), getChainLinksMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ChainLinkOwners queries for the owners of chain links, optionally searching
     * for a specific chain name and external address
     * </pre>
     */
    public desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersResponse chainLinkOwners(desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersRequest request) {
      return blockingUnaryCall(
          getChannel(), getChainLinkOwnersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DefaultExternalAddresses queries the default addresses associated to the
     * given user and (optionally) chain name
     * </pre>
     */
    public desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesResponse defaultExternalAddresses(desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesRequest request) {
      return blockingUnaryCall(
          getChannel(), getDefaultExternalAddressesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ApplicationLinks queries the applications links associated to the given
     * user, if provided. Otherwise, it queries all the application links stored.
     * </pre>
     */
    public desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksResponse applicationLinks(desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksRequest request) {
      return blockingUnaryCall(
          getChannel(), getApplicationLinksMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ApplicationLinkByClientID queries a single application link for a given
     * client id.
     * </pre>
     */
    public desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDResponse applicationLinkByClientID(desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDRequest request) {
      return blockingUnaryCall(
          getChannel(), getApplicationLinkByClientIDMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ApplicationLinkOwners queries for the owners of applications links,
     * optionally searching for a specific application and username.
     * </pre>
     */
    public desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersResponse applicationLinkOwners(desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersRequest request) {
      return blockingUnaryCall(
          getChannel(), getApplicationLinkOwnersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries the profiles module params
     * </pre>
     */
    public desmos.profiles.v3.QueryParams.QueryParamsResponse params(desmos.profiles.v3.QueryParams.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v3.QueryProfile.QueryProfileResponse> profile(
        desmos.profiles.v3.QueryProfile.QueryProfileRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getProfileMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * IncomingDTagTransferRequests queries all the DTag transfers requests that
     * have been made towards the user with the given address
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse> incomingDTagTransferRequests(
        desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getIncomingDTagTransferRequestsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ChainLinks queries the chain links associated to the given user, if
     * provided. Otherwise it queries all the chain links stored.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v3.QueryChainLinks.QueryChainLinksResponse> chainLinks(
        desmos.profiles.v3.QueryChainLinks.QueryChainLinksRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getChainLinksMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ChainLinkOwners queries for the owners of chain links, optionally searching
     * for a specific chain name and external address
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersResponse> chainLinkOwners(
        desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getChainLinkOwnersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DefaultExternalAddresses queries the default addresses associated to the
     * given user and (optionally) chain name
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesResponse> defaultExternalAddresses(
        desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDefaultExternalAddressesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ApplicationLinks queries the applications links associated to the given
     * user, if provided. Otherwise, it queries all the application links stored.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksResponse> applicationLinks(
        desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getApplicationLinksMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ApplicationLinkByClientID queries a single application link for a given
     * client id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDResponse> applicationLinkByClientID(
        desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getApplicationLinkByClientIDMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ApplicationLinkOwners queries for the owners of applications links,
     * optionally searching for a specific application and username.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersResponse> applicationLinkOwners(
        desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getApplicationLinkOwnersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries the profiles module params
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.profiles.v3.QueryParams.QueryParamsResponse> params(
        desmos.profiles.v3.QueryParams.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PROFILE = 0;
  private static final int METHODID_INCOMING_DTAG_TRANSFER_REQUESTS = 1;
  private static final int METHODID_CHAIN_LINKS = 2;
  private static final int METHODID_CHAIN_LINK_OWNERS = 3;
  private static final int METHODID_DEFAULT_EXTERNAL_ADDRESSES = 4;
  private static final int METHODID_APPLICATION_LINKS = 5;
  private static final int METHODID_APPLICATION_LINK_BY_CLIENT_ID = 6;
  private static final int METHODID_APPLICATION_LINK_OWNERS = 7;
  private static final int METHODID_PARAMS = 8;

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
          serviceImpl.profile((desmos.profiles.v3.QueryProfile.QueryProfileRequest) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryProfile.QueryProfileResponse>) responseObserver);
          break;
        case METHODID_INCOMING_DTAG_TRANSFER_REQUESTS:
          serviceImpl.incomingDTagTransferRequests((desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsRequest) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryDtagRequests.QueryIncomingDTagTransferRequestsResponse>) responseObserver);
          break;
        case METHODID_CHAIN_LINKS:
          serviceImpl.chainLinks((desmos.profiles.v3.QueryChainLinks.QueryChainLinksRequest) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryChainLinks.QueryChainLinksResponse>) responseObserver);
          break;
        case METHODID_CHAIN_LINK_OWNERS:
          serviceImpl.chainLinkOwners((desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersRequest) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryChainLinks.QueryChainLinkOwnersResponse>) responseObserver);
          break;
        case METHODID_DEFAULT_EXTERNAL_ADDRESSES:
          serviceImpl.defaultExternalAddresses((desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesRequest) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryChainLinks.QueryDefaultExternalAddressesResponse>) responseObserver);
          break;
        case METHODID_APPLICATION_LINKS:
          serviceImpl.applicationLinks((desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksRequest) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinksResponse>) responseObserver);
          break;
        case METHODID_APPLICATION_LINK_BY_CLIENT_ID:
          serviceImpl.applicationLinkByClientID((desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDRequest) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkByClientIDResponse>) responseObserver);
          break;
        case METHODID_APPLICATION_LINK_OWNERS:
          serviceImpl.applicationLinkOwners((desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersRequest) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryAppLinks.QueryApplicationLinkOwnersResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((desmos.profiles.v3.QueryParams.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<desmos.profiles.v3.QueryParams.QueryParamsResponse>) responseObserver);
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
      return desmos.profiles.v3.QueryOuterClass.getDescriptor();
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
              .addMethod(getChainLinksMethod())
              .addMethod(getChainLinkOwnersMethod())
              .addMethod(getDefaultExternalAddressesMethod())
              .addMethod(getApplicationLinksMethod())
              .addMethod(getApplicationLinkByClientIDMethod())
              .addMethod(getApplicationLinkOwnersMethod())
              .addMethod(getParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
