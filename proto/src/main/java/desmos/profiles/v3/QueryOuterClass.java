// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: desmos/profiles/v3/query.proto

package desmos.profiles.v3;

public final class QueryOuterClass {
  private QueryOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036desmos/profiles/v3/query.proto\022\022desmos" +
      ".profiles.v3\032\024gogoproto/gogo.proto\032\034goog" +
      "le/api/annotations.proto\032&desmos/profile" +
      "s/v3/query_profile.proto\032,desmos/profile" +
      "s/v3/query_dtag_requests.proto\032%desmos/p" +
      "rofiles/v3/query_params.proto\032*desmos/pr" +
      "ofiles/v3/query_chain_links.proto\032(desmo" +
      "s/profiles/v3/query_app_links.proto\032\031cos" +
      "mos_proto/cosmos.proto\032*cosmos/base/quer" +
      "y/v1beta1/pagination.proto2\216\014\n\005Query\022\211\001\n" +
      "\007Profile\022\'.desmos.profiles.v3.QueryProfi" +
      "leRequest\032(.desmos.profiles.v3.QueryProf" +
      "ileResponse\"+\202\323\344\223\002%\022#/desmos/profiles/v3" +
      "/profiles/{user}\022\317\001\n\034IncomingDTagTransfe" +
      "rRequests\022<.desmos.profiles.v3.QueryInco" +
      "mingDTagTransferRequestsRequest\032=.desmos" +
      ".profiles.v3.QueryIncomingDTagTransferRe" +
      "questsResponse\"2\202\323\344\223\002,\022*/desmos/profiles" +
      "/v3/dtag-transfer-requests\022\216\001\n\nChainLink" +
      "s\022*.desmos.profiles.v3.QueryChainLinksRe" +
      "quest\032+.desmos.profiles.v3.QueryChainLin" +
      "ksResponse\"\'\202\323\344\223\002!\022\037/desmos/profiles/v3/" +
      "chain-links\022\244\001\n\017ChainLinkOwners\022/.desmos" +
      ".profiles.v3.QueryChainLinkOwnersRequest" +
      "\0320.desmos.profiles.v3.QueryChainLinkOwne" +
      "rsResponse\".\202\323\344\223\002(\022&/desmos/profiles/v3/" +
      "chain-links/owners\022\307\001\n\030DefaultExternalAd" +
      "dresses\0228.desmos.profiles.v3.QueryDefaul" +
      "tExternalAddressesRequest\0329.desmos.profi" +
      "les.v3.QueryDefaultExternalAddressesResp" +
      "onse\"6\202\323\344\223\0020\022./desmos/profiles/v3/defaul" +
      "t-external-addresses\022\236\001\n\020ApplicationLink" +
      "s\0220.desmos.profiles.v3.QueryApplicationL" +
      "inksRequest\0321.desmos.profiles.v3.QueryAp" +
      "plicationLinksResponse\"%\202\323\344\223\002\037\022\035/desmos/" +
      "profiles/v3/app-links\022\315\001\n\031ApplicationLin" +
      "kByClientID\0229.desmos.profiles.v3.QueryAp" +
      "plicationLinkByClientIDRequest\032:.desmos." +
      "profiles.v3.QueryApplicationLinkByClient" +
      "IDResponse\"9\202\323\344\223\0023\0221/desmos/profiles/v3/" +
      "app-links/clients/{client_id}\022\264\001\n\025Applic" +
      "ationLinkOwners\0225.desmos.profiles.v3.Que" +
      "ryApplicationLinkOwnersRequest\0326.desmos." +
      "profiles.v3.QueryApplicationLinkOwnersRe" +
      "sponse\",\202\323\344\223\002&\022$/desmos/profiles/v3/app-" +
      "links/owners\022}\n\006Params\022&.desmos.profiles" +
      ".v3.QueryParamsRequest\032\'.desmos.profiles" +
      ".v3.QueryParamsResponse\"\"\202\323\344\223\002\034\022\032/desmos" +
      "/profiles/v3/paramsB3Z1github.com/desmos" +
      "-labs/desmos/v4/x/profiles/typesb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf2.GoGoProtos.getDescriptor(),
          com.google.api.AnnotationsProto.getDescriptor(),
          desmos.profiles.v3.QueryProfile.getDescriptor(),
          desmos.profiles.v3.QueryDtagRequests.getDescriptor(),
          desmos.profiles.v3.QueryParams.getDescriptor(),
          desmos.profiles.v3.QueryChainLinks.getDescriptor(),
          desmos.profiles.v3.QueryAppLinks.getDescriptor(),
          cosmos_proto.Cosmos.getDescriptor(),
          cosmos.base.query.v1beta1.Pagination.getDescriptor(),
        });
    com.google.protobuf.ExtensionRegistry registry =
        com.google.protobuf.ExtensionRegistry.newInstance();
    registry.add(com.google.api.AnnotationsProto.http);
    com.google.protobuf.Descriptors.FileDescriptor
        .internalUpdateFileDescriptor(descriptor, registry);
    com.google.protobuf2.GoGoProtos.getDescriptor();
    com.google.api.AnnotationsProto.getDescriptor();
    desmos.profiles.v3.QueryProfile.getDescriptor();
    desmos.profiles.v3.QueryDtagRequests.getDescriptor();
    desmos.profiles.v3.QueryParams.getDescriptor();
    desmos.profiles.v3.QueryChainLinks.getDescriptor();
    desmos.profiles.v3.QueryAppLinks.getDescriptor();
    cosmos_proto.Cosmos.getDescriptor();
    cosmos.base.query.v1beta1.Pagination.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}