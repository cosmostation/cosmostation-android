package cosmos.group.v1beta1;

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
 * Query is the cosmos.group.v1beta1 Query service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: cosmos/group/v1beta1/query.proto")
public final class QueryGrpc {

  private QueryGrpc() {}

  public static final String SERVICE_NAME = "cosmos.group.v1beta1.Query";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoRequest,
      cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoResponse> getGroupInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GroupInfo",
      requestType = cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoRequest.class,
      responseType = cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoRequest,
      cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoResponse> getGroupInfoMethod() {
    io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoRequest, cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoResponse> getGroupInfoMethod;
    if ((getGroupInfoMethod = QueryGrpc.getGroupInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGroupInfoMethod = QueryGrpc.getGroupInfoMethod) == null) {
          QueryGrpc.getGroupInfoMethod = getGroupInfoMethod =
              io.grpc.MethodDescriptor.<cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoRequest, cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GroupInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GroupInfo"))
              .build();
        }
      }
    }
    return getGroupInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoRequest,
      cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoResponse> getGroupAccountInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GroupAccountInfo",
      requestType = cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoRequest.class,
      responseType = cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoRequest,
      cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoResponse> getGroupAccountInfoMethod() {
    io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoRequest, cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoResponse> getGroupAccountInfoMethod;
    if ((getGroupAccountInfoMethod = QueryGrpc.getGroupAccountInfoMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGroupAccountInfoMethod = QueryGrpc.getGroupAccountInfoMethod) == null) {
          QueryGrpc.getGroupAccountInfoMethod = getGroupAccountInfoMethod =
              io.grpc.MethodDescriptor.<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoRequest, cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GroupAccountInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GroupAccountInfo"))
              .build();
        }
      }
    }
    return getGroupAccountInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersRequest,
      cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse> getGroupMembersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GroupMembers",
      requestType = cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersRequest.class,
      responseType = cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersRequest,
      cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse> getGroupMembersMethod() {
    io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersRequest, cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse> getGroupMembersMethod;
    if ((getGroupMembersMethod = QueryGrpc.getGroupMembersMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGroupMembersMethod = QueryGrpc.getGroupMembersMethod) == null) {
          QueryGrpc.getGroupMembersMethod = getGroupMembersMethod =
              io.grpc.MethodDescriptor.<cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersRequest, cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GroupMembers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GroupMembers"))
              .build();
        }
      }
    }
    return getGroupMembersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminRequest,
      cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse> getGroupsByAdminMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GroupsByAdmin",
      requestType = cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminRequest.class,
      responseType = cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminRequest,
      cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse> getGroupsByAdminMethod() {
    io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminRequest, cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse> getGroupsByAdminMethod;
    if ((getGroupsByAdminMethod = QueryGrpc.getGroupsByAdminMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGroupsByAdminMethod = QueryGrpc.getGroupsByAdminMethod) == null) {
          QueryGrpc.getGroupsByAdminMethod = getGroupsByAdminMethod =
              io.grpc.MethodDescriptor.<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminRequest, cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GroupsByAdmin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GroupsByAdmin"))
              .build();
        }
      }
    }
    return getGroupsByAdminMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupRequest,
      cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse> getGroupAccountsByGroupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GroupAccountsByGroup",
      requestType = cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupRequest.class,
      responseType = cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupRequest,
      cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse> getGroupAccountsByGroupMethod() {
    io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupRequest, cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse> getGroupAccountsByGroupMethod;
    if ((getGroupAccountsByGroupMethod = QueryGrpc.getGroupAccountsByGroupMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGroupAccountsByGroupMethod = QueryGrpc.getGroupAccountsByGroupMethod) == null) {
          QueryGrpc.getGroupAccountsByGroupMethod = getGroupAccountsByGroupMethod =
              io.grpc.MethodDescriptor.<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupRequest, cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GroupAccountsByGroup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GroupAccountsByGroup"))
              .build();
        }
      }
    }
    return getGroupAccountsByGroupMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminRequest,
      cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse> getGroupAccountsByAdminMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GroupAccountsByAdmin",
      requestType = cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminRequest.class,
      responseType = cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminRequest,
      cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse> getGroupAccountsByAdminMethod() {
    io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminRequest, cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse> getGroupAccountsByAdminMethod;
    if ((getGroupAccountsByAdminMethod = QueryGrpc.getGroupAccountsByAdminMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getGroupAccountsByAdminMethod = QueryGrpc.getGroupAccountsByAdminMethod) == null) {
          QueryGrpc.getGroupAccountsByAdminMethod = getGroupAccountsByAdminMethod =
              io.grpc.MethodDescriptor.<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminRequest, cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GroupAccountsByAdmin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("GroupAccountsByAdmin"))
              .build();
        }
      }
    }
    return getGroupAccountsByAdminMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryProposalRequest,
      cosmos.group.v1beta1.QueryOuterClass.QueryProposalResponse> getProposalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Proposal",
      requestType = cosmos.group.v1beta1.QueryOuterClass.QueryProposalRequest.class,
      responseType = cosmos.group.v1beta1.QueryOuterClass.QueryProposalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryProposalRequest,
      cosmos.group.v1beta1.QueryOuterClass.QueryProposalResponse> getProposalMethod() {
    io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryProposalRequest, cosmos.group.v1beta1.QueryOuterClass.QueryProposalResponse> getProposalMethod;
    if ((getProposalMethod = QueryGrpc.getProposalMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProposalMethod = QueryGrpc.getProposalMethod) == null) {
          QueryGrpc.getProposalMethod = getProposalMethod =
              io.grpc.MethodDescriptor.<cosmos.group.v1beta1.QueryOuterClass.QueryProposalRequest, cosmos.group.v1beta1.QueryOuterClass.QueryProposalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Proposal"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.group.v1beta1.QueryOuterClass.QueryProposalRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.group.v1beta1.QueryOuterClass.QueryProposalResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("Proposal"))
              .build();
        }
      }
    }
    return getProposalMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountRequest,
      cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse> getProposalsByGroupAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProposalsByGroupAccount",
      requestType = cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountRequest.class,
      responseType = cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountRequest,
      cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse> getProposalsByGroupAccountMethod() {
    io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountRequest, cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse> getProposalsByGroupAccountMethod;
    if ((getProposalsByGroupAccountMethod = QueryGrpc.getProposalsByGroupAccountMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getProposalsByGroupAccountMethod = QueryGrpc.getProposalsByGroupAccountMethod) == null) {
          QueryGrpc.getProposalsByGroupAccountMethod = getProposalsByGroupAccountMethod =
              io.grpc.MethodDescriptor.<cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountRequest, cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ProposalsByGroupAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("ProposalsByGroupAccount"))
              .build();
        }
      }
    }
    return getProposalsByGroupAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterRequest,
      cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterResponse> getVoteByProposalVoterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "VoteByProposalVoter",
      requestType = cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterRequest.class,
      responseType = cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterRequest,
      cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterResponse> getVoteByProposalVoterMethod() {
    io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterRequest, cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterResponse> getVoteByProposalVoterMethod;
    if ((getVoteByProposalVoterMethod = QueryGrpc.getVoteByProposalVoterMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getVoteByProposalVoterMethod = QueryGrpc.getVoteByProposalVoterMethod) == null) {
          QueryGrpc.getVoteByProposalVoterMethod = getVoteByProposalVoterMethod =
              io.grpc.MethodDescriptor.<cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterRequest, cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "VoteByProposalVoter"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("VoteByProposalVoter"))
              .build();
        }
      }
    }
    return getVoteByProposalVoterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalRequest,
      cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse> getVotesByProposalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "VotesByProposal",
      requestType = cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalRequest.class,
      responseType = cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalRequest,
      cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse> getVotesByProposalMethod() {
    io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalRequest, cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse> getVotesByProposalMethod;
    if ((getVotesByProposalMethod = QueryGrpc.getVotesByProposalMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getVotesByProposalMethod = QueryGrpc.getVotesByProposalMethod) == null) {
          QueryGrpc.getVotesByProposalMethod = getVotesByProposalMethod =
              io.grpc.MethodDescriptor.<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalRequest, cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "VotesByProposal"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("VotesByProposal"))
              .build();
        }
      }
    }
    return getVotesByProposalMethod;
  }

  private static volatile io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterRequest,
      cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse> getVotesByVoterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "VotesByVoter",
      requestType = cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterRequest.class,
      responseType = cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterRequest,
      cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse> getVotesByVoterMethod() {
    io.grpc.MethodDescriptor<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterRequest, cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse> getVotesByVoterMethod;
    if ((getVotesByVoterMethod = QueryGrpc.getVotesByVoterMethod) == null) {
      synchronized (QueryGrpc.class) {
        if ((getVotesByVoterMethod = QueryGrpc.getVotesByVoterMethod) == null) {
          QueryGrpc.getVotesByVoterMethod = getVotesByVoterMethod =
              io.grpc.MethodDescriptor.<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterRequest, cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "VotesByVoter"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryMethodDescriptorSupplier("VotesByVoter"))
              .build();
        }
      }
    }
    return getVotesByVoterMethod;
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
   * Query is the cosmos.group.v1beta1 Query service.
   * </pre>
   */
  public static abstract class QueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * GroupInfo queries group info based on group id.
     * </pre>
     */
    public void groupInfo(cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoRequest request,
        io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGroupInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * GroupAccountInfo queries group account info based on group account address.
     * </pre>
     */
    public void groupAccountInfo(cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoRequest request,
        io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGroupAccountInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * GroupMembers queries members of a group
     * </pre>
     */
    public void groupMembers(cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersRequest request,
        io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGroupMembersMethod(), responseObserver);
    }

    /**
     * <pre>
     * GroupsByAdmin queries groups by admin address.
     * </pre>
     */
    public void groupsByAdmin(cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminRequest request,
        io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGroupsByAdminMethod(), responseObserver);
    }

    /**
     * <pre>
     * GroupAccountsByGroup queries group accounts by group id.
     * </pre>
     */
    public void groupAccountsByGroup(cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupRequest request,
        io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGroupAccountsByGroupMethod(), responseObserver);
    }

    /**
     * <pre>
     * GroupsByAdmin queries group accounts by admin address.
     * </pre>
     */
    public void groupAccountsByAdmin(cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminRequest request,
        io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGroupAccountsByAdminMethod(), responseObserver);
    }

    /**
     * <pre>
     * Proposal queries a proposal based on proposal id.
     * </pre>
     */
    public void proposal(cosmos.group.v1beta1.QueryOuterClass.QueryProposalRequest request,
        io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryProposalResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProposalMethod(), responseObserver);
    }

    /**
     * <pre>
     * ProposalsByGroupAccount queries proposals based on group account address.
     * </pre>
     */
    public void proposalsByGroupAccount(cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountRequest request,
        io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProposalsByGroupAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * VoteByProposalVoter queries a vote by proposal id and voter.
     * </pre>
     */
    public void voteByProposalVoter(cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterRequest request,
        io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getVoteByProposalVoterMethod(), responseObserver);
    }

    /**
     * <pre>
     * VotesByProposal queries a vote by proposal.
     * </pre>
     */
    public void votesByProposal(cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalRequest request,
        io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getVotesByProposalMethod(), responseObserver);
    }

    /**
     * <pre>
     * VotesByVoter queries a vote by voter.
     * </pre>
     */
    public void votesByVoter(cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterRequest request,
        io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getVotesByVoterMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGroupInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoRequest,
                cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoResponse>(
                  this, METHODID_GROUP_INFO)))
          .addMethod(
            getGroupAccountInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoRequest,
                cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoResponse>(
                  this, METHODID_GROUP_ACCOUNT_INFO)))
          .addMethod(
            getGroupMembersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersRequest,
                cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse>(
                  this, METHODID_GROUP_MEMBERS)))
          .addMethod(
            getGroupsByAdminMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminRequest,
                cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse>(
                  this, METHODID_GROUPS_BY_ADMIN)))
          .addMethod(
            getGroupAccountsByGroupMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupRequest,
                cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse>(
                  this, METHODID_GROUP_ACCOUNTS_BY_GROUP)))
          .addMethod(
            getGroupAccountsByAdminMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminRequest,
                cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse>(
                  this, METHODID_GROUP_ACCOUNTS_BY_ADMIN)))
          .addMethod(
            getProposalMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.group.v1beta1.QueryOuterClass.QueryProposalRequest,
                cosmos.group.v1beta1.QueryOuterClass.QueryProposalResponse>(
                  this, METHODID_PROPOSAL)))
          .addMethod(
            getProposalsByGroupAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountRequest,
                cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse>(
                  this, METHODID_PROPOSALS_BY_GROUP_ACCOUNT)))
          .addMethod(
            getVoteByProposalVoterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterRequest,
                cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterResponse>(
                  this, METHODID_VOTE_BY_PROPOSAL_VOTER)))
          .addMethod(
            getVotesByProposalMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalRequest,
                cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse>(
                  this, METHODID_VOTES_BY_PROPOSAL)))
          .addMethod(
            getVotesByVoterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterRequest,
                cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse>(
                  this, METHODID_VOTES_BY_VOTER)))
          .build();
    }
  }

  /**
   * <pre>
   * Query is the cosmos.group.v1beta1 Query service.
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
     * GroupInfo queries group info based on group id.
     * </pre>
     */
    public void groupInfo(cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoRequest request,
        io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGroupInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GroupAccountInfo queries group account info based on group account address.
     * </pre>
     */
    public void groupAccountInfo(cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoRequest request,
        io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGroupAccountInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GroupMembers queries members of a group
     * </pre>
     */
    public void groupMembers(cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersRequest request,
        io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGroupMembersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GroupsByAdmin queries groups by admin address.
     * </pre>
     */
    public void groupsByAdmin(cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminRequest request,
        io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGroupsByAdminMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GroupAccountsByGroup queries group accounts by group id.
     * </pre>
     */
    public void groupAccountsByGroup(cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupRequest request,
        io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGroupAccountsByGroupMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GroupsByAdmin queries group accounts by admin address.
     * </pre>
     */
    public void groupAccountsByAdmin(cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminRequest request,
        io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGroupAccountsByAdminMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Proposal queries a proposal based on proposal id.
     * </pre>
     */
    public void proposal(cosmos.group.v1beta1.QueryOuterClass.QueryProposalRequest request,
        io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryProposalResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProposalMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ProposalsByGroupAccount queries proposals based on group account address.
     * </pre>
     */
    public void proposalsByGroupAccount(cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountRequest request,
        io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProposalsByGroupAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * VoteByProposalVoter queries a vote by proposal id and voter.
     * </pre>
     */
    public void voteByProposalVoter(cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterRequest request,
        io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVoteByProposalVoterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * VotesByProposal queries a vote by proposal.
     * </pre>
     */
    public void votesByProposal(cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalRequest request,
        io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVotesByProposalMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * VotesByVoter queries a vote by voter.
     * </pre>
     */
    public void votesByVoter(cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterRequest request,
        io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVotesByVoterMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Query is the cosmos.group.v1beta1 Query service.
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
     * GroupInfo queries group info based on group id.
     * </pre>
     */
    public cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoResponse groupInfo(cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoRequest request) {
      return blockingUnaryCall(
          getChannel(), getGroupInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GroupAccountInfo queries group account info based on group account address.
     * </pre>
     */
    public cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoResponse groupAccountInfo(cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoRequest request) {
      return blockingUnaryCall(
          getChannel(), getGroupAccountInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GroupMembers queries members of a group
     * </pre>
     */
    public cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse groupMembers(cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersRequest request) {
      return blockingUnaryCall(
          getChannel(), getGroupMembersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GroupsByAdmin queries groups by admin address.
     * </pre>
     */
    public cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse groupsByAdmin(cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminRequest request) {
      return blockingUnaryCall(
          getChannel(), getGroupsByAdminMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GroupAccountsByGroup queries group accounts by group id.
     * </pre>
     */
    public cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse groupAccountsByGroup(cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupRequest request) {
      return blockingUnaryCall(
          getChannel(), getGroupAccountsByGroupMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GroupsByAdmin queries group accounts by admin address.
     * </pre>
     */
    public cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse groupAccountsByAdmin(cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminRequest request) {
      return blockingUnaryCall(
          getChannel(), getGroupAccountsByAdminMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Proposal queries a proposal based on proposal id.
     * </pre>
     */
    public cosmos.group.v1beta1.QueryOuterClass.QueryProposalResponse proposal(cosmos.group.v1beta1.QueryOuterClass.QueryProposalRequest request) {
      return blockingUnaryCall(
          getChannel(), getProposalMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ProposalsByGroupAccount queries proposals based on group account address.
     * </pre>
     */
    public cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse proposalsByGroupAccount(cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountRequest request) {
      return blockingUnaryCall(
          getChannel(), getProposalsByGroupAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * VoteByProposalVoter queries a vote by proposal id and voter.
     * </pre>
     */
    public cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterResponse voteByProposalVoter(cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterRequest request) {
      return blockingUnaryCall(
          getChannel(), getVoteByProposalVoterMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * VotesByProposal queries a vote by proposal.
     * </pre>
     */
    public cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse votesByProposal(cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalRequest request) {
      return blockingUnaryCall(
          getChannel(), getVotesByProposalMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * VotesByVoter queries a vote by voter.
     * </pre>
     */
    public cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse votesByVoter(cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterRequest request) {
      return blockingUnaryCall(
          getChannel(), getVotesByVoterMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Query is the cosmos.group.v1beta1 Query service.
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
     * GroupInfo queries group info based on group id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoResponse> groupInfo(
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGroupInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GroupAccountInfo queries group account info based on group account address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoResponse> groupAccountInfo(
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGroupAccountInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GroupMembers queries members of a group
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse> groupMembers(
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGroupMembersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GroupsByAdmin queries groups by admin address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse> groupsByAdmin(
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGroupsByAdminMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GroupAccountsByGroup queries group accounts by group id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse> groupAccountsByGroup(
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGroupAccountsByGroupMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GroupsByAdmin queries group accounts by admin address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse> groupAccountsByAdmin(
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGroupAccountsByAdminMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Proposal queries a proposal based on proposal id.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalResponse> proposal(
        cosmos.group.v1beta1.QueryOuterClass.QueryProposalRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getProposalMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ProposalsByGroupAccount queries proposals based on group account address.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse> proposalsByGroupAccount(
        cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getProposalsByGroupAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * VoteByProposalVoter queries a vote by proposal id and voter.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterResponse> voteByProposalVoter(
        cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getVoteByProposalVoterMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * VotesByProposal queries a vote by proposal.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse> votesByProposal(
        cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getVotesByProposalMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * VotesByVoter queries a vote by voter.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse> votesByVoter(
        cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getVotesByVoterMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GROUP_INFO = 0;
  private static final int METHODID_GROUP_ACCOUNT_INFO = 1;
  private static final int METHODID_GROUP_MEMBERS = 2;
  private static final int METHODID_GROUPS_BY_ADMIN = 3;
  private static final int METHODID_GROUP_ACCOUNTS_BY_GROUP = 4;
  private static final int METHODID_GROUP_ACCOUNTS_BY_ADMIN = 5;
  private static final int METHODID_PROPOSAL = 6;
  private static final int METHODID_PROPOSALS_BY_GROUP_ACCOUNT = 7;
  private static final int METHODID_VOTE_BY_PROPOSAL_VOTER = 8;
  private static final int METHODID_VOTES_BY_PROPOSAL = 9;
  private static final int METHODID_VOTES_BY_VOTER = 10;

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
        case METHODID_GROUP_INFO:
          serviceImpl.groupInfo((cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoResponse>) responseObserver);
          break;
        case METHODID_GROUP_ACCOUNT_INFO:
          serviceImpl.groupAccountInfo((cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoResponse>) responseObserver);
          break;
        case METHODID_GROUP_MEMBERS:
          serviceImpl.groupMembers((cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse>) responseObserver);
          break;
        case METHODID_GROUPS_BY_ADMIN:
          serviceImpl.groupsByAdmin((cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse>) responseObserver);
          break;
        case METHODID_GROUP_ACCOUNTS_BY_GROUP:
          serviceImpl.groupAccountsByGroup((cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse>) responseObserver);
          break;
        case METHODID_GROUP_ACCOUNTS_BY_ADMIN:
          serviceImpl.groupAccountsByAdmin((cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse>) responseObserver);
          break;
        case METHODID_PROPOSAL:
          serviceImpl.proposal((cosmos.group.v1beta1.QueryOuterClass.QueryProposalRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryProposalResponse>) responseObserver);
          break;
        case METHODID_PROPOSALS_BY_GROUP_ACCOUNT:
          serviceImpl.proposalsByGroupAccount((cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse>) responseObserver);
          break;
        case METHODID_VOTE_BY_PROPOSAL_VOTER:
          serviceImpl.voteByProposalVoter((cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterResponse>) responseObserver);
          break;
        case METHODID_VOTES_BY_PROPOSAL:
          serviceImpl.votesByProposal((cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse>) responseObserver);
          break;
        case METHODID_VOTES_BY_VOTER:
          serviceImpl.votesByVoter((cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterRequest) request,
              (io.grpc.stub.StreamObserver<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse>) responseObserver);
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
      return cosmos.group.v1beta1.QueryOuterClass.getDescriptor();
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
              .addMethod(getGroupInfoMethod())
              .addMethod(getGroupAccountInfoMethod())
              .addMethod(getGroupMembersMethod())
              .addMethod(getGroupsByAdminMethod())
              .addMethod(getGroupAccountsByGroupMethod())
              .addMethod(getGroupAccountsByAdminMethod())
              .addMethod(getProposalMethod())
              .addMethod(getProposalsByGroupAccountMethod())
              .addMethod(getVoteByProposalVoterMethod())
              .addMethod(getVotesByProposalMethod())
              .addMethod(getVotesByVoterMethod())
              .build();
        }
      }
    }
    return result;
  }
}
