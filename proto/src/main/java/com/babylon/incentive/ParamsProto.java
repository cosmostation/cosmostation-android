// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: babylon/incentive/params.proto

package com.babylon.incentive;

public final class ParamsProto {
  private ParamsProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ParamsOrBuilder extends
      // @@protoc_insertion_point(interface_extends:babylon.incentive.Params)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * btc_staking_portion is the portion of rewards that goes to Finality
     * Providers/delegations NOTE: the portion of each Finality
     * Provider/delegation is calculated by using its voting power and finality
     * provider's commission
     * </pre>
     *
     * <code>string btc_staking_portion = 1 [json_name = "btcStakingPortion", (.gogoproto.nullable) = false, (.gogoproto.customtype) = "cosmossdk.io/math.LegacyDec", (.cosmos_proto.scalar) = "cosmos.Dec"];</code>
     * @return The btcStakingPortion.
     */
    java.lang.String getBtcStakingPortion();
    /**
     * <pre>
     * btc_staking_portion is the portion of rewards that goes to Finality
     * Providers/delegations NOTE: the portion of each Finality
     * Provider/delegation is calculated by using its voting power and finality
     * provider's commission
     * </pre>
     *
     * <code>string btc_staking_portion = 1 [json_name = "btcStakingPortion", (.gogoproto.nullable) = false, (.gogoproto.customtype) = "cosmossdk.io/math.LegacyDec", (.cosmos_proto.scalar) = "cosmos.Dec"];</code>
     * @return The bytes for btcStakingPortion.
     */
    com.google.protobuf.ByteString
        getBtcStakingPortionBytes();
  }
  /**
   * <pre>
   * Params defines the parameters for the module, including portions of rewards
   * distributed to each type of stakeholder. Note that sum of the portions should
   * be strictly less than 1 so that the rest will go to Comet
   * validators/delegations adapted from
   * https://github.com/cosmos/cosmos-sdk/blob/release/v0.47.x/proto/cosmos/distribution/v1beta1/distribution.proto
   * </pre>
   *
   * Protobuf type {@code babylon.incentive.Params}
   */
  public static final class Params extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:babylon.incentive.Params)
      ParamsOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Params.newBuilder() to construct.
    private Params(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Params() {
      btcStakingPortion_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new Params();
    }

    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.babylon.incentive.ParamsProto.internal_static_babylon_incentive_Params_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.babylon.incentive.ParamsProto.internal_static_babylon_incentive_Params_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.babylon.incentive.ParamsProto.Params.class, com.babylon.incentive.ParamsProto.Params.Builder.class);
    }

    public static final int BTC_STAKING_PORTION_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object btcStakingPortion_ = "";
    /**
     * <pre>
     * btc_staking_portion is the portion of rewards that goes to Finality
     * Providers/delegations NOTE: the portion of each Finality
     * Provider/delegation is calculated by using its voting power and finality
     * provider's commission
     * </pre>
     *
     * <code>string btc_staking_portion = 1 [json_name = "btcStakingPortion", (.gogoproto.nullable) = false, (.gogoproto.customtype) = "cosmossdk.io/math.LegacyDec", (.cosmos_proto.scalar) = "cosmos.Dec"];</code>
     * @return The btcStakingPortion.
     */
    @java.lang.Override
    public java.lang.String getBtcStakingPortion() {
      java.lang.Object ref = btcStakingPortion_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        btcStakingPortion_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * btc_staking_portion is the portion of rewards that goes to Finality
     * Providers/delegations NOTE: the portion of each Finality
     * Provider/delegation is calculated by using its voting power and finality
     * provider's commission
     * </pre>
     *
     * <code>string btc_staking_portion = 1 [json_name = "btcStakingPortion", (.gogoproto.nullable) = false, (.gogoproto.customtype) = "cosmossdk.io/math.LegacyDec", (.cosmos_proto.scalar) = "cosmos.Dec"];</code>
     * @return The bytes for btcStakingPortion.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getBtcStakingPortionBytes() {
      java.lang.Object ref = btcStakingPortion_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        btcStakingPortion_ = b;
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
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(btcStakingPortion_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, btcStakingPortion_);
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(btcStakingPortion_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, btcStakingPortion_);
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
      if (!(obj instanceof com.babylon.incentive.ParamsProto.Params)) {
        return super.equals(obj);
      }
      com.babylon.incentive.ParamsProto.Params other = (com.babylon.incentive.ParamsProto.Params) obj;

      if (!getBtcStakingPortion()
          .equals(other.getBtcStakingPortion())) return false;
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
      hash = (37 * hash) + BTC_STAKING_PORTION_FIELD_NUMBER;
      hash = (53 * hash) + getBtcStakingPortion().hashCode();
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.babylon.incentive.ParamsProto.Params parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.babylon.incentive.ParamsProto.Params parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.babylon.incentive.ParamsProto.Params parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.babylon.incentive.ParamsProto.Params parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.babylon.incentive.ParamsProto.Params parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.babylon.incentive.ParamsProto.Params parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.babylon.incentive.ParamsProto.Params parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.babylon.incentive.ParamsProto.Params parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static com.babylon.incentive.ParamsProto.Params parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }

    public static com.babylon.incentive.ParamsProto.Params parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.babylon.incentive.ParamsProto.Params parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.babylon.incentive.ParamsProto.Params parseFrom(
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
    public static Builder newBuilder(com.babylon.incentive.ParamsProto.Params prototype) {
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
     * Params defines the parameters for the module, including portions of rewards
     * distributed to each type of stakeholder. Note that sum of the portions should
     * be strictly less than 1 so that the rest will go to Comet
     * validators/delegations adapted from
     * https://github.com/cosmos/cosmos-sdk/blob/release/v0.47.x/proto/cosmos/distribution/v1beta1/distribution.proto
     * </pre>
     *
     * Protobuf type {@code babylon.incentive.Params}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:babylon.incentive.Params)
        com.babylon.incentive.ParamsProto.ParamsOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.babylon.incentive.ParamsProto.internal_static_babylon_incentive_Params_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.babylon.incentive.ParamsProto.internal_static_babylon_incentive_Params_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.babylon.incentive.ParamsProto.Params.class, com.babylon.incentive.ParamsProto.Params.Builder.class);
      }

      // Construct using com.babylon.incentive.ParamsProto.Params.newBuilder()
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
        btcStakingPortion_ = "";
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.babylon.incentive.ParamsProto.internal_static_babylon_incentive_Params_descriptor;
      }

      @java.lang.Override
      public com.babylon.incentive.ParamsProto.Params getDefaultInstanceForType() {
        return com.babylon.incentive.ParamsProto.Params.getDefaultInstance();
      }

      @java.lang.Override
      public com.babylon.incentive.ParamsProto.Params build() {
        com.babylon.incentive.ParamsProto.Params result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.babylon.incentive.ParamsProto.Params buildPartial() {
        com.babylon.incentive.ParamsProto.Params result = new com.babylon.incentive.ParamsProto.Params(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(com.babylon.incentive.ParamsProto.Params result) {
        int from_bitField0_ = bitField0_;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.btcStakingPortion_ = btcStakingPortion_;
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
        if (other instanceof com.babylon.incentive.ParamsProto.Params) {
          return mergeFrom((com.babylon.incentive.ParamsProto.Params)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.babylon.incentive.ParamsProto.Params other) {
        if (other == com.babylon.incentive.ParamsProto.Params.getDefaultInstance()) return this;
        if (!other.getBtcStakingPortion().isEmpty()) {
          btcStakingPortion_ = other.btcStakingPortion_;
          bitField0_ |= 0x00000001;
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
                btcStakingPortion_ = input.readStringRequireUtf8();
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

      private java.lang.Object btcStakingPortion_ = "";
      /**
       * <pre>
       * btc_staking_portion is the portion of rewards that goes to Finality
       * Providers/delegations NOTE: the portion of each Finality
       * Provider/delegation is calculated by using its voting power and finality
       * provider's commission
       * </pre>
       *
       * <code>string btc_staking_portion = 1 [json_name = "btcStakingPortion", (.gogoproto.nullable) = false, (.gogoproto.customtype) = "cosmossdk.io/math.LegacyDec", (.cosmos_proto.scalar) = "cosmos.Dec"];</code>
       * @return The btcStakingPortion.
       */
      public java.lang.String getBtcStakingPortion() {
        java.lang.Object ref = btcStakingPortion_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          btcStakingPortion_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       * btc_staking_portion is the portion of rewards that goes to Finality
       * Providers/delegations NOTE: the portion of each Finality
       * Provider/delegation is calculated by using its voting power and finality
       * provider's commission
       * </pre>
       *
       * <code>string btc_staking_portion = 1 [json_name = "btcStakingPortion", (.gogoproto.nullable) = false, (.gogoproto.customtype) = "cosmossdk.io/math.LegacyDec", (.cosmos_proto.scalar) = "cosmos.Dec"];</code>
       * @return The bytes for btcStakingPortion.
       */
      public com.google.protobuf.ByteString
          getBtcStakingPortionBytes() {
        java.lang.Object ref = btcStakingPortion_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          btcStakingPortion_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * btc_staking_portion is the portion of rewards that goes to Finality
       * Providers/delegations NOTE: the portion of each Finality
       * Provider/delegation is calculated by using its voting power and finality
       * provider's commission
       * </pre>
       *
       * <code>string btc_staking_portion = 1 [json_name = "btcStakingPortion", (.gogoproto.nullable) = false, (.gogoproto.customtype) = "cosmossdk.io/math.LegacyDec", (.cosmos_proto.scalar) = "cosmos.Dec"];</code>
       * @param value The btcStakingPortion to set.
       * @return This builder for chaining.
       */
      public Builder setBtcStakingPortion(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        btcStakingPortion_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * btc_staking_portion is the portion of rewards that goes to Finality
       * Providers/delegations NOTE: the portion of each Finality
       * Provider/delegation is calculated by using its voting power and finality
       * provider's commission
       * </pre>
       *
       * <code>string btc_staking_portion = 1 [json_name = "btcStakingPortion", (.gogoproto.nullable) = false, (.gogoproto.customtype) = "cosmossdk.io/math.LegacyDec", (.cosmos_proto.scalar) = "cosmos.Dec"];</code>
       * @return This builder for chaining.
       */
      public Builder clearBtcStakingPortion() {
        btcStakingPortion_ = getDefaultInstance().getBtcStakingPortion();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
        return this;
      }
      /**
       * <pre>
       * btc_staking_portion is the portion of rewards that goes to Finality
       * Providers/delegations NOTE: the portion of each Finality
       * Provider/delegation is calculated by using its voting power and finality
       * provider's commission
       * </pre>
       *
       * <code>string btc_staking_portion = 1 [json_name = "btcStakingPortion", (.gogoproto.nullable) = false, (.gogoproto.customtype) = "cosmossdk.io/math.LegacyDec", (.cosmos_proto.scalar) = "cosmos.Dec"];</code>
       * @param value The bytes for btcStakingPortion to set.
       * @return This builder for chaining.
       */
      public Builder setBtcStakingPortionBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        btcStakingPortion_ = value;
        bitField0_ |= 0x00000001;
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


      // @@protoc_insertion_point(builder_scope:babylon.incentive.Params)
    }

    // @@protoc_insertion_point(class_scope:babylon.incentive.Params)
    private static final com.babylon.incentive.ParamsProto.Params DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.babylon.incentive.ParamsProto.Params();
    }

    public static com.babylon.incentive.ParamsProto.Params getDefaultInstance() {
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
    public com.babylon.incentive.ParamsProto.Params getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_babylon_incentive_Params_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_babylon_incentive_Params_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036babylon/incentive/params.proto\022\021babylo" +
      "n.incentive\032\024gogoproto/gogo.proto\032\031cosmo" +
      "s_proto/cosmos.proto\"q\n\006Params\022a\n\023btc_st" +
      "aking_portion\030\001 \001(\tB1\310\336\037\000\332\336\037\033cosmossdk.i" +
      "o/math.LegacyDec\322\264-\ncosmos.DecR\021btcStaki" +
      "ngPortion:\004\230\240\037\000B\274\001\n\025com.babylon.incentiv" +
      "eB\013ParamsProtoZ3github.com/babylonlabs-i" +
      "o/babylon/x/incentive/types\242\002\003BIX\252\002\021Baby" +
      "lon.Incentive\312\002\021Babylon\\Incentive\342\002\035Baby" +
      "lon\\Incentive\\GPBMetadata\352\002\022Babylon::Inc" +
      "entiveb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.gogoproto.GogoProto.getDescriptor(),
          com.cosmos_proto.CosmosProto.getDescriptor(),
        });
    internal_static_babylon_incentive_Params_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_babylon_incentive_Params_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_babylon_incentive_Params_descriptor,
        new java.lang.String[] { "BtcStakingPortion", });
    com.google.protobuf.ExtensionRegistry registry =
        com.google.protobuf.ExtensionRegistry.newInstance();
    registry.add(com.cosmos_proto.CosmosProto.scalar);
    registry.add(com.gogoproto.GogoProto.customtype);
    registry.add(com.gogoproto.GogoProto.goprotoStringer);
    registry.add(com.gogoproto.GogoProto.nullable);
    com.google.protobuf.Descriptors.FileDescriptor
        .internalUpdateFileDescriptor(descriptor, registry);
    com.gogoproto.GogoProto.getDescriptor();
    com.cosmos_proto.CosmosProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
