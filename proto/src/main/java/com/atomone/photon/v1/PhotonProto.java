// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: atomone/photon/v1/photon.proto

package com.atomone.photon.v1;

public final class PhotonProto {
  private PhotonProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ParamsOrBuilder extends
      // @@protoc_insertion_point(interface_extends:atomone.photon.v1.Params)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * Allow to mint photon or not
     * </pre>
     *
     * <code>bool mint_disabled = 1 [json_name = "mintDisabled"];</code>
     * @return The mintDisabled.
     */
    boolean getMintDisabled();

    /**
     * <pre>
     * tx_fee_exceptions holds the msg type urls that are allowed to use some
     * different tx fee coins than photon.
     * A wildcard "*" can be used to allow all transactions to use any fee denom.
     * </pre>
     *
     * <code>repeated string tx_fee_exceptions = 2 [json_name = "txFeeExceptions"];</code>
     * @return A list containing the txFeeExceptions.
     */
    java.util.List<java.lang.String>
        getTxFeeExceptionsList();
    /**
     * <pre>
     * tx_fee_exceptions holds the msg type urls that are allowed to use some
     * different tx fee coins than photon.
     * A wildcard "*" can be used to allow all transactions to use any fee denom.
     * </pre>
     *
     * <code>repeated string tx_fee_exceptions = 2 [json_name = "txFeeExceptions"];</code>
     * @return The count of txFeeExceptions.
     */
    int getTxFeeExceptionsCount();
    /**
     * <pre>
     * tx_fee_exceptions holds the msg type urls that are allowed to use some
     * different tx fee coins than photon.
     * A wildcard "*" can be used to allow all transactions to use any fee denom.
     * </pre>
     *
     * <code>repeated string tx_fee_exceptions = 2 [json_name = "txFeeExceptions"];</code>
     * @param index The index of the element to return.
     * @return The txFeeExceptions at the given index.
     */
    java.lang.String getTxFeeExceptions(int index);
    /**
     * <pre>
     * tx_fee_exceptions holds the msg type urls that are allowed to use some
     * different tx fee coins than photon.
     * A wildcard "*" can be used to allow all transactions to use any fee denom.
     * </pre>
     *
     * <code>repeated string tx_fee_exceptions = 2 [json_name = "txFeeExceptions"];</code>
     * @param index The index of the value to return.
     * @return The bytes of the txFeeExceptions at the given index.
     */
    com.google.protobuf.ByteString
        getTxFeeExceptionsBytes(int index);
  }
  /**
   * <pre>
   * Params defines the parameters for the x/photon module.
   * </pre>
   *
   * Protobuf type {@code atomone.photon.v1.Params}
   */
  public static final class Params extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:atomone.photon.v1.Params)
      ParamsOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Params.newBuilder() to construct.
    private Params(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Params() {
      txFeeExceptions_ =
          com.google.protobuf.LazyStringArrayList.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new Params();
    }

    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.atomone.photon.v1.PhotonProto.internal_static_atomone_photon_v1_Params_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.atomone.photon.v1.PhotonProto.internal_static_atomone_photon_v1_Params_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.atomone.photon.v1.PhotonProto.Params.class, com.atomone.photon.v1.PhotonProto.Params.Builder.class);
    }

    public static final int MINT_DISABLED_FIELD_NUMBER = 1;
    private boolean mintDisabled_ = false;
    /**
     * <pre>
     * Allow to mint photon or not
     * </pre>
     *
     * <code>bool mint_disabled = 1 [json_name = "mintDisabled"];</code>
     * @return The mintDisabled.
     */
    @java.lang.Override
    public boolean getMintDisabled() {
      return mintDisabled_;
    }

    public static final int TX_FEE_EXCEPTIONS_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private com.google.protobuf.LazyStringArrayList txFeeExceptions_ =
        com.google.protobuf.LazyStringArrayList.emptyList();
    /**
     * <pre>
     * tx_fee_exceptions holds the msg type urls that are allowed to use some
     * different tx fee coins than photon.
     * A wildcard "*" can be used to allow all transactions to use any fee denom.
     * </pre>
     *
     * <code>repeated string tx_fee_exceptions = 2 [json_name = "txFeeExceptions"];</code>
     * @return A list containing the txFeeExceptions.
     */
    public com.google.protobuf.ProtocolStringList
        getTxFeeExceptionsList() {
      return txFeeExceptions_;
    }
    /**
     * <pre>
     * tx_fee_exceptions holds the msg type urls that are allowed to use some
     * different tx fee coins than photon.
     * A wildcard "*" can be used to allow all transactions to use any fee denom.
     * </pre>
     *
     * <code>repeated string tx_fee_exceptions = 2 [json_name = "txFeeExceptions"];</code>
     * @return The count of txFeeExceptions.
     */
    public int getTxFeeExceptionsCount() {
      return txFeeExceptions_.size();
    }
    /**
     * <pre>
     * tx_fee_exceptions holds the msg type urls that are allowed to use some
     * different tx fee coins than photon.
     * A wildcard "*" can be used to allow all transactions to use any fee denom.
     * </pre>
     *
     * <code>repeated string tx_fee_exceptions = 2 [json_name = "txFeeExceptions"];</code>
     * @param index The index of the element to return.
     * @return The txFeeExceptions at the given index.
     */
    public java.lang.String getTxFeeExceptions(int index) {
      return txFeeExceptions_.get(index);
    }
    /**
     * <pre>
     * tx_fee_exceptions holds the msg type urls that are allowed to use some
     * different tx fee coins than photon.
     * A wildcard "*" can be used to allow all transactions to use any fee denom.
     * </pre>
     *
     * <code>repeated string tx_fee_exceptions = 2 [json_name = "txFeeExceptions"];</code>
     * @param index The index of the value to return.
     * @return The bytes of the txFeeExceptions at the given index.
     */
    public com.google.protobuf.ByteString
        getTxFeeExceptionsBytes(int index) {
      return txFeeExceptions_.getByteString(index);
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
      if (mintDisabled_ != false) {
        output.writeBool(1, mintDisabled_);
      }
      for (int i = 0; i < txFeeExceptions_.size(); i++) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, txFeeExceptions_.getRaw(i));
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (mintDisabled_ != false) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(1, mintDisabled_);
      }
      {
        int dataSize = 0;
        for (int i = 0; i < txFeeExceptions_.size(); i++) {
          dataSize += computeStringSizeNoTag(txFeeExceptions_.getRaw(i));
        }
        size += dataSize;
        size += 1 * getTxFeeExceptionsList().size();
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
      if (!(obj instanceof com.atomone.photon.v1.PhotonProto.Params)) {
        return super.equals(obj);
      }
      com.atomone.photon.v1.PhotonProto.Params other = (com.atomone.photon.v1.PhotonProto.Params) obj;

      if (getMintDisabled()
          != other.getMintDisabled()) return false;
      if (!getTxFeeExceptionsList()
          .equals(other.getTxFeeExceptionsList())) return false;
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
      hash = (37 * hash) + MINT_DISABLED_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
          getMintDisabled());
      if (getTxFeeExceptionsCount() > 0) {
        hash = (37 * hash) + TX_FEE_EXCEPTIONS_FIELD_NUMBER;
        hash = (53 * hash) + getTxFeeExceptionsList().hashCode();
      }
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.atomone.photon.v1.PhotonProto.Params parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.atomone.photon.v1.PhotonProto.Params parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.atomone.photon.v1.PhotonProto.Params parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.atomone.photon.v1.PhotonProto.Params parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.atomone.photon.v1.PhotonProto.Params parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.atomone.photon.v1.PhotonProto.Params parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.atomone.photon.v1.PhotonProto.Params parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.atomone.photon.v1.PhotonProto.Params parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static com.atomone.photon.v1.PhotonProto.Params parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }

    public static com.atomone.photon.v1.PhotonProto.Params parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.atomone.photon.v1.PhotonProto.Params parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.atomone.photon.v1.PhotonProto.Params parseFrom(
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
    public static Builder newBuilder(com.atomone.photon.v1.PhotonProto.Params prototype) {
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
     * Params defines the parameters for the x/photon module.
     * </pre>
     *
     * Protobuf type {@code atomone.photon.v1.Params}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:atomone.photon.v1.Params)
        com.atomone.photon.v1.PhotonProto.ParamsOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.atomone.photon.v1.PhotonProto.internal_static_atomone_photon_v1_Params_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.atomone.photon.v1.PhotonProto.internal_static_atomone_photon_v1_Params_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.atomone.photon.v1.PhotonProto.Params.class, com.atomone.photon.v1.PhotonProto.Params.Builder.class);
      }

      // Construct using com.atomone.photon.v1.PhotonProto.Params.newBuilder()
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
        mintDisabled_ = false;
        txFeeExceptions_ =
            com.google.protobuf.LazyStringArrayList.emptyList();
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.atomone.photon.v1.PhotonProto.internal_static_atomone_photon_v1_Params_descriptor;
      }

      @java.lang.Override
      public com.atomone.photon.v1.PhotonProto.Params getDefaultInstanceForType() {
        return com.atomone.photon.v1.PhotonProto.Params.getDefaultInstance();
      }

      @java.lang.Override
      public com.atomone.photon.v1.PhotonProto.Params build() {
        com.atomone.photon.v1.PhotonProto.Params result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.atomone.photon.v1.PhotonProto.Params buildPartial() {
        com.atomone.photon.v1.PhotonProto.Params result = new com.atomone.photon.v1.PhotonProto.Params(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(com.atomone.photon.v1.PhotonProto.Params result) {
        int from_bitField0_ = bitField0_;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.mintDisabled_ = mintDisabled_;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          txFeeExceptions_.makeImmutable();
          result.txFeeExceptions_ = txFeeExceptions_;
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
        if (other instanceof com.atomone.photon.v1.PhotonProto.Params) {
          return mergeFrom((com.atomone.photon.v1.PhotonProto.Params)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.atomone.photon.v1.PhotonProto.Params other) {
        if (other == com.atomone.photon.v1.PhotonProto.Params.getDefaultInstance()) return this;
        if (other.getMintDisabled() != false) {
          setMintDisabled(other.getMintDisabled());
        }
        if (!other.txFeeExceptions_.isEmpty()) {
          if (txFeeExceptions_.isEmpty()) {
            txFeeExceptions_ = other.txFeeExceptions_;
            bitField0_ |= 0x00000002;
          } else {
            ensureTxFeeExceptionsIsMutable();
            txFeeExceptions_.addAll(other.txFeeExceptions_);
          }
          onChanged();
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
              case 8: {
                mintDisabled_ = input.readBool();
                bitField0_ |= 0x00000001;
                break;
              } // case 8
              case 18: {
                java.lang.String s = input.readStringRequireUtf8();
                ensureTxFeeExceptionsIsMutable();
                txFeeExceptions_.add(s);
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

      private boolean mintDisabled_ ;
      /**
       * <pre>
       * Allow to mint photon or not
       * </pre>
       *
       * <code>bool mint_disabled = 1 [json_name = "mintDisabled"];</code>
       * @return The mintDisabled.
       */
      @java.lang.Override
      public boolean getMintDisabled() {
        return mintDisabled_;
      }
      /**
       * <pre>
       * Allow to mint photon or not
       * </pre>
       *
       * <code>bool mint_disabled = 1 [json_name = "mintDisabled"];</code>
       * @param value The mintDisabled to set.
       * @return This builder for chaining.
       */
      public Builder setMintDisabled(boolean value) {

        mintDisabled_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Allow to mint photon or not
       * </pre>
       *
       * <code>bool mint_disabled = 1 [json_name = "mintDisabled"];</code>
       * @return This builder for chaining.
       */
      public Builder clearMintDisabled() {
        bitField0_ = (bitField0_ & ~0x00000001);
        mintDisabled_ = false;
        onChanged();
        return this;
      }

      private com.google.protobuf.LazyStringArrayList txFeeExceptions_ =
          com.google.protobuf.LazyStringArrayList.emptyList();
      private void ensureTxFeeExceptionsIsMutable() {
        if (!txFeeExceptions_.isModifiable()) {
          txFeeExceptions_ = new com.google.protobuf.LazyStringArrayList(txFeeExceptions_);
        }
        bitField0_ |= 0x00000002;
      }
      /**
       * <pre>
       * tx_fee_exceptions holds the msg type urls that are allowed to use some
       * different tx fee coins than photon.
       * A wildcard "*" can be used to allow all transactions to use any fee denom.
       * </pre>
       *
       * <code>repeated string tx_fee_exceptions = 2 [json_name = "txFeeExceptions"];</code>
       * @return A list containing the txFeeExceptions.
       */
      public com.google.protobuf.ProtocolStringList
          getTxFeeExceptionsList() {
        txFeeExceptions_.makeImmutable();
        return txFeeExceptions_;
      }
      /**
       * <pre>
       * tx_fee_exceptions holds the msg type urls that are allowed to use some
       * different tx fee coins than photon.
       * A wildcard "*" can be used to allow all transactions to use any fee denom.
       * </pre>
       *
       * <code>repeated string tx_fee_exceptions = 2 [json_name = "txFeeExceptions"];</code>
       * @return The count of txFeeExceptions.
       */
      public int getTxFeeExceptionsCount() {
        return txFeeExceptions_.size();
      }
      /**
       * <pre>
       * tx_fee_exceptions holds the msg type urls that are allowed to use some
       * different tx fee coins than photon.
       * A wildcard "*" can be used to allow all transactions to use any fee denom.
       * </pre>
       *
       * <code>repeated string tx_fee_exceptions = 2 [json_name = "txFeeExceptions"];</code>
       * @param index The index of the element to return.
       * @return The txFeeExceptions at the given index.
       */
      public java.lang.String getTxFeeExceptions(int index) {
        return txFeeExceptions_.get(index);
      }
      /**
       * <pre>
       * tx_fee_exceptions holds the msg type urls that are allowed to use some
       * different tx fee coins than photon.
       * A wildcard "*" can be used to allow all transactions to use any fee denom.
       * </pre>
       *
       * <code>repeated string tx_fee_exceptions = 2 [json_name = "txFeeExceptions"];</code>
       * @param index The index of the value to return.
       * @return The bytes of the txFeeExceptions at the given index.
       */
      public com.google.protobuf.ByteString
          getTxFeeExceptionsBytes(int index) {
        return txFeeExceptions_.getByteString(index);
      }
      /**
       * <pre>
       * tx_fee_exceptions holds the msg type urls that are allowed to use some
       * different tx fee coins than photon.
       * A wildcard "*" can be used to allow all transactions to use any fee denom.
       * </pre>
       *
       * <code>repeated string tx_fee_exceptions = 2 [json_name = "txFeeExceptions"];</code>
       * @param index The index to set the value at.
       * @param value The txFeeExceptions to set.
       * @return This builder for chaining.
       */
      public Builder setTxFeeExceptions(
          int index, java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        ensureTxFeeExceptionsIsMutable();
        txFeeExceptions_.set(index, value);
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * tx_fee_exceptions holds the msg type urls that are allowed to use some
       * different tx fee coins than photon.
       * A wildcard "*" can be used to allow all transactions to use any fee denom.
       * </pre>
       *
       * <code>repeated string tx_fee_exceptions = 2 [json_name = "txFeeExceptions"];</code>
       * @param value The txFeeExceptions to add.
       * @return This builder for chaining.
       */
      public Builder addTxFeeExceptions(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        ensureTxFeeExceptionsIsMutable();
        txFeeExceptions_.add(value);
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * tx_fee_exceptions holds the msg type urls that are allowed to use some
       * different tx fee coins than photon.
       * A wildcard "*" can be used to allow all transactions to use any fee denom.
       * </pre>
       *
       * <code>repeated string tx_fee_exceptions = 2 [json_name = "txFeeExceptions"];</code>
       * @param values The txFeeExceptions to add.
       * @return This builder for chaining.
       */
      public Builder addAllTxFeeExceptions(
          java.lang.Iterable<java.lang.String> values) {
        ensureTxFeeExceptionsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, txFeeExceptions_);
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * tx_fee_exceptions holds the msg type urls that are allowed to use some
       * different tx fee coins than photon.
       * A wildcard "*" can be used to allow all transactions to use any fee denom.
       * </pre>
       *
       * <code>repeated string tx_fee_exceptions = 2 [json_name = "txFeeExceptions"];</code>
       * @return This builder for chaining.
       */
      public Builder clearTxFeeExceptions() {
        txFeeExceptions_ =
          com.google.protobuf.LazyStringArrayList.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * tx_fee_exceptions holds the msg type urls that are allowed to use some
       * different tx fee coins than photon.
       * A wildcard "*" can be used to allow all transactions to use any fee denom.
       * </pre>
       *
       * <code>repeated string tx_fee_exceptions = 2 [json_name = "txFeeExceptions"];</code>
       * @param value The bytes of the txFeeExceptions to add.
       * @return This builder for chaining.
       */
      public Builder addTxFeeExceptionsBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        ensureTxFeeExceptionsIsMutable();
        txFeeExceptions_.add(value);
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
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


      // @@protoc_insertion_point(builder_scope:atomone.photon.v1.Params)
    }

    // @@protoc_insertion_point(class_scope:atomone.photon.v1.Params)
    private static final com.atomone.photon.v1.PhotonProto.Params DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.atomone.photon.v1.PhotonProto.Params();
    }

    public static com.atomone.photon.v1.PhotonProto.Params getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Params>
        PARSER = new com.google.protobuf.AbstractParser<Params>() {
      @java.lang.Override
      public Params parsePartialFrom(
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

    public static com.google.protobuf.Parser<Params> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Params> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.atomone.photon.v1.PhotonProto.Params getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_atomone_photon_v1_Params_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_atomone_photon_v1_Params_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036atomone/photon/v1/photon.proto\022\021atomon" +
      "e.photon.v1\032\024gogoproto/gogo.proto\"Y\n\006Par" +
      "ams\022#\n\rmint_disabled\030\001 \001(\010R\014mintDisabled" +
      "\022*\n\021tx_fee_exceptions\030\002 \003(\tR\017txFeeExcept" +
      "ionsB\267\001\n\025com.atomone.photon.v1B\013PhotonPr" +
      "otoZ-github.com/atomone-hub/atomone/x/ph" +
      "oton/types\242\002\003APX\252\002\021Atomone.Photon.V1\312\002\021A" +
      "tomone\\Photon\\V1\342\002\035Atomone\\Photon\\V1\\GPB" +
      "Metadata\352\002\023Atomone::Photon::V1b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.gogoproto.GogoProto.getDescriptor(),
        });
    internal_static_atomone_photon_v1_Params_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_atomone_photon_v1_Params_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_atomone_photon_v1_Params_descriptor,
        new java.lang.String[] { "MintDisabled", "TxFeeExceptions", });
    com.gogoproto.GogoProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
