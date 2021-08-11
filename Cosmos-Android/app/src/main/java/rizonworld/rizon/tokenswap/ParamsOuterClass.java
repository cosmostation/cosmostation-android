// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tokenswap/params.proto

package rizonworld.rizon.tokenswap;

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
      // @@protoc_insertion_point(interface_extends:rizonworld.rizon.tokenswap.Params)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * swappable indicates whether tokenswap module is enabled or not
     * </pre>
     *
     * <code>bool swappable = 1 [(.gogoproto.moretags) = "yaml:&#92;"swappable&#92;""];</code>
     * @return The swappable.
     */
    boolean getSwappable();

    /**
     * <pre>
     * signer is someone who can request tokenswap
     * </pre>
     *
     * <code>string signer = 2 [(.gogoproto.moretags) = "yaml:&#92;"signer&#92;""];</code>
     * @return The signer.
     */
    java.lang.String getSigner();
    /**
     * <pre>
     * signer is someone who can request tokenswap
     * </pre>
     *
     * <code>string signer = 2 [(.gogoproto.moretags) = "yaml:&#92;"signer&#92;""];</code>
     * @return The bytes for signer.
     */
    com.google.protobuf.ByteString
        getSignerBytes();

    /**
     * <pre>
     * limit is the maximum swappable amount
     * </pre>
     *
     * <code>int64 limit = 3 [(.gogoproto.moretags) = "yaml:&#92;"limit&#92;""];</code>
     * @return The limit.
     */
    long getLimit();
  }
  /**
   * <pre>
   * Params defines the parameters for the tokenswap module
   * </pre>
   *
   * Protobuf type {@code rizonworld.rizon.tokenswap.Params}
   */
  public  static final class Params extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:rizonworld.rizon.tokenswap.Params)
      ParamsOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Params.newBuilder() to construct.
    private Params(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Params() {
      signer_ = "";
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

              swappable_ = input.readBool();
              break;
            }
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();

              signer_ = s;
              break;
            }
            case 24: {

              limit_ = input.readInt64();
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
      return rizonworld.rizon.tokenswap.ParamsOuterClass.internal_static_rizonworld_rizon_tokenswap_Params_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return rizonworld.rizon.tokenswap.ParamsOuterClass.internal_static_rizonworld_rizon_tokenswap_Params_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              rizonworld.rizon.tokenswap.ParamsOuterClass.Params.class, rizonworld.rizon.tokenswap.ParamsOuterClass.Params.Builder.class);
    }

    public static final int SWAPPABLE_FIELD_NUMBER = 1;
    private boolean swappable_;
    /**
     * <pre>
     * swappable indicates whether tokenswap module is enabled or not
     * </pre>
     *
     * <code>bool swappable = 1 [(.gogoproto.moretags) = "yaml:&#92;"swappable&#92;""];</code>
     * @return The swappable.
     */
    public boolean getSwappable() {
      return swappable_;
    }

    public static final int SIGNER_FIELD_NUMBER = 2;
    private volatile java.lang.Object signer_;
    /**
     * <pre>
     * signer is someone who can request tokenswap
     * </pre>
     *
     * <code>string signer = 2 [(.gogoproto.moretags) = "yaml:&#92;"signer&#92;""];</code>
     * @return The signer.
     */
    public java.lang.String getSigner() {
      java.lang.Object ref = signer_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        signer_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * signer is someone who can request tokenswap
     * </pre>
     *
     * <code>string signer = 2 [(.gogoproto.moretags) = "yaml:&#92;"signer&#92;""];</code>
     * @return The bytes for signer.
     */
    public com.google.protobuf.ByteString
        getSignerBytes() {
      java.lang.Object ref = signer_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        signer_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int LIMIT_FIELD_NUMBER = 3;
    private long limit_;
    /**
     * <pre>
     * limit is the maximum swappable amount
     * </pre>
     *
     * <code>int64 limit = 3 [(.gogoproto.moretags) = "yaml:&#92;"limit&#92;""];</code>
     * @return The limit.
     */
    public long getLimit() {
      return limit_;
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
      if (swappable_ != false) {
        output.writeBool(1, swappable_);
      }
      if (!getSignerBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, signer_);
      }
      if (limit_ != 0L) {
        output.writeInt64(3, limit_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (swappable_ != false) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(1, swappable_);
      }
      if (!getSignerBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, signer_);
      }
      if (limit_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(3, limit_);
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
      if (!(obj instanceof rizonworld.rizon.tokenswap.ParamsOuterClass.Params)) {
        return super.equals(obj);
      }
      rizonworld.rizon.tokenswap.ParamsOuterClass.Params other = (rizonworld.rizon.tokenswap.ParamsOuterClass.Params) obj;

      if (getSwappable()
          != other.getSwappable()) return false;
      if (!getSigner()
          .equals(other.getSigner())) return false;
      if (getLimit()
          != other.getLimit()) return false;
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
      hash = (37 * hash) + SWAPPABLE_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
          getSwappable());
      hash = (37 * hash) + SIGNER_FIELD_NUMBER;
      hash = (53 * hash) + getSigner().hashCode();
      hash = (37 * hash) + LIMIT_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getLimit());
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static rizonworld.rizon.tokenswap.ParamsOuterClass.Params parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static rizonworld.rizon.tokenswap.ParamsOuterClass.Params parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static rizonworld.rizon.tokenswap.ParamsOuterClass.Params parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static rizonworld.rizon.tokenswap.ParamsOuterClass.Params parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static rizonworld.rizon.tokenswap.ParamsOuterClass.Params parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static rizonworld.rizon.tokenswap.ParamsOuterClass.Params parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static rizonworld.rizon.tokenswap.ParamsOuterClass.Params parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static rizonworld.rizon.tokenswap.ParamsOuterClass.Params parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static rizonworld.rizon.tokenswap.ParamsOuterClass.Params parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static rizonworld.rizon.tokenswap.ParamsOuterClass.Params parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static rizonworld.rizon.tokenswap.ParamsOuterClass.Params parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static rizonworld.rizon.tokenswap.ParamsOuterClass.Params parseFrom(
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
    public static Builder newBuilder(rizonworld.rizon.tokenswap.ParamsOuterClass.Params prototype) {
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
     * Params defines the parameters for the tokenswap module
     * </pre>
     *
     * Protobuf type {@code rizonworld.rizon.tokenswap.Params}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:rizonworld.rizon.tokenswap.Params)
        rizonworld.rizon.tokenswap.ParamsOuterClass.ParamsOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return rizonworld.rizon.tokenswap.ParamsOuterClass.internal_static_rizonworld_rizon_tokenswap_Params_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return rizonworld.rizon.tokenswap.ParamsOuterClass.internal_static_rizonworld_rizon_tokenswap_Params_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                rizonworld.rizon.tokenswap.ParamsOuterClass.Params.class, rizonworld.rizon.tokenswap.ParamsOuterClass.Params.Builder.class);
      }

      // Construct using rizonworld.rizon.tokenswap.ParamsOuterClass.Params.newBuilder()
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
        swappable_ = false;

        signer_ = "";

        limit_ = 0L;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return rizonworld.rizon.tokenswap.ParamsOuterClass.internal_static_rizonworld_rizon_tokenswap_Params_descriptor;
      }

      @java.lang.Override
      public rizonworld.rizon.tokenswap.ParamsOuterClass.Params getDefaultInstanceForType() {
        return rizonworld.rizon.tokenswap.ParamsOuterClass.Params.getDefaultInstance();
      }

      @java.lang.Override
      public rizonworld.rizon.tokenswap.ParamsOuterClass.Params build() {
        rizonworld.rizon.tokenswap.ParamsOuterClass.Params result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public rizonworld.rizon.tokenswap.ParamsOuterClass.Params buildPartial() {
        rizonworld.rizon.tokenswap.ParamsOuterClass.Params result = new rizonworld.rizon.tokenswap.ParamsOuterClass.Params(this);
        result.swappable_ = swappable_;
        result.signer_ = signer_;
        result.limit_ = limit_;
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
        if (other instanceof rizonworld.rizon.tokenswap.ParamsOuterClass.Params) {
          return mergeFrom((rizonworld.rizon.tokenswap.ParamsOuterClass.Params)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(rizonworld.rizon.tokenswap.ParamsOuterClass.Params other) {
        if (other == rizonworld.rizon.tokenswap.ParamsOuterClass.Params.getDefaultInstance()) return this;
        if (other.getSwappable() != false) {
          setSwappable(other.getSwappable());
        }
        if (!other.getSigner().isEmpty()) {
          signer_ = other.signer_;
          onChanged();
        }
        if (other.getLimit() != 0L) {
          setLimit(other.getLimit());
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
        rizonworld.rizon.tokenswap.ParamsOuterClass.Params parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (rizonworld.rizon.tokenswap.ParamsOuterClass.Params) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private boolean swappable_ ;
      /**
       * <pre>
       * swappable indicates whether tokenswap module is enabled or not
       * </pre>
       *
       * <code>bool swappable = 1 [(.gogoproto.moretags) = "yaml:&#92;"swappable&#92;""];</code>
       * @return The swappable.
       */
      public boolean getSwappable() {
        return swappable_;
      }
      /**
       * <pre>
       * swappable indicates whether tokenswap module is enabled or not
       * </pre>
       *
       * <code>bool swappable = 1 [(.gogoproto.moretags) = "yaml:&#92;"swappable&#92;""];</code>
       * @param value The swappable to set.
       * @return This builder for chaining.
       */
      public Builder setSwappable(boolean value) {
        
        swappable_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * swappable indicates whether tokenswap module is enabled or not
       * </pre>
       *
       * <code>bool swappable = 1 [(.gogoproto.moretags) = "yaml:&#92;"swappable&#92;""];</code>
       * @return This builder for chaining.
       */
      public Builder clearSwappable() {
        
        swappable_ = false;
        onChanged();
        return this;
      }

      private java.lang.Object signer_ = "";
      /**
       * <pre>
       * signer is someone who can request tokenswap
       * </pre>
       *
       * <code>string signer = 2 [(.gogoproto.moretags) = "yaml:&#92;"signer&#92;""];</code>
       * @return The signer.
       */
      public java.lang.String getSigner() {
        java.lang.Object ref = signer_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          signer_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       * signer is someone who can request tokenswap
       * </pre>
       *
       * <code>string signer = 2 [(.gogoproto.moretags) = "yaml:&#92;"signer&#92;""];</code>
       * @return The bytes for signer.
       */
      public com.google.protobuf.ByteString
          getSignerBytes() {
        java.lang.Object ref = signer_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          signer_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * signer is someone who can request tokenswap
       * </pre>
       *
       * <code>string signer = 2 [(.gogoproto.moretags) = "yaml:&#92;"signer&#92;""];</code>
       * @param value The signer to set.
       * @return This builder for chaining.
       */
      public Builder setSigner(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        signer_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * signer is someone who can request tokenswap
       * </pre>
       *
       * <code>string signer = 2 [(.gogoproto.moretags) = "yaml:&#92;"signer&#92;""];</code>
       * @return This builder for chaining.
       */
      public Builder clearSigner() {
        
        signer_ = getDefaultInstance().getSigner();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * signer is someone who can request tokenswap
       * </pre>
       *
       * <code>string signer = 2 [(.gogoproto.moretags) = "yaml:&#92;"signer&#92;""];</code>
       * @param value The bytes for signer to set.
       * @return This builder for chaining.
       */
      public Builder setSignerBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        signer_ = value;
        onChanged();
        return this;
      }

      private long limit_ ;
      /**
       * <pre>
       * limit is the maximum swappable amount
       * </pre>
       *
       * <code>int64 limit = 3 [(.gogoproto.moretags) = "yaml:&#92;"limit&#92;""];</code>
       * @return The limit.
       */
      public long getLimit() {
        return limit_;
      }
      /**
       * <pre>
       * limit is the maximum swappable amount
       * </pre>
       *
       * <code>int64 limit = 3 [(.gogoproto.moretags) = "yaml:&#92;"limit&#92;""];</code>
       * @param value The limit to set.
       * @return This builder for chaining.
       */
      public Builder setLimit(long value) {
        
        limit_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * limit is the maximum swappable amount
       * </pre>
       *
       * <code>int64 limit = 3 [(.gogoproto.moretags) = "yaml:&#92;"limit&#92;""];</code>
       * @return This builder for chaining.
       */
      public Builder clearLimit() {
        
        limit_ = 0L;
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


      // @@protoc_insertion_point(builder_scope:rizonworld.rizon.tokenswap.Params)
    }

    // @@protoc_insertion_point(class_scope:rizonworld.rizon.tokenswap.Params)
    private static final rizonworld.rizon.tokenswap.ParamsOuterClass.Params DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new rizonworld.rizon.tokenswap.ParamsOuterClass.Params();
    }

    public static rizonworld.rizon.tokenswap.ParamsOuterClass.Params getDefaultInstance() {
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
    public rizonworld.rizon.tokenswap.ParamsOuterClass.Params getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_rizonworld_rizon_tokenswap_Params_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_rizonworld_rizon_tokenswap_Params_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\026tokenswap/params.proto\022\032rizonworld.riz" +
      "on.tokenswap\032\024gogoproto/gogo.proto\"{\n\006Pa" +
      "rams\022\'\n\tswappable\030\001 \001(\010B\024\362\336\037\020yaml:\"swapp" +
      "able\"\022!\n\006signer\030\002 \001(\tB\021\362\336\037\ryaml:\"signer\"" +
      "\022\037\n\005limit\030\003 \001(\003B\020\362\336\037\014yaml:\"limit\":\004\230\240\037\000B" +
      "0Z.github.com/rizon-world/rizon/x/tokens" +
      "wap/typesb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf2.GoGoProtos.getDescriptor(),
        });
    internal_static_rizonworld_rizon_tokenswap_Params_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_rizonworld_rizon_tokenswap_Params_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_rizonworld_rizon_tokenswap_Params_descriptor,
        new java.lang.String[] { "Swappable", "Signer", "Limit", });
    com.google.protobuf.ExtensionRegistry registry =
        com.google.protobuf.ExtensionRegistry.newInstance();
    registry.add(com.google.protobuf2.GoGoProtos.goprotoStringer);
    registry.add(com.google.protobuf2.GoGoProtos.moretags);
    com.google.protobuf.Descriptors.FileDescriptor
        .internalUpdateFileDescriptor(descriptor, registry);
    com.google.protobuf2.GoGoProtos.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
