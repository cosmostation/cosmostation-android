// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: cosmos/mint/v1beta1/genesis.proto

package com.cosmos.mint.v1beta1;

public final class GenesisProto {
  private GenesisProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface GenesisStateOrBuilder extends
      // @@protoc_insertion_point(interface_extends:cosmos.mint.v1beta1.GenesisState)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * minter is a space for holding current inflation information.
     * </pre>
     *
     * <code>.cosmos.mint.v1beta1.Minter minter = 1 [json_name = "minter", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
     * @return Whether the minter field is set.
     */
    boolean hasMinter();
    /**
     * <pre>
     * minter is a space for holding current inflation information.
     * </pre>
     *
     * <code>.cosmos.mint.v1beta1.Minter minter = 1 [json_name = "minter", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
     * @return The minter.
     */
    com.cosmos.mint.v1beta1.MintProto.Minter getMinter();
    /**
     * <pre>
     * minter is a space for holding current inflation information.
     * </pre>
     *
     * <code>.cosmos.mint.v1beta1.Minter minter = 1 [json_name = "minter", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
     */
    com.cosmos.mint.v1beta1.MintProto.MinterOrBuilder getMinterOrBuilder();

    /**
     * <pre>
     * params defines all the parameters of the module.
     * </pre>
     *
     * <code>.cosmos.mint.v1beta1.Params params = 2 [json_name = "params", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
     * @return Whether the params field is set.
     */
    boolean hasParams();
    /**
     * <pre>
     * params defines all the parameters of the module.
     * </pre>
     *
     * <code>.cosmos.mint.v1beta1.Params params = 2 [json_name = "params", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
     * @return The params.
     */
    com.cosmos.mint.v1beta1.MintProto.Params getParams();
    /**
     * <pre>
     * params defines all the parameters of the module.
     * </pre>
     *
     * <code>.cosmos.mint.v1beta1.Params params = 2 [json_name = "params", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
     */
    com.cosmos.mint.v1beta1.MintProto.ParamsOrBuilder getParamsOrBuilder();
  }
  /**
   * <pre>
   * GenesisState defines the mint module's genesis state.
   * </pre>
   *
   * Protobuf type {@code cosmos.mint.v1beta1.GenesisState}
   */
  public static final class GenesisState extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:cosmos.mint.v1beta1.GenesisState)
      GenesisStateOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use GenesisState.newBuilder() to construct.
    private GenesisState(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private GenesisState() {
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new GenesisState();
    }

    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.cosmos.mint.v1beta1.GenesisProto.internal_static_cosmos_mint_v1beta1_GenesisState_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.cosmos.mint.v1beta1.GenesisProto.internal_static_cosmos_mint_v1beta1_GenesisState_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.cosmos.mint.v1beta1.GenesisProto.GenesisState.class, com.cosmos.mint.v1beta1.GenesisProto.GenesisState.Builder.class);
    }

    public static final int MINTER_FIELD_NUMBER = 1;
    private com.cosmos.mint.v1beta1.MintProto.Minter minter_;
    /**
     * <pre>
     * minter is a space for holding current inflation information.
     * </pre>
     *
     * <code>.cosmos.mint.v1beta1.Minter minter = 1 [json_name = "minter", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
     * @return Whether the minter field is set.
     */
    @java.lang.Override
    public boolean hasMinter() {
      return minter_ != null;
    }
    /**
     * <pre>
     * minter is a space for holding current inflation information.
     * </pre>
     *
     * <code>.cosmos.mint.v1beta1.Minter minter = 1 [json_name = "minter", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
     * @return The minter.
     */
    @java.lang.Override
    public com.cosmos.mint.v1beta1.MintProto.Minter getMinter() {
      return minter_ == null ? com.cosmos.mint.v1beta1.MintProto.Minter.getDefaultInstance() : minter_;
    }
    /**
     * <pre>
     * minter is a space for holding current inflation information.
     * </pre>
     *
     * <code>.cosmos.mint.v1beta1.Minter minter = 1 [json_name = "minter", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
     */
    @java.lang.Override
    public com.cosmos.mint.v1beta1.MintProto.MinterOrBuilder getMinterOrBuilder() {
      return minter_ == null ? com.cosmos.mint.v1beta1.MintProto.Minter.getDefaultInstance() : minter_;
    }

    public static final int PARAMS_FIELD_NUMBER = 2;
    private com.cosmos.mint.v1beta1.MintProto.Params params_;
    /**
     * <pre>
     * params defines all the parameters of the module.
     * </pre>
     *
     * <code>.cosmos.mint.v1beta1.Params params = 2 [json_name = "params", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
     * @return Whether the params field is set.
     */
    @java.lang.Override
    public boolean hasParams() {
      return params_ != null;
    }
    /**
     * <pre>
     * params defines all the parameters of the module.
     * </pre>
     *
     * <code>.cosmos.mint.v1beta1.Params params = 2 [json_name = "params", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
     * @return The params.
     */
    @java.lang.Override
    public com.cosmos.mint.v1beta1.MintProto.Params getParams() {
      return params_ == null ? com.cosmos.mint.v1beta1.MintProto.Params.getDefaultInstance() : params_;
    }
    /**
     * <pre>
     * params defines all the parameters of the module.
     * </pre>
     *
     * <code>.cosmos.mint.v1beta1.Params params = 2 [json_name = "params", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
     */
    @java.lang.Override
    public com.cosmos.mint.v1beta1.MintProto.ParamsOrBuilder getParamsOrBuilder() {
      return params_ == null ? com.cosmos.mint.v1beta1.MintProto.Params.getDefaultInstance() : params_;
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
      if (minter_ != null) {
        output.writeMessage(1, getMinter());
      }
      if (params_ != null) {
        output.writeMessage(2, getParams());
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (minter_ != null) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, getMinter());
      }
      if (params_ != null) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(2, getParams());
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
      if (!(obj instanceof com.cosmos.mint.v1beta1.GenesisProto.GenesisState)) {
        return super.equals(obj);
      }
      com.cosmos.mint.v1beta1.GenesisProto.GenesisState other = (com.cosmos.mint.v1beta1.GenesisProto.GenesisState) obj;

      if (hasMinter() != other.hasMinter()) return false;
      if (hasMinter()) {
        if (!getMinter()
            .equals(other.getMinter())) return false;
      }
      if (hasParams() != other.hasParams()) return false;
      if (hasParams()) {
        if (!getParams()
            .equals(other.getParams())) return false;
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
      if (hasMinter()) {
        hash = (37 * hash) + MINTER_FIELD_NUMBER;
        hash = (53 * hash) + getMinter().hashCode();
      }
      if (hasParams()) {
        hash = (37 * hash) + PARAMS_FIELD_NUMBER;
        hash = (53 * hash) + getParams().hashCode();
      }
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.cosmos.mint.v1beta1.GenesisProto.GenesisState parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.cosmos.mint.v1beta1.GenesisProto.GenesisState parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.cosmos.mint.v1beta1.GenesisProto.GenesisState parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.cosmos.mint.v1beta1.GenesisProto.GenesisState parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.cosmos.mint.v1beta1.GenesisProto.GenesisState parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.cosmos.mint.v1beta1.GenesisProto.GenesisState parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.cosmos.mint.v1beta1.GenesisProto.GenesisState parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.cosmos.mint.v1beta1.GenesisProto.GenesisState parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static com.cosmos.mint.v1beta1.GenesisProto.GenesisState parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }

    public static com.cosmos.mint.v1beta1.GenesisProto.GenesisState parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.cosmos.mint.v1beta1.GenesisProto.GenesisState parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.cosmos.mint.v1beta1.GenesisProto.GenesisState parseFrom(
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
    public static Builder newBuilder(com.cosmos.mint.v1beta1.GenesisProto.GenesisState prototype) {
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
     * GenesisState defines the mint module's genesis state.
     * </pre>
     *
     * Protobuf type {@code cosmos.mint.v1beta1.GenesisState}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:cosmos.mint.v1beta1.GenesisState)
        com.cosmos.mint.v1beta1.GenesisProto.GenesisStateOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.cosmos.mint.v1beta1.GenesisProto.internal_static_cosmos_mint_v1beta1_GenesisState_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.cosmos.mint.v1beta1.GenesisProto.internal_static_cosmos_mint_v1beta1_GenesisState_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.cosmos.mint.v1beta1.GenesisProto.GenesisState.class, com.cosmos.mint.v1beta1.GenesisProto.GenesisState.Builder.class);
      }

      // Construct using com.cosmos.mint.v1beta1.GenesisProto.GenesisState.newBuilder()
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
        minter_ = null;
        if (minterBuilder_ != null) {
          minterBuilder_.dispose();
          minterBuilder_ = null;
        }
        params_ = null;
        if (paramsBuilder_ != null) {
          paramsBuilder_.dispose();
          paramsBuilder_ = null;
        }
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.cosmos.mint.v1beta1.GenesisProto.internal_static_cosmos_mint_v1beta1_GenesisState_descriptor;
      }

      @java.lang.Override
      public com.cosmos.mint.v1beta1.GenesisProto.GenesisState getDefaultInstanceForType() {
        return com.cosmos.mint.v1beta1.GenesisProto.GenesisState.getDefaultInstance();
      }

      @java.lang.Override
      public com.cosmos.mint.v1beta1.GenesisProto.GenesisState build() {
        com.cosmos.mint.v1beta1.GenesisProto.GenesisState result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.cosmos.mint.v1beta1.GenesisProto.GenesisState buildPartial() {
        com.cosmos.mint.v1beta1.GenesisProto.GenesisState result = new com.cosmos.mint.v1beta1.GenesisProto.GenesisState(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(com.cosmos.mint.v1beta1.GenesisProto.GenesisState result) {
        int from_bitField0_ = bitField0_;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.minter_ = minterBuilder_ == null
              ? minter_
              : minterBuilder_.build();
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.params_ = paramsBuilder_ == null
              ? params_
              : paramsBuilder_.build();
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
        if (other instanceof com.cosmos.mint.v1beta1.GenesisProto.GenesisState) {
          return mergeFrom((com.cosmos.mint.v1beta1.GenesisProto.GenesisState)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.cosmos.mint.v1beta1.GenesisProto.GenesisState other) {
        if (other == com.cosmos.mint.v1beta1.GenesisProto.GenesisState.getDefaultInstance()) return this;
        if (other.hasMinter()) {
          mergeMinter(other.getMinter());
        }
        if (other.hasParams()) {
          mergeParams(other.getParams());
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
                    getMinterFieldBuilder().getBuilder(),
                    extensionRegistry);
                bitField0_ |= 0x00000001;
                break;
              } // case 10
              case 18: {
                input.readMessage(
                    getParamsFieldBuilder().getBuilder(),
                    extensionRegistry);
                bitField0_ |= 0x00000002;
                break;
              } // case 18
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

      private com.cosmos.mint.v1beta1.MintProto.Minter minter_;
      private com.google.protobuf.SingleFieldBuilderV3<
          com.cosmos.mint.v1beta1.MintProto.Minter, com.cosmos.mint.v1beta1.MintProto.Minter.Builder, com.cosmos.mint.v1beta1.MintProto.MinterOrBuilder> minterBuilder_;
      /**
       * <pre>
       * minter is a space for holding current inflation information.
       * </pre>
       *
       * <code>.cosmos.mint.v1beta1.Minter minter = 1 [json_name = "minter", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       * @return Whether the minter field is set.
       */
      public boolean hasMinter() {
        return ((bitField0_ & 0x00000001) != 0);
      }
      /**
       * <pre>
       * minter is a space for holding current inflation information.
       * </pre>
       *
       * <code>.cosmos.mint.v1beta1.Minter minter = 1 [json_name = "minter", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       * @return The minter.
       */
      public com.cosmos.mint.v1beta1.MintProto.Minter getMinter() {
        if (minterBuilder_ == null) {
          return minter_ == null ? com.cosmos.mint.v1beta1.MintProto.Minter.getDefaultInstance() : minter_;
        } else {
          return minterBuilder_.getMessage();
        }
      }
      /**
       * <pre>
       * minter is a space for holding current inflation information.
       * </pre>
       *
       * <code>.cosmos.mint.v1beta1.Minter minter = 1 [json_name = "minter", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public Builder setMinter(com.cosmos.mint.v1beta1.MintProto.Minter value) {
        if (minterBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          minter_ = value;
        } else {
          minterBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * minter is a space for holding current inflation information.
       * </pre>
       *
       * <code>.cosmos.mint.v1beta1.Minter minter = 1 [json_name = "minter", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public Builder setMinter(
          com.cosmos.mint.v1beta1.MintProto.Minter.Builder builderForValue) {
        if (minterBuilder_ == null) {
          minter_ = builderForValue.build();
        } else {
          minterBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * minter is a space for holding current inflation information.
       * </pre>
       *
       * <code>.cosmos.mint.v1beta1.Minter minter = 1 [json_name = "minter", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public Builder mergeMinter(com.cosmos.mint.v1beta1.MintProto.Minter value) {
        if (minterBuilder_ == null) {
          if (((bitField0_ & 0x00000001) != 0) &&
            minter_ != null &&
            minter_ != com.cosmos.mint.v1beta1.MintProto.Minter.getDefaultInstance()) {
            getMinterBuilder().mergeFrom(value);
          } else {
            minter_ = value;
          }
        } else {
          minterBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * minter is a space for holding current inflation information.
       * </pre>
       *
       * <code>.cosmos.mint.v1beta1.Minter minter = 1 [json_name = "minter", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public Builder clearMinter() {
        bitField0_ = (bitField0_ & ~0x00000001);
        minter_ = null;
        if (minterBuilder_ != null) {
          minterBuilder_.dispose();
          minterBuilder_ = null;
        }
        onChanged();
        return this;
      }
      /**
       * <pre>
       * minter is a space for holding current inflation information.
       * </pre>
       *
       * <code>.cosmos.mint.v1beta1.Minter minter = 1 [json_name = "minter", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public com.cosmos.mint.v1beta1.MintProto.Minter.Builder getMinterBuilder() {
        bitField0_ |= 0x00000001;
        onChanged();
        return getMinterFieldBuilder().getBuilder();
      }
      /**
       * <pre>
       * minter is a space for holding current inflation information.
       * </pre>
       *
       * <code>.cosmos.mint.v1beta1.Minter minter = 1 [json_name = "minter", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public com.cosmos.mint.v1beta1.MintProto.MinterOrBuilder getMinterOrBuilder() {
        if (minterBuilder_ != null) {
          return minterBuilder_.getMessageOrBuilder();
        } else {
          return minter_ == null ?
              com.cosmos.mint.v1beta1.MintProto.Minter.getDefaultInstance() : minter_;
        }
      }
      /**
       * <pre>
       * minter is a space for holding current inflation information.
       * </pre>
       *
       * <code>.cosmos.mint.v1beta1.Minter minter = 1 [json_name = "minter", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      private com.google.protobuf.SingleFieldBuilderV3<
          com.cosmos.mint.v1beta1.MintProto.Minter, com.cosmos.mint.v1beta1.MintProto.Minter.Builder, com.cosmos.mint.v1beta1.MintProto.MinterOrBuilder> 
          getMinterFieldBuilder() {
        if (minterBuilder_ == null) {
          minterBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
              com.cosmos.mint.v1beta1.MintProto.Minter, com.cosmos.mint.v1beta1.MintProto.Minter.Builder, com.cosmos.mint.v1beta1.MintProto.MinterOrBuilder>(
                  getMinter(),
                  getParentForChildren(),
                  isClean());
          minter_ = null;
        }
        return minterBuilder_;
      }

      private com.cosmos.mint.v1beta1.MintProto.Params params_;
      private com.google.protobuf.SingleFieldBuilderV3<
          com.cosmos.mint.v1beta1.MintProto.Params, com.cosmos.mint.v1beta1.MintProto.Params.Builder, com.cosmos.mint.v1beta1.MintProto.ParamsOrBuilder> paramsBuilder_;
      /**
       * <pre>
       * params defines all the parameters of the module.
       * </pre>
       *
       * <code>.cosmos.mint.v1beta1.Params params = 2 [json_name = "params", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       * @return Whether the params field is set.
       */
      public boolean hasParams() {
        return ((bitField0_ & 0x00000002) != 0);
      }
      /**
       * <pre>
       * params defines all the parameters of the module.
       * </pre>
       *
       * <code>.cosmos.mint.v1beta1.Params params = 2 [json_name = "params", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       * @return The params.
       */
      public com.cosmos.mint.v1beta1.MintProto.Params getParams() {
        if (paramsBuilder_ == null) {
          return params_ == null ? com.cosmos.mint.v1beta1.MintProto.Params.getDefaultInstance() : params_;
        } else {
          return paramsBuilder_.getMessage();
        }
      }
      /**
       * <pre>
       * params defines all the parameters of the module.
       * </pre>
       *
       * <code>.cosmos.mint.v1beta1.Params params = 2 [json_name = "params", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public Builder setParams(com.cosmos.mint.v1beta1.MintProto.Params value) {
        if (paramsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          params_ = value;
        } else {
          paramsBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * params defines all the parameters of the module.
       * </pre>
       *
       * <code>.cosmos.mint.v1beta1.Params params = 2 [json_name = "params", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public Builder setParams(
          com.cosmos.mint.v1beta1.MintProto.Params.Builder builderForValue) {
        if (paramsBuilder_ == null) {
          params_ = builderForValue.build();
        } else {
          paramsBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * params defines all the parameters of the module.
       * </pre>
       *
       * <code>.cosmos.mint.v1beta1.Params params = 2 [json_name = "params", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public Builder mergeParams(com.cosmos.mint.v1beta1.MintProto.Params value) {
        if (paramsBuilder_ == null) {
          if (((bitField0_ & 0x00000002) != 0) &&
            params_ != null &&
            params_ != com.cosmos.mint.v1beta1.MintProto.Params.getDefaultInstance()) {
            getParamsBuilder().mergeFrom(value);
          } else {
            params_ = value;
          }
        } else {
          paramsBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * params defines all the parameters of the module.
       * </pre>
       *
       * <code>.cosmos.mint.v1beta1.Params params = 2 [json_name = "params", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public Builder clearParams() {
        bitField0_ = (bitField0_ & ~0x00000002);
        params_ = null;
        if (paramsBuilder_ != null) {
          paramsBuilder_.dispose();
          paramsBuilder_ = null;
        }
        onChanged();
        return this;
      }
      /**
       * <pre>
       * params defines all the parameters of the module.
       * </pre>
       *
       * <code>.cosmos.mint.v1beta1.Params params = 2 [json_name = "params", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public com.cosmos.mint.v1beta1.MintProto.Params.Builder getParamsBuilder() {
        bitField0_ |= 0x00000002;
        onChanged();
        return getParamsFieldBuilder().getBuilder();
      }
      /**
       * <pre>
       * params defines all the parameters of the module.
       * </pre>
       *
       * <code>.cosmos.mint.v1beta1.Params params = 2 [json_name = "params", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public com.cosmos.mint.v1beta1.MintProto.ParamsOrBuilder getParamsOrBuilder() {
        if (paramsBuilder_ != null) {
          return paramsBuilder_.getMessageOrBuilder();
        } else {
          return params_ == null ?
              com.cosmos.mint.v1beta1.MintProto.Params.getDefaultInstance() : params_;
        }
      }
      /**
       * <pre>
       * params defines all the parameters of the module.
       * </pre>
       *
       * <code>.cosmos.mint.v1beta1.Params params = 2 [json_name = "params", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      private com.google.protobuf.SingleFieldBuilderV3<
          com.cosmos.mint.v1beta1.MintProto.Params, com.cosmos.mint.v1beta1.MintProto.Params.Builder, com.cosmos.mint.v1beta1.MintProto.ParamsOrBuilder> 
          getParamsFieldBuilder() {
        if (paramsBuilder_ == null) {
          paramsBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
              com.cosmos.mint.v1beta1.MintProto.Params, com.cosmos.mint.v1beta1.MintProto.Params.Builder, com.cosmos.mint.v1beta1.MintProto.ParamsOrBuilder>(
                  getParams(),
                  getParentForChildren(),
                  isClean());
          params_ = null;
        }
        return paramsBuilder_;
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


      // @@protoc_insertion_point(builder_scope:cosmos.mint.v1beta1.GenesisState)
    }

    // @@protoc_insertion_point(class_scope:cosmos.mint.v1beta1.GenesisState)
    private static final com.cosmos.mint.v1beta1.GenesisProto.GenesisState DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.cosmos.mint.v1beta1.GenesisProto.GenesisState();
    }

    public static com.cosmos.mint.v1beta1.GenesisProto.GenesisState getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<GenesisState>
        PARSER = new com.google.protobuf.AbstractParser<GenesisState>() {
      @java.lang.Override
      public GenesisState parsePartialFrom(
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

    public static com.google.protobuf.Parser<GenesisState> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<GenesisState> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.cosmos.mint.v1beta1.GenesisProto.GenesisState getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_cosmos_mint_v1beta1_GenesisState_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_cosmos_mint_v1beta1_GenesisState_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n!cosmos/mint/v1beta1/genesis.proto\022\023cos" +
      "mos.mint.v1beta1\032\024gogoproto/gogo.proto\032\036" +
      "cosmos/mint/v1beta1/mint.proto\032\021amino/am" +
      "ino.proto\"\216\001\n\014GenesisState\022>\n\006minter\030\001 \001" +
      "(\0132\033.cosmos.mint.v1beta1.MinterB\t\310\336\037\000\250\347\260" +
      "*\001R\006minter\022>\n\006params\030\002 \001(\0132\033.cosmos.mint" +
      ".v1beta1.ParamsB\t\310\336\037\000\250\347\260*\001R\006paramsB\276\001\n\027c" +
      "om.cosmos.mint.v1beta1B\014GenesisProtoZ)gi" +
      "thub.com/cosmos/cosmos-sdk/x/mint/types\242" +
      "\002\003CMX\252\002\023Cosmos.Mint.V1beta1\312\002\023Cosmos\\Min" +
      "t\\V1beta1\342\002\037Cosmos\\Mint\\V1beta1\\GPBMetad" +
      "ata\352\002\025Cosmos::Mint::V1beta1b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.gogoproto.GogoProto.getDescriptor(),
          com.cosmos.mint.v1beta1.MintProto.getDescriptor(),
          com.amino.AminoProto.getDescriptor(),
        });
    internal_static_cosmos_mint_v1beta1_GenesisState_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_cosmos_mint_v1beta1_GenesisState_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_cosmos_mint_v1beta1_GenesisState_descriptor,
        new java.lang.String[] { "Minter", "Params", });
    com.google.protobuf.ExtensionRegistry registry =
        com.google.protobuf.ExtensionRegistry.newInstance();
    registry.add(com.amino.AminoProto.dontOmitempty);
    registry.add(com.gogoproto.GogoProto.nullable);
    com.google.protobuf.Descriptors.FileDescriptor
        .internalUpdateFileDescriptor(descriptor, registry);
    com.gogoproto.GogoProto.getDescriptor();
    com.cosmos.mint.v1beta1.MintProto.getDescriptor();
    com.amino.AminoProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
