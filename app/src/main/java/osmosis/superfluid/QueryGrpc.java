package osmosis.superfluid;

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
    comments = "Source: osmosis/superfluid/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "osmosis.superfluid.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.QueryParamsRequest,
      osmosis.superfluid.QueryOuterClass.QueryParamsResponse> getParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Params",
      requestType = osmosis.superfluid.QueryOuterClass.QueryParamsRequest.class,
      responseType = osmosis.superfluid.QueryOuterClass.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.QueryParamsRequest,
      osmosis.superfluid.QueryOuterClass.QueryParamsResponse> getParamsMethod() {
    io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.QueryParamsRequest, osmosis.superfluid.QueryOuterClass.QueryParamsResponse> getParamsMethod;
    if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getParamsMethod = QueryGrpc.getParamsMethod) == null) {
          QueryGrpc.getParamsMethod = getParamsMethod =
              io.grpc.MethodDescriptor.<osmosis.superfluid.QueryOuterClass.QueryParamsRequest, osmosis.superfluid.QueryOuterClass.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Params"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Params"))
              .build();
        }
      }
    }
    return getParamsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.AssetTypeRequest,
      osmosis.superfluid.QueryOuterClass.AssetTypeResponse> getAssetTypeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AssetType",
      requestType = osmosis.superfluid.QueryOuterClass.AssetTypeRequest.class,
      responseType = osmosis.superfluid.QueryOuterClass.AssetTypeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.AssetTypeRequest,
      osmosis.superfluid.QueryOuterClass.AssetTypeResponse> getAssetTypeMethod() {
    io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.AssetTypeRequest, osmosis.superfluid.QueryOuterClass.AssetTypeResponse> getAssetTypeMethod;
    if ((getAssetTypeMethod = QueryGrpc.getAssetTypeMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAssetTypeMethod = QueryGrpc.getAssetTypeMethod) == null) {
          QueryGrpc.getAssetTypeMethod = getAssetTypeMethod =
              io.grpc.MethodDescriptor.<osmosis.superfluid.QueryOuterClass.AssetTypeRequest, osmosis.superfluid.QueryOuterClass.AssetTypeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AssetType"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.AssetTypeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.AssetTypeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AssetType"))
              .build();
        }
      }
    }
    return getAssetTypeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.AllAssetsRequest,
      osmosis.superfluid.QueryOuterClass.AllAssetsResponse> getAllAssetsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllAssets",
      requestType = osmosis.superfluid.QueryOuterClass.AllAssetsRequest.class,
      responseType = osmosis.superfluid.QueryOuterClass.AllAssetsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.AllAssetsRequest,
      osmosis.superfluid.QueryOuterClass.AllAssetsResponse> getAllAssetsMethod() {
    io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.AllAssetsRequest, osmosis.superfluid.QueryOuterClass.AllAssetsResponse> getAllAssetsMethod;
    if ((getAllAssetsMethod = QueryGrpc.getAllAssetsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAllAssetsMethod = QueryGrpc.getAllAssetsMethod) == null) {
          QueryGrpc.getAllAssetsMethod = getAllAssetsMethod =
              io.grpc.MethodDescriptor.<osmosis.superfluid.QueryOuterClass.AllAssetsRequest, osmosis.superfluid.QueryOuterClass.AllAssetsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AllAssets"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.AllAssetsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.AllAssetsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AllAssets"))
              .build();
        }
      }
    }
    return getAllAssetsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.AssetMultiplierRequest,
      osmosis.superfluid.QueryOuterClass.AssetMultiplierResponse> getAssetMultiplierMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AssetMultiplier",
      requestType = osmosis.superfluid.QueryOuterClass.AssetMultiplierRequest.class,
      responseType = osmosis.superfluid.QueryOuterClass.AssetMultiplierResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.AssetMultiplierRequest,
      osmosis.superfluid.QueryOuterClass.AssetMultiplierResponse> getAssetMultiplierMethod() {
    io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.AssetMultiplierRequest, osmosis.superfluid.QueryOuterClass.AssetMultiplierResponse> getAssetMultiplierMethod;
    if ((getAssetMultiplierMethod = QueryGrpc.getAssetMultiplierMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAssetMultiplierMethod = QueryGrpc.getAssetMultiplierMethod) == null) {
          QueryGrpc.getAssetMultiplierMethod = getAssetMultiplierMethod =
              io.grpc.MethodDescriptor.<osmosis.superfluid.QueryOuterClass.AssetMultiplierRequest, osmosis.superfluid.QueryOuterClass.AssetMultiplierResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AssetMultiplier"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.AssetMultiplierRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.AssetMultiplierResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AssetMultiplier"))
              .build();
        }
      }
    }
    return getAssetMultiplierMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsRequest,
      osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsResponse> getAllIntermediaryAccountsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllIntermediaryAccounts",
      requestType = osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsRequest.class,
      responseType = osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsRequest,
      osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsResponse> getAllIntermediaryAccountsMethod() {
    io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsRequest, osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsResponse> getAllIntermediaryAccountsMethod;
    if ((getAllIntermediaryAccountsMethod = QueryGrpc.getAllIntermediaryAccountsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getAllIntermediaryAccountsMethod = QueryGrpc.getAllIntermediaryAccountsMethod) == null) {
          QueryGrpc.getAllIntermediaryAccountsMethod = getAllIntermediaryAccountsMethod =
              io.grpc.MethodDescriptor.<osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsRequest, osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AllIntermediaryAccounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("AllIntermediaryAccounts"))
              .build();
        }
      }
    }
    return getAllIntermediaryAccountsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountRequest,
      osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountResponse> getConnectedIntermediaryAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConnectedIntermediaryAccount",
      requestType = osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountRequest.class,
      responseType = osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountRequest,
      osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountResponse> getConnectedIntermediaryAccountMethod() {
    io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountRequest, osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountResponse> getConnectedIntermediaryAccountMethod;
    if ((getConnectedIntermediaryAccountMethod = QueryGrpc.getConnectedIntermediaryAccountMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getConnectedIntermediaryAccountMethod = QueryGrpc.getConnectedIntermediaryAccountMethod) == null) {
          QueryGrpc.getConnectedIntermediaryAccountMethod = getConnectedIntermediaryAccountMethod =
              io.grpc.MethodDescriptor.<osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountRequest, osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConnectedIntermediaryAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ConnectedIntermediaryAccount"))
              .build();
        }
      }
    }
    return getConnectedIntermediaryAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsRequest,
      osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsResponse> getTotalSuperfluidDelegationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TotalSuperfluidDelegations",
      requestType = osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsRequest.class,
      responseType = osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsRequest,
      osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsResponse> getTotalSuperfluidDelegationsMethod() {
    io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsRequest, osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsResponse> getTotalSuperfluidDelegationsMethod;
    if ((getTotalSuperfluidDelegationsMethod = QueryGrpc.getTotalSuperfluidDelegationsMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getTotalSuperfluidDelegationsMethod = QueryGrpc.getTotalSuperfluidDelegationsMethod) == null) {
          QueryGrpc.getTotalSuperfluidDelegationsMethod = getTotalSuperfluidDelegationsMethod =
              io.grpc.MethodDescriptor.<osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsRequest, osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TotalSuperfluidDelegations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("TotalSuperfluidDelegations"))
              .build();
        }
      }
    }
    return getTotalSuperfluidDelegationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountRequest,
      osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountResponse> getSuperfluidDelegationAmountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SuperfluidDelegationAmount",
      requestType = osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountRequest.class,
      responseType = osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountRequest,
      osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountResponse> getSuperfluidDelegationAmountMethod() {
    io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountRequest, osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountResponse> getSuperfluidDelegationAmountMethod;
    if ((getSuperfluidDelegationAmountMethod = QueryGrpc.getSuperfluidDelegationAmountMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSuperfluidDelegationAmountMethod = QueryGrpc.getSuperfluidDelegationAmountMethod) == null) {
          QueryGrpc.getSuperfluidDelegationAmountMethod = getSuperfluidDelegationAmountMethod =
              io.grpc.MethodDescriptor.<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountRequest, osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SuperfluidDelegationAmount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SuperfluidDelegationAmount"))
              .build();
        }
      }
    }
    return getSuperfluidDelegationAmountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorRequest,
      osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorResponse> getSuperfluidDelegationsByDelegatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SuperfluidDelegationsByDelegator",
      requestType = osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorRequest.class,
      responseType = osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorRequest,
      osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorResponse> getSuperfluidDelegationsByDelegatorMethod() {
    io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorRequest, osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorResponse> getSuperfluidDelegationsByDelegatorMethod;
    if ((getSuperfluidDelegationsByDelegatorMethod = QueryGrpc.getSuperfluidDelegationsByDelegatorMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSuperfluidDelegationsByDelegatorMethod = QueryGrpc.getSuperfluidDelegationsByDelegatorMethod) == null) {
          QueryGrpc.getSuperfluidDelegationsByDelegatorMethod = getSuperfluidDelegationsByDelegatorMethod =
              io.grpc.MethodDescriptor.<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorRequest, osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SuperfluidDelegationsByDelegator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SuperfluidDelegationsByDelegator"))
              .build();
        }
      }
    }
    return getSuperfluidDelegationsByDelegatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorRequest,
      osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorResponse> getSuperfluidUndelegationsByDelegatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SuperfluidUndelegationsByDelegator",
      requestType = osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorRequest.class,
      responseType = osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorRequest,
      osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorResponse> getSuperfluidUndelegationsByDelegatorMethod() {
    io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorRequest, osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorResponse> getSuperfluidUndelegationsByDelegatorMethod;
    if ((getSuperfluidUndelegationsByDelegatorMethod = QueryGrpc.getSuperfluidUndelegationsByDelegatorMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSuperfluidUndelegationsByDelegatorMethod = QueryGrpc.getSuperfluidUndelegationsByDelegatorMethod) == null) {
          QueryGrpc.getSuperfluidUndelegationsByDelegatorMethod = getSuperfluidUndelegationsByDelegatorMethod =
              io.grpc.MethodDescriptor.<osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorRequest, osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SuperfluidUndelegationsByDelegator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SuperfluidUndelegationsByDelegator"))
              .build();
        }
      }
    }
    return getSuperfluidUndelegationsByDelegatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomRequest,
      osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomResponse> getSuperfluidDelegationsByValidatorDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SuperfluidDelegationsByValidatorDenom",
      requestType = osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomRequest.class,
      responseType = osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomRequest,
      osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomResponse> getSuperfluidDelegationsByValidatorDenomMethod() {
    io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomRequest, osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomResponse> getSuperfluidDelegationsByValidatorDenomMethod;
    if ((getSuperfluidDelegationsByValidatorDenomMethod = QueryGrpc.getSuperfluidDelegationsByValidatorDenomMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getSuperfluidDelegationsByValidatorDenomMethod = QueryGrpc.getSuperfluidDelegationsByValidatorDenomMethod) == null) {
          QueryGrpc.getSuperfluidDelegationsByValidatorDenomMethod = getSuperfluidDelegationsByValidatorDenomMethod =
              io.grpc.MethodDescriptor.<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomRequest, osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SuperfluidDelegationsByValidatorDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("SuperfluidDelegationsByValidatorDenom"))
              .build();
        }
      }
    }
    return getSuperfluidDelegationsByValidatorDenomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomRequest,
      osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomResponse> getEstimateSuperfluidDelegatedAmountByValidatorDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EstimateSuperfluidDelegatedAmountByValidatorDenom",
      requestType = osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomRequest.class,
      responseType = osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomRequest,
      osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomResponse> getEstimateSuperfluidDelegatedAmountByValidatorDenomMethod() {
    io.grpc.MethodDescriptor<osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomRequest, osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomResponse> getEstimateSuperfluidDelegatedAmountByValidatorDenomMethod;
    if ((getEstimateSuperfluidDelegatedAmountByValidatorDenomMethod = QueryGrpc.getEstimateSuperfluidDelegatedAmountByValidatorDenomMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getEstimateSuperfluidDelegatedAmountByValidatorDenomMethod = QueryGrpc.getEstimateSuperfluidDelegatedAmountByValidatorDenomMethod) == null) {
          QueryGrpc.getEstimateSuperfluidDelegatedAmountByValidatorDenomMethod = getEstimateSuperfluidDelegatedAmountByValidatorDenomMethod =
              io.grpc.MethodDescriptor.<osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomRequest, osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EstimateSuperfluidDelegatedAmountByValidatorDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("EstimateSuperfluidDelegatedAmountByValidatorDenom"))
              .build();
        }
      }
    }
    return getEstimateSuperfluidDelegatedAmountByValidatorDenomMethod;
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
     * Params returns the total set of minting parameters.
     * </pre>
     */
    public void params(osmosis.superfluid.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getParamsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns superfluid asset type
     * </pre>
     */
    public void assetType(osmosis.superfluid.QueryOuterClass.AssetTypeRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.AssetTypeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAssetTypeMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns all superfluid asset types
     * </pre>
     */
    public void allAssets(osmosis.superfluid.QueryOuterClass.AllAssetsRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.AllAssetsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAllAssetsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns superfluid asset Multiplier
     * </pre>
     */
    public void assetMultiplier(osmosis.superfluid.QueryOuterClass.AssetMultiplierRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.AssetMultiplierResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAssetMultiplierMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns all superfluid intermediary account
     * </pre>
     */
    public void allIntermediaryAccounts(osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAllIntermediaryAccountsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns intermediary account connected to a superfluid staked lock by id
     * </pre>
     */
    public void connectedIntermediaryAccount(osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getConnectedIntermediaryAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns the total amount of osmo superfluidly staked
     * response denominated in uosmo
     * </pre>
     */
    public void totalSuperfluidDelegations(osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTotalSuperfluidDelegationsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns the coins superfluid delegated for a delegator, validator, denom
     * triplet
     * </pre>
     */
    public void superfluidDelegationAmount(osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSuperfluidDelegationAmountMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns all the superfluid poistions for a specific delegator
     * </pre>
     */
    public void superfluidDelegationsByDelegator(osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSuperfluidDelegationsByDelegatorMethod(), responseObserver);
    }

    /**
     */
    public void superfluidUndelegationsByDelegator(osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSuperfluidUndelegationsByDelegatorMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns all the superfluid positions of a specific denom delegated to one
     * validator
     * </pre>
     */
    public void superfluidDelegationsByValidatorDenom(osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSuperfluidDelegationsByValidatorDenomMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns the amount of a specific denom delegated to a specific validator
     * This is labeled an estimate, because the way it calculates the amount can
     * lead rounding errors from the true delegated amount
     * </pre>
     */
    public void estimateSuperfluidDelegatedAmountByValidatorDenom(osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEstimateSuperfluidDelegatedAmountByValidatorDenomMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParamsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.superfluid.QueryOuterClass.QueryParamsRequest,
                osmosis.superfluid.QueryOuterClass.QueryParamsResponse>(
                  this, METHODID_PARAMS)))
          .addMethod(
            getAssetTypeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.superfluid.QueryOuterClass.AssetTypeRequest,
                osmosis.superfluid.QueryOuterClass.AssetTypeResponse>(
                  this, METHODID_ASSET_TYPE)))
          .addMethod(
            getAllAssetsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.superfluid.QueryOuterClass.AllAssetsRequest,
                osmosis.superfluid.QueryOuterClass.AllAssetsResponse>(
                  this, METHODID_ALL_ASSETS)))
          .addMethod(
            getAssetMultiplierMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.superfluid.QueryOuterClass.AssetMultiplierRequest,
                osmosis.superfluid.QueryOuterClass.AssetMultiplierResponse>(
                  this, METHODID_ASSET_MULTIPLIER)))
          .addMethod(
            getAllIntermediaryAccountsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsRequest,
                osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsResponse>(
                  this, METHODID_ALL_INTERMEDIARY_ACCOUNTS)))
          .addMethod(
            getConnectedIntermediaryAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountRequest,
                osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountResponse>(
                  this, METHODID_CONNECTED_INTERMEDIARY_ACCOUNT)))
          .addMethod(
            getTotalSuperfluidDelegationsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsRequest,
                osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsResponse>(
                  this, METHODID_TOTAL_SUPERFLUID_DELEGATIONS)))
          .addMethod(
            getSuperfluidDelegationAmountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountRequest,
                osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountResponse>(
                  this, METHODID_SUPERFLUID_DELEGATION_AMOUNT)))
          .addMethod(
            getSuperfluidDelegationsByDelegatorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorRequest,
                osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorResponse>(
                  this, METHODID_SUPERFLUID_DELEGATIONS_BY_DELEGATOR)))
          .addMethod(
            getSuperfluidUndelegationsByDelegatorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorRequest,
                osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorResponse>(
                  this, METHODID_SUPERFLUID_UNDELEGATIONS_BY_DELEGATOR)))
          .addMethod(
            getSuperfluidDelegationsByValidatorDenomMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomRequest,
                osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomResponse>(
                  this, METHODID_SUPERFLUID_DELEGATIONS_BY_VALIDATOR_DENOM)))
          .addMethod(
            getEstimateSuperfluidDelegatedAmountByValidatorDenomMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomRequest,
                osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomResponse>(
                  this, METHODID_ESTIMATE_SUPERFLUID_DELEGATED_AMOUNT_BY_VALIDATOR_DENOM)))
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
     * Params returns the total set of minting parameters.
     * </pre>
     */
    public void params(osmosis.superfluid.QueryOuterClass.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.QueryParamsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns superfluid asset type
     * </pre>
     */
    public void assetType(osmosis.superfluid.QueryOuterClass.AssetTypeRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.AssetTypeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAssetTypeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns all superfluid asset types
     * </pre>
     */
    public void allAssets(osmosis.superfluid.QueryOuterClass.AllAssetsRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.AllAssetsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAllAssetsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns superfluid asset Multiplier
     * </pre>
     */
    public void assetMultiplier(osmosis.superfluid.QueryOuterClass.AssetMultiplierRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.AssetMultiplierResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAssetMultiplierMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns all superfluid intermediary account
     * </pre>
     */
    public void allIntermediaryAccounts(osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAllIntermediaryAccountsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns intermediary account connected to a superfluid staked lock by id
     * </pre>
     */
    public void connectedIntermediaryAccount(osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConnectedIntermediaryAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns the total amount of osmo superfluidly staked
     * response denominated in uosmo
     * </pre>
     */
    public void totalSuperfluidDelegations(osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTotalSuperfluidDelegationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns the coins superfluid delegated for a delegator, validator, denom
     * triplet
     * </pre>
     */
    public void superfluidDelegationAmount(osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSuperfluidDelegationAmountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns all the superfluid poistions for a specific delegator
     * </pre>
     */
    public void superfluidDelegationsByDelegator(osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSuperfluidDelegationsByDelegatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void superfluidUndelegationsByDelegator(osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSuperfluidUndelegationsByDelegatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns all the superfluid positions of a specific denom delegated to one
     * validator
     * </pre>
     */
    public void superfluidDelegationsByValidatorDenom(osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSuperfluidDelegationsByValidatorDenomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns the amount of a specific denom delegated to a specific validator
     * This is labeled an estimate, because the way it calculates the amount can
     * lead rounding errors from the true delegated amount
     * </pre>
     */
    public void estimateSuperfluidDelegatedAmountByValidatorDenom(osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomRequest request,
        io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEstimateSuperfluidDelegatedAmountByValidatorDenomMethod(), getCallOptions()), request, responseObserver);
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
     * Params returns the total set of minting parameters.
     * </pre>
     */
    public osmosis.superfluid.QueryOuterClass.QueryParamsResponse params(osmosis.superfluid.QueryOuterClass.QueryParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getParamsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns superfluid asset type
     * </pre>
     */
    public osmosis.superfluid.QueryOuterClass.AssetTypeResponse assetType(osmosis.superfluid.QueryOuterClass.AssetTypeRequest request) {
      return blockingUnaryCall(
          getChannel(), getAssetTypeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns all superfluid asset types
     * </pre>
     */
    public osmosis.superfluid.QueryOuterClass.AllAssetsResponse allAssets(osmosis.superfluid.QueryOuterClass.AllAssetsRequest request) {
      return blockingUnaryCall(
          getChannel(), getAllAssetsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns superfluid asset Multiplier
     * </pre>
     */
    public osmosis.superfluid.QueryOuterClass.AssetMultiplierResponse assetMultiplier(osmosis.superfluid.QueryOuterClass.AssetMultiplierRequest request) {
      return blockingUnaryCall(
          getChannel(), getAssetMultiplierMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns all superfluid intermediary account
     * </pre>
     */
    public osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsResponse allIntermediaryAccounts(osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsRequest request) {
      return blockingUnaryCall(
          getChannel(), getAllIntermediaryAccountsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns intermediary account connected to a superfluid staked lock by id
     * </pre>
     */
    public osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountResponse connectedIntermediaryAccount(osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountRequest request) {
      return blockingUnaryCall(
          getChannel(), getConnectedIntermediaryAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns the total amount of osmo superfluidly staked
     * response denominated in uosmo
     * </pre>
     */
    public osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsResponse totalSuperfluidDelegations(osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsRequest request) {
      return blockingUnaryCall(
          getChannel(), getTotalSuperfluidDelegationsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns the coins superfluid delegated for a delegator, validator, denom
     * triplet
     * </pre>
     */
    public osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountResponse superfluidDelegationAmount(osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountRequest request) {
      return blockingUnaryCall(
          getChannel(), getSuperfluidDelegationAmountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns all the superfluid poistions for a specific delegator
     * </pre>
     */
    public osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorResponse superfluidDelegationsByDelegator(osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorRequest request) {
      return blockingUnaryCall(
          getChannel(), getSuperfluidDelegationsByDelegatorMethod(), getCallOptions(), request);
    }

    /**
     */
    public osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorResponse superfluidUndelegationsByDelegator(osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorRequest request) {
      return blockingUnaryCall(
          getChannel(), getSuperfluidUndelegationsByDelegatorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns all the superfluid positions of a specific denom delegated to one
     * validator
     * </pre>
     */
    public osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomResponse superfluidDelegationsByValidatorDenom(osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomRequest request) {
      return blockingUnaryCall(
          getChannel(), getSuperfluidDelegationsByValidatorDenomMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns the amount of a specific denom delegated to a specific validator
     * This is labeled an estimate, because the way it calculates the amount can
     * lead rounding errors from the true delegated amount
     * </pre>
     */
    public osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomResponse estimateSuperfluidDelegatedAmountByValidatorDenom(osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomRequest request) {
      return blockingUnaryCall(
          getChannel(), getEstimateSuperfluidDelegatedAmountByValidatorDenomMethod(), getCallOptions(), request);
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
     * Params returns the total set of minting parameters.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.superfluid.QueryOuterClass.QueryParamsResponse> params(
        osmosis.superfluid.QueryOuterClass.QueryParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getParamsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns superfluid asset type
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.superfluid.QueryOuterClass.AssetTypeResponse> assetType(
        osmosis.superfluid.QueryOuterClass.AssetTypeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAssetTypeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns all superfluid asset types
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.superfluid.QueryOuterClass.AllAssetsResponse> allAssets(
        osmosis.superfluid.QueryOuterClass.AllAssetsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAllAssetsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns superfluid asset Multiplier
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.superfluid.QueryOuterClass.AssetMultiplierResponse> assetMultiplier(
        osmosis.superfluid.QueryOuterClass.AssetMultiplierRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAssetMultiplierMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns all superfluid intermediary account
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsResponse> allIntermediaryAccounts(
        osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAllIntermediaryAccountsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns intermediary account connected to a superfluid staked lock by id
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountResponse> connectedIntermediaryAccount(
        osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getConnectedIntermediaryAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns the total amount of osmo superfluidly staked
     * response denominated in uosmo
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsResponse> totalSuperfluidDelegations(
        osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTotalSuperfluidDelegationsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns the coins superfluid delegated for a delegator, validator, denom
     * triplet
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountResponse> superfluidDelegationAmount(
        osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSuperfluidDelegationAmountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns all the superfluid poistions for a specific delegator
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorResponse> superfluidDelegationsByDelegator(
        osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSuperfluidDelegationsByDelegatorMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorResponse> superfluidUndelegationsByDelegator(
        osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSuperfluidUndelegationsByDelegatorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns all the superfluid positions of a specific denom delegated to one
     * validator
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomResponse> superfluidDelegationsByValidatorDenom(
        osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSuperfluidDelegationsByValidatorDenomMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns the amount of a specific denom delegated to a specific validator
     * This is labeled an estimate, because the way it calculates the amount can
     * lead rounding errors from the true delegated amount
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomResponse> estimateSuperfluidDelegatedAmountByValidatorDenom(
        osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEstimateSuperfluidDelegatedAmountByValidatorDenomMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PARAMS = 0;
  private static final int METHODID_ASSET_TYPE = 1;
  private static final int METHODID_ALL_ASSETS = 2;
  private static final int METHODID_ASSET_MULTIPLIER = 3;
  private static final int METHODID_ALL_INTERMEDIARY_ACCOUNTS = 4;
  private static final int METHODID_CONNECTED_INTERMEDIARY_ACCOUNT = 5;
  private static final int METHODID_TOTAL_SUPERFLUID_DELEGATIONS = 6;
  private static final int METHODID_SUPERFLUID_DELEGATION_AMOUNT = 7;
  private static final int METHODID_SUPERFLUID_DELEGATIONS_BY_DELEGATOR = 8;
  private static final int METHODID_SUPERFLUID_UNDELEGATIONS_BY_DELEGATOR = 9;
  private static final int METHODID_SUPERFLUID_DELEGATIONS_BY_VALIDATOR_DENOM = 10;
  private static final int METHODID_ESTIMATE_SUPERFLUID_DELEGATED_AMOUNT_BY_VALIDATOR_DENOM = 11;

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
          serviceImpl.params((osmosis.superfluid.QueryOuterClass.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.QueryParamsResponse>) responseObserver);
          break;
        case METHODID_ASSET_TYPE:
          serviceImpl.assetType((osmosis.superfluid.QueryOuterClass.AssetTypeRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.AssetTypeResponse>) responseObserver);
          break;
        case METHODID_ALL_ASSETS:
          serviceImpl.allAssets((osmosis.superfluid.QueryOuterClass.AllAssetsRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.AllAssetsResponse>) responseObserver);
          break;
        case METHODID_ASSET_MULTIPLIER:
          serviceImpl.assetMultiplier((osmosis.superfluid.QueryOuterClass.AssetMultiplierRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.AssetMultiplierResponse>) responseObserver);
          break;
        case METHODID_ALL_INTERMEDIARY_ACCOUNTS:
          serviceImpl.allIntermediaryAccounts((osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.AllIntermediaryAccountsResponse>) responseObserver);
          break;
        case METHODID_CONNECTED_INTERMEDIARY_ACCOUNT:
          serviceImpl.connectedIntermediaryAccount((osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.ConnectedIntermediaryAccountResponse>) responseObserver);
          break;
        case METHODID_TOTAL_SUPERFLUID_DELEGATIONS:
          serviceImpl.totalSuperfluidDelegations((osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.TotalSuperfluidDelegationsResponse>) responseObserver);
          break;
        case METHODID_SUPERFLUID_DELEGATION_AMOUNT:
          serviceImpl.superfluidDelegationAmount((osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationAmountResponse>) responseObserver);
          break;
        case METHODID_SUPERFLUID_DELEGATIONS_BY_DELEGATOR:
          serviceImpl.superfluidDelegationsByDelegator((osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByDelegatorResponse>) responseObserver);
          break;
        case METHODID_SUPERFLUID_UNDELEGATIONS_BY_DELEGATOR:
          serviceImpl.superfluidUndelegationsByDelegator((osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.SuperfluidUndelegationsByDelegatorResponse>) responseObserver);
          break;
        case METHODID_SUPERFLUID_DELEGATIONS_BY_VALIDATOR_DENOM:
          serviceImpl.superfluidDelegationsByValidatorDenom((osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.SuperfluidDelegationsByValidatorDenomResponse>) responseObserver);
          break;
        case METHODID_ESTIMATE_SUPERFLUID_DELEGATED_AMOUNT_BY_VALIDATOR_DENOM:
          serviceImpl.estimateSuperfluidDelegatedAmountByValidatorDenom((osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomRequest) request,
              (io.grpc.stub.StreamObserver<osmosis.superfluid.QueryOuterClass.EstimateSuperfluidDelegatedAmountByValidatorDenomResponse>) responseObserver);
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
      return osmosis.superfluid.QueryOuterClass.getDescriptor();
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
              .addMethod(getAssetTypeMethod())
              .addMethod(getAllAssetsMethod())
              .addMethod(getAssetMultiplierMethod())
              .addMethod(getAllIntermediaryAccountsMethod())
              .addMethod(getConnectedIntermediaryAccountMethod())
              .addMethod(getTotalSuperfluidDelegationsMethod())
              .addMethod(getSuperfluidDelegationAmountMethod())
              .addMethod(getSuperfluidDelegationsByDelegatorMethod())
              .addMethod(getSuperfluidUndelegationsByDelegatorMethod())
              .addMethod(getSuperfluidDelegationsByValidatorDenomMethod())
              .addMethod(getEstimateSuperfluidDelegatedAmountByValidatorDenomMethod())
              .build();
        }
      }
    }
    return result;
  }
}
