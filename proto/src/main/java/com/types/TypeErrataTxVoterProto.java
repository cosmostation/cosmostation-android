// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: thorchain/v1/x/thorchain/types/type_errata_tx_voter.proto

package com.types;

public final class TypeErrataTxVoterProto {
  private TypeErrataTxVoterProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ErrataTxVoterOrBuilder extends
      // @@protoc_insertion_point(interface_extends:types.ErrataTxVoter)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string tx_id = 1 [json_name = "txId", (.gogoproto.customname) = "TxID", (.gogoproto.casttype) = "gitlab.com/thorchain/thornode/common.TxID"];</code>
     * @return The txId.
     */
    java.lang.String getTxId();
    /**
     * <code>string tx_id = 1 [json_name = "txId", (.gogoproto.customname) = "TxID", (.gogoproto.casttype) = "gitlab.com/thorchain/thornode/common.TxID"];</code>
     * @return The bytes for txId.
     */
    com.google.protobuf.ByteString
        getTxIdBytes();

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
     * <code>int64 block_height = 3 [json_name = "blockHeight"];</code>
     * @return The blockHeight.
     */
    long getBlockHeight();

    /**
     * <code>repeated string signers = 4 [json_name = "signers"];</code>
     * @return A list containing the signers.
     */
    java.util.List<java.lang.String>
        getSignersList();
    /**
     * <code>repeated string signers = 4 [json_name = "signers"];</code>
     * @return The count of signers.
     */
    int getSignersCount();
    /**
     * <code>repeated string signers = 4 [json_name = "signers"];</code>
     * @param index The index of the element to return.
     * @return The signers at the given index.
     */
    java.lang.String getSigners(int index);
    /**
     * <code>repeated string signers = 4 [json_name = "signers"];</code>
     * @param index The index of the value to return.
     * @return The bytes of the signers at the given index.
     */
    com.google.protobuf.ByteString
        getSignersBytes(int index);
  }
  /**
   * Protobuf type {@code types.ErrataTxVoter}
   */
  public static final class ErrataTxVoter extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:types.ErrataTxVoter)
      ErrataTxVoterOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ErrataTxVoter.newBuilder() to construct.
    private ErrataTxVoter(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ErrataTxVoter() {
      txId_ = "";
      chain_ = "";
      signers_ =
          com.google.protobuf.LazyStringArrayList.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new ErrataTxVoter();
    }

    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.types.TypeErrataTxVoterProto.internal_static_types_ErrataTxVoter_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.types.TypeErrataTxVoterProto.internal_static_types_ErrataTxVoter_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.types.TypeErrataTxVoterProto.ErrataTxVoter.class, com.types.TypeErrataTxVoterProto.ErrataTxVoter.Builder.class);
    }

    public static final int TX_ID_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object txId_ = "";
    /**
     * <code>string tx_id = 1 [json_name = "txId", (.gogoproto.customname) = "TxID", (.gogoproto.casttype) = "gitlab.com/thorchain/thornode/common.TxID"];</code>
     * @return The txId.
     */
    @java.lang.Override
    public java.lang.String getTxId() {
      java.lang.Object ref = txId_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        txId_ = s;
        return s;
      }
    }
    /**
     * <code>string tx_id = 1 [json_name = "txId", (.gogoproto.customname) = "TxID", (.gogoproto.casttype) = "gitlab.com/thorchain/thornode/common.TxID"];</code>
     * @return The bytes for txId.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getTxIdBytes() {
      java.lang.Object ref = txId_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        txId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
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

    public static final int BLOCK_HEIGHT_FIELD_NUMBER = 3;
    private long blockHeight_ = 0L;
    /**
     * <code>int64 block_height = 3 [json_name = "blockHeight"];</code>
     * @return The blockHeight.
     */
    @java.lang.Override
    public long getBlockHeight() {
      return blockHeight_;
    }

    public static final int SIGNERS_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
    private com.google.protobuf.LazyStringArrayList signers_ =
        com.google.protobuf.LazyStringArrayList.emptyList();
    /**
     * <code>repeated string signers = 4 [json_name = "signers"];</code>
     * @return A list containing the signers.
     */
    public com.google.protobuf.ProtocolStringList
        getSignersList() {
      return signers_;
    }
    /**
     * <code>repeated string signers = 4 [json_name = "signers"];</code>
     * @return The count of signers.
     */
    public int getSignersCount() {
      return signers_.size();
    }
    /**
     * <code>repeated string signers = 4 [json_name = "signers"];</code>
     * @param index The index of the element to return.
     * @return The signers at the given index.
     */
    public java.lang.String getSigners(int index) {
      return signers_.get(index);
    }
    /**
     * <code>repeated string signers = 4 [json_name = "signers"];</code>
     * @param index The index of the value to return.
     * @return The bytes of the signers at the given index.
     */
    public com.google.protobuf.ByteString
        getSignersBytes(int index) {
      return signers_.getByteString(index);
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
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(txId_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, txId_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(chain_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, chain_);
      }
      if (blockHeight_ != 0L) {
        output.writeInt64(3, blockHeight_);
      }
      for (int i = 0; i < signers_.size(); i++) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, signers_.getRaw(i));
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(txId_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, txId_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(chain_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, chain_);
      }
      if (blockHeight_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(3, blockHeight_);
      }
      {
        int dataSize = 0;
        for (int i = 0; i < signers_.size(); i++) {
          dataSize += computeStringSizeNoTag(signers_.getRaw(i));
        }
        size += dataSize;
        size += 1 * getSignersList().size();
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
      if (!(obj instanceof com.types.TypeErrataTxVoterProto.ErrataTxVoter)) {
        return super.equals(obj);
      }
      com.types.TypeErrataTxVoterProto.ErrataTxVoter other = (com.types.TypeErrataTxVoterProto.ErrataTxVoter) obj;

      if (!getTxId()
          .equals(other.getTxId())) return false;
      if (!getChain()
          .equals(other.getChain())) return false;
      if (getBlockHeight()
          != other.getBlockHeight()) return false;
      if (!getSignersList()
          .equals(other.getSignersList())) return false;
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
      hash = (37 * hash) + TX_ID_FIELD_NUMBER;
      hash = (53 * hash) + getTxId().hashCode();
      hash = (37 * hash) + CHAIN_FIELD_NUMBER;
      hash = (53 * hash) + getChain().hashCode();
      hash = (37 * hash) + BLOCK_HEIGHT_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getBlockHeight());
      if (getSignersCount() > 0) {
        hash = (37 * hash) + SIGNERS_FIELD_NUMBER;
        hash = (53 * hash) + getSignersList().hashCode();
      }
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.types.TypeErrataTxVoterProto.ErrataTxVoter parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.types.TypeErrataTxVoterProto.ErrataTxVoter parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.types.TypeErrataTxVoterProto.ErrataTxVoter parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.types.TypeErrataTxVoterProto.ErrataTxVoter parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.types.TypeErrataTxVoterProto.ErrataTxVoter parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.types.TypeErrataTxVoterProto.ErrataTxVoter parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.types.TypeErrataTxVoterProto.ErrataTxVoter parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.types.TypeErrataTxVoterProto.ErrataTxVoter parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static com.types.TypeErrataTxVoterProto.ErrataTxVoter parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }

    public static com.types.TypeErrataTxVoterProto.ErrataTxVoter parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.types.TypeErrataTxVoterProto.ErrataTxVoter parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.types.TypeErrataTxVoterProto.ErrataTxVoter parseFrom(
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
    public static Builder newBuilder(com.types.TypeErrataTxVoterProto.ErrataTxVoter prototype) {
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
     * Protobuf type {@code types.ErrataTxVoter}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:types.ErrataTxVoter)
        com.types.TypeErrataTxVoterProto.ErrataTxVoterOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.types.TypeErrataTxVoterProto.internal_static_types_ErrataTxVoter_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.types.TypeErrataTxVoterProto.internal_static_types_ErrataTxVoter_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.types.TypeErrataTxVoterProto.ErrataTxVoter.class, com.types.TypeErrataTxVoterProto.ErrataTxVoter.Builder.class);
      }

      // Construct using com.types.TypeErrataTxVoterProto.ErrataTxVoter.newBuilder()
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
        txId_ = "";
        chain_ = "";
        blockHeight_ = 0L;
        signers_ =
            com.google.protobuf.LazyStringArrayList.emptyList();
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.types.TypeErrataTxVoterProto.internal_static_types_ErrataTxVoter_descriptor;
      }

      @java.lang.Override
      public com.types.TypeErrataTxVoterProto.ErrataTxVoter getDefaultInstanceForType() {
        return com.types.TypeErrataTxVoterProto.ErrataTxVoter.getDefaultInstance();
      }

      @java.lang.Override
      public com.types.TypeErrataTxVoterProto.ErrataTxVoter build() {
        com.types.TypeErrataTxVoterProto.ErrataTxVoter result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.types.TypeErrataTxVoterProto.ErrataTxVoter buildPartial() {
        com.types.TypeErrataTxVoterProto.ErrataTxVoter result = new com.types.TypeErrataTxVoterProto.ErrataTxVoter(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(com.types.TypeErrataTxVoterProto.ErrataTxVoter result) {
        int from_bitField0_ = bitField0_;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.txId_ = txId_;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.chain_ = chain_;
        }
        if (((from_bitField0_ & 0x00000004) != 0)) {
          result.blockHeight_ = blockHeight_;
        }
        if (((from_bitField0_ & 0x00000008) != 0)) {
          signers_.makeImmutable();
          result.signers_ = signers_;
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
        if (other instanceof com.types.TypeErrataTxVoterProto.ErrataTxVoter) {
          return mergeFrom((com.types.TypeErrataTxVoterProto.ErrataTxVoter)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.types.TypeErrataTxVoterProto.ErrataTxVoter other) {
        if (other == com.types.TypeErrataTxVoterProto.ErrataTxVoter.getDefaultInstance()) return this;
        if (!other.getTxId().isEmpty()) {
          txId_ = other.txId_;
          bitField0_ |= 0x00000001;
          onChanged();
        }
        if (!other.getChain().isEmpty()) {
          chain_ = other.chain_;
          bitField0_ |= 0x00000002;
          onChanged();
        }
        if (other.getBlockHeight() != 0L) {
          setBlockHeight(other.getBlockHeight());
        }
        if (!other.signers_.isEmpty()) {
          if (signers_.isEmpty()) {
            signers_ = other.signers_;
            bitField0_ |= 0x00000008;
          } else {
            ensureSignersIsMutable();
            signers_.addAll(other.signers_);
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
              case 10: {
                txId_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000001;
                break;
              } // case 10
              case 18: {
                chain_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000002;
                break;
              } // case 18
              case 24: {
                blockHeight_ = input.readInt64();
                bitField0_ |= 0x00000004;
                break;
              } // case 24
              case 34: {
                java.lang.String s = input.readStringRequireUtf8();
                ensureSignersIsMutable();
                signers_.add(s);
                break;
              } // case 34
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

      private java.lang.Object txId_ = "";
      /**
       * <code>string tx_id = 1 [json_name = "txId", (.gogoproto.customname) = "TxID", (.gogoproto.casttype) = "gitlab.com/thorchain/thornode/common.TxID"];</code>
       * @return The txId.
       */
      public java.lang.String getTxId() {
        java.lang.Object ref = txId_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          txId_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string tx_id = 1 [json_name = "txId", (.gogoproto.customname) = "TxID", (.gogoproto.casttype) = "gitlab.com/thorchain/thornode/common.TxID"];</code>
       * @return The bytes for txId.
       */
      public com.google.protobuf.ByteString
          getTxIdBytes() {
        java.lang.Object ref = txId_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          txId_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string tx_id = 1 [json_name = "txId", (.gogoproto.customname) = "TxID", (.gogoproto.casttype) = "gitlab.com/thorchain/thornode/common.TxID"];</code>
       * @param value The txId to set.
       * @return This builder for chaining.
       */
      public Builder setTxId(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        txId_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <code>string tx_id = 1 [json_name = "txId", (.gogoproto.customname) = "TxID", (.gogoproto.casttype) = "gitlab.com/thorchain/thornode/common.TxID"];</code>
       * @return This builder for chaining.
       */
      public Builder clearTxId() {
        txId_ = getDefaultInstance().getTxId();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
        return this;
      }
      /**
       * <code>string tx_id = 1 [json_name = "txId", (.gogoproto.customname) = "TxID", (.gogoproto.casttype) = "gitlab.com/thorchain/thornode/common.TxID"];</code>
       * @param value The bytes for txId to set.
       * @return This builder for chaining.
       */
      public Builder setTxIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        txId_ = value;
        bitField0_ |= 0x00000001;
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

      private long blockHeight_ ;
      /**
       * <code>int64 block_height = 3 [json_name = "blockHeight"];</code>
       * @return The blockHeight.
       */
      @java.lang.Override
      public long getBlockHeight() {
        return blockHeight_;
      }
      /**
       * <code>int64 block_height = 3 [json_name = "blockHeight"];</code>
       * @param value The blockHeight to set.
       * @return This builder for chaining.
       */
      public Builder setBlockHeight(long value) {

        blockHeight_ = value;
        bitField0_ |= 0x00000004;
        onChanged();
        return this;
      }
      /**
       * <code>int64 block_height = 3 [json_name = "blockHeight"];</code>
       * @return This builder for chaining.
       */
      public Builder clearBlockHeight() {
        bitField0_ = (bitField0_ & ~0x00000004);
        blockHeight_ = 0L;
        onChanged();
        return this;
      }

      private com.google.protobuf.LazyStringArrayList signers_ =
          com.google.protobuf.LazyStringArrayList.emptyList();
      private void ensureSignersIsMutable() {
        if (!signers_.isModifiable()) {
          signers_ = new com.google.protobuf.LazyStringArrayList(signers_);
        }
        bitField0_ |= 0x00000008;
      }
      /**
       * <code>repeated string signers = 4 [json_name = "signers"];</code>
       * @return A list containing the signers.
       */
      public com.google.protobuf.ProtocolStringList
          getSignersList() {
        signers_.makeImmutable();
        return signers_;
      }
      /**
       * <code>repeated string signers = 4 [json_name = "signers"];</code>
       * @return The count of signers.
       */
      public int getSignersCount() {
        return signers_.size();
      }
      /**
       * <code>repeated string signers = 4 [json_name = "signers"];</code>
       * @param index The index of the element to return.
       * @return The signers at the given index.
       */
      public java.lang.String getSigners(int index) {
        return signers_.get(index);
      }
      /**
       * <code>repeated string signers = 4 [json_name = "signers"];</code>
       * @param index The index of the value to return.
       * @return The bytes of the signers at the given index.
       */
      public com.google.protobuf.ByteString
          getSignersBytes(int index) {
        return signers_.getByteString(index);
      }
      /**
       * <code>repeated string signers = 4 [json_name = "signers"];</code>
       * @param index The index to set the value at.
       * @param value The signers to set.
       * @return This builder for chaining.
       */
      public Builder setSigners(
          int index, java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        ensureSignersIsMutable();
        signers_.set(index, value);
        bitField0_ |= 0x00000008;
        onChanged();
        return this;
      }
      /**
       * <code>repeated string signers = 4 [json_name = "signers"];</code>
       * @param value The signers to add.
       * @return This builder for chaining.
       */
      public Builder addSigners(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        ensureSignersIsMutable();
        signers_.add(value);
        bitField0_ |= 0x00000008;
        onChanged();
        return this;
      }
      /**
       * <code>repeated string signers = 4 [json_name = "signers"];</code>
       * @param values The signers to add.
       * @return This builder for chaining.
       */
      public Builder addAllSigners(
          java.lang.Iterable<java.lang.String> values) {
        ensureSignersIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, signers_);
        bitField0_ |= 0x00000008;
        onChanged();
        return this;
      }
      /**
       * <code>repeated string signers = 4 [json_name = "signers"];</code>
       * @return This builder for chaining.
       */
      public Builder clearSigners() {
        signers_ =
          com.google.protobuf.LazyStringArrayList.emptyList();
        bitField0_ = (bitField0_ & ~0x00000008);;
        onChanged();
        return this;
      }
      /**
       * <code>repeated string signers = 4 [json_name = "signers"];</code>
       * @param value The bytes of the signers to add.
       * @return This builder for chaining.
       */
      public Builder addSignersBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        ensureSignersIsMutable();
        signers_.add(value);
        bitField0_ |= 0x00000008;
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


      // @@protoc_insertion_point(builder_scope:types.ErrataTxVoter)
    }

    // @@protoc_insertion_point(class_scope:types.ErrataTxVoter)
    private static final com.types.TypeErrataTxVoterProto.ErrataTxVoter DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.types.TypeErrataTxVoterProto.ErrataTxVoter();
    }

    public static com.types.TypeErrataTxVoterProto.ErrataTxVoter getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ErrataTxVoter>
        PARSER = new com.google.protobuf.AbstractParser<ErrataTxVoter>() {
      @java.lang.Override
      public ErrataTxVoter parsePartialFrom(
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

    public static com.google.protobuf.Parser<ErrataTxVoter> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ErrataTxVoter> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.types.TypeErrataTxVoterProto.ErrataTxVoter getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_types_ErrataTxVoter_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_types_ErrataTxVoter_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n9thorchain/v1/x/thorchain/types/type_er" +
      "rata_tx_voter.proto\022\005types\032\024gogoproto/go" +
      "go.proto\"\336\001\n\rErrataTxVoter\022J\n\005tx_id\030\001 \001(" +
      "\tB5\342\336\037\004TxID\372\336\037)gitlab.com/thorchain/thor" +
      "node/common.TxIDR\004txId\022D\n\005chain\030\002 \001(\tB.\372" +
      "\336\037*gitlab.com/thorchain/thornode/common." +
      "ChainR\005chain\022!\n\014block_height\030\003 \001(\003R\013bloc" +
      "kHeight\022\030\n\007signers\030\004 \003(\tR\007signersB\222\001\n\tco" +
      "m.typesB\026TypeErrataTxVoterProtoZ/gitlab." +
      "com/thorchain/thornode/x/thorchain/types" +
      "\242\002\003TXX\252\002\005Types\312\002\005Types\342\002\021Types\\GPBMetada" +
      "ta\352\002\005Types\310\341\036\000\330\341\036\000\200\342\036\000b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.gogoproto.GogoProto.getDescriptor(),
        });
    internal_static_types_ErrataTxVoter_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_types_ErrataTxVoter_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_types_ErrataTxVoter_descriptor,
        new java.lang.String[] { "TxId", "Chain", "BlockHeight", "Signers", });
    com.google.protobuf.ExtensionRegistry registry =
        com.google.protobuf.ExtensionRegistry.newInstance();
    registry.add(com.gogoproto.GogoProto.casttype);
    registry.add(com.gogoproto.GogoProto.customname);
    registry.add(com.gogoproto.GogoProto.goprotoGettersAll);
    registry.add(com.gogoproto.GogoProto.goprotoStringerAll);
    registry.add(com.gogoproto.GogoProto.stringerAll);
    com.google.protobuf.Descriptors.FileDescriptor
        .internalUpdateFileDescriptor(descriptor, registry);
    com.gogoproto.GogoProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
