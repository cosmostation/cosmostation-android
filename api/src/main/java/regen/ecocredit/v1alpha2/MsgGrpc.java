package regen.ecocredit.v1alpha2;

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
 * Msg is the regen.ecocredit.v1alpha1 Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: regen/ecocredit/v1alpha2/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "regen.ecocredit.v1alpha2.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgCreateClass,
      regen.ecocredit.v1alpha2.Tx.MsgCreateClassResponse> getCreateClassMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateClass",
      requestType = regen.ecocredit.v1alpha2.Tx.MsgCreateClass.class,
      responseType = regen.ecocredit.v1alpha2.Tx.MsgCreateClassResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgCreateClass,
      regen.ecocredit.v1alpha2.Tx.MsgCreateClassResponse> getCreateClassMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgCreateClass, regen.ecocredit.v1alpha2.Tx.MsgCreateClassResponse> getCreateClassMethod;
    if ((getCreateClassMethod = MsgGrpc.getCreateClassMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateClassMethod = MsgGrpc.getCreateClassMethod) == null) {
          MsgGrpc.getCreateClassMethod = getCreateClassMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.Tx.MsgCreateClass, regen.ecocredit.v1alpha2.Tx.MsgCreateClassResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateClass"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgCreateClass.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgCreateClassResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateClass"))
              .build();
        }
      }
    }
    return getCreateClassMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgCreateProject,
      regen.ecocredit.v1alpha2.Tx.MsgCreateProjectResponse> getCreateProjectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateProject",
      requestType = regen.ecocredit.v1alpha2.Tx.MsgCreateProject.class,
      responseType = regen.ecocredit.v1alpha2.Tx.MsgCreateProjectResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgCreateProject,
      regen.ecocredit.v1alpha2.Tx.MsgCreateProjectResponse> getCreateProjectMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgCreateProject, regen.ecocredit.v1alpha2.Tx.MsgCreateProjectResponse> getCreateProjectMethod;
    if ((getCreateProjectMethod = MsgGrpc.getCreateProjectMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateProjectMethod = MsgGrpc.getCreateProjectMethod) == null) {
          MsgGrpc.getCreateProjectMethod = getCreateProjectMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.Tx.MsgCreateProject, regen.ecocredit.v1alpha2.Tx.MsgCreateProjectResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateProject"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgCreateProject.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgCreateProjectResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateProject"))
              .build();
        }
      }
    }
    return getCreateProjectMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgCreateBatch,
      regen.ecocredit.v1alpha2.Tx.MsgCreateBatchResponse> getCreateBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateBatch",
      requestType = regen.ecocredit.v1alpha2.Tx.MsgCreateBatch.class,
      responseType = regen.ecocredit.v1alpha2.Tx.MsgCreateBatchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgCreateBatch,
      regen.ecocredit.v1alpha2.Tx.MsgCreateBatchResponse> getCreateBatchMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgCreateBatch, regen.ecocredit.v1alpha2.Tx.MsgCreateBatchResponse> getCreateBatchMethod;
    if ((getCreateBatchMethod = MsgGrpc.getCreateBatchMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateBatchMethod = MsgGrpc.getCreateBatchMethod) == null) {
          MsgGrpc.getCreateBatchMethod = getCreateBatchMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.Tx.MsgCreateBatch, regen.ecocredit.v1alpha2.Tx.MsgCreateBatchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgCreateBatch.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgCreateBatchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateBatch"))
              .build();
        }
      }
    }
    return getCreateBatchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgSend,
      regen.ecocredit.v1alpha2.Tx.MsgSendResponse> getSendMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Send",
      requestType = regen.ecocredit.v1alpha2.Tx.MsgSend.class,
      responseType = regen.ecocredit.v1alpha2.Tx.MsgSendResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgSend,
      regen.ecocredit.v1alpha2.Tx.MsgSendResponse> getSendMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgSend, regen.ecocredit.v1alpha2.Tx.MsgSendResponse> getSendMethod;
    if ((getSendMethod = MsgGrpc.getSendMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSendMethod = MsgGrpc.getSendMethod) == null) {
          MsgGrpc.getSendMethod = getSendMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.Tx.MsgSend, regen.ecocredit.v1alpha2.Tx.MsgSendResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Send"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgSend.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgSendResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Send"))
              .build();
        }
      }
    }
    return getSendMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgRetire,
      regen.ecocredit.v1alpha2.Tx.MsgRetireResponse> getRetireMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Retire",
      requestType = regen.ecocredit.v1alpha2.Tx.MsgRetire.class,
      responseType = regen.ecocredit.v1alpha2.Tx.MsgRetireResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgRetire,
      regen.ecocredit.v1alpha2.Tx.MsgRetireResponse> getRetireMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgRetire, regen.ecocredit.v1alpha2.Tx.MsgRetireResponse> getRetireMethod;
    if ((getRetireMethod = MsgGrpc.getRetireMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRetireMethod = MsgGrpc.getRetireMethod) == null) {
          MsgGrpc.getRetireMethod = getRetireMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.Tx.MsgRetire, regen.ecocredit.v1alpha2.Tx.MsgRetireResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Retire"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgRetire.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgRetireResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Retire"))
              .build();
        }
      }
    }
    return getRetireMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgCancel,
      regen.ecocredit.v1alpha2.Tx.MsgCancelResponse> getCancelMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Cancel",
      requestType = regen.ecocredit.v1alpha2.Tx.MsgCancel.class,
      responseType = regen.ecocredit.v1alpha2.Tx.MsgCancelResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgCancel,
      regen.ecocredit.v1alpha2.Tx.MsgCancelResponse> getCancelMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgCancel, regen.ecocredit.v1alpha2.Tx.MsgCancelResponse> getCancelMethod;
    if ((getCancelMethod = MsgGrpc.getCancelMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCancelMethod = MsgGrpc.getCancelMethod) == null) {
          MsgGrpc.getCancelMethod = getCancelMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.Tx.MsgCancel, regen.ecocredit.v1alpha2.Tx.MsgCancelResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Cancel"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgCancel.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgCancelResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Cancel"))
              .build();
        }
      }
    }
    return getCancelMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdmin,
      regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdminResponse> getUpdateClassAdminMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateClassAdmin",
      requestType = regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdmin.class,
      responseType = regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdminResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdmin,
      regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdminResponse> getUpdateClassAdminMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdmin, regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdminResponse> getUpdateClassAdminMethod;
    if ((getUpdateClassAdminMethod = MsgGrpc.getUpdateClassAdminMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateClassAdminMethod = MsgGrpc.getUpdateClassAdminMethod) == null) {
          MsgGrpc.getUpdateClassAdminMethod = getUpdateClassAdminMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdmin, regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdminResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateClassAdmin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdmin.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdminResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateClassAdmin"))
              .build();
        }
      }
    }
    return getUpdateClassAdminMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuers,
      regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuersResponse> getUpdateClassIssuersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateClassIssuers",
      requestType = regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuers.class,
      responseType = regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuers,
      regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuersResponse> getUpdateClassIssuersMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuers, regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuersResponse> getUpdateClassIssuersMethod;
    if ((getUpdateClassIssuersMethod = MsgGrpc.getUpdateClassIssuersMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateClassIssuersMethod = MsgGrpc.getUpdateClassIssuersMethod) == null) {
          MsgGrpc.getUpdateClassIssuersMethod = getUpdateClassIssuersMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuers, regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateClassIssuers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuers.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateClassIssuers"))
              .build();
        }
      }
    }
    return getUpdateClassIssuersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadata,
      regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadataResponse> getUpdateClassMetadataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateClassMetadata",
      requestType = regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadata.class,
      responseType = regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadata,
      regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadataResponse> getUpdateClassMetadataMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadata, regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadataResponse> getUpdateClassMetadataMethod;
    if ((getUpdateClassMetadataMethod = MsgGrpc.getUpdateClassMetadataMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateClassMetadataMethod = MsgGrpc.getUpdateClassMetadataMethod) == null) {
          MsgGrpc.getUpdateClassMetadataMethod = getUpdateClassMetadataMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadata, regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateClassMetadata"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadata.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateClassMetadata"))
              .build();
        }
      }
    }
    return getUpdateClassMetadataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgSell,
      regen.ecocredit.v1alpha2.Tx.MsgSellResponse> getSellMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Sell",
      requestType = regen.ecocredit.v1alpha2.Tx.MsgSell.class,
      responseType = regen.ecocredit.v1alpha2.Tx.MsgSellResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgSell,
      regen.ecocredit.v1alpha2.Tx.MsgSellResponse> getSellMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgSell, regen.ecocredit.v1alpha2.Tx.MsgSellResponse> getSellMethod;
    if ((getSellMethod = MsgGrpc.getSellMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSellMethod = MsgGrpc.getSellMethod) == null) {
          MsgGrpc.getSellMethod = getSellMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.Tx.MsgSell, regen.ecocredit.v1alpha2.Tx.MsgSellResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Sell"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgSell.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgSellResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Sell"))
              .build();
        }
      }
    }
    return getSellMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrders,
      regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrdersResponse> getUpdateSellOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateSellOrders",
      requestType = regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrders.class,
      responseType = regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrdersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrders,
      regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrdersResponse> getUpdateSellOrdersMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrders, regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrdersResponse> getUpdateSellOrdersMethod;
    if ((getUpdateSellOrdersMethod = MsgGrpc.getUpdateSellOrdersMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateSellOrdersMethod = MsgGrpc.getUpdateSellOrdersMethod) == null) {
          MsgGrpc.getUpdateSellOrdersMethod = getUpdateSellOrdersMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrders, regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrdersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateSellOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrders.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrdersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateSellOrders"))
              .build();
        }
      }
    }
    return getUpdateSellOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgBuy,
      regen.ecocredit.v1alpha2.Tx.MsgBuyResponse> getBuyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Buy",
      requestType = regen.ecocredit.v1alpha2.Tx.MsgBuy.class,
      responseType = regen.ecocredit.v1alpha2.Tx.MsgBuyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgBuy,
      regen.ecocredit.v1alpha2.Tx.MsgBuyResponse> getBuyMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgBuy, regen.ecocredit.v1alpha2.Tx.MsgBuyResponse> getBuyMethod;
    if ((getBuyMethod = MsgGrpc.getBuyMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBuyMethod = MsgGrpc.getBuyMethod) == null) {
          MsgGrpc.getBuyMethod = getBuyMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.Tx.MsgBuy, regen.ecocredit.v1alpha2.Tx.MsgBuyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Buy"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgBuy.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgBuyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Buy"))
              .build();
        }
      }
    }
    return getBuyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenom,
      regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenomResponse> getAllowAskDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllowAskDenom",
      requestType = regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenom.class,
      responseType = regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenom,
      regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenomResponse> getAllowAskDenomMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenom, regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenomResponse> getAllowAskDenomMethod;
    if ((getAllowAskDenomMethod = MsgGrpc.getAllowAskDenomMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAllowAskDenomMethod = MsgGrpc.getAllowAskDenomMethod) == null) {
          MsgGrpc.getAllowAskDenomMethod = getAllowAskDenomMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenom, regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AllowAskDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenom.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AllowAskDenom"))
              .build();
        }
      }
    }
    return getAllowAskDenomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgCreateBasket,
      regen.ecocredit.v1alpha2.Tx.MsgCreateBasketResponse> getCreateBasketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateBasket",
      requestType = regen.ecocredit.v1alpha2.Tx.MsgCreateBasket.class,
      responseType = regen.ecocredit.v1alpha2.Tx.MsgCreateBasketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgCreateBasket,
      regen.ecocredit.v1alpha2.Tx.MsgCreateBasketResponse> getCreateBasketMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgCreateBasket, regen.ecocredit.v1alpha2.Tx.MsgCreateBasketResponse> getCreateBasketMethod;
    if ((getCreateBasketMethod = MsgGrpc.getCreateBasketMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateBasketMethod = MsgGrpc.getCreateBasketMethod) == null) {
          MsgGrpc.getCreateBasketMethod = getCreateBasketMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.Tx.MsgCreateBasket, regen.ecocredit.v1alpha2.Tx.MsgCreateBasketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateBasket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgCreateBasket.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgCreateBasketResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateBasket"))
              .build();
        }
      }
    }
    return getCreateBasketMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgAddToBasket,
      regen.ecocredit.v1alpha2.Tx.MsgAddToBasketResponse> getAddToBasketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddToBasket",
      requestType = regen.ecocredit.v1alpha2.Tx.MsgAddToBasket.class,
      responseType = regen.ecocredit.v1alpha2.Tx.MsgAddToBasketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgAddToBasket,
      regen.ecocredit.v1alpha2.Tx.MsgAddToBasketResponse> getAddToBasketMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgAddToBasket, regen.ecocredit.v1alpha2.Tx.MsgAddToBasketResponse> getAddToBasketMethod;
    if ((getAddToBasketMethod = MsgGrpc.getAddToBasketMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddToBasketMethod = MsgGrpc.getAddToBasketMethod) == null) {
          MsgGrpc.getAddToBasketMethod = getAddToBasketMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.Tx.MsgAddToBasket, regen.ecocredit.v1alpha2.Tx.MsgAddToBasketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddToBasket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgAddToBasket.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgAddToBasketResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddToBasket"))
              .build();
        }
      }
    }
    return getAddToBasketMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasket,
      regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasketResponse> getTakeFromBasketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TakeFromBasket",
      requestType = regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasket.class,
      responseType = regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasket,
      regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasketResponse> getTakeFromBasketMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasket, regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasketResponse> getTakeFromBasketMethod;
    if ((getTakeFromBasketMethod = MsgGrpc.getTakeFromBasketMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getTakeFromBasketMethod = MsgGrpc.getTakeFromBasketMethod) == null) {
          MsgGrpc.getTakeFromBasketMethod = getTakeFromBasketMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasket, regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TakeFromBasket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasket.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasketResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("TakeFromBasket"))
              .build();
        }
      }
    }
    return getTakeFromBasketMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgPickFromBasket,
      regen.ecocredit.v1alpha2.Tx.MsgPickFromBasketResponse> getPickFromBasketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PickFromBasket",
      requestType = regen.ecocredit.v1alpha2.Tx.MsgPickFromBasket.class,
      responseType = regen.ecocredit.v1alpha2.Tx.MsgPickFromBasketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgPickFromBasket,
      regen.ecocredit.v1alpha2.Tx.MsgPickFromBasketResponse> getPickFromBasketMethod() {
    io.grpc.MethodDescriptor<regen.ecocredit.v1alpha2.Tx.MsgPickFromBasket, regen.ecocredit.v1alpha2.Tx.MsgPickFromBasketResponse> getPickFromBasketMethod;
    if ((getPickFromBasketMethod = MsgGrpc.getPickFromBasketMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getPickFromBasketMethod = MsgGrpc.getPickFromBasketMethod) == null) {
          MsgGrpc.getPickFromBasketMethod = getPickFromBasketMethod =
              io.grpc.MethodDescriptor.<regen.ecocredit.v1alpha2.Tx.MsgPickFromBasket, regen.ecocredit.v1alpha2.Tx.MsgPickFromBasketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PickFromBasket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgPickFromBasket.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.ecocredit.v1alpha2.Tx.MsgPickFromBasketResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("PickFromBasket"))
              .build();
        }
      }
    }
    return getPickFromBasketMethod;
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
   * Msg is the regen.ecocredit.v1alpha1 Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CreateClass creates a new credit class with an approved list of issuers and
     * optional metadata.
     * </pre>
     */
    public void createClass(regen.ecocredit.v1alpha2.Tx.MsgCreateClass request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgCreateClassResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateClassMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateProject creates a new project within a credit class.
     * </pre>
     */
    public void createProject(regen.ecocredit.v1alpha2.Tx.MsgCreateProject request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgCreateProjectResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateProjectMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateBatch creates a new batch of credits for an existing project.
     * This will create a new batch denom with a fixed supply. Issued credits can
     * be distributed to recipients in either tradable or retired form.
     * </pre>
     */
    public void createBatch(regen.ecocredit.v1alpha2.Tx.MsgCreateBatch request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgCreateBatchResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateBatchMethod(), responseObserver);
    }

    /**
     * <pre>
     * Send sends tradable credits from one account to another account. Sent
     * credits can either be tradable or retired on receipt.
     * </pre>
     */
    public void send(regen.ecocredit.v1alpha2.Tx.MsgSend request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgSendResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retire retires a specified number of credits in the holder's account.
     * </pre>
     */
    public void retire(regen.ecocredit.v1alpha2.Tx.MsgRetire request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgRetireResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRetireMethod(), responseObserver);
    }

    /**
     * <pre>
     * Cancel removes a number of credits from the holder's account and also
     * deducts them from the tradable supply, effectively cancelling their
     * issuance on Regen Ledger
     * </pre>
     */
    public void cancel(regen.ecocredit.v1alpha2.Tx.MsgCancel request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgCancelResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCancelMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateClassAdmin updates the credit class admin
     * </pre>
     */
    public void updateClassAdmin(regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdmin request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdminResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateClassAdminMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateClassIssuers updates the credit class issuer list
     * </pre>
     */
    public void updateClassIssuers(regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuers request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateClassIssuersMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateClassMetadata updates the credit class metadata
     * </pre>
     */
    public void updateClassMetadata(regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadata request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadataResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateClassMetadataMethod(), responseObserver);
    }

    /**
     * <pre>
     * Sell creates new sell orders.
     * </pre>
     */
    public void sell(regen.ecocredit.v1alpha2.Tx.MsgSell request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgSellResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSellMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateSellOrders updates existing sell orders.
     * </pre>
     */
    public void updateSellOrders(regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrders request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrdersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateSellOrdersMethod(), responseObserver);
    }

    /**
     * <pre>
     * Buy creates credit buy orders.
     * </pre>
     */
    public void buy(regen.ecocredit.v1alpha2.Tx.MsgBuy request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgBuyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBuyMethod(), responseObserver);
    }

    /**
     * <pre>
     * AllowAskDenom is a governance operation which authorizes a new ask denom to be used in sell orders
     * </pre>
     */
    public void allowAskDenom(regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenom request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenomResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAllowAskDenomMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateBasket creates a bank denom which wraps credits.
     * </pre>
     */
    public void createBasket(regen.ecocredit.v1alpha2.Tx.MsgCreateBasket request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgCreateBasketResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateBasketMethod(), responseObserver);
    }

    /**
     * <pre>
     * AddToBasket adds credits to a basket in return for basket tokens.
     * </pre>
     */
    public void addToBasket(regen.ecocredit.v1alpha2.Tx.MsgAddToBasket request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgAddToBasketResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddToBasketMethod(), responseObserver);
    }

    /**
     * <pre>
     * TakeFromBasket takes credits from a basket without regard for which
     * credits they are. The credits will be auto-retired if disable_auto_retire
     * is false. Credits will be chosen randomly using the previous block hash
     * as a consensus source of randomness.
     * More concretely, the implementation is as follows:
     * - take the previous block hash and convert it into an uint64,
     * - given the total number of different credits within the basket `n`, the
     *   first credits that will get picked correspond to: hash modulo n (in
     *   terms of order),
     * - then if we need to take more credits, we get some from the next one and
     *   so on.
     * </pre>
     */
    public void takeFromBasket(regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasket request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasketResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTakeFromBasketMethod(), responseObserver);
    }

    /**
     * <pre>
     * PickFromBasket picks specific credits from a basket. If allow_picking is
     * set to false, then only an address which deposited credits in the basket
     * can pick those credits. All other addresses will be blocked from picking
     * those credits. The credits will be auto-retired if disable_auto_retire is
     * false unless the credits were previously put into the basket by the
     * address picking them from the basket, in which case they will remain
     * tradable. This functionality allows the owner of a credit to have more
     * control over the credits they are putting in baskets then ordinary users
     * to deal with the scenario where basket tokens end up being worth
     * significantly less than the credits on their own.
     * </pre>
     */
    public void pickFromBasket(regen.ecocredit.v1alpha2.Tx.MsgPickFromBasket request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgPickFromBasketResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPickFromBasketMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateClassMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.Tx.MsgCreateClass,
                regen.ecocredit.v1alpha2.Tx.MsgCreateClassResponse>(
                  this, METHODID_CREATE_CLASS)))
          .addMethod(
            getCreateProjectMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.Tx.MsgCreateProject,
                regen.ecocredit.v1alpha2.Tx.MsgCreateProjectResponse>(
                  this, METHODID_CREATE_PROJECT)))
          .addMethod(
            getCreateBatchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.Tx.MsgCreateBatch,
                regen.ecocredit.v1alpha2.Tx.MsgCreateBatchResponse>(
                  this, METHODID_CREATE_BATCH)))
          .addMethod(
            getSendMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.Tx.MsgSend,
                regen.ecocredit.v1alpha2.Tx.MsgSendResponse>(
                  this, METHODID_SEND)))
          .addMethod(
            getRetireMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.Tx.MsgRetire,
                regen.ecocredit.v1alpha2.Tx.MsgRetireResponse>(
                  this, METHODID_RETIRE)))
          .addMethod(
            getCancelMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.Tx.MsgCancel,
                regen.ecocredit.v1alpha2.Tx.MsgCancelResponse>(
                  this, METHODID_CANCEL)))
          .addMethod(
            getUpdateClassAdminMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdmin,
                regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdminResponse>(
                  this, METHODID_UPDATE_CLASS_ADMIN)))
          .addMethod(
            getUpdateClassIssuersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuers,
                regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuersResponse>(
                  this, METHODID_UPDATE_CLASS_ISSUERS)))
          .addMethod(
            getUpdateClassMetadataMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadata,
                regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadataResponse>(
                  this, METHODID_UPDATE_CLASS_METADATA)))
          .addMethod(
            getSellMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.Tx.MsgSell,
                regen.ecocredit.v1alpha2.Tx.MsgSellResponse>(
                  this, METHODID_SELL)))
          .addMethod(
            getUpdateSellOrdersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrders,
                regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrdersResponse>(
                  this, METHODID_UPDATE_SELL_ORDERS)))
          .addMethod(
            getBuyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.Tx.MsgBuy,
                regen.ecocredit.v1alpha2.Tx.MsgBuyResponse>(
                  this, METHODID_BUY)))
          .addMethod(
            getAllowAskDenomMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenom,
                regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenomResponse>(
                  this, METHODID_ALLOW_ASK_DENOM)))
          .addMethod(
            getCreateBasketMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.Tx.MsgCreateBasket,
                regen.ecocredit.v1alpha2.Tx.MsgCreateBasketResponse>(
                  this, METHODID_CREATE_BASKET)))
          .addMethod(
            getAddToBasketMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.Tx.MsgAddToBasket,
                regen.ecocredit.v1alpha2.Tx.MsgAddToBasketResponse>(
                  this, METHODID_ADD_TO_BASKET)))
          .addMethod(
            getTakeFromBasketMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasket,
                regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasketResponse>(
                  this, METHODID_TAKE_FROM_BASKET)))
          .addMethod(
            getPickFromBasketMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.ecocredit.v1alpha2.Tx.MsgPickFromBasket,
                regen.ecocredit.v1alpha2.Tx.MsgPickFromBasketResponse>(
                  this, METHODID_PICK_FROM_BASKET)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg is the regen.ecocredit.v1alpha1 Msg service.
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
     * CreateClass creates a new credit class with an approved list of issuers and
     * optional metadata.
     * </pre>
     */
    public void createClass(regen.ecocredit.v1alpha2.Tx.MsgCreateClass request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgCreateClassResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateClassMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateProject creates a new project within a credit class.
     * </pre>
     */
    public void createProject(regen.ecocredit.v1alpha2.Tx.MsgCreateProject request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgCreateProjectResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateProjectMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateBatch creates a new batch of credits for an existing project.
     * This will create a new batch denom with a fixed supply. Issued credits can
     * be distributed to recipients in either tradable or retired form.
     * </pre>
     */
    public void createBatch(regen.ecocredit.v1alpha2.Tx.MsgCreateBatch request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgCreateBatchResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateBatchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Send sends tradable credits from one account to another account. Sent
     * credits can either be tradable or retired on receipt.
     * </pre>
     */
    public void send(regen.ecocredit.v1alpha2.Tx.MsgSend request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgSendResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retire retires a specified number of credits in the holder's account.
     * </pre>
     */
    public void retire(regen.ecocredit.v1alpha2.Tx.MsgRetire request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgRetireResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRetireMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Cancel removes a number of credits from the holder's account and also
     * deducts them from the tradable supply, effectively cancelling their
     * issuance on Regen Ledger
     * </pre>
     */
    public void cancel(regen.ecocredit.v1alpha2.Tx.MsgCancel request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgCancelResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCancelMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateClassAdmin updates the credit class admin
     * </pre>
     */
    public void updateClassAdmin(regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdmin request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdminResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateClassAdminMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateClassIssuers updates the credit class issuer list
     * </pre>
     */
    public void updateClassIssuers(regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuers request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateClassIssuersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateClassMetadata updates the credit class metadata
     * </pre>
     */
    public void updateClassMetadata(regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadata request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadataResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateClassMetadataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Sell creates new sell orders.
     * </pre>
     */
    public void sell(regen.ecocredit.v1alpha2.Tx.MsgSell request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgSellResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSellMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateSellOrders updates existing sell orders.
     * </pre>
     */
    public void updateSellOrders(regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrders request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrdersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateSellOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Buy creates credit buy orders.
     * </pre>
     */
    public void buy(regen.ecocredit.v1alpha2.Tx.MsgBuy request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgBuyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBuyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AllowAskDenom is a governance operation which authorizes a new ask denom to be used in sell orders
     * </pre>
     */
    public void allowAskDenom(regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenom request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenomResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAllowAskDenomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateBasket creates a bank denom which wraps credits.
     * </pre>
     */
    public void createBasket(regen.ecocredit.v1alpha2.Tx.MsgCreateBasket request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgCreateBasketResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateBasketMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AddToBasket adds credits to a basket in return for basket tokens.
     * </pre>
     */
    public void addToBasket(regen.ecocredit.v1alpha2.Tx.MsgAddToBasket request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgAddToBasketResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddToBasketMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TakeFromBasket takes credits from a basket without regard for which
     * credits they are. The credits will be auto-retired if disable_auto_retire
     * is false. Credits will be chosen randomly using the previous block hash
     * as a consensus source of randomness.
     * More concretely, the implementation is as follows:
     * - take the previous block hash and convert it into an uint64,
     * - given the total number of different credits within the basket `n`, the
     *   first credits that will get picked correspond to: hash modulo n (in
     *   terms of order),
     * - then if we need to take more credits, we get some from the next one and
     *   so on.
     * </pre>
     */
    public void takeFromBasket(regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasket request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasketResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTakeFromBasketMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PickFromBasket picks specific credits from a basket. If allow_picking is
     * set to false, then only an address which deposited credits in the basket
     * can pick those credits. All other addresses will be blocked from picking
     * those credits. The credits will be auto-retired if disable_auto_retire is
     * false unless the credits were previously put into the basket by the
     * address picking them from the basket, in which case they will remain
     * tradable. This functionality allows the owner of a credit to have more
     * control over the credits they are putting in baskets then ordinary users
     * to deal with the scenario where basket tokens end up being worth
     * significantly less than the credits on their own.
     * </pre>
     */
    public void pickFromBasket(regen.ecocredit.v1alpha2.Tx.MsgPickFromBasket request,
        io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgPickFromBasketResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPickFromBasketMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg is the regen.ecocredit.v1alpha1 Msg service.
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
     * CreateClass creates a new credit class with an approved list of issuers and
     * optional metadata.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.Tx.MsgCreateClassResponse createClass(regen.ecocredit.v1alpha2.Tx.MsgCreateClass request) {
      return blockingUnaryCall(
          getChannel(), getCreateClassMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateProject creates a new project within a credit class.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.Tx.MsgCreateProjectResponse createProject(regen.ecocredit.v1alpha2.Tx.MsgCreateProject request) {
      return blockingUnaryCall(
          getChannel(), getCreateProjectMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateBatch creates a new batch of credits for an existing project.
     * This will create a new batch denom with a fixed supply. Issued credits can
     * be distributed to recipients in either tradable or retired form.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.Tx.MsgCreateBatchResponse createBatch(regen.ecocredit.v1alpha2.Tx.MsgCreateBatch request) {
      return blockingUnaryCall(
          getChannel(), getCreateBatchMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Send sends tradable credits from one account to another account. Sent
     * credits can either be tradable or retired on receipt.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.Tx.MsgSendResponse send(regen.ecocredit.v1alpha2.Tx.MsgSend request) {
      return blockingUnaryCall(
          getChannel(), getSendMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retire retires a specified number of credits in the holder's account.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.Tx.MsgRetireResponse retire(regen.ecocredit.v1alpha2.Tx.MsgRetire request) {
      return blockingUnaryCall(
          getChannel(), getRetireMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Cancel removes a number of credits from the holder's account and also
     * deducts them from the tradable supply, effectively cancelling their
     * issuance on Regen Ledger
     * </pre>
     */
    public regen.ecocredit.v1alpha2.Tx.MsgCancelResponse cancel(regen.ecocredit.v1alpha2.Tx.MsgCancel request) {
      return blockingUnaryCall(
          getChannel(), getCancelMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateClassAdmin updates the credit class admin
     * </pre>
     */
    public regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdminResponse updateClassAdmin(regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdmin request) {
      return blockingUnaryCall(
          getChannel(), getUpdateClassAdminMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateClassIssuers updates the credit class issuer list
     * </pre>
     */
    public regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuersResponse updateClassIssuers(regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuers request) {
      return blockingUnaryCall(
          getChannel(), getUpdateClassIssuersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateClassMetadata updates the credit class metadata
     * </pre>
     */
    public regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadataResponse updateClassMetadata(regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadata request) {
      return blockingUnaryCall(
          getChannel(), getUpdateClassMetadataMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Sell creates new sell orders.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.Tx.MsgSellResponse sell(regen.ecocredit.v1alpha2.Tx.MsgSell request) {
      return blockingUnaryCall(
          getChannel(), getSellMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateSellOrders updates existing sell orders.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrdersResponse updateSellOrders(regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrders request) {
      return blockingUnaryCall(
          getChannel(), getUpdateSellOrdersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Buy creates credit buy orders.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.Tx.MsgBuyResponse buy(regen.ecocredit.v1alpha2.Tx.MsgBuy request) {
      return blockingUnaryCall(
          getChannel(), getBuyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AllowAskDenom is a governance operation which authorizes a new ask denom to be used in sell orders
     * </pre>
     */
    public regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenomResponse allowAskDenom(regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenom request) {
      return blockingUnaryCall(
          getChannel(), getAllowAskDenomMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateBasket creates a bank denom which wraps credits.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.Tx.MsgCreateBasketResponse createBasket(regen.ecocredit.v1alpha2.Tx.MsgCreateBasket request) {
      return blockingUnaryCall(
          getChannel(), getCreateBasketMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AddToBasket adds credits to a basket in return for basket tokens.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.Tx.MsgAddToBasketResponse addToBasket(regen.ecocredit.v1alpha2.Tx.MsgAddToBasket request) {
      return blockingUnaryCall(
          getChannel(), getAddToBasketMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TakeFromBasket takes credits from a basket without regard for which
     * credits they are. The credits will be auto-retired if disable_auto_retire
     * is false. Credits will be chosen randomly using the previous block hash
     * as a consensus source of randomness.
     * More concretely, the implementation is as follows:
     * - take the previous block hash and convert it into an uint64,
     * - given the total number of different credits within the basket `n`, the
     *   first credits that will get picked correspond to: hash modulo n (in
     *   terms of order),
     * - then if we need to take more credits, we get some from the next one and
     *   so on.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasketResponse takeFromBasket(regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasket request) {
      return blockingUnaryCall(
          getChannel(), getTakeFromBasketMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PickFromBasket picks specific credits from a basket. If allow_picking is
     * set to false, then only an address which deposited credits in the basket
     * can pick those credits. All other addresses will be blocked from picking
     * those credits. The credits will be auto-retired if disable_auto_retire is
     * false unless the credits were previously put into the basket by the
     * address picking them from the basket, in which case they will remain
     * tradable. This functionality allows the owner of a credit to have more
     * control over the credits they are putting in baskets then ordinary users
     * to deal with the scenario where basket tokens end up being worth
     * significantly less than the credits on their own.
     * </pre>
     */
    public regen.ecocredit.v1alpha2.Tx.MsgPickFromBasketResponse pickFromBasket(regen.ecocredit.v1alpha2.Tx.MsgPickFromBasket request) {
      return blockingUnaryCall(
          getChannel(), getPickFromBasketMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg is the regen.ecocredit.v1alpha1 Msg service.
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
     * CreateClass creates a new credit class with an approved list of issuers and
     * optional metadata.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.Tx.MsgCreateClassResponse> createClass(
        regen.ecocredit.v1alpha2.Tx.MsgCreateClass request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateClassMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateProject creates a new project within a credit class.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.Tx.MsgCreateProjectResponse> createProject(
        regen.ecocredit.v1alpha2.Tx.MsgCreateProject request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateProjectMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateBatch creates a new batch of credits for an existing project.
     * This will create a new batch denom with a fixed supply. Issued credits can
     * be distributed to recipients in either tradable or retired form.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.Tx.MsgCreateBatchResponse> createBatch(
        regen.ecocredit.v1alpha2.Tx.MsgCreateBatch request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateBatchMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Send sends tradable credits from one account to another account. Sent
     * credits can either be tradable or retired on receipt.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.Tx.MsgSendResponse> send(
        regen.ecocredit.v1alpha2.Tx.MsgSend request) {
      return futureUnaryCall(
          getChannel().newCall(getSendMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retire retires a specified number of credits in the holder's account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.Tx.MsgRetireResponse> retire(
        regen.ecocredit.v1alpha2.Tx.MsgRetire request) {
      return futureUnaryCall(
          getChannel().newCall(getRetireMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Cancel removes a number of credits from the holder's account and also
     * deducts them from the tradable supply, effectively cancelling their
     * issuance on Regen Ledger
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.Tx.MsgCancelResponse> cancel(
        regen.ecocredit.v1alpha2.Tx.MsgCancel request) {
      return futureUnaryCall(
          getChannel().newCall(getCancelMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateClassAdmin updates the credit class admin
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdminResponse> updateClassAdmin(
        regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdmin request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateClassAdminMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateClassIssuers updates the credit class issuer list
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuersResponse> updateClassIssuers(
        regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuers request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateClassIssuersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateClassMetadata updates the credit class metadata
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadataResponse> updateClassMetadata(
        regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadata request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateClassMetadataMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Sell creates new sell orders.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.Tx.MsgSellResponse> sell(
        regen.ecocredit.v1alpha2.Tx.MsgSell request) {
      return futureUnaryCall(
          getChannel().newCall(getSellMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateSellOrders updates existing sell orders.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrdersResponse> updateSellOrders(
        regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrders request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateSellOrdersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Buy creates credit buy orders.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.Tx.MsgBuyResponse> buy(
        regen.ecocredit.v1alpha2.Tx.MsgBuy request) {
      return futureUnaryCall(
          getChannel().newCall(getBuyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AllowAskDenom is a governance operation which authorizes a new ask denom to be used in sell orders
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenomResponse> allowAskDenom(
        regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenom request) {
      return futureUnaryCall(
          getChannel().newCall(getAllowAskDenomMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateBasket creates a bank denom which wraps credits.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.Tx.MsgCreateBasketResponse> createBasket(
        regen.ecocredit.v1alpha2.Tx.MsgCreateBasket request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateBasketMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AddToBasket adds credits to a basket in return for basket tokens.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.Tx.MsgAddToBasketResponse> addToBasket(
        regen.ecocredit.v1alpha2.Tx.MsgAddToBasket request) {
      return futureUnaryCall(
          getChannel().newCall(getAddToBasketMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * TakeFromBasket takes credits from a basket without regard for which
     * credits they are. The credits will be auto-retired if disable_auto_retire
     * is false. Credits will be chosen randomly using the previous block hash
     * as a consensus source of randomness.
     * More concretely, the implementation is as follows:
     * - take the previous block hash and convert it into an uint64,
     * - given the total number of different credits within the basket `n`, the
     *   first credits that will get picked correspond to: hash modulo n (in
     *   terms of order),
     * - then if we need to take more credits, we get some from the next one and
     *   so on.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasketResponse> takeFromBasket(
        regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasket request) {
      return futureUnaryCall(
          getChannel().newCall(getTakeFromBasketMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PickFromBasket picks specific credits from a basket. If allow_picking is
     * set to false, then only an address which deposited credits in the basket
     * can pick those credits. All other addresses will be blocked from picking
     * those credits. The credits will be auto-retired if disable_auto_retire is
     * false unless the credits were previously put into the basket by the
     * address picking them from the basket, in which case they will remain
     * tradable. This functionality allows the owner of a credit to have more
     * control over the credits they are putting in baskets then ordinary users
     * to deal with the scenario where basket tokens end up being worth
     * significantly less than the credits on their own.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.ecocredit.v1alpha2.Tx.MsgPickFromBasketResponse> pickFromBasket(
        regen.ecocredit.v1alpha2.Tx.MsgPickFromBasket request) {
      return futureUnaryCall(
          getChannel().newCall(getPickFromBasketMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_CLASS = 0;
  private static final int METHODID_CREATE_PROJECT = 1;
  private static final int METHODID_CREATE_BATCH = 2;
  private static final int METHODID_SEND = 3;
  private static final int METHODID_RETIRE = 4;
  private static final int METHODID_CANCEL = 5;
  private static final int METHODID_UPDATE_CLASS_ADMIN = 6;
  private static final int METHODID_UPDATE_CLASS_ISSUERS = 7;
  private static final int METHODID_UPDATE_CLASS_METADATA = 8;
  private static final int METHODID_SELL = 9;
  private static final int METHODID_UPDATE_SELL_ORDERS = 10;
  private static final int METHODID_BUY = 11;
  private static final int METHODID_ALLOW_ASK_DENOM = 12;
  private static final int METHODID_CREATE_BASKET = 13;
  private static final int METHODID_ADD_TO_BASKET = 14;
  private static final int METHODID_TAKE_FROM_BASKET = 15;
  private static final int METHODID_PICK_FROM_BASKET = 16;

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
        case METHODID_CREATE_CLASS:
          serviceImpl.createClass((regen.ecocredit.v1alpha2.Tx.MsgCreateClass) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgCreateClassResponse>) responseObserver);
          break;
        case METHODID_CREATE_PROJECT:
          serviceImpl.createProject((regen.ecocredit.v1alpha2.Tx.MsgCreateProject) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgCreateProjectResponse>) responseObserver);
          break;
        case METHODID_CREATE_BATCH:
          serviceImpl.createBatch((regen.ecocredit.v1alpha2.Tx.MsgCreateBatch) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgCreateBatchResponse>) responseObserver);
          break;
        case METHODID_SEND:
          serviceImpl.send((regen.ecocredit.v1alpha2.Tx.MsgSend) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgSendResponse>) responseObserver);
          break;
        case METHODID_RETIRE:
          serviceImpl.retire((regen.ecocredit.v1alpha2.Tx.MsgRetire) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgRetireResponse>) responseObserver);
          break;
        case METHODID_CANCEL:
          serviceImpl.cancel((regen.ecocredit.v1alpha2.Tx.MsgCancel) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgCancelResponse>) responseObserver);
          break;
        case METHODID_UPDATE_CLASS_ADMIN:
          serviceImpl.updateClassAdmin((regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdmin) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassAdminResponse>) responseObserver);
          break;
        case METHODID_UPDATE_CLASS_ISSUERS:
          serviceImpl.updateClassIssuers((regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuers) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassIssuersResponse>) responseObserver);
          break;
        case METHODID_UPDATE_CLASS_METADATA:
          serviceImpl.updateClassMetadata((regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadata) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgUpdateClassMetadataResponse>) responseObserver);
          break;
        case METHODID_SELL:
          serviceImpl.sell((regen.ecocredit.v1alpha2.Tx.MsgSell) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgSellResponse>) responseObserver);
          break;
        case METHODID_UPDATE_SELL_ORDERS:
          serviceImpl.updateSellOrders((regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrders) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgUpdateSellOrdersResponse>) responseObserver);
          break;
        case METHODID_BUY:
          serviceImpl.buy((regen.ecocredit.v1alpha2.Tx.MsgBuy) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgBuyResponse>) responseObserver);
          break;
        case METHODID_ALLOW_ASK_DENOM:
          serviceImpl.allowAskDenom((regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenom) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgAllowAskDenomResponse>) responseObserver);
          break;
        case METHODID_CREATE_BASKET:
          serviceImpl.createBasket((regen.ecocredit.v1alpha2.Tx.MsgCreateBasket) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgCreateBasketResponse>) responseObserver);
          break;
        case METHODID_ADD_TO_BASKET:
          serviceImpl.addToBasket((regen.ecocredit.v1alpha2.Tx.MsgAddToBasket) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgAddToBasketResponse>) responseObserver);
          break;
        case METHODID_TAKE_FROM_BASKET:
          serviceImpl.takeFromBasket((regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasket) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgTakeFromBasketResponse>) responseObserver);
          break;
        case METHODID_PICK_FROM_BASKET:
          serviceImpl.pickFromBasket((regen.ecocredit.v1alpha2.Tx.MsgPickFromBasket) request,
              (io.grpc.stub.StreamObserver<regen.ecocredit.v1alpha2.Tx.MsgPickFromBasketResponse>) responseObserver);
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
      return regen.ecocredit.v1alpha2.Tx.getDescriptor();
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
              .addMethod(getCreateClassMethod())
              .addMethod(getCreateProjectMethod())
              .addMethod(getCreateBatchMethod())
              .addMethod(getSendMethod())
              .addMethod(getRetireMethod())
              .addMethod(getCancelMethod())
              .addMethod(getUpdateClassAdminMethod())
              .addMethod(getUpdateClassIssuersMethod())
              .addMethod(getUpdateClassMetadataMethod())
              .addMethod(getSellMethod())
              .addMethod(getUpdateSellOrdersMethod())
              .addMethod(getBuyMethod())
              .addMethod(getAllowAskDenomMethod())
              .addMethod(getCreateBasketMethod())
              .addMethod(getAddToBasketMethod())
              .addMethod(getTakeFromBasketMethod())
              .addMethod(getPickFromBasketMethod())
              .build();
        }
      }
    }
    return result;
  }
}
