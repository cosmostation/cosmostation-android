package kava.committee.v1beta1;

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
 * Msg defines the committee Msg service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: kava/committee/v1beta1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "kava.committee.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<kava.committee.v1beta1.Tx.MsgSubmitProposal,
      kava.committee.v1beta1.Tx.MsgSubmitProposalResponse> getSubmitProposalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubmitProposal",
      requestType = kava.committee.v1beta1.Tx.MsgSubmitProposal.class,
      responseType = kava.committee.v1beta1.Tx.MsgSubmitProposalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.committee.v1beta1.Tx.MsgSubmitProposal,
      kava.committee.v1beta1.Tx.MsgSubmitProposalResponse> getSubmitProposalMethod() {
    io.grpc.MethodDescriptor<kava.committee.v1beta1.Tx.MsgSubmitProposal, kava.committee.v1beta1.Tx.MsgSubmitProposalResponse> getSubmitProposalMethod;
    if ((getSubmitProposalMethod = MsgGrpc.getSubmitProposalMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSubmitProposalMethod = MsgGrpc.getSubmitProposalMethod) == null) {
          MsgGrpc.getSubmitProposalMethod = getSubmitProposalMethod =
              io.grpc.MethodDescriptor.<kava.committee.v1beta1.Tx.MsgSubmitProposal, kava.committee.v1beta1.Tx.MsgSubmitProposalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubmitProposal"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.committee.v1beta1.Tx.MsgSubmitProposal.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.committee.v1beta1.Tx.MsgSubmitProposalResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SubmitProposal"))
              .build();
        }
      }
    }
    return getSubmitProposalMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kava.committee.v1beta1.Tx.MsgVote,
      kava.committee.v1beta1.Tx.MsgVoteResponse> getVoteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Vote",
      requestType = kava.committee.v1beta1.Tx.MsgVote.class,
      responseType = kava.committee.v1beta1.Tx.MsgVoteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kava.committee.v1beta1.Tx.MsgVote,
      kava.committee.v1beta1.Tx.MsgVoteResponse> getVoteMethod() {
    io.grpc.MethodDescriptor<kava.committee.v1beta1.Tx.MsgVote, kava.committee.v1beta1.Tx.MsgVoteResponse> getVoteMethod;
    if ((getVoteMethod = MsgGrpc.getVoteMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getVoteMethod = MsgGrpc.getVoteMethod) == null) {
          MsgGrpc.getVoteMethod = getVoteMethod =
              io.grpc.MethodDescriptor.<kava.committee.v1beta1.Tx.MsgVote, kava.committee.v1beta1.Tx.MsgVoteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Vote"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.committee.v1beta1.Tx.MsgVote.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kava.committee.v1beta1.Tx.MsgVoteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Vote"))
              .build();
        }
      }
    }
    return getVoteMethod;
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
   * Msg defines the committee Msg service
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * SubmitProposal defines a method for submitting a committee proposal
     * </pre>
     */
    public void submitProposal(kava.committee.v1beta1.Tx.MsgSubmitProposal request,
        io.grpc.stub.StreamObserver<kava.committee.v1beta1.Tx.MsgSubmitProposalResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubmitProposalMethod(), responseObserver);
    }

    /**
     * <pre>
     * Vote defines a method for voting on a proposal
     * </pre>
     */
    public void vote(kava.committee.v1beta1.Tx.MsgVote request,
        io.grpc.stub.StreamObserver<kava.committee.v1beta1.Tx.MsgVoteResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getVoteMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSubmitProposalMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.committee.v1beta1.Tx.MsgSubmitProposal,
                kava.committee.v1beta1.Tx.MsgSubmitProposalResponse>(
                  this, METHODID_SUBMIT_PROPOSAL)))
          .addMethod(
            getVoteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kava.committee.v1beta1.Tx.MsgVote,
                kava.committee.v1beta1.Tx.MsgVoteResponse>(
                  this, METHODID_VOTE)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the committee Msg service
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
     * SubmitProposal defines a method for submitting a committee proposal
     * </pre>
     */
    public void submitProposal(kava.committee.v1beta1.Tx.MsgSubmitProposal request,
        io.grpc.stub.StreamObserver<kava.committee.v1beta1.Tx.MsgSubmitProposalResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubmitProposalMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Vote defines a method for voting on a proposal
     * </pre>
     */
    public void vote(kava.committee.v1beta1.Tx.MsgVote request,
        io.grpc.stub.StreamObserver<kava.committee.v1beta1.Tx.MsgVoteResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVoteMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the committee Msg service
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
     * SubmitProposal defines a method for submitting a committee proposal
     * </pre>
     */
    public kava.committee.v1beta1.Tx.MsgSubmitProposalResponse submitProposal(kava.committee.v1beta1.Tx.MsgSubmitProposal request) {
      return blockingUnaryCall(
          getChannel(), getSubmitProposalMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Vote defines a method for voting on a proposal
     * </pre>
     */
    public kava.committee.v1beta1.Tx.MsgVoteResponse vote(kava.committee.v1beta1.Tx.MsgVote request) {
      return blockingUnaryCall(
          getChannel(), getVoteMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the committee Msg service
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
     * SubmitProposal defines a method for submitting a committee proposal
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.committee.v1beta1.Tx.MsgSubmitProposalResponse> submitProposal(
        kava.committee.v1beta1.Tx.MsgSubmitProposal request) {
      return futureUnaryCall(
          getChannel().newCall(getSubmitProposalMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Vote defines a method for voting on a proposal
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kava.committee.v1beta1.Tx.MsgVoteResponse> vote(
        kava.committee.v1beta1.Tx.MsgVote request) {
      return futureUnaryCall(
          getChannel().newCall(getVoteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SUBMIT_PROPOSAL = 0;
  private static final int METHODID_VOTE = 1;

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
        case METHODID_SUBMIT_PROPOSAL:
          serviceImpl.submitProposal((kava.committee.v1beta1.Tx.MsgSubmitProposal) request,
              (io.grpc.stub.StreamObserver<kava.committee.v1beta1.Tx.MsgSubmitProposalResponse>) responseObserver);
          break;
        case METHODID_VOTE:
          serviceImpl.vote((kava.committee.v1beta1.Tx.MsgVote) request,
              (io.grpc.stub.StreamObserver<kava.committee.v1beta1.Tx.MsgVoteResponse>) responseObserver);
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
      return kava.committee.v1beta1.Tx.getDescriptor();
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
              .addMethod(getSubmitProposalMethod())
              .addMethod(getVoteMethod())
              .build();
        }
      }
    }
    return result;
  }
}
