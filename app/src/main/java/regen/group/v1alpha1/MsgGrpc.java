package regen.group.v1alpha1;

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
 * Msg is the regen.group.v1alpha1 Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: regen/group/v1alpha1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "regen.group.v1alpha1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgCreateGroup,
      regen.group.v1alpha1.Tx.MsgCreateGroupResponse> getCreateGroupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateGroup",
      requestType = regen.group.v1alpha1.Tx.MsgCreateGroup.class,
      responseType = regen.group.v1alpha1.Tx.MsgCreateGroupResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgCreateGroup,
      regen.group.v1alpha1.Tx.MsgCreateGroupResponse> getCreateGroupMethod() {
    io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgCreateGroup, regen.group.v1alpha1.Tx.MsgCreateGroupResponse> getCreateGroupMethod;
    if ((getCreateGroupMethod = MsgGrpc.getCreateGroupMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateGroupMethod = MsgGrpc.getCreateGroupMethod) == null) {
          MsgGrpc.getCreateGroupMethod = getCreateGroupMethod =
              io.grpc.MethodDescriptor.<regen.group.v1alpha1.Tx.MsgCreateGroup, regen.group.v1alpha1.Tx.MsgCreateGroupResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateGroup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.group.v1alpha1.Tx.MsgCreateGroup.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.group.v1alpha1.Tx.MsgCreateGroupResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateGroup"))
              .build();
        }
      }
    }
    return getCreateGroupMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgUpdateGroupMembers,
      regen.group.v1alpha1.Tx.MsgUpdateGroupMembersResponse> getUpdateGroupMembersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateGroupMembers",
      requestType = regen.group.v1alpha1.Tx.MsgUpdateGroupMembers.class,
      responseType = regen.group.v1alpha1.Tx.MsgUpdateGroupMembersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgUpdateGroupMembers,
      regen.group.v1alpha1.Tx.MsgUpdateGroupMembersResponse> getUpdateGroupMembersMethod() {
    io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgUpdateGroupMembers, regen.group.v1alpha1.Tx.MsgUpdateGroupMembersResponse> getUpdateGroupMembersMethod;
    if ((getUpdateGroupMembersMethod = MsgGrpc.getUpdateGroupMembersMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateGroupMembersMethod = MsgGrpc.getUpdateGroupMembersMethod) == null) {
          MsgGrpc.getUpdateGroupMembersMethod = getUpdateGroupMembersMethod =
              io.grpc.MethodDescriptor.<regen.group.v1alpha1.Tx.MsgUpdateGroupMembers, regen.group.v1alpha1.Tx.MsgUpdateGroupMembersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateGroupMembers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.group.v1alpha1.Tx.MsgUpdateGroupMembers.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.group.v1alpha1.Tx.MsgUpdateGroupMembersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateGroupMembers"))
              .build();
        }
      }
    }
    return getUpdateGroupMembersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgUpdateGroupAdmin,
      regen.group.v1alpha1.Tx.MsgUpdateGroupAdminResponse> getUpdateGroupAdminMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateGroupAdmin",
      requestType = regen.group.v1alpha1.Tx.MsgUpdateGroupAdmin.class,
      responseType = regen.group.v1alpha1.Tx.MsgUpdateGroupAdminResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgUpdateGroupAdmin,
      regen.group.v1alpha1.Tx.MsgUpdateGroupAdminResponse> getUpdateGroupAdminMethod() {
    io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgUpdateGroupAdmin, regen.group.v1alpha1.Tx.MsgUpdateGroupAdminResponse> getUpdateGroupAdminMethod;
    if ((getUpdateGroupAdminMethod = MsgGrpc.getUpdateGroupAdminMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateGroupAdminMethod = MsgGrpc.getUpdateGroupAdminMethod) == null) {
          MsgGrpc.getUpdateGroupAdminMethod = getUpdateGroupAdminMethod =
              io.grpc.MethodDescriptor.<regen.group.v1alpha1.Tx.MsgUpdateGroupAdmin, regen.group.v1alpha1.Tx.MsgUpdateGroupAdminResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateGroupAdmin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.group.v1alpha1.Tx.MsgUpdateGroupAdmin.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.group.v1alpha1.Tx.MsgUpdateGroupAdminResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateGroupAdmin"))
              .build();
        }
      }
    }
    return getUpdateGroupAdminMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgUpdateGroupMetadata,
      regen.group.v1alpha1.Tx.MsgUpdateGroupMetadataResponse> getUpdateGroupMetadataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateGroupMetadata",
      requestType = regen.group.v1alpha1.Tx.MsgUpdateGroupMetadata.class,
      responseType = regen.group.v1alpha1.Tx.MsgUpdateGroupMetadataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgUpdateGroupMetadata,
      regen.group.v1alpha1.Tx.MsgUpdateGroupMetadataResponse> getUpdateGroupMetadataMethod() {
    io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgUpdateGroupMetadata, regen.group.v1alpha1.Tx.MsgUpdateGroupMetadataResponse> getUpdateGroupMetadataMethod;
    if ((getUpdateGroupMetadataMethod = MsgGrpc.getUpdateGroupMetadataMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateGroupMetadataMethod = MsgGrpc.getUpdateGroupMetadataMethod) == null) {
          MsgGrpc.getUpdateGroupMetadataMethod = getUpdateGroupMetadataMethod =
              io.grpc.MethodDescriptor.<regen.group.v1alpha1.Tx.MsgUpdateGroupMetadata, regen.group.v1alpha1.Tx.MsgUpdateGroupMetadataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateGroupMetadata"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.group.v1alpha1.Tx.MsgUpdateGroupMetadata.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.group.v1alpha1.Tx.MsgUpdateGroupMetadataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateGroupMetadata"))
              .build();
        }
      }
    }
    return getUpdateGroupMetadataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgCreateGroupAccount,
      regen.group.v1alpha1.Tx.MsgCreateGroupAccountResponse> getCreateGroupAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateGroupAccount",
      requestType = regen.group.v1alpha1.Tx.MsgCreateGroupAccount.class,
      responseType = regen.group.v1alpha1.Tx.MsgCreateGroupAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgCreateGroupAccount,
      regen.group.v1alpha1.Tx.MsgCreateGroupAccountResponse> getCreateGroupAccountMethod() {
    io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgCreateGroupAccount, regen.group.v1alpha1.Tx.MsgCreateGroupAccountResponse> getCreateGroupAccountMethod;
    if ((getCreateGroupAccountMethod = MsgGrpc.getCreateGroupAccountMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateGroupAccountMethod = MsgGrpc.getCreateGroupAccountMethod) == null) {
          MsgGrpc.getCreateGroupAccountMethod = getCreateGroupAccountMethod =
              io.grpc.MethodDescriptor.<regen.group.v1alpha1.Tx.MsgCreateGroupAccount, regen.group.v1alpha1.Tx.MsgCreateGroupAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateGroupAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.group.v1alpha1.Tx.MsgCreateGroupAccount.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.group.v1alpha1.Tx.MsgCreateGroupAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateGroupAccount"))
              .build();
        }
      }
    }
    return getCreateGroupAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdmin,
      regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdminResponse> getUpdateGroupAccountAdminMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateGroupAccountAdmin",
      requestType = regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdmin.class,
      responseType = regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdminResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdmin,
      regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdminResponse> getUpdateGroupAccountAdminMethod() {
    io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdmin, regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdminResponse> getUpdateGroupAccountAdminMethod;
    if ((getUpdateGroupAccountAdminMethod = MsgGrpc.getUpdateGroupAccountAdminMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateGroupAccountAdminMethod = MsgGrpc.getUpdateGroupAccountAdminMethod) == null) {
          MsgGrpc.getUpdateGroupAccountAdminMethod = getUpdateGroupAccountAdminMethod =
              io.grpc.MethodDescriptor.<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdmin, regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdminResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateGroupAccountAdmin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdmin.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdminResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateGroupAccountAdmin"))
              .build();
        }
      }
    }
    return getUpdateGroupAccountAdminMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicy,
      regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicyResponse> getUpdateGroupAccountDecisionPolicyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateGroupAccountDecisionPolicy",
      requestType = regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicy.class,
      responseType = regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicy,
      regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicyResponse> getUpdateGroupAccountDecisionPolicyMethod() {
    io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicy, regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicyResponse> getUpdateGroupAccountDecisionPolicyMethod;
    if ((getUpdateGroupAccountDecisionPolicyMethod = MsgGrpc.getUpdateGroupAccountDecisionPolicyMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateGroupAccountDecisionPolicyMethod = MsgGrpc.getUpdateGroupAccountDecisionPolicyMethod) == null) {
          MsgGrpc.getUpdateGroupAccountDecisionPolicyMethod = getUpdateGroupAccountDecisionPolicyMethod =
              io.grpc.MethodDescriptor.<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicy, regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateGroupAccountDecisionPolicy"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicy.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateGroupAccountDecisionPolicy"))
              .build();
        }
      }
    }
    return getUpdateGroupAccountDecisionPolicyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadata,
      regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadataResponse> getUpdateGroupAccountMetadataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateGroupAccountMetadata",
      requestType = regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadata.class,
      responseType = regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadata,
      regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadataResponse> getUpdateGroupAccountMetadataMethod() {
    io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadata, regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadataResponse> getUpdateGroupAccountMetadataMethod;
    if ((getUpdateGroupAccountMetadataMethod = MsgGrpc.getUpdateGroupAccountMetadataMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateGroupAccountMetadataMethod = MsgGrpc.getUpdateGroupAccountMetadataMethod) == null) {
          MsgGrpc.getUpdateGroupAccountMetadataMethod = getUpdateGroupAccountMetadataMethod =
              io.grpc.MethodDescriptor.<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadata, regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateGroupAccountMetadata"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadata.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateGroupAccountMetadata"))
              .build();
        }
      }
    }
    return getUpdateGroupAccountMetadataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgCreateProposal,
      regen.group.v1alpha1.Tx.MsgCreateProposalResponse> getCreateProposalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateProposal",
      requestType = regen.group.v1alpha1.Tx.MsgCreateProposal.class,
      responseType = regen.group.v1alpha1.Tx.MsgCreateProposalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgCreateProposal,
      regen.group.v1alpha1.Tx.MsgCreateProposalResponse> getCreateProposalMethod() {
    io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgCreateProposal, regen.group.v1alpha1.Tx.MsgCreateProposalResponse> getCreateProposalMethod;
    if ((getCreateProposalMethod = MsgGrpc.getCreateProposalMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateProposalMethod = MsgGrpc.getCreateProposalMethod) == null) {
          MsgGrpc.getCreateProposalMethod = getCreateProposalMethod =
              io.grpc.MethodDescriptor.<regen.group.v1alpha1.Tx.MsgCreateProposal, regen.group.v1alpha1.Tx.MsgCreateProposalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateProposal"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.group.v1alpha1.Tx.MsgCreateProposal.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.group.v1alpha1.Tx.MsgCreateProposalResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateProposal"))
              .build();
        }
      }
    }
    return getCreateProposalMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgVote,
      regen.group.v1alpha1.Tx.MsgVoteResponse> getVoteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Vote",
      requestType = regen.group.v1alpha1.Tx.MsgVote.class,
      responseType = regen.group.v1alpha1.Tx.MsgVoteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgVote,
      regen.group.v1alpha1.Tx.MsgVoteResponse> getVoteMethod() {
    io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgVote, regen.group.v1alpha1.Tx.MsgVoteResponse> getVoteMethod;
    if ((getVoteMethod = MsgGrpc.getVoteMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getVoteMethod = MsgGrpc.getVoteMethod) == null) {
          MsgGrpc.getVoteMethod = getVoteMethod =
              io.grpc.MethodDescriptor.<regen.group.v1alpha1.Tx.MsgVote, regen.group.v1alpha1.Tx.MsgVoteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Vote"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.group.v1alpha1.Tx.MsgVote.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.group.v1alpha1.Tx.MsgVoteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Vote"))
              .build();
        }
      }
    }
    return getVoteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgExec,
      regen.group.v1alpha1.Tx.MsgExecResponse> getExecMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Exec",
      requestType = regen.group.v1alpha1.Tx.MsgExec.class,
      responseType = regen.group.v1alpha1.Tx.MsgExecResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgExec,
      regen.group.v1alpha1.Tx.MsgExecResponse> getExecMethod() {
    io.grpc.MethodDescriptor<regen.group.v1alpha1.Tx.MsgExec, regen.group.v1alpha1.Tx.MsgExecResponse> getExecMethod;
    if ((getExecMethod = MsgGrpc.getExecMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getExecMethod = MsgGrpc.getExecMethod) == null) {
          MsgGrpc.getExecMethod = getExecMethod =
              io.grpc.MethodDescriptor.<regen.group.v1alpha1.Tx.MsgExec, regen.group.v1alpha1.Tx.MsgExecResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Exec"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.group.v1alpha1.Tx.MsgExec.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  regen.group.v1alpha1.Tx.MsgExecResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Exec"))
              .build();
        }
      }
    }
    return getExecMethod;
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
   * Msg is the regen.group.v1alpha1 Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CreateGroup creates a new group with an admin account address, a list of members and some optional metadata.
     * </pre>
     */
    public void createGroup(regen.group.v1alpha1.Tx.MsgCreateGroup request,
        io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgCreateGroupResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateGroupMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateGroupMembers updates the group members with given group id and admin address.
     * </pre>
     */
    public void updateGroupMembers(regen.group.v1alpha1.Tx.MsgUpdateGroupMembers request,
        io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgUpdateGroupMembersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateGroupMembersMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateGroupAdmin updates the group admin with given group id and previous admin address.
     * </pre>
     */
    public void updateGroupAdmin(regen.group.v1alpha1.Tx.MsgUpdateGroupAdmin request,
        io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgUpdateGroupAdminResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateGroupAdminMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateGroupMetadata updates the group metadata with given group id and admin address.
     * </pre>
     */
    public void updateGroupMetadata(regen.group.v1alpha1.Tx.MsgUpdateGroupMetadata request,
        io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgUpdateGroupMetadataResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateGroupMetadataMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateGroupAccount creates a new group account using given DecisionPolicy. 
     * </pre>
     */
    public void createGroupAccount(regen.group.v1alpha1.Tx.MsgCreateGroupAccount request,
        io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgCreateGroupAccountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateGroupAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateGroupAccountAdmin updates a group account admin. 
     * </pre>
     */
    public void updateGroupAccountAdmin(regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdmin request,
        io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdminResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateGroupAccountAdminMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateGroupAccountDecisionPolicy allows a group account decision policy to be updated.
     * </pre>
     */
    public void updateGroupAccountDecisionPolicy(regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicy request,
        io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateGroupAccountDecisionPolicyMethod(), responseObserver);
    }

    /**
     * <pre>
     * UpdateGroupAccountMetadata updates a group account metadata.
     * </pre>
     */
    public void updateGroupAccountMetadata(regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadata request,
        io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadataResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateGroupAccountMetadataMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateProposal submits a new proposal.
     * </pre>
     */
    public void createProposal(regen.group.v1alpha1.Tx.MsgCreateProposal request,
        io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgCreateProposalResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateProposalMethod(), responseObserver);
    }

    /**
     * <pre>
     * Vote allows a voter to vote on a proposal.
     * </pre>
     */
    public void vote(regen.group.v1alpha1.Tx.MsgVote request,
        io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgVoteResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getVoteMethod(), responseObserver);
    }

    /**
     * <pre>
     * Exec executes a proposal.
     * </pre>
     */
    public void exec(regen.group.v1alpha1.Tx.MsgExec request,
        io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgExecResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getExecMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateGroupMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.group.v1alpha1.Tx.MsgCreateGroup,
                regen.group.v1alpha1.Tx.MsgCreateGroupResponse>(
                  this, METHODID_CREATE_GROUP)))
          .addMethod(
            getUpdateGroupMembersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.group.v1alpha1.Tx.MsgUpdateGroupMembers,
                regen.group.v1alpha1.Tx.MsgUpdateGroupMembersResponse>(
                  this, METHODID_UPDATE_GROUP_MEMBERS)))
          .addMethod(
            getUpdateGroupAdminMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.group.v1alpha1.Tx.MsgUpdateGroupAdmin,
                regen.group.v1alpha1.Tx.MsgUpdateGroupAdminResponse>(
                  this, METHODID_UPDATE_GROUP_ADMIN)))
          .addMethod(
            getUpdateGroupMetadataMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.group.v1alpha1.Tx.MsgUpdateGroupMetadata,
                regen.group.v1alpha1.Tx.MsgUpdateGroupMetadataResponse>(
                  this, METHODID_UPDATE_GROUP_METADATA)))
          .addMethod(
            getCreateGroupAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.group.v1alpha1.Tx.MsgCreateGroupAccount,
                regen.group.v1alpha1.Tx.MsgCreateGroupAccountResponse>(
                  this, METHODID_CREATE_GROUP_ACCOUNT)))
          .addMethod(
            getUpdateGroupAccountAdminMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdmin,
                regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdminResponse>(
                  this, METHODID_UPDATE_GROUP_ACCOUNT_ADMIN)))
          .addMethod(
            getUpdateGroupAccountDecisionPolicyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicy,
                regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicyResponse>(
                  this, METHODID_UPDATE_GROUP_ACCOUNT_DECISION_POLICY)))
          .addMethod(
            getUpdateGroupAccountMetadataMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadata,
                regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadataResponse>(
                  this, METHODID_UPDATE_GROUP_ACCOUNT_METADATA)))
          .addMethod(
            getCreateProposalMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.group.v1alpha1.Tx.MsgCreateProposal,
                regen.group.v1alpha1.Tx.MsgCreateProposalResponse>(
                  this, METHODID_CREATE_PROPOSAL)))
          .addMethod(
            getVoteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.group.v1alpha1.Tx.MsgVote,
                regen.group.v1alpha1.Tx.MsgVoteResponse>(
                  this, METHODID_VOTE)))
          .addMethod(
            getExecMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                regen.group.v1alpha1.Tx.MsgExec,
                regen.group.v1alpha1.Tx.MsgExecResponse>(
                  this, METHODID_EXEC)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg is the regen.group.v1alpha1 Msg service.
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
     * CreateGroup creates a new group with an admin account address, a list of members and some optional metadata.
     * </pre>
     */
    public void createGroup(regen.group.v1alpha1.Tx.MsgCreateGroup request,
        io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgCreateGroupResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateGroupMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateGroupMembers updates the group members with given group id and admin address.
     * </pre>
     */
    public void updateGroupMembers(regen.group.v1alpha1.Tx.MsgUpdateGroupMembers request,
        io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgUpdateGroupMembersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateGroupMembersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateGroupAdmin updates the group admin with given group id and previous admin address.
     * </pre>
     */
    public void updateGroupAdmin(regen.group.v1alpha1.Tx.MsgUpdateGroupAdmin request,
        io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgUpdateGroupAdminResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateGroupAdminMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateGroupMetadata updates the group metadata with given group id and admin address.
     * </pre>
     */
    public void updateGroupMetadata(regen.group.v1alpha1.Tx.MsgUpdateGroupMetadata request,
        io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgUpdateGroupMetadataResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateGroupMetadataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateGroupAccount creates a new group account using given DecisionPolicy. 
     * </pre>
     */
    public void createGroupAccount(regen.group.v1alpha1.Tx.MsgCreateGroupAccount request,
        io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgCreateGroupAccountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateGroupAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateGroupAccountAdmin updates a group account admin. 
     * </pre>
     */
    public void updateGroupAccountAdmin(regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdmin request,
        io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdminResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateGroupAccountAdminMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateGroupAccountDecisionPolicy allows a group account decision policy to be updated.
     * </pre>
     */
    public void updateGroupAccountDecisionPolicy(regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicy request,
        io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateGroupAccountDecisionPolicyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UpdateGroupAccountMetadata updates a group account metadata.
     * </pre>
     */
    public void updateGroupAccountMetadata(regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadata request,
        io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadataResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateGroupAccountMetadataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateProposal submits a new proposal.
     * </pre>
     */
    public void createProposal(regen.group.v1alpha1.Tx.MsgCreateProposal request,
        io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgCreateProposalResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateProposalMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Vote allows a voter to vote on a proposal.
     * </pre>
     */
    public void vote(regen.group.v1alpha1.Tx.MsgVote request,
        io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgVoteResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVoteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Exec executes a proposal.
     * </pre>
     */
    public void exec(regen.group.v1alpha1.Tx.MsgExec request,
        io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgExecResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExecMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg is the regen.group.v1alpha1 Msg service.
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
     * CreateGroup creates a new group with an admin account address, a list of members and some optional metadata.
     * </pre>
     */
    public regen.group.v1alpha1.Tx.MsgCreateGroupResponse createGroup(regen.group.v1alpha1.Tx.MsgCreateGroup request) {
      return blockingUnaryCall(
          getChannel(), getCreateGroupMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateGroupMembers updates the group members with given group id and admin address.
     * </pre>
     */
    public regen.group.v1alpha1.Tx.MsgUpdateGroupMembersResponse updateGroupMembers(regen.group.v1alpha1.Tx.MsgUpdateGroupMembers request) {
      return blockingUnaryCall(
          getChannel(), getUpdateGroupMembersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateGroupAdmin updates the group admin with given group id and previous admin address.
     * </pre>
     */
    public regen.group.v1alpha1.Tx.MsgUpdateGroupAdminResponse updateGroupAdmin(regen.group.v1alpha1.Tx.MsgUpdateGroupAdmin request) {
      return blockingUnaryCall(
          getChannel(), getUpdateGroupAdminMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateGroupMetadata updates the group metadata with given group id and admin address.
     * </pre>
     */
    public regen.group.v1alpha1.Tx.MsgUpdateGroupMetadataResponse updateGroupMetadata(regen.group.v1alpha1.Tx.MsgUpdateGroupMetadata request) {
      return blockingUnaryCall(
          getChannel(), getUpdateGroupMetadataMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateGroupAccount creates a new group account using given DecisionPolicy. 
     * </pre>
     */
    public regen.group.v1alpha1.Tx.MsgCreateGroupAccountResponse createGroupAccount(regen.group.v1alpha1.Tx.MsgCreateGroupAccount request) {
      return blockingUnaryCall(
          getChannel(), getCreateGroupAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateGroupAccountAdmin updates a group account admin. 
     * </pre>
     */
    public regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdminResponse updateGroupAccountAdmin(regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdmin request) {
      return blockingUnaryCall(
          getChannel(), getUpdateGroupAccountAdminMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateGroupAccountDecisionPolicy allows a group account decision policy to be updated.
     * </pre>
     */
    public regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicyResponse updateGroupAccountDecisionPolicy(regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicy request) {
      return blockingUnaryCall(
          getChannel(), getUpdateGroupAccountDecisionPolicyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UpdateGroupAccountMetadata updates a group account metadata.
     * </pre>
     */
    public regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadataResponse updateGroupAccountMetadata(regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadata request) {
      return blockingUnaryCall(
          getChannel(), getUpdateGroupAccountMetadataMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateProposal submits a new proposal.
     * </pre>
     */
    public regen.group.v1alpha1.Tx.MsgCreateProposalResponse createProposal(regen.group.v1alpha1.Tx.MsgCreateProposal request) {
      return blockingUnaryCall(
          getChannel(), getCreateProposalMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Vote allows a voter to vote on a proposal.
     * </pre>
     */
    public regen.group.v1alpha1.Tx.MsgVoteResponse vote(regen.group.v1alpha1.Tx.MsgVote request) {
      return blockingUnaryCall(
          getChannel(), getVoteMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Exec executes a proposal.
     * </pre>
     */
    public regen.group.v1alpha1.Tx.MsgExecResponse exec(regen.group.v1alpha1.Tx.MsgExec request) {
      return blockingUnaryCall(
          getChannel(), getExecMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg is the regen.group.v1alpha1 Msg service.
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
     * CreateGroup creates a new group with an admin account address, a list of members and some optional metadata.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.group.v1alpha1.Tx.MsgCreateGroupResponse> createGroup(
        regen.group.v1alpha1.Tx.MsgCreateGroup request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateGroupMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateGroupMembers updates the group members with given group id and admin address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.group.v1alpha1.Tx.MsgUpdateGroupMembersResponse> updateGroupMembers(
        regen.group.v1alpha1.Tx.MsgUpdateGroupMembers request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateGroupMembersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateGroupAdmin updates the group admin with given group id and previous admin address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.group.v1alpha1.Tx.MsgUpdateGroupAdminResponse> updateGroupAdmin(
        regen.group.v1alpha1.Tx.MsgUpdateGroupAdmin request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateGroupAdminMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateGroupMetadata updates the group metadata with given group id and admin address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.group.v1alpha1.Tx.MsgUpdateGroupMetadataResponse> updateGroupMetadata(
        regen.group.v1alpha1.Tx.MsgUpdateGroupMetadata request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateGroupMetadataMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateGroupAccount creates a new group account using given DecisionPolicy. 
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.group.v1alpha1.Tx.MsgCreateGroupAccountResponse> createGroupAccount(
        regen.group.v1alpha1.Tx.MsgCreateGroupAccount request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateGroupAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateGroupAccountAdmin updates a group account admin. 
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdminResponse> updateGroupAccountAdmin(
        regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdmin request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateGroupAccountAdminMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateGroupAccountDecisionPolicy allows a group account decision policy to be updated.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicyResponse> updateGroupAccountDecisionPolicy(
        regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicy request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateGroupAccountDecisionPolicyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UpdateGroupAccountMetadata updates a group account metadata.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadataResponse> updateGroupAccountMetadata(
        regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadata request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateGroupAccountMetadataMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateProposal submits a new proposal.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.group.v1alpha1.Tx.MsgCreateProposalResponse> createProposal(
        regen.group.v1alpha1.Tx.MsgCreateProposal request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateProposalMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Vote allows a voter to vote on a proposal.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.group.v1alpha1.Tx.MsgVoteResponse> vote(
        regen.group.v1alpha1.Tx.MsgVote request) {
      return futureUnaryCall(
          getChannel().newCall(getVoteMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Exec executes a proposal.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<regen.group.v1alpha1.Tx.MsgExecResponse> exec(
        regen.group.v1alpha1.Tx.MsgExec request) {
      return futureUnaryCall(
          getChannel().newCall(getExecMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_GROUP = 0;
  private static final int METHODID_UPDATE_GROUP_MEMBERS = 1;
  private static final int METHODID_UPDATE_GROUP_ADMIN = 2;
  private static final int METHODID_UPDATE_GROUP_METADATA = 3;
  private static final int METHODID_CREATE_GROUP_ACCOUNT = 4;
  private static final int METHODID_UPDATE_GROUP_ACCOUNT_ADMIN = 5;
  private static final int METHODID_UPDATE_GROUP_ACCOUNT_DECISION_POLICY = 6;
  private static final int METHODID_UPDATE_GROUP_ACCOUNT_METADATA = 7;
  private static final int METHODID_CREATE_PROPOSAL = 8;
  private static final int METHODID_VOTE = 9;
  private static final int METHODID_EXEC = 10;

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
        case METHODID_CREATE_GROUP:
          serviceImpl.createGroup((regen.group.v1alpha1.Tx.MsgCreateGroup) request,
              (io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgCreateGroupResponse>) responseObserver);
          break;
        case METHODID_UPDATE_GROUP_MEMBERS:
          serviceImpl.updateGroupMembers((regen.group.v1alpha1.Tx.MsgUpdateGroupMembers) request,
              (io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgUpdateGroupMembersResponse>) responseObserver);
          break;
        case METHODID_UPDATE_GROUP_ADMIN:
          serviceImpl.updateGroupAdmin((regen.group.v1alpha1.Tx.MsgUpdateGroupAdmin) request,
              (io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgUpdateGroupAdminResponse>) responseObserver);
          break;
        case METHODID_UPDATE_GROUP_METADATA:
          serviceImpl.updateGroupMetadata((regen.group.v1alpha1.Tx.MsgUpdateGroupMetadata) request,
              (io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgUpdateGroupMetadataResponse>) responseObserver);
          break;
        case METHODID_CREATE_GROUP_ACCOUNT:
          serviceImpl.createGroupAccount((regen.group.v1alpha1.Tx.MsgCreateGroupAccount) request,
              (io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgCreateGroupAccountResponse>) responseObserver);
          break;
        case METHODID_UPDATE_GROUP_ACCOUNT_ADMIN:
          serviceImpl.updateGroupAccountAdmin((regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdmin) request,
              (io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountAdminResponse>) responseObserver);
          break;
        case METHODID_UPDATE_GROUP_ACCOUNT_DECISION_POLICY:
          serviceImpl.updateGroupAccountDecisionPolicy((regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicy) request,
              (io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountDecisionPolicyResponse>) responseObserver);
          break;
        case METHODID_UPDATE_GROUP_ACCOUNT_METADATA:
          serviceImpl.updateGroupAccountMetadata((regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadata) request,
              (io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgUpdateGroupAccountMetadataResponse>) responseObserver);
          break;
        case METHODID_CREATE_PROPOSAL:
          serviceImpl.createProposal((regen.group.v1alpha1.Tx.MsgCreateProposal) request,
              (io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgCreateProposalResponse>) responseObserver);
          break;
        case METHODID_VOTE:
          serviceImpl.vote((regen.group.v1alpha1.Tx.MsgVote) request,
              (io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgVoteResponse>) responseObserver);
          break;
        case METHODID_EXEC:
          serviceImpl.exec((regen.group.v1alpha1.Tx.MsgExec) request,
              (io.grpc.stub.StreamObserver<regen.group.v1alpha1.Tx.MsgExecResponse>) responseObserver);
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
      return regen.group.v1alpha1.Tx.getDescriptor();
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
              .addMethod(getCreateGroupMethod())
              .addMethod(getUpdateGroupMembersMethod())
              .addMethod(getUpdateGroupAdminMethod())
              .addMethod(getUpdateGroupMetadataMethod())
              .addMethod(getCreateGroupAccountMethod())
              .addMethod(getUpdateGroupAccountAdminMethod())
              .addMethod(getUpdateGroupAccountDecisionPolicyMethod())
              .addMethod(getUpdateGroupAccountMetadataMethod())
              .addMethod(getCreateProposalMethod())
              .addMethod(getVoteMethod())
              .addMethod(getExecMethod())
              .build();
        }
      }
    }
    return result;
  }
}
