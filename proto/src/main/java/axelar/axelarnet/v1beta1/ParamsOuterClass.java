// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: axelar/axelarnet/v1beta1/params.proto

package axelar.axelarnet.v1beta1;

public final class ParamsOuterClass {
  private ParamsOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ParamsOrBuilder extends
      // @@protoc_insertion_point(interface_extends:axelar.axelarnet.v1beta1.Params)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * IBC packet route timeout window
     * </pre>
     *
     * <code>uint64 route_timeout_window = 1;</code>
     * @return The routeTimeoutWindow.
     */
    long getRouteTimeoutWindow();

    /**
     * <code>string transaction_fee_rate = 2 [(.gogoproto.nullable) = false, (.gogoproto.customtype) = "github.com/cosmos/cosmos-sdk/types.Dec"];</code>
     * @return The transactionFeeRate.
     */
    java.lang.String getTransactionFeeRate();
    /**
     * <code>string transaction_fee_rate = 2 [(.gogoproto.nullable) = false, (.gogoproto.customtype) = "github.com/cosmos/cosmos-sdk/types.Dec"];</code>
     * @return The bytes for transactionFeeRate.
     */
    com.google.protobuf.ByteString
        getTransactionFeeRateBytes();
  }
  /**
   * <pre>
   * Params represent the genesis parameters for the module
   * </pre>
   *
   * Protobuf type {@code axelar.axelarnet.v1beta1.Params}
   */
  public static final class Params extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:axelar.axelarnet.v1beta1.Params)
      ParamsOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Params.newBuilder() to construct.
    private Params(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Params() {
      transactionFeeRate_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new Params();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private Params(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {

              routeTimeoutWindow_ = input.readUInt64();
              break;
            }
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();

              transactionFeeRate_ = s;
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return axelar.axelarnet.v1beta1.ParamsOuterClass.internal_static_axelar_axelarnet_v1beta1_Params_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return axelar.axelarnet.v1beta1.ParamsOuterClass.internal_static_axelar_axelarnet_v1beta1_Params_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              axelar.axelarnet.v1beta1.ParamsOuterClass.Params.class, axelar.axelarnet.v1beta1.ParamsOuterClass.Params.Builder.class);
    }

    public static final int ROUTE_TIMEOUT_WINDOW_FIELD_NUMBER = 1;
    private long routeTimeoutWindow_;
    /**
     * <pre>
     * IBC packet route timeout window
     * </pre>
     *
     * <code>uint64 route_timeout_window = 1;</code>
     * @return The routeTimeoutWindow.
     */
    @java.lang.Override
    public long getRouteTimeoutWindow() {
      return routeTimeoutWindow_;
    }

    public static final int TRANSACTION_FEE_RATE_FIELD_NUMBER = 2;
    private volatile java.lang.Object transactionFeeRate_;
    /**
     * <code>string transaction_fee_rate = 2 [(.gogoproto.nullable) = false, (.gogoproto.customtype) = "github.com/cosmos/cosmos-sdk/types.Dec"];</code>
     * @return The transactionFeeRate.
     */
    @java.lang.Override
    public java.lang.String getTransactionFeeRate() {
      java.lang.Object ref = transactionFeeRate_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        transactionFeeRate_ = s;
        return s;
      }
    }
    /**
     * <code>string transaction_fee_rate = 2 [(.gogoproto.nullable) = false, (.gogoproto.customtype) = "github.com/cosmos/cosmos-sdk/types.Dec"];</code>
     * @return The bytes for transactionFeeRate.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getTransactionFeeRateBytes() {
      java.lang.Object ref = transactionFeeRate_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        transactionFeeRate_ = b;
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
      if (routeTimeoutWindow_ != 0L) {
        output.writeUInt64(1, routeTimeoutWindow_);
      }
      if (!getTransactionFeeRateBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, transactionFeeRate_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (routeTimeoutWindow_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt64Size(1, routeTimeoutWindow_);
      }
      if (!getTransactionFeeRateBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, transactionFeeRate_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof axelar.axelarnet.v1beta1.ParamsOuterClass.Params)) {
        return super.equals(obj);
      }
      axelar.axelarnet.v1beta1.ParamsOuterClass.Params other = (axelar.axelarnet.v1beta1.ParamsOuterClass.Params) obj;

      if (getRouteTimeoutWindow()
          != other.getRouteTimeoutWindow()) return false;
      if (!getTransactionFeeRate()
          .equals(other.getTransactionFeeRate())) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + ROUTE_TIMEOUT_WINDOW_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getRouteTimeoutWindow());
      hash = (37 * hash) + TRANSACTION_FEE_RATE_FIELD_NUMBER;
      hash = (53 * hash) + getTransactionFeeRate().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static axelar.axelarnet.v1beta1.ParamsOuterClass.Params parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static axelar.axelarnet.v1beta1.ParamsOuterClass.Params parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static axelar.axelarnet.v1beta1.ParamsOuterClass.Params parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static axelar.axelarnet.v1beta1.ParamsOuterClass.Params parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static axelar.axelarnet.v1beta1.ParamsOuterClass.Params parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static axelar.axelarnet.v1beta1.ParamsOuterClass.Params parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static axelar.axelarnet.v1beta1.ParamsOuterClass.Params parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static axelar.axelarnet.v1beta1.ParamsOuterClass.Params parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static axelar.axelarnet.v1beta1.ParamsOuterClass.Params parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static axelar.axelarnet.v1beta1.ParamsOuterClass.Params parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static axelar.axelarnet.v1beta1.ParamsOuterClass.Params parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static axelar.axelarnet.v1beta1.ParamsOuterClass.Params parseFrom(
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
    public static Builder newBuilder(axelar.axelarnet.v1beta1.ParamsOuterClass.Params prototype) {
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
     * Params represent the genesis parameters for the module
     * </pre>
     *
     * Protobuf type {@code axelar.axelarnet.v1beta1.Params}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:axelar.axelarnet.v1beta1.Params)
        axelar.axelarnet.v1beta1.ParamsOuterClass.ParamsOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return axelar.axelarnet.v1beta1.ParamsOuterClass.internal_static_axelar_axelarnet_v1beta1_Params_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return axelar.axelarnet.v1beta1.ParamsOuterClass.internal_static_axelar_axelarnet_v1beta1_Params_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                axelar.axelarnet.v1beta1.ParamsOuterClass.Params.class, axelar.axelarnet.v1beta1.ParamsOuterClass.Params.Builder.class);
      }

      // Construct using axelar.axelarnet.v1beta1.ParamsOuterClass.Params.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        routeTimeoutWindow_ = 0L;

        transactionFeeRate_ = "";

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return axelar.axelarnet.v1beta1.ParamsOuterClass.internal_static_axelar_axelarnet_v1beta1_Params_descriptor;
      }

      @java.lang.Override
      public axelar.axelarnet.v1beta1.ParamsOuterClass.Params getDefaultInstanceForType() {
        return axelar.axelarnet.v1beta1.ParamsOuterClass.Params.getDefaultInstance();
      }

      @java.lang.Override
      public axelar.axelarnet.v1beta1.ParamsOuterClass.Params build() {
        axelar.axelarnet.v1beta1.ParamsOuterClass.Params result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public axelar.axelarnet.v1beta1.ParamsOuterClass.Params buildPartial() {
        axelar.axelarnet.v1beta1.ParamsOuterClass.Params result = new axelar.axelarnet.v1beta1.ParamsOuterClass.Params(this);
        result.routeTimeoutWindow_ = routeTimeoutWindow_;
        result.transactionFeeRate_ = transactionFeeRate_;
        onBuilt();
        return result;
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
        if (other instanceof axelar.axelarnet.v1beta1.ParamsOuterClass.Params) {
          return mergeFrom((axelar.axelarnet.v1beta1.ParamsOuterClass.Params)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(axelar.axelarnet.v1beta1.ParamsOuterClass.Params other) {
        if (other == axelar.axelarnet.v1beta1.ParamsOuterClass.Params.getDefaultInstance()) return this;
        if (other.getRouteTimeoutWindow() != 0L) {
          setRouteTimeoutWindow(other.getRouteTimeoutWindow());
        }
        if (!other.getTransactionFeeRate().isEmpty()) {
          transactionFeeRate_ = other.transactionFeeRate_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
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
        axelar.axelarnet.v1beta1.ParamsOuterClass.Params parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (axelar.axelarnet.v1beta1.ParamsOuterClass.Params) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private long routeTimeoutWindow_ ;
      /**
       * <pre>
       * IBC packet route timeout window
       * </pre>
       *
       * <code>uint64 route_timeout_window = 1;</code>
       * @return The routeTimeoutWindow.
       */
      @java.lang.Override
      public long getRouteTimeoutWindow() {
        return routeTimeoutWindow_;
      }
      /**
       * <pre>
       * IBC packet route timeout window
       * </pre>
       *
       * <code>uint64 route_timeout_window = 1;</code>
       * @param value The routeTimeoutWindow to set.
       * @return This builder for chaining.
       */
      public Builder setRouteTimeoutWindow(long value) {
        
        routeTimeoutWindow_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * IBC packet route timeout window
       * </pre>
       *
       * <code>uint64 route_timeout_window = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearRouteTimeoutWindow() {
        
        routeTimeoutWindow_ = 0L;
        onChanged();
        return this;
      }

      private java.lang.Object transactionFeeRate_ = "";
      /**
       * <code>string transaction_fee_rate = 2 [(.gogoproto.nullable) = false, (.gogoproto.customtype) = "github.com/cosmos/cosmos-sdk/types.Dec"];</code>
       * @return The transactionFeeRate.
       */
      public java.lang.String getTransactionFeeRate() {
        java.lang.Object ref = transactionFeeRate_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          transactionFeeRate_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string transaction_fee_rate = 2 [(.gogoproto.nullable) = false, (.gogoproto.customtype) = "github.com/cosmos/cosmos-sdk/types.Dec"];</code>
       * @return The bytes for transactionFeeRate.
       */
      public com.google.protobuf.ByteString
          getTransactionFeeRateBytes() {
        java.lang.Object ref = transactionFeeRate_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          transactionFeeRate_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string transaction_fee_rate = 2 [(.gogoproto.nullable) = false, (.gogoproto.customtype) = "github.com/cosmos/cosmos-sdk/types.Dec"];</code>
       * @param value The transactionFeeRate to set.
       * @return This builder for chaining.
       */
      public Builder setTransactionFeeRate(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        transactionFeeRate_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string transaction_fee_rate = 2 [(.gogoproto.nullable) = false, (.gogoproto.customtype) = "github.com/cosmos/cosmos-sdk/types.Dec"];</code>
       * @return This builder for chaining.
       */
      public Builder clearTransactionFeeRate() {
        
        transactionFeeRate_ = getDefaultInstance().getTransactionFeeRate();
        onChanged();
        return this;
      }
      /**
       * <code>string transaction_fee_rate = 2 [(.gogoproto.nullable) = false, (.gogoproto.customtype) = "github.com/cosmos/cosmos-sdk/types.Dec"];</code>
       * @param value The bytes for transactionFeeRate to set.
       * @return This builder for chaining.
       */
      public Builder setTransactionFeeRateBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        transactionFeeRate_ = value;
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


      // @@protoc_insertion_point(builder_scope:axelar.axelarnet.v1beta1.Params)
    }

    // @@protoc_insertion_point(class_scope:axelar.axelarnet.v1beta1.Params)
    private static final axelar.axelarnet.v1beta1.ParamsOuterClass.Params DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new axelar.axelarnet.v1beta1.ParamsOuterClass.Params();
    }

    public static axelar.axelarnet.v1beta1.ParamsOuterClass.Params getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Params>
        PARSER = new com.google.protobuf.AbstractParser<Params>() {
      @java.lang.Override
      public Params parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Params(input, extensionRegistry);
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
    public axelar.axelarnet.v1beta1.ParamsOuterClass.Params getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_axelar_axelarnet_v1beta1_Params_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_axelar_axelarnet_v1beta1_Params_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n%axelar/axelarnet/v1beta1/params.proto\022" +
      "\030axelar.axelarnet.v1beta1\032\024gogoproto/gog" +
      "o.proto\"t\n\006Params\022\034\n\024route_timeout_windo" +
      "w\030\001 \001(\004\022L\n\024transaction_fee_rate\030\002 \001(\tB.\332" +
      "\336\037&github.com/cosmos/cosmos-sdk/types.De" +
      "c\310\336\037\000B<Z6github.com/axelarnetwork/axelar" +
      "-core/x/axelarnet/types\310\341\036\000b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf2.GoGoProtos.getDescriptor(),
        });
    internal_static_axelar_axelarnet_v1beta1_Params_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_axelar_axelarnet_v1beta1_Params_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_axelar_axelarnet_v1beta1_Params_descriptor,
        new java.lang.String[] { "RouteTimeoutWindow", "TransactionFeeRate", });
    com.google.protobuf.ExtensionRegistry registry =
        com.google.protobuf.ExtensionRegistry.newInstance();
    registry.add(com.google.protobuf2.GoGoProtos.customtype);
    registry.add(com.google.protobuf2.GoGoProtos.goprotoGettersAll);
    registry.add(com.google.protobuf2.GoGoProtos.nullable);
    com.google.protobuf.Descriptors.FileDescriptor
        .internalUpdateFileDescriptor(descriptor, registry);
    com.google.protobuf2.GoGoProtos.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
