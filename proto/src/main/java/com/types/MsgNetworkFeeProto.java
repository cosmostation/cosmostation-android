// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: thorchain/v1/x/thorchain/types/msg_network_fee.proto

package com.types;

public final class MsgNetworkFeeProto {
  private MsgNetworkFeeProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface MsgNetworkFeeOrBuilder extends
      // @@protoc_insertion_point(interface_extends:types.MsgNetworkFee)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int64 block_height = 1 [json_name = "blockHeight"];</code>
     * @return The blockHeight.
     */
    long getBlockHeight();

    /**
     * <code>string chain = 2 [json_name = "chain", (.gogoproto.casttype) = "gitlab.com/thorchain/thornode/common.Chain"];</code>
     * @return The chain.
     */
    java.lang.String getChain();
    /**
     * <code>string chain = 2 [json_name = "chain", (.gogoproto.casttype) = "gitlab.com/thorchain/thornode/common.Chain"];</code>
     * @return The bytes for chain.
     */
    com.google.protobuf.ByteString
        getChainBytes();

    /**
     * <code>uint64 transaction_size = 3 [json_name = "transactionSize"];</code>
     * @return The transactionSize.
     */
    long getTransactionSize();

    /**
     * <code>uint64 transaction_fee_rate = 4 [json_name = "transactionFeeRate"];</code>
     * @return The transactionFeeRate.
     */
    long getTransactionFeeRate();

    /**
     * <code>bytes signer = 5 [json_name = "signer", (.gogoproto.casttype) = "github.com/cosmos/cosmos-sdk/types.AccAddress"];</code>
     * @return The signer.
     */
    com.google.protobuf.ByteString getSigner();
  }
  /**
   * Protobuf type {@code types.MsgNetworkFee}
   */
  public static final class MsgNetworkFee extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:types.MsgNetworkFee)
      MsgNetworkFeeOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use MsgNetworkFee.newBuilder() to construct.
    private MsgNetworkFee(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private MsgNetworkFee() {
      chain_ = "";
      signer_ = com.google.protobuf.ByteString.EMPTY;
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new MsgNetworkFee();
    }

    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.types.MsgNetworkFeeProto.internal_static_types_MsgNetworkFee_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.types.MsgNetworkFeeProto.internal_static_types_MsgNetworkFee_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.types.MsgNetworkFeeProto.MsgNetworkFee.class, com.types.MsgNetworkFeeProto.MsgNetworkFee.Builder.class);
    }

    public static final int BLOCK_HEIGHT_FIELD_NUMBER = 1;
    private long blockHeight_ = 0L;
    /**
     * <code>int64 block_height = 1 [json_name = "blockHeight"];</code>
     * @return The blockHeight.
     */
    @java.lang.Override
    public long getBlockHeight() {
      return blockHeight_;
    }

    public static final int CHAIN_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object chain_ = "";
    /**
     * <code>string chain = 2 [json_name = "chain", (.gogoproto.casttype) = "gitlab.com/thorchain/thornode/common.Chain"];</code>
     * @return The chain.
     */
    @java.lang.Override
    public java.lang.String getChain() {
      java.lang.Object ref = chain_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        chain_ = s;
        return s;
      }
    }
    /**
     * <code>string chain = 2 [json_name = "chain", (.gogoproto.casttype) = "gitlab.com/thorchain/thornode/common.Chain"];</code>
     * @return The bytes for chain.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getChainBytes() {
      java.lang.Object ref = chain_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        chain_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TRANSACTION_SIZE_FIELD_NUMBER = 3;
    private long transactionSize_ = 0L;
    /**
     * <code>uint64 transaction_size = 3 [json_name = "transactionSize"];</code>
     * @return The transactionSize.
     */
    @java.lang.Override
    public long getTransactionSize() {
      return transactionSize_;
    }

    public static final int TRANSACTION_FEE_RATE_FIELD_NUMBER = 4;
    private long transactionFeeRate_ = 0L;
    /**
     * <code>uint64 transaction_fee_rate = 4 [json_name = "transactionFeeRate"];</code>
     * @return The transactionFeeRate.
     */
    @java.lang.Override
    public long getTransactionFeeRate() {
      return transactionFeeRate_;
    }

    public static final int SIGNER_FIELD_NUMBER = 5;
    private com.google.protobuf.ByteString signer_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes signer = 5 [json_name = "signer", (.gogoproto.casttype) = "github.com/cosmos/cosmos-sdk/types.AccAddress"];</code>
     * @return The signer.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getSigner() {
      return signer_;
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
      if (blockHeight_ != 0L) {
        output.writeInt64(1, blockHeight_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(chain_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, chain_);
      }
      if (transactionSize_ != 0L) {
        output.writeUInt64(3, transactionSize_);
      }
      if (transactionFeeRate_ != 0L) {
        output.writeUInt64(4, transactionFeeRate_);
      }
      if (!signer_.isEmpty()) {
        output.writeBytes(5, signer_);
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (blockHeight_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(1, blockHeight_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(chain_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, chain_);
      }
      if (transactionSize_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt64Size(3, transactionSize_);
      }
      if (transactionFeeRate_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt64Size(4, transactionFeeRate_);
      }
      if (!signer_.isEmpty()) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(5, signer_);
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
      if (!(obj instanceof com.types.MsgNetworkFeeProto.MsgNetworkFee)) {
        return super.equals(obj);
      }
      com.types.MsgNetworkFeeProto.MsgNetworkFee other = (com.types.MsgNetworkFeeProto.MsgNetworkFee) obj;

      if (getBlockHeight()
          != other.getBlockHeight()) return false;
      if (!getChain()
          .equals(other.getChain())) return false;
      if (getTransactionSize()
          != other.getTransactionSize()) return false;
      if (getTransactionFeeRate()
          != other.getTransactionFeeRate()) return false;
      if (!getSigner()
          .equals(other.getSigner())) return false;
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
      hash = (37 * hash) + BLOCK_HEIGHT_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getBlockHeight());
      hash = (37 * hash) + CHAIN_FIELD_NUMBER;
      hash = (53 * hash) + getChain().hashCode();
      hash = (37 * hash) + TRANSACTION_SIZE_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getTransactionSize());
      hash = (37 * hash) + TRANSACTION_FEE_RATE_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getTransactionFeeRate());
      hash = (37 * hash) + SIGNER_FIELD_NUMBER;
      hash = (53 * hash) + getSigner().hashCode();
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.types.MsgNetworkFeeProto.MsgNetworkFee parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.types.MsgNetworkFeeProto.MsgNetworkFee parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.types.MsgNetworkFeeProto.MsgNetworkFee parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.types.MsgNetworkFeeProto.MsgNetworkFee parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.types.MsgNetworkFeeProto.MsgNetworkFee parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.types.MsgNetworkFeeProto.MsgNetworkFee parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.types.MsgNetworkFeeProto.MsgNetworkFee parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.types.MsgNetworkFeeProto.MsgNetworkFee parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static com.types.MsgNetworkFeeProto.MsgNetworkFee parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }

    public static com.types.MsgNetworkFeeProto.MsgNetworkFee parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.types.MsgNetworkFeeProto.MsgNetworkFee parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.types.MsgNetworkFeeProto.MsgNetworkFee parseFrom(
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
    public static Builder newBuilder(com.types.MsgNetworkFeeProto.MsgNetworkFee prototype) {
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
     * Protobuf type {@code types.MsgNetworkFee}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:types.MsgNetworkFee)
        com.types.MsgNetworkFeeProto.MsgNetworkFeeOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.types.MsgNetworkFeeProto.internal_static_types_MsgNetworkFee_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.types.MsgNetworkFeeProto.internal_static_types_MsgNetworkFee_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.types.MsgNetworkFeeProto.MsgNetworkFee.class, com.types.MsgNetworkFeeProto.MsgNetworkFee.Builder.class);
      }

      // Construct using com.types.MsgNetworkFeeProto.MsgNetworkFee.newBuilder()
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
        blockHeight_ = 0L;
        chain_ = "";
        transactionSize_ = 0L;
        transactionFeeRate_ = 0L;
        signer_ = com.google.protobuf.ByteString.EMPTY;
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.types.MsgNetworkFeeProto.internal_static_types_MsgNetworkFee_descriptor;
      }

      @java.lang.Override
      public com.types.MsgNetworkFeeProto.MsgNetworkFee getDefaultInstanceForType() {
        return com.types.MsgNetworkFeeProto.MsgNetworkFee.getDefaultInstance();
      }

      @java.lang.Override
      public com.types.MsgNetworkFeeProto.MsgNetworkFee build() {
        com.types.MsgNetworkFeeProto.MsgNetworkFee result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.types.MsgNetworkFeeProto.MsgNetworkFee buildPartial() {
        com.types.MsgNetworkFeeProto.MsgNetworkFee result = new com.types.MsgNetworkFeeProto.MsgNetworkFee(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(com.types.MsgNetworkFeeProto.MsgNetworkFee result) {
        int from_bitField0_ = bitField0_;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.blockHeight_ = blockHeight_;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.chain_ = chain_;
        }
        if (((from_bitField0_ & 0x00000004) != 0)) {
          result.transactionSize_ = transactionSize_;
        }
        if (((from_bitField0_ & 0x00000008) != 0)) {
          result.transactionFeeRate_ = transactionFeeRate_;
        }
        if (((from_bitField0_ & 0x00000010) != 0)) {
          result.signer_ = signer_;
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
        if (other instanceof com.types.MsgNetworkFeeProto.MsgNetworkFee) {
          return mergeFrom((com.types.MsgNetworkFeeProto.MsgNetworkFee)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.types.MsgNetworkFeeProto.MsgNetworkFee other) {
        if (other == com.types.MsgNetworkFeeProto.MsgNetworkFee.getDefaultInstance()) return this;
        if (other.getBlockHeight() != 0L) {
          setBlockHeight(other.getBlockHeight());
        }
        if (!other.getChain().isEmpty()) {
          chain_ = other.chain_;
          bitField0_ |= 0x00000002;
          onChanged();
        }
        if (other.getTransactionSize() != 0L) {
          setTransactionSize(other.getTransactionSize());
        }
        if (other.getTransactionFeeRate() != 0L) {
          setTransactionFeeRate(other.getTransactionFeeRate());
        }
        if (other.getSigner() != com.google.protobuf.ByteString.EMPTY) {
          setSigner(other.getSigner());
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
                blockHeight_ = input.readInt64();
                bitField0_ |= 0x00000001;
                break;
              } // case 8
              case 18: {
                chain_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000002;
                break;
              } // case 18
              case 24: {
                transactionSize_ = input.readUInt64();
                bitField0_ |= 0x00000004;
                break;
              } // case 24
              case 32: {
                transactionFeeRate_ = input.readUInt64();
                bitField0_ |= 0x00000008;
                break;
              } // case 32
              case 42: {
                signer_ = input.readBytes();
                bitField0_ |= 0x00000010;
                break;
              } // case 42
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

      private long blockHeight_ ;
      /**
       * <code>int64 block_height = 1 [json_name = "blockHeight"];</code>
       * @return The blockHeight.
       */
      @java.lang.Override
      public long getBlockHeight() {
        return blockHeight_;
      }
      /**
       * <code>int64 block_height = 1 [json_name = "blockHeight"];</code>
       * @param value The blockHeight to set.
       * @return This builder for chaining.
       */
      public Builder setBlockHeight(long value) {

        blockHeight_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <code>int64 block_height = 1 [json_name = "blockHeight"];</code>
       * @return This builder for chaining.
       */
      public Builder clearBlockHeight() {
        bitField0_ = (bitField0_ & ~0x00000001);
        blockHeight_ = 0L;
        onChanged();
        return this;
      }

      private java.lang.Object chain_ = "";
      /**
       * <code>string chain = 2 [json_name = "chain", (.gogoproto.casttype) = "gitlab.com/thorchain/thornode/common.Chain"];</code>
       * @return The chain.
       */
      public java.lang.String getChain() {
        java.lang.Object ref = chain_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          chain_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string chain = 2 [json_name = "chain", (.gogoproto.casttype) = "gitlab.com/thorchain/thornode/common.Chain"];</code>
       * @return The bytes for chain.
       */
      public com.google.protobuf.ByteString
          getChainBytes() {
        java.lang.Object ref = chain_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          chain_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string chain = 2 [json_name = "chain", (.gogoproto.casttype) = "gitlab.com/thorchain/thornode/common.Chain"];</code>
       * @param value The chain to set.
       * @return This builder for chaining.
       */
      public Builder setChain(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        chain_ = value;
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <code>string chain = 2 [json_name = "chain", (.gogoproto.casttype) = "gitlab.com/thorchain/thornode/common.Chain"];</code>
       * @return This builder for chaining.
       */
      public Builder clearChain() {
        chain_ = getDefaultInstance().getChain();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
        return this;
      }
      /**
       * <code>string chain = 2 [json_name = "chain", (.gogoproto.casttype) = "gitlab.com/thorchain/thornode/common.Chain"];</code>
       * @param value The bytes for chain to set.
       * @return This builder for chaining.
       */
      public Builder setChainBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        chain_ = value;
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }

      private long transactionSize_ ;
      /**
       * <code>uint64 transaction_size = 3 [json_name = "transactionSize"];</code>
       * @return The transactionSize.
       */
      @java.lang.Override
      public long getTransactionSize() {
        return transactionSize_;
      }
      /**
       * <code>uint64 transaction_size = 3 [json_name = "transactionSize"];</code>
       * @param value The transactionSize to set.
       * @return This builder for chaining.
       */
      public Builder setTransactionSize(long value) {

        transactionSize_ = value;
        bitField0_ |= 0x00000004;
        onChanged();
        return this;
      }
      /**
       * <code>uint64 transaction_size = 3 [json_name = "transactionSize"];</code>
       * @return This builder for chaining.
       */
      public Builder clearTransactionSize() {
        bitField0_ = (bitField0_ & ~0x00000004);
        transactionSize_ = 0L;
        onChanged();
        return this;
      }

      private long transactionFeeRate_ ;
      /**
       * <code>uint64 transaction_fee_rate = 4 [json_name = "transactionFeeRate"];</code>
       * @return The transactionFeeRate.
       */
      @java.lang.Override
      public long getTransactionFeeRate() {
        return transactionFeeRate_;
      }
      /**
       * <code>uint64 transaction_fee_rate = 4 [json_name = "transactionFeeRate"];</code>
       * @param value The transactionFeeRate to set.
       * @return This builder for chaining.
       */
      public Builder setTransactionFeeRate(long value) {

        transactionFeeRate_ = value;
        bitField0_ |= 0x00000008;
        onChanged();
        return this;
      }
      /**
       * <code>uint64 transaction_fee_rate = 4 [json_name = "transactionFeeRate"];</code>
       * @return This builder for chaining.
       */
      public Builder clearTransactionFeeRate() {
        bitField0_ = (bitField0_ & ~0x00000008);
        transactionFeeRate_ = 0L;
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString signer_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>bytes signer = 5 [json_name = "signer", (.gogoproto.casttype) = "github.com/cosmos/cosmos-sdk/types.AccAddress"];</code>
       * @return The signer.
       */
      @java.lang.Override
      public com.google.protobuf.ByteString getSigner() {
        return signer_;
      }
      /**
       * <code>bytes signer = 5 [json_name = "signer", (.gogoproto.casttype) = "github.com/cosmos/cosmos-sdk/types.AccAddress"];</code>
       * @param value The signer to set.
       * @return This builder for chaining.
       */
      public Builder setSigner(com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        signer_ = value;
        bitField0_ |= 0x00000010;
        onChanged();
        return this;
      }
      /**
       * <code>bytes signer = 5 [json_name = "signer", (.gogoproto.casttype) = "github.com/cosmos/cosmos-sdk/types.AccAddress"];</code>
       * @return This builder for chaining.
       */
      public Builder clearSigner() {
        bitField0_ = (bitField0_ & ~0x00000010);
        signer_ = getDefaultInstance().getSigner();
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


      // @@protoc_insertion_point(builder_scope:types.MsgNetworkFee)
    }

    // @@protoc_insertion_point(class_scope:types.MsgNetworkFee)
    private static final com.types.MsgNetworkFeeProto.MsgNetworkFee DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.types.MsgNetworkFeeProto.MsgNetworkFee();
    }

    public static com.types.MsgNetworkFeeProto.MsgNetworkFee getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<MsgNetworkFee>
        PARSER = new com.google.protobuf.AbstractParser<MsgNetworkFee>() {
      @java.lang.Override
      public MsgNetworkFee parsePartialFrom(
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

    public static com.google.protobuf.Parser<MsgNetworkFee> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<MsgNetworkFee> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.types.MsgNetworkFeeProto.MsgNetworkFee getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_types_MsgNetworkFee_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_types_MsgNetworkFee_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n4thorchain/v1/x/thorchain/types/msg_net" +
      "work_fee.proto\022\005types\032\024gogoproto/gogo.pr" +
      "oto\"\240\002\n\rMsgNetworkFee\022!\n\014block_height\030\001 " +
      "\001(\003R\013blockHeight\022D\n\005chain\030\002 \001(\tB.\372\336\037*git" +
      "lab.com/thorchain/thornode/common.ChainR" +
      "\005chain\022)\n\020transaction_size\030\003 \001(\004R\017transa" +
      "ctionSize\0220\n\024transaction_fee_rate\030\004 \001(\004R" +
      "\022transactionFeeRate\022I\n\006signer\030\005 \001(\014B1\372\336\037" +
      "-github.com/cosmos/cosmos-sdk/types.AccA" +
      "ddressR\006signerB\202\001\n\tcom.typesB\022MsgNetwork" +
      "FeeProtoZ/gitlab.com/thorchain/thornode/" +
      "x/thorchain/types\242\002\003TXX\252\002\005Types\312\002\005Types\342" +
      "\002\021Types\\GPBMetadata\352\002\005Typesb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.gogoproto.GogoProto.getDescriptor(),
        });
    internal_static_types_MsgNetworkFee_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_types_MsgNetworkFee_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_types_MsgNetworkFee_descriptor,
        new java.lang.String[] { "BlockHeight", "Chain", "TransactionSize", "TransactionFeeRate", "Signer", });
    com.google.protobuf.ExtensionRegistry registry =
        com.google.protobuf.ExtensionRegistry.newInstance();
    registry.add(com.gogoproto.GogoProto.casttype);
    com.google.protobuf.Descriptors.FileDescriptor
        .internalUpdateFileDescriptor(descriptor, registry);
    com.gogoproto.GogoProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
