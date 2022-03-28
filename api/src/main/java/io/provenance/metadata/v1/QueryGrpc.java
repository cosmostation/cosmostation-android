package io.provenance.metadata.v1;

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
 * Query defines the Metadata Query service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: provenance/metadata/v1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "provenance.metadata.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.QueryParamsRequest,
      io.provenance.metadata.v1.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = io.provenance.metadata.v1.QueryParamsRequest.class,
      responseType = io.provenance.metadata.v1.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.QueryParamsRequest,
      io.provenance.metadata.v1.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.QueryParamsRequest, io.provenance.metadata.v1.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.QueryParamsRequest, io.provenance.metadata.v1.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.ScopeRequest,
      io.provenance.metadata.v1.ScopeResponse> getScopeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Scope",
      requestType = io.provenance.metadata.v1.ScopeRequest.class,
      responseType = io.provenance.metadata.v1.ScopeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.ScopeRequest,
      io.provenance.metadata.v1.ScopeResponse> getScopeMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.ScopeRequest, io.provenance.metadata.v1.ScopeResponse> getScopeMethod;
    if ((getScopeMethod = QueryGrpc.getScopeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getScopeMethod = QueryGrpc.getScopeMethod) == null) {
          QueryGrpc.getScopeMethod = getScopeMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.ScopeRequest, io.provenance.metadata.v1.ScopeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Scope"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.ScopeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.ScopeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Scope"))
              .build();
        }
      }
    }
    return getScopeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.ScopesAllRequest,
      io.provenance.metadata.v1.ScopesAllResponse> getScopesAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ScopesAll",
      requestType = io.provenance.metadata.v1.ScopesAllRequest.class,
      responseType = io.provenance.metadata.v1.ScopesAllResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.ScopesAllRequest,
      io.provenance.metadata.v1.ScopesAllResponse> getScopesAllMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.ScopesAllRequest, io.provenance.metadata.v1.ScopesAllResponse> getScopesAllMethod;
    if ((getScopesAllMethod = QueryGrpc.getScopesAllMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getScopesAllMethod = QueryGrpc.getScopesAllMethod) == null) {
          QueryGrpc.getScopesAllMethod = getScopesAllMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.ScopesAllRequest, io.provenance.metadata.v1.ScopesAllResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ScopesAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.ScopesAllRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.ScopesAllResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ScopesAll"))
              .build();
        }
      }
    }
    return getScopesAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.SessionsRequest,
      io.provenance.metadata.v1.SessionsResponse> getSessionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Sessions",
      requestType = io.provenance.metadata.v1.SessionsRequest.class,
      responseType = io.provenance.metadata.v1.SessionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.SessionsRequest,
      io.provenance.metadata.v1.SessionsResponse> getSessionsMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.SessionsRequest, io.provenance.metadata.v1.SessionsResponse> getSessionsMethod;
    if ((getSessionsMethod = QueryGrpc.getSessionsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSessionsMethod = QueryGrpc.getSessionsMethod) == null) {
          QueryGrpc.getSessionsMethod = getSessionsMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.SessionsRequest, io.provenance.metadata.v1.SessionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Sessions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.SessionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.SessionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Sessions"))
              .build();
        }
      }
    }
    return getSessionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.SessionsAllRequest,
      io.provenance.metadata.v1.SessionsAllResponse> getSessionsAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SessionsAll",
      requestType = io.provenance.metadata.v1.SessionsAllRequest.class,
      responseType = io.provenance.metadata.v1.SessionsAllResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.SessionsAllRequest,
      io.provenance.metadata.v1.SessionsAllResponse> getSessionsAllMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.SessionsAllRequest, io.provenance.metadata.v1.SessionsAllResponse> getSessionsAllMethod;
    if ((getSessionsAllMethod = QueryGrpc.getSessionsAllMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSessionsAllMethod = QueryGrpc.getSessionsAllMethod) == null) {
          QueryGrpc.getSessionsAllMethod = getSessionsAllMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.SessionsAllRequest, io.provenance.metadata.v1.SessionsAllResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SessionsAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.SessionsAllRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.SessionsAllResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SessionsAll"))
              .build();
        }
      }
    }
    return getSessionsAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.RecordsRequest,
      io.provenance.metadata.v1.RecordsResponse> getRecordsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Records",
      requestType = io.provenance.metadata.v1.RecordsRequest.class,
      responseType = io.provenance.metadata.v1.RecordsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.RecordsRequest,
      io.provenance.metadata.v1.RecordsResponse> getRecordsMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.RecordsRequest, io.provenance.metadata.v1.RecordsResponse> getRecordsMethod;
    if ((getRecordsMethod = QueryGrpc.getRecordsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRecordsMethod = QueryGrpc.getRecordsMethod) == null) {
          QueryGrpc.getRecordsMethod = getRecordsMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.RecordsRequest, io.provenance.metadata.v1.RecordsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Records"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.RecordsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.RecordsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Records"))
              .build();
        }
      }
    }
    return getRecordsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.RecordsAllRequest,
      io.provenance.metadata.v1.RecordsAllResponse> getRecordsAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RecordsAll",
      requestType = io.provenance.metadata.v1.RecordsAllRequest.class,
      responseType = io.provenance.metadata.v1.RecordsAllResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.RecordsAllRequest,
      io.provenance.metadata.v1.RecordsAllResponse> getRecordsAllMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.RecordsAllRequest, io.provenance.metadata.v1.RecordsAllResponse> getRecordsAllMethod;
    if ((getRecordsAllMethod = QueryGrpc.getRecordsAllMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRecordsAllMethod = QueryGrpc.getRecordsAllMethod) == null) {
          QueryGrpc.getRecordsAllMethod = getRecordsAllMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.RecordsAllRequest, io.provenance.metadata.v1.RecordsAllResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RecordsAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.RecordsAllRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.RecordsAllResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RecordsAll"))
              .build();
        }
      }
    }
    return getRecordsAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.OwnershipRequest,
      io.provenance.metadata.v1.OwnershipResponse> getOwnershipMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Ownership",
      requestType = io.provenance.metadata.v1.OwnershipRequest.class,
      responseType = io.provenance.metadata.v1.OwnershipResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.OwnershipRequest,
      io.provenance.metadata.v1.OwnershipResponse> getOwnershipMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.OwnershipRequest, io.provenance.metadata.v1.OwnershipResponse> getOwnershipMethod;
    if ((getOwnershipMethod = QueryGrpc.getOwnershipMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOwnershipMethod = QueryGrpc.getOwnershipMethod) == null) {
          QueryGrpc.getOwnershipMethod = getOwnershipMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.OwnershipRequest, io.provenance.metadata.v1.OwnershipResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Ownership"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.OwnershipRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.OwnershipResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Ownership"))
              .build();
        }
      }
    }
    return getOwnershipMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.ValueOwnershipRequest,
      io.provenance.metadata.v1.ValueOwnershipResponse> getValueOwnershipMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValueOwnership",
      requestType = io.provenance.metadata.v1.ValueOwnershipRequest.class,
      responseType = io.provenance.metadata.v1.ValueOwnershipResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.ValueOwnershipRequest,
      io.provenance.metadata.v1.ValueOwnershipResponse> getValueOwnershipMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.ValueOwnershipRequest, io.provenance.metadata.v1.ValueOwnershipResponse> getValueOwnershipMethod;
    if ((getValueOwnershipMethod = QueryGrpc.getValueOwnershipMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValueOwnershipMethod = QueryGrpc.getValueOwnershipMethod) == null) {
          QueryGrpc.getValueOwnershipMethod = getValueOwnershipMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.ValueOwnershipRequest, io.provenance.metadata.v1.ValueOwnershipResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValueOwnership"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.ValueOwnershipRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.ValueOwnershipResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ValueOwnership"))
              .build();
        }
      }
    }
    return getValueOwnershipMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.ScopeSpecificationRequest,
      io.provenance.metadata.v1.ScopeSpecificationResponse> getScopeSpecificationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ScopeSpecification",
      requestType = io.provenance.metadata.v1.ScopeSpecificationRequest.class,
      responseType = io.provenance.metadata.v1.ScopeSpecificationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.ScopeSpecificationRequest,
      io.provenance.metadata.v1.ScopeSpecificationResponse> getScopeSpecificationMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.ScopeSpecificationRequest, io.provenance.metadata.v1.ScopeSpecificationResponse> getScopeSpecificationMethod;
    if ((getScopeSpecificationMethod = QueryGrpc.getScopeSpecificationMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getScopeSpecificationMethod = QueryGrpc.getScopeSpecificationMethod) == null) {
          QueryGrpc.getScopeSpecificationMethod = getScopeSpecificationMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.ScopeSpecificationRequest, io.provenance.metadata.v1.ScopeSpecificationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ScopeSpecification"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.ScopeSpecificationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.ScopeSpecificationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ScopeSpecification"))
              .build();
        }
      }
    }
    return getScopeSpecificationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.ScopeSpecificationsAllRequest,
      io.provenance.metadata.v1.ScopeSpecificationsAllResponse> getScopeSpecificationsAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ScopeSpecificationsAll",
      requestType = io.provenance.metadata.v1.ScopeSpecificationsAllRequest.class,
      responseType = io.provenance.metadata.v1.ScopeSpecificationsAllResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.ScopeSpecificationsAllRequest,
      io.provenance.metadata.v1.ScopeSpecificationsAllResponse> getScopeSpecificationsAllMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.ScopeSpecificationsAllRequest, io.provenance.metadata.v1.ScopeSpecificationsAllResponse> getScopeSpecificationsAllMethod;
    if ((getScopeSpecificationsAllMethod = QueryGrpc.getScopeSpecificationsAllMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getScopeSpecificationsAllMethod = QueryGrpc.getScopeSpecificationsAllMethod) == null) {
          QueryGrpc.getScopeSpecificationsAllMethod = getScopeSpecificationsAllMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.ScopeSpecificationsAllRequest, io.provenance.metadata.v1.ScopeSpecificationsAllResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ScopeSpecificationsAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.ScopeSpecificationsAllRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.ScopeSpecificationsAllResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ScopeSpecificationsAll"))
              .build();
        }
      }
    }
    return getScopeSpecificationsAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.ContractSpecificationRequest,
      io.provenance.metadata.v1.ContractSpecificationResponse> getContractSpecificationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ContractSpecification",
      requestType = io.provenance.metadata.v1.ContractSpecificationRequest.class,
      responseType = io.provenance.metadata.v1.ContractSpecificationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.ContractSpecificationRequest,
      io.provenance.metadata.v1.ContractSpecificationResponse> getContractSpecificationMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.ContractSpecificationRequest, io.provenance.metadata.v1.ContractSpecificationResponse> getContractSpecificationMethod;
    if ((getContractSpecificationMethod = QueryGrpc.getContractSpecificationMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getContractSpecificationMethod = QueryGrpc.getContractSpecificationMethod) == null) {
          QueryGrpc.getContractSpecificationMethod = getContractSpecificationMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.ContractSpecificationRequest, io.provenance.metadata.v1.ContractSpecificationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ContractSpecification"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.ContractSpecificationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.ContractSpecificationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ContractSpecification"))
              .build();
        }
      }
    }
    return getContractSpecificationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.ContractSpecificationsAllRequest,
      io.provenance.metadata.v1.ContractSpecificationsAllResponse> getContractSpecificationsAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ContractSpecificationsAll",
      requestType = io.provenance.metadata.v1.ContractSpecificationsAllRequest.class,
      responseType = io.provenance.metadata.v1.ContractSpecificationsAllResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.ContractSpecificationsAllRequest,
      io.provenance.metadata.v1.ContractSpecificationsAllResponse> getContractSpecificationsAllMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.ContractSpecificationsAllRequest, io.provenance.metadata.v1.ContractSpecificationsAllResponse> getContractSpecificationsAllMethod;
    if ((getContractSpecificationsAllMethod = QueryGrpc.getContractSpecificationsAllMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getContractSpecificationsAllMethod = QueryGrpc.getContractSpecificationsAllMethod) == null) {
          QueryGrpc.getContractSpecificationsAllMethod = getContractSpecificationsAllMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.ContractSpecificationsAllRequest, io.provenance.metadata.v1.ContractSpecificationsAllResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ContractSpecificationsAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.ContractSpecificationsAllRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.ContractSpecificationsAllResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ContractSpecificationsAll"))
              .build();
        }
      }
    }
    return getContractSpecificationsAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationRequest,
      io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationResponse> getRecordSpecificationsForContractSpecificationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RecordSpecificationsForContractSpecification",
      requestType = io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationRequest.class,
      responseType = io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationRequest,
      io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationResponse> getRecordSpecificationsForContractSpecificationMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationRequest, io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationResponse> getRecordSpecificationsForContractSpecificationMethod;
    if ((getRecordSpecificationsForContractSpecificationMethod = QueryGrpc.getRecordSpecificationsForContractSpecificationMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRecordSpecificationsForContractSpecificationMethod = QueryGrpc.getRecordSpecificationsForContractSpecificationMethod) == null) {
          QueryGrpc.getRecordSpecificationsForContractSpecificationMethod = getRecordSpecificationsForContractSpecificationMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationRequest, io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RecordSpecificationsForContractSpecification"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RecordSpecificationsForContractSpecification"))
              .build();
        }
      }
    }
    return getRecordSpecificationsForContractSpecificationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.RecordSpecificationRequest,
      io.provenance.metadata.v1.RecordSpecificationResponse> getRecordSpecificationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RecordSpecification",
      requestType = io.provenance.metadata.v1.RecordSpecificationRequest.class,
      responseType = io.provenance.metadata.v1.RecordSpecificationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.RecordSpecificationRequest,
      io.provenance.metadata.v1.RecordSpecificationResponse> getRecordSpecificationMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.RecordSpecificationRequest, io.provenance.metadata.v1.RecordSpecificationResponse> getRecordSpecificationMethod;
    if ((getRecordSpecificationMethod = QueryGrpc.getRecordSpecificationMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRecordSpecificationMethod = QueryGrpc.getRecordSpecificationMethod) == null) {
          QueryGrpc.getRecordSpecificationMethod = getRecordSpecificationMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.RecordSpecificationRequest, io.provenance.metadata.v1.RecordSpecificationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RecordSpecification"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.RecordSpecificationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.RecordSpecificationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RecordSpecification"))
              .build();
        }
      }
    }
    return getRecordSpecificationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.RecordSpecificationsAllRequest,
      io.provenance.metadata.v1.RecordSpecificationsAllResponse> getRecordSpecificationsAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RecordSpecificationsAll",
      requestType = io.provenance.metadata.v1.RecordSpecificationsAllRequest.class,
      responseType = io.provenance.metadata.v1.RecordSpecificationsAllResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.RecordSpecificationsAllRequest,
      io.provenance.metadata.v1.RecordSpecificationsAllResponse> getRecordSpecificationsAllMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.RecordSpecificationsAllRequest, io.provenance.metadata.v1.RecordSpecificationsAllResponse> getRecordSpecificationsAllMethod;
    if ((getRecordSpecificationsAllMethod = QueryGrpc.getRecordSpecificationsAllMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRecordSpecificationsAllMethod = QueryGrpc.getRecordSpecificationsAllMethod) == null) {
          QueryGrpc.getRecordSpecificationsAllMethod = getRecordSpecificationsAllMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.RecordSpecificationsAllRequest, io.provenance.metadata.v1.RecordSpecificationsAllResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RecordSpecificationsAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.RecordSpecificationsAllRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.RecordSpecificationsAllResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RecordSpecificationsAll"))
              .build();
        }
      }
    }
    return getRecordSpecificationsAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.OSLocatorParamsRequest,
      io.provenance.metadata.v1.OSLocatorParamsResponse> getOSLocatorParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OSLocatorParams",
      requestType = io.provenance.metadata.v1.OSLocatorParamsRequest.class,
      responseType = io.provenance.metadata.v1.OSLocatorParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.OSLocatorParamsRequest,
      io.provenance.metadata.v1.OSLocatorParamsResponse> getOSLocatorParamsMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.OSLocatorParamsRequest, io.provenance.metadata.v1.OSLocatorParamsResponse> getOSLocatorParamsMethod;
    if ((getOSLocatorParamsMethod = QueryGrpc.getOSLocatorParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOSLocatorParamsMethod = QueryGrpc.getOSLocatorParamsMethod) == null) {
          QueryGrpc.getOSLocatorParamsMethod = getOSLocatorParamsMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.OSLocatorParamsRequest, io.provenance.metadata.v1.OSLocatorParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OSLocatorParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.OSLocatorParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.OSLocatorParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OSLocatorParams"))
              .build();
        }
      }
    }
    return getOSLocatorParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.OSLocatorRequest,
      io.provenance.metadata.v1.OSLocatorResponse> getOSLocatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OSLocator",
      requestType = io.provenance.metadata.v1.OSLocatorRequest.class,
      responseType = io.provenance.metadata.v1.OSLocatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.OSLocatorRequest,
      io.provenance.metadata.v1.OSLocatorResponse> getOSLocatorMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.OSLocatorRequest, io.provenance.metadata.v1.OSLocatorResponse> getOSLocatorMethod;
    if ((getOSLocatorMethod = QueryGrpc.getOSLocatorMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOSLocatorMethod = QueryGrpc.getOSLocatorMethod) == null) {
          QueryGrpc.getOSLocatorMethod = getOSLocatorMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.OSLocatorRequest, io.provenance.metadata.v1.OSLocatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OSLocator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.OSLocatorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.OSLocatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OSLocator"))
              .build();
        }
      }
    }
    return getOSLocatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.OSLocatorsByURIRequest,
      io.provenance.metadata.v1.OSLocatorsByURIResponse> getOSLocatorsByURIMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OSLocatorsByURI",
      requestType = io.provenance.metadata.v1.OSLocatorsByURIRequest.class,
      responseType = io.provenance.metadata.v1.OSLocatorsByURIResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.OSLocatorsByURIRequest,
      io.provenance.metadata.v1.OSLocatorsByURIResponse> getOSLocatorsByURIMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.OSLocatorsByURIRequest, io.provenance.metadata.v1.OSLocatorsByURIResponse> getOSLocatorsByURIMethod;
    if ((getOSLocatorsByURIMethod = QueryGrpc.getOSLocatorsByURIMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOSLocatorsByURIMethod = QueryGrpc.getOSLocatorsByURIMethod) == null) {
          QueryGrpc.getOSLocatorsByURIMethod = getOSLocatorsByURIMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.OSLocatorsByURIRequest, io.provenance.metadata.v1.OSLocatorsByURIResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OSLocatorsByURI"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.OSLocatorsByURIRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.OSLocatorsByURIResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OSLocatorsByURI"))
              .build();
        }
      }
    }
    return getOSLocatorsByURIMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.OSLocatorsByScopeRequest,
      io.provenance.metadata.v1.OSLocatorsByScopeResponse> getOSLocatorsByScopeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OSLocatorsByScope",
      requestType = io.provenance.metadata.v1.OSLocatorsByScopeRequest.class,
      responseType = io.provenance.metadata.v1.OSLocatorsByScopeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.OSLocatorsByScopeRequest,
      io.provenance.metadata.v1.OSLocatorsByScopeResponse> getOSLocatorsByScopeMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.OSLocatorsByScopeRequest, io.provenance.metadata.v1.OSLocatorsByScopeResponse> getOSLocatorsByScopeMethod;
    if ((getOSLocatorsByScopeMethod = QueryGrpc.getOSLocatorsByScopeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOSLocatorsByScopeMethod = QueryGrpc.getOSLocatorsByScopeMethod) == null) {
          QueryGrpc.getOSLocatorsByScopeMethod = getOSLocatorsByScopeMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.OSLocatorsByScopeRequest, io.provenance.metadata.v1.OSLocatorsByScopeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OSLocatorsByScope"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.OSLocatorsByScopeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.OSLocatorsByScopeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OSLocatorsByScope"))
              .build();
        }
      }
    }
    return getOSLocatorsByScopeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.OSAllLocatorsRequest,
      io.provenance.metadata.v1.OSAllLocatorsResponse> getOSAllLocatorsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OSAllLocators",
      requestType = io.provenance.metadata.v1.OSAllLocatorsRequest.class,
      responseType = io.provenance.metadata.v1.OSAllLocatorsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.OSAllLocatorsRequest,
      io.provenance.metadata.v1.OSAllLocatorsResponse> getOSAllLocatorsMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.OSAllLocatorsRequest, io.provenance.metadata.v1.OSAllLocatorsResponse> getOSAllLocatorsMethod;
    if ((getOSAllLocatorsMethod = QueryGrpc.getOSAllLocatorsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOSAllLocatorsMethod = QueryGrpc.getOSAllLocatorsMethod) == null) {
          QueryGrpc.getOSAllLocatorsMethod = getOSAllLocatorsMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.OSAllLocatorsRequest, io.provenance.metadata.v1.OSAllLocatorsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OSAllLocators"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.OSAllLocatorsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.OSAllLocatorsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OSAllLocators"))
              .build();
        }
      }
    }
    return getOSAllLocatorsMethod;
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
   * Query defines the Metadata Query service.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Params queries the parameters of x/metadata module.
     * </pre>
     */
    public void params(io.provenance.metadata.v1.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Scope searches for a scope.
     * The scope id, if provided, must either be scope uuid, e.g. 91978ba2-5f35-459a-86a7-feca1b0512e0 or a scope address,
     * e.g. scope1qzge0zaztu65tx5x5llv5xc9ztsqxlkwel. The session addr, if provided, must be a bech32 session address,
     * e.g. session1qxge0zaztu65tx5x5llv5xc9zts9sqlch3sxwn44j50jzgt8rshvqyfrjcr. The record_addr, if provided, must be a
     * bech32 record address, e.g. record1q2ge0zaztu65tx5x5llv5xc9ztsw42dq2jdvmdazuwzcaddhh8gmu3mcze3.
     * * If only a scope_id is provided, that scope is returned.
     * * If only a session_addr is provided, the scope containing that session is returned.
     * * If only a record_addr is provided, the scope containing that record is returned.
     * * If more than one of scope_id, session_addr, and record_addr are provided, and they don't refer to the same scope,
     * a bad request is returned.
     * Providing a session addr or record addr does not limit the sessions and records returned (if requested).
     * Those parameters are only used to find the scope.
     * By default, sessions and records are not included.
     * Set include_sessions and/or include_records to true to include sessions and/or records.
     * </pre>
     */
    public void scope(io.provenance.metadata.v1.ScopeRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.ScopeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getScopeMethod(), responseObserver);
    }

    /**
     * <pre>
     * ScopesAll retrieves all scopes.
     * </pre>
     */
    public void scopesAll(io.provenance.metadata.v1.ScopesAllRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.ScopesAllResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getScopesAllMethod(), responseObserver);
    }

    /**
     * <pre>
     * Sessions searches for sessions.
     * The scope_id can either be scope uuid, e.g. 91978ba2-5f35-459a-86a7-feca1b0512e0 or a scope address, e.g.
     * scope1qzge0zaztu65tx5x5llv5xc9ztsqxlkwel. Similarly, the session_id can either be a uuid or session address, e.g.
     * session1qxge0zaztu65tx5x5llv5xc9zts9sqlch3sxwn44j50jzgt8rshvqyfrjcr. The record_addr, if provided, must be a
     * bech32 record address, e.g. record1q2ge0zaztu65tx5x5llv5xc9ztsw42dq2jdvmdazuwzcaddhh8gmu3mcze3.
     * * If only a scope_id is provided, all sessions in that scope are returned.
     * * If only a session_id is provided, it must be an address, and that single session is returned.
     * * If the session_id is a uuid, then either a scope_id or record_addr must also be provided, and that single session
     * is returned.
     * * If only a record_addr is provided, the session containing that record will be returned.
     * * If a record_name is provided then either a scope_id, session_id as an address, or record_addr must also be
     * provided, and the session containing that record will be returned.
     * A bad request is returned if:
     * * The session_id is a uuid and is provided without a scope_id or record_addr.
     * * A record_name is provided without any way to identify the scope (e.g. a scope_id, a session_id as an address, or
     * a record_addr).
     * * Two or more of scope_id, session_id as an address, and record_addr are provided and don't all refer to the same
     * scope.
     * * A record_addr (or scope_id and record_name) is provided with a session_id and that session does not contain such
     * a record.
     * * A record_addr and record_name are both provided, but reference different records.
     * By default, the scope and records are not included.
     * Set include_scope and/or include_records to true to include the scope and/or records.
     * </pre>
     */
    public void sessions(io.provenance.metadata.v1.SessionsRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.SessionsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSessionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * SessionsAll retrieves all sessions.
     * </pre>
     */
    public void sessionsAll(io.provenance.metadata.v1.SessionsAllRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.SessionsAllResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSessionsAllMethod(), responseObserver);
    }

    /**
     * <pre>
     * Records searches for records.
     * The record_addr, if provided, must be a bech32 record address, e.g.
     * record1q2ge0zaztu65tx5x5llv5xc9ztsw42dq2jdvmdazuwzcaddhh8gmu3mcze3. The scope-id can either be scope uuid, e.g.
     * 91978ba2-5f35-459a-86a7-feca1b0512e0 or a scope address, e.g. scope1qzge0zaztu65tx5x5llv5xc9ztsqxlkwel. Similarly,
     * the session_id can either be a uuid or session address, e.g.
     * session1qxge0zaztu65tx5x5llv5xc9zts9sqlch3sxwn44j50jzgt8rshvqyfrjcr. The name is the name of the record you're
     * interested in.
     * * If only a record_addr is provided, that single record will be returned.
     * * If only a scope_id is provided, all records in that scope will be returned.
     * * If only a session_id (or scope_id/session_id), all records in that session will be returned.
     * * If a name is provided with a scope_id and/or session_id, that single record will be returned.
     * A bad request is returned if:
     * * The session_id is a uuid and no scope_id is provided.
     * * There are two or more of record_addr, session_id, and scope_id, and they don't all refer to the same scope.
     * * A name is provided, but not a scope_id and/or a session_id.
     * * A name and record_addr are provided and the name doesn't match the record_addr.
     * By default, the scope and sessions are not included.
     * Set include_scope and/or include_sessions to true to include the scope and/or sessions.
     * </pre>
     */
    public void records(io.provenance.metadata.v1.RecordsRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.RecordsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRecordsMethod(), responseObserver);
    }

    /**
     * <pre>
     * RecordsAll retrieves all records.
     * </pre>
     */
    public void recordsAll(io.provenance.metadata.v1.RecordsAllRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.RecordsAllResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRecordsAllMethod(), responseObserver);
    }

    /**
     * <pre>
     * Ownership returns the scope identifiers that list the given address as either a data or value owner.
     * </pre>
     */
    public void ownership(io.provenance.metadata.v1.OwnershipRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.OwnershipResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOwnershipMethod(), responseObserver);
    }

    /**
     * <pre>
     * ValueOwnership returns the scope identifiers that list the given address as the value owner.
     * </pre>
     */
    public void valueOwnership(io.provenance.metadata.v1.ValueOwnershipRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.ValueOwnershipResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getValueOwnershipMethod(), responseObserver);
    }

    /**
     * <pre>
     * ScopeSpecification returns a scope specification for the given specification id.
     * The specification_id can either be a uuid, e.g. dc83ea70-eacd-40fe-9adf-1cf6148bf8a2 or a bech32 scope
     * specification address, e.g. scopespec1qnwg86nsatx5pl56muw0v9ytlz3qu3jx6m.
     * </pre>
     */
    public void scopeSpecification(io.provenance.metadata.v1.ScopeSpecificationRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.ScopeSpecificationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getScopeSpecificationMethod(), responseObserver);
    }

    /**
     * <pre>
     * ScopeSpecificationsAll retrieves all scope specifications.
     * </pre>
     */
    public void scopeSpecificationsAll(io.provenance.metadata.v1.ScopeSpecificationsAllRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.ScopeSpecificationsAllResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getScopeSpecificationsAllMethod(), responseObserver);
    }

    /**
     * <pre>
     * ContractSpecification returns a contract specification for the given specification id.
     * The specification_id can either be a uuid, e.g. def6bc0a-c9dd-4874-948f-5206e6060a84, a bech32 contract
     * specification address, e.g. contractspec1q000d0q2e8w5say53afqdesxp2zqzkr4fn, or a bech32 record specification
     * address, e.g. recspec1qh00d0q2e8w5say53afqdesxp2zw42dq2jdvmdazuwzcaddhh8gmuqhez44. If it is a record specification
     * address, then the contract specification that contains that record specification is looked up.
     * By default, the record specifications for this contract specification are not included.
     * Set include_record_specs to true to include them in the result.
     * </pre>
     */
    public void contractSpecification(io.provenance.metadata.v1.ContractSpecificationRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.ContractSpecificationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getContractSpecificationMethod(), responseObserver);
    }

    /**
     * <pre>
     * ContractSpecificationsAll retrieves all contract specifications.
     * </pre>
     */
    public void contractSpecificationsAll(io.provenance.metadata.v1.ContractSpecificationsAllRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.ContractSpecificationsAllResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getContractSpecificationsAllMethod(), responseObserver);
    }

    /**
     * <pre>
     * RecordSpecificationsForContractSpecification returns the record specifications for the given input.
     * The specification_id can either be a uuid, e.g. def6bc0a-c9dd-4874-948f-5206e6060a84, a bech32 contract
     * specification address, e.g. contractspec1q000d0q2e8w5say53afqdesxp2zqzkr4fn, or a bech32 record specification
     * address, e.g. recspec1qh00d0q2e8w5say53afqdesxp2zw42dq2jdvmdazuwzcaddhh8gmuqhez44. If it is a record specification
     * address, then the contract specification that contains that record specification is used.
     * </pre>
     */
    public void recordSpecificationsForContractSpecification(io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRecordSpecificationsForContractSpecificationMethod(), responseObserver);
    }

    /**
     * <pre>
     * RecordSpecification returns a record specification for the given input.
     * </pre>
     */
    public void recordSpecification(io.provenance.metadata.v1.RecordSpecificationRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.RecordSpecificationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRecordSpecificationMethod(), responseObserver);
    }

    /**
     * <pre>
     * RecordSpecificationsAll retrieves all record specifications.
     * </pre>
     */
    public void recordSpecificationsAll(io.provenance.metadata.v1.RecordSpecificationsAllRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.RecordSpecificationsAllResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRecordSpecificationsAllMethod(), responseObserver);
    }

    /**
     * <pre>
     * OSLocatorParams returns all parameters for the object store locator sub module.
     * </pre>
     */
    public void oSLocatorParams(io.provenance.metadata.v1.OSLocatorParamsRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.OSLocatorParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOSLocatorParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * OSLocator returns an ObjectStoreLocator by its owner's address.
     * </pre>
     */
    public void oSLocator(io.provenance.metadata.v1.OSLocatorRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.OSLocatorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOSLocatorMethod(), responseObserver);
    }

    /**
     * <pre>
     * OSLocatorsByURI returns all ObjectStoreLocator entries for a locator uri.
     * </pre>
     */
    public void oSLocatorsByURI(io.provenance.metadata.v1.OSLocatorsByURIRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.OSLocatorsByURIResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOSLocatorsByURIMethod(), responseObserver);
    }

    /**
     * <pre>
     * OSLocatorsByScope returns all ObjectStoreLocator entries for a for all signer's present in the specified scope.
     * </pre>
     */
    public void oSLocatorsByScope(io.provenance.metadata.v1.OSLocatorsByScopeRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.OSLocatorsByScopeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOSLocatorsByScopeMethod(), responseObserver);
    }

    /**
     * <pre>
     * OSAllLocators returns all ObjectStoreLocator entries.
     * </pre>
     */
    public void oSAllLocators(io.provenance.metadata.v1.OSAllLocatorsRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.OSAllLocatorsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOSAllLocatorsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.QueryParamsRequest,
                io.provenance.metadata.v1.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getScopeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.ScopeRequest,
                io.provenance.metadata.v1.ScopeResponse>(
                  this, METHODID_SCOPE)))
          .addMethod(
            getScopesAllMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.ScopesAllRequest,
                io.provenance.metadata.v1.ScopesAllResponse>(
                  this, METHODID_SCOPES_ALL)))
          .addMethod(
            getSessionsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.SessionsRequest,
                io.provenance.metadata.v1.SessionsResponse>(
                  this, METHODID_SESSIONS)))
          .addMethod(
            getSessionsAllMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.SessionsAllRequest,
                io.provenance.metadata.v1.SessionsAllResponse>(
                  this, METHODID_SESSIONS_ALL)))
          .addMethod(
            getRecordsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.RecordsRequest,
                io.provenance.metadata.v1.RecordsResponse>(
                  this, METHODID_RECORDS)))
          .addMethod(
            getRecordsAllMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.RecordsAllRequest,
                io.provenance.metadata.v1.RecordsAllResponse>(
                  this, METHODID_RECORDS_ALL)))
          .addMethod(
            getOwnershipMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.OwnershipRequest,
                io.provenance.metadata.v1.OwnershipResponse>(
                  this, METHODID_OWNERSHIP)))
          .addMethod(
            getValueOwnershipMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.ValueOwnershipRequest,
                io.provenance.metadata.v1.ValueOwnershipResponse>(
                  this, METHODID_VALUE_OWNERSHIP)))
          .addMethod(
            getScopeSpecificationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.ScopeSpecificationRequest,
                io.provenance.metadata.v1.ScopeSpecificationResponse>(
                  this, METHODID_SCOPE_SPECIFICATION)))
          .addMethod(
            getScopeSpecificationsAllMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.ScopeSpecificationsAllRequest,
                io.provenance.metadata.v1.ScopeSpecificationsAllResponse>(
                  this, METHODID_SCOPE_SPECIFICATIONS_ALL)))
          .addMethod(
            getContractSpecificationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.ContractSpecificationRequest,
                io.provenance.metadata.v1.ContractSpecificationResponse>(
                  this, METHODID_CONTRACT_SPECIFICATION)))
          .addMethod(
            getContractSpecificationsAllMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.ContractSpecificationsAllRequest,
                io.provenance.metadata.v1.ContractSpecificationsAllResponse>(
                  this, METHODID_CONTRACT_SPECIFICATIONS_ALL)))
          .addMethod(
            getRecordSpecificationsForContractSpecificationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationRequest,
                io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationResponse>(
                  this, METHODID_RECORD_SPECIFICATIONS_FOR_CONTRACT_SPECIFICATION)))
          .addMethod(
            getRecordSpecificationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.RecordSpecificationRequest,
                io.provenance.metadata.v1.RecordSpecificationResponse>(
                  this, METHODID_RECORD_SPECIFICATION)))
          .addMethod(
            getRecordSpecificationsAllMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.RecordSpecificationsAllRequest,
                io.provenance.metadata.v1.RecordSpecificationsAllResponse>(
                  this, METHODID_RECORD_SPECIFICATIONS_ALL)))
          .addMethod(
            getOSLocatorParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.OSLocatorParamsRequest,
                io.provenance.metadata.v1.OSLocatorParamsResponse>(
                  this, METHODID_OSLOCATOR_PARAMS)))
          .addMethod(
            getOSLocatorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.OSLocatorRequest,
                io.provenance.metadata.v1.OSLocatorResponse>(
                  this, METHODID_OSLOCATOR)))
          .addMethod(
            getOSLocatorsByURIMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.OSLocatorsByURIRequest,
                io.provenance.metadata.v1.OSLocatorsByURIResponse>(
                  this, METHODID_OSLOCATORS_BY_URI)))
          .addMethod(
            getOSLocatorsByScopeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.OSLocatorsByScopeRequest,
                io.provenance.metadata.v1.OSLocatorsByScopeResponse>(
                  this, METHODID_OSLOCATORS_BY_SCOPE)))
          .addMethod(
            getOSAllLocatorsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.OSAllLocatorsRequest,
                io.provenance.metadata.v1.OSAllLocatorsResponse>(
                  this, METHODID_OSALL_LOCATORS)))
          .build();
    }
  }

  /**
   * <pre>
   * Query defines the Metadata Query service.
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
     * Params queries the parameters of x/metadata module.
     * </pre>
     */
    public void params(io.provenance.metadata.v1.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Scope searches for a scope.
     * The scope id, if provided, must either be scope uuid, e.g. 91978ba2-5f35-459a-86a7-feca1b0512e0 or a scope address,
     * e.g. scope1qzge0zaztu65tx5x5llv5xc9ztsqxlkwel. The session addr, if provided, must be a bech32 session address,
     * e.g. session1qxge0zaztu65tx5x5llv5xc9zts9sqlch3sxwn44j50jzgt8rshvqyfrjcr. The record_addr, if provided, must be a
     * bech32 record address, e.g. record1q2ge0zaztu65tx5x5llv5xc9ztsw42dq2jdvmdazuwzcaddhh8gmu3mcze3.
     * * If only a scope_id is provided, that scope is returned.
     * * If only a session_addr is provided, the scope containing that session is returned.
     * * If only a record_addr is provided, the scope containing that record is returned.
     * * If more than one of scope_id, session_addr, and record_addr are provided, and they don't refer to the same scope,
     * a bad request is returned.
     * Providing a session addr or record addr does not limit the sessions and records returned (if requested).
     * Those parameters are only used to find the scope.
     * By default, sessions and records are not included.
     * Set include_sessions and/or include_records to true to include sessions and/or records.
     * </pre>
     */
    public void scope(io.provenance.metadata.v1.ScopeRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.ScopeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getScopeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ScopesAll retrieves all scopes.
     * </pre>
     */
    public void scopesAll(io.provenance.metadata.v1.ScopesAllRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.ScopesAllResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getScopesAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Sessions searches for sessions.
     * The scope_id can either be scope uuid, e.g. 91978ba2-5f35-459a-86a7-feca1b0512e0 or a scope address, e.g.
     * scope1qzge0zaztu65tx5x5llv5xc9ztsqxlkwel. Similarly, the session_id can either be a uuid or session address, e.g.
     * session1qxge0zaztu65tx5x5llv5xc9zts9sqlch3sxwn44j50jzgt8rshvqyfrjcr. The record_addr, if provided, must be a
     * bech32 record address, e.g. record1q2ge0zaztu65tx5x5llv5xc9ztsw42dq2jdvmdazuwzcaddhh8gmu3mcze3.
     * * If only a scope_id is provided, all sessions in that scope are returned.
     * * If only a session_id is provided, it must be an address, and that single session is returned.
     * * If the session_id is a uuid, then either a scope_id or record_addr must also be provided, and that single session
     * is returned.
     * * If only a record_addr is provided, the session containing that record will be returned.
     * * If a record_name is provided then either a scope_id, session_id as an address, or record_addr must also be
     * provided, and the session containing that record will be returned.
     * A bad request is returned if:
     * * The session_id is a uuid and is provided without a scope_id or record_addr.
     * * A record_name is provided without any way to identify the scope (e.g. a scope_id, a session_id as an address, or
     * a record_addr).
     * * Two or more of scope_id, session_id as an address, and record_addr are provided and don't all refer to the same
     * scope.
     * * A record_addr (or scope_id and record_name) is provided with a session_id and that session does not contain such
     * a record.
     * * A record_addr and record_name are both provided, but reference different records.
     * By default, the scope and records are not included.
     * Set include_scope and/or include_records to true to include the scope and/or records.
     * </pre>
     */
    public void sessions(io.provenance.metadata.v1.SessionsRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.SessionsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSessionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SessionsAll retrieves all sessions.
     * </pre>
     */
    public void sessionsAll(io.provenance.metadata.v1.SessionsAllRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.SessionsAllResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSessionsAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Records searches for records.
     * The record_addr, if provided, must be a bech32 record address, e.g.
     * record1q2ge0zaztu65tx5x5llv5xc9ztsw42dq2jdvmdazuwzcaddhh8gmu3mcze3. The scope-id can either be scope uuid, e.g.
     * 91978ba2-5f35-459a-86a7-feca1b0512e0 or a scope address, e.g. scope1qzge0zaztu65tx5x5llv5xc9ztsqxlkwel. Similarly,
     * the session_id can either be a uuid or session address, e.g.
     * session1qxge0zaztu65tx5x5llv5xc9zts9sqlch3sxwn44j50jzgt8rshvqyfrjcr. The name is the name of the record you're
     * interested in.
     * * If only a record_addr is provided, that single record will be returned.
     * * If only a scope_id is provided, all records in that scope will be returned.
     * * If only a session_id (or scope_id/session_id), all records in that session will be returned.
     * * If a name is provided with a scope_id and/or session_id, that single record will be returned.
     * A bad request is returned if:
     * * The session_id is a uuid and no scope_id is provided.
     * * There are two or more of record_addr, session_id, and scope_id, and they don't all refer to the same scope.
     * * A name is provided, but not a scope_id and/or a session_id.
     * * A name and record_addr are provided and the name doesn't match the record_addr.
     * By default, the scope and sessions are not included.
     * Set include_scope and/or include_sessions to true to include the scope and/or sessions.
     * </pre>
     */
    public void records(io.provenance.metadata.v1.RecordsRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.RecordsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRecordsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RecordsAll retrieves all records.
     * </pre>
     */
    public void recordsAll(io.provenance.metadata.v1.RecordsAllRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.RecordsAllResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRecordsAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Ownership returns the scope identifiers that list the given address as either a data or value owner.
     * </pre>
     */
    public void ownership(io.provenance.metadata.v1.OwnershipRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.OwnershipResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOwnershipMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ValueOwnership returns the scope identifiers that list the given address as the value owner.
     * </pre>
     */
    public void valueOwnership(io.provenance.metadata.v1.ValueOwnershipRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.ValueOwnershipResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getValueOwnershipMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ScopeSpecification returns a scope specification for the given specification id.
     * The specification_id can either be a uuid, e.g. dc83ea70-eacd-40fe-9adf-1cf6148bf8a2 or a bech32 scope
     * specification address, e.g. scopespec1qnwg86nsatx5pl56muw0v9ytlz3qu3jx6m.
     * </pre>
     */
    public void scopeSpecification(io.provenance.metadata.v1.ScopeSpecificationRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.ScopeSpecificationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getScopeSpecificationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ScopeSpecificationsAll retrieves all scope specifications.
     * </pre>
     */
    public void scopeSpecificationsAll(io.provenance.metadata.v1.ScopeSpecificationsAllRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.ScopeSpecificationsAllResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getScopeSpecificationsAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ContractSpecification returns a contract specification for the given specification id.
     * The specification_id can either be a uuid, e.g. def6bc0a-c9dd-4874-948f-5206e6060a84, a bech32 contract
     * specification address, e.g. contractspec1q000d0q2e8w5say53afqdesxp2zqzkr4fn, or a bech32 record specification
     * address, e.g. recspec1qh00d0q2e8w5say53afqdesxp2zw42dq2jdvmdazuwzcaddhh8gmuqhez44. If it is a record specification
     * address, then the contract specification that contains that record specification is looked up.
     * By default, the record specifications for this contract specification are not included.
     * Set include_record_specs to true to include them in the result.
     * </pre>
     */
    public void contractSpecification(io.provenance.metadata.v1.ContractSpecificationRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.ContractSpecificationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getContractSpecificationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ContractSpecificationsAll retrieves all contract specifications.
     * </pre>
     */
    public void contractSpecificationsAll(io.provenance.metadata.v1.ContractSpecificationsAllRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.ContractSpecificationsAllResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getContractSpecificationsAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RecordSpecificationsForContractSpecification returns the record specifications for the given input.
     * The specification_id can either be a uuid, e.g. def6bc0a-c9dd-4874-948f-5206e6060a84, a bech32 contract
     * specification address, e.g. contractspec1q000d0q2e8w5say53afqdesxp2zqzkr4fn, or a bech32 record specification
     * address, e.g. recspec1qh00d0q2e8w5say53afqdesxp2zw42dq2jdvmdazuwzcaddhh8gmuqhez44. If it is a record specification
     * address, then the contract specification that contains that record specification is used.
     * </pre>
     */
    public void recordSpecificationsForContractSpecification(io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRecordSpecificationsForContractSpecificationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RecordSpecification returns a record specification for the given input.
     * </pre>
     */
    public void recordSpecification(io.provenance.metadata.v1.RecordSpecificationRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.RecordSpecificationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRecordSpecificationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RecordSpecificationsAll retrieves all record specifications.
     * </pre>
     */
    public void recordSpecificationsAll(io.provenance.metadata.v1.RecordSpecificationsAllRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.RecordSpecificationsAllResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRecordSpecificationsAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * OSLocatorParams returns all parameters for the object store locator sub module.
     * </pre>
     */
    public void oSLocatorParams(io.provenance.metadata.v1.OSLocatorParamsRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.OSLocatorParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOSLocatorParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * OSLocator returns an ObjectStoreLocator by its owner's address.
     * </pre>
     */
    public void oSLocator(io.provenance.metadata.v1.OSLocatorRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.OSLocatorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOSLocatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * OSLocatorsByURI returns all ObjectStoreLocator entries for a locator uri.
     * </pre>
     */
    public void oSLocatorsByURI(io.provenance.metadata.v1.OSLocatorsByURIRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.OSLocatorsByURIResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOSLocatorsByURIMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * OSLocatorsByScope returns all ObjectStoreLocator entries for a for all signer's present in the specified scope.
     * </pre>
     */
    public void oSLocatorsByScope(io.provenance.metadata.v1.OSLocatorsByScopeRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.OSLocatorsByScopeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOSLocatorsByScopeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * OSAllLocators returns all ObjectStoreLocator entries.
     * </pre>
     */
    public void oSAllLocators(io.provenance.metadata.v1.OSAllLocatorsRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.OSAllLocatorsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOSAllLocatorsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query defines the Metadata Query service.
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
     * Params queries the parameters of x/metadata module.
     * </pre>
     */
    public io.provenance.metadata.v1.QueryParamsResponse params(io.provenance.metadata.v1.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Scope searches for a scope.
     * The scope id, if provided, must either be scope uuid, e.g. 91978ba2-5f35-459a-86a7-feca1b0512e0 or a scope address,
     * e.g. scope1qzge0zaztu65tx5x5llv5xc9ztsqxlkwel. The session addr, if provided, must be a bech32 session address,
     * e.g. session1qxge0zaztu65tx5x5llv5xc9zts9sqlch3sxwn44j50jzgt8rshvqyfrjcr. The record_addr, if provided, must be a
     * bech32 record address, e.g. record1q2ge0zaztu65tx5x5llv5xc9ztsw42dq2jdvmdazuwzcaddhh8gmu3mcze3.
     * * If only a scope_id is provided, that scope is returned.
     * * If only a session_addr is provided, the scope containing that session is returned.
     * * If only a record_addr is provided, the scope containing that record is returned.
     * * If more than one of scope_id, session_addr, and record_addr are provided, and they don't refer to the same scope,
     * a bad request is returned.
     * Providing a session addr or record addr does not limit the sessions and records returned (if requested).
     * Those parameters are only used to find the scope.
     * By default, sessions and records are not included.
     * Set include_sessions and/or include_records to true to include sessions and/or records.
     * </pre>
     */
    public io.provenance.metadata.v1.ScopeResponse scope(io.provenance.metadata.v1.ScopeRequest request) {
      return blockingUnaryCall(
          getChannel(), getScopeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ScopesAll retrieves all scopes.
     * </pre>
     */
    public io.provenance.metadata.v1.ScopesAllResponse scopesAll(io.provenance.metadata.v1.ScopesAllRequest request) {
      return blockingUnaryCall(
          getChannel(), getScopesAllMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Sessions searches for sessions.
     * The scope_id can either be scope uuid, e.g. 91978ba2-5f35-459a-86a7-feca1b0512e0 or a scope address, e.g.
     * scope1qzge0zaztu65tx5x5llv5xc9ztsqxlkwel. Similarly, the session_id can either be a uuid or session address, e.g.
     * session1qxge0zaztu65tx5x5llv5xc9zts9sqlch3sxwn44j50jzgt8rshvqyfrjcr. The record_addr, if provided, must be a
     * bech32 record address, e.g. record1q2ge0zaztu65tx5x5llv5xc9ztsw42dq2jdvmdazuwzcaddhh8gmu3mcze3.
     * * If only a scope_id is provided, all sessions in that scope are returned.
     * * If only a session_id is provided, it must be an address, and that single session is returned.
     * * If the session_id is a uuid, then either a scope_id or record_addr must also be provided, and that single session
     * is returned.
     * * If only a record_addr is provided, the session containing that record will be returned.
     * * If a record_name is provided then either a scope_id, session_id as an address, or record_addr must also be
     * provided, and the session containing that record will be returned.
     * A bad request is returned if:
     * * The session_id is a uuid and is provided without a scope_id or record_addr.
     * * A record_name is provided without any way to identify the scope (e.g. a scope_id, a session_id as an address, or
     * a record_addr).
     * * Two or more of scope_id, session_id as an address, and record_addr are provided and don't all refer to the same
     * scope.
     * * A record_addr (or scope_id and record_name) is provided with a session_id and that session does not contain such
     * a record.
     * * A record_addr and record_name are both provided, but reference different records.
     * By default, the scope and records are not included.
     * Set include_scope and/or include_records to true to include the scope and/or records.
     * </pre>
     */
    public io.provenance.metadata.v1.SessionsResponse sessions(io.provenance.metadata.v1.SessionsRequest request) {
      return blockingUnaryCall(
          getChannel(), getSessionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SessionsAll retrieves all sessions.
     * </pre>
     */
    public io.provenance.metadata.v1.SessionsAllResponse sessionsAll(io.provenance.metadata.v1.SessionsAllRequest request) {
      return blockingUnaryCall(
          getChannel(), getSessionsAllMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Records searches for records.
     * The record_addr, if provided, must be a bech32 record address, e.g.
     * record1q2ge0zaztu65tx5x5llv5xc9ztsw42dq2jdvmdazuwzcaddhh8gmu3mcze3. The scope-id can either be scope uuid, e.g.
     * 91978ba2-5f35-459a-86a7-feca1b0512e0 or a scope address, e.g. scope1qzge0zaztu65tx5x5llv5xc9ztsqxlkwel. Similarly,
     * the session_id can either be a uuid or session address, e.g.
     * session1qxge0zaztu65tx5x5llv5xc9zts9sqlch3sxwn44j50jzgt8rshvqyfrjcr. The name is the name of the record you're
     * interested in.
     * * If only a record_addr is provided, that single record will be returned.
     * * If only a scope_id is provided, all records in that scope will be returned.
     * * If only a session_id (or scope_id/session_id), all records in that session will be returned.
     * * If a name is provided with a scope_id and/or session_id, that single record will be returned.
     * A bad request is returned if:
     * * The session_id is a uuid and no scope_id is provided.
     * * There are two or more of record_addr, session_id, and scope_id, and they don't all refer to the same scope.
     * * A name is provided, but not a scope_id and/or a session_id.
     * * A name and record_addr are provided and the name doesn't match the record_addr.
     * By default, the scope and sessions are not included.
     * Set include_scope and/or include_sessions to true to include the scope and/or sessions.
     * </pre>
     */
    public io.provenance.metadata.v1.RecordsResponse records(io.provenance.metadata.v1.RecordsRequest request) {
      return blockingUnaryCall(
          getChannel(), getRecordsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RecordsAll retrieves all records.
     * </pre>
     */
    public io.provenance.metadata.v1.RecordsAllResponse recordsAll(io.provenance.metadata.v1.RecordsAllRequest request) {
      return blockingUnaryCall(
          getChannel(), getRecordsAllMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Ownership returns the scope identifiers that list the given address as either a data or value owner.
     * </pre>
     */
    public io.provenance.metadata.v1.OwnershipResponse ownership(io.provenance.metadata.v1.OwnershipRequest request) {
      return blockingUnaryCall(
          getChannel(), getOwnershipMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ValueOwnership returns the scope identifiers that list the given address as the value owner.
     * </pre>
     */
    public io.provenance.metadata.v1.ValueOwnershipResponse valueOwnership(io.provenance.metadata.v1.ValueOwnershipRequest request) {
      return blockingUnaryCall(
          getChannel(), getValueOwnershipMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ScopeSpecification returns a scope specification for the given specification id.
     * The specification_id can either be a uuid, e.g. dc83ea70-eacd-40fe-9adf-1cf6148bf8a2 or a bech32 scope
     * specification address, e.g. scopespec1qnwg86nsatx5pl56muw0v9ytlz3qu3jx6m.
     * </pre>
     */
    public io.provenance.metadata.v1.ScopeSpecificationResponse scopeSpecification(io.provenance.metadata.v1.ScopeSpecificationRequest request) {
      return blockingUnaryCall(
          getChannel(), getScopeSpecificationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ScopeSpecificationsAll retrieves all scope specifications.
     * </pre>
     */
    public io.provenance.metadata.v1.ScopeSpecificationsAllResponse scopeSpecificationsAll(io.provenance.metadata.v1.ScopeSpecificationsAllRequest request) {
      return blockingUnaryCall(
          getChannel(), getScopeSpecificationsAllMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ContractSpecification returns a contract specification for the given specification id.
     * The specification_id can either be a uuid, e.g. def6bc0a-c9dd-4874-948f-5206e6060a84, a bech32 contract
     * specification address, e.g. contractspec1q000d0q2e8w5say53afqdesxp2zqzkr4fn, or a bech32 record specification
     * address, e.g. recspec1qh00d0q2e8w5say53afqdesxp2zw42dq2jdvmdazuwzcaddhh8gmuqhez44. If it is a record specification
     * address, then the contract specification that contains that record specification is looked up.
     * By default, the record specifications for this contract specification are not included.
     * Set include_record_specs to true to include them in the result.
     * </pre>
     */
    public io.provenance.metadata.v1.ContractSpecificationResponse contractSpecification(io.provenance.metadata.v1.ContractSpecificationRequest request) {
      return blockingUnaryCall(
          getChannel(), getContractSpecificationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ContractSpecificationsAll retrieves all contract specifications.
     * </pre>
     */
    public io.provenance.metadata.v1.ContractSpecificationsAllResponse contractSpecificationsAll(io.provenance.metadata.v1.ContractSpecificationsAllRequest request) {
      return blockingUnaryCall(
          getChannel(), getContractSpecificationsAllMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RecordSpecificationsForContractSpecification returns the record specifications for the given input.
     * The specification_id can either be a uuid, e.g. def6bc0a-c9dd-4874-948f-5206e6060a84, a bech32 contract
     * specification address, e.g. contractspec1q000d0q2e8w5say53afqdesxp2zqzkr4fn, or a bech32 record specification
     * address, e.g. recspec1qh00d0q2e8w5say53afqdesxp2zw42dq2jdvmdazuwzcaddhh8gmuqhez44. If it is a record specification
     * address, then the contract specification that contains that record specification is used.
     * </pre>
     */
    public io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationResponse recordSpecificationsForContractSpecification(io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationRequest request) {
      return blockingUnaryCall(
          getChannel(), getRecordSpecificationsForContractSpecificationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RecordSpecification returns a record specification for the given input.
     * </pre>
     */
    public io.provenance.metadata.v1.RecordSpecificationResponse recordSpecification(io.provenance.metadata.v1.RecordSpecificationRequest request) {
      return blockingUnaryCall(
          getChannel(), getRecordSpecificationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RecordSpecificationsAll retrieves all record specifications.
     * </pre>
     */
    public io.provenance.metadata.v1.RecordSpecificationsAllResponse recordSpecificationsAll(io.provenance.metadata.v1.RecordSpecificationsAllRequest request) {
      return blockingUnaryCall(
          getChannel(), getRecordSpecificationsAllMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * OSLocatorParams returns all parameters for the object store locator sub module.
     * </pre>
     */
    public io.provenance.metadata.v1.OSLocatorParamsResponse oSLocatorParams(io.provenance.metadata.v1.OSLocatorParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getOSLocatorParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * OSLocator returns an ObjectStoreLocator by its owner's address.
     * </pre>
     */
    public io.provenance.metadata.v1.OSLocatorResponse oSLocator(io.provenance.metadata.v1.OSLocatorRequest request) {
      return blockingUnaryCall(
          getChannel(), getOSLocatorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * OSLocatorsByURI returns all ObjectStoreLocator entries for a locator uri.
     * </pre>
     */
    public io.provenance.metadata.v1.OSLocatorsByURIResponse oSLocatorsByURI(io.provenance.metadata.v1.OSLocatorsByURIRequest request) {
      return blockingUnaryCall(
          getChannel(), getOSLocatorsByURIMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * OSLocatorsByScope returns all ObjectStoreLocator entries for a for all signer's present in the specified scope.
     * </pre>
     */
    public io.provenance.metadata.v1.OSLocatorsByScopeResponse oSLocatorsByScope(io.provenance.metadata.v1.OSLocatorsByScopeRequest request) {
      return blockingUnaryCall(
          getChannel(), getOSLocatorsByScopeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * OSAllLocators returns all ObjectStoreLocator entries.
     * </pre>
     */
    public io.provenance.metadata.v1.OSAllLocatorsResponse oSAllLocators(io.provenance.metadata.v1.OSAllLocatorsRequest request) {
      return blockingUnaryCall(
          getChannel(), getOSAllLocatorsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query defines the Metadata Query service.
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
     * Params queries the parameters of x/metadata module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.QueryParamsResponse> params(
        io.provenance.metadata.v1.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Scope searches for a scope.
     * The scope id, if provided, must either be scope uuid, e.g. 91978ba2-5f35-459a-86a7-feca1b0512e0 or a scope address,
     * e.g. scope1qzge0zaztu65tx5x5llv5xc9ztsqxlkwel. The session addr, if provided, must be a bech32 session address,
     * e.g. session1qxge0zaztu65tx5x5llv5xc9zts9sqlch3sxwn44j50jzgt8rshvqyfrjcr. The record_addr, if provided, must be a
     * bech32 record address, e.g. record1q2ge0zaztu65tx5x5llv5xc9ztsw42dq2jdvmdazuwzcaddhh8gmu3mcze3.
     * * If only a scope_id is provided, that scope is returned.
     * * If only a session_addr is provided, the scope containing that session is returned.
     * * If only a record_addr is provided, the scope containing that record is returned.
     * * If more than one of scope_id, session_addr, and record_addr are provided, and they don't refer to the same scope,
     * a bad request is returned.
     * Providing a session addr or record addr does not limit the sessions and records returned (if requested).
     * Those parameters are only used to find the scope.
     * By default, sessions and records are not included.
     * Set include_sessions and/or include_records to true to include sessions and/or records.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.ScopeResponse> scope(
        io.provenance.metadata.v1.ScopeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getScopeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ScopesAll retrieves all scopes.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.ScopesAllResponse> scopesAll(
        io.provenance.metadata.v1.ScopesAllRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getScopesAllMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Sessions searches for sessions.
     * The scope_id can either be scope uuid, e.g. 91978ba2-5f35-459a-86a7-feca1b0512e0 or a scope address, e.g.
     * scope1qzge0zaztu65tx5x5llv5xc9ztsqxlkwel. Similarly, the session_id can either be a uuid or session address, e.g.
     * session1qxge0zaztu65tx5x5llv5xc9zts9sqlch3sxwn44j50jzgt8rshvqyfrjcr. The record_addr, if provided, must be a
     * bech32 record address, e.g. record1q2ge0zaztu65tx5x5llv5xc9ztsw42dq2jdvmdazuwzcaddhh8gmu3mcze3.
     * * If only a scope_id is provided, all sessions in that scope are returned.
     * * If only a session_id is provided, it must be an address, and that single session is returned.
     * * If the session_id is a uuid, then either a scope_id or record_addr must also be provided, and that single session
     * is returned.
     * * If only a record_addr is provided, the session containing that record will be returned.
     * * If a record_name is provided then either a scope_id, session_id as an address, or record_addr must also be
     * provided, and the session containing that record will be returned.
     * A bad request is returned if:
     * * The session_id is a uuid and is provided without a scope_id or record_addr.
     * * A record_name is provided without any way to identify the scope (e.g. a scope_id, a session_id as an address, or
     * a record_addr).
     * * Two or more of scope_id, session_id as an address, and record_addr are provided and don't all refer to the same
     * scope.
     * * A record_addr (or scope_id and record_name) is provided with a session_id and that session does not contain such
     * a record.
     * * A record_addr and record_name are both provided, but reference different records.
     * By default, the scope and records are not included.
     * Set include_scope and/or include_records to true to include the scope and/or records.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.SessionsResponse> sessions(
        io.provenance.metadata.v1.SessionsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSessionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SessionsAll retrieves all sessions.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.SessionsAllResponse> sessionsAll(
        io.provenance.metadata.v1.SessionsAllRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSessionsAllMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Records searches for records.
     * The record_addr, if provided, must be a bech32 record address, e.g.
     * record1q2ge0zaztu65tx5x5llv5xc9ztsw42dq2jdvmdazuwzcaddhh8gmu3mcze3. The scope-id can either be scope uuid, e.g.
     * 91978ba2-5f35-459a-86a7-feca1b0512e0 or a scope address, e.g. scope1qzge0zaztu65tx5x5llv5xc9ztsqxlkwel. Similarly,
     * the session_id can either be a uuid or session address, e.g.
     * session1qxge0zaztu65tx5x5llv5xc9zts9sqlch3sxwn44j50jzgt8rshvqyfrjcr. The name is the name of the record you're
     * interested in.
     * * If only a record_addr is provided, that single record will be returned.
     * * If only a scope_id is provided, all records in that scope will be returned.
     * * If only a session_id (or scope_id/session_id), all records in that session will be returned.
     * * If a name is provided with a scope_id and/or session_id, that single record will be returned.
     * A bad request is returned if:
     * * The session_id is a uuid and no scope_id is provided.
     * * There are two or more of record_addr, session_id, and scope_id, and they don't all refer to the same scope.
     * * A name is provided, but not a scope_id and/or a session_id.
     * * A name and record_addr are provided and the name doesn't match the record_addr.
     * By default, the scope and sessions are not included.
     * Set include_scope and/or include_sessions to true to include the scope and/or sessions.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.RecordsResponse> records(
        io.provenance.metadata.v1.RecordsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRecordsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RecordsAll retrieves all records.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.RecordsAllResponse> recordsAll(
        io.provenance.metadata.v1.RecordsAllRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRecordsAllMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Ownership returns the scope identifiers that list the given address as either a data or value owner.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.OwnershipResponse> ownership(
        io.provenance.metadata.v1.OwnershipRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOwnershipMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ValueOwnership returns the scope identifiers that list the given address as the value owner.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.ValueOwnershipResponse> valueOwnership(
        io.provenance.metadata.v1.ValueOwnershipRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getValueOwnershipMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ScopeSpecification returns a scope specification for the given specification id.
     * The specification_id can either be a uuid, e.g. dc83ea70-eacd-40fe-9adf-1cf6148bf8a2 or a bech32 scope
     * specification address, e.g. scopespec1qnwg86nsatx5pl56muw0v9ytlz3qu3jx6m.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.ScopeSpecificationResponse> scopeSpecification(
        io.provenance.metadata.v1.ScopeSpecificationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getScopeSpecificationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ScopeSpecificationsAll retrieves all scope specifications.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.ScopeSpecificationsAllResponse> scopeSpecificationsAll(
        io.provenance.metadata.v1.ScopeSpecificationsAllRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getScopeSpecificationsAllMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ContractSpecification returns a contract specification for the given specification id.
     * The specification_id can either be a uuid, e.g. def6bc0a-c9dd-4874-948f-5206e6060a84, a bech32 contract
     * specification address, e.g. contractspec1q000d0q2e8w5say53afqdesxp2zqzkr4fn, or a bech32 record specification
     * address, e.g. recspec1qh00d0q2e8w5say53afqdesxp2zw42dq2jdvmdazuwzcaddhh8gmuqhez44. If it is a record specification
     * address, then the contract specification that contains that record specification is looked up.
     * By default, the record specifications for this contract specification are not included.
     * Set include_record_specs to true to include them in the result.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.ContractSpecificationResponse> contractSpecification(
        io.provenance.metadata.v1.ContractSpecificationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getContractSpecificationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ContractSpecificationsAll retrieves all contract specifications.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.ContractSpecificationsAllResponse> contractSpecificationsAll(
        io.provenance.metadata.v1.ContractSpecificationsAllRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getContractSpecificationsAllMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RecordSpecificationsForContractSpecification returns the record specifications for the given input.
     * The specification_id can either be a uuid, e.g. def6bc0a-c9dd-4874-948f-5206e6060a84, a bech32 contract
     * specification address, e.g. contractspec1q000d0q2e8w5say53afqdesxp2zqzkr4fn, or a bech32 record specification
     * address, e.g. recspec1qh00d0q2e8w5say53afqdesxp2zw42dq2jdvmdazuwzcaddhh8gmuqhez44. If it is a record specification
     * address, then the contract specification that contains that record specification is used.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationResponse> recordSpecificationsForContractSpecification(
        io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRecordSpecificationsForContractSpecificationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RecordSpecification returns a record specification for the given input.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.RecordSpecificationResponse> recordSpecification(
        io.provenance.metadata.v1.RecordSpecificationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRecordSpecificationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RecordSpecificationsAll retrieves all record specifications.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.RecordSpecificationsAllResponse> recordSpecificationsAll(
        io.provenance.metadata.v1.RecordSpecificationsAllRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRecordSpecificationsAllMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * OSLocatorParams returns all parameters for the object store locator sub module.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.OSLocatorParamsResponse> oSLocatorParams(
        io.provenance.metadata.v1.OSLocatorParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOSLocatorParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * OSLocator returns an ObjectStoreLocator by its owner's address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.OSLocatorResponse> oSLocator(
        io.provenance.metadata.v1.OSLocatorRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOSLocatorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * OSLocatorsByURI returns all ObjectStoreLocator entries for a locator uri.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.OSLocatorsByURIResponse> oSLocatorsByURI(
        io.provenance.metadata.v1.OSLocatorsByURIRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOSLocatorsByURIMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * OSLocatorsByScope returns all ObjectStoreLocator entries for a for all signer's present in the specified scope.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.OSLocatorsByScopeResponse> oSLocatorsByScope(
        io.provenance.metadata.v1.OSLocatorsByScopeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOSLocatorsByScopeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * OSAllLocators returns all ObjectStoreLocator entries.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.OSAllLocatorsResponse> oSAllLocators(
        io.provenance.metadata.v1.OSAllLocatorsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOSAllLocatorsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_SCOPE = 1;
  private static final int METHODID_SCOPES_ALL = 2;
  private static final int METHODID_SESSIONS = 3;
  private static final int METHODID_SESSIONS_ALL = 4;
  private static final int METHODID_RECORDS = 5;
  private static final int METHODID_RECORDS_ALL = 6;
  private static final int METHODID_OWNERSHIP = 7;
  private static final int METHODID_VALUE_OWNERSHIP = 8;
  private static final int METHODID_SCOPE_SPECIFICATION = 9;
  private static final int METHODID_SCOPE_SPECIFICATIONS_ALL = 10;
  private static final int METHODID_CONTRACT_SPECIFICATION = 11;
  private static final int METHODID_CONTRACT_SPECIFICATIONS_ALL = 12;
  private static final int METHODID_RECORD_SPECIFICATIONS_FOR_CONTRACT_SPECIFICATION = 13;
  private static final int METHODID_RECORD_SPECIFICATION = 14;
  private static final int METHODID_RECORD_SPECIFICATIONS_ALL = 15;
  private static final int METHODID_OSLOCATOR_PARAMS = 16;
  private static final int METHODID_OSLOCATOR = 17;
  private static final int METHODID_OSLOCATORS_BY_URI = 18;
  private static final int METHODID_OSLOCATORS_BY_SCOPE = 19;
  private static final int METHODID_OSALL_LOCATORS = 20;

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
        case METHODID_PARAMS:
          serviceImpl.params((io.provenance.metadata.v1.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_SCOPE:
          serviceImpl.scope((io.provenance.metadata.v1.ScopeRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.ScopeResponse>) responseObserver);
          break;
        case METHODID_SCOPES_ALL:
          serviceImpl.scopesAll((io.provenance.metadata.v1.ScopesAllRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.ScopesAllResponse>) responseObserver);
          break;
        case METHODID_SESSIONS:
          serviceImpl.sessions((io.provenance.metadata.v1.SessionsRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.SessionsResponse>) responseObserver);
          break;
        case METHODID_SESSIONS_ALL:
          serviceImpl.sessionsAll((io.provenance.metadata.v1.SessionsAllRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.SessionsAllResponse>) responseObserver);
          break;
        case METHODID_RECORDS:
          serviceImpl.records((io.provenance.metadata.v1.RecordsRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.RecordsResponse>) responseObserver);
          break;
        case METHODID_RECORDS_ALL:
          serviceImpl.recordsAll((io.provenance.metadata.v1.RecordsAllRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.RecordsAllResponse>) responseObserver);
          break;
        case METHODID_OWNERSHIP:
          serviceImpl.ownership((io.provenance.metadata.v1.OwnershipRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.OwnershipResponse>) responseObserver);
          break;
        case METHODID_VALUE_OWNERSHIP:
          serviceImpl.valueOwnership((io.provenance.metadata.v1.ValueOwnershipRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.ValueOwnershipResponse>) responseObserver);
          break;
        case METHODID_SCOPE_SPECIFICATION:
          serviceImpl.scopeSpecification((io.provenance.metadata.v1.ScopeSpecificationRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.ScopeSpecificationResponse>) responseObserver);
          break;
        case METHODID_SCOPE_SPECIFICATIONS_ALL:
          serviceImpl.scopeSpecificationsAll((io.provenance.metadata.v1.ScopeSpecificationsAllRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.ScopeSpecificationsAllResponse>) responseObserver);
          break;
        case METHODID_CONTRACT_SPECIFICATION:
          serviceImpl.contractSpecification((io.provenance.metadata.v1.ContractSpecificationRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.ContractSpecificationResponse>) responseObserver);
          break;
        case METHODID_CONTRACT_SPECIFICATIONS_ALL:
          serviceImpl.contractSpecificationsAll((io.provenance.metadata.v1.ContractSpecificationsAllRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.ContractSpecificationsAllResponse>) responseObserver);
          break;
        case METHODID_RECORD_SPECIFICATIONS_FOR_CONTRACT_SPECIFICATION:
          serviceImpl.recordSpecificationsForContractSpecification((io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.RecordSpecificationsForContractSpecificationResponse>) responseObserver);
          break;
        case METHODID_RECORD_SPECIFICATION:
          serviceImpl.recordSpecification((io.provenance.metadata.v1.RecordSpecificationRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.RecordSpecificationResponse>) responseObserver);
          break;
        case METHODID_RECORD_SPECIFICATIONS_ALL:
          serviceImpl.recordSpecificationsAll((io.provenance.metadata.v1.RecordSpecificationsAllRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.RecordSpecificationsAllResponse>) responseObserver);
          break;
        case METHODID_OSLOCATOR_PARAMS:
          serviceImpl.oSLocatorParams((io.provenance.metadata.v1.OSLocatorParamsRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.OSLocatorParamsResponse>) responseObserver);
          break;
        case METHODID_OSLOCATOR:
          serviceImpl.oSLocator((io.provenance.metadata.v1.OSLocatorRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.OSLocatorResponse>) responseObserver);
          break;
        case METHODID_OSLOCATORS_BY_URI:
          serviceImpl.oSLocatorsByURI((io.provenance.metadata.v1.OSLocatorsByURIRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.OSLocatorsByURIResponse>) responseObserver);
          break;
        case METHODID_OSLOCATORS_BY_SCOPE:
          serviceImpl.oSLocatorsByScope((io.provenance.metadata.v1.OSLocatorsByScopeRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.OSLocatorsByScopeResponse>) responseObserver);
          break;
        case METHODID_OSALL_LOCATORS:
          serviceImpl.oSAllLocators((io.provenance.metadata.v1.OSAllLocatorsRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.OSAllLocatorsResponse>) responseObserver);
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
      return io.provenance.metadata.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getParamsMethod())
              .addMethod(getScopeMethod())
              .addMethod(getScopesAllMethod())
              .addMethod(getSessionsMethod())
              .addMethod(getSessionsAllMethod())
              .addMethod(getRecordsMethod())
              .addMethod(getRecordsAllMethod())
              .addMethod(getOwnershipMethod())
              .addMethod(getValueOwnershipMethod())
              .addMethod(getScopeSpecificationMethod())
              .addMethod(getScopeSpecificationsAllMethod())
              .addMethod(getContractSpecificationMethod())
              .addMethod(getContractSpecificationsAllMethod())
              .addMethod(getRecordSpecificationsForContractSpecificationMethod())
              .addMethod(getRecordSpecificationMethod())
              .addMethod(getRecordSpecificationsAllMethod())
              .addMethod(getOSLocatorParamsMethod())
              .addMethod(getOSLocatorMethod())
              .addMethod(getOSLocatorsByURIMethod())
              .addMethod(getOSLocatorsByScopeMethod())
              .addMethod(getOSAllLocatorsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
