package starnamed.x.starname.v1beta1;

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
    comments = "Source: iov/starname/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "starnamed.x.starname.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainRequest,
      starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainResponse> getDomainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Domain",
      requestType = starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainRequest.class,
      responseType = starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainRequest,
      starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainResponse> getDomainMethod() {
    io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainRequest, starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainResponse> getDomainMethod;
    if ((getDomainMethod = QueryGrpc.getDomainMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDomainMethod = QueryGrpc.getDomainMethod) == null) {
          QueryGrpc.getDomainMethod = getDomainMethod =
              io.grpc.MethodDescriptor.<starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainRequest, starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Domain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Domain"))
              .build();
        }
      }
    }
    return getDomainMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsRequest,
      starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsResponse> getDomainAccountsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DomainAccounts",
      requestType = starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsRequest.class,
      responseType = starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsRequest,
      starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsResponse> getDomainAccountsMethod() {
    io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsRequest, starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsResponse> getDomainAccountsMethod;
    if ((getDomainAccountsMethod = QueryGrpc.getDomainAccountsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDomainAccountsMethod = QueryGrpc.getDomainAccountsMethod) == null) {
          QueryGrpc.getDomainAccountsMethod = getDomainAccountsMethod =
              io.grpc.MethodDescriptor.<starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsRequest, starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DomainAccounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DomainAccounts"))
              .build();
        }
      }
    }
    return getDomainAccountsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameRequest,
      starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameResponse> getStarnameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Starname",
      requestType = starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameRequest.class,
      responseType = starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameRequest,
      starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameResponse> getStarnameMethod() {
    io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameRequest, starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameResponse> getStarnameMethod;
    if ((getStarnameMethod = QueryGrpc.getStarnameMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getStarnameMethod = QueryGrpc.getStarnameMethod) == null) {
          QueryGrpc.getStarnameMethod = getStarnameMethod =
              io.grpc.MethodDescriptor.<starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameRequest, starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Starname"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Starname"))
              .build();
        }
      }
    }
    return getStarnameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsRequest,
      starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsResponse> getOwnerAccountsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OwnerAccounts",
      requestType = starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsRequest.class,
      responseType = starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsRequest,
      starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsResponse> getOwnerAccountsMethod() {
    io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsRequest, starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsResponse> getOwnerAccountsMethod;
    if ((getOwnerAccountsMethod = QueryGrpc.getOwnerAccountsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOwnerAccountsMethod = QueryGrpc.getOwnerAccountsMethod) == null) {
          QueryGrpc.getOwnerAccountsMethod = getOwnerAccountsMethod =
              io.grpc.MethodDescriptor.<starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsRequest, starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OwnerAccounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OwnerAccounts"))
              .build();
        }
      }
    }
    return getOwnerAccountsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsRequest,
      starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsResponse> getOwnerDomainsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OwnerDomains",
      requestType = starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsRequest.class,
      responseType = starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsRequest,
      starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsResponse> getOwnerDomainsMethod() {
    io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsRequest, starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsResponse> getOwnerDomainsMethod;
    if ((getOwnerDomainsMethod = QueryGrpc.getOwnerDomainsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOwnerDomainsMethod = QueryGrpc.getOwnerDomainsMethod) == null) {
          QueryGrpc.getOwnerDomainsMethod = getOwnerDomainsMethod =
              io.grpc.MethodDescriptor.<starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsRequest, starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OwnerDomains"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OwnerDomains"))
              .build();
        }
      }
    }
    return getOwnerDomainsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsRequest,
      starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsResponse> getResourceAccountsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ResourceAccounts",
      requestType = starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsRequest.class,
      responseType = starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsRequest,
      starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsResponse> getResourceAccountsMethod() {
    io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsRequest, starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsResponse> getResourceAccountsMethod;
    if ((getResourceAccountsMethod = QueryGrpc.getResourceAccountsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getResourceAccountsMethod = QueryGrpc.getResourceAccountsMethod) == null) {
          QueryGrpc.getResourceAccountsMethod = getResourceAccountsMethod =
              io.grpc.MethodDescriptor.<starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsRequest, starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ResourceAccounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ResourceAccounts"))
              .build();
        }
      }
    }
    return getResourceAccountsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsRequest,
      starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsResponse> getBrokerAccountsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BrokerAccounts",
      requestType = starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsRequest.class,
      responseType = starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsRequest,
      starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsResponse> getBrokerAccountsMethod() {
    io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsRequest, starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsResponse> getBrokerAccountsMethod;
    if ((getBrokerAccountsMethod = QueryGrpc.getBrokerAccountsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBrokerAccountsMethod = QueryGrpc.getBrokerAccountsMethod) == null) {
          QueryGrpc.getBrokerAccountsMethod = getBrokerAccountsMethod =
              io.grpc.MethodDescriptor.<starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsRequest, starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BrokerAccounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BrokerAccounts"))
              .build();
        }
      }
    }
    return getBrokerAccountsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsRequest,
      starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsResponse> getBrokerDomainsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BrokerDomains",
      requestType = starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsRequest.class,
      responseType = starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsRequest,
      starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsResponse> getBrokerDomainsMethod() {
    io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsRequest, starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsResponse> getBrokerDomainsMethod;
    if ((getBrokerDomainsMethod = QueryGrpc.getBrokerDomainsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getBrokerDomainsMethod = QueryGrpc.getBrokerDomainsMethod) == null) {
          QueryGrpc.getBrokerDomainsMethod = getBrokerDomainsMethod =
              io.grpc.MethodDescriptor.<starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsRequest, starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BrokerDomains"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("BrokerDomains"))
              .build();
        }
      }
    }
    return getBrokerDomainsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldRequest,
      starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldResponse> getYieldMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Yield",
      requestType = starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldRequest.class,
      responseType = starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldRequest,
      starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldResponse> getYieldMethod() {
    io.grpc.MethodDescriptor<starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldRequest, starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldResponse> getYieldMethod;
    if ((getYieldMethod = QueryGrpc.getYieldMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getYieldMethod = QueryGrpc.getYieldMethod) == null) {
          QueryGrpc.getYieldMethod = getYieldMethod =
              io.grpc.MethodDescriptor.<starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldRequest, starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Yield"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Yield"))
              .build();
        }
      }
    }
    return getYieldMethod;
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
     * Domain gets a starname's domain info.
     * </pre>
     */
    public void domain(starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDomainMethod(), responseObserver);
    }

    /**
     * <pre>
     * DomainAccounts gets accounts associated with a given domain.
     * </pre>
     */
    public void domainAccounts(starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDomainAccountsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Starname gets all the information associated with a starname.
     * </pre>
     */
    public void starname(starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStarnameMethod(), responseObserver);
    }

    /**
     * <pre>
     * OwnerAccounts gets accounts associated with a given owner.
     * </pre>
     */
    public void ownerAccounts(starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOwnerAccountsMethod(), responseObserver);
    }

    /**
     * <pre>
     * OwnerDomains gets domains associated with a given owner.
     * </pre>
     */
    public void ownerDomains(starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOwnerDomainsMethod(), responseObserver);
    }

    /**
     * <pre>
     * ResourceAccounts gets accounts associated with a given resource.
     * </pre>
     */
    public void resourceAccounts(starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getResourceAccountsMethod(), responseObserver);
    }

    /**
     * <pre>
     * BrokerAccounts gets accounts associated with a given broker.
     * </pre>
     */
    public void brokerAccounts(starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBrokerAccountsMethod(), responseObserver);
    }

    /**
     * <pre>
     * BrokerDomains gets domains associated with a given broker.
     * </pre>
     */
    public void brokerDomains(starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBrokerDomainsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Yield estimates and retrieves the annualized yield for delegators
     * </pre>
     */
    public void yield(starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getYieldMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDomainMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainRequest,
                starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainResponse>(
                  this, METHODID_DOMAIN)))
          .addMethod(
            getDomainAccountsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsRequest,
                starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsResponse>(
                  this, METHODID_DOMAIN_ACCOUNTS)))
          .addMethod(
            getStarnameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameRequest,
                starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameResponse>(
                  this, METHODID_STARNAME)))
          .addMethod(
            getOwnerAccountsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsRequest,
                starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsResponse>(
                  this, METHODID_OWNER_ACCOUNTS)))
          .addMethod(
            getOwnerDomainsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsRequest,
                starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsResponse>(
                  this, METHODID_OWNER_DOMAINS)))
          .addMethod(
            getResourceAccountsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsRequest,
                starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsResponse>(
                  this, METHODID_RESOURCE_ACCOUNTS)))
          .addMethod(
            getBrokerAccountsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsRequest,
                starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsResponse>(
                  this, METHODID_BROKER_ACCOUNTS)))
          .addMethod(
            getBrokerDomainsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsRequest,
                starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsResponse>(
                  this, METHODID_BROKER_DOMAINS)))
          .addMethod(
            getYieldMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldRequest,
                starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldResponse>(
                  this, METHODID_YIELD)))
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
     * Domain gets a starname's domain info.
     * </pre>
     */
    public void domain(starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDomainMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DomainAccounts gets accounts associated with a given domain.
     * </pre>
     */
    public void domainAccounts(starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDomainAccountsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Starname gets all the information associated with a starname.
     * </pre>
     */
    public void starname(starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStarnameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * OwnerAccounts gets accounts associated with a given owner.
     * </pre>
     */
    public void ownerAccounts(starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOwnerAccountsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * OwnerDomains gets domains associated with a given owner.
     * </pre>
     */
    public void ownerDomains(starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOwnerDomainsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ResourceAccounts gets accounts associated with a given resource.
     * </pre>
     */
    public void resourceAccounts(starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getResourceAccountsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BrokerAccounts gets accounts associated with a given broker.
     * </pre>
     */
    public void brokerAccounts(starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBrokerAccountsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BrokerDomains gets domains associated with a given broker.
     * </pre>
     */
    public void brokerDomains(starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBrokerDomainsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Yield estimates and retrieves the annualized yield for delegators
     * </pre>
     */
    public void yield(starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldRequest request,
        io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getYieldMethod(), getCallOptions()), request, responseObserver);
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
     * Domain gets a starname's domain info.
     * </pre>
     */
    public starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainResponse domain(starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainRequest request) {
      return blockingUnaryCall(
          getChannel(), getDomainMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DomainAccounts gets accounts associated with a given domain.
     * </pre>
     */
    public starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsResponse domainAccounts(starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsRequest request) {
      return blockingUnaryCall(
          getChannel(), getDomainAccountsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Starname gets all the information associated with a starname.
     * </pre>
     */
    public starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameResponse starname(starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameRequest request) {
      return blockingUnaryCall(
          getChannel(), getStarnameMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * OwnerAccounts gets accounts associated with a given owner.
     * </pre>
     */
    public starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsResponse ownerAccounts(starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsRequest request) {
      return blockingUnaryCall(
          getChannel(), getOwnerAccountsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * OwnerDomains gets domains associated with a given owner.
     * </pre>
     */
    public starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsResponse ownerDomains(starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsRequest request) {
      return blockingUnaryCall(
          getChannel(), getOwnerDomainsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ResourceAccounts gets accounts associated with a given resource.
     * </pre>
     */
    public starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsResponse resourceAccounts(starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsRequest request) {
      return blockingUnaryCall(
          getChannel(), getResourceAccountsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BrokerAccounts gets accounts associated with a given broker.
     * </pre>
     */
    public starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsResponse brokerAccounts(starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsRequest request) {
      return blockingUnaryCall(
          getChannel(), getBrokerAccountsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BrokerDomains gets domains associated with a given broker.
     * </pre>
     */
    public starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsResponse brokerDomains(starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsRequest request) {
      return blockingUnaryCall(
          getChannel(), getBrokerDomainsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Yield estimates and retrieves the annualized yield for delegators
     * </pre>
     */
    public starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldResponse yield(starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldRequest request) {
      return blockingUnaryCall(
          getChannel(), getYieldMethod(), getCallOptions(), request);
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
     * Domain gets a starname's domain info.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainResponse> domain(
        starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDomainMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DomainAccounts gets accounts associated with a given domain.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsResponse> domainAccounts(
        starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDomainAccountsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Starname gets all the information associated with a starname.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameResponse> starname(
        starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getStarnameMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * OwnerAccounts gets accounts associated with a given owner.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsResponse> ownerAccounts(
        starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOwnerAccountsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * OwnerDomains gets domains associated with a given owner.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsResponse> ownerDomains(
        starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOwnerDomainsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ResourceAccounts gets accounts associated with a given resource.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsResponse> resourceAccounts(
        starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getResourceAccountsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BrokerAccounts gets accounts associated with a given broker.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsResponse> brokerAccounts(
        starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBrokerAccountsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BrokerDomains gets domains associated with a given broker.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsResponse> brokerDomains(
        starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBrokerDomainsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Yield estimates and retrieves the annualized yield for delegators
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldResponse> yield(
        starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getYieldMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DOMAIN = 0;
  private static final int METHODID_DOMAIN_ACCOUNTS = 1;
  private static final int METHODID_STARNAME = 2;
  private static final int METHODID_OWNER_ACCOUNTS = 3;
  private static final int METHODID_OWNER_DOMAINS = 4;
  private static final int METHODID_RESOURCE_ACCOUNTS = 5;
  private static final int METHODID_BROKER_ACCOUNTS = 6;
  private static final int METHODID_BROKER_DOMAINS = 7;
  private static final int METHODID_YIELD = 8;

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
        case METHODID_DOMAIN:
          serviceImpl.domain((starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainRequest) request,
              (io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainResponse>) responseObserver);
          break;
        case METHODID_DOMAIN_ACCOUNTS:
          serviceImpl.domainAccounts((starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsRequest) request,
              (io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryDomainAccountsResponse>) responseObserver);
          break;
        case METHODID_STARNAME:
          serviceImpl.starname((starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameRequest) request,
              (io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameResponse>) responseObserver);
          break;
        case METHODID_OWNER_ACCOUNTS:
          serviceImpl.ownerAccounts((starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsRequest) request,
              (io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerAccountsResponse>) responseObserver);
          break;
        case METHODID_OWNER_DOMAINS:
          serviceImpl.ownerDomains((starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsRequest) request,
              (io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryOwnerDomainsResponse>) responseObserver);
          break;
        case METHODID_RESOURCE_ACCOUNTS:
          serviceImpl.resourceAccounts((starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsRequest) request,
              (io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryResourceAccountsResponse>) responseObserver);
          break;
        case METHODID_BROKER_ACCOUNTS:
          serviceImpl.brokerAccounts((starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsRequest) request,
              (io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerAccountsResponse>) responseObserver);
          break;
        case METHODID_BROKER_DOMAINS:
          serviceImpl.brokerDomains((starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsRequest) request,
              (io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryBrokerDomainsResponse>) responseObserver);
          break;
        case METHODID_YIELD:
          serviceImpl.yield((starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldRequest) request,
              (io.grpc.stub.StreamObserver<starnamed.x.starname.v1beta1.QueryOuterClass.QueryYieldResponse>) responseObserver);
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
      return starnamed.x.starname.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getDomainMethod())
              .addMethod(getDomainAccountsMethod())
              .addMethod(getStarnameMethod())
              .addMethod(getOwnerAccountsMethod())
              .addMethod(getOwnerDomainsMethod())
              .addMethod(getResourceAccountsMethod())
              .addMethod(getBrokerAccountsMethod())
              .addMethod(getBrokerDomainsMethod())
              .addMethod(getYieldMethod())
              .build();
        }
      }
    }
    return result;
  }
}
