// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: kava/earn/v1beta1/params.proto

package kava.earn.v1beta1;

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
      // @@protoc_insertion_point(interface_extends:kava.earn.v1beta1.Params)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
     */
    java.util.List<kava.earn.v1beta1.Vault.AllowedVault> 
        getAllowedVaultsList();
    /**
     * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
     */
    kava.earn.v1beta1.Vault.AllowedVault getAllowedVaults(int index);
    /**
     * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
     */
    int getAllowedVaultsCount();
    /**
     * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
     */
    java.util.List<? extends kava.earn.v1beta1.Vault.AllowedVaultOrBuilder> 
        getAllowedVaultsOrBuilderList();
    /**
     * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
     */
    kava.earn.v1beta1.Vault.AllowedVaultOrBuilder getAllowedVaultsOrBuilder(
        int index);
  }
  /**
   * <pre>
   * Params defines the parameters of the earn module.
   * </pre>
   *
   * Protobuf type {@code kava.earn.v1beta1.Params}
   */
  public static final class Params extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:kava.earn.v1beta1.Params)
      ParamsOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Params.newBuilder() to construct.
    private Params(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Params() {
      allowedVaults_ = java.util.Collections.emptyList();
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
      int mutable_bitField0_ = 0;
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
            case 10: {
              if (!((mutable_bitField0_ & 0x00000001) != 0)) {
                allowedVaults_ = new java.util.ArrayList<kava.earn.v1beta1.Vault.AllowedVault>();
                mutable_bitField0_ |= 0x00000001;
              }
              allowedVaults_.add(
                  input.readMessage(kava.earn.v1beta1.Vault.AllowedVault.parser(), extensionRegistry));
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
        if (((mutable_bitField0_ & 0x00000001) != 0)) {
          allowedVaults_ = java.util.Collections.unmodifiableList(allowedVaults_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return kava.earn.v1beta1.ParamsOuterClass.internal_static_kava_earn_v1beta1_Params_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return kava.earn.v1beta1.ParamsOuterClass.internal_static_kava_earn_v1beta1_Params_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              kava.earn.v1beta1.ParamsOuterClass.Params.class, kava.earn.v1beta1.ParamsOuterClass.Params.Builder.class);
    }

    public static final int ALLOWED_VAULTS_FIELD_NUMBER = 1;
    private java.util.List<kava.earn.v1beta1.Vault.AllowedVault> allowedVaults_;
    /**
     * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
     */
    @java.lang.Override
    public java.util.List<kava.earn.v1beta1.Vault.AllowedVault> getAllowedVaultsList() {
      return allowedVaults_;
    }
    /**
     * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
     */
    @java.lang.Override
    public java.util.List<? extends kava.earn.v1beta1.Vault.AllowedVaultOrBuilder> 
        getAllowedVaultsOrBuilderList() {
      return allowedVaults_;
    }
    /**
     * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
     */
    @java.lang.Override
    public int getAllowedVaultsCount() {
      return allowedVaults_.size();
    }
    /**
     * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
     */
    @java.lang.Override
    public kava.earn.v1beta1.Vault.AllowedVault getAllowedVaults(int index) {
      return allowedVaults_.get(index);
    }
    /**
     * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
     */
    @java.lang.Override
    public kava.earn.v1beta1.Vault.AllowedVaultOrBuilder getAllowedVaultsOrBuilder(
        int index) {
      return allowedVaults_.get(index);
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
      for (int i = 0; i < allowedVaults_.size(); i++) {
        output.writeMessage(1, allowedVaults_.get(i));
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      for (int i = 0; i < allowedVaults_.size(); i++) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, allowedVaults_.get(i));
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
      if (!(obj instanceof kava.earn.v1beta1.ParamsOuterClass.Params)) {
        return super.equals(obj);
      }
      kava.earn.v1beta1.ParamsOuterClass.Params other = (kava.earn.v1beta1.ParamsOuterClass.Params) obj;

      if (!getAllowedVaultsList()
          .equals(other.getAllowedVaultsList())) return false;
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
      if (getAllowedVaultsCount() > 0) {
        hash = (37 * hash) + ALLOWED_VAULTS_FIELD_NUMBER;
        hash = (53 * hash) + getAllowedVaultsList().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static kava.earn.v1beta1.ParamsOuterClass.Params parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static kava.earn.v1beta1.ParamsOuterClass.Params parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static kava.earn.v1beta1.ParamsOuterClass.Params parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static kava.earn.v1beta1.ParamsOuterClass.Params parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static kava.earn.v1beta1.ParamsOuterClass.Params parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static kava.earn.v1beta1.ParamsOuterClass.Params parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static kava.earn.v1beta1.ParamsOuterClass.Params parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static kava.earn.v1beta1.ParamsOuterClass.Params parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static kava.earn.v1beta1.ParamsOuterClass.Params parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static kava.earn.v1beta1.ParamsOuterClass.Params parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static kava.earn.v1beta1.ParamsOuterClass.Params parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static kava.earn.v1beta1.ParamsOuterClass.Params parseFrom(
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
    public static Builder newBuilder(kava.earn.v1beta1.ParamsOuterClass.Params prototype) {
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
     * Params defines the parameters of the earn module.
     * </pre>
     *
     * Protobuf type {@code kava.earn.v1beta1.Params}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:kava.earn.v1beta1.Params)
        kava.earn.v1beta1.ParamsOuterClass.ParamsOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return kava.earn.v1beta1.ParamsOuterClass.internal_static_kava_earn_v1beta1_Params_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return kava.earn.v1beta1.ParamsOuterClass.internal_static_kava_earn_v1beta1_Params_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                kava.earn.v1beta1.ParamsOuterClass.Params.class, kava.earn.v1beta1.ParamsOuterClass.Params.Builder.class);
      }

      // Construct using kava.earn.v1beta1.ParamsOuterClass.Params.newBuilder()
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
          getAllowedVaultsFieldBuilder();
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        if (allowedVaultsBuilder_ == null) {
          allowedVaults_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          allowedVaultsBuilder_.clear();
        }
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return kava.earn.v1beta1.ParamsOuterClass.internal_static_kava_earn_v1beta1_Params_descriptor;
      }

      @java.lang.Override
      public kava.earn.v1beta1.ParamsOuterClass.Params getDefaultInstanceForType() {
        return kava.earn.v1beta1.ParamsOuterClass.Params.getDefaultInstance();
      }

      @java.lang.Override
      public kava.earn.v1beta1.ParamsOuterClass.Params build() {
        kava.earn.v1beta1.ParamsOuterClass.Params result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public kava.earn.v1beta1.ParamsOuterClass.Params buildPartial() {
        kava.earn.v1beta1.ParamsOuterClass.Params result = new kava.earn.v1beta1.ParamsOuterClass.Params(this);
        int from_bitField0_ = bitField0_;
        if (allowedVaultsBuilder_ == null) {
          if (((bitField0_ & 0x00000001) != 0)) {
            allowedVaults_ = java.util.Collections.unmodifiableList(allowedVaults_);
            bitField0_ = (bitField0_ & ~0x00000001);
          }
          result.allowedVaults_ = allowedVaults_;
        } else {
          result.allowedVaults_ = allowedVaultsBuilder_.build();
        }
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
        if (other instanceof kava.earn.v1beta1.ParamsOuterClass.Params) {
          return mergeFrom((kava.earn.v1beta1.ParamsOuterClass.Params)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(kava.earn.v1beta1.ParamsOuterClass.Params other) {
        if (other == kava.earn.v1beta1.ParamsOuterClass.Params.getDefaultInstance()) return this;
        if (allowedVaultsBuilder_ == null) {
          if (!other.allowedVaults_.isEmpty()) {
            if (allowedVaults_.isEmpty()) {
              allowedVaults_ = other.allowedVaults_;
              bitField0_ = (bitField0_ & ~0x00000001);
            } else {
              ensureAllowedVaultsIsMutable();
              allowedVaults_.addAll(other.allowedVaults_);
            }
            onChanged();
          }
        } else {
          if (!other.allowedVaults_.isEmpty()) {
            if (allowedVaultsBuilder_.isEmpty()) {
              allowedVaultsBuilder_.dispose();
              allowedVaultsBuilder_ = null;
              allowedVaults_ = other.allowedVaults_;
              bitField0_ = (bitField0_ & ~0x00000001);
              allowedVaultsBuilder_ = 
                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                   getAllowedVaultsFieldBuilder() : null;
            } else {
              allowedVaultsBuilder_.addAllMessages(other.allowedVaults_);
            }
          }
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
        kava.earn.v1beta1.ParamsOuterClass.Params parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (kava.earn.v1beta1.ParamsOuterClass.Params) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.util.List<kava.earn.v1beta1.Vault.AllowedVault> allowedVaults_ =
        java.util.Collections.emptyList();
      private void ensureAllowedVaultsIsMutable() {
        if (!((bitField0_ & 0x00000001) != 0)) {
          allowedVaults_ = new java.util.ArrayList<kava.earn.v1beta1.Vault.AllowedVault>(allowedVaults_);
          bitField0_ |= 0x00000001;
         }
      }

      private com.google.protobuf.RepeatedFieldBuilderV3<
          kava.earn.v1beta1.Vault.AllowedVault, kava.earn.v1beta1.Vault.AllowedVault.Builder, kava.earn.v1beta1.Vault.AllowedVaultOrBuilder> allowedVaultsBuilder_;

      /**
       * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
       */
      public java.util.List<kava.earn.v1beta1.Vault.AllowedVault> getAllowedVaultsList() {
        if (allowedVaultsBuilder_ == null) {
          return java.util.Collections.unmodifiableList(allowedVaults_);
        } else {
          return allowedVaultsBuilder_.getMessageList();
        }
      }
      /**
       * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
       */
      public int getAllowedVaultsCount() {
        if (allowedVaultsBuilder_ == null) {
          return allowedVaults_.size();
        } else {
          return allowedVaultsBuilder_.getCount();
        }
      }
      /**
       * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
       */
      public kava.earn.v1beta1.Vault.AllowedVault getAllowedVaults(int index) {
        if (allowedVaultsBuilder_ == null) {
          return allowedVaults_.get(index);
        } else {
          return allowedVaultsBuilder_.getMessage(index);
        }
      }
      /**
       * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
       */
      public Builder setAllowedVaults(
          int index, kava.earn.v1beta1.Vault.AllowedVault value) {
        if (allowedVaultsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureAllowedVaultsIsMutable();
          allowedVaults_.set(index, value);
          onChanged();
        } else {
          allowedVaultsBuilder_.setMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
       */
      public Builder setAllowedVaults(
          int index, kava.earn.v1beta1.Vault.AllowedVault.Builder builderForValue) {
        if (allowedVaultsBuilder_ == null) {
          ensureAllowedVaultsIsMutable();
          allowedVaults_.set(index, builderForValue.build());
          onChanged();
        } else {
          allowedVaultsBuilder_.setMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
       */
      public Builder addAllowedVaults(kava.earn.v1beta1.Vault.AllowedVault value) {
        if (allowedVaultsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureAllowedVaultsIsMutable();
          allowedVaults_.add(value);
          onChanged();
        } else {
          allowedVaultsBuilder_.addMessage(value);
        }
        return this;
      }
      /**
       * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
       */
      public Builder addAllowedVaults(
          int index, kava.earn.v1beta1.Vault.AllowedVault value) {
        if (allowedVaultsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureAllowedVaultsIsMutable();
          allowedVaults_.add(index, value);
          onChanged();
        } else {
          allowedVaultsBuilder_.addMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
       */
      public Builder addAllowedVaults(
          kava.earn.v1beta1.Vault.AllowedVault.Builder builderForValue) {
        if (allowedVaultsBuilder_ == null) {
          ensureAllowedVaultsIsMutable();
          allowedVaults_.add(builderForValue.build());
          onChanged();
        } else {
          allowedVaultsBuilder_.addMessage(builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
       */
      public Builder addAllowedVaults(
          int index, kava.earn.v1beta1.Vault.AllowedVault.Builder builderForValue) {
        if (allowedVaultsBuilder_ == null) {
          ensureAllowedVaultsIsMutable();
          allowedVaults_.add(index, builderForValue.build());
          onChanged();
        } else {
          allowedVaultsBuilder_.addMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
       */
      public Builder addAllAllowedVaults(
          java.lang.Iterable<? extends kava.earn.v1beta1.Vault.AllowedVault> values) {
        if (allowedVaultsBuilder_ == null) {
          ensureAllowedVaultsIsMutable();
          com.google.protobuf.AbstractMessageLite.Builder.addAll(
              values, allowedVaults_);
          onChanged();
        } else {
          allowedVaultsBuilder_.addAllMessages(values);
        }
        return this;
      }
      /**
       * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
       */
      public Builder clearAllowedVaults() {
        if (allowedVaultsBuilder_ == null) {
          allowedVaults_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
          onChanged();
        } else {
          allowedVaultsBuilder_.clear();
        }
        return this;
      }
      /**
       * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
       */
      public Builder removeAllowedVaults(int index) {
        if (allowedVaultsBuilder_ == null) {
          ensureAllowedVaultsIsMutable();
          allowedVaults_.remove(index);
          onChanged();
        } else {
          allowedVaultsBuilder_.remove(index);
        }
        return this;
      }
      /**
       * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
       */
      public kava.earn.v1beta1.Vault.AllowedVault.Builder getAllowedVaultsBuilder(
          int index) {
        return getAllowedVaultsFieldBuilder().getBuilder(index);
      }
      /**
       * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
       */
      public kava.earn.v1beta1.Vault.AllowedVaultOrBuilder getAllowedVaultsOrBuilder(
          int index) {
        if (allowedVaultsBuilder_ == null) {
          return allowedVaults_.get(index);  } else {
          return allowedVaultsBuilder_.getMessageOrBuilder(index);
        }
      }
      /**
       * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
       */
      public java.util.List<? extends kava.earn.v1beta1.Vault.AllowedVaultOrBuilder> 
           getAllowedVaultsOrBuilderList() {
        if (allowedVaultsBuilder_ != null) {
          return allowedVaultsBuilder_.getMessageOrBuilderList();
        } else {
          return java.util.Collections.unmodifiableList(allowedVaults_);
        }
      }
      /**
       * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
       */
      public kava.earn.v1beta1.Vault.AllowedVault.Builder addAllowedVaultsBuilder() {
        return getAllowedVaultsFieldBuilder().addBuilder(
            kava.earn.v1beta1.Vault.AllowedVault.getDefaultInstance());
      }
      /**
       * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
       */
      public kava.earn.v1beta1.Vault.AllowedVault.Builder addAllowedVaultsBuilder(
          int index) {
        return getAllowedVaultsFieldBuilder().addBuilder(
            index, kava.earn.v1beta1.Vault.AllowedVault.getDefaultInstance());
      }
      /**
       * <code>repeated .kava.earn.v1beta1.AllowedVault allowed_vaults = 1 [(.gogoproto.nullable) = false, (.gogoproto.castrepeated) = "AllowedVaults"];</code>
       */
      public java.util.List<kava.earn.v1beta1.Vault.AllowedVault.Builder> 
           getAllowedVaultsBuilderList() {
        return getAllowedVaultsFieldBuilder().getBuilderList();
      }
      private com.google.protobuf.RepeatedFieldBuilderV3<
          kava.earn.v1beta1.Vault.AllowedVault, kava.earn.v1beta1.Vault.AllowedVault.Builder, kava.earn.v1beta1.Vault.AllowedVaultOrBuilder> 
          getAllowedVaultsFieldBuilder() {
        if (allowedVaultsBuilder_ == null) {
          allowedVaultsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
              kava.earn.v1beta1.Vault.AllowedVault, kava.earn.v1beta1.Vault.AllowedVault.Builder, kava.earn.v1beta1.Vault.AllowedVaultOrBuilder>(
                  allowedVaults_,
                  ((bitField0_ & 0x00000001) != 0),
                  getParentForChildren(),
                  isClean());
          allowedVaults_ = null;
        }
        return allowedVaultsBuilder_;
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


      // @@protoc_insertion_point(builder_scope:kava.earn.v1beta1.Params)
    }

    // @@protoc_insertion_point(class_scope:kava.earn.v1beta1.Params)
    private static final kava.earn.v1beta1.ParamsOuterClass.Params DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new kava.earn.v1beta1.ParamsOuterClass.Params();
    }

    public static kava.earn.v1beta1.ParamsOuterClass.Params getDefaultInstance() {
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
    public kava.earn.v1beta1.ParamsOuterClass.Params getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_kava_earn_v1beta1_Params_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_kava_earn_v1beta1_Params_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036kava/earn/v1beta1/params.proto\022\021kava.e" +
      "arn.v1beta1\032\024gogoproto/gogo.proto\032\035kava/" +
      "earn/v1beta1/vault.proto\"X\n\006Params\022N\n\016al" +
      "lowed_vaults\030\001 \003(\0132\037.kava.earn.v1beta1.A" +
      "llowedVaultB\025\252\337\037\rAllowedVaults\310\336\037\000B(Z&gi" +
      "thub.com/kava-labs/kava/x/earn/typesb\006pr" +
      "oto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf2.GoGoProtos.getDescriptor(),
          kava.earn.v1beta1.Vault.getDescriptor(),
        });
    internal_static_kava_earn_v1beta1_Params_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_kava_earn_v1beta1_Params_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_kava_earn_v1beta1_Params_descriptor,
        new java.lang.String[] { "AllowedVaults", });
    com.google.protobuf.ExtensionRegistry registry =
        com.google.protobuf.ExtensionRegistry.newInstance();
    registry.add(com.google.protobuf2.GoGoProtos.castrepeated);
    registry.add(com.google.protobuf2.GoGoProtos.nullable);
    com.google.protobuf.Descriptors.FileDescriptor
        .internalUpdateFileDescriptor(descriptor, registry);
    com.google.protobuf2.GoGoProtos.getDescriptor();
    kava.earn.v1beta1.Vault.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
