package com.tendermint.abci;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: tendermint/abci/types.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ABCIGrpc {

  private ABCIGrpc() {}

  public static final String SERVICE_NAME = "tendermint.abci.ABCI";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestEcho,
      com.tendermint.abci.TypesProto.ResponseEcho> getEchoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Echo",
      requestType = com.tendermint.abci.TypesProto.RequestEcho.class,
      responseType = com.tendermint.abci.TypesProto.ResponseEcho.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestEcho,
      com.tendermint.abci.TypesProto.ResponseEcho> getEchoMethod() {
    io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestEcho, com.tendermint.abci.TypesProto.ResponseEcho> getEchoMethod;
    if ((getEchoMethod = ABCIGrpc.getEchoMethod) == null) {
      synchronized (ABCIGrpc.class) {
        if ((getEchoMethod = ABCIGrpc.getEchoMethod) == null) {
          ABCIGrpc.getEchoMethod = getEchoMethod =
              io.grpc.MethodDescriptor.<com.tendermint.abci.TypesProto.RequestEcho, com.tendermint.abci.TypesProto.ResponseEcho>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Echo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.RequestEcho.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.ResponseEcho.getDefaultInstance()))
              .setSchemaDescriptor(new ABCIMethodDescriptorSupplier("Echo"))
              .build();
        }
      }
    }
    return getEchoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestFlush,
      com.tendermint.abci.TypesProto.ResponseFlush> getFlushMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Flush",
      requestType = com.tendermint.abci.TypesProto.RequestFlush.class,
      responseType = com.tendermint.abci.TypesProto.ResponseFlush.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestFlush,
      com.tendermint.abci.TypesProto.ResponseFlush> getFlushMethod() {
    io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestFlush, com.tendermint.abci.TypesProto.ResponseFlush> getFlushMethod;
    if ((getFlushMethod = ABCIGrpc.getFlushMethod) == null) {
      synchronized (ABCIGrpc.class) {
        if ((getFlushMethod = ABCIGrpc.getFlushMethod) == null) {
          ABCIGrpc.getFlushMethod = getFlushMethod =
              io.grpc.MethodDescriptor.<com.tendermint.abci.TypesProto.RequestFlush, com.tendermint.abci.TypesProto.ResponseFlush>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Flush"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.RequestFlush.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.ResponseFlush.getDefaultInstance()))
              .setSchemaDescriptor(new ABCIMethodDescriptorSupplier("Flush"))
              .build();
        }
      }
    }
    return getFlushMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestInfo,
      com.tendermint.abci.TypesProto.ResponseInfo> getInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Info",
      requestType = com.tendermint.abci.TypesProto.RequestInfo.class,
      responseType = com.tendermint.abci.TypesProto.ResponseInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestInfo,
      com.tendermint.abci.TypesProto.ResponseInfo> getInfoMethod() {
    io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestInfo, com.tendermint.abci.TypesProto.ResponseInfo> getInfoMethod;
    if ((getInfoMethod = ABCIGrpc.getInfoMethod) == null) {
      synchronized (ABCIGrpc.class) {
        if ((getInfoMethod = ABCIGrpc.getInfoMethod) == null) {
          ABCIGrpc.getInfoMethod = getInfoMethod =
              io.grpc.MethodDescriptor.<com.tendermint.abci.TypesProto.RequestInfo, com.tendermint.abci.TypesProto.ResponseInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Info"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.RequestInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.ResponseInfo.getDefaultInstance()))
              .setSchemaDescriptor(new ABCIMethodDescriptorSupplier("Info"))
              .build();
        }
      }
    }
    return getInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestCheckTx,
      com.tendermint.abci.TypesProto.ResponseCheckTx> getCheckTxMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckTx",
      requestType = com.tendermint.abci.TypesProto.RequestCheckTx.class,
      responseType = com.tendermint.abci.TypesProto.ResponseCheckTx.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestCheckTx,
      com.tendermint.abci.TypesProto.ResponseCheckTx> getCheckTxMethod() {
    io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestCheckTx, com.tendermint.abci.TypesProto.ResponseCheckTx> getCheckTxMethod;
    if ((getCheckTxMethod = ABCIGrpc.getCheckTxMethod) == null) {
      synchronized (ABCIGrpc.class) {
        if ((getCheckTxMethod = ABCIGrpc.getCheckTxMethod) == null) {
          ABCIGrpc.getCheckTxMethod = getCheckTxMethod =
              io.grpc.MethodDescriptor.<com.tendermint.abci.TypesProto.RequestCheckTx, com.tendermint.abci.TypesProto.ResponseCheckTx>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckTx"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.RequestCheckTx.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.ResponseCheckTx.getDefaultInstance()))
              .setSchemaDescriptor(new ABCIMethodDescriptorSupplier("CheckTx"))
              .build();
        }
      }
    }
    return getCheckTxMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestQuery,
      com.tendermint.abci.TypesProto.ResponseQuery> getQueryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Query",
      requestType = com.tendermint.abci.TypesProto.RequestQuery.class,
      responseType = com.tendermint.abci.TypesProto.ResponseQuery.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestQuery,
      com.tendermint.abci.TypesProto.ResponseQuery> getQueryMethod() {
    io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestQuery, com.tendermint.abci.TypesProto.ResponseQuery> getQueryMethod;
    if ((getQueryMethod = ABCIGrpc.getQueryMethod) == null) {
      synchronized (ABCIGrpc.class) {
        if ((getQueryMethod = ABCIGrpc.getQueryMethod) == null) {
          ABCIGrpc.getQueryMethod = getQueryMethod =
              io.grpc.MethodDescriptor.<com.tendermint.abci.TypesProto.RequestQuery, com.tendermint.abci.TypesProto.ResponseQuery>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Query"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.RequestQuery.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.ResponseQuery.getDefaultInstance()))
              .setSchemaDescriptor(new ABCIMethodDescriptorSupplier("Query"))
              .build();
        }
      }
    }
    return getQueryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestCommit,
      com.tendermint.abci.TypesProto.ResponseCommit> getCommitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Commit",
      requestType = com.tendermint.abci.TypesProto.RequestCommit.class,
      responseType = com.tendermint.abci.TypesProto.ResponseCommit.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestCommit,
      com.tendermint.abci.TypesProto.ResponseCommit> getCommitMethod() {
    io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestCommit, com.tendermint.abci.TypesProto.ResponseCommit> getCommitMethod;
    if ((getCommitMethod = ABCIGrpc.getCommitMethod) == null) {
      synchronized (ABCIGrpc.class) {
        if ((getCommitMethod = ABCIGrpc.getCommitMethod) == null) {
          ABCIGrpc.getCommitMethod = getCommitMethod =
              io.grpc.MethodDescriptor.<com.tendermint.abci.TypesProto.RequestCommit, com.tendermint.abci.TypesProto.ResponseCommit>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Commit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.RequestCommit.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.ResponseCommit.getDefaultInstance()))
              .setSchemaDescriptor(new ABCIMethodDescriptorSupplier("Commit"))
              .build();
        }
      }
    }
    return getCommitMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestInitChain,
      com.tendermint.abci.TypesProto.ResponseInitChain> getInitChainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InitChain",
      requestType = com.tendermint.abci.TypesProto.RequestInitChain.class,
      responseType = com.tendermint.abci.TypesProto.ResponseInitChain.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestInitChain,
      com.tendermint.abci.TypesProto.ResponseInitChain> getInitChainMethod() {
    io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestInitChain, com.tendermint.abci.TypesProto.ResponseInitChain> getInitChainMethod;
    if ((getInitChainMethod = ABCIGrpc.getInitChainMethod) == null) {
      synchronized (ABCIGrpc.class) {
        if ((getInitChainMethod = ABCIGrpc.getInitChainMethod) == null) {
          ABCIGrpc.getInitChainMethod = getInitChainMethod =
              io.grpc.MethodDescriptor.<com.tendermint.abci.TypesProto.RequestInitChain, com.tendermint.abci.TypesProto.ResponseInitChain>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InitChain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.RequestInitChain.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.ResponseInitChain.getDefaultInstance()))
              .setSchemaDescriptor(new ABCIMethodDescriptorSupplier("InitChain"))
              .build();
        }
      }
    }
    return getInitChainMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestListSnapshots,
      com.tendermint.abci.TypesProto.ResponseListSnapshots> getListSnapshotsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListSnapshots",
      requestType = com.tendermint.abci.TypesProto.RequestListSnapshots.class,
      responseType = com.tendermint.abci.TypesProto.ResponseListSnapshots.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestListSnapshots,
      com.tendermint.abci.TypesProto.ResponseListSnapshots> getListSnapshotsMethod() {
    io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestListSnapshots, com.tendermint.abci.TypesProto.ResponseListSnapshots> getListSnapshotsMethod;
    if ((getListSnapshotsMethod = ABCIGrpc.getListSnapshotsMethod) == null) {
      synchronized (ABCIGrpc.class) {
        if ((getListSnapshotsMethod = ABCIGrpc.getListSnapshotsMethod) == null) {
          ABCIGrpc.getListSnapshotsMethod = getListSnapshotsMethod =
              io.grpc.MethodDescriptor.<com.tendermint.abci.TypesProto.RequestListSnapshots, com.tendermint.abci.TypesProto.ResponseListSnapshots>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListSnapshots"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.RequestListSnapshots.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.ResponseListSnapshots.getDefaultInstance()))
              .setSchemaDescriptor(new ABCIMethodDescriptorSupplier("ListSnapshots"))
              .build();
        }
      }
    }
    return getListSnapshotsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestOfferSnapshot,
      com.tendermint.abci.TypesProto.ResponseOfferSnapshot> getOfferSnapshotMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OfferSnapshot",
      requestType = com.tendermint.abci.TypesProto.RequestOfferSnapshot.class,
      responseType = com.tendermint.abci.TypesProto.ResponseOfferSnapshot.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestOfferSnapshot,
      com.tendermint.abci.TypesProto.ResponseOfferSnapshot> getOfferSnapshotMethod() {
    io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestOfferSnapshot, com.tendermint.abci.TypesProto.ResponseOfferSnapshot> getOfferSnapshotMethod;
    if ((getOfferSnapshotMethod = ABCIGrpc.getOfferSnapshotMethod) == null) {
      synchronized (ABCIGrpc.class) {
        if ((getOfferSnapshotMethod = ABCIGrpc.getOfferSnapshotMethod) == null) {
          ABCIGrpc.getOfferSnapshotMethod = getOfferSnapshotMethod =
              io.grpc.MethodDescriptor.<com.tendermint.abci.TypesProto.RequestOfferSnapshot, com.tendermint.abci.TypesProto.ResponseOfferSnapshot>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OfferSnapshot"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.RequestOfferSnapshot.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.ResponseOfferSnapshot.getDefaultInstance()))
              .setSchemaDescriptor(new ABCIMethodDescriptorSupplier("OfferSnapshot"))
              .build();
        }
      }
    }
    return getOfferSnapshotMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestLoadSnapshotChunk,
      com.tendermint.abci.TypesProto.ResponseLoadSnapshotChunk> getLoadSnapshotChunkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LoadSnapshotChunk",
      requestType = com.tendermint.abci.TypesProto.RequestLoadSnapshotChunk.class,
      responseType = com.tendermint.abci.TypesProto.ResponseLoadSnapshotChunk.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestLoadSnapshotChunk,
      com.tendermint.abci.TypesProto.ResponseLoadSnapshotChunk> getLoadSnapshotChunkMethod() {
    io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestLoadSnapshotChunk, com.tendermint.abci.TypesProto.ResponseLoadSnapshotChunk> getLoadSnapshotChunkMethod;
    if ((getLoadSnapshotChunkMethod = ABCIGrpc.getLoadSnapshotChunkMethod) == null) {
      synchronized (ABCIGrpc.class) {
        if ((getLoadSnapshotChunkMethod = ABCIGrpc.getLoadSnapshotChunkMethod) == null) {
          ABCIGrpc.getLoadSnapshotChunkMethod = getLoadSnapshotChunkMethod =
              io.grpc.MethodDescriptor.<com.tendermint.abci.TypesProto.RequestLoadSnapshotChunk, com.tendermint.abci.TypesProto.ResponseLoadSnapshotChunk>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LoadSnapshotChunk"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.RequestLoadSnapshotChunk.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.ResponseLoadSnapshotChunk.getDefaultInstance()))
              .setSchemaDescriptor(new ABCIMethodDescriptorSupplier("LoadSnapshotChunk"))
              .build();
        }
      }
    }
    return getLoadSnapshotChunkMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestApplySnapshotChunk,
      com.tendermint.abci.TypesProto.ResponseApplySnapshotChunk> getApplySnapshotChunkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ApplySnapshotChunk",
      requestType = com.tendermint.abci.TypesProto.RequestApplySnapshotChunk.class,
      responseType = com.tendermint.abci.TypesProto.ResponseApplySnapshotChunk.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestApplySnapshotChunk,
      com.tendermint.abci.TypesProto.ResponseApplySnapshotChunk> getApplySnapshotChunkMethod() {
    io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestApplySnapshotChunk, com.tendermint.abci.TypesProto.ResponseApplySnapshotChunk> getApplySnapshotChunkMethod;
    if ((getApplySnapshotChunkMethod = ABCIGrpc.getApplySnapshotChunkMethod) == null) {
      synchronized (ABCIGrpc.class) {
        if ((getApplySnapshotChunkMethod = ABCIGrpc.getApplySnapshotChunkMethod) == null) {
          ABCIGrpc.getApplySnapshotChunkMethod = getApplySnapshotChunkMethod =
              io.grpc.MethodDescriptor.<com.tendermint.abci.TypesProto.RequestApplySnapshotChunk, com.tendermint.abci.TypesProto.ResponseApplySnapshotChunk>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ApplySnapshotChunk"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.RequestApplySnapshotChunk.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.ResponseApplySnapshotChunk.getDefaultInstance()))
              .setSchemaDescriptor(new ABCIMethodDescriptorSupplier("ApplySnapshotChunk"))
              .build();
        }
      }
    }
    return getApplySnapshotChunkMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestPrepareProposal,
      com.tendermint.abci.TypesProto.ResponsePrepareProposal> getPrepareProposalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PrepareProposal",
      requestType = com.tendermint.abci.TypesProto.RequestPrepareProposal.class,
      responseType = com.tendermint.abci.TypesProto.ResponsePrepareProposal.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestPrepareProposal,
      com.tendermint.abci.TypesProto.ResponsePrepareProposal> getPrepareProposalMethod() {
    io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestPrepareProposal, com.tendermint.abci.TypesProto.ResponsePrepareProposal> getPrepareProposalMethod;
    if ((getPrepareProposalMethod = ABCIGrpc.getPrepareProposalMethod) == null) {
      synchronized (ABCIGrpc.class) {
        if ((getPrepareProposalMethod = ABCIGrpc.getPrepareProposalMethod) == null) {
          ABCIGrpc.getPrepareProposalMethod = getPrepareProposalMethod =
              io.grpc.MethodDescriptor.<com.tendermint.abci.TypesProto.RequestPrepareProposal, com.tendermint.abci.TypesProto.ResponsePrepareProposal>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PrepareProposal"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.RequestPrepareProposal.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.ResponsePrepareProposal.getDefaultInstance()))
              .setSchemaDescriptor(new ABCIMethodDescriptorSupplier("PrepareProposal"))
              .build();
        }
      }
    }
    return getPrepareProposalMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestProcessProposal,
      com.tendermint.abci.TypesProto.ResponseProcessProposal> getProcessProposalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProcessProposal",
      requestType = com.tendermint.abci.TypesProto.RequestProcessProposal.class,
      responseType = com.tendermint.abci.TypesProto.ResponseProcessProposal.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestProcessProposal,
      com.tendermint.abci.TypesProto.ResponseProcessProposal> getProcessProposalMethod() {
    io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestProcessProposal, com.tendermint.abci.TypesProto.ResponseProcessProposal> getProcessProposalMethod;
    if ((getProcessProposalMethod = ABCIGrpc.getProcessProposalMethod) == null) {
      synchronized (ABCIGrpc.class) {
        if ((getProcessProposalMethod = ABCIGrpc.getProcessProposalMethod) == null) {
          ABCIGrpc.getProcessProposalMethod = getProcessProposalMethod =
              io.grpc.MethodDescriptor.<com.tendermint.abci.TypesProto.RequestProcessProposal, com.tendermint.abci.TypesProto.ResponseProcessProposal>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ProcessProposal"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.RequestProcessProposal.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.ResponseProcessProposal.getDefaultInstance()))
              .setSchemaDescriptor(new ABCIMethodDescriptorSupplier("ProcessProposal"))
              .build();
        }
      }
    }
    return getProcessProposalMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestExtendVote,
      com.tendermint.abci.TypesProto.ResponseExtendVote> getExtendVoteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExtendVote",
      requestType = com.tendermint.abci.TypesProto.RequestExtendVote.class,
      responseType = com.tendermint.abci.TypesProto.ResponseExtendVote.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestExtendVote,
      com.tendermint.abci.TypesProto.ResponseExtendVote> getExtendVoteMethod() {
    io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestExtendVote, com.tendermint.abci.TypesProto.ResponseExtendVote> getExtendVoteMethod;
    if ((getExtendVoteMethod = ABCIGrpc.getExtendVoteMethod) == null) {
      synchronized (ABCIGrpc.class) {
        if ((getExtendVoteMethod = ABCIGrpc.getExtendVoteMethod) == null) {
          ABCIGrpc.getExtendVoteMethod = getExtendVoteMethod =
              io.grpc.MethodDescriptor.<com.tendermint.abci.TypesProto.RequestExtendVote, com.tendermint.abci.TypesProto.ResponseExtendVote>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExtendVote"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.RequestExtendVote.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.ResponseExtendVote.getDefaultInstance()))
              .setSchemaDescriptor(new ABCIMethodDescriptorSupplier("ExtendVote"))
              .build();
        }
      }
    }
    return getExtendVoteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestVerifyVoteExtension,
      com.tendermint.abci.TypesProto.ResponseVerifyVoteExtension> getVerifyVoteExtensionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "VerifyVoteExtension",
      requestType = com.tendermint.abci.TypesProto.RequestVerifyVoteExtension.class,
      responseType = com.tendermint.abci.TypesProto.ResponseVerifyVoteExtension.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestVerifyVoteExtension,
      com.tendermint.abci.TypesProto.ResponseVerifyVoteExtension> getVerifyVoteExtensionMethod() {
    io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestVerifyVoteExtension, com.tendermint.abci.TypesProto.ResponseVerifyVoteExtension> getVerifyVoteExtensionMethod;
    if ((getVerifyVoteExtensionMethod = ABCIGrpc.getVerifyVoteExtensionMethod) == null) {
      synchronized (ABCIGrpc.class) {
        if ((getVerifyVoteExtensionMethod = ABCIGrpc.getVerifyVoteExtensionMethod) == null) {
          ABCIGrpc.getVerifyVoteExtensionMethod = getVerifyVoteExtensionMethod =
              io.grpc.MethodDescriptor.<com.tendermint.abci.TypesProto.RequestVerifyVoteExtension, com.tendermint.abci.TypesProto.ResponseVerifyVoteExtension>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "VerifyVoteExtension"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.RequestVerifyVoteExtension.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.ResponseVerifyVoteExtension.getDefaultInstance()))
              .setSchemaDescriptor(new ABCIMethodDescriptorSupplier("VerifyVoteExtension"))
              .build();
        }
      }
    }
    return getVerifyVoteExtensionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestFinalizeBlock,
      com.tendermint.abci.TypesProto.ResponseFinalizeBlock> getFinalizeBlockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FinalizeBlock",
      requestType = com.tendermint.abci.TypesProto.RequestFinalizeBlock.class,
      responseType = com.tendermint.abci.TypesProto.ResponseFinalizeBlock.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestFinalizeBlock,
      com.tendermint.abci.TypesProto.ResponseFinalizeBlock> getFinalizeBlockMethod() {
    io.grpc.MethodDescriptor<com.tendermint.abci.TypesProto.RequestFinalizeBlock, com.tendermint.abci.TypesProto.ResponseFinalizeBlock> getFinalizeBlockMethod;
    if ((getFinalizeBlockMethod = ABCIGrpc.getFinalizeBlockMethod) == null) {
      synchronized (ABCIGrpc.class) {
        if ((getFinalizeBlockMethod = ABCIGrpc.getFinalizeBlockMethod) == null) {
          ABCIGrpc.getFinalizeBlockMethod = getFinalizeBlockMethod =
              io.grpc.MethodDescriptor.<com.tendermint.abci.TypesProto.RequestFinalizeBlock, com.tendermint.abci.TypesProto.ResponseFinalizeBlock>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FinalizeBlock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.RequestFinalizeBlock.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tendermint.abci.TypesProto.ResponseFinalizeBlock.getDefaultInstance()))
              .setSchemaDescriptor(new ABCIMethodDescriptorSupplier("FinalizeBlock"))
              .build();
        }
      }
    }
    return getFinalizeBlockMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ABCIStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ABCIStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ABCIStub>() {
        @java.lang.Override
        public ABCIStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ABCIStub(channel, callOptions);
        }
      };
    return ABCIStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ABCIBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ABCIBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ABCIBlockingStub>() {
        @java.lang.Override
        public ABCIBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ABCIBlockingStub(channel, callOptions);
        }
      };
    return ABCIBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ABCIFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ABCIFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ABCIFutureStub>() {
        @java.lang.Override
        public ABCIFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ABCIFutureStub(channel, callOptions);
        }
      };
    return ABCIFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void echo(com.tendermint.abci.TypesProto.RequestEcho request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseEcho> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEchoMethod(), responseObserver);
    }

    /**
     */
    default void flush(com.tendermint.abci.TypesProto.RequestFlush request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseFlush> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFlushMethod(), responseObserver);
    }

    /**
     */
    default void info(com.tendermint.abci.TypesProto.RequestInfo request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseInfo> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInfoMethod(), responseObserver);
    }

    /**
     */
    default void checkTx(com.tendermint.abci.TypesProto.RequestCheckTx request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseCheckTx> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckTxMethod(), responseObserver);
    }

    /**
     */
    default void query(com.tendermint.abci.TypesProto.RequestQuery request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseQuery> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQueryMethod(), responseObserver);
    }

    /**
     */
    default void commit(com.tendermint.abci.TypesProto.RequestCommit request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseCommit> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCommitMethod(), responseObserver);
    }

    /**
     */
    default void initChain(com.tendermint.abci.TypesProto.RequestInitChain request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseInitChain> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInitChainMethod(), responseObserver);
    }

    /**
     */
    default void listSnapshots(com.tendermint.abci.TypesProto.RequestListSnapshots request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseListSnapshots> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListSnapshotsMethod(), responseObserver);
    }

    /**
     */
    default void offerSnapshot(com.tendermint.abci.TypesProto.RequestOfferSnapshot request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseOfferSnapshot> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOfferSnapshotMethod(), responseObserver);
    }

    /**
     */
    default void loadSnapshotChunk(com.tendermint.abci.TypesProto.RequestLoadSnapshotChunk request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseLoadSnapshotChunk> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLoadSnapshotChunkMethod(), responseObserver);
    }

    /**
     */
    default void applySnapshotChunk(com.tendermint.abci.TypesProto.RequestApplySnapshotChunk request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseApplySnapshotChunk> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getApplySnapshotChunkMethod(), responseObserver);
    }

    /**
     */
    default void prepareProposal(com.tendermint.abci.TypesProto.RequestPrepareProposal request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponsePrepareProposal> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPrepareProposalMethod(), responseObserver);
    }

    /**
     */
    default void processProposal(com.tendermint.abci.TypesProto.RequestProcessProposal request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseProcessProposal> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getProcessProposalMethod(), responseObserver);
    }

    /**
     */
    default void extendVote(com.tendermint.abci.TypesProto.RequestExtendVote request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseExtendVote> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExtendVoteMethod(), responseObserver);
    }

    /**
     */
    default void verifyVoteExtension(com.tendermint.abci.TypesProto.RequestVerifyVoteExtension request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseVerifyVoteExtension> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getVerifyVoteExtensionMethod(), responseObserver);
    }

    /**
     */
    default void finalizeBlock(com.tendermint.abci.TypesProto.RequestFinalizeBlock request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseFinalizeBlock> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFinalizeBlockMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ABCI.
   */
  public static abstract class ABCIImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ABCIGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ABCI.
   */
  public static final class ABCIStub
      extends io.grpc.stub.AbstractAsyncStub<ABCIStub> {
    private ABCIStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ABCIStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ABCIStub(channel, callOptions);
    }

    /**
     */
    public void echo(com.tendermint.abci.TypesProto.RequestEcho request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseEcho> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEchoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void flush(com.tendermint.abci.TypesProto.RequestFlush request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseFlush> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFlushMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void info(com.tendermint.abci.TypesProto.RequestInfo request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseInfo> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkTx(com.tendermint.abci.TypesProto.RequestCheckTx request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseCheckTx> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckTxMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void query(com.tendermint.abci.TypesProto.RequestQuery request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseQuery> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQueryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void commit(com.tendermint.abci.TypesProto.RequestCommit request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseCommit> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCommitMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void initChain(com.tendermint.abci.TypesProto.RequestInitChain request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseInitChain> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInitChainMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listSnapshots(com.tendermint.abci.TypesProto.RequestListSnapshots request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseListSnapshots> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListSnapshotsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void offerSnapshot(com.tendermint.abci.TypesProto.RequestOfferSnapshot request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseOfferSnapshot> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOfferSnapshotMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void loadSnapshotChunk(com.tendermint.abci.TypesProto.RequestLoadSnapshotChunk request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseLoadSnapshotChunk> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLoadSnapshotChunkMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void applySnapshotChunk(com.tendermint.abci.TypesProto.RequestApplySnapshotChunk request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseApplySnapshotChunk> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getApplySnapshotChunkMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void prepareProposal(com.tendermint.abci.TypesProto.RequestPrepareProposal request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponsePrepareProposal> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPrepareProposalMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void processProposal(com.tendermint.abci.TypesProto.RequestProcessProposal request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseProcessProposal> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getProcessProposalMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void extendVote(com.tendermint.abci.TypesProto.RequestExtendVote request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseExtendVote> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExtendVoteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void verifyVoteExtension(com.tendermint.abci.TypesProto.RequestVerifyVoteExtension request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseVerifyVoteExtension> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getVerifyVoteExtensionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void finalizeBlock(com.tendermint.abci.TypesProto.RequestFinalizeBlock request,
        io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseFinalizeBlock> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFinalizeBlockMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ABCI.
   */
  public static final class ABCIBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ABCIBlockingStub> {
    private ABCIBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ABCIBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ABCIBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.tendermint.abci.TypesProto.ResponseEcho echo(com.tendermint.abci.TypesProto.RequestEcho request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEchoMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.tendermint.abci.TypesProto.ResponseFlush flush(com.tendermint.abci.TypesProto.RequestFlush request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFlushMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.tendermint.abci.TypesProto.ResponseInfo info(com.tendermint.abci.TypesProto.RequestInfo request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInfoMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.tendermint.abci.TypesProto.ResponseCheckTx checkTx(com.tendermint.abci.TypesProto.RequestCheckTx request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckTxMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.tendermint.abci.TypesProto.ResponseQuery query(com.tendermint.abci.TypesProto.RequestQuery request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQueryMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.tendermint.abci.TypesProto.ResponseCommit commit(com.tendermint.abci.TypesProto.RequestCommit request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCommitMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.tendermint.abci.TypesProto.ResponseInitChain initChain(com.tendermint.abci.TypesProto.RequestInitChain request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInitChainMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.tendermint.abci.TypesProto.ResponseListSnapshots listSnapshots(com.tendermint.abci.TypesProto.RequestListSnapshots request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListSnapshotsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.tendermint.abci.TypesProto.ResponseOfferSnapshot offerSnapshot(com.tendermint.abci.TypesProto.RequestOfferSnapshot request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOfferSnapshotMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.tendermint.abci.TypesProto.ResponseLoadSnapshotChunk loadSnapshotChunk(com.tendermint.abci.TypesProto.RequestLoadSnapshotChunk request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLoadSnapshotChunkMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.tendermint.abci.TypesProto.ResponseApplySnapshotChunk applySnapshotChunk(com.tendermint.abci.TypesProto.RequestApplySnapshotChunk request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getApplySnapshotChunkMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.tendermint.abci.TypesProto.ResponsePrepareProposal prepareProposal(com.tendermint.abci.TypesProto.RequestPrepareProposal request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPrepareProposalMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.tendermint.abci.TypesProto.ResponseProcessProposal processProposal(com.tendermint.abci.TypesProto.RequestProcessProposal request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getProcessProposalMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.tendermint.abci.TypesProto.ResponseExtendVote extendVote(com.tendermint.abci.TypesProto.RequestExtendVote request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExtendVoteMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.tendermint.abci.TypesProto.ResponseVerifyVoteExtension verifyVoteExtension(com.tendermint.abci.TypesProto.RequestVerifyVoteExtension request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getVerifyVoteExtensionMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.tendermint.abci.TypesProto.ResponseFinalizeBlock finalizeBlock(com.tendermint.abci.TypesProto.RequestFinalizeBlock request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFinalizeBlockMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ABCI.
   */
  public static final class ABCIFutureStub
      extends io.grpc.stub.AbstractFutureStub<ABCIFutureStub> {
    private ABCIFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ABCIFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ABCIFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tendermint.abci.TypesProto.ResponseEcho> echo(
        com.tendermint.abci.TypesProto.RequestEcho request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEchoMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tendermint.abci.TypesProto.ResponseFlush> flush(
        com.tendermint.abci.TypesProto.RequestFlush request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFlushMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tendermint.abci.TypesProto.ResponseInfo> info(
        com.tendermint.abci.TypesProto.RequestInfo request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInfoMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tendermint.abci.TypesProto.ResponseCheckTx> checkTx(
        com.tendermint.abci.TypesProto.RequestCheckTx request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckTxMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tendermint.abci.TypesProto.ResponseQuery> query(
        com.tendermint.abci.TypesProto.RequestQuery request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQueryMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tendermint.abci.TypesProto.ResponseCommit> commit(
        com.tendermint.abci.TypesProto.RequestCommit request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCommitMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tendermint.abci.TypesProto.ResponseInitChain> initChain(
        com.tendermint.abci.TypesProto.RequestInitChain request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInitChainMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tendermint.abci.TypesProto.ResponseListSnapshots> listSnapshots(
        com.tendermint.abci.TypesProto.RequestListSnapshots request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListSnapshotsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tendermint.abci.TypesProto.ResponseOfferSnapshot> offerSnapshot(
        com.tendermint.abci.TypesProto.RequestOfferSnapshot request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOfferSnapshotMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tendermint.abci.TypesProto.ResponseLoadSnapshotChunk> loadSnapshotChunk(
        com.tendermint.abci.TypesProto.RequestLoadSnapshotChunk request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLoadSnapshotChunkMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tendermint.abci.TypesProto.ResponseApplySnapshotChunk> applySnapshotChunk(
        com.tendermint.abci.TypesProto.RequestApplySnapshotChunk request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getApplySnapshotChunkMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tendermint.abci.TypesProto.ResponsePrepareProposal> prepareProposal(
        com.tendermint.abci.TypesProto.RequestPrepareProposal request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPrepareProposalMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tendermint.abci.TypesProto.ResponseProcessProposal> processProposal(
        com.tendermint.abci.TypesProto.RequestProcessProposal request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getProcessProposalMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tendermint.abci.TypesProto.ResponseExtendVote> extendVote(
        com.tendermint.abci.TypesProto.RequestExtendVote request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getExtendVoteMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tendermint.abci.TypesProto.ResponseVerifyVoteExtension> verifyVoteExtension(
        com.tendermint.abci.TypesProto.RequestVerifyVoteExtension request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getVerifyVoteExtensionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tendermint.abci.TypesProto.ResponseFinalizeBlock> finalizeBlock(
        com.tendermint.abci.TypesProto.RequestFinalizeBlock request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFinalizeBlockMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ECHO = 0;
  private static final int METHODID_FLUSH = 1;
  private static final int METHODID_INFO = 2;
  private static final int METHODID_CHECK_TX = 3;
  private static final int METHODID_QUERY = 4;
  private static final int METHODID_COMMIT = 5;
  private static final int METHODID_INIT_CHAIN = 6;
  private static final int METHODID_LIST_SNAPSHOTS = 7;
  private static final int METHODID_OFFER_SNAPSHOT = 8;
  private static final int METHODID_LOAD_SNAPSHOT_CHUNK = 9;
  private static final int METHODID_APPLY_SNAPSHOT_CHUNK = 10;
  private static final int METHODID_PREPARE_PROPOSAL = 11;
  private static final int METHODID_PROCESS_PROPOSAL = 12;
  private static final int METHODID_EXTEND_VOTE = 13;
  private static final int METHODID_VERIFY_VOTE_EXTENSION = 14;
  private static final int METHODID_FINALIZE_BLOCK = 15;

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
        case METHODID_ECHO:
          serviceImpl.echo((com.tendermint.abci.TypesProto.RequestEcho) request,
              (io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseEcho>) responseObserver);
          break;
        case METHODID_FLUSH:
          serviceImpl.flush((com.tendermint.abci.TypesProto.RequestFlush) request,
              (io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseFlush>) responseObserver);
          break;
        case METHODID_INFO:
          serviceImpl.info((com.tendermint.abci.TypesProto.RequestInfo) request,
              (io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseInfo>) responseObserver);
          break;
        case METHODID_CHECK_TX:
          serviceImpl.checkTx((com.tendermint.abci.TypesProto.RequestCheckTx) request,
              (io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseCheckTx>) responseObserver);
          break;
        case METHODID_QUERY:
          serviceImpl.query((com.tendermint.abci.TypesProto.RequestQuery) request,
              (io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseQuery>) responseObserver);
          break;
        case METHODID_COMMIT:
          serviceImpl.commit((com.tendermint.abci.TypesProto.RequestCommit) request,
              (io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseCommit>) responseObserver);
          break;
        case METHODID_INIT_CHAIN:
          serviceImpl.initChain((com.tendermint.abci.TypesProto.RequestInitChain) request,
              (io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseInitChain>) responseObserver);
          break;
        case METHODID_LIST_SNAPSHOTS:
          serviceImpl.listSnapshots((com.tendermint.abci.TypesProto.RequestListSnapshots) request,
              (io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseListSnapshots>) responseObserver);
          break;
        case METHODID_OFFER_SNAPSHOT:
          serviceImpl.offerSnapshot((com.tendermint.abci.TypesProto.RequestOfferSnapshot) request,
              (io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseOfferSnapshot>) responseObserver);
          break;
        case METHODID_LOAD_SNAPSHOT_CHUNK:
          serviceImpl.loadSnapshotChunk((com.tendermint.abci.TypesProto.RequestLoadSnapshotChunk) request,
              (io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseLoadSnapshotChunk>) responseObserver);
          break;
        case METHODID_APPLY_SNAPSHOT_CHUNK:
          serviceImpl.applySnapshotChunk((com.tendermint.abci.TypesProto.RequestApplySnapshotChunk) request,
              (io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseApplySnapshotChunk>) responseObserver);
          break;
        case METHODID_PREPARE_PROPOSAL:
          serviceImpl.prepareProposal((com.tendermint.abci.TypesProto.RequestPrepareProposal) request,
              (io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponsePrepareProposal>) responseObserver);
          break;
        case METHODID_PROCESS_PROPOSAL:
          serviceImpl.processProposal((com.tendermint.abci.TypesProto.RequestProcessProposal) request,
              (io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseProcessProposal>) responseObserver);
          break;
        case METHODID_EXTEND_VOTE:
          serviceImpl.extendVote((com.tendermint.abci.TypesProto.RequestExtendVote) request,
              (io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseExtendVote>) responseObserver);
          break;
        case METHODID_VERIFY_VOTE_EXTENSION:
          serviceImpl.verifyVoteExtension((com.tendermint.abci.TypesProto.RequestVerifyVoteExtension) request,
              (io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseVerifyVoteExtension>) responseObserver);
          break;
        case METHODID_FINALIZE_BLOCK:
          serviceImpl.finalizeBlock((com.tendermint.abci.TypesProto.RequestFinalizeBlock) request,
              (io.grpc.stub.StreamObserver<com.tendermint.abci.TypesProto.ResponseFinalizeBlock>) responseObserver);
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
          getEchoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.tendermint.abci.TypesProto.RequestEcho,
              com.tendermint.abci.TypesProto.ResponseEcho>(
                service, METHODID_ECHO)))
        .addMethod(
          getFlushMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.tendermint.abci.TypesProto.RequestFlush,
              com.tendermint.abci.TypesProto.ResponseFlush>(
                service, METHODID_FLUSH)))
        .addMethod(
          getInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.tendermint.abci.TypesProto.RequestInfo,
              com.tendermint.abci.TypesProto.ResponseInfo>(
                service, METHODID_INFO)))
        .addMethod(
          getCheckTxMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.tendermint.abci.TypesProto.RequestCheckTx,
              com.tendermint.abci.TypesProto.ResponseCheckTx>(
                service, METHODID_CHECK_TX)))
        .addMethod(
          getQueryMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.tendermint.abci.TypesProto.RequestQuery,
              com.tendermint.abci.TypesProto.ResponseQuery>(
                service, METHODID_QUERY)))
        .addMethod(
          getCommitMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.tendermint.abci.TypesProto.RequestCommit,
              com.tendermint.abci.TypesProto.ResponseCommit>(
                service, METHODID_COMMIT)))
        .addMethod(
          getInitChainMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.tendermint.abci.TypesProto.RequestInitChain,
              com.tendermint.abci.TypesProto.ResponseInitChain>(
                service, METHODID_INIT_CHAIN)))
        .addMethod(
          getListSnapshotsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.tendermint.abci.TypesProto.RequestListSnapshots,
              com.tendermint.abci.TypesProto.ResponseListSnapshots>(
                service, METHODID_LIST_SNAPSHOTS)))
        .addMethod(
          getOfferSnapshotMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.tendermint.abci.TypesProto.RequestOfferSnapshot,
              com.tendermint.abci.TypesProto.ResponseOfferSnapshot>(
                service, METHODID_OFFER_SNAPSHOT)))
        .addMethod(
          getLoadSnapshotChunkMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.tendermint.abci.TypesProto.RequestLoadSnapshotChunk,
              com.tendermint.abci.TypesProto.ResponseLoadSnapshotChunk>(
                service, METHODID_LOAD_SNAPSHOT_CHUNK)))
        .addMethod(
          getApplySnapshotChunkMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.tendermint.abci.TypesProto.RequestApplySnapshotChunk,
              com.tendermint.abci.TypesProto.ResponseApplySnapshotChunk>(
                service, METHODID_APPLY_SNAPSHOT_CHUNK)))
        .addMethod(
          getPrepareProposalMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.tendermint.abci.TypesProto.RequestPrepareProposal,
              com.tendermint.abci.TypesProto.ResponsePrepareProposal>(
                service, METHODID_PREPARE_PROPOSAL)))
        .addMethod(
          getProcessProposalMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.tendermint.abci.TypesProto.RequestProcessProposal,
              com.tendermint.abci.TypesProto.ResponseProcessProposal>(
                service, METHODID_PROCESS_PROPOSAL)))
        .addMethod(
          getExtendVoteMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.tendermint.abci.TypesProto.RequestExtendVote,
              com.tendermint.abci.TypesProto.ResponseExtendVote>(
                service, METHODID_EXTEND_VOTE)))
        .addMethod(
          getVerifyVoteExtensionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.tendermint.abci.TypesProto.RequestVerifyVoteExtension,
              com.tendermint.abci.TypesProto.ResponseVerifyVoteExtension>(
                service, METHODID_VERIFY_VOTE_EXTENSION)))
        .addMethod(
          getFinalizeBlockMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.tendermint.abci.TypesProto.RequestFinalizeBlock,
              com.tendermint.abci.TypesProto.ResponseFinalizeBlock>(
                service, METHODID_FINALIZE_BLOCK)))
        .build();
  }

  private static abstract class ABCIBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ABCIBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.tendermint.abci.TypesProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ABCI");
    }
  }

  private static final class ABCIFileDescriptorSupplier
      extends ABCIBaseDescriptorSupplier {
    ABCIFileDescriptorSupplier() {}
  }

  private static final class ABCIMethodDescriptorSupplier
      extends ABCIBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ABCIMethodDescriptorSupplier(String methodName) {
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
      synchronized (ABCIGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ABCIFileDescriptorSupplier())
              .addMethod(getEchoMethod())
              .addMethod(getFlushMethod())
              .addMethod(getInfoMethod())
              .addMethod(getCheckTxMethod())
              .addMethod(getQueryMethod())
              .addMethod(getCommitMethod())
              .addMethod(getInitChainMethod())
              .addMethod(getListSnapshotsMethod())
              .addMethod(getOfferSnapshotMethod())
              .addMethod(getLoadSnapshotChunkMethod())
              .addMethod(getApplySnapshotChunkMethod())
              .addMethod(getPrepareProposalMethod())
              .addMethod(getProcessProposalMethod())
              .addMethod(getExtendVoteMethod())
              .addMethod(getVerifyVoteExtensionMethod())
              .addMethod(getFinalizeBlockMethod())
              .build();
        }
      }
    }
    return result;
  }
}
