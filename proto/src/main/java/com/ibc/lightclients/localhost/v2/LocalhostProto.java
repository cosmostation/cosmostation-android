// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ibc/lightclients/localhost/v2/localhost.proto

package com.ibc.lightclients.localhost.v2;

public final class LocalhostProto {
  private LocalhostProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ClientStateOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ibc.lightclients.localhost.v2.ClientState)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * the latest block height
     * </pre>
     *
     * <code>.ibc.core.client.v1.Height latest_height = 1 [json_name = "latestHeight", (.gogoproto.nullable) = false];</code>
     * @return Whether the latestHeight field is set.
     */
    boolean hasLatestHeight();
    /**
     * <pre>
     * the latest block height
     * </pre>
     *
     * <code>.ibc.core.client.v1.Height latest_height = 1 [json_name = "latestHeight", (.gogoproto.nullable) = false];</code>
     * @return The latestHeight.
     */
    com.ibc.core.client.v1.ClientProto.Height getLatestHeight();
    /**
     * <pre>
     * the latest block height
     * </pre>
     *
     * <code>.ibc.core.client.v1.Height latest_height = 1 [json_name = "latestHeight", (.gogoproto.nullable) = false];</code>
     */
    com.ibc.core.client.v1.ClientProto.HeightOrBuilder getLatestHeightOrBuilder();
  }
  /**
   * <pre>
   * ClientState defines the 09-localhost client state
   * </pre>
   *
   * Protobuf type {@code ibc.lightclients.localhost.v2.ClientState}
   */
  public static final class ClientState extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:ibc.lightclients.localhost.v2.ClientState)
      ClientStateOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ClientState.newBuilder() to construct.
    private ClientState(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ClientState() {
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new ClientState();
    }

    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.ibc.lightclients.localhost.v2.LocalhostProto.internal_static_ibc_lightclients_localhost_v2_ClientState_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.ibc.lightclients.localhost.v2.LocalhostProto.internal_static_ibc_lightclients_localhost_v2_ClientState_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState.class, com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState.Builder.class);
    }

    public static final int LATEST_HEIGHT_FIELD_NUMBER = 1;
    private com.ibc.core.client.v1.ClientProto.Height latestHeight_;
    /**
     * <pre>
     * the latest block height
     * </pre>
     *
     * <code>.ibc.core.client.v1.Height latest_height = 1 [json_name = "latestHeight", (.gogoproto.nullable) = false];</code>
     * @return Whether the latestHeight field is set.
     */
    @java.lang.Override
    public boolean hasLatestHeight() {
      return latestHeight_ != null;
    }
    /**
     * <pre>
     * the latest block height
     * </pre>
     *
     * <code>.ibc.core.client.v1.Height latest_height = 1 [json_name = "latestHeight", (.gogoproto.nullable) = false];</code>
     * @return The latestHeight.
     */
    @java.lang.Override
    public com.ibc.core.client.v1.ClientProto.Height getLatestHeight() {
      return latestHeight_ == null ? com.ibc.core.client.v1.ClientProto.Height.getDefaultInstance() : latestHeight_;
    }
    /**
     * <pre>
     * the latest block height
     * </pre>
     *
     * <code>.ibc.core.client.v1.Height latest_height = 1 [json_name = "latestHeight", (.gogoproto.nullable) = false];</code>
     */
    @java.lang.Override
    public com.ibc.core.client.v1.ClientProto.HeightOrBuilder getLatestHeightOrBuilder() {
      return latestHeight_ == null ? com.ibc.core.client.v1.ClientProto.Height.getDefaultInstance() : latestHeight_;
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (latestHeight_ != null) {
        output.writeMessage(1, getLatestHeight());
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (latestHeight_ != null) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, getLatestHeight());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState)) {
        return super.equals(obj);
      }
      com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState other = (com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState) obj;

      if (hasLatestHeight() != other.hasLatestHeight()) return false;
      if (hasLatestHeight()) {
        if (!getLatestHeight()
            .equals(other.getLatestHeight())) return false;
      }
      if (!getUnknownFields().equals(other.getUnknownFields())) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasLatestHeight()) {
        hash = (37 * hash) + LATEST_HEIGHT_FIELD_NUMBER;
        hash = (53 * hash) + getLatestHeight().hashCode();
      }
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }

    public static com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * <pre>
     * ClientState defines the 09-localhost client state
     * </pre>
     *
     * Protobuf type {@code ibc.lightclients.localhost.v2.ClientState}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ibc.lightclients.localhost.v2.ClientState)
        com.ibc.lightclients.localhost.v2.LocalhostProto.ClientStateOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.ibc.lightclients.localhost.v2.LocalhostProto.internal_static_ibc_lightclients_localhost_v2_ClientState_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.ibc.lightclients.localhost.v2.LocalhostProto.internal_static_ibc_lightclients_localhost_v2_ClientState_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState.class, com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState.Builder.class);
      }

      // Construct using com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState.newBuilder()
      private Builder() {

      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);

      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        bitField0_ = 0;
        latestHeight_ = null;
        if (latestHeightBuilder_ != null) {
          latestHeightBuilder_.dispose();
          latestHeightBuilder_ = null;
        }
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.ibc.lightclients.localhost.v2.LocalhostProto.internal_static_ibc_lightclients_localhost_v2_ClientState_descriptor;
      }

      @java.lang.Override
      public com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState getDefaultInstanceForType() {
        return com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState.getDefaultInstance();
      }

      @java.lang.Override
      public com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState build() {
        com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState buildPartial() {
        com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState result = new com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState result) {
        int from_bitField0_ = bitField0_;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.latestHeight_ = latestHeightBuilder_ == null
              ? latestHeight_
              : latestHeightBuilder_.build();
        }
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState) {
          return mergeFrom((com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState other) {
        if (other == com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState.getDefaultInstance()) return this;
        if (other.hasLatestHeight()) {
          mergeLatestHeight(other.getLatestHeight());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        if (extensionRegistry == null) {
          throw new java.lang.NullPointerException();
        }
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              case 10: {
                input.readMessage(
                    getLatestHeightFieldBuilder().getBuilder(),
                    extensionRegistry);
                bitField0_ |= 0x00000001;
                break;
              } // case 10
              default: {
                if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                  done = true; // was an endgroup tag
                }
                break;
              } // default:
            } // switch (tag)
          } // while (!done)
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.unwrapIOException();
        } finally {
          onChanged();
        } // finally
        return this;
      }
      private int bitField0_;

      private com.ibc.core.client.v1.ClientProto.Height latestHeight_;
      private com.google.protobuf.SingleFieldBuilderV3<
          com.ibc.core.client.v1.ClientProto.Height, com.ibc.core.client.v1.ClientProto.Height.Builder, com.ibc.core.client.v1.ClientProto.HeightOrBuilder> latestHeightBuilder_;
      /**
       * <pre>
       * the latest block height
       * </pre>
       *
       * <code>.ibc.core.client.v1.Height latest_height = 1 [json_name = "latestHeight", (.gogoproto.nullable) = false];</code>
       * @return Whether the latestHeight field is set.
       */
      public boolean hasLatestHeight() {
        return ((bitField0_ & 0x00000001) != 0);
      }
      /**
       * <pre>
       * the latest block height
       * </pre>
       *
       * <code>.ibc.core.client.v1.Height latest_height = 1 [json_name = "latestHeight", (.gogoproto.nullable) = false];</code>
       * @return The latestHeight.
       */
      public com.ibc.core.client.v1.ClientProto.Height getLatestHeight() {
        if (latestHeightBuilder_ == null) {
          return latestHeight_ == null ? com.ibc.core.client.v1.ClientProto.Height.getDefaultInstance() : latestHeight_;
        } else {
          return latestHeightBuilder_.getMessage();
        }
      }
      /**
       * <pre>
       * the latest block height
       * </pre>
       *
       * <code>.ibc.core.client.v1.Height latest_height = 1 [json_name = "latestHeight", (.gogoproto.nullable) = false];</code>
       */
      public Builder setLatestHeight(com.ibc.core.client.v1.ClientProto.Height value) {
        if (latestHeightBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          latestHeight_ = value;
        } else {
          latestHeightBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * the latest block height
       * </pre>
       *
       * <code>.ibc.core.client.v1.Height latest_height = 1 [json_name = "latestHeight", (.gogoproto.nullable) = false];</code>
       */
      public Builder setLatestHeight(
          com.ibc.core.client.v1.ClientProto.Height.Builder builderForValue) {
        if (latestHeightBuilder_ == null) {
          latestHeight_ = builderForValue.build();
        } else {
          latestHeightBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * the latest block height
       * </pre>
       *
       * <code>.ibc.core.client.v1.Height latest_height = 1 [json_name = "latestHeight", (.gogoproto.nullable) = false];</code>
       */
      public Builder mergeLatestHeight(com.ibc.core.client.v1.ClientProto.Height value) {
        if (latestHeightBuilder_ == null) {
          if (((bitField0_ & 0x00000001) != 0) &&
            latestHeight_ != null &&
            latestHeight_ != com.ibc.core.client.v1.ClientProto.Height.getDefaultInstance()) {
            getLatestHeightBuilder().mergeFrom(value);
          } else {
            latestHeight_ = value;
          }
        } else {
          latestHeightBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * the latest block height
       * </pre>
       *
       * <code>.ibc.core.client.v1.Height latest_height = 1 [json_name = "latestHeight", (.gogoproto.nullable) = false];</code>
       */
      public Builder clearLatestHeight() {
        bitField0_ = (bitField0_ & ~0x00000001);
        latestHeight_ = null;
        if (latestHeightBuilder_ != null) {
          latestHeightBuilder_.dispose();
          latestHeightBuilder_ = null;
        }
        onChanged();
        return this;
      }
      /**
       * <pre>
       * the latest block height
       * </pre>
       *
       * <code>.ibc.core.client.v1.Height latest_height = 1 [json_name = "latestHeight", (.gogoproto.nullable) = false];</code>
       */
      public com.ibc.core.client.v1.ClientProto.Height.Builder getLatestHeightBuilder() {
        bitField0_ |= 0x00000001;
        onChanged();
        return getLatestHeightFieldBuilder().getBuilder();
      }
      /**
       * <pre>
       * the latest block height
       * </pre>
       *
       * <code>.ibc.core.client.v1.Height latest_height = 1 [json_name = "latestHeight", (.gogoproto.nullable) = false];</code>
       */
      public com.ibc.core.client.v1.ClientProto.HeightOrBuilder getLatestHeightOrBuilder() {
        if (latestHeightBuilder_ != null) {
          return latestHeightBuilder_.getMessageOrBuilder();
        } else {
          return latestHeight_ == null ?
              com.ibc.core.client.v1.ClientProto.Height.getDefaultInstance() : latestHeight_;
        }
      }
      /**
       * <pre>
       * the latest block height
       * </pre>
       *
       * <code>.ibc.core.client.v1.Height latest_height = 1 [json_name = "latestHeight", (.gogoproto.nullable) = false];</code>
       */
      private com.google.protobuf.SingleFieldBuilderV3<
          com.ibc.core.client.v1.ClientProto.Height, com.ibc.core.client.v1.ClientProto.Height.Builder, com.ibc.core.client.v1.ClientProto.HeightOrBuilder> 
          getLatestHeightFieldBuilder() {
        if (latestHeightBuilder_ == null) {
          latestHeightBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
              com.ibc.core.client.v1.ClientProto.Height, com.ibc.core.client.v1.ClientProto.Height.Builder, com.ibc.core.client.v1.ClientProto.HeightOrBuilder>(
                  getLatestHeight(),
                  getParentForChildren(),
                  isClean());
          latestHeight_ = null;
        }
        return latestHeightBuilder_;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:ibc.lightclients.localhost.v2.ClientState)
    }

    // @@protoc_insertion_point(class_scope:ibc.lightclients.localhost.v2.ClientState)
    private static final com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState();
    }

    public static com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ClientState>
        PARSER = new com.google.protobuf.AbstractParser<ClientState>() {
      @java.lang.Override
      public ClientState parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        Builder builder = newBuilder();
        try {
          builder.mergeFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.setUnfinishedMessage(builder.buildPartial());
        } catch (com.google.protobuf.UninitializedMessageException e) {
          throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
        } catch (java.io.IOException e) {
          throw new com.google.protobuf.InvalidProtocolBufferException(e)
              .setUnfinishedMessage(builder.buildPartial());
        }
        return builder.buildPartial();
      }
    };

    public static com.google.protobuf.Parser<ClientState> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ClientState> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.ibc.lightclients.localhost.v2.LocalhostProto.ClientState getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ibc_lightclients_localhost_v2_ClientState_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ibc_lightclients_localhost_v2_ClientState_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n-ibc/lightclients/localhost/v2/localhos" +
      "t.proto\022\035ibc.lightclients.localhost.v2\032\037" +
      "ibc/core/client/v1/client.proto\032\024gogopro" +
      "to/gogo.proto\"Z\n\013ClientState\022E\n\rlatest_h" +
      "eight\030\001 \001(\0132\032.ibc.core.client.v1.HeightB" +
      "\004\310\336\037\000R\014latestHeight:\004\210\240\037\000B\222\002\n!com.ibc.li" +
      "ghtclients.localhost.v2B\016LocalhostProtoZ" +
      "Hgithub.com/cosmos/ibc-go/v7/modules/lig" +
      "ht-clients/09-localhost;localhost\242\002\003ILL\252" +
      "\002\035Ibc.Lightclients.Localhost.V2\312\002\035Ibc\\Li" +
      "ghtclients\\Localhost\\V2\342\002)Ibc\\Lightclien" +
      "ts\\Localhost\\V2\\GPBMetadata\352\002 Ibc::Light" +
      "clients::Localhost::V2b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.ibc.core.client.v1.ClientProto.getDescriptor(),
          com.gogoproto.GogoProto.getDescriptor(),
        });
    internal_static_ibc_lightclients_localhost_v2_ClientState_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ibc_lightclients_localhost_v2_ClientState_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ibc_lightclients_localhost_v2_ClientState_descriptor,
        new java.lang.String[] { "LatestHeight", });
    com.google.protobuf.ExtensionRegistry registry =
        com.google.protobuf.ExtensionRegistry.newInstance();
    registry.add(com.gogoproto.GogoProto.goprotoGetters);
    registry.add(com.gogoproto.GogoProto.nullable);
    com.google.protobuf.Descriptors.FileDescriptor
        .internalUpdateFileDescriptor(descriptor, registry);
    com.ibc.core.client.v1.ClientProto.getDescriptor();
    com.gogoproto.GogoProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}