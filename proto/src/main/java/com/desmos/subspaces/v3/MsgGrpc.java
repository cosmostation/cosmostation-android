package com.desmos.subspaces.v3;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines subspaces Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: desmos/subspaces/v3/msgs.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "desmos.subspaces.v3.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspace,
      com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspaceResponse> getCreateSubspaceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateSubspace",
      requestType = com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspace.class,
      responseType = com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspaceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspace,
      com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspaceResponse> getCreateSubspaceMethod() {
    io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspace, com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspaceResponse> getCreateSubspaceMethod;
    if ((getCreateSubspaceMethod = MsgGrpc.getCreateSubspaceMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateSubspaceMethod = MsgGrpc.getCreateSubspaceMethod) == null) {
          MsgGrpc.getCreateSubspaceMethod = getCreateSubspaceMethod =
              io.grpc.MethodDescriptor.<com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspace, com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspaceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateSubspace"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspace.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspaceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateSubspace"))
              .build();
        }
      }
    }
    return getCreateSubspaceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgEditSubspace,
      com.desmos.subspaces.v3.MsgsProto.MsgEditSubspaceResponse> getEditSubspaceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EditSubspace",
      requestType = com.desmos.subspaces.v3.MsgsProto.MsgEditSubspace.class,
      responseType = com.desmos.subspaces.v3.MsgsProto.MsgEditSubspaceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgEditSubspace,
      com.desmos.subspaces.v3.MsgsProto.MsgEditSubspaceResponse> getEditSubspaceMethod() {
    io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgEditSubspace, com.desmos.subspaces.v3.MsgsProto.MsgEditSubspaceResponse> getEditSubspaceMethod;
    if ((getEditSubspaceMethod = MsgGrpc.getEditSubspaceMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getEditSubspaceMethod = MsgGrpc.getEditSubspaceMethod) == null) {
          MsgGrpc.getEditSubspaceMethod = getEditSubspaceMethod =
              io.grpc.MethodDescriptor.<com.desmos.subspaces.v3.MsgsProto.MsgEditSubspace, com.desmos.subspaces.v3.MsgsProto.MsgEditSubspaceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EditSubspace"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgEditSubspace.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgEditSubspaceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("EditSubspace"))
              .build();
        }
      }
    }
    return getEditSubspaceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspace,
      com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspaceResponse> getDeleteSubspaceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteSubspace",
      requestType = com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspace.class,
      responseType = com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspaceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspace,
      com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspaceResponse> getDeleteSubspaceMethod() {
    io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspace, com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspaceResponse> getDeleteSubspaceMethod;
    if ((getDeleteSubspaceMethod = MsgGrpc.getDeleteSubspaceMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteSubspaceMethod = MsgGrpc.getDeleteSubspaceMethod) == null) {
          MsgGrpc.getDeleteSubspaceMethod = getDeleteSubspaceMethod =
              io.grpc.MethodDescriptor.<com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspace, com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspaceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteSubspace"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspace.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspaceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteSubspace"))
              .build();
        }
      }
    }
    return getDeleteSubspaceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgCreateSection,
      com.desmos.subspaces.v3.MsgsProto.MsgCreateSectionResponse> getCreateSectionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateSection",
      requestType = com.desmos.subspaces.v3.MsgsProto.MsgCreateSection.class,
      responseType = com.desmos.subspaces.v3.MsgsProto.MsgCreateSectionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgCreateSection,
      com.desmos.subspaces.v3.MsgsProto.MsgCreateSectionResponse> getCreateSectionMethod() {
    io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgCreateSection, com.desmos.subspaces.v3.MsgsProto.MsgCreateSectionResponse> getCreateSectionMethod;
    if ((getCreateSectionMethod = MsgGrpc.getCreateSectionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateSectionMethod = MsgGrpc.getCreateSectionMethod) == null) {
          MsgGrpc.getCreateSectionMethod = getCreateSectionMethod =
              io.grpc.MethodDescriptor.<com.desmos.subspaces.v3.MsgsProto.MsgCreateSection, com.desmos.subspaces.v3.MsgsProto.MsgCreateSectionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateSection"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgCreateSection.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgCreateSectionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateSection"))
              .build();
        }
      }
    }
    return getCreateSectionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgEditSection,
      com.desmos.subspaces.v3.MsgsProto.MsgEditSectionResponse> getEditSectionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EditSection",
      requestType = com.desmos.subspaces.v3.MsgsProto.MsgEditSection.class,
      responseType = com.desmos.subspaces.v3.MsgsProto.MsgEditSectionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgEditSection,
      com.desmos.subspaces.v3.MsgsProto.MsgEditSectionResponse> getEditSectionMethod() {
    io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgEditSection, com.desmos.subspaces.v3.MsgsProto.MsgEditSectionResponse> getEditSectionMethod;
    if ((getEditSectionMethod = MsgGrpc.getEditSectionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getEditSectionMethod = MsgGrpc.getEditSectionMethod) == null) {
          MsgGrpc.getEditSectionMethod = getEditSectionMethod =
              io.grpc.MethodDescriptor.<com.desmos.subspaces.v3.MsgsProto.MsgEditSection, com.desmos.subspaces.v3.MsgsProto.MsgEditSectionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EditSection"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgEditSection.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgEditSectionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("EditSection"))
              .build();
        }
      }
    }
    return getEditSectionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgMoveSection,
      com.desmos.subspaces.v3.MsgsProto.MsgMoveSectionResponse> getMoveSectionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MoveSection",
      requestType = com.desmos.subspaces.v3.MsgsProto.MsgMoveSection.class,
      responseType = com.desmos.subspaces.v3.MsgsProto.MsgMoveSectionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgMoveSection,
      com.desmos.subspaces.v3.MsgsProto.MsgMoveSectionResponse> getMoveSectionMethod() {
    io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgMoveSection, com.desmos.subspaces.v3.MsgsProto.MsgMoveSectionResponse> getMoveSectionMethod;
    if ((getMoveSectionMethod = MsgGrpc.getMoveSectionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getMoveSectionMethod = MsgGrpc.getMoveSectionMethod) == null) {
          MsgGrpc.getMoveSectionMethod = getMoveSectionMethod =
              io.grpc.MethodDescriptor.<com.desmos.subspaces.v3.MsgsProto.MsgMoveSection, com.desmos.subspaces.v3.MsgsProto.MsgMoveSectionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MoveSection"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgMoveSection.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgMoveSectionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("MoveSection"))
              .build();
        }
      }
    }
    return getMoveSectionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgDeleteSection,
      com.desmos.subspaces.v3.MsgsProto.MsgDeleteSectionResponse> getDeleteSectionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteSection",
      requestType = com.desmos.subspaces.v3.MsgsProto.MsgDeleteSection.class,
      responseType = com.desmos.subspaces.v3.MsgsProto.MsgDeleteSectionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgDeleteSection,
      com.desmos.subspaces.v3.MsgsProto.MsgDeleteSectionResponse> getDeleteSectionMethod() {
    io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgDeleteSection, com.desmos.subspaces.v3.MsgsProto.MsgDeleteSectionResponse> getDeleteSectionMethod;
    if ((getDeleteSectionMethod = MsgGrpc.getDeleteSectionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteSectionMethod = MsgGrpc.getDeleteSectionMethod) == null) {
          MsgGrpc.getDeleteSectionMethod = getDeleteSectionMethod =
              io.grpc.MethodDescriptor.<com.desmos.subspaces.v3.MsgsProto.MsgDeleteSection, com.desmos.subspaces.v3.MsgsProto.MsgDeleteSectionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteSection"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgDeleteSection.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgDeleteSectionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteSection"))
              .build();
        }
      }
    }
    return getDeleteSectionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroup,
      com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroupResponse> getCreateUserGroupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateUserGroup",
      requestType = com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroup.class,
      responseType = com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroupResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroup,
      com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroupResponse> getCreateUserGroupMethod() {
    io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroup, com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroupResponse> getCreateUserGroupMethod;
    if ((getCreateUserGroupMethod = MsgGrpc.getCreateUserGroupMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateUserGroupMethod = MsgGrpc.getCreateUserGroupMethod) == null) {
          MsgGrpc.getCreateUserGroupMethod = getCreateUserGroupMethod =
              io.grpc.MethodDescriptor.<com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroup, com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroupResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateUserGroup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroup.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroupResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateUserGroup"))
              .build();
        }
      }
    }
    return getCreateUserGroupMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroup,
      com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroupResponse> getEditUserGroupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EditUserGroup",
      requestType = com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroup.class,
      responseType = com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroupResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroup,
      com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroupResponse> getEditUserGroupMethod() {
    io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroup, com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroupResponse> getEditUserGroupMethod;
    if ((getEditUserGroupMethod = MsgGrpc.getEditUserGroupMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getEditUserGroupMethod = MsgGrpc.getEditUserGroupMethod) == null) {
          MsgGrpc.getEditUserGroupMethod = getEditUserGroupMethod =
              io.grpc.MethodDescriptor.<com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroup, com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroupResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EditUserGroup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroup.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroupResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("EditUserGroup"))
              .build();
        }
      }
    }
    return getEditUserGroupMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroup,
      com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroupResponse> getMoveUserGroupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MoveUserGroup",
      requestType = com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroup.class,
      responseType = com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroupResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroup,
      com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroupResponse> getMoveUserGroupMethod() {
    io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroup, com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroupResponse> getMoveUserGroupMethod;
    if ((getMoveUserGroupMethod = MsgGrpc.getMoveUserGroupMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getMoveUserGroupMethod = MsgGrpc.getMoveUserGroupMethod) == null) {
          MsgGrpc.getMoveUserGroupMethod = getMoveUserGroupMethod =
              io.grpc.MethodDescriptor.<com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroup, com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroupResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MoveUserGroup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroup.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroupResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("MoveUserGroup"))
              .build();
        }
      }
    }
    return getMoveUserGroupMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissions,
      com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissionsResponse> getSetUserGroupPermissionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetUserGroupPermissions",
      requestType = com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissions.class,
      responseType = com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissions,
      com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissionsResponse> getSetUserGroupPermissionsMethod() {
    io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissions, com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissionsResponse> getSetUserGroupPermissionsMethod;
    if ((getSetUserGroupPermissionsMethod = MsgGrpc.getSetUserGroupPermissionsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetUserGroupPermissionsMethod = MsgGrpc.getSetUserGroupPermissionsMethod) == null) {
          MsgGrpc.getSetUserGroupPermissionsMethod = getSetUserGroupPermissionsMethod =
              io.grpc.MethodDescriptor.<com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissions, com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetUserGroupPermissions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissions.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetUserGroupPermissions"))
              .build();
        }
      }
    }
    return getSetUserGroupPermissionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroup,
      com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroupResponse> getDeleteUserGroupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteUserGroup",
      requestType = com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroup.class,
      responseType = com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroupResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroup,
      com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroupResponse> getDeleteUserGroupMethod() {
    io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroup, com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroupResponse> getDeleteUserGroupMethod;
    if ((getDeleteUserGroupMethod = MsgGrpc.getDeleteUserGroupMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteUserGroupMethod = MsgGrpc.getDeleteUserGroupMethod) == null) {
          MsgGrpc.getDeleteUserGroupMethod = getDeleteUserGroupMethod =
              io.grpc.MethodDescriptor.<com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroup, com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroupResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteUserGroup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroup.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroupResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteUserGroup"))
              .build();
        }
      }
    }
    return getDeleteUserGroupMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroup,
      com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroupResponse> getAddUserToUserGroupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddUserToUserGroup",
      requestType = com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroup.class,
      responseType = com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroupResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroup,
      com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroupResponse> getAddUserToUserGroupMethod() {
    io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroup, com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroupResponse> getAddUserToUserGroupMethod;
    if ((getAddUserToUserGroupMethod = MsgGrpc.getAddUserToUserGroupMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getAddUserToUserGroupMethod = MsgGrpc.getAddUserToUserGroupMethod) == null) {
          MsgGrpc.getAddUserToUserGroupMethod = getAddUserToUserGroupMethod =
              io.grpc.MethodDescriptor.<com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroup, com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroupResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddUserToUserGroup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroup.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroupResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("AddUserToUserGroup"))
              .build();
        }
      }
    }
    return getAddUserToUserGroupMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroup,
      com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroupResponse> getRemoveUserFromUserGroupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveUserFromUserGroup",
      requestType = com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroup.class,
      responseType = com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroupResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroup,
      com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroupResponse> getRemoveUserFromUserGroupMethod() {
    io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroup, com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroupResponse> getRemoveUserFromUserGroupMethod;
    if ((getRemoveUserFromUserGroupMethod = MsgGrpc.getRemoveUserFromUserGroupMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRemoveUserFromUserGroupMethod = MsgGrpc.getRemoveUserFromUserGroupMethod) == null) {
          MsgGrpc.getRemoveUserFromUserGroupMethod = getRemoveUserFromUserGroupMethod =
              io.grpc.MethodDescriptor.<com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroup, com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroupResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemoveUserFromUserGroup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroup.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroupResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RemoveUserFromUserGroup"))
              .build();
        }
      }
    }
    return getRemoveUserFromUserGroupMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissions,
      com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissionsResponse> getSetUserPermissionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetUserPermissions",
      requestType = com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissions.class,
      responseType = com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissions,
      com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissionsResponse> getSetUserPermissionsMethod() {
    io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissions, com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissionsResponse> getSetUserPermissionsMethod;
    if ((getSetUserPermissionsMethod = MsgGrpc.getSetUserPermissionsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetUserPermissionsMethod = MsgGrpc.getSetUserPermissionsMethod) == null) {
          MsgGrpc.getSetUserPermissionsMethod = getSetUserPermissionsMethod =
              io.grpc.MethodDescriptor.<com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissions, com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetUserPermissions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissions.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetUserPermissions"))
              .build();
        }
      }
    }
    return getSetUserPermissionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorization,
      com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorizationResponse> getGrantTreasuryAuthorizationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GrantTreasuryAuthorization",
      requestType = com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorization.class,
      responseType = com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorizationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorization,
      com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorizationResponse> getGrantTreasuryAuthorizationMethod() {
    io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorization, com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorizationResponse> getGrantTreasuryAuthorizationMethod;
    if ((getGrantTreasuryAuthorizationMethod = MsgGrpc.getGrantTreasuryAuthorizationMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getGrantTreasuryAuthorizationMethod = MsgGrpc.getGrantTreasuryAuthorizationMethod) == null) {
          MsgGrpc.getGrantTreasuryAuthorizationMethod = getGrantTreasuryAuthorizationMethod =
              io.grpc.MethodDescriptor.<com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorization, com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorizationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GrantTreasuryAuthorization"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorization.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorizationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("GrantTreasuryAuthorization"))
              .build();
        }
      }
    }
    return getGrantTreasuryAuthorizationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorization,
      com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorizationResponse> getRevokeTreasuryAuthorizationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RevokeTreasuryAuthorization",
      requestType = com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorization.class,
      responseType = com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorizationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorization,
      com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorizationResponse> getRevokeTreasuryAuthorizationMethod() {
    io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorization, com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorizationResponse> getRevokeTreasuryAuthorizationMethod;
    if ((getRevokeTreasuryAuthorizationMethod = MsgGrpc.getRevokeTreasuryAuthorizationMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRevokeTreasuryAuthorizationMethod = MsgGrpc.getRevokeTreasuryAuthorizationMethod) == null) {
          MsgGrpc.getRevokeTreasuryAuthorizationMethod = getRevokeTreasuryAuthorizationMethod =
              io.grpc.MethodDescriptor.<com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorization, com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorizationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RevokeTreasuryAuthorization"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorization.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorizationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RevokeTreasuryAuthorization"))
              .build();
        }
      }
    }
    return getRevokeTreasuryAuthorizationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowance,
      com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowanceResponse> getGrantAllowanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GrantAllowance",
      requestType = com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowance.class,
      responseType = com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowance,
      com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowanceResponse> getGrantAllowanceMethod() {
    io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowance, com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowanceResponse> getGrantAllowanceMethod;
    if ((getGrantAllowanceMethod = MsgGrpc.getGrantAllowanceMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getGrantAllowanceMethod = MsgGrpc.getGrantAllowanceMethod) == null) {
          MsgGrpc.getGrantAllowanceMethod = getGrantAllowanceMethod =
              io.grpc.MethodDescriptor.<com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowance, com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GrantAllowance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowance.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("GrantAllowance"))
              .build();
        }
      }
    }
    return getGrantAllowanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowance,
      com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowanceResponse> getRevokeAllowanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RevokeAllowance",
      requestType = com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowance.class,
      responseType = com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowance,
      com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowanceResponse> getRevokeAllowanceMethod() {
    io.grpc.MethodDescriptor<com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowance, com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowanceResponse> getRevokeAllowanceMethod;
    if ((getRevokeAllowanceMethod = MsgGrpc.getRevokeAllowanceMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRevokeAllowanceMethod = MsgGrpc.getRevokeAllowanceMethod) == null) {
          MsgGrpc.getRevokeAllowanceMethod = getRevokeAllowanceMethod =
              io.grpc.MethodDescriptor.<com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowance, com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RevokeAllowance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowance.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RevokeAllowance"))
              .build();
        }
      }
    }
    return getRevokeAllowanceMethod;
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
  public interface AsyncService {

    /**
     * <pre>
     * CreateSubspace allows to create a subspace
     * </pre>
     */
    default void createSubspace(com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspace request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspaceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateSubspaceMethod(), responseObserver);
    }

    /**
     * <pre>
     * EditSubspace allows to edit a subspace
     * </pre>
     */
    default void editSubspace(com.desmos.subspaces.v3.MsgsProto.MsgEditSubspace request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgEditSubspaceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEditSubspaceMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteSubspace allows to delete a subspace
     * </pre>
     */
    default void deleteSubspace(com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspace request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspaceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteSubspaceMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateSection allows to create a new subspace section
     * </pre>
     */
    default void createSection(com.desmos.subspaces.v3.MsgsProto.MsgCreateSection request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgCreateSectionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateSectionMethod(), responseObserver);
    }

    /**
     * <pre>
     * EditSection allows to edit an existing section
     * </pre>
     */
    default void editSection(com.desmos.subspaces.v3.MsgsProto.MsgEditSection request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgEditSectionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEditSectionMethod(), responseObserver);
    }

    /**
     * <pre>
     * MoveSection allows to move an existing section to another parent
     * </pre>
     */
    default void moveSection(com.desmos.subspaces.v3.MsgsProto.MsgMoveSection request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgMoveSectionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMoveSectionMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteSection allows to delete an existing section
     * </pre>
     */
    default void deleteSection(com.desmos.subspaces.v3.MsgsProto.MsgDeleteSection request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgDeleteSectionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteSectionMethod(), responseObserver);
    }

    /**
     * <pre>
     * CreateUserGroup allows to create a user group
     * </pre>
     */
    default void createUserGroup(com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroup request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroupResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateUserGroupMethod(), responseObserver);
    }

    /**
     * <pre>
     * EditUserGroup allows to edit a user group
     * </pre>
     */
    default void editUserGroup(com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroup request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroupResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEditUserGroupMethod(), responseObserver);
    }

    /**
     * <pre>
     * MoveUserGroup allows to move a user group from a section to another
     * </pre>
     */
    default void moveUserGroup(com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroup request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroupResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMoveUserGroupMethod(), responseObserver);
    }

    /**
     * <pre>
     * SetUserGroupPermissions allows to set the permissions for a specific group
     * </pre>
     */
    default void setUserGroupPermissions(com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissions request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissionsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetUserGroupPermissionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * DeleteUserGroup allows to delete an existing user group
     * </pre>
     */
    default void deleteUserGroup(com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroup request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroupResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteUserGroupMethod(), responseObserver);
    }

    /**
     * <pre>
     * AddUserToUserGroup allows to add a specific user to a specific user group
     * </pre>
     */
    default void addUserToUserGroup(com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroup request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroupResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddUserToUserGroupMethod(), responseObserver);
    }

    /**
     * <pre>
     * RemoveUserFromUserGroup allows to remove a specific user from a specific
     * user group
     * </pre>
     */
    default void removeUserFromUserGroup(com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroup request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroupResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRemoveUserFromUserGroupMethod(), responseObserver);
    }

    /**
     * <pre>
     * SetUserPermissions allows to set the permissions for a specific user
     * </pre>
     */
    default void setUserPermissions(com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissions request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissionsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetUserPermissionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * GrantTreasuryAuthorization allows managers who have the permission to grant
     * a treasury authorization to a user
     * </pre>
     */
    default void grantTreasuryAuthorization(com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorization request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorizationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGrantTreasuryAuthorizationMethod(), responseObserver);
    }

    /**
     * <pre>
     * RevokeTreasuryAuthorization allows managers who have the permission to
     * revoke an existing treasury authorization
     * </pre>
     */
    default void revokeTreasuryAuthorization(com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorization request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorizationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRevokeTreasuryAuthorizationMethod(), responseObserver);
    }

    /**
     * <pre>
     * GrantAllowance allows the granter to grant a fee allowance to the
     * grantee
     * </pre>
     */
    default void grantAllowance(com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowance request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowanceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGrantAllowanceMethod(), responseObserver);
    }

    /**
     * <pre>
     * RevokeAllowance allows a granter to revoke any existing treasury allowance
     * that has been granted to the grantee
     * </pre>
     */
    default void revokeAllowance(com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowance request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowanceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRevokeAllowanceMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines subspaces Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MsgGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Msg.
   * <pre>
   * Msg defines subspaces Msg service.
   * </pre>
   */
  public static final class MsgStub
      extends io.grpc.stub.AbstractAsyncStub<MsgStub> {
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
     * CreateSubspace allows to create a subspace
     * </pre>
     */
    public void createSubspace(com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspace request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspaceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateSubspaceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EditSubspace allows to edit a subspace
     * </pre>
     */
    public void editSubspace(com.desmos.subspaces.v3.MsgsProto.MsgEditSubspace request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgEditSubspaceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEditSubspaceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteSubspace allows to delete a subspace
     * </pre>
     */
    public void deleteSubspace(com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspace request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspaceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteSubspaceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateSection allows to create a new subspace section
     * </pre>
     */
    public void createSection(com.desmos.subspaces.v3.MsgsProto.MsgCreateSection request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgCreateSectionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateSectionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EditSection allows to edit an existing section
     * </pre>
     */
    public void editSection(com.desmos.subspaces.v3.MsgsProto.MsgEditSection request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgEditSectionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEditSectionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * MoveSection allows to move an existing section to another parent
     * </pre>
     */
    public void moveSection(com.desmos.subspaces.v3.MsgsProto.MsgMoveSection request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgMoveSectionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMoveSectionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteSection allows to delete an existing section
     * </pre>
     */
    public void deleteSection(com.desmos.subspaces.v3.MsgsProto.MsgDeleteSection request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgDeleteSectionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteSectionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * CreateUserGroup allows to create a user group
     * </pre>
     */
    public void createUserGroup(com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroup request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroupResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateUserGroupMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EditUserGroup allows to edit a user group
     * </pre>
     */
    public void editUserGroup(com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroup request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroupResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEditUserGroupMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * MoveUserGroup allows to move a user group from a section to another
     * </pre>
     */
    public void moveUserGroup(com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroup request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroupResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMoveUserGroupMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SetUserGroupPermissions allows to set the permissions for a specific group
     * </pre>
     */
    public void setUserGroupPermissions(com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissions request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissionsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetUserGroupPermissionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DeleteUserGroup allows to delete an existing user group
     * </pre>
     */
    public void deleteUserGroup(com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroup request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroupResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteUserGroupMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * AddUserToUserGroup allows to add a specific user to a specific user group
     * </pre>
     */
    public void addUserToUserGroup(com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroup request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroupResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddUserToUserGroupMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RemoveUserFromUserGroup allows to remove a specific user from a specific
     * user group
     * </pre>
     */
    public void removeUserFromUserGroup(com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroup request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroupResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRemoveUserFromUserGroupMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SetUserPermissions allows to set the permissions for a specific user
     * </pre>
     */
    public void setUserPermissions(com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissions request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissionsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetUserPermissionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GrantTreasuryAuthorization allows managers who have the permission to grant
     * a treasury authorization to a user
     * </pre>
     */
    public void grantTreasuryAuthorization(com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorization request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorizationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGrantTreasuryAuthorizationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RevokeTreasuryAuthorization allows managers who have the permission to
     * revoke an existing treasury authorization
     * </pre>
     */
    public void revokeTreasuryAuthorization(com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorization request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorizationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRevokeTreasuryAuthorizationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GrantAllowance allows the granter to grant a fee allowance to the
     * grantee
     * </pre>
     */
    public void grantAllowance(com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowance request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowanceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGrantAllowanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RevokeAllowance allows a granter to revoke any existing treasury allowance
     * that has been granted to the grantee
     * </pre>
     */
    public void revokeAllowance(com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowance request,
        io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowanceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRevokeAllowanceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines subspaces Msg service.
   * </pre>
   */
  public static final class MsgBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<MsgBlockingStub> {
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
     * CreateSubspace allows to create a subspace
     * </pre>
     */
    public com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspaceResponse createSubspace(com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspace request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateSubspaceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EditSubspace allows to edit a subspace
     * </pre>
     */
    public com.desmos.subspaces.v3.MsgsProto.MsgEditSubspaceResponse editSubspace(com.desmos.subspaces.v3.MsgsProto.MsgEditSubspace request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEditSubspaceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteSubspace allows to delete a subspace
     * </pre>
     */
    public com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspaceResponse deleteSubspace(com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspace request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteSubspaceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateSection allows to create a new subspace section
     * </pre>
     */
    public com.desmos.subspaces.v3.MsgsProto.MsgCreateSectionResponse createSection(com.desmos.subspaces.v3.MsgsProto.MsgCreateSection request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateSectionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EditSection allows to edit an existing section
     * </pre>
     */
    public com.desmos.subspaces.v3.MsgsProto.MsgEditSectionResponse editSection(com.desmos.subspaces.v3.MsgsProto.MsgEditSection request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEditSectionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * MoveSection allows to move an existing section to another parent
     * </pre>
     */
    public com.desmos.subspaces.v3.MsgsProto.MsgMoveSectionResponse moveSection(com.desmos.subspaces.v3.MsgsProto.MsgMoveSection request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMoveSectionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteSection allows to delete an existing section
     * </pre>
     */
    public com.desmos.subspaces.v3.MsgsProto.MsgDeleteSectionResponse deleteSection(com.desmos.subspaces.v3.MsgsProto.MsgDeleteSection request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteSectionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * CreateUserGroup allows to create a user group
     * </pre>
     */
    public com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroupResponse createUserGroup(com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroup request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateUserGroupMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EditUserGroup allows to edit a user group
     * </pre>
     */
    public com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroupResponse editUserGroup(com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroup request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEditUserGroupMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * MoveUserGroup allows to move a user group from a section to another
     * </pre>
     */
    public com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroupResponse moveUserGroup(com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroup request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMoveUserGroupMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SetUserGroupPermissions allows to set the permissions for a specific group
     * </pre>
     */
    public com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissionsResponse setUserGroupPermissions(com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissions request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetUserGroupPermissionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DeleteUserGroup allows to delete an existing user group
     * </pre>
     */
    public com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroupResponse deleteUserGroup(com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroup request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteUserGroupMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * AddUserToUserGroup allows to add a specific user to a specific user group
     * </pre>
     */
    public com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroupResponse addUserToUserGroup(com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroup request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddUserToUserGroupMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RemoveUserFromUserGroup allows to remove a specific user from a specific
     * user group
     * </pre>
     */
    public com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroupResponse removeUserFromUserGroup(com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroup request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRemoveUserFromUserGroupMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SetUserPermissions allows to set the permissions for a specific user
     * </pre>
     */
    public com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissionsResponse setUserPermissions(com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissions request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetUserPermissionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GrantTreasuryAuthorization allows managers who have the permission to grant
     * a treasury authorization to a user
     * </pre>
     */
    public com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorizationResponse grantTreasuryAuthorization(com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorization request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGrantTreasuryAuthorizationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RevokeTreasuryAuthorization allows managers who have the permission to
     * revoke an existing treasury authorization
     * </pre>
     */
    public com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorizationResponse revokeTreasuryAuthorization(com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorization request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRevokeTreasuryAuthorizationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * GrantAllowance allows the granter to grant a fee allowance to the
     * grantee
     * </pre>
     */
    public com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowanceResponse grantAllowance(com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowance request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGrantAllowanceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RevokeAllowance allows a granter to revoke any existing treasury allowance
     * that has been granted to the grantee
     * </pre>
     */
    public com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowanceResponse revokeAllowance(com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowance request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRevokeAllowanceMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines subspaces Msg service.
   * </pre>
   */
  public static final class MsgFutureStub
      extends io.grpc.stub.AbstractFutureStub<MsgFutureStub> {
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
     * CreateSubspace allows to create a subspace
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspaceResponse> createSubspace(
        com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspace request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateSubspaceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EditSubspace allows to edit a subspace
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.subspaces.v3.MsgsProto.MsgEditSubspaceResponse> editSubspace(
        com.desmos.subspaces.v3.MsgsProto.MsgEditSubspace request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEditSubspaceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteSubspace allows to delete a subspace
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspaceResponse> deleteSubspace(
        com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspace request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteSubspaceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateSection allows to create a new subspace section
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.subspaces.v3.MsgsProto.MsgCreateSectionResponse> createSection(
        com.desmos.subspaces.v3.MsgsProto.MsgCreateSection request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateSectionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EditSection allows to edit an existing section
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.subspaces.v3.MsgsProto.MsgEditSectionResponse> editSection(
        com.desmos.subspaces.v3.MsgsProto.MsgEditSection request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEditSectionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * MoveSection allows to move an existing section to another parent
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.subspaces.v3.MsgsProto.MsgMoveSectionResponse> moveSection(
        com.desmos.subspaces.v3.MsgsProto.MsgMoveSection request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMoveSectionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteSection allows to delete an existing section
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.subspaces.v3.MsgsProto.MsgDeleteSectionResponse> deleteSection(
        com.desmos.subspaces.v3.MsgsProto.MsgDeleteSection request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteSectionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * CreateUserGroup allows to create a user group
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroupResponse> createUserGroup(
        com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroup request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateUserGroupMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EditUserGroup allows to edit a user group
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroupResponse> editUserGroup(
        com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroup request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEditUserGroupMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * MoveUserGroup allows to move a user group from a section to another
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroupResponse> moveUserGroup(
        com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroup request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMoveUserGroupMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SetUserGroupPermissions allows to set the permissions for a specific group
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissionsResponse> setUserGroupPermissions(
        com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissions request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetUserGroupPermissionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DeleteUserGroup allows to delete an existing user group
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroupResponse> deleteUserGroup(
        com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroup request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteUserGroupMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * AddUserToUserGroup allows to add a specific user to a specific user group
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroupResponse> addUserToUserGroup(
        com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroup request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddUserToUserGroupMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RemoveUserFromUserGroup allows to remove a specific user from a specific
     * user group
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroupResponse> removeUserFromUserGroup(
        com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroup request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRemoveUserFromUserGroupMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SetUserPermissions allows to set the permissions for a specific user
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissionsResponse> setUserPermissions(
        com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissions request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetUserPermissionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GrantTreasuryAuthorization allows managers who have the permission to grant
     * a treasury authorization to a user
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorizationResponse> grantTreasuryAuthorization(
        com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorization request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGrantTreasuryAuthorizationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RevokeTreasuryAuthorization allows managers who have the permission to
     * revoke an existing treasury authorization
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorizationResponse> revokeTreasuryAuthorization(
        com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorization request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRevokeTreasuryAuthorizationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * GrantAllowance allows the granter to grant a fee allowance to the
     * grantee
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowanceResponse> grantAllowance(
        com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowance request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGrantAllowanceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RevokeAllowance allows a granter to revoke any existing treasury allowance
     * that has been granted to the grantee
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowanceResponse> revokeAllowance(
        com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowance request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRevokeAllowanceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_SUBSPACE = 0;
  private static final int METHODID_EDIT_SUBSPACE = 1;
  private static final int METHODID_DELETE_SUBSPACE = 2;
  private static final int METHODID_CREATE_SECTION = 3;
  private static final int METHODID_EDIT_SECTION = 4;
  private static final int METHODID_MOVE_SECTION = 5;
  private static final int METHODID_DELETE_SECTION = 6;
  private static final int METHODID_CREATE_USER_GROUP = 7;
  private static final int METHODID_EDIT_USER_GROUP = 8;
  private static final int METHODID_MOVE_USER_GROUP = 9;
  private static final int METHODID_SET_USER_GROUP_PERMISSIONS = 10;
  private static final int METHODID_DELETE_USER_GROUP = 11;
  private static final int METHODID_ADD_USER_TO_USER_GROUP = 12;
  private static final int METHODID_REMOVE_USER_FROM_USER_GROUP = 13;
  private static final int METHODID_SET_USER_PERMISSIONS = 14;
  private static final int METHODID_GRANT_TREASURY_AUTHORIZATION = 15;
  private static final int METHODID_REVOKE_TREASURY_AUTHORIZATION = 16;
  private static final int METHODID_GRANT_ALLOWANCE = 17;
  private static final int METHODID_REVOKE_ALLOWANCE = 18;

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
        case METHODID_CREATE_SUBSPACE:
          serviceImpl.createSubspace((com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspace) request,
              (io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspaceResponse>) responseObserver);
          break;
        case METHODID_EDIT_SUBSPACE:
          serviceImpl.editSubspace((com.desmos.subspaces.v3.MsgsProto.MsgEditSubspace) request,
              (io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgEditSubspaceResponse>) responseObserver);
          break;
        case METHODID_DELETE_SUBSPACE:
          serviceImpl.deleteSubspace((com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspace) request,
              (io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspaceResponse>) responseObserver);
          break;
        case METHODID_CREATE_SECTION:
          serviceImpl.createSection((com.desmos.subspaces.v3.MsgsProto.MsgCreateSection) request,
              (io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgCreateSectionResponse>) responseObserver);
          break;
        case METHODID_EDIT_SECTION:
          serviceImpl.editSection((com.desmos.subspaces.v3.MsgsProto.MsgEditSection) request,
              (io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgEditSectionResponse>) responseObserver);
          break;
        case METHODID_MOVE_SECTION:
          serviceImpl.moveSection((com.desmos.subspaces.v3.MsgsProto.MsgMoveSection) request,
              (io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgMoveSectionResponse>) responseObserver);
          break;
        case METHODID_DELETE_SECTION:
          serviceImpl.deleteSection((com.desmos.subspaces.v3.MsgsProto.MsgDeleteSection) request,
              (io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgDeleteSectionResponse>) responseObserver);
          break;
        case METHODID_CREATE_USER_GROUP:
          serviceImpl.createUserGroup((com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroup) request,
              (io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroupResponse>) responseObserver);
          break;
        case METHODID_EDIT_USER_GROUP:
          serviceImpl.editUserGroup((com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroup) request,
              (io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroupResponse>) responseObserver);
          break;
        case METHODID_MOVE_USER_GROUP:
          serviceImpl.moveUserGroup((com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroup) request,
              (io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroupResponse>) responseObserver);
          break;
        case METHODID_SET_USER_GROUP_PERMISSIONS:
          serviceImpl.setUserGroupPermissions((com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissions) request,
              (io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissionsResponse>) responseObserver);
          break;
        case METHODID_DELETE_USER_GROUP:
          serviceImpl.deleteUserGroup((com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroup) request,
              (io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroupResponse>) responseObserver);
          break;
        case METHODID_ADD_USER_TO_USER_GROUP:
          serviceImpl.addUserToUserGroup((com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroup) request,
              (io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroupResponse>) responseObserver);
          break;
        case METHODID_REMOVE_USER_FROM_USER_GROUP:
          serviceImpl.removeUserFromUserGroup((com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroup) request,
              (io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroupResponse>) responseObserver);
          break;
        case METHODID_SET_USER_PERMISSIONS:
          serviceImpl.setUserPermissions((com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissions) request,
              (io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissionsResponse>) responseObserver);
          break;
        case METHODID_GRANT_TREASURY_AUTHORIZATION:
          serviceImpl.grantTreasuryAuthorization((com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorization) request,
              (io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorizationResponse>) responseObserver);
          break;
        case METHODID_REVOKE_TREASURY_AUTHORIZATION:
          serviceImpl.revokeTreasuryAuthorization((com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorization) request,
              (io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorizationResponse>) responseObserver);
          break;
        case METHODID_GRANT_ALLOWANCE:
          serviceImpl.grantAllowance((com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowance) request,
              (io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowanceResponse>) responseObserver);
          break;
        case METHODID_REVOKE_ALLOWANCE:
          serviceImpl.revokeAllowance((com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowance) request,
              (io.grpc.stub.StreamObserver<com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowanceResponse>) responseObserver);
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
          getCreateSubspaceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspace,
              com.desmos.subspaces.v3.MsgsProto.MsgCreateSubspaceResponse>(
                service, METHODID_CREATE_SUBSPACE)))
        .addMethod(
          getEditSubspaceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.subspaces.v3.MsgsProto.MsgEditSubspace,
              com.desmos.subspaces.v3.MsgsProto.MsgEditSubspaceResponse>(
                service, METHODID_EDIT_SUBSPACE)))
        .addMethod(
          getDeleteSubspaceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspace,
              com.desmos.subspaces.v3.MsgsProto.MsgDeleteSubspaceResponse>(
                service, METHODID_DELETE_SUBSPACE)))
        .addMethod(
          getCreateSectionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.subspaces.v3.MsgsProto.MsgCreateSection,
              com.desmos.subspaces.v3.MsgsProto.MsgCreateSectionResponse>(
                service, METHODID_CREATE_SECTION)))
        .addMethod(
          getEditSectionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.subspaces.v3.MsgsProto.MsgEditSection,
              com.desmos.subspaces.v3.MsgsProto.MsgEditSectionResponse>(
                service, METHODID_EDIT_SECTION)))
        .addMethod(
          getMoveSectionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.subspaces.v3.MsgsProto.MsgMoveSection,
              com.desmos.subspaces.v3.MsgsProto.MsgMoveSectionResponse>(
                service, METHODID_MOVE_SECTION)))
        .addMethod(
          getDeleteSectionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.subspaces.v3.MsgsProto.MsgDeleteSection,
              com.desmos.subspaces.v3.MsgsProto.MsgDeleteSectionResponse>(
                service, METHODID_DELETE_SECTION)))
        .addMethod(
          getCreateUserGroupMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroup,
              com.desmos.subspaces.v3.MsgsProto.MsgCreateUserGroupResponse>(
                service, METHODID_CREATE_USER_GROUP)))
        .addMethod(
          getEditUserGroupMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroup,
              com.desmos.subspaces.v3.MsgsProto.MsgEditUserGroupResponse>(
                service, METHODID_EDIT_USER_GROUP)))
        .addMethod(
          getMoveUserGroupMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroup,
              com.desmos.subspaces.v3.MsgsProto.MsgMoveUserGroupResponse>(
                service, METHODID_MOVE_USER_GROUP)))
        .addMethod(
          getSetUserGroupPermissionsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissions,
              com.desmos.subspaces.v3.MsgsProto.MsgSetUserGroupPermissionsResponse>(
                service, METHODID_SET_USER_GROUP_PERMISSIONS)))
        .addMethod(
          getDeleteUserGroupMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroup,
              com.desmos.subspaces.v3.MsgsProto.MsgDeleteUserGroupResponse>(
                service, METHODID_DELETE_USER_GROUP)))
        .addMethod(
          getAddUserToUserGroupMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroup,
              com.desmos.subspaces.v3.MsgsProto.MsgAddUserToUserGroupResponse>(
                service, METHODID_ADD_USER_TO_USER_GROUP)))
        .addMethod(
          getRemoveUserFromUserGroupMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroup,
              com.desmos.subspaces.v3.MsgsProto.MsgRemoveUserFromUserGroupResponse>(
                service, METHODID_REMOVE_USER_FROM_USER_GROUP)))
        .addMethod(
          getSetUserPermissionsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissions,
              com.desmos.subspaces.v3.MsgsProto.MsgSetUserPermissionsResponse>(
                service, METHODID_SET_USER_PERMISSIONS)))
        .addMethod(
          getGrantTreasuryAuthorizationMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorization,
              com.desmos.subspaces.v3.MsgsTreasuryProto.MsgGrantTreasuryAuthorizationResponse>(
                service, METHODID_GRANT_TREASURY_AUTHORIZATION)))
        .addMethod(
          getRevokeTreasuryAuthorizationMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorization,
              com.desmos.subspaces.v3.MsgsTreasuryProto.MsgRevokeTreasuryAuthorizationResponse>(
                service, METHODID_REVOKE_TREASURY_AUTHORIZATION)))
        .addMethod(
          getGrantAllowanceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowance,
              com.desmos.subspaces.v3.MsgsFeegrantProto.MsgGrantAllowanceResponse>(
                service, METHODID_GRANT_ALLOWANCE)))
        .addMethod(
          getRevokeAllowanceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowance,
              com.desmos.subspaces.v3.MsgsFeegrantProto.MsgRevokeAllowanceResponse>(
                service, METHODID_REVOKE_ALLOWANCE)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.desmos.subspaces.v3.MsgsProto.getDescriptor();
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
              .addMethod(getDeleteSubspaceMethod())
              .addMethod(getCreateSectionMethod())
              .addMethod(getEditSectionMethod())
              .addMethod(getMoveSectionMethod())
              .addMethod(getDeleteSectionMethod())
              .addMethod(getCreateUserGroupMethod())
              .addMethod(getEditUserGroupMethod())
              .addMethod(getMoveUserGroupMethod())
              .addMethod(getSetUserGroupPermissionsMethod())
              .addMethod(getDeleteUserGroupMethod())
              .addMethod(getAddUserToUserGroupMethod())
              .addMethod(getRemoveUserFromUserGroupMethod())
              .addMethod(getSetUserPermissionsMethod())
              .addMethod(getGrantTreasuryAuthorizationMethod())
              .addMethod(getRevokeTreasuryAuthorizationMethod())
              .addMethod(getGrantAllowanceMethod())
              .addMethod(getRevokeAllowanceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
