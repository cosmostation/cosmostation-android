package desmos.subspaces.v1beta1;

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
 * Msg defines subspaces Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: desmos/subspaces/v1beta1/msgs.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "desmos.subspaces.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgCreateSubspace,
      desmos.subspaces.v1beta1.Msgs.MsgCreateSubspaceResponse> getCreateSubspaceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateSubspace",
      requestType = desmos.subspaces.v1beta1.Msgs.MsgCreateSubspace.class,
      responseType = desmos.subspaces.v1beta1.Msgs.MsgCreateSubspaceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgCreateSubspace,
      desmos.subspaces.v1beta1.Msgs.MsgCreateSubspaceResponse> getCreateSubspaceMethod() {
    io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgCreateSubspace, desmos.subspaces.v1beta1.Msgs.MsgCreateSubspaceResponse> getCreateSubspaceMethod;
    if ((getCreateSubspaceMethod = MsgGrpc.getCreateSubspaceMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateSubspaceMethod = MsgGrpc.getCreateSubspaceMethod) == null) {
          MsgGrpc.getCreateSubspaceMethod = getCreateSubspaceMethod =
              io.grpc.MethodDescriptor.<desmos.subspaces.v1beta1.Msgs.MsgCreateSubspace, desmos.subspaces.v1beta1.Msgs.MsgCreateSubspaceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateSubspace"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.subspaces.v1beta1.Msgs.MsgCreateSubspace.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.subspaces.v1beta1.Msgs.MsgCreateSubspaceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateSubspace"))
              .build();
        }
      }
    }
    return getCreateSubspaceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgEditSubspace,
      desmos.subspaces.v1beta1.Msgs.MsgEditSubspaceResponse> getEditSubspaceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EditSubspace",
      requestType = desmos.subspaces.v1beta1.Msgs.MsgEditSubspace.class,
      responseType = desmos.subspaces.v1beta1.Msgs.MsgEditSubspaceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgEditSubspace,
      desmos.subspaces.v1beta1.Msgs.MsgEditSubspaceResponse> getEditSubspaceMethod() {
    io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgEditSubspace, desmos.subspaces.v1beta1.Msgs.MsgEditSubspaceResponse> getEditSubspaceMethod;
    if ((getEditSubspaceMethod = MsgGrpc.getEditSubspaceMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getEditSubspaceMethod = MsgGrpc.getEditSubspaceMethod) == null) {
          MsgGrpc.getEditSubspaceMethod = getEditSubspaceMethod =
              io.grpc.MethodDescriptor.<desmos.subspaces.v1beta1.Msgs.MsgEditSubspace, desmos.subspaces.v1beta1.Msgs.MsgEditSubspaceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EditSubspace"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.subspaces.v1beta1.Msgs.MsgEditSubspace.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.subspaces.v1beta1.Msgs.MsgEditSubspaceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("EditSubspace"))
              .build();
        }
      }
    }
    return getEditSubspaceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgAddAdmin,
      desmos.subspaces.v1beta1.Msgs.MsgAddAdminResponse> getAddAdminMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddAdmin",
      requestType = desmos.subspaces.v1beta1.Msgs.MsgAddAdmin.class,
      responseType = desmos.subspaces.v1beta1.Msgs.MsgAddAdminResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgAddAdmin,
      desmos.subspaces.v1beta1.Msgs.MsgAddAdminResponse> getAddAdminMethod() {
    io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgAddAdmin, desmos.subspaces.v1beta1.Msgs.MsgAddAdminResponse> getAddAdminMethod;
    if ((getAddAdminMethod = MsgGrpc.getAddAdminMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddAdminMethod = MsgGrpc.getAddAdminMethod) == null) {
          MsgGrpc.getAddAdminMethod = getAddAdminMethod =
              io.grpc.MethodDescriptor.<desmos.subspaces.v1beta1.Msgs.MsgAddAdmin, desmos.subspaces.v1beta1.Msgs.MsgAddAdminResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddAdmin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.subspaces.v1beta1.Msgs.MsgAddAdmin.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.subspaces.v1beta1.Msgs.MsgAddAdminResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddAdmin"))
              .build();
        }
      }
    }
    return getAddAdminMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgRemoveAdmin,
      desmos.subspaces.v1beta1.Msgs.MsgRemoveAdminResponse> getRemoveAdminMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveAdmin",
      requestType = desmos.subspaces.v1beta1.Msgs.MsgRemoveAdmin.class,
      responseType = desmos.subspaces.v1beta1.Msgs.MsgRemoveAdminResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgRemoveAdmin,
      desmos.subspaces.v1beta1.Msgs.MsgRemoveAdminResponse> getRemoveAdminMethod() {
    io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgRemoveAdmin, desmos.subspaces.v1beta1.Msgs.MsgRemoveAdminResponse> getRemoveAdminMethod;
    if ((getRemoveAdminMethod = MsgGrpc.getRemoveAdminMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRemoveAdminMethod = MsgGrpc.getRemoveAdminMethod) == null) {
          MsgGrpc.getRemoveAdminMethod = getRemoveAdminMethod =
              io.grpc.MethodDescriptor.<desmos.subspaces.v1beta1.Msgs.MsgRemoveAdmin, desmos.subspaces.v1beta1.Msgs.MsgRemoveAdminResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemoveAdmin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.subspaces.v1beta1.Msgs.MsgRemoveAdmin.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.subspaces.v1beta1.Msgs.MsgRemoveAdminResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RemoveAdmin"))
              .build();
        }
      }
    }
    return getRemoveAdminMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgRegisterUser,
      desmos.subspaces.v1beta1.Msgs.MsgRegisterUserResponse> getRegisterUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterUser",
      requestType = desmos.subspaces.v1beta1.Msgs.MsgRegisterUser.class,
      responseType = desmos.subspaces.v1beta1.Msgs.MsgRegisterUserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgRegisterUser,
      desmos.subspaces.v1beta1.Msgs.MsgRegisterUserResponse> getRegisterUserMethod() {
    io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgRegisterUser, desmos.subspaces.v1beta1.Msgs.MsgRegisterUserResponse> getRegisterUserMethod;
    if ((getRegisterUserMethod = MsgGrpc.getRegisterUserMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRegisterUserMethod = MsgGrpc.getRegisterUserMethod) == null) {
          MsgGrpc.getRegisterUserMethod = getRegisterUserMethod =
              io.grpc.MethodDescriptor.<desmos.subspaces.v1beta1.Msgs.MsgRegisterUser, desmos.subspaces.v1beta1.Msgs.MsgRegisterUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.subspaces.v1beta1.Msgs.MsgRegisterUser.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.subspaces.v1beta1.Msgs.MsgRegisterUserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RegisterUser"))
              .build();
        }
      }
    }
    return getRegisterUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgUnregisterUser,
      desmos.subspaces.v1beta1.Msgs.MsgUnregisterUserResponse> getUnregisterUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnregisterUser",
      requestType = desmos.subspaces.v1beta1.Msgs.MsgUnregisterUser.class,
      responseType = desmos.subspaces.v1beta1.Msgs.MsgUnregisterUserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgUnregisterUser,
      desmos.subspaces.v1beta1.Msgs.MsgUnregisterUserResponse> getUnregisterUserMethod() {
    io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgUnregisterUser, desmos.subspaces.v1beta1.Msgs.MsgUnregisterUserResponse> getUnregisterUserMethod;
    if ((getUnregisterUserMethod = MsgGrpc.getUnregisterUserMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUnregisterUserMethod = MsgGrpc.getUnregisterUserMethod) == null) {
          MsgGrpc.getUnregisterUserMethod = getUnregisterUserMethod =
              io.grpc.MethodDescriptor.<desmos.subspaces.v1beta1.Msgs.MsgUnregisterUser, desmos.subspaces.v1beta1.Msgs.MsgUnregisterUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnregisterUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.subspaces.v1beta1.Msgs.MsgUnregisterUser.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.subspaces.v1beta1.Msgs.MsgUnregisterUserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UnregisterUser"))
              .build();
        }
      }
    }
    return getUnregisterUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgBanUser,
      desmos.subspaces.v1beta1.Msgs.MsgBanUserResponse> getBanUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BanUser",
      requestType = desmos.subspaces.v1beta1.Msgs.MsgBanUser.class,
      responseType = desmos.subspaces.v1beta1.Msgs.MsgBanUserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgBanUser,
      desmos.subspaces.v1beta1.Msgs.MsgBanUserResponse> getBanUserMethod() {
    io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgBanUser, desmos.subspaces.v1beta1.Msgs.MsgBanUserResponse> getBanUserMethod;
    if ((getBanUserMethod = MsgGrpc.getBanUserMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBanUserMethod = MsgGrpc.getBanUserMethod) == null) {
          MsgGrpc.getBanUserMethod = getBanUserMethod =
              io.grpc.MethodDescriptor.<desmos.subspaces.v1beta1.Msgs.MsgBanUser, desmos.subspaces.v1beta1.Msgs.MsgBanUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BanUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.subspaces.v1beta1.Msgs.MsgBanUser.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.subspaces.v1beta1.Msgs.MsgBanUserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("BanUser"))
              .build();
        }
      }
    }
    return getBanUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgUnbanUser,
      desmos.subspaces.v1beta1.Msgs.MsgUnbanUserResponse> getUnbanUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnbanUser",
      requestType = desmos.subspaces.v1beta1.Msgs.MsgUnbanUser.class,
      responseType = desmos.subspaces.v1beta1.Msgs.MsgUnbanUserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgUnbanUser,
      desmos.subspaces.v1beta1.Msgs.MsgUnbanUserResponse> getUnbanUserMethod() {
    io.grpc.MethodDescriptor<desmos.subspaces.v1beta1.Msgs.MsgUnbanUser, desmos.subspaces.v1beta1.Msgs.MsgUnbanUserResponse> getUnbanUserMethod;
    if ((getUnbanUserMethod = MsgGrpc.getUnbanUserMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUnbanUserMethod = MsgGrpc.getUnbanUserMethod) == null) {
          MsgGrpc.getUnbanUserMethod = getUnbanUserMethod =
              io.grpc.MethodDescriptor.<desmos.subspaces.v1beta1.Msgs.MsgUnbanUser, desmos.subspaces.v1beta1.Msgs.MsgUnbanUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnbanUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.subspaces.v1beta1.Msgs.MsgUnbanUser.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  desmos.subspaces.v1beta1.Msgs.MsgUnbanUserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UnbanUser"))
              .build();
        }
      }
    }
    return getUnbanUserMethod;
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
   * Msg defines subspaces Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CreateSubspace defines the method to create a subspace
     * </pre>
     */
    public void createSubspace(desmos.subspaces.v1beta1.Msgs.MsgCreateSubspace request,
        io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgCreateSubspaceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateSubspaceMethod(), responseObserver);
    }

    /**
     * <pre>
     * EditSubspace defines the method to edit a subspace fields
     * </pre>
     */
    public void editSubspace(desmos.subspaces.v1beta1.Msgs.MsgEditSubspace request,
        io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgEditSubspaceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEditSubspaceMethod(), responseObserver);
    }

    /**
     * <pre>
     * AddAdmin defines the method to add a new admin to the subspace
     * </pre>
     */
    public void addAdmin(desmos.subspaces.v1beta1.Msgs.MsgAddAdmin request,
        io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgAddAdminResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddAdminMethod(), responseObserver);
    }

    /**
     * <pre>
     * RemoveAdmin defines the method to remove an admin from a specific subspace
     * </pre>
     */
    public void removeAdmin(desmos.subspaces.v1beta1.Msgs.MsgRemoveAdmin request,
        io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgRemoveAdminResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveAdminMethod(), responseObserver);
    }

    /**
     * <pre>
     * RegisterUser defines the method to let user posts inside a specific
     * subspace
     * </pre>
     */
    public void registerUser(desmos.subspaces.v1beta1.Msgs.MsgRegisterUser request,
        io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgRegisterUserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterUserMethod(), responseObserver);
    }

    /**
     * <pre>
     * UnregisterUser defines the method to unregister a user from a subspace
     * </pre>
     */
    public void unregisterUser(desmos.subspaces.v1beta1.Msgs.MsgUnregisterUser request,
        io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgUnregisterUserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnregisterUserMethod(), responseObserver);
    }

    /**
     * <pre>
     * BanUser defines the method to ban a user inside a specific subspace
     * </pre>
     */
    public void banUser(desmos.subspaces.v1beta1.Msgs.MsgBanUser request,
        io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgBanUserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBanUserMethod(), responseObserver);
    }

    /**
     * <pre>
     * UnbanUser defines the method to unban a user inside a specific subspace
     * </pre>
     */
    public void unbanUser(desmos.subspaces.v1beta1.Msgs.MsgUnbanUser request,
        io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgUnbanUserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnbanUserMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateSubspaceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.subspaces.v1beta1.Msgs.MsgCreateSubspace,
                desmos.subspaces.v1beta1.Msgs.MsgCreateSubspaceResponse>(
                  this, METHODID_CREATE_SUBSPACE)))
          .addMethod(
            getEditSubspaceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.subspaces.v1beta1.Msgs.MsgEditSubspace,
                desmos.subspaces.v1beta1.Msgs.MsgEditSubspaceResponse>(
                  this, METHODID_EDIT_SUBSPACE)))
          .addMethod(
            getAddAdminMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.subspaces.v1beta1.Msgs.MsgAddAdmin,
                desmos.subspaces.v1beta1.Msgs.MsgAddAdminResponse>(
                  this, METHODID_ADD_ADMIN)))
          .addMethod(
            getRemoveAdminMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.subspaces.v1beta1.Msgs.MsgRemoveAdmin,
                desmos.subspaces.v1beta1.Msgs.MsgRemoveAdminResponse>(
                  this, METHODID_REMOVE_ADMIN)))
          .addMethod(
            getRegisterUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.subspaces.v1beta1.Msgs.MsgRegisterUser,
                desmos.subspaces.v1beta1.Msgs.MsgRegisterUserResponse>(
                  this, METHODID_REGISTER_USER)))
          .addMethod(
            getUnregisterUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.subspaces.v1beta1.Msgs.MsgUnregisterUser,
                desmos.subspaces.v1beta1.Msgs.MsgUnregisterUserResponse>(
                  this, METHODID_UNREGISTER_USER)))
          .addMethod(
            getBanUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.subspaces.v1beta1.Msgs.MsgBanUser,
                desmos.subspaces.v1beta1.Msgs.MsgBanUserResponse>(
                  this, METHODID_BAN_USER)))
          .addMethod(
            getUnbanUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                desmos.subspaces.v1beta1.Msgs.MsgUnbanUser,
                desmos.subspaces.v1beta1.Msgs.MsgUnbanUserResponse>(
                  this, METHODID_UNBAN_USER)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines subspaces Msg service.
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
     * CreateSubspace defines the method to create a subspace
     * </pre>
     */
    public void createSubspace(desmos.subspaces.v1beta1.Msgs.MsgCreateSubspace request,
        io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgCreateSubspaceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateSubspaceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EditSubspace defines the method to edit a subspace fields
     * </pre>
     */
    public void editSubspace(desmos.subspaces.v1beta1.Msgs.MsgEditSubspace request,
        io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgEditSubspaceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEditSubspaceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AddAdmin defines the method to add a new admin to the subspace
     * </pre>
     */
    public void addAdmin(desmos.subspaces.v1beta1.Msgs.MsgAddAdmin request,
        io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgAddAdminResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddAdminMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RemoveAdmin defines the method to remove an admin from a specific subspace
     * </pre>
     */
    public void removeAdmin(desmos.subspaces.v1beta1.Msgs.MsgRemoveAdmin request,
        io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgRemoveAdminResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveAdminMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RegisterUser defines the method to let user posts inside a specific
     * subspace
     * </pre>
     */
    public void registerUser(desmos.subspaces.v1beta1.Msgs.MsgRegisterUser request,
        io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgRegisterUserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UnregisterUser defines the method to unregister a user from a subspace
     * </pre>
     */
    public void unregisterUser(desmos.subspaces.v1beta1.Msgs.MsgUnregisterUser request,
        io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgUnregisterUserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnregisterUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BanUser defines the method to ban a user inside a specific subspace
     * </pre>
     */
    public void banUser(desmos.subspaces.v1beta1.Msgs.MsgBanUser request,
        io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgBanUserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBanUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UnbanUser defines the method to unban a user inside a specific subspace
     * </pre>
     */
    public void unbanUser(desmos.subspaces.v1beta1.Msgs.MsgUnbanUser request,
        io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgUnbanUserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnbanUserMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines subspaces Msg service.
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
     * CreateSubspace defines the method to create a subspace
     * </pre>
     */
    public desmos.subspaces.v1beta1.Msgs.MsgCreateSubspaceResponse createSubspace(desmos.subspaces.v1beta1.Msgs.MsgCreateSubspace request) {
      return blockingUnaryCall(
          getChannel(), getCreateSubspaceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EditSubspace defines the method to edit a subspace fields
     * </pre>
     */
    public desmos.subspaces.v1beta1.Msgs.MsgEditSubspaceResponse editSubspace(desmos.subspaces.v1beta1.Msgs.MsgEditSubspace request) {
      return blockingUnaryCall(
          getChannel(), getEditSubspaceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AddAdmin defines the method to add a new admin to the subspace
     * </pre>
     */
    public desmos.subspaces.v1beta1.Msgs.MsgAddAdminResponse addAdmin(desmos.subspaces.v1beta1.Msgs.MsgAddAdmin request) {
      return blockingUnaryCall(
          getChannel(), getAddAdminMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RemoveAdmin defines the method to remove an admin from a specific subspace
     * </pre>
     */
    public desmos.subspaces.v1beta1.Msgs.MsgRemoveAdminResponse removeAdmin(desmos.subspaces.v1beta1.Msgs.MsgRemoveAdmin request) {
      return blockingUnaryCall(
          getChannel(), getRemoveAdminMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RegisterUser defines the method to let user posts inside a specific
     * subspace
     * </pre>
     */
    public desmos.subspaces.v1beta1.Msgs.MsgRegisterUserResponse registerUser(desmos.subspaces.v1beta1.Msgs.MsgRegisterUser request) {
      return blockingUnaryCall(
          getChannel(), getRegisterUserMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UnregisterUser defines the method to unregister a user from a subspace
     * </pre>
     */
    public desmos.subspaces.v1beta1.Msgs.MsgUnregisterUserResponse unregisterUser(desmos.subspaces.v1beta1.Msgs.MsgUnregisterUser request) {
      return blockingUnaryCall(
          getChannel(), getUnregisterUserMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * BanUser defines the method to ban a user inside a specific subspace
     * </pre>
     */
    public desmos.subspaces.v1beta1.Msgs.MsgBanUserResponse banUser(desmos.subspaces.v1beta1.Msgs.MsgBanUser request) {
      return blockingUnaryCall(
          getChannel(), getBanUserMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UnbanUser defines the method to unban a user inside a specific subspace
     * </pre>
     */
    public desmos.subspaces.v1beta1.Msgs.MsgUnbanUserResponse unbanUser(desmos.subspaces.v1beta1.Msgs.MsgUnbanUser request) {
      return blockingUnaryCall(
          getChannel(), getUnbanUserMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines subspaces Msg service.
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
     * CreateSubspace defines the method to create a subspace
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.subspaces.v1beta1.Msgs.MsgCreateSubspaceResponse> createSubspace(
        desmos.subspaces.v1beta1.Msgs.MsgCreateSubspace request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateSubspaceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EditSubspace defines the method to edit a subspace fields
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.subspaces.v1beta1.Msgs.MsgEditSubspaceResponse> editSubspace(
        desmos.subspaces.v1beta1.Msgs.MsgEditSubspace request) {
      return futureUnaryCall(
          getChannel().newCall(getEditSubspaceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AddAdmin defines the method to add a new admin to the subspace
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.subspaces.v1beta1.Msgs.MsgAddAdminResponse> addAdmin(
        desmos.subspaces.v1beta1.Msgs.MsgAddAdmin request) {
      return futureUnaryCall(
          getChannel().newCall(getAddAdminMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RemoveAdmin defines the method to remove an admin from a specific subspace
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.subspaces.v1beta1.Msgs.MsgRemoveAdminResponse> removeAdmin(
        desmos.subspaces.v1beta1.Msgs.MsgRemoveAdmin request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveAdminMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RegisterUser defines the method to let user posts inside a specific
     * subspace
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.subspaces.v1beta1.Msgs.MsgRegisterUserResponse> registerUser(
        desmos.subspaces.v1beta1.Msgs.MsgRegisterUser request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterUserMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UnregisterUser defines the method to unregister a user from a subspace
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.subspaces.v1beta1.Msgs.MsgUnregisterUserResponse> unregisterUser(
        desmos.subspaces.v1beta1.Msgs.MsgUnregisterUser request) {
      return futureUnaryCall(
          getChannel().newCall(getUnregisterUserMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * BanUser defines the method to ban a user inside a specific subspace
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.subspaces.v1beta1.Msgs.MsgBanUserResponse> banUser(
        desmos.subspaces.v1beta1.Msgs.MsgBanUser request) {
      return futureUnaryCall(
          getChannel().newCall(getBanUserMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UnbanUser defines the method to unban a user inside a specific subspace
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<desmos.subspaces.v1beta1.Msgs.MsgUnbanUserResponse> unbanUser(
        desmos.subspaces.v1beta1.Msgs.MsgUnbanUser request) {
      return futureUnaryCall(
          getChannel().newCall(getUnbanUserMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_SUBSPACE = 0;
  private static final int METHODID_EDIT_SUBSPACE = 1;
  private static final int METHODID_ADD_ADMIN = 2;
  private static final int METHODID_REMOVE_ADMIN = 3;
  private static final int METHODID_REGISTER_USER = 4;
  private static final int METHODID_UNREGISTER_USER = 5;
  private static final int METHODID_BAN_USER = 6;
  private static final int METHODID_UNBAN_USER = 7;

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
        case METHODID_CREATE_SUBSPACE:
          serviceImpl.createSubspace((desmos.subspaces.v1beta1.Msgs.MsgCreateSubspace) request,
              (io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgCreateSubspaceResponse>) responseObserver);
          break;
        case METHODID_EDIT_SUBSPACE:
          serviceImpl.editSubspace((desmos.subspaces.v1beta1.Msgs.MsgEditSubspace) request,
              (io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgEditSubspaceResponse>) responseObserver);
          break;
        case METHODID_ADD_ADMIN:
          serviceImpl.addAdmin((desmos.subspaces.v1beta1.Msgs.MsgAddAdmin) request,
              (io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgAddAdminResponse>) responseObserver);
          break;
        case METHODID_REMOVE_ADMIN:
          serviceImpl.removeAdmin((desmos.subspaces.v1beta1.Msgs.MsgRemoveAdmin) request,
              (io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgRemoveAdminResponse>) responseObserver);
          break;
        case METHODID_REGISTER_USER:
          serviceImpl.registerUser((desmos.subspaces.v1beta1.Msgs.MsgRegisterUser) request,
              (io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgRegisterUserResponse>) responseObserver);
          break;
        case METHODID_UNREGISTER_USER:
          serviceImpl.unregisterUser((desmos.subspaces.v1beta1.Msgs.MsgUnregisterUser) request,
              (io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgUnregisterUserResponse>) responseObserver);
          break;
        case METHODID_BAN_USER:
          serviceImpl.banUser((desmos.subspaces.v1beta1.Msgs.MsgBanUser) request,
              (io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgBanUserResponse>) responseObserver);
          break;
        case METHODID_UNBAN_USER:
          serviceImpl.unbanUser((desmos.subspaces.v1beta1.Msgs.MsgUnbanUser) request,
              (io.grpc.stub.StreamObserver<desmos.subspaces.v1beta1.Msgs.MsgUnbanUserResponse>) responseObserver);
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
      return desmos.subspaces.v1beta1.Msgs.getDescriptor();
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
              .addMethod(getCreateSubspaceMethod())
              .addMethod(getEditSubspaceMethod())
              .addMethod(getAddAdminMethod())
              .addMethod(getRemoveAdminMethod())
              .addMethod(getRegisterUserMethod())
              .addMethod(getUnregisterUserMethod())
              .addMethod(getBanUserMethod())
              .addMethod(getUnbanUserMethod())
              .build();
        }
      }
    }
    return result;
  }
}
