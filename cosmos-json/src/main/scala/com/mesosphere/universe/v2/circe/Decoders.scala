package com.mesosphere.universe.v2.circe

import com.mesosphere.universe.common.circe.Decoders._
import com.mesosphere.universe.v2.model._
import io.circe.generic.semiauto._
import io.circe.{Decoder, HCursor}

object Decoders {
  implicit val decodeV2License: Decoder[License] = deriveFor[License].decoder
  implicit val decodeV2PackageDetails: Decoder[PackageDetails] = deriveFor[PackageDetails].decoder
  implicit val decodeV2Container: Decoder[Container] = deriveFor[Container].decoder
  implicit val decodeV2Assets: Decoder[Assets] = deriveFor[Assets].decoder
  implicit val decodeV2Images: Decoder[Images] = Decoder.instance { (cursor: HCursor) =>
    for {
      iS <- cursor.downField("icon-small").as[String]
      iM <- cursor.downField("icon-medium").as[String]
      iL <- cursor.downField("icon-large").as[String]
      ss <- cursor.downField("screenshots").as[Option[List[String]]]
    } yield Images(iS, iM, iL, ss)
  }
  implicit val decodeV2Resource: Decoder[Resource] = deriveFor[Resource].decoder
  implicit val decodeV2PackageFiles: Decoder[PackageFiles] = deriveFor[PackageFiles].decoder
  implicit val decodeV2CommandDefinition: Decoder[Command] = deriveFor[Command].decoder
  implicit val decodeV2UniverseVersion: Decoder[UniverseVersion] = Decoder.decodeString.map(UniverseVersion)
  implicit val decodeV2PackagingVersion: Decoder[PackagingVersion] = Decoder.decodeString.map(PackagingVersion)
  implicit val decodeV2PackageRevision: Decoder[ReleaseVersion] = Decoder.decodeString.map(ReleaseVersion)
  implicit val decodeV2PackageDetailsVersion: Decoder[PackageDetailsVersion] = Decoder.decodeString.map(PackageDetailsVersion)
}
