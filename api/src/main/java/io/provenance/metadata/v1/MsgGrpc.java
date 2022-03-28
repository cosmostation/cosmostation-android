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
 * Msg defines the Metadata Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: provenance/metadata/v1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "provenance.metadata.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgWriteScopeRequest,
      io.provenance.metadata.v1.MsgWriteScopeResponse> getWriteScopeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WriteScope",
      requestType = io.provenance.metadata.v1.MsgWriteScopeRequest.class,
      responseType = io.provenance.metadata.v1.MsgWriteScopeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgWriteScopeRequest,
      io.provenance.metadata.v1.MsgWriteScopeResponse> getWriteScopeMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgWriteScopeRequest, io.provenance.metadata.v1.MsgWriteScopeResponse> getWriteScopeMethod;
    if ((getWriteScopeMethod = MsgGrpc.getWriteScopeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWriteScopeMethod = MsgGrpc.getWriteScopeMethod) == null) {
          MsgGrpc.getWriteScopeMethod = getWriteScopeMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.MsgWriteScopeRequest, io.provenance.metadata.v1.MsgWriteScopeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WriteScope"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgWriteScopeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgWriteScopeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WriteScope"))
              .build();
        }
      }
    }
    return getWriteScopeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteScopeRequest,
      io.provenance.metadata.v1.MsgDeleteScopeResponse> getDeleteScopeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteScope",
      requestType = io.provenance.metadata.v1.MsgDeleteScopeRequest.class,
      responseType = io.provenance.metadata.v1.MsgDeleteScopeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteScopeRequest,
      io.provenance.metadata.v1.MsgDeleteScopeResponse> getDeleteScopeMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteScopeRequest, io.provenance.metadata.v1.MsgDeleteScopeResponse> getDeleteScopeMethod;
    if ((getDeleteScopeMethod = MsgGrpc.getDeleteScopeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteScopeMethod = MsgGrpc.getDeleteScopeMethod) == null) {
          MsgGrpc.getDeleteScopeMethod = getDeleteScopeMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.MsgDeleteScopeRequest, io.provenance.metadata.v1.MsgDeleteScopeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteScope"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgDeleteScopeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgDeleteScopeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteScope"))
              .build();
        }
      }
    }
    return getDeleteScopeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgAddScopeDataAccessRequest,
      io.provenance.metadata.v1.MsgAddScopeDataAccessResponse> getAddScopeDataAccessMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddScopeDataAccess",
      requestType = io.provenance.metadata.v1.MsgAddScopeDataAccessRequest.class,
      responseType = io.provenance.metadata.v1.MsgAddScopeDataAccessResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgAddScopeDataAccessRequest,
      io.provenance.metadata.v1.MsgAddScopeDataAccessResponse> getAddScopeDataAccessMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgAddScopeDataAccessRequest, io.provenance.metadata.v1.MsgAddScopeDataAccessResponse> getAddScopeDataAccessMethod;
    if ((getAddScopeDataAccessMethod = MsgGrpc.getAddScopeDataAccessMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddScopeDataAccessMethod = MsgGrpc.getAddScopeDataAccessMethod) == null) {
          MsgGrpc.getAddScopeDataAccessMethod = getAddScopeDataAccessMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.MsgAddScopeDataAccessRequest, io.provenance.metadata.v1.MsgAddScopeDataAccessResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddScopeDataAccess"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgAddScopeDataAccessRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgAddScopeDataAccessResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddScopeDataAccess"))
              .build();
        }
      }
    }
    return getAddScopeDataAccessMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteScopeDataAccessRequest,
      io.provenance.metadata.v1.MsgDeleteScopeDataAccessResponse> getDeleteScopeDataAccessMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteScopeDataAccess",
      requestType = io.provenance.metadata.v1.MsgDeleteScopeDataAccessRequest.class,
      responseType = io.provenance.metadata.v1.MsgDeleteScopeDataAccessResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteScopeDataAccessRequest,
      io.provenance.metadata.v1.MsgDeleteScopeDataAccessResponse> getDeleteScopeDataAccessMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteScopeDataAccessRequest, io.provenance.metadata.v1.MsgDeleteScopeDataAccessResponse> getDeleteScopeDataAccessMethod;
    if ((getDeleteScopeDataAccessMethod = MsgGrpc.getDeleteScopeDataAccessMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteScopeDataAccessMethod = MsgGrpc.getDeleteScopeDataAccessMethod) == null) {
          MsgGrpc.getDeleteScopeDataAccessMethod = getDeleteScopeDataAccessMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.MsgDeleteScopeDataAccessRequest, io.provenance.metadata.v1.MsgDeleteScopeDataAccessResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteScopeDataAccess"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgDeleteScopeDataAccessRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgDeleteScopeDataAccessResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteScopeDataAccess"))
              .build();
        }
      }
    }
    return getDeleteScopeDataAccessMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgAddScopeOwnerRequest,
      io.provenance.metadata.v1.MsgAddScopeOwnerResponse> getAddScopeOwnerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddScopeOwner",
      requestType = io.provenance.metadata.v1.MsgAddScopeOwnerRequest.class,
      responseType = io.provenance.metadata.v1.MsgAddScopeOwnerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgAddScopeOwnerRequest,
      io.provenance.metadata.v1.MsgAddScopeOwnerResponse> getAddScopeOwnerMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgAddScopeOwnerRequest, io.provenance.metadata.v1.MsgAddScopeOwnerResponse> getAddScopeOwnerMethod;
    if ((getAddScopeOwnerMethod = MsgGrpc.getAddScopeOwnerMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddScopeOwnerMethod = MsgGrpc.getAddScopeOwnerMethod) == null) {
          MsgGrpc.getAddScopeOwnerMethod = getAddScopeOwnerMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.MsgAddScopeOwnerRequest, io.provenance.metadata.v1.MsgAddScopeOwnerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddScopeOwner"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgAddScopeOwnerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgAddScopeOwnerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddScopeOwner"))
              .build();
        }
      }
    }
    return getAddScopeOwnerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteScopeOwnerRequest,
      io.provenance.metadata.v1.MsgDeleteScopeOwnerResponse> getDeleteScopeOwnerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteScopeOwner",
      requestType = io.provenance.metadata.v1.MsgDeleteScopeOwnerRequest.class,
      responseType = io.provenance.metadata.v1.MsgDeleteScopeOwnerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteScopeOwnerRequest,
      io.provenance.metadata.v1.MsgDeleteScopeOwnerResponse> getDeleteScopeOwnerMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteScopeOwnerRequest, io.provenance.metadata.v1.MsgDeleteScopeOwnerResponse> getDeleteScopeOwnerMethod;
    if ((getDeleteScopeOwnerMethod = MsgGrpc.getDeleteScopeOwnerMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteScopeOwnerMethod = MsgGrpc.getDeleteScopeOwnerMethod) == null) {
          MsgGrpc.getDeleteScopeOwnerMethod = getDeleteScopeOwnerMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.MsgDeleteScopeOwnerRequest, io.provenance.metadata.v1.MsgDeleteScopeOwnerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteScopeOwner"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgDeleteScopeOwnerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgDeleteScopeOwnerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteScopeOwner"))
              .build();
        }
      }
    }
    return getDeleteScopeOwnerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgWriteSessionRequest,
      io.provenance.metadata.v1.MsgWriteSessionResponse> getWriteSessionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WriteSession",
      requestType = io.provenance.metadata.v1.MsgWriteSessionRequest.class,
      responseType = io.provenance.metadata.v1.MsgWriteSessionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgWriteSessionRequest,
      io.provenance.metadata.v1.MsgWriteSessionResponse> getWriteSessionMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgWriteSessionRequest, io.provenance.metadata.v1.MsgWriteSessionResponse> getWriteSessionMethod;
    if ((getWriteSessionMethod = MsgGrpc.getWriteSessionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWriteSessionMethod = MsgGrpc.getWriteSessionMethod) == null) {
          MsgGrpc.getWriteSessionMethod = getWriteSessionMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.MsgWriteSessionRequest, io.provenance.metadata.v1.MsgWriteSessionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WriteSession"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgWriteSessionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgWriteSessionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WriteSession"))
              .build();
        }
      }
    }
    return getWriteSessionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgWriteRecordRequest,
      io.provenance.metadata.v1.MsgWriteRecordResponse> getWriteRecordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WriteRecord",
      requestType = io.provenance.metadata.v1.MsgWriteRecordRequest.class,
      responseType = io.provenance.metadata.v1.MsgWriteRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgWriteRecordRequest,
      io.provenance.metadata.v1.MsgWriteRecordResponse> getWriteRecordMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgWriteRecordRequest, io.provenance.metadata.v1.MsgWriteRecordResponse> getWriteRecordMethod;
    if ((getWriteRecordMethod = MsgGrpc.getWriteRecordMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWriteRecordMethod = MsgGrpc.getWriteRecordMethod) == null) {
          MsgGrpc.getWriteRecordMethod = getWriteRecordMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.MsgWriteRecordRequest, io.provenance.metadata.v1.MsgWriteRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WriteRecord"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgWriteRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgWriteRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WriteRecord"))
              .build();
        }
      }
    }
    return getWriteRecordMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteRecordRequest,
      io.provenance.metadata.v1.MsgDeleteRecordResponse> getDeleteRecordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteRecord",
      requestType = io.provenance.metadata.v1.MsgDeleteRecordRequest.class,
      responseType = io.provenance.metadata.v1.MsgDeleteRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteRecordRequest,
      io.provenance.metadata.v1.MsgDeleteRecordResponse> getDeleteRecordMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteRecordRequest, io.provenance.metadata.v1.MsgDeleteRecordResponse> getDeleteRecordMethod;
    if ((getDeleteRecordMethod = MsgGrpc.getDeleteRecordMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteRecordMethod = MsgGrpc.getDeleteRecordMethod) == null) {
          MsgGrpc.getDeleteRecordMethod = getDeleteRecordMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.MsgDeleteRecordRequest, io.provenance.metadata.v1.MsgDeleteRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteRecord"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgDeleteRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgDeleteRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteRecord"))
              .build();
        }
      }
    }
    return getDeleteRecordMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgWriteScopeSpecificationRequest,
      io.provenance.metadata.v1.MsgWriteScopeSpecificationResponse> getWriteScopeSpecificationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WriteScopeSpecification",
      requestType = io.provenance.metadata.v1.MsgWriteScopeSpecificationRequest.class,
      responseType = io.provenance.metadata.v1.MsgWriteScopeSpecificationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgWriteScopeSpecificationRequest,
      io.provenance.metadata.v1.MsgWriteScopeSpecificationResponse> getWriteScopeSpecificationMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgWriteScopeSpecificationRequest, io.provenance.metadata.v1.MsgWriteScopeSpecificationResponse> getWriteScopeSpecificationMethod;
    if ((getWriteScopeSpecificationMethod = MsgGrpc.getWriteScopeSpecificationMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWriteScopeSpecificationMethod = MsgGrpc.getWriteScopeSpecificationMethod) == null) {
          MsgGrpc.getWriteScopeSpecificationMethod = getWriteScopeSpecificationMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.MsgWriteScopeSpecificationRequest, io.provenance.metadata.v1.MsgWriteScopeSpecificationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WriteScopeSpecification"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgWriteScopeSpecificationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgWriteScopeSpecificationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WriteScopeSpecification"))
              .build();
        }
      }
    }
    return getWriteScopeSpecificationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteScopeSpecificationRequest,
      io.provenance.metadata.v1.MsgDeleteScopeSpecificationResponse> getDeleteScopeSpecificationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteScopeSpecification",
      requestType = io.provenance.metadata.v1.MsgDeleteScopeSpecificationRequest.class,
      responseType = io.provenance.metadata.v1.MsgDeleteScopeSpecificationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteScopeSpecificationRequest,
      io.provenance.metadata.v1.MsgDeleteScopeSpecificationResponse> getDeleteScopeSpecificationMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteScopeSpecificationRequest, io.provenance.metadata.v1.MsgDeleteScopeSpecificationResponse> getDeleteScopeSpecificationMethod;
    if ((getDeleteScopeSpecificationMethod = MsgGrpc.getDeleteScopeSpecificationMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteScopeSpecificationMethod = MsgGrpc.getDeleteScopeSpecificationMethod) == null) {
          MsgGrpc.getDeleteScopeSpecificationMethod = getDeleteScopeSpecificationMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.MsgDeleteScopeSpecificationRequest, io.provenance.metadata.v1.MsgDeleteScopeSpecificationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteScopeSpecification"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgDeleteScopeSpecificationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgDeleteScopeSpecificationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteScopeSpecification"))
              .build();
        }
      }
    }
    return getDeleteScopeSpecificationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgWriteContractSpecificationRequest,
      io.provenance.metadata.v1.MsgWriteContractSpecificationResponse> getWriteContractSpecificationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WriteContractSpecification",
      requestType = io.provenance.metadata.v1.MsgWriteContractSpecificationRequest.class,
      responseType = io.provenance.metadata.v1.MsgWriteContractSpecificationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgWriteContractSpecificationRequest,
      io.provenance.metadata.v1.MsgWriteContractSpecificationResponse> getWriteContractSpecificationMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgWriteContractSpecificationRequest, io.provenance.metadata.v1.MsgWriteContractSpecificationResponse> getWriteContractSpecificationMethod;
    if ((getWriteContractSpecificationMethod = MsgGrpc.getWriteContractSpecificationMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWriteContractSpecificationMethod = MsgGrpc.getWriteContractSpecificationMethod) == null) {
          MsgGrpc.getWriteContractSpecificationMethod = getWriteContractSpecificationMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.MsgWriteContractSpecificationRequest, io.provenance.metadata.v1.MsgWriteContractSpecificationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WriteContractSpecification"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgWriteContractSpecificationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgWriteContractSpecificationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WriteContractSpecification"))
              .build();
        }
      }
    }
    return getWriteContractSpecificationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteContractSpecificationRequest,
      io.provenance.metadata.v1.MsgDeleteContractSpecificationResponse> getDeleteContractSpecificationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteContractSpecification",
      requestType = io.provenance.metadata.v1.MsgDeleteContractSpecificationRequest.class,
      responseType = io.provenance.metadata.v1.MsgDeleteContractSpecificationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteContractSpecificationRequest,
      io.provenance.metadata.v1.MsgDeleteContractSpecificationResponse> getDeleteContractSpecificationMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteContractSpecificationRequest, io.provenance.metadata.v1.MsgDeleteContractSpecificationResponse> getDeleteContractSpecificationMethod;
    if ((getDeleteContractSpecificationMethod = MsgGrpc.getDeleteContractSpecificationMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteContractSpecificationMethod = MsgGrpc.getDeleteContractSpecificationMethod) == null) {
          MsgGrpc.getDeleteContractSpecificationMethod = getDeleteContractSpecificationMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.MsgDeleteContractSpecificationRequest, io.provenance.metadata.v1.MsgDeleteContractSpecificationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteContractSpecification"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgDeleteContractSpecificationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgDeleteContractSpecificationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteContractSpecification"))
              .build();
        }
      }
    }
    return getDeleteContractSpecificationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecRequest,
      io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecResponse> getAddContractSpecToScopeSpecMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddContractSpecToScopeSpec",
      requestType = io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecRequest.class,
      responseType = io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecRequest,
      io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecResponse> getAddContractSpecToScopeSpecMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecRequest, io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecResponse> getAddContractSpecToScopeSpecMethod;
    if ((getAddContractSpecToScopeSpecMethod = MsgGrpc.getAddContractSpecToScopeSpecMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddContractSpecToScopeSpecMethod = MsgGrpc.getAddContractSpecToScopeSpecMethod) == null) {
          MsgGrpc.getAddContractSpecToScopeSpecMethod = getAddContractSpecToScopeSpecMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecRequest, io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddContractSpecToScopeSpec"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddContractSpecToScopeSpec"))
              .build();
        }
      }
    }
    return getAddContractSpecToScopeSpecMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecRequest,
      io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecResponse> getDeleteContractSpecFromScopeSpecMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteContractSpecFromScopeSpec",
      requestType = io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecRequest.class,
      responseType = io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecRequest,
      io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecResponse> getDeleteContractSpecFromScopeSpecMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecRequest, io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecResponse> getDeleteContractSpecFromScopeSpecMethod;
    if ((getDeleteContractSpecFromScopeSpecMethod = MsgGrpc.getDeleteContractSpecFromScopeSpecMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteContractSpecFromScopeSpecMethod = MsgGrpc.getDeleteContractSpecFromScopeSpecMethod) == null) {
          MsgGrpc.getDeleteContractSpecFromScopeSpecMethod = getDeleteContractSpecFromScopeSpecMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecRequest, io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteContractSpecFromScopeSpec"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteContractSpecFromScopeSpec"))
              .build();
        }
      }
    }
    return getDeleteContractSpecFromScopeSpecMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgWriteRecordSpecificationRequest,
      io.provenance.metadata.v1.MsgWriteRecordSpecificationResponse> getWriteRecordSpecificationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WriteRecordSpecification",
      requestType = io.provenance.metadata.v1.MsgWriteRecordSpecificationRequest.class,
      responseType = io.provenance.metadata.v1.MsgWriteRecordSpecificationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgWriteRecordSpecificationRequest,
      io.provenance.metadata.v1.MsgWriteRecordSpecificationResponse> getWriteRecordSpecificationMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgWriteRecordSpecificationRequest, io.provenance.metadata.v1.MsgWriteRecordSpecificationResponse> getWriteRecordSpecificationMethod;
    if ((getWriteRecordSpecificationMethod = MsgGrpc.getWriteRecordSpecificationMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWriteRecordSpecificationMethod = MsgGrpc.getWriteRecordSpecificationMethod) == null) {
          MsgGrpc.getWriteRecordSpecificationMethod = getWriteRecordSpecificationMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.MsgWriteRecordSpecificationRequest, io.provenance.metadata.v1.MsgWriteRecordSpecificationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WriteRecordSpecification"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgWriteRecordSpecificationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgWriteRecordSpecificationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WriteRecordSpecification"))
              .build();
        }
      }
    }
    return getWriteRecordSpecificationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteRecordSpecificationRequest,
      io.provenance.metadata.v1.MsgDeleteRecordSpecificationResponse> getDeleteRecordSpecificationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteRecordSpecification",
      requestType = io.provenance.metadata.v1.MsgDeleteRecordSpecificationRequest.class,
      responseType = io.provenance.metadata.v1.MsgDeleteRecordSpecificationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteRecordSpecificationRequest,
      io.provenance.metadata.v1.MsgDeleteRecordSpecificationResponse> getDeleteRecordSpecificationMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteRecordSpecificationRequest, io.provenance.metadata.v1.MsgDeleteRecordSpecificationResponse> getDeleteRecordSpecificationMethod;
    if ((getDeleteRecordSpecificationMethod = MsgGrpc.getDeleteRecordSpecificationMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteRecordSpecificationMethod = MsgGrpc.getDeleteRecordSpecificationMethod) == null) {
          MsgGrpc.getDeleteRecordSpecificationMethod = getDeleteRecordSpecificationMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.MsgDeleteRecordSpecificationRequest, io.provenance.metadata.v1.MsgDeleteRecordSpecificationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteRecordSpecification"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgDeleteRecordSpecificationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgDeleteRecordSpecificationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteRecordSpecification"))
              .build();
        }
      }
    }
    return getDeleteRecordSpecificationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgWriteP8eContractSpecRequest,
      io.provenance.metadata.v1.MsgWriteP8eContractSpecResponse> getWriteP8eContractSpecMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WriteP8eContractSpec",
      requestType = io.provenance.metadata.v1.MsgWriteP8eContractSpecRequest.class,
      responseType = io.provenance.metadata.v1.MsgWriteP8eContractSpecResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgWriteP8eContractSpecRequest,
      io.provenance.metadata.v1.MsgWriteP8eContractSpecResponse> getWriteP8eContractSpecMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgWriteP8eContractSpecRequest, io.provenance.metadata.v1.MsgWriteP8eContractSpecResponse> getWriteP8eContractSpecMethod;
    if ((getWriteP8eContractSpecMethod = MsgGrpc.getWriteP8eContractSpecMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getWriteP8eContractSpecMethod = MsgGrpc.getWriteP8eContractSpecMethod) == null) {
          MsgGrpc.getWriteP8eContractSpecMethod = getWriteP8eContractSpecMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.MsgWriteP8eContractSpecRequest, io.provenance.metadata.v1.MsgWriteP8eContractSpecResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WriteP8eContractSpec"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgWriteP8eContractSpecRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgWriteP8eContractSpecResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("WriteP8eContractSpec"))
              .build();
        }
      }
    }
    return getWriteP8eContractSpecMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgP8eMemorializeContractRequest,
      io.provenance.metadata.v1.MsgP8eMemorializeContractResponse> getP8eMemorializeContractMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "P8eMemorializeContract",
      requestType = io.provenance.metadata.v1.MsgP8eMemorializeContractRequest.class,
      responseType = io.provenance.metadata.v1.MsgP8eMemorializeContractResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgP8eMemorializeContractRequest,
      io.provenance.metadata.v1.MsgP8eMemorializeContractResponse> getP8eMemorializeContractMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgP8eMemorializeContractRequest, io.provenance.metadata.v1.MsgP8eMemorializeContractResponse> getP8eMemorializeContractMethod;
    if ((getP8eMemorializeContractMethod = MsgGrpc.getP8eMemorializeContractMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getP8eMemorializeContractMethod = MsgGrpc.getP8eMemorializeContractMethod) == null) {
          MsgGrpc.getP8eMemorializeContractMethod = getP8eMemorializeContractMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.MsgP8eMemorializeContractRequest, io.provenance.metadata.v1.MsgP8eMemorializeContractResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "P8eMemorializeContract"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgP8eMemorializeContractRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgP8eMemorializeContractResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("P8eMemorializeContract"))
              .build();
        }
      }
    }
    return getP8eMemorializeContractMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgBindOSLocatorRequest,
      io.provenance.metadata.v1.MsgBindOSLocatorResponse> getBindOSLocatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BindOSLocator",
      requestType = io.provenance.metadata.v1.MsgBindOSLocatorRequest.class,
      responseType = io.provenance.metadata.v1.MsgBindOSLocatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgBindOSLocatorRequest,
      io.provenance.metadata.v1.MsgBindOSLocatorResponse> getBindOSLocatorMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgBindOSLocatorRequest, io.provenance.metadata.v1.MsgBindOSLocatorResponse> getBindOSLocatorMethod;
    if ((getBindOSLocatorMethod = MsgGrpc.getBindOSLocatorMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBindOSLocatorMethod = MsgGrpc.getBindOSLocatorMethod) == null) {
          MsgGrpc.getBindOSLocatorMethod = getBindOSLocatorMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.MsgBindOSLocatorRequest, io.provenance.metadata.v1.MsgBindOSLocatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BindOSLocator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgBindOSLocatorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgBindOSLocatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BindOSLocator"))
              .build();
        }
      }
    }
    return getBindOSLocatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteOSLocatorRequest,
      io.provenance.metadata.v1.MsgDeleteOSLocatorResponse> getDeleteOSLocatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteOSLocator",
      requestType = io.provenance.metadata.v1.MsgDeleteOSLocatorRequest.class,
      responseType = io.provenance.metadata.v1.MsgDeleteOSLocatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteOSLocatorRequest,
      io.provenance.metadata.v1.MsgDeleteOSLocatorResponse> getDeleteOSLocatorMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgDeleteOSLocatorRequest, io.provenance.metadata.v1.MsgDeleteOSLocatorResponse> getDeleteOSLocatorMethod;
    if ((getDeleteOSLocatorMethod = MsgGrpc.getDeleteOSLocatorMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteOSLocatorMethod = MsgGrpc.getDeleteOSLocatorMethod) == null) {
          MsgGrpc.getDeleteOSLocatorMethod = getDeleteOSLocatorMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.MsgDeleteOSLocatorRequest, io.provenance.metadata.v1.MsgDeleteOSLocatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteOSLocator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgDeleteOSLocatorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgDeleteOSLocatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteOSLocator"))
              .build();
        }
      }
    }
    return getDeleteOSLocatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgModifyOSLocatorRequest,
      io.provenance.metadata.v1.MsgModifyOSLocatorResponse> getModifyOSLocatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ModifyOSLocator",
      requestType = io.provenance.metadata.v1.MsgModifyOSLocatorRequest.class,
      responseType = io.provenance.metadata.v1.MsgModifyOSLocatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgModifyOSLocatorRequest,
      io.provenance.metadata.v1.MsgModifyOSLocatorResponse> getModifyOSLocatorMethod() {
    io.grpc.MethodDescriptor<io.provenance.metadata.v1.MsgModifyOSLocatorRequest, io.provenance.metadata.v1.MsgModifyOSLocatorResponse> getModifyOSLocatorMethod;
    if ((getModifyOSLocatorMethod = MsgGrpc.getModifyOSLocatorMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getModifyOSLocatorMethod = MsgGrpc.getModifyOSLocatorMethod) == null) {
          MsgGrpc.getModifyOSLocatorMethod = getModifyOSLocatorMethod =
              io.grpc.MethodDescriptor.<io.provenance.metadata.v1.MsgModifyOSLocatorRequest, io.provenance.metadata.v1.MsgModifyOSLocatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ModifyOSLocator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgModifyOSLocatorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.provenance.metadata.v1.MsgModifyOSLocatorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ModifyOSLocator"))
              .build();
        }
      }
    }
    return getModifyOSLocatorMethod;
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
   * Msg defines the Metadata Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * WriteScope adds or updates a scope.
     * </pre>
     */
    public void writeScope(io.provenance.metadata.v1.MsgWriteScopeRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgWriteScopeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWriteScopeMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteScope deletes a scope and all associated Records, Sessions.
     * </pre>
     */
    public void deleteScope(io.provenance.metadata.v1.MsgDeleteScopeRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteScopeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteScopeMethod(), responseObserver);
    }

    /**
     * <pre>
     * AddScopeDataAccess adds data access AccAddress to scope
     * </pre>
     */
    public void addScopeDataAccess(io.provenance.metadata.v1.MsgAddScopeDataAccessRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgAddScopeDataAccessResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddScopeDataAccessMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteScopeDataAccess removes data access AccAddress from scope
     * </pre>
     */
    public void deleteScopeDataAccess(io.provenance.metadata.v1.MsgDeleteScopeDataAccessRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteScopeDataAccessResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteScopeDataAccessMethod(), responseObserver);
    }

    /**
     * <pre>
     * AddScopeOwner adds new owner AccAddress to scope
     * </pre>
     */
    public void addScopeOwner(io.provenance.metadata.v1.MsgAddScopeOwnerRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgAddScopeOwnerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddScopeOwnerMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteScopeOwner removes data access AccAddress from scope
     * </pre>
     */
    public void deleteScopeOwner(io.provenance.metadata.v1.MsgDeleteScopeOwnerRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteScopeOwnerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteScopeOwnerMethod(), responseObserver);
    }

    /**
     * <pre>
     * WriteSession adds or updates a session context.
     * </pre>
     */
    public void writeSession(io.provenance.metadata.v1.MsgWriteSessionRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgWriteSessionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWriteSessionMethod(), responseObserver);
    }

    /**
     * <pre>
     * WriteRecord adds or updates a record.
     * </pre>
     */
    public void writeRecord(io.provenance.metadata.v1.MsgWriteRecordRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgWriteRecordResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWriteRecordMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteRecord deletes a record.
     * </pre>
     */
    public void deleteRecord(io.provenance.metadata.v1.MsgDeleteRecordRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteRecordResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteRecordMethod(), responseObserver);
    }

    /**
     * <pre>
     * WriteScopeSpecification adds or updates a scope specification.
     * </pre>
     */
    public void writeScopeSpecification(io.provenance.metadata.v1.MsgWriteScopeSpecificationRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgWriteScopeSpecificationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWriteScopeSpecificationMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteScopeSpecification deletes a scope specification.
     * </pre>
     */
    public void deleteScopeSpecification(io.provenance.metadata.v1.MsgDeleteScopeSpecificationRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteScopeSpecificationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteScopeSpecificationMethod(), responseObserver);
    }

    /**
     * <pre>
     * WriteContractSpecification adds or updates a contract specification.
     * </pre>
     */
    public void writeContractSpecification(io.provenance.metadata.v1.MsgWriteContractSpecificationRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgWriteContractSpecificationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWriteContractSpecificationMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteContractSpecification deletes a contract specification.
     * </pre>
     */
    public void deleteContractSpecification(io.provenance.metadata.v1.MsgDeleteContractSpecificationRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteContractSpecificationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteContractSpecificationMethod(), responseObserver);
    }

    /**
     * <pre>
     * AddContractSpecToScopeSpec adds contract specification to a scope specification.
     * </pre>
     */
    public void addContractSpecToScopeSpec(io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddContractSpecToScopeSpecMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteContractSpecFromScopeSpec deletes a contract specification from a scope specification.
     * </pre>
     */
    public void deleteContractSpecFromScopeSpec(io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteContractSpecFromScopeSpecMethod(), responseObserver);
    }

    /**
     * <pre>
     * WriteRecordSpecification adds or updates a record specification.
     * </pre>
     */
    public void writeRecordSpecification(io.provenance.metadata.v1.MsgWriteRecordSpecificationRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgWriteRecordSpecificationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWriteRecordSpecificationMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteRecordSpecification deletes a record specification.
     * </pre>
     */
    public void deleteRecordSpecification(io.provenance.metadata.v1.MsgDeleteRecordSpecificationRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteRecordSpecificationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteRecordSpecificationMethod(), responseObserver);
    }

    /**
     * <pre>
     * WriteP8eContractSpec adds a P8e v39 contract spec as a v40 ContractSpecification
     * It only exists to help facilitate the transition. Users should transition to WriteContractSpecification.
     * </pre>
     */
    @java.lang.Deprecated
    public void writeP8eContractSpec(io.provenance.metadata.v1.MsgWriteP8eContractSpecRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgWriteP8eContractSpecResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWriteP8eContractSpecMethod(), responseObserver);
    }

    /**
     * <pre>
     * P8EMemorializeContract records the results of a P8e contract execution as a session and set of records in a scope
     * It only exists to help facilitate the transition. Users should transition to calling the individual Write methods.
     * </pre>
     */
    @java.lang.Deprecated
    public void p8eMemorializeContract(io.provenance.metadata.v1.MsgP8eMemorializeContractRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgP8eMemorializeContractResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getP8eMemorializeContractMethod(), responseObserver);
    }

    /**
     * <pre>
     * BindOSLocator binds an owner address to a uri.
     * </pre>
     */
    public void bindOSLocator(io.provenance.metadata.v1.MsgBindOSLocatorRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgBindOSLocatorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBindOSLocatorMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteOSLocator deletes an existing ObjectStoreLocator record.
     * </pre>
     */
    public void deleteOSLocator(io.provenance.metadata.v1.MsgDeleteOSLocatorRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteOSLocatorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteOSLocatorMethod(), responseObserver);
    }

    /**
     * <pre>
     * ModifyOSLocator updates an ObjectStoreLocator record by the current owner.
     * </pre>
     */
    public void modifyOSLocator(io.provenance.metadata.v1.MsgModifyOSLocatorRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgModifyOSLocatorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getModifyOSLocatorMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getWriteScopeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.MsgWriteScopeRequest,
                io.provenance.metadata.v1.MsgWriteScopeResponse>(
                  this, METHODID_WRITE_SCOPE)))
          .addMethod(
            getDeleteScopeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.MsgDeleteScopeRequest,
                io.provenance.metadata.v1.MsgDeleteScopeResponse>(
                  this, METHODID_DELETE_SCOPE)))
          .addMethod(
            getAddScopeDataAccessMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.MsgAddScopeDataAccessRequest,
                io.provenance.metadata.v1.MsgAddScopeDataAccessResponse>(
                  this, METHODID_ADD_SCOPE_DATA_ACCESS)))
          .addMethod(
            getDeleteScopeDataAccessMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.MsgDeleteScopeDataAccessRequest,
                io.provenance.metadata.v1.MsgDeleteScopeDataAccessResponse>(
                  this, METHODID_DELETE_SCOPE_DATA_ACCESS)))
          .addMethod(
            getAddScopeOwnerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.MsgAddScopeOwnerRequest,
                io.provenance.metadata.v1.MsgAddScopeOwnerResponse>(
                  this, METHODID_ADD_SCOPE_OWNER)))
          .addMethod(
            getDeleteScopeOwnerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.MsgDeleteScopeOwnerRequest,
                io.provenance.metadata.v1.MsgDeleteScopeOwnerResponse>(
                  this, METHODID_DELETE_SCOPE_OWNER)))
          .addMethod(
            getWriteSessionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.MsgWriteSessionRequest,
                io.provenance.metadata.v1.MsgWriteSessionResponse>(
                  this, METHODID_WRITE_SESSION)))
          .addMethod(
            getWriteRecordMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.MsgWriteRecordRequest,
                io.provenance.metadata.v1.MsgWriteRecordResponse>(
                  this, METHODID_WRITE_RECORD)))
          .addMethod(
            getDeleteRecordMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.MsgDeleteRecordRequest,
                io.provenance.metadata.v1.MsgDeleteRecordResponse>(
                  this, METHODID_DELETE_RECORD)))
          .addMethod(
            getWriteScopeSpecificationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.MsgWriteScopeSpecificationRequest,
                io.provenance.metadata.v1.MsgWriteScopeSpecificationResponse>(
                  this, METHODID_WRITE_SCOPE_SPECIFICATION)))
          .addMethod(
            getDeleteScopeSpecificationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.MsgDeleteScopeSpecificationRequest,
                io.provenance.metadata.v1.MsgDeleteScopeSpecificationResponse>(
                  this, METHODID_DELETE_SCOPE_SPECIFICATION)))
          .addMethod(
            getWriteContractSpecificationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.MsgWriteContractSpecificationRequest,
                io.provenance.metadata.v1.MsgWriteContractSpecificationResponse>(
                  this, METHODID_WRITE_CONTRACT_SPECIFICATION)))
          .addMethod(
            getDeleteContractSpecificationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.MsgDeleteContractSpecificationRequest,
                io.provenance.metadata.v1.MsgDeleteContractSpecificationResponse>(
                  this, METHODID_DELETE_CONTRACT_SPECIFICATION)))
          .addMethod(
            getAddContractSpecToScopeSpecMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecRequest,
                io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecResponse>(
                  this, METHODID_ADD_CONTRACT_SPEC_TO_SCOPE_SPEC)))
          .addMethod(
            getDeleteContractSpecFromScopeSpecMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecRequest,
                io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecResponse>(
                  this, METHODID_DELETE_CONTRACT_SPEC_FROM_SCOPE_SPEC)))
          .addMethod(
            getWriteRecordSpecificationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.MsgWriteRecordSpecificationRequest,
                io.provenance.metadata.v1.MsgWriteRecordSpecificationResponse>(
                  this, METHODID_WRITE_RECORD_SPECIFICATION)))
          .addMethod(
            getDeleteRecordSpecificationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.MsgDeleteRecordSpecificationRequest,
                io.provenance.metadata.v1.MsgDeleteRecordSpecificationResponse>(
                  this, METHODID_DELETE_RECORD_SPECIFICATION)))
          .addMethod(
            getWriteP8eContractSpecMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.MsgWriteP8eContractSpecRequest,
                io.provenance.metadata.v1.MsgWriteP8eContractSpecResponse>(
                  this, METHODID_WRITE_P8E_CONTRACT_SPEC)))
          .addMethod(
            getP8eMemorializeContractMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.MsgP8eMemorializeContractRequest,
                io.provenance.metadata.v1.MsgP8eMemorializeContractResponse>(
                  this, METHODID_P8E_MEMORIALIZE_CONTRACT)))
          .addMethod(
            getBindOSLocatorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.MsgBindOSLocatorRequest,
                io.provenance.metadata.v1.MsgBindOSLocatorResponse>(
                  this, METHODID_BIND_OSLOCATOR)))
          .addMethod(
            getDeleteOSLocatorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.MsgDeleteOSLocatorRequest,
                io.provenance.metadata.v1.MsgDeleteOSLocatorResponse>(
                  this, METHODID_DELETE_OSLOCATOR)))
          .addMethod(
            getModifyOSLocatorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.provenance.metadata.v1.MsgModifyOSLocatorRequest,
                io.provenance.metadata.v1.MsgModifyOSLocatorResponse>(
                  this, METHODID_MODIFY_OSLOCATOR)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the Metadata Msg service.
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
     * WriteScope adds or updates a scope.
     * </pre>
     */
    public void writeScope(io.provenance.metadata.v1.MsgWriteScopeRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgWriteScopeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWriteScopeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteScope deletes a scope and all associated Records, Sessions.
     * </pre>
     */
    public void deleteScope(io.provenance.metadata.v1.MsgDeleteScopeRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteScopeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteScopeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AddScopeDataAccess adds data access AccAddress to scope
     * </pre>
     */
    public void addScopeDataAccess(io.provenance.metadata.v1.MsgAddScopeDataAccessRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgAddScopeDataAccessResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddScopeDataAccessMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteScopeDataAccess removes data access AccAddress from scope
     * </pre>
     */
    public void deleteScopeDataAccess(io.provenance.metadata.v1.MsgDeleteScopeDataAccessRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteScopeDataAccessResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteScopeDataAccessMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AddScopeOwner adds new owner AccAddress to scope
     * </pre>
     */
    public void addScopeOwner(io.provenance.metadata.v1.MsgAddScopeOwnerRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgAddScopeOwnerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddScopeOwnerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteScopeOwner removes data access AccAddress from scope
     * </pre>
     */
    public void deleteScopeOwner(io.provenance.metadata.v1.MsgDeleteScopeOwnerRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteScopeOwnerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteScopeOwnerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WriteSession adds or updates a session context.
     * </pre>
     */
    public void writeSession(io.provenance.metadata.v1.MsgWriteSessionRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgWriteSessionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWriteSessionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WriteRecord adds or updates a record.
     * </pre>
     */
    public void writeRecord(io.provenance.metadata.v1.MsgWriteRecordRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgWriteRecordResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWriteRecordMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteRecord deletes a record.
     * </pre>
     */
    public void deleteRecord(io.provenance.metadata.v1.MsgDeleteRecordRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteRecordResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteRecordMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WriteScopeSpecification adds or updates a scope specification.
     * </pre>
     */
    public void writeScopeSpecification(io.provenance.metadata.v1.MsgWriteScopeSpecificationRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgWriteScopeSpecificationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWriteScopeSpecificationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteScopeSpecification deletes a scope specification.
     * </pre>
     */
    public void deleteScopeSpecification(io.provenance.metadata.v1.MsgDeleteScopeSpecificationRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteScopeSpecificationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteScopeSpecificationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WriteContractSpecification adds or updates a contract specification.
     * </pre>
     */
    public void writeContractSpecification(io.provenance.metadata.v1.MsgWriteContractSpecificationRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgWriteContractSpecificationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWriteContractSpecificationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteContractSpecification deletes a contract specification.
     * </pre>
     */
    public void deleteContractSpecification(io.provenance.metadata.v1.MsgDeleteContractSpecificationRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteContractSpecificationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteContractSpecificationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AddContractSpecToScopeSpec adds contract specification to a scope specification.
     * </pre>
     */
    public void addContractSpecToScopeSpec(io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddContractSpecToScopeSpecMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteContractSpecFromScopeSpec deletes a contract specification from a scope specification.
     * </pre>
     */
    public void deleteContractSpecFromScopeSpec(io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteContractSpecFromScopeSpecMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WriteRecordSpecification adds or updates a record specification.
     * </pre>
     */
    public void writeRecordSpecification(io.provenance.metadata.v1.MsgWriteRecordSpecificationRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgWriteRecordSpecificationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWriteRecordSpecificationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteRecordSpecification deletes a record specification.
     * </pre>
     */
    public void deleteRecordSpecification(io.provenance.metadata.v1.MsgDeleteRecordSpecificationRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteRecordSpecificationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteRecordSpecificationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * WriteP8eContractSpec adds a P8e v39 contract spec as a v40 ContractSpecification
     * It only exists to help facilitate the transition. Users should transition to WriteContractSpecification.
     * </pre>
     */
    @java.lang.Deprecated
    public void writeP8eContractSpec(io.provenance.metadata.v1.MsgWriteP8eContractSpecRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgWriteP8eContractSpecResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWriteP8eContractSpecMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * P8EMemorializeContract records the results of a P8e contract execution as a session and set of records in a scope
     * It only exists to help facilitate the transition. Users should transition to calling the individual Write methods.
     * </pre>
     */
    @java.lang.Deprecated
    public void p8eMemorializeContract(io.provenance.metadata.v1.MsgP8eMemorializeContractRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgP8eMemorializeContractResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getP8eMemorializeContractMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BindOSLocator binds an owner address to a uri.
     * </pre>
     */
    public void bindOSLocator(io.provenance.metadata.v1.MsgBindOSLocatorRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgBindOSLocatorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBindOSLocatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteOSLocator deletes an existing ObjectStoreLocator record.
     * </pre>
     */
    public void deleteOSLocator(io.provenance.metadata.v1.MsgDeleteOSLocatorRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteOSLocatorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteOSLocatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ModifyOSLocator updates an ObjectStoreLocator record by the current owner.
     * </pre>
     */
    public void modifyOSLocator(io.provenance.metadata.v1.MsgModifyOSLocatorRequest request,
        io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgModifyOSLocatorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getModifyOSLocatorMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the Metadata Msg service.
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
     * WriteScope adds or updates a scope.
     * </pre>
     */
    public io.provenance.metadata.v1.MsgWriteScopeResponse writeScope(io.provenance.metadata.v1.MsgWriteScopeRequest request) {
      return blockingUnaryCall(
          getChannel(), getWriteScopeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteScope deletes a scope and all associated Records, Sessions.
     * </pre>
     */
    public io.provenance.metadata.v1.MsgDeleteScopeResponse deleteScope(io.provenance.metadata.v1.MsgDeleteScopeRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteScopeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AddScopeDataAccess adds data access AccAddress to scope
     * </pre>
     */
    public io.provenance.metadata.v1.MsgAddScopeDataAccessResponse addScopeDataAccess(io.provenance.metadata.v1.MsgAddScopeDataAccessRequest request) {
      return blockingUnaryCall(
          getChannel(), getAddScopeDataAccessMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteScopeDataAccess removes data access AccAddress from scope
     * </pre>
     */
    public io.provenance.metadata.v1.MsgDeleteScopeDataAccessResponse deleteScopeDataAccess(io.provenance.metadata.v1.MsgDeleteScopeDataAccessRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteScopeDataAccessMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AddScopeOwner adds new owner AccAddress to scope
     * </pre>
     */
    public io.provenance.metadata.v1.MsgAddScopeOwnerResponse addScopeOwner(io.provenance.metadata.v1.MsgAddScopeOwnerRequest request) {
      return blockingUnaryCall(
          getChannel(), getAddScopeOwnerMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteScopeOwner removes data access AccAddress from scope
     * </pre>
     */
    public io.provenance.metadata.v1.MsgDeleteScopeOwnerResponse deleteScopeOwner(io.provenance.metadata.v1.MsgDeleteScopeOwnerRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteScopeOwnerMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WriteSession adds or updates a session context.
     * </pre>
     */
    public io.provenance.metadata.v1.MsgWriteSessionResponse writeSession(io.provenance.metadata.v1.MsgWriteSessionRequest request) {
      return blockingUnaryCall(
          getChannel(), getWriteSessionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WriteRecord adds or updates a record.
     * </pre>
     */
    public io.provenance.metadata.v1.MsgWriteRecordResponse writeRecord(io.provenance.metadata.v1.MsgWriteRecordRequest request) {
      return blockingUnaryCall(
          getChannel(), getWriteRecordMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteRecord deletes a record.
     * </pre>
     */
    public io.provenance.metadata.v1.MsgDeleteRecordResponse deleteRecord(io.provenance.metadata.v1.MsgDeleteRecordRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteRecordMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WriteScopeSpecification adds or updates a scope specification.
     * </pre>
     */
    public io.provenance.metadata.v1.MsgWriteScopeSpecificationResponse writeScopeSpecification(io.provenance.metadata.v1.MsgWriteScopeSpecificationRequest request) {
      return blockingUnaryCall(
          getChannel(), getWriteScopeSpecificationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteScopeSpecification deletes a scope specification.
     * </pre>
     */
    public io.provenance.metadata.v1.MsgDeleteScopeSpecificationResponse deleteScopeSpecification(io.provenance.metadata.v1.MsgDeleteScopeSpecificationRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteScopeSpecificationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WriteContractSpecification adds or updates a contract specification.
     * </pre>
     */
    public io.provenance.metadata.v1.MsgWriteContractSpecificationResponse writeContractSpecification(io.provenance.metadata.v1.MsgWriteContractSpecificationRequest request) {
      return blockingUnaryCall(
          getChannel(), getWriteContractSpecificationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteContractSpecification deletes a contract specification.
     * </pre>
     */
    public io.provenance.metadata.v1.MsgDeleteContractSpecificationResponse deleteContractSpecification(io.provenance.metadata.v1.MsgDeleteContractSpecificationRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteContractSpecificationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AddContractSpecToScopeSpec adds contract specification to a scope specification.
     * </pre>
     */
    public io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecResponse addContractSpecToScopeSpec(io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecRequest request) {
      return blockingUnaryCall(
          getChannel(), getAddContractSpecToScopeSpecMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteContractSpecFromScopeSpec deletes a contract specification from a scope specification.
     * </pre>
     */
    public io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecResponse deleteContractSpecFromScopeSpec(io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteContractSpecFromScopeSpecMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WriteRecordSpecification adds or updates a record specification.
     * </pre>
     */
    public io.provenance.metadata.v1.MsgWriteRecordSpecificationResponse writeRecordSpecification(io.provenance.metadata.v1.MsgWriteRecordSpecificationRequest request) {
      return blockingUnaryCall(
          getChannel(), getWriteRecordSpecificationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteRecordSpecification deletes a record specification.
     * </pre>
     */
    public io.provenance.metadata.v1.MsgDeleteRecordSpecificationResponse deleteRecordSpecification(io.provenance.metadata.v1.MsgDeleteRecordSpecificationRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteRecordSpecificationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * WriteP8eContractSpec adds a P8e v39 contract spec as a v40 ContractSpecification
     * It only exists to help facilitate the transition. Users should transition to WriteContractSpecification.
     * </pre>
     */
    @java.lang.Deprecated
    public io.provenance.metadata.v1.MsgWriteP8eContractSpecResponse writeP8eContractSpec(io.provenance.metadata.v1.MsgWriteP8eContractSpecRequest request) {
      return blockingUnaryCall(
          getChannel(), getWriteP8eContractSpecMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * P8EMemorializeContract records the results of a P8e contract execution as a session and set of records in a scope
     * It only exists to help facilitate the transition. Users should transition to calling the individual Write methods.
     * </pre>
     */
    @java.lang.Deprecated
    public io.provenance.metadata.v1.MsgP8eMemorializeContractResponse p8eMemorializeContract(io.provenance.metadata.v1.MsgP8eMemorializeContractRequest request) {
      return blockingUnaryCall(
          getChannel(), getP8eMemorializeContractMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BindOSLocator binds an owner address to a uri.
     * </pre>
     */
    public io.provenance.metadata.v1.MsgBindOSLocatorResponse bindOSLocator(io.provenance.metadata.v1.MsgBindOSLocatorRequest request) {
      return blockingUnaryCall(
          getChannel(), getBindOSLocatorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteOSLocator deletes an existing ObjectStoreLocator record.
     * </pre>
     */
    public io.provenance.metadata.v1.MsgDeleteOSLocatorResponse deleteOSLocator(io.provenance.metadata.v1.MsgDeleteOSLocatorRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteOSLocatorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ModifyOSLocator updates an ObjectStoreLocator record by the current owner.
     * </pre>
     */
    public io.provenance.metadata.v1.MsgModifyOSLocatorResponse modifyOSLocator(io.provenance.metadata.v1.MsgModifyOSLocatorRequest request) {
      return blockingUnaryCall(
          getChannel(), getModifyOSLocatorMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the Metadata Msg service.
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
     * WriteScope adds or updates a scope.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.MsgWriteScopeResponse> writeScope(
        io.provenance.metadata.v1.MsgWriteScopeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getWriteScopeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteScope deletes a scope and all associated Records, Sessions.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.MsgDeleteScopeResponse> deleteScope(
        io.provenance.metadata.v1.MsgDeleteScopeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteScopeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AddScopeDataAccess adds data access AccAddress to scope
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.MsgAddScopeDataAccessResponse> addScopeDataAccess(
        io.provenance.metadata.v1.MsgAddScopeDataAccessRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAddScopeDataAccessMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteScopeDataAccess removes data access AccAddress from scope
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.MsgDeleteScopeDataAccessResponse> deleteScopeDataAccess(
        io.provenance.metadata.v1.MsgDeleteScopeDataAccessRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteScopeDataAccessMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AddScopeOwner adds new owner AccAddress to scope
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.MsgAddScopeOwnerResponse> addScopeOwner(
        io.provenance.metadata.v1.MsgAddScopeOwnerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAddScopeOwnerMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteScopeOwner removes data access AccAddress from scope
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.MsgDeleteScopeOwnerResponse> deleteScopeOwner(
        io.provenance.metadata.v1.MsgDeleteScopeOwnerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteScopeOwnerMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WriteSession adds or updates a session context.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.MsgWriteSessionResponse> writeSession(
        io.provenance.metadata.v1.MsgWriteSessionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getWriteSessionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WriteRecord adds or updates a record.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.MsgWriteRecordResponse> writeRecord(
        io.provenance.metadata.v1.MsgWriteRecordRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getWriteRecordMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteRecord deletes a record.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.MsgDeleteRecordResponse> deleteRecord(
        io.provenance.metadata.v1.MsgDeleteRecordRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteRecordMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WriteScopeSpecification adds or updates a scope specification.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.MsgWriteScopeSpecificationResponse> writeScopeSpecification(
        io.provenance.metadata.v1.MsgWriteScopeSpecificationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getWriteScopeSpecificationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteScopeSpecification deletes a scope specification.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.MsgDeleteScopeSpecificationResponse> deleteScopeSpecification(
        io.provenance.metadata.v1.MsgDeleteScopeSpecificationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteScopeSpecificationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WriteContractSpecification adds or updates a contract specification.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.MsgWriteContractSpecificationResponse> writeContractSpecification(
        io.provenance.metadata.v1.MsgWriteContractSpecificationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getWriteContractSpecificationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteContractSpecification deletes a contract specification.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.MsgDeleteContractSpecificationResponse> deleteContractSpecification(
        io.provenance.metadata.v1.MsgDeleteContractSpecificationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteContractSpecificationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AddContractSpecToScopeSpec adds contract specification to a scope specification.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecResponse> addContractSpecToScopeSpec(
        io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAddContractSpecToScopeSpecMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteContractSpecFromScopeSpec deletes a contract specification from a scope specification.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecResponse> deleteContractSpecFromScopeSpec(
        io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteContractSpecFromScopeSpecMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WriteRecordSpecification adds or updates a record specification.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.MsgWriteRecordSpecificationResponse> writeRecordSpecification(
        io.provenance.metadata.v1.MsgWriteRecordSpecificationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getWriteRecordSpecificationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteRecordSpecification deletes a record specification.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.MsgDeleteRecordSpecificationResponse> deleteRecordSpecification(
        io.provenance.metadata.v1.MsgDeleteRecordSpecificationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteRecordSpecificationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * WriteP8eContractSpec adds a P8e v39 contract spec as a v40 ContractSpecification
     * It only exists to help facilitate the transition. Users should transition to WriteContractSpecification.
     * </pre>
     */
    @java.lang.Deprecated
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.MsgWriteP8eContractSpecResponse> writeP8eContractSpec(
        io.provenance.metadata.v1.MsgWriteP8eContractSpecRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getWriteP8eContractSpecMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * P8EMemorializeContract records the results of a P8e contract execution as a session and set of records in a scope
     * It only exists to help facilitate the transition. Users should transition to calling the individual Write methods.
     * </pre>
     */
    @java.lang.Deprecated
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.MsgP8eMemorializeContractResponse> p8eMemorializeContract(
        io.provenance.metadata.v1.MsgP8eMemorializeContractRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getP8eMemorializeContractMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BindOSLocator binds an owner address to a uri.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.MsgBindOSLocatorResponse> bindOSLocator(
        io.provenance.metadata.v1.MsgBindOSLocatorRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBindOSLocatorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteOSLocator deletes an existing ObjectStoreLocator record.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.MsgDeleteOSLocatorResponse> deleteOSLocator(
        io.provenance.metadata.v1.MsgDeleteOSLocatorRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteOSLocatorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ModifyOSLocator updates an ObjectStoreLocator record by the current owner.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.provenance.metadata.v1.MsgModifyOSLocatorResponse> modifyOSLocator(
        io.provenance.metadata.v1.MsgModifyOSLocatorRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getModifyOSLocatorMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_WRITE_SCOPE = 0;
  private static final int METHODID_DELETE_SCOPE = 1;
  private static final int METHODID_ADD_SCOPE_DATA_ACCESS = 2;
  private static final int METHODID_DELETE_SCOPE_DATA_ACCESS = 3;
  private static final int METHODID_ADD_SCOPE_OWNER = 4;
  private static final int METHODID_DELETE_SCOPE_OWNER = 5;
  private static final int METHODID_WRITE_SESSION = 6;
  private static final int METHODID_WRITE_RECORD = 7;
  private static final int METHODID_DELETE_RECORD = 8;
  private static final int METHODID_WRITE_SCOPE_SPECIFICATION = 9;
  private static final int METHODID_DELETE_SCOPE_SPECIFICATION = 10;
  private static final int METHODID_WRITE_CONTRACT_SPECIFICATION = 11;
  private static final int METHODID_DELETE_CONTRACT_SPECIFICATION = 12;
  private static final int METHODID_ADD_CONTRACT_SPEC_TO_SCOPE_SPEC = 13;
  private static final int METHODID_DELETE_CONTRACT_SPEC_FROM_SCOPE_SPEC = 14;
  private static final int METHODID_WRITE_RECORD_SPECIFICATION = 15;
  private static final int METHODID_DELETE_RECORD_SPECIFICATION = 16;
  private static final int METHODID_WRITE_P8E_CONTRACT_SPEC = 17;
  private static final int METHODID_P8E_MEMORIALIZE_CONTRACT = 18;
  private static final int METHODID_BIND_OSLOCATOR = 19;
  private static final int METHODID_DELETE_OSLOCATOR = 20;
  private static final int METHODID_MODIFY_OSLOCATOR = 21;

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
        case METHODID_WRITE_SCOPE:
          serviceImpl.writeScope((io.provenance.metadata.v1.MsgWriteScopeRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgWriteScopeResponse>) responseObserver);
          break;
        case METHODID_DELETE_SCOPE:
          serviceImpl.deleteScope((io.provenance.metadata.v1.MsgDeleteScopeRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteScopeResponse>) responseObserver);
          break;
        case METHODID_ADD_SCOPE_DATA_ACCESS:
          serviceImpl.addScopeDataAccess((io.provenance.metadata.v1.MsgAddScopeDataAccessRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgAddScopeDataAccessResponse>) responseObserver);
          break;
        case METHODID_DELETE_SCOPE_DATA_ACCESS:
          serviceImpl.deleteScopeDataAccess((io.provenance.metadata.v1.MsgDeleteScopeDataAccessRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteScopeDataAccessResponse>) responseObserver);
          break;
        case METHODID_ADD_SCOPE_OWNER:
          serviceImpl.addScopeOwner((io.provenance.metadata.v1.MsgAddScopeOwnerRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgAddScopeOwnerResponse>) responseObserver);
          break;
        case METHODID_DELETE_SCOPE_OWNER:
          serviceImpl.deleteScopeOwner((io.provenance.metadata.v1.MsgDeleteScopeOwnerRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteScopeOwnerResponse>) responseObserver);
          break;
        case METHODID_WRITE_SESSION:
          serviceImpl.writeSession((io.provenance.metadata.v1.MsgWriteSessionRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgWriteSessionResponse>) responseObserver);
          break;
        case METHODID_WRITE_RECORD:
          serviceImpl.writeRecord((io.provenance.metadata.v1.MsgWriteRecordRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgWriteRecordResponse>) responseObserver);
          break;
        case METHODID_DELETE_RECORD:
          serviceImpl.deleteRecord((io.provenance.metadata.v1.MsgDeleteRecordRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteRecordResponse>) responseObserver);
          break;
        case METHODID_WRITE_SCOPE_SPECIFICATION:
          serviceImpl.writeScopeSpecification((io.provenance.metadata.v1.MsgWriteScopeSpecificationRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgWriteScopeSpecificationResponse>) responseObserver);
          break;
        case METHODID_DELETE_SCOPE_SPECIFICATION:
          serviceImpl.deleteScopeSpecification((io.provenance.metadata.v1.MsgDeleteScopeSpecificationRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteScopeSpecificationResponse>) responseObserver);
          break;
        case METHODID_WRITE_CONTRACT_SPECIFICATION:
          serviceImpl.writeContractSpecification((io.provenance.metadata.v1.MsgWriteContractSpecificationRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgWriteContractSpecificationResponse>) responseObserver);
          break;
        case METHODID_DELETE_CONTRACT_SPECIFICATION:
          serviceImpl.deleteContractSpecification((io.provenance.metadata.v1.MsgDeleteContractSpecificationRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteContractSpecificationResponse>) responseObserver);
          break;
        case METHODID_ADD_CONTRACT_SPEC_TO_SCOPE_SPEC:
          serviceImpl.addContractSpecToScopeSpec((io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgAddContractSpecToScopeSpecResponse>) responseObserver);
          break;
        case METHODID_DELETE_CONTRACT_SPEC_FROM_SCOPE_SPEC:
          serviceImpl.deleteContractSpecFromScopeSpec((io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteContractSpecFromScopeSpecResponse>) responseObserver);
          break;
        case METHODID_WRITE_RECORD_SPECIFICATION:
          serviceImpl.writeRecordSpecification((io.provenance.metadata.v1.MsgWriteRecordSpecificationRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgWriteRecordSpecificationResponse>) responseObserver);
          break;
        case METHODID_DELETE_RECORD_SPECIFICATION:
          serviceImpl.deleteRecordSpecification((io.provenance.metadata.v1.MsgDeleteRecordSpecificationRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteRecordSpecificationResponse>) responseObserver);
          break;
        case METHODID_WRITE_P8E_CONTRACT_SPEC:
          serviceImpl.writeP8eContractSpec((io.provenance.metadata.v1.MsgWriteP8eContractSpecRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgWriteP8eContractSpecResponse>) responseObserver);
          break;
        case METHODID_P8E_MEMORIALIZE_CONTRACT:
          serviceImpl.p8eMemorializeContract((io.provenance.metadata.v1.MsgP8eMemorializeContractRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgP8eMemorializeContractResponse>) responseObserver);
          break;
        case METHODID_BIND_OSLOCATOR:
          serviceImpl.bindOSLocator((io.provenance.metadata.v1.MsgBindOSLocatorRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgBindOSLocatorResponse>) responseObserver);
          break;
        case METHODID_DELETE_OSLOCATOR:
          serviceImpl.deleteOSLocator((io.provenance.metadata.v1.MsgDeleteOSLocatorRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgDeleteOSLocatorResponse>) responseObserver);
          break;
        case METHODID_MODIFY_OSLOCATOR:
          serviceImpl.modifyOSLocator((io.provenance.metadata.v1.MsgModifyOSLocatorRequest) request,
              (io.grpc.stub.StreamObserver<io.provenance.metadata.v1.MsgModifyOSLocatorResponse>) responseObserver);
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
      return io.provenance.metadata.v1.Tx.getDescriptor();
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
              .addMethod(getWriteScopeMethod())
              .addMethod(getDeleteScopeMethod())
              .addMethod(getAddScopeDataAccessMethod())
              .addMethod(getDeleteScopeDataAccessMethod())
              .addMethod(getAddScopeOwnerMethod())
              .addMethod(getDeleteScopeOwnerMethod())
              .addMethod(getWriteSessionMethod())
              .addMethod(getWriteRecordMethod())
              .addMethod(getDeleteRecordMethod())
              .addMethod(getWriteScopeSpecificationMethod())
              .addMethod(getDeleteScopeSpecificationMethod())
              .addMethod(getWriteContractSpecificationMethod())
              .addMethod(getDeleteContractSpecificationMethod())
              .addMethod(getAddContractSpecToScopeSpecMethod())
              .addMethod(getDeleteContractSpecFromScopeSpecMethod())
              .addMethod(getWriteRecordSpecificationMethod())
              .addMethod(getDeleteRecordSpecificationMethod())
              .addMethod(getWriteP8eContractSpecMethod())
              .addMethod(getP8eMemorializeContractMethod())
              .addMethod(getBindOSLocatorMethod())
              .addMethod(getDeleteOSLocatorMethod())
              .addMethod(getModifyOSLocatorMethod())
              .build();
        }
      }
    }
    return result;
  }
}
