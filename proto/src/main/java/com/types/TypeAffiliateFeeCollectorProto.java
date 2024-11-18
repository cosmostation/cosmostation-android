// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: thorchain/v1/x/thorchain/types/type_affiliate_fee_collector.proto

package com.types;

public final class TypeAffiliateFeeCollectorProto {
  private TypeAffiliateFeeCollectorProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface AffiliateFeeCollectorOrBuilder extends
      // @@protoc_insertion_point(interface_extends:types.AffiliateFeeCollector)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>bytes owner_address = 1 [json_name = "ownerAddress", (.gogoproto.casttype) = "github.com/cosmos/cosmos-sdk/types.AccAddress"];</code>
     * @return The ownerAddress.
     */
    com.google.protobuf.ByteString getOwnerAddress();

    /**
     * <code>string rune_amount = 2 [json_name = "runeAmount", (.gogoproto.nullable) = false, (.gogoproto.customtype) = "github.com/cosmos/cosmos-sdk/types.Uint"];</code>
     * @return The runeAmount.
     */
    java.lang.String getRuneAmount();
    /**
     * <code>string rune_amount = 2 [json_name = "runeAmount", (.gogoproto.nullable) = false, (.gogoproto.customtype) = "github.com/cosmos/cosmos-sdk/types.Uint"];</code>
     * @return The bytes for runeAmount.
     */
    com.google.protobuf.ByteString
        getRuneAmountBytes();
  }
  /**
   * Protobuf type {@code types.AffiliateFeeCollector}
   */
  public static final class AffiliateFeeCollector extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:types.AffiliateFeeCollector)
      AffiliateFeeCollectorOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use AffiliateFeeCollector.newBuilder() to construct.
    private AffiliateFeeCollector(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private AffiliateFeeCollector() {
      ownerAddress_ = com.google.protobuf.ByteString.EMPTY;
      runeAmount_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new AffiliateFeeCollector();
    }

    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.types.TypeAffiliateFeeCollectorProto.internal_static_types_AffiliateFeeCollector_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.types.TypeAffiliateFeeCollectorProto.internal_static_types_AffiliateFeeCollector_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector.class, com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector.Builder.class);
    }

    public static final int OWNER_ADDRESS_FIELD_NUMBER = 1;
    private com.google.protobuf.ByteString ownerAddress_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes owner_address = 1 [json_name = "ownerAddress", (.gogoproto.casttype) = "github.com/cosmos/cosmos-sdk/types.AccAddress"];</code>
     * @return The ownerAddress.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getOwnerAddress() {
      return ownerAddress_;
    }

    public static final int RUNE_AMOUNT_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object runeAmount_ = "";
    /**
     * <code>string rune_amount = 2 [json_name = "runeAmount", (.gogoproto.nullable) = false, (.gogoproto.customtype) = "github.com/cosmos/cosmos-sdk/types.Uint"];</code>
     * @return The runeAmount.
     */
    @java.lang.Override
    public java.lang.String getRuneAmount() {
      java.lang.Object ref = runeAmount_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        runeAmount_ = s;
        return s;
      }
    }
    /**
     * <code>string rune_amount = 2 [json_name = "runeAmount", (.gogoproto.nullable) = false, (.gogoproto.customtype) = "github.com/cosmos/cosmos-sdk/types.Uint"];</code>
     * @return The bytes for runeAmount.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getRuneAmountBytes() {
      java.lang.Object ref = runeAmount_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        runeAmount_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
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
      if (!ownerAddress_.isEmpty()) {
        output.writeBytes(1, ownerAddress_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(runeAmount_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, runeAmount_);
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!ownerAddress_.isEmpty()) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, ownerAddress_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(runeAmount_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, runeAmount_);
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
      if (!(obj instanceof com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector)) {
        return super.equals(obj);
      }
      com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector other = (com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector) obj;

      if (!getOwnerAddress()
          .equals(other.getOwnerAddress())) return false;
      if (!getRuneAmount()
          .equals(other.getRuneAmount())) return false;
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
      hash = (37 * hash) + OWNER_ADDRESS_FIELD_NUMBER;
      hash = (53 * hash) + getOwnerAddress().hashCode();
      hash = (37 * hash) + RUNE_AMOUNT_FIELD_NUMBER;
      hash = (53 * hash) + getRuneAmount().hashCode();
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }

    public static com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector parseFrom(
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
    public static Builder newBuilder(com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector prototype) {
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
     * Protobuf type {@code types.AffiliateFeeCollector}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:types.AffiliateFeeCollector)
        com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollectorOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.types.TypeAffiliateFeeCollectorProto.internal_static_types_AffiliateFeeCollector_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.types.TypeAffiliateFeeCollectorProto.internal_static_types_AffiliateFeeCollector_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector.class, com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector.Builder.class);
      }

      // Construct using com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector.newBuilder()
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
        ownerAddress_ = com.google.protobuf.ByteString.EMPTY;
        runeAmount_ = "";
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.types.TypeAffiliateFeeCollectorProto.internal_static_types_AffiliateFeeCollector_descriptor;
      }

      @java.lang.Override
      public com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector getDefaultInstanceForType() {
        return com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector.getDefaultInstance();
      }

      @java.lang.Override
      public com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector build() {
        com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector buildPartial() {
        com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector result = new com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector result) {
        int from_bitField0_ = bitField0_;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.ownerAddress_ = ownerAddress_;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.runeAmount_ = runeAmount_;
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
        if (other instanceof com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector) {
          return mergeFrom((com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector other) {
        if (other == com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector.getDefaultInstance()) return this;
        if (other.getOwnerAddress() != com.google.protobuf.ByteString.EMPTY) {
          setOwnerAddress(other.getOwnerAddress());
        }
        if (!other.getRuneAmount().isEmpty()) {
          runeAmount_ = other.runeAmount_;
          bitField0_ |= 0x00000002;
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
                ownerAddress_ = input.readBytes();
                bitField0_ |= 0x00000001;
                break;
              } // case 10
              case 18: {
                runeAmount_ = input.readStringRequireUtf8();
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

      private com.google.protobuf.ByteString ownerAddress_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>bytes owner_address = 1 [json_name = "ownerAddress", (.gogoproto.casttype) = "github.com/cosmos/cosmos-sdk/types.AccAddress"];</code>
       * @return The ownerAddress.
       */
      @java.lang.Override
      public com.google.protobuf.ByteString getOwnerAddress() {
        return ownerAddress_;
      }
      /**
       * <code>bytes owner_address = 1 [json_name = "ownerAddress", (.gogoproto.casttype) = "github.com/cosmos/cosmos-sdk/types.AccAddress"];</code>
       * @param value The ownerAddress to set.
       * @return This builder for chaining.
       */
      public Builder setOwnerAddress(com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        ownerAddress_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <code>bytes owner_address = 1 [json_name = "ownerAddress", (.gogoproto.casttype) = "github.com/cosmos/cosmos-sdk/types.AccAddress"];</code>
       * @return This builder for chaining.
       */
      public Builder clearOwnerAddress() {
        bitField0_ = (bitField0_ & ~0x00000001);
        ownerAddress_ = getDefaultInstance().getOwnerAddress();
        onChanged();
        return this;
      }

      private java.lang.Object runeAmount_ = "";
      /**
       * <code>string rune_amount = 2 [json_name = "runeAmount", (.gogoproto.nullable) = false, (.gogoproto.customtype) = "github.com/cosmos/cosmos-sdk/types.Uint"];</code>
       * @return The runeAmount.
       */
      public java.lang.String getRuneAmount() {
        java.lang.Object ref = runeAmount_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          runeAmount_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string rune_amount = 2 [json_name = "runeAmount", (.gogoproto.nullable) = false, (.gogoproto.customtype) = "github.com/cosmos/cosmos-sdk/types.Uint"];</code>
       * @return The bytes for runeAmount.
       */
      public com.google.protobuf.ByteString
          getRuneAmountBytes() {
        java.lang.Object ref = runeAmount_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          runeAmount_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string rune_amount = 2 [json_name = "runeAmount", (.gogoproto.nullable) = false, (.gogoproto.customtype) = "github.com/cosmos/cosmos-sdk/types.Uint"];</code>
       * @param value The runeAmount to set.
       * @return This builder for chaining.
       */
      public Builder setRuneAmount(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        runeAmount_ = value;
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <code>string rune_amount = 2 [json_name = "runeAmount", (.gogoproto.nullable) = false, (.gogoproto.customtype) = "github.com/cosmos/cosmos-sdk/types.Uint"];</code>
       * @return This builder for chaining.
       */
      public Builder clearRuneAmount() {
        runeAmount_ = getDefaultInstance().getRuneAmount();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
        return this;
      }
      /**
       * <code>string rune_amount = 2 [json_name = "runeAmount", (.gogoproto.nullable) = false, (.gogoproto.customtype) = "github.com/cosmos/cosmos-sdk/types.Uint"];</code>
       * @param value The bytes for runeAmount to set.
       * @return This builder for chaining.
       */
      public Builder setRuneAmountBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        runeAmount_ = value;
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


      // @@protoc_insertion_point(builder_scope:types.AffiliateFeeCollector)
    }

    // @@protoc_insertion_point(class_scope:types.AffiliateFeeCollector)
    private static final com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector();
    }

    public static com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<AffiliateFeeCollector>
        PARSER = new com.google.protobuf.AbstractParser<AffiliateFeeCollector>() {
      @java.lang.Override
      public AffiliateFeeCollector parsePartialFrom(
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

    public static com.google.protobuf.Parser<AffiliateFeeCollector> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<AffiliateFeeCollector> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.types.TypeAffiliateFeeCollectorProto.AffiliateFeeCollector getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_types_AffiliateFeeCollector_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_types_AffiliateFeeCollector_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\nAthorchain/v1/x/thorchain/types/type_af" +
      "filiate_fee_collector.proto\022\005types\032\024gogo" +
      "proto/gogo.proto\"\301\001\n\025AffiliateFeeCollect" +
      "or\022V\n\rowner_address\030\001 \001(\014B1\372\336\037-github.co" +
      "m/cosmos/cosmos-sdk/types.AccAddressR\014ow" +
      "nerAddress\022P\n\013rune_amount\030\002 \001(\tB/\310\336\037\000\332\336\037" +
      "\'github.com/cosmos/cosmos-sdk/types.Uint" +
      "R\nruneAmountB\216\001\n\tcom.typesB\036TypeAffiliat" +
      "eFeeCollectorProtoZ/gitlab.com/thorchain" +
      "/thornode/x/thorchain/types\242\002\003TXX\252\002\005Type" +
      "s\312\002\005Types\342\002\021Types\\GPBMetadata\352\002\005Typesb\006p" +
      "roto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.gogoproto.GogoProto.getDescriptor(),
        });
    internal_static_types_AffiliateFeeCollector_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_types_AffiliateFeeCollector_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_types_AffiliateFeeCollector_descriptor,
        new java.lang.String[] { "OwnerAddress", "RuneAmount", });
    com.google.protobuf.ExtensionRegistry registry =
        com.google.protobuf.ExtensionRegistry.newInstance();
    registry.add(com.gogoproto.GogoProto.casttype);
    registry.add(com.gogoproto.GogoProto.customtype);
    registry.add(com.gogoproto.GogoProto.nullable);
    com.google.protobuf.Descriptors.FileDescriptor
        .internalUpdateFileDescriptor(descriptor, registry);
    com.gogoproto.GogoProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
