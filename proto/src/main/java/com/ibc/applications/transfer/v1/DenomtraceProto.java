// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ibc/applications/transfer/v1/denomtrace.proto

package com.ibc.applications.transfer.v1;

public final class DenomtraceProto {
  private DenomtraceProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  @java.lang.Deprecated public interface DenomTraceOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ibc.applications.transfer.v1.DenomTrace)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * path defines the chain of port/channel identifiers used for tracing the
     * source of the fungible token.
     * </pre>
     *
     * <code>string path = 1 [json_name = "path"];</code>
     * @return The path.
     */
    java.lang.String getPath();
    /**
     * <pre>
     * path defines the chain of port/channel identifiers used for tracing the
     * source of the fungible token.
     * </pre>
     *
     * <code>string path = 1 [json_name = "path"];</code>
     * @return The bytes for path.
     */
    com.google.protobuf.ByteString
        getPathBytes();

    /**
     * <pre>
     * base denomination of the relayed fungible token.
     * </pre>
     *
     * <code>string base_denom = 2 [json_name = "baseDenom"];</code>
     * @return The baseDenom.
     */
    java.lang.String getBaseDenom();
    /**
     * <pre>
     * base denomination of the relayed fungible token.
     * </pre>
     *
     * <code>string base_denom = 2 [json_name = "baseDenom"];</code>
     * @return The bytes for baseDenom.
     */
    com.google.protobuf.ByteString
        getBaseDenomBytes();
  }
  /**
   * <pre>
   * DenomTrace contains the base denomination for ICS20 fungible tokens and the
   * source tracing information path.
   * </pre>
   *
   * Protobuf type {@code ibc.applications.transfer.v1.DenomTrace}
   */
  @java.lang.Deprecated public static final class DenomTrace extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:ibc.applications.transfer.v1.DenomTrace)
      DenomTraceOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use DenomTrace.newBuilder() to construct.
    private DenomTrace(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private DenomTrace() {
      path_ = "";
      baseDenom_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new DenomTrace();
    }

    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.ibc.applications.transfer.v1.DenomtraceProto.internal_static_ibc_applications_transfer_v1_DenomTrace_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.ibc.applications.transfer.v1.DenomtraceProto.internal_static_ibc_applications_transfer_v1_DenomTrace_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace.class, com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace.Builder.class);
    }

    public static final int PATH_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object path_ = "";
    /**
     * <pre>
     * path defines the chain of port/channel identifiers used for tracing the
     * source of the fungible token.
     * </pre>
     *
     * <code>string path = 1 [json_name = "path"];</code>
     * @return The path.
     */
    @java.lang.Override
    public java.lang.String getPath() {
      java.lang.Object ref = path_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        path_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * path defines the chain of port/channel identifiers used for tracing the
     * source of the fungible token.
     * </pre>
     *
     * <code>string path = 1 [json_name = "path"];</code>
     * @return The bytes for path.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getPathBytes() {
      java.lang.Object ref = path_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        path_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int BASE_DENOM_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object baseDenom_ = "";
    /**
     * <pre>
     * base denomination of the relayed fungible token.
     * </pre>
     *
     * <code>string base_denom = 2 [json_name = "baseDenom"];</code>
     * @return The baseDenom.
     */
    @java.lang.Override
    public java.lang.String getBaseDenom() {
      java.lang.Object ref = baseDenom_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        baseDenom_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * base denomination of the relayed fungible token.
     * </pre>
     *
     * <code>string base_denom = 2 [json_name = "baseDenom"];</code>
     * @return The bytes for baseDenom.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getBaseDenomBytes() {
      java.lang.Object ref = baseDenom_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        baseDenom_ = b;
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
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(path_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, path_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(baseDenom_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, baseDenom_);
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(path_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, path_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(baseDenom_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, baseDenom_);
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
      if (!(obj instanceof com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace)) {
        return super.equals(obj);
      }
      com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace other = (com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace) obj;

      if (!getPath()
          .equals(other.getPath())) return false;
      if (!getBaseDenom()
          .equals(other.getBaseDenom())) return false;
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
      hash = (37 * hash) + PATH_FIELD_NUMBER;
      hash = (53 * hash) + getPath().hashCode();
      hash = (37 * hash) + BASE_DENOM_FIELD_NUMBER;
      hash = (53 * hash) + getBaseDenom().hashCode();
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }

    public static com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace parseFrom(
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
    public static Builder newBuilder(com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace prototype) {
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
     * DenomTrace contains the base denomination for ICS20 fungible tokens and the
     * source tracing information path.
     * </pre>
     *
     * Protobuf type {@code ibc.applications.transfer.v1.DenomTrace}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ibc.applications.transfer.v1.DenomTrace)
        com.ibc.applications.transfer.v1.DenomtraceProto.DenomTraceOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.ibc.applications.transfer.v1.DenomtraceProto.internal_static_ibc_applications_transfer_v1_DenomTrace_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.ibc.applications.transfer.v1.DenomtraceProto.internal_static_ibc_applications_transfer_v1_DenomTrace_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace.class, com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace.Builder.class);
      }

      // Construct using com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace.newBuilder()
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
        path_ = "";
        baseDenom_ = "";
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.ibc.applications.transfer.v1.DenomtraceProto.internal_static_ibc_applications_transfer_v1_DenomTrace_descriptor;
      }

      @java.lang.Override
      public com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace getDefaultInstanceForType() {
        return com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace.getDefaultInstance();
      }

      @java.lang.Override
      public com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace build() {
        com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace buildPartial() {
        com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace result = new com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace result) {
        int from_bitField0_ = bitField0_;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.path_ = path_;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.baseDenom_ = baseDenom_;
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
        if (other instanceof com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace) {
          return mergeFrom((com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace other) {
        if (other == com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace.getDefaultInstance()) return this;
        if (!other.getPath().isEmpty()) {
          path_ = other.path_;
          bitField0_ |= 0x00000001;
          onChanged();
        }
        if (!other.getBaseDenom().isEmpty()) {
          baseDenom_ = other.baseDenom_;
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
                path_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000001;
                break;
              } // case 10
              case 18: {
                baseDenom_ = input.readStringRequireUtf8();
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

      private java.lang.Object path_ = "";
      /**
       * <pre>
       * path defines the chain of port/channel identifiers used for tracing the
       * source of the fungible token.
       * </pre>
       *
       * <code>string path = 1 [json_name = "path"];</code>
       * @return The path.
       */
      public java.lang.String getPath() {
        java.lang.Object ref = path_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          path_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       * path defines the chain of port/channel identifiers used for tracing the
       * source of the fungible token.
       * </pre>
       *
       * <code>string path = 1 [json_name = "path"];</code>
       * @return The bytes for path.
       */
      public com.google.protobuf.ByteString
          getPathBytes() {
        java.lang.Object ref = path_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          path_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * path defines the chain of port/channel identifiers used for tracing the
       * source of the fungible token.
       * </pre>
       *
       * <code>string path = 1 [json_name = "path"];</code>
       * @param value The path to set.
       * @return This builder for chaining.
       */
      public Builder setPath(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        path_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * path defines the chain of port/channel identifiers used for tracing the
       * source of the fungible token.
       * </pre>
       *
       * <code>string path = 1 [json_name = "path"];</code>
       * @return This builder for chaining.
       */
      public Builder clearPath() {
        path_ = getDefaultInstance().getPath();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
        return this;
      }
      /**
       * <pre>
       * path defines the chain of port/channel identifiers used for tracing the
       * source of the fungible token.
       * </pre>
       *
       * <code>string path = 1 [json_name = "path"];</code>
       * @param value The bytes for path to set.
       * @return This builder for chaining.
       */
      public Builder setPathBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        path_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }

      private java.lang.Object baseDenom_ = "";
      /**
       * <pre>
       * base denomination of the relayed fungible token.
       * </pre>
       *
       * <code>string base_denom = 2 [json_name = "baseDenom"];</code>
       * @return The baseDenom.
       */
      public java.lang.String getBaseDenom() {
        java.lang.Object ref = baseDenom_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          baseDenom_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       * base denomination of the relayed fungible token.
       * </pre>
       *
       * <code>string base_denom = 2 [json_name = "baseDenom"];</code>
       * @return The bytes for baseDenom.
       */
      public com.google.protobuf.ByteString
          getBaseDenomBytes() {
        java.lang.Object ref = baseDenom_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          baseDenom_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * base denomination of the relayed fungible token.
       * </pre>
       *
       * <code>string base_denom = 2 [json_name = "baseDenom"];</code>
       * @param value The baseDenom to set.
       * @return This builder for chaining.
       */
      public Builder setBaseDenom(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        baseDenom_ = value;
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * base denomination of the relayed fungible token.
       * </pre>
       *
       * <code>string base_denom = 2 [json_name = "baseDenom"];</code>
       * @return This builder for chaining.
       */
      public Builder clearBaseDenom() {
        baseDenom_ = getDefaultInstance().getBaseDenom();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
        return this;
      }
      /**
       * <pre>
       * base denomination of the relayed fungible token.
       * </pre>
       *
       * <code>string base_denom = 2 [json_name = "baseDenom"];</code>
       * @param value The bytes for baseDenom to set.
       * @return This builder for chaining.
       */
      public Builder setBaseDenomBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        baseDenom_ = value;
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


      // @@protoc_insertion_point(builder_scope:ibc.applications.transfer.v1.DenomTrace)
    }

    // @@protoc_insertion_point(class_scope:ibc.applications.transfer.v1.DenomTrace)
    private static final com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace();
    }

    public static com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<DenomTrace>
        PARSER = new com.google.protobuf.AbstractParser<DenomTrace>() {
      @java.lang.Override
      public DenomTrace parsePartialFrom(
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

    public static com.google.protobuf.Parser<DenomTrace> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<DenomTrace> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.ibc.applications.transfer.v1.DenomtraceProto.DenomTrace getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ibc_applications_transfer_v1_DenomTrace_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ibc_applications_transfer_v1_DenomTrace_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n-ibc/applications/transfer/v1/denomtrac" +
      "e.proto\022\034ibc.applications.transfer.v1\"C\n" +
      "\nDenomTrace\022\022\n\004path\030\001 \001(\tR\004path\022\035\n\nbase_" +
      "denom\030\002 \001(\tR\tbaseDenom:\002\030\001B\376\001\n com.ibc.a" +
      "pplications.transfer.v1B\017DenomtraceProto" +
      "Z8github.com/cosmos/ibc-go/v10/modules/a" +
      "pps/transfer/types\242\002\003IAT\252\002\034Ibc.Applicati" +
      "ons.Transfer.V1\312\002\034Ibc\\Applications\\Trans" +
      "fer\\V1\342\002(Ibc\\Applications\\Transfer\\V1\\GP" +
      "BMetadata\352\002\037Ibc::Applications::Transfer:" +
      ":V1b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_ibc_applications_transfer_v1_DenomTrace_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ibc_applications_transfer_v1_DenomTrace_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ibc_applications_transfer_v1_DenomTrace_descriptor,
        new java.lang.String[] { "Path", "BaseDenom", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
