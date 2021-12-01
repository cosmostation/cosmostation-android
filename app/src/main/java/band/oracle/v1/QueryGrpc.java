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
 * Query defines the gRPC querier service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: band/oracle/v1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "band.oracle.v1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryCountsRequest,
      band.oracle.v1.QueryOuterClass.QueryCountsResponse> getCountsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Counts",
      requestType = band.oracle.v1.QueryOuterClass.QueryCountsRequest.class,
      responseType = band.oracle.v1.QueryOuterClass.QueryCountsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryCountsRequest,
      band.oracle.v1.QueryOuterClass.QueryCountsResponse> getCountsMethod() {
    io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryCountsRequest, band.oracle.v1.QueryOuterClass.QueryCountsResponse> getCountsMethod;
    if ((getCountsMethod = QueryGrpc.getCountsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getCountsMethod = QueryGrpc.getCountsMethod) == null) {
          QueryGrpc.getCountsMethod = getCountsMethod =
              io.grpc.MethodDescriptor.<band.oracle.v1.QueryOuterClass.QueryCountsRequest, band.oracle.v1.QueryOuterClass.QueryCountsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Counts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryCountsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryCountsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Counts"))
              .build();
        }
      }
    }
    return getCountsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryDataRequest,
      band.oracle.v1.QueryOuterClass.QueryDataResponse> getDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Data",
      requestType = band.oracle.v1.QueryOuterClass.QueryDataRequest.class,
      responseType = band.oracle.v1.QueryOuterClass.QueryDataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryDataRequest,
      band.oracle.v1.QueryOuterClass.QueryDataResponse> getDataMethod() {
    io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryDataRequest, band.oracle.v1.QueryOuterClass.QueryDataResponse> getDataMethod;
    if ((getDataMethod = QueryGrpc.getDataMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDataMethod = QueryGrpc.getDataMethod) == null) {
          QueryGrpc.getDataMethod = getDataMethod =
              io.grpc.MethodDescriptor.<band.oracle.v1.QueryOuterClass.QueryDataRequest, band.oracle.v1.QueryOuterClass.QueryDataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Data"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryDataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryDataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Data"))
              .build();
        }
      }
    }
    return getDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryDataSourceRequest,
      band.oracle.v1.QueryOuterClass.QueryDataSourceResponse> getDataSourceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DataSource",
      requestType = band.oracle.v1.QueryOuterClass.QueryDataSourceRequest.class,
      responseType = band.oracle.v1.QueryOuterClass.QueryDataSourceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryDataSourceRequest,
      band.oracle.v1.QueryOuterClass.QueryDataSourceResponse> getDataSourceMethod() {
    io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryDataSourceRequest, band.oracle.v1.QueryOuterClass.QueryDataSourceResponse> getDataSourceMethod;
    if ((getDataSourceMethod = QueryGrpc.getDataSourceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getDataSourceMethod = QueryGrpc.getDataSourceMethod) == null) {
          QueryGrpc.getDataSourceMethod = getDataSourceMethod =
              io.grpc.MethodDescriptor.<band.oracle.v1.QueryOuterClass.QueryDataSourceRequest, band.oracle.v1.QueryOuterClass.QueryDataSourceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DataSource"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryDataSourceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryDataSourceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("DataSource"))
              .build();
        }
      }
    }
    return getDataSourceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryOracleScriptRequest,
      band.oracle.v1.QueryOuterClass.QueryOracleScriptResponse> getOracleScriptMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OracleScript",
      requestType = band.oracle.v1.QueryOuterClass.QueryOracleScriptRequest.class,
      responseType = band.oracle.v1.QueryOuterClass.QueryOracleScriptResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryOracleScriptRequest,
      band.oracle.v1.QueryOuterClass.QueryOracleScriptResponse> getOracleScriptMethod() {
    io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryOracleScriptRequest, band.oracle.v1.QueryOuterClass.QueryOracleScriptResponse> getOracleScriptMethod;
    if ((getOracleScriptMethod = QueryGrpc.getOracleScriptMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getOracleScriptMethod = QueryGrpc.getOracleScriptMethod) == null) {
          QueryGrpc.getOracleScriptMethod = getOracleScriptMethod =
              io.grpc.MethodDescriptor.<band.oracle.v1.QueryOuterClass.QueryOracleScriptRequest, band.oracle.v1.QueryOuterClass.QueryOracleScriptResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OracleScript"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryOracleScriptRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryOracleScriptResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("OracleScript"))
              .build();
        }
      }
    }
    return getOracleScriptMethod;
  }

  private static volatile io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryRequestRequest,
      band.oracle.v1.QueryOuterClass.QueryRequestResponse> getRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Request",
      requestType = band.oracle.v1.QueryOuterClass.QueryRequestRequest.class,
      responseType = band.oracle.v1.QueryOuterClass.QueryRequestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryRequestRequest,
      band.oracle.v1.QueryOuterClass.QueryRequestResponse> getRequestMethod() {
    io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryRequestRequest, band.oracle.v1.QueryOuterClass.QueryRequestResponse> getRequestMethod;
    if ((getRequestMethod = QueryGrpc.getRequestMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRequestMethod = QueryGrpc.getRequestMethod) == null) {
          QueryGrpc.getRequestMethod = getRequestMethod =
              io.grpc.MethodDescriptor.<band.oracle.v1.QueryOuterClass.QueryRequestRequest, band.oracle.v1.QueryOuterClass.QueryRequestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Request"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryRequestRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryRequestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Request"))
              .build();
        }
      }
    }
    return getRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryPendingRequestsRequest,
      band.oracle.v1.QueryOuterClass.QueryPendingRequestsResponse> getPendingRequestsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PendingRequests",
      requestType = band.oracle.v1.QueryOuterClass.QueryPendingRequestsRequest.class,
      responseType = band.oracle.v1.QueryOuterClass.QueryPendingRequestsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryPendingRequestsRequest,
      band.oracle.v1.QueryOuterClass.QueryPendingRequestsResponse> getPendingRequestsMethod() {
    io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryPendingRequestsRequest, band.oracle.v1.QueryOuterClass.QueryPendingRequestsResponse> getPendingRequestsMethod;
    if ((getPendingRequestsMethod = QueryGrpc.getPendingRequestsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getPendingRequestsMethod = QueryGrpc.getPendingRequestsMethod) == null) {
          QueryGrpc.getPendingRequestsMethod = getPendingRequestsMethod =
              io.grpc.MethodDescriptor.<band.oracle.v1.QueryOuterClass.QueryPendingRequestsRequest, band.oracle.v1.QueryOuterClass.QueryPendingRequestsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PendingRequests"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryPendingRequestsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryPendingRequestsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("PendingRequests"))
              .build();
        }
      }
    }
    return getPendingRequestsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryValidatorRequest,
      band.oracle.v1.QueryOuterClass.QueryValidatorResponse> getValidatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Validator",
      requestType = band.oracle.v1.QueryOuterClass.QueryValidatorRequest.class,
      responseType = band.oracle.v1.QueryOuterClass.QueryValidatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryValidatorRequest,
      band.oracle.v1.QueryOuterClass.QueryValidatorResponse> getValidatorMethod() {
    io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryValidatorRequest, band.oracle.v1.QueryOuterClass.QueryValidatorResponse> getValidatorMethod;
    if ((getValidatorMethod = QueryGrpc.getValidatorMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getValidatorMethod = QueryGrpc.getValidatorMethod) == null) {
          QueryGrpc.getValidatorMethod = getValidatorMethod =
              io.grpc.MethodDescriptor.<band.oracle.v1.QueryOuterClass.QueryValidatorRequest, band.oracle.v1.QueryOuterClass.QueryValidatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Validator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryValidatorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryValidatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Validator"))
              .build();
        }
      }
    }
    return getValidatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryIsReporterRequest,
      band.oracle.v1.QueryOuterClass.QueryIsReporterResponse> getIsReporterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "IsReporter",
      requestType = band.oracle.v1.QueryOuterClass.QueryIsReporterRequest.class,
      responseType = band.oracle.v1.QueryOuterClass.QueryIsReporterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryIsReporterRequest,
      band.oracle.v1.QueryOuterClass.QueryIsReporterResponse> getIsReporterMethod() {
    io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryIsReporterRequest, band.oracle.v1.QueryOuterClass.QueryIsReporterResponse> getIsReporterMethod;
    if ((getIsReporterMethod = QueryGrpc.getIsReporterMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getIsReporterMethod = QueryGrpc.getIsReporterMethod) == null) {
          QueryGrpc.getIsReporterMethod = getIsReporterMethod =
              io.grpc.MethodDescriptor.<band.oracle.v1.QueryOuterClass.QueryIsReporterRequest, band.oracle.v1.QueryOuterClass.QueryIsReporterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IsReporter"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryIsReporterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryIsReporterResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("IsReporter"))
              .build();
        }
      }
    }
    return getIsReporterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryReportersRequest,
      band.oracle.v1.QueryOuterClass.QueryReportersResponse> getReportersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Reporters",
      requestType = band.oracle.v1.QueryOuterClass.QueryReportersRequest.class,
      responseType = band.oracle.v1.QueryOuterClass.QueryReportersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryReportersRequest,
      band.oracle.v1.QueryOuterClass.QueryReportersResponse> getReportersMethod() {
    io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryReportersRequest, band.oracle.v1.QueryOuterClass.QueryReportersResponse> getReportersMethod;
    if ((getReportersMethod = QueryGrpc.getReportersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getReportersMethod = QueryGrpc.getReportersMethod) == null) {
          QueryGrpc.getReportersMethod = getReportersMethod =
              io.grpc.MethodDescriptor.<band.oracle.v1.QueryOuterClass.QueryReportersRequest, band.oracle.v1.QueryOuterClass.QueryReportersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Reporters"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryReportersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryReportersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Reporters"))
              .build();
        }
      }
    }
    return getReportersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryActiveValidatorsRequest,
      band.oracle.v1.QueryOuterClass.QueryActiveValidatorsResponse> getActiveValidatorsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ActiveValidators",
      requestType = band.oracle.v1.QueryOuterClass.QueryActiveValidatorsRequest.class,
      responseType = band.oracle.v1.QueryOuterClass.QueryActiveValidatorsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryActiveValidatorsRequest,
      band.oracle.v1.QueryOuterClass.QueryActiveValidatorsResponse> getActiveValidatorsMethod() {
    io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryActiveValidatorsRequest, band.oracle.v1.QueryOuterClass.QueryActiveValidatorsResponse> getActiveValidatorsMethod;
    if ((getActiveValidatorsMethod = QueryGrpc.getActiveValidatorsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getActiveValidatorsMethod = QueryGrpc.getActiveValidatorsMethod) == null) {
          QueryGrpc.getActiveValidatorsMethod = getActiveValidatorsMethod =
              io.grpc.MethodDescriptor.<band.oracle.v1.QueryOuterClass.QueryActiveValidatorsRequest, band.oracle.v1.QueryOuterClass.QueryActiveValidatorsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ActiveValidators"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryActiveValidatorsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryActiveValidatorsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ActiveValidators"))
              .build();
        }
      }
    }
    return getActiveValidatorsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryParamsRequest,
      band.oracle.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = band.oracle.v1.QueryOuterClass.QueryParamsRequest.class,
      responseType = band.oracle.v1.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryParamsRequest,
      band.oracle.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryParamsRequest, band.oracle.v1.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<band.oracle.v1.QueryOuterClass.QueryParamsRequest, band.oracle.v1.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryRequestSearchRequest,
      band.oracle.v1.QueryOuterClass.QueryRequestSearchResponse> getRequestSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RequestSearch",
      requestType = band.oracle.v1.QueryOuterClass.QueryRequestSearchRequest.class,
      responseType = band.oracle.v1.QueryOuterClass.QueryRequestSearchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryRequestSearchRequest,
      band.oracle.v1.QueryOuterClass.QueryRequestSearchResponse> getRequestSearchMethod() {
    io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryRequestSearchRequest, band.oracle.v1.QueryOuterClass.QueryRequestSearchResponse> getRequestSearchMethod;
    if ((getRequestSearchMethod = QueryGrpc.getRequestSearchMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRequestSearchMethod = QueryGrpc.getRequestSearchMethod) == null) {
          QueryGrpc.getRequestSearchMethod = getRequestSearchMethod =
              io.grpc.MethodDescriptor.<band.oracle.v1.QueryOuterClass.QueryRequestSearchRequest, band.oracle.v1.QueryOuterClass.QueryRequestSearchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RequestSearch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryRequestSearchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryRequestSearchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RequestSearch"))
              .build();
        }
      }
    }
    return getRequestSearchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryRequestPriceRequest,
      band.oracle.v1.QueryOuterClass.QueryRequestPriceResponse> getRequestPriceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RequestPrice",
      requestType = band.oracle.v1.QueryOuterClass.QueryRequestPriceRequest.class,
      responseType = band.oracle.v1.QueryOuterClass.QueryRequestPriceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryRequestPriceRequest,
      band.oracle.v1.QueryOuterClass.QueryRequestPriceResponse> getRequestPriceMethod() {
    io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryRequestPriceRequest, band.oracle.v1.QueryOuterClass.QueryRequestPriceResponse> getRequestPriceMethod;
    if ((getRequestPriceMethod = QueryGrpc.getRequestPriceMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRequestPriceMethod = QueryGrpc.getRequestPriceMethod) == null) {
          QueryGrpc.getRequestPriceMethod = getRequestPriceMethod =
              io.grpc.MethodDescriptor.<band.oracle.v1.QueryOuterClass.QueryRequestPriceRequest, band.oracle.v1.QueryOuterClass.QueryRequestPriceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RequestPrice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryRequestPriceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryRequestPriceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RequestPrice"))
              .build();
        }
      }
    }
    return getRequestPriceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryRequestVerificationRequest,
      band.oracle.v1.QueryOuterClass.QueryRequestVerificationResponse> getRequestVerificationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RequestVerification",
      requestType = band.oracle.v1.QueryOuterClass.QueryRequestVerificationRequest.class,
      responseType = band.oracle.v1.QueryOuterClass.QueryRequestVerificationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryRequestVerificationRequest,
      band.oracle.v1.QueryOuterClass.QueryRequestVerificationResponse> getRequestVerificationMethod() {
    io.grpc.MethodDescriptor<band.oracle.v1.QueryOuterClass.QueryRequestVerificationRequest, band.oracle.v1.QueryOuterClass.QueryRequestVerificationResponse> getRequestVerificationMethod;
    if ((getRequestVerificationMethod = QueryGrpc.getRequestVerificationMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getRequestVerificationMethod = QueryGrpc.getRequestVerificationMethod) == null) {
          QueryGrpc.getRequestVerificationMethod = getRequestVerificationMethod =
              io.grpc.MethodDescriptor.<band.oracle.v1.QueryOuterClass.QueryRequestVerificationRequest, band.oracle.v1.QueryOuterClass.QueryRequestVerificationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RequestVerification"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryRequestVerificationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  band.oracle.v1.QueryOuterClass.QueryRequestVerificationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("RequestVerification"))
              .build();
        }
      }
    }
    return getRequestVerificationMethod;
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
     * Counts queries the number of existing data sources, oracle scripts, and
     * requests.
     * </pre>
     */
    public void counts(band.oracle.v1.QueryOuterClass.QueryCountsRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryCountsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCountsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Data queries content of the data source or oracle script for given SHA256
     * file hash.
     * </pre>
     */
    public void data(band.oracle.v1.QueryOuterClass.QueryDataRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryDataResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDataMethod(), responseObserver);
    }

    /**
     * <pre>
     * DataSource queries data source summary info for given data source id.
     * </pre>
     */
    public void dataSource(band.oracle.v1.QueryOuterClass.QueryDataSourceRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryDataSourceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDataSourceMethod(), responseObserver);
    }

    /**
     * <pre>
     * OracleScript queries oracle script summary info for given oracle script id.
     * </pre>
     */
    public void oracleScript(band.oracle.v1.QueryOuterClass.QueryOracleScriptRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryOracleScriptResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOracleScriptMethod(), responseObserver);
    }

    /**
     * <pre>
     * Request queries request info for given request id.
     * </pre>
     */
    public void request(band.oracle.v1.QueryOuterClass.QueryRequestRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryRequestResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRequestMethod(), responseObserver);
    }

    /**
     * <pre>
     * PendingRequests queries list of pending request IDs assigned to given
     * validator.
     * </pre>
     */
    public void pendingRequests(band.oracle.v1.QueryOuterClass.QueryPendingRequestsRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryPendingRequestsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPendingRequestsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Validator queries properties of given validator address.
     * </pre>
     */
    public void validator(band.oracle.v1.QueryOuterClass.QueryValidatorRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryValidatorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getValidatorMethod(), responseObserver);
    }

    /**
     * <pre>
     * IsReporter queries grant of account on this validator.
     * </pre>
     */
    public void isReporter(band.oracle.v1.QueryOuterClass.QueryIsReporterRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryIsReporterResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getIsReporterMethod(), responseObserver);
    }

    /**
     * <pre>
     * Reporters queries all reporters associated with given validator address.
     * </pre>
     */
    public void reporters(band.oracle.v1.QueryOuterClass.QueryReportersRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryReportersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReportersMethod(), responseObserver);
    }

    /**
     * <pre>
     * ActiveValidators queries all active oracle validators.
     * </pre>
     */
    public void activeValidators(band.oracle.v1.QueryOuterClass.QueryActiveValidatorsRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryActiveValidatorsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getActiveValidatorsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Params queries parameters used for runnning bandchain network.
     * </pre>
     */
    public void params(band.oracle.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * RequestSearch queries the latest request that match search criteria.
     * </pre>
     */
    public void requestSearch(band.oracle.v1.QueryOuterClass.QueryRequestSearchRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryRequestSearchResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRequestSearchMethod(), responseObserver);
    }

    /**
     * <pre>
     * RequestPrice queries the latest price on standard price reference oracle
     * script.
     * </pre>
     */
    public void requestPrice(band.oracle.v1.QueryOuterClass.QueryRequestPriceRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryRequestPriceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRequestPriceMethod(), responseObserver);
    }

    /**
     * <pre>
     * RequestVerification verifies a request to make sure that
     * all information that will be used to report the data is valid
     * </pre>
     */
    public void requestVerification(band.oracle.v1.QueryOuterClass.QueryRequestVerificationRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryRequestVerificationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRequestVerificationMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCountsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                band.oracle.v1.QueryOuterClass.QueryCountsRequest,
                band.oracle.v1.QueryOuterClass.QueryCountsResponse>(
                  this, METHODID_COUNTS)))
          .addMethod(
            getDataMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                band.oracle.v1.QueryOuterClass.QueryDataRequest,
                band.oracle.v1.QueryOuterClass.QueryDataResponse>(
                  this, METHODID_DATA)))
          .addMethod(
            getDataSourceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                band.oracle.v1.QueryOuterClass.QueryDataSourceRequest,
                band.oracle.v1.QueryOuterClass.QueryDataSourceResponse>(
                  this, METHODID_DATA_SOURCE)))
          .addMethod(
            getOracleScriptMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                band.oracle.v1.QueryOuterClass.QueryOracleScriptRequest,
                band.oracle.v1.QueryOuterClass.QueryOracleScriptResponse>(
                  this, METHODID_ORACLE_SCRIPT)))
          .addMethod(
            getRequestMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                band.oracle.v1.QueryOuterClass.QueryRequestRequest,
                band.oracle.v1.QueryOuterClass.QueryRequestResponse>(
                  this, METHODID_REQUEST)))
          .addMethod(
            getPendingRequestsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                band.oracle.v1.QueryOuterClass.QueryPendingRequestsRequest,
                band.oracle.v1.QueryOuterClass.QueryPendingRequestsResponse>(
                  this, METHODID_PENDING_REQUESTS)))
          .addMethod(
            getValidatorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                band.oracle.v1.QueryOuterClass.QueryValidatorRequest,
                band.oracle.v1.QueryOuterClass.QueryValidatorResponse>(
                  this, METHODID_VALIDATOR)))
          .addMethod(
            getIsReporterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                band.oracle.v1.QueryOuterClass.QueryIsReporterRequest,
                band.oracle.v1.QueryOuterClass.QueryIsReporterResponse>(
                  this, METHODID_IS_REPORTER)))
          .addMethod(
            getReportersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                band.oracle.v1.QueryOuterClass.QueryReportersRequest,
                band.oracle.v1.QueryOuterClass.QueryReportersResponse>(
                  this, METHODID_REPORTERS)))
          .addMethod(
            getActiveValidatorsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                band.oracle.v1.QueryOuterClass.QueryActiveValidatorsRequest,
                band.oracle.v1.QueryOuterClass.QueryActiveValidatorsResponse>(
                  this, METHODID_ACTIVE_VALIDATORS)))
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                band.oracle.v1.QueryOuterClass.QueryParamsRequest,
                band.oracle.v1.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getRequestSearchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                band.oracle.v1.QueryOuterClass.QueryRequestSearchRequest,
                band.oracle.v1.QueryOuterClass.QueryRequestSearchResponse>(
                  this, METHODID_REQUEST_SEARCH)))
          .addMethod(
            getRequestPriceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                band.oracle.v1.QueryOuterClass.QueryRequestPriceRequest,
                band.oracle.v1.QueryOuterClass.QueryRequestPriceResponse>(
                  this, METHODID_REQUEST_PRICE)))
          .addMethod(
            getRequestVerificationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                band.oracle.v1.QueryOuterClass.QueryRequestVerificationRequest,
                band.oracle.v1.QueryOuterClass.QueryRequestVerificationResponse>(
                  this, METHODID_REQUEST_VERIFICATION)))
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
     * Counts queries the number of existing data sources, oracle scripts, and
     * requests.
     * </pre>
     */
    public void counts(band.oracle.v1.QueryOuterClass.QueryCountsRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryCountsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCountsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Data queries content of the data source or oracle script for given SHA256
     * file hash.
     * </pre>
     */
    public void data(band.oracle.v1.QueryOuterClass.QueryDataRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryDataResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DataSource queries data source summary info for given data source id.
     * </pre>
     */
    public void dataSource(band.oracle.v1.QueryOuterClass.QueryDataSourceRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryDataSourceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDataSourceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * OracleScript queries oracle script summary info for given oracle script id.
     * </pre>
     */
    public void oracleScript(band.oracle.v1.QueryOuterClass.QueryOracleScriptRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryOracleScriptResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOracleScriptMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Request queries request info for given request id.
     * </pre>
     */
    public void request(band.oracle.v1.QueryOuterClass.QueryRequestRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryRequestResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PendingRequests queries list of pending request IDs assigned to given
     * validator.
     * </pre>
     */
    public void pendingRequests(band.oracle.v1.QueryOuterClass.QueryPendingRequestsRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryPendingRequestsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPendingRequestsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Validator queries properties of given validator address.
     * </pre>
     */
    public void validator(band.oracle.v1.QueryOuterClass.QueryValidatorRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryValidatorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getValidatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * IsReporter queries grant of account on this validator.
     * </pre>
     */
    public void isReporter(band.oracle.v1.QueryOuterClass.QueryIsReporterRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryIsReporterResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIsReporterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Reporters queries all reporters associated with given validator address.
     * </pre>
     */
    public void reporters(band.oracle.v1.QueryOuterClass.QueryReportersRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryReportersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReportersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ActiveValidators queries all active oracle validators.
     * </pre>
     */
    public void activeValidators(band.oracle.v1.QueryOuterClass.QueryActiveValidatorsRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryActiveValidatorsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getActiveValidatorsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Params queries parameters used for runnning bandchain network.
     * </pre>
     */
    public void params(band.oracle.v1.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RequestSearch queries the latest request that match search criteria.
     * </pre>
     */
    public void requestSearch(band.oracle.v1.QueryOuterClass.QueryRequestSearchRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryRequestSearchResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRequestSearchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RequestPrice queries the latest price on standard price reference oracle
     * script.
     * </pre>
     */
    public void requestPrice(band.oracle.v1.QueryOuterClass.QueryRequestPriceRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryRequestPriceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRequestPriceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RequestVerification verifies a request to make sure that
     * all information that will be used to report the data is valid
     * </pre>
     */
    public void requestVerification(band.oracle.v1.QueryOuterClass.QueryRequestVerificationRequest request,
        io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryRequestVerificationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRequestVerificationMethod(), getCallOptions()), request, responseObserver);
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
     * Counts queries the number of existing data sources, oracle scripts, and
     * requests.
     * </pre>
     */
    public band.oracle.v1.QueryOuterClass.QueryCountsResponse counts(band.oracle.v1.QueryOuterClass.QueryCountsRequest request) {
      return blockingUnaryCall(
          getChannel(), getCountsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Data queries content of the data source or oracle script for given SHA256
     * file hash.
     * </pre>
     */
    public band.oracle.v1.QueryOuterClass.QueryDataResponse data(band.oracle.v1.QueryOuterClass.QueryDataRequest request) {
      return blockingUnaryCall(
          getChannel(), getDataMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DataSource queries data source summary info for given data source id.
     * </pre>
     */
    public band.oracle.v1.QueryOuterClass.QueryDataSourceResponse dataSource(band.oracle.v1.QueryOuterClass.QueryDataSourceRequest request) {
      return blockingUnaryCall(
          getChannel(), getDataSourceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * OracleScript queries oracle script summary info for given oracle script id.
     * </pre>
     */
    public band.oracle.v1.QueryOuterClass.QueryOracleScriptResponse oracleScript(band.oracle.v1.QueryOuterClass.QueryOracleScriptRequest request) {
      return blockingUnaryCall(
          getChannel(), getOracleScriptMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Request queries request info for given request id.
     * </pre>
     */
    public band.oracle.v1.QueryOuterClass.QueryRequestResponse request(band.oracle.v1.QueryOuterClass.QueryRequestRequest request) {
      return blockingUnaryCall(
          getChannel(), getRequestMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PendingRequests queries list of pending request IDs assigned to given
     * validator.
     * </pre>
     */
    public band.oracle.v1.QueryOuterClass.QueryPendingRequestsResponse pendingRequests(band.oracle.v1.QueryOuterClass.QueryPendingRequestsRequest request) {
      return blockingUnaryCall(
          getChannel(), getPendingRequestsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Validator queries properties of given validator address.
     * </pre>
     */
    public band.oracle.v1.QueryOuterClass.QueryValidatorResponse validator(band.oracle.v1.QueryOuterClass.QueryValidatorRequest request) {
      return blockingUnaryCall(
          getChannel(), getValidatorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * IsReporter queries grant of account on this validator.
     * </pre>
     */
    public band.oracle.v1.QueryOuterClass.QueryIsReporterResponse isReporter(band.oracle.v1.QueryOuterClass.QueryIsReporterRequest request) {
      return blockingUnaryCall(
          getChannel(), getIsReporterMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Reporters queries all reporters associated with given validator address.
     * </pre>
     */
    public band.oracle.v1.QueryOuterClass.QueryReportersResponse reporters(band.oracle.v1.QueryOuterClass.QueryReportersRequest request) {
      return blockingUnaryCall(
          getChannel(), getReportersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ActiveValidators queries all active oracle validators.
     * </pre>
     */
    public band.oracle.v1.QueryOuterClass.QueryActiveValidatorsResponse activeValidators(band.oracle.v1.QueryOuterClass.QueryActiveValidatorsRequest request) {
      return blockingUnaryCall(
          getChannel(), getActiveValidatorsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Params queries parameters used for runnning bandchain network.
     * </pre>
     */
    public band.oracle.v1.QueryOuterClass.QueryParamsResponse params(band.oracle.v1.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RequestSearch queries the latest request that match search criteria.
     * </pre>
     */
    public band.oracle.v1.QueryOuterClass.QueryRequestSearchResponse requestSearch(band.oracle.v1.QueryOuterClass.QueryRequestSearchRequest request) {
      return blockingUnaryCall(
          getChannel(), getRequestSearchMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RequestPrice queries the latest price on standard price reference oracle
     * script.
     * </pre>
     */
    public band.oracle.v1.QueryOuterClass.QueryRequestPriceResponse requestPrice(band.oracle.v1.QueryOuterClass.QueryRequestPriceRequest request) {
      return blockingUnaryCall(
          getChannel(), getRequestPriceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RequestVerification verifies a request to make sure that
     * all information that will be used to report the data is valid
     * </pre>
     */
    public band.oracle.v1.QueryOuterClass.QueryRequestVerificationResponse requestVerification(band.oracle.v1.QueryOuterClass.QueryRequestVerificationRequest request) {
      return blockingUnaryCall(
          getChannel(), getRequestVerificationMethod(), getCallOptions(), request);
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
     * Counts queries the number of existing data sources, oracle scripts, and
     * requests.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<band.oracle.v1.QueryOuterClass.QueryCountsResponse> counts(
        band.oracle.v1.QueryOuterClass.QueryCountsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCountsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Data queries content of the data source or oracle script for given SHA256
     * file hash.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<band.oracle.v1.QueryOuterClass.QueryDataResponse> data(
        band.oracle.v1.QueryOuterClass.QueryDataRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDataMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DataSource queries data source summary info for given data source id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<band.oracle.v1.QueryOuterClass.QueryDataSourceResponse> dataSource(
        band.oracle.v1.QueryOuterClass.QueryDataSourceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDataSourceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * OracleScript queries oracle script summary info for given oracle script id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<band.oracle.v1.QueryOuterClass.QueryOracleScriptResponse> oracleScript(
        band.oracle.v1.QueryOuterClass.QueryOracleScriptRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOracleScriptMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Request queries request info for given request id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<band.oracle.v1.QueryOuterClass.QueryRequestResponse> request(
        band.oracle.v1.QueryOuterClass.QueryRequestRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRequestMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PendingRequests queries list of pending request IDs assigned to given
     * validator.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<band.oracle.v1.QueryOuterClass.QueryPendingRequestsResponse> pendingRequests(
        band.oracle.v1.QueryOuterClass.QueryPendingRequestsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPendingRequestsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Validator queries properties of given validator address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<band.oracle.v1.QueryOuterClass.QueryValidatorResponse> validator(
        band.oracle.v1.QueryOuterClass.QueryValidatorRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getValidatorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * IsReporter queries grant of account on this validator.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<band.oracle.v1.QueryOuterClass.QueryIsReporterResponse> isReporter(
        band.oracle.v1.QueryOuterClass.QueryIsReporterRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getIsReporterMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Reporters queries all reporters associated with given validator address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<band.oracle.v1.QueryOuterClass.QueryReportersResponse> reporters(
        band.oracle.v1.QueryOuterClass.QueryReportersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getReportersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ActiveValidators queries all active oracle validators.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<band.oracle.v1.QueryOuterClass.QueryActiveValidatorsResponse> activeValidators(
        band.oracle.v1.QueryOuterClass.QueryActiveValidatorsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getActiveValidatorsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Params queries parameters used for runnning bandchain network.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<band.oracle.v1.QueryOuterClass.QueryParamsResponse> params(
        band.oracle.v1.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RequestSearch queries the latest request that match search criteria.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<band.oracle.v1.QueryOuterClass.QueryRequestSearchResponse> requestSearch(
        band.oracle.v1.QueryOuterClass.QueryRequestSearchRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRequestSearchMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RequestPrice queries the latest price on standard price reference oracle
     * script.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<band.oracle.v1.QueryOuterClass.QueryRequestPriceResponse> requestPrice(
        band.oracle.v1.QueryOuterClass.QueryRequestPriceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRequestPriceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RequestVerification verifies a request to make sure that
     * all information that will be used to report the data is valid
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<band.oracle.v1.QueryOuterClass.QueryRequestVerificationResponse> requestVerification(
        band.oracle.v1.QueryOuterClass.QueryRequestVerificationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRequestVerificationMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_COUNTS = 0;
  private static final int METHODID_DATA = 1;
  private static final int METHODID_DATA_SOURCE = 2;
  private static final int METHODID_ORACLE_SCRIPT = 3;
  private static final int METHODID_REQUEST = 4;
  private static final int METHODID_PENDING_REQUESTS = 5;
  private static final int METHODID_VALIDATOR = 6;
  private static final int METHODID_IS_REPORTER = 7;
  private static final int METHODID_REPORTERS = 8;
  private static final int METHODID_ACTIVE_VALIDATORS = 9;
  private static final int METHODID_PARAMS = 10;
  private static final int METHODID_REQUEST_SEARCH = 11;
  private static final int METHODID_REQUEST_PRICE = 12;
  private static final int METHODID_REQUEST_VERIFICATION = 13;

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
        case METHODID_COUNTS:
          serviceImpl.counts((band.oracle.v1.QueryOuterClass.QueryCountsRequest) request,
              (io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryCountsResponse>) responseObserver);
          break;
        case METHODID_DATA:
          serviceImpl.data((band.oracle.v1.QueryOuterClass.QueryDataRequest) request,
              (io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryDataResponse>) responseObserver);
          break;
        case METHODID_DATA_SOURCE:
          serviceImpl.dataSource((band.oracle.v1.QueryOuterClass.QueryDataSourceRequest) request,
              (io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryDataSourceResponse>) responseObserver);
          break;
        case METHODID_ORACLE_SCRIPT:
          serviceImpl.oracleScript((band.oracle.v1.QueryOuterClass.QueryOracleScriptRequest) request,
              (io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryOracleScriptResponse>) responseObserver);
          break;
        case METHODID_REQUEST:
          serviceImpl.request((band.oracle.v1.QueryOuterClass.QueryRequestRequest) request,
              (io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryRequestResponse>) responseObserver);
          break;
        case METHODID_PENDING_REQUESTS:
          serviceImpl.pendingRequests((band.oracle.v1.QueryOuterClass.QueryPendingRequestsRequest) request,
              (io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryPendingRequestsResponse>) responseObserver);
          break;
        case METHODID_VALIDATOR:
          serviceImpl.validator((band.oracle.v1.QueryOuterClass.QueryValidatorRequest) request,
              (io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryValidatorResponse>) responseObserver);
          break;
        case METHODID_IS_REPORTER:
          serviceImpl.isReporter((band.oracle.v1.QueryOuterClass.QueryIsReporterRequest) request,
              (io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryIsReporterResponse>) responseObserver);
          break;
        case METHODID_REPORTERS:
          serviceImpl.reporters((band.oracle.v1.QueryOuterClass.QueryReportersRequest) request,
              (io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryReportersResponse>) responseObserver);
          break;
        case METHODID_ACTIVE_VALIDATORS:
          serviceImpl.activeValidators((band.oracle.v1.QueryOuterClass.QueryActiveValidatorsRequest) request,
              (io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryActiveValidatorsResponse>) responseObserver);
          break;
        case METHODID_PARAMS:
          serviceImpl.params((band.oracle.v1.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_REQUEST_SEARCH:
          serviceImpl.requestSearch((band.oracle.v1.QueryOuterClass.QueryRequestSearchRequest) request,
              (io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryRequestSearchResponse>) responseObserver);
          break;
        case METHODID_REQUEST_PRICE:
          serviceImpl.requestPrice((band.oracle.v1.QueryOuterClass.QueryRequestPriceRequest) request,
              (io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryRequestPriceResponse>) responseObserver);
          break;
        case METHODID_REQUEST_VERIFICATION:
          serviceImpl.requestVerification((band.oracle.v1.QueryOuterClass.QueryRequestVerificationRequest) request,
              (io.grpc.stub.StreamObserver<band.oracle.v1.QueryOuterClass.QueryRequestVerificationResponse>) responseObserver);
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
      return band.oracle.v1.QueryOuterClass.getDescriptor();
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
              .addMethod(getCountsMethod())
              .addMethod(getDataMethod())
              .addMethod(getDataSourceMethod())
              .addMethod(getOracleScriptMethod())
              .addMethod(getRequestMethod())
              .addMethod(getPendingRequestsMethod())
              .addMethod(getValidatorMethod())
              .addMethod(getIsReporterMethod())
              .addMethod(getReportersMethod())
              .addMethod(getActiveValidatorsMethod())
              .addMethod(getParamsMethod())
              .addMethod(getRequestSearchMethod())
              .addMethod(getRequestPriceMethod())
              .addMethod(getRequestVerificationMethod())
              .build();
        }
      }
    }
    return result;
  }
}
